package com.telepathicgrunt.repurposedstructures.misc.pooladditions;

import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.events.lifecycle.ServerGoingToStartEvent;
import com.telepathicgrunt.repurposedstructures.mixins.structures.StructurePoolAccessor;
import com.telepathicgrunt.repurposedstructures.modinit.RSConditionsRegistry;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public final class PoolAdditionMergerManager extends SimpleJsonResourceReloadListener<JsonElement> {
    // Needed for detecting the correct files, ignoring file extension, and what JSON parser to use for parsing the files
    public final static PoolAdditionMergerManager POOL_ADDITIONS_MERGER_MANAGER = new PoolAdditionMergerManager();
    private static Map<Identifier, JsonElement> cachedMap = null;

    public PoolAdditionMergerManager() {
        // NOTE: Anyone copying this class, PLEASE CHANGE THE BELOW STRING TO BE UNIQUE!!!!
        // If you do not, both of our mods will read the same file twice and apply the file twice, causing duplicate additions to pools!!!
        super(ExtraCodecs.JSON, FileToIdConverter.json("rs_pool_additions"));
    }

    @Override
    protected void apply(Map<Identifier, JsonElement> loader, ResourceManager manager, ProfilerFiller profiler) {
        cachedMap = loader;
    }

    /**
     * Call this at mod init so we can subscribe our pool merging to run at server startup as that's when the dynamic registry exists.
     */
    public static void mergeAdditionPools(final ServerGoingToStartEvent event) {
        if (cachedMap != null) {
            parsePoolsAndBeginMerger(cachedMap, event.getServer().registryAccess(), event.getServer().getResourceManager());
            cachedMap = null;
        }
    }

    /**
     * Using the given dynamic registry, will now parse the JSON objects of pools and resolve their processors with the dynamic registry.
     * Afterwards, it will merge the parsed pool into the targeted pool found in the dynamic registry.
     */
    private static void parsePoolsAndBeginMerger(Map<Identifier, JsonElement> poolAdditionJSON, RegistryAccess.Frozen frozen, ResourceManager manager) {
        Registry<StructureTemplatePool> poolRegistry = frozen.lookupOrThrow(Registries.TEMPLATE_POOL);
        RegistryOps<JsonElement> customRegistryOps = RegistryOps.create(JsonOps.INSTANCE, frozen);

        // Will iterate over all of our found pool additions and make sure the target pool exists before we parse our JSON objects
        for (Map.Entry<Identifier, JsonElement> entry : poolAdditionJSON.entrySet()) {
            Identifier targetPool = Identifier.parse(entry.getValue().getAsJsonObject().get("target_pool").getAsString());
            if (poolRegistry.get(targetPool) == null) continue;

            // Parse the given pool addition JSON objects and add their pool to the dynamic registry pool
            try {
                AdditionalStructureTemplatePool.DIRECT_CODEC.parse(customRegistryOps, entry.getValue())
                        .resultOrPartial(messageString -> logBadData(entry.getKey(), messageString))
                        .ifPresent(validPool -> mergeIntoExistingPool(validPool, poolRegistry.get(targetPool), manager));
            }
            catch (Exception e) {
                RepurposedStructures.LOGGER.error("""

                        Repurposed Structures: Pool Addition json failed to be parsed.
                        This is usually due to using a mod compat datapack without the other mod being on.
                        File failed to be resolved: %s
                        Target pool: %s
                        Registry being used: %s
                        Error message is: %s""".formatted(entry.getKey(), targetPool, poolRegistry, e.getMessage()).indent(1));
            }
        }
    }

    /**
     * Merges the incoming pool with the given target pool in an additive manner that does not affect any other pools and can be stacked safely.
     */
    private static void mergeIntoExistingPool(AdditionalStructureTemplatePool feedingPool, Optional<Holder.Reference<StructureTemplatePool>> gluttonyPoolOptional, ResourceManager manager) {
        if (gluttonyPoolOptional.isEmpty() || gluttonyPoolOptional.get().value().size() == 0) {
            return;
        }

        StructureTemplatePool gluttonyPool = gluttonyPoolOptional.get().value();

        // Make new copies of lists as the originals are immutable lists and we want to make sure our changes only stays with this pool element
        ObjectArrayList<StructurePoolElement> elements = new ObjectArrayList<>(((StructurePoolAccessor) gluttonyPool).repurposedstructures$getTemplates());
        List<Pair<StructurePoolElement, Integer>> elementCounts = new ArrayList<>(((StructurePoolAccessor) gluttonyPool).repurposedstructures$getRawTemplates());

        elements.addAll(((StructurePoolAccessor) feedingPool).repurposedstructures$getTemplates());
        elementCounts.addAll(((StructurePoolAccessor) feedingPool).repurposedstructures$getRawTemplates());

        ((StructurePoolAccessor) gluttonyPool).repurposedstructures$setTemplates(elements);
        ((StructurePoolAccessor) gluttonyPool).repurposedstructures$setRawTemplates(elementCounts);
    }

    /**
     * Log out the pool that failed to be parsed and what the error is.
     */
    private static void logBadData(Identifier poolPath, String messageString) {
        RepurposedStructures.LOGGER.error("(Repurposed Structures POOL MERGER) Failed to parse {} additions file. Error is: {}", poolPath, messageString);
    }

    private static class AdditionalStructureTemplatePool extends StructureTemplatePool {
        private static final Codec<ExpandedPoolEntry> EXPANDED_POOL_ENTRY_CODEC = RecordCodecBuilder.create(instance -> instance.group(
                StructurePoolElement.CODEC.fieldOf("element").forGetter(ExpandedPoolEntry::poolElement),
                Codec.intRange(1, 5000).fieldOf("weight").forGetter(ExpandedPoolEntry::weight),
                Identifier.CODEC.optionalFieldOf("condition").forGetter(ExpandedPoolEntry::condition)
        ).apply(instance, ExpandedPoolEntry::new));

        public static final Codec<AdditionalStructureTemplatePool> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Identifier.CODEC.fieldOf("target_pool").forGetter(structureTemplatePool -> structureTemplatePool.targetPool),
                Codec.lazyInitialized(StructurePoolAccessor.repurposedstructures$getCODEC_REFERENCE()::getValue).fieldOf("fallback").forGetter(StructureTemplatePool::getFallback),
                EXPANDED_POOL_ENTRY_CODEC.listOf().fieldOf("elements").forGetter(structureTemplatePool -> structureTemplatePool.rawTemplatesWithConditions)
        ).apply(instance, AdditionalStructureTemplatePool::new));

        protected final List<ExpandedPoolEntry> rawTemplatesWithConditions;
        protected final Identifier targetPool;

        public AdditionalStructureTemplatePool(Identifier targetPool, Holder<StructureTemplatePool> fallback, List<ExpandedPoolEntry> rawTemplatesWithConditions) {
            super(fallback, rawTemplatesWithConditions.stream().filter(triple -> {
                if(triple.condition().isPresent()) {
                    Supplier<Boolean> supplier = RSConditionsRegistry.RS_JSON_CONDITIONS_REGISTRY.lookup().get(triple.condition.get());
                    if(supplier != null) {
                        return supplier.get();
                    }
                    else {
                        RepurposedStructures.LOGGER.error("Repurposed Structures Error: Found {} entry has a condition that does not exist. Extra info: {}", targetPool, fallback);
                    }
                }
                return true;
            }).map(triple -> Pair.of(triple.poolElement(), triple.weight())).collect(Collectors.toList()));
            this.rawTemplatesWithConditions = rawTemplatesWithConditions;
            this.targetPool = targetPool;
        }

        public record ExpandedPoolEntry(StructurePoolElement poolElement, Integer weight, Optional<Identifier> condition) {}
    }
}
