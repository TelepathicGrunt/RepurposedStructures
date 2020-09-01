package com.telepathicgrunt.repurposedstructures.mixin;

import com.google.common.collect.ImmutableMap;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.datafixers.DataFixer;
import com.telepathicgrunt.repurposedstructures.RSFeatures;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.resources.DataPackRegistries;
import net.minecraft.resources.ResourcePackList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.listener.IChunkStatusListenerFactory;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.storage.IServerConfiguration;
import net.minecraft.world.storage.SaveFormat;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.net.Proxy;
import java.util.*;
import java.util.function.Supplier;


@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    @Shadow @Final
    protected DynamicRegistries.Impl registryManager;

    @Inject(
            method = "<init>",
            at = @At(value = "TAIL")
    )
    private void modifyBiomeRegistry(Thread p_i232576_1_, DynamicRegistries.Impl impl, SaveFormat.LevelSave p_i232576_3_, IServerConfiguration p_i232576_4_, ResourcePackList p_i232576_5_, Proxy p_i232576_6_, DataFixer p_i232576_7_, DataPackRegistries p_i232576_8_, MinecraftSessionService p_i232576_9_, GameProfileRepository p_i232576_10_, PlayerProfileCache p_i232576_11_, IChunkStatusListenerFactory p_i232576_12_, CallbackInfo ci) {

        //Gets blacklisted biome IDs for each structure type
        //Done here so the map can be garbage collected later
        Map<String, List<String>> allBiomeBlacklists = new HashMap<>();
        allBiomeBlacklists.put("dungeon", Arrays.asList(RepurposedStructures.RSDungeonsConfig.blacklistedDungeonBiomes.get().split(",")));
        allBiomeBlacklists.put("boulder", Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedBoulderBiomes.get().split(",")));
        allBiomeBlacklists.put("swamp_tree", Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedSwampTreeBiomes.get().split(",")));
        allBiomeBlacklists.put("fortress", Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedFortressBiomes.get().split(",")));
        allBiomeBlacklists.put("igloo", Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedIglooBiomes.get().split(",")));
        allBiomeBlacklists.put("mineshaft", Arrays.asList(RepurposedStructures.RSMineshaftsConfig.blacklistedMineshaftBiomes.get().split(",")));
        allBiomeBlacklists.put("outpost", Arrays.asList(RepurposedStructures.RSOutpostsConfig.blacklistedOutpostBiomes.get().split(",")));
        allBiomeBlacklists.put("shipwreck", Arrays.asList(RepurposedStructures.RSShipwrecksConfig.blacklistedShipwreckBiomes.get().split(",")));
        allBiomeBlacklists.put("stronghold", Arrays.asList(RepurposedStructures.RSStrongholdsConfig.blacklistedStrongholdBiomes.get().split(",")));
        allBiomeBlacklists.put("temple", Arrays.asList(RepurposedStructures.RSTemplesConfig.blacklistedTempleBiomes.get().split(",")));
        allBiomeBlacklists.put("pyramid", Arrays.asList(RepurposedStructures.RSTemplesConfig.blacklistedPyramidBiomes.get().split(",")));
        allBiomeBlacklists.put("village", Arrays.asList(RepurposedStructures.RSVillagesConfig.blacklistedVillageBiomes.get().split(",")));
        allBiomeBlacklists.put("well", Arrays.asList(RepurposedStructures.RSWellsConfig.blacklistedWellBiomes.get().split(",")));

        if(registryManager.getOptional(Registry.BIOME_KEY).isPresent()) {
            // Make the structure and features list mutable for modification later
            for (Biome biome : registryManager.getOptional(Registry.BIOME_KEY).get()) {
                List<List<Supplier<ConfiguredFeature<?, ?>>>> tempFeature = ((GenerationSettingsAccessor)biome.getGenerationSettings()).getGSFeatures();
                List<List<Supplier<ConfiguredFeature<?, ?>>>> mutableFeatures = new ArrayList<>();
                for(int i = 0; i < Math.max(10, tempFeature.size()); i++){
                    if(i >= tempFeature.size()){
                        mutableFeatures.add(new ArrayList<>());
                    }
                    else{
                        mutableFeatures.add(new ArrayList<>(tempFeature.get(i)));
                    }
                }
                ((GenerationSettingsAccessor)biome.getGenerationSettings()).setGSFeatures(mutableFeatures);
                ((GenerationSettingsAccessor)biome.getGenerationSettings()).setGSStructureFeatures(new ArrayList<>(((GenerationSettingsAccessor)biome.getGenerationSettings()).getGSStructureFeatures()));

                //Add our structures and features
                RepurposedStructures.addFeaturesAndStructuresToBiomes(registryManager.getOptional(Registry.BIOME_KEY).get(), biome, Objects.requireNonNull(registryManager.getOptional(Registry.BIOME_KEY).get().getKey(biome)), allBiomeBlacklists);

                //add our structure spacing to all chunkgenerators
                for(DimensionSettings dimensionSettings : registryManager.get(Registry.NOISE_SETTINGS_WORLDGEN)){
                    Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(dimensionSettings.getStructuresConfig().structures);
                    tempMap.putAll(RSFeatures.RS_STRUCTURES);
                    dimensionSettings.getStructuresConfig().structures = tempMap;
                }
            }
        }
    }
}
