package com.telepathicgrunt.repurposedstructures.misc.pooladditions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.misc.structurepiececounter.JSONConditionsRegistry;
import com.telepathicgrunt.repurposedstructures.mixin.structures.ListPoolElementAccessor;
import com.telepathicgrunt.repurposedstructures.mixin.structures.SinglePoolElementAccessor;
import com.telepathicgrunt.repurposedstructures.mixin.structures.StructureManagerAccessor;
import com.telepathicgrunt.repurposedstructures.mixin.structures.StructurePoolAccessor;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.levelgen.structure.pools.ListPoolElement;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public final class PoolAdditionMerger {
    private PoolAdditionMerger() {}

    // Needed for detecting the correct files, ignoring file extension, and what JSON parser to use for parsing the files
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().setLenient().disableHtmlEscaping().create();
    private static final String DATA_TYPE = "pool_additions";
    private static final int FILE_SUFFIX_LENGTH = ".json".length();

    /**
     * Call this at mod init so we can subscribe our pool merging to run at server startup as that's when the dynamic registry exists.
     */
    public static void mergeAdditionPools() {
        ServerLifecycleEvents.SERVER_STARTING.register((MinecraftServer minecraftServer) -> {
            ResourceManager resourceManager = ((StructureManagerAccessor) minecraftServer.getStructureManager()).repurposedstructures_getResourceManager();
            Map<ResourceLocation, List<JsonElement>> poolAdditionJSON = GeneralUtils.getAllDatapacksJSONElement(resourceManager, GSON, DATA_TYPE, FILE_SUFFIX_LENGTH);
            parsePoolsAndBeginMerger(poolAdditionJSON, minecraftServer.registryAccess(), minecraftServer.getStructureManager());
        });
    }

    /**
     * Using the given dynamic registry, will now parse the JSON objects of pools and resolve their processors with the dynamic registry.
     * Afterwards, it will merge the parsed pool into the targeted pool found in the dynamic registry.
     */
    private static void parsePoolsAndBeginMerger(Map<ResourceLocation, List<JsonElement>> poolAdditionJSON, RegistryAccess dynamicRegistryManager, StructureManager structureManager) {
        Registry<StructureTemplatePool> poolRegistry = dynamicRegistryManager.ownedRegistryOrThrow(Registry.TEMPLATE_POOL_REGISTRY);
        RegistryOps<JsonElement> customRegistryOps = RegistryOps.create(JsonOps.INSTANCE, dynamicRegistryManager);

        // Will iterate over all of our found pool additions and make sure the target pool exists before we parse our JSON objects
        for (Map.Entry<ResourceLocation, List<JsonElement>> entry : poolAdditionJSON.entrySet()) {
            if (poolRegistry.get(entry.getKey()) == null) continue;

            // Parse the given pool addition JSON objects and add their pool to the dynamic registry pool
            for (JsonElement jsonElement : entry.getValue()) {
                try {
                    AdditionalStructureTemplatePool.DIRECT_CODEC.parse(customRegistryOps, jsonElement)
                            .resultOrPartial(messageString -> logBadData(entry.getKey(), messageString))
                            .ifPresent(validPool -> mergeIntoExistingPool(validPool, poolRegistry.get(entry.getKey()), structureManager));
                }
                catch (Exception e) {
                    RepurposedStructures.LOGGER.error("""

                            Repurposed Structures: Pool Addition json failed to be parsed.
                            This is usually due to using a mod compat datapack without the other mod being on.
                            Entry failed to be resolved: %s
                            Registry being used: %s
                            Error message is: %s""".formatted(entry.getKey(), poolRegistry, e.getMessage()).indent(1));
                }
            }
        }
    }

    /**
     * Merges the incoming pool with the given target pool in an additive manner that does not affect any other pools and can be stacked safely.
     */
    private static void mergeIntoExistingPool(AdditionalStructureTemplatePool feedingPool, StructureTemplatePool gluttonyPool, StructureManager structureManager) {
        // Make new copies of lists as the originals are immutable lists and we want to make sure our changes only stays with this pool element
        List<StructurePoolElement> elements = new ArrayList<>(((StructurePoolAccessor) gluttonyPool).repurposedstructures_getTemplates());
        List<Pair<StructurePoolElement, Integer>> elementCounts = new ArrayList<>(((StructurePoolAccessor) gluttonyPool).repurposedstructures_getRawTemplates());

        elements.addAll(((StructurePoolAccessor) feedingPool).repurposedstructures_getTemplates());
        elementCounts.addAll(((StructurePoolAccessor) feedingPool).repurposedstructures_getRawTemplates());

        // Helps people know if they typoed their merger pool's nbt file paths
        for(StructurePoolElement element : elements) {
            if(element instanceof SinglePoolElement singlePoolElement) {
                Optional<ResourceLocation> nbtID = ((SinglePoolElementAccessor)singlePoolElement).repurposedstructures_getTemplate().left();
                if(nbtID.isEmpty()) continue;
                Optional<StructureTemplate> structureTemplate = structureManager.get(nbtID.get());
                if(structureTemplate.isEmpty()) {
                    RepurposedStructures.LOGGER.error("(Repurposed Structures POOL MERGER) Found an entry in {} that points to the non-existent nbt file called {}", feedingPool.getName(), nbtID.get());
                }
            }
            else if(element instanceof ListPoolElement listPoolElement) {
                for(StructurePoolElement listElement : ((ListPoolElementAccessor)listPoolElement).repurposedstructures_getElements()) {
                    if(listElement instanceof SinglePoolElement singlePoolElement) {
                        Optional<ResourceLocation> nbtID = ((SinglePoolElementAccessor) singlePoolElement).repurposedstructures_getTemplate().left();
                        if (nbtID.isEmpty()) continue;
                        Optional<StructureTemplate> structureTemplate = structureManager.get(nbtID.get());
                        if (structureTemplate.isEmpty()) {
                            RepurposedStructures.LOGGER.error("(Repurposed Structures POOL MERGER) Found an entry in {} that points to the non-existent nbt file called {}", feedingPool.getName(), nbtID.get());
                        }
                    }
                }
            }
        }

        ((StructurePoolAccessor) gluttonyPool).repurposedstructures_setTemplates(elements);
        ((StructurePoolAccessor) gluttonyPool).repurposedstructures_setRawTemplates(elementCounts);
    }

    /**
     * Log out the pool that failed to be parsed and what the error is.
     */
    private static void logBadData(ResourceLocation poolPath, String messageString) {
        RepurposedStructures.LOGGER.error("(Repurposed Structures POOL MERGER) Failed to parse {} additions file. Error is: {}", poolPath, messageString);
    }


    private static class AdditionalStructureTemplatePool extends StructureTemplatePool {
        private static final Codec<ExpandedPoolEntry> EXPANDED_POOL_ENTRY_CODEC = RecordCodecBuilder.create(instance -> instance.group(
                StructurePoolElement.CODEC.fieldOf("element").forGetter(ExpandedPoolEntry::poolElement),
                Codec.intRange(1, 5000).fieldOf("weight").forGetter(ExpandedPoolEntry::weight),
                ResourceLocation.CODEC.optionalFieldOf("condition").forGetter(ExpandedPoolEntry::condition)
        ).apply(instance, ExpandedPoolEntry::new));

        public static final Codec<AdditionalStructureTemplatePool> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance.group(
                ResourceLocation.CODEC.fieldOf("name").forGetter(StructureTemplatePool::getName),
                ResourceLocation.CODEC.fieldOf("fallback").forGetter(StructureTemplatePool::getFallback),
                EXPANDED_POOL_ENTRY_CODEC.listOf().fieldOf("elements").forGetter(structureTemplatePool -> structureTemplatePool.rawTemplatesWithConditions)
        ).apply(instance, AdditionalStructureTemplatePool::new));

        protected final List<ExpandedPoolEntry> rawTemplatesWithConditions;

        public AdditionalStructureTemplatePool(ResourceLocation resourceLocation, ResourceLocation resourceLocation2, List<ExpandedPoolEntry> rawTemplatesWithConditions) {
            super(resourceLocation, resourceLocation2, rawTemplatesWithConditions.stream().filter(triple -> {
                if(triple.condition().isPresent()) {
                    Optional<Supplier<Boolean>> optionalSupplier = JSONConditionsRegistry.RS_JSON_CONDITIONS_REGISTRY.getOptional(triple.condition.get());
                    if(optionalSupplier.isPresent()) {
                        return optionalSupplier.get().get();
                    }
                    else {
                        RepurposedStructures.LOGGER.error("Repurposed Structures Error: Found {} entry has a condition that does not exist. Extra info: {}", resourceLocation, resourceLocation2);
                    }
                }
                return true;
            }).map(triple -> Pair.of(triple.poolElement(), triple.weight())).collect(Collectors.toList()));
            this.rawTemplatesWithConditions = rawTemplatesWithConditions;
        }

        public record ExpandedPoolEntry(StructurePoolElement poolElement, Integer weight, Optional<ResourceLocation> condition) {}
    }
}
