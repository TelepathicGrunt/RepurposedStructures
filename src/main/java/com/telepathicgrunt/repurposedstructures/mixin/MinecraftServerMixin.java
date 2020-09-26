package com.telepathicgrunt.repurposedstructures.mixin;

import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.datafixers.DataFixer;
import com.telepathicgrunt.repurposedstructures.RSFeatures;
import com.telepathicgrunt.repurposedstructures.RSStructures;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.resource.ServerResourceManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListenerFactory;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.UserCache;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.SaveProperties;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.level.storage.LevelStorage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;


@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    @Shadow @Final
    protected DynamicRegistryManager.Impl registryManager;

    @Shadow @Final
    private StructureManager structureManager;

    @Inject(
            method = "<init>",
            at = @At(value = "TAIL")
    )
    private void modifyBiomeRegistry(Thread thread, DynamicRegistryManager.Impl dynamicRegistryManager, LevelStorage.Session session, SaveProperties saveProperties, ResourcePackManager resourcePackManager, Proxy proxy, DataFixer dataFixer, ServerResourceManager serverResourceManager, MinecraftSessionService minecraftSessionService, GameProfileRepository gameProfileRepository, UserCache userCache, WorldGenerationProgressListenerFactory worldGenerationProgressListenerFactory, CallbackInfo ci) {

        //Gets blacklisted biome IDs for each structure type
        //Done here so the map can be garbage collected later
        Map<String, List<String>> allBiomeBlacklists = RepurposedStructures.getBiomeBlacklists();

        if(registryManager.getOptional(Registry.BIOME_KEY).isPresent()) {
            for (Map.Entry<RegistryKey<Biome>, Biome> biomeEntry : registryManager.getOptional(Registry.BIOME_KEY).get().getEntries()) {

                // Make the structure and features list mutable for modification later
                List<List<Supplier<ConfiguredFeature<?, ?>>>> tempFeature = ((GenerationSettingsAccessor)biomeEntry.getValue().getGenerationSettings()).getGSFeatures();
                List<List<Supplier<ConfiguredFeature<?, ?>>>> mutableGenerationStages = new ArrayList<>();

                // Fill in generation stages so there are at least 9 or else Minecraft crashes.
                // (we need all stages for adding features/structures to the right stage too)
                for(int currentStageIndex = 0; currentStageIndex < Math.max(10, tempFeature.size()); currentStageIndex++){
                    if(currentStageIndex >= tempFeature.size()){
                        mutableGenerationStages.add(new ArrayList<>());
                    }else{
                        mutableGenerationStages.add(new ArrayList<>(tempFeature.get(currentStageIndex)));
                    }
                }

                // Make the Structure and GenerationStages (features) list mutable for modification later
                ((GenerationSettingsAccessor)biomeEntry.getValue().getGenerationSettings()).setGSFeatures(mutableGenerationStages);
                ((GenerationSettingsAccessor)biomeEntry.getValue().getGenerationSettings()).setGSStructureFeatures(new ArrayList<>(((GenerationSettingsAccessor)biomeEntry.getValue().getGenerationSettings()).getGSStructureFeatures()));

                //Add our structures and features
                RepurposedStructures.addFeaturesAndStructuresToBiomes(
                        biomeEntry.getValue(), // Biome
                        biomeEntry.getKey().getValue(), // Identifier
                        allBiomeBlacklists); // Blacklists
            }
        }

        // Load up the nbt files for several structures at startup instead of during worldgen.
        for(Identifier identifier : RSStructures.RS_STRUCTURE_START_PIECES){
            StructurePool structurePool = dynamicRegistryManager.get(Registry.TEMPLATE_POOL_WORLDGEN).get(identifier);
            if(structurePool != null){
                List<StructurePoolElement> elements = structurePool.getElementIndicesInRandomOrder(new Random());
                for(StructurePoolElement element: elements){
                    // This loads the structure piece to nbt
                    element.getBoundingBox(structureManager, new BlockPos(0,0,0), BlockRotation.NONE);
                }
            }
        }
    }
}
