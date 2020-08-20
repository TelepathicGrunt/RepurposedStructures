package com.telepathicgrunt.repurposedstructures.mixin;

import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.datafixers.DataFixer;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.resource.ServerResourceManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListenerFactory;
import net.minecraft.util.UserCache;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
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
import java.util.*;
import java.util.function.Supplier;


@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    @Shadow @Final
    protected DynamicRegistryManager.Impl registryManager;

    @Inject(
            method = "<init>",
            at = @At(value = "TAIL")
    )
    private void modifyBiomeRegistry(Thread thread, DynamicRegistryManager.Impl impl, LevelStorage.Session session, SaveProperties saveProperties, ResourcePackManager resourcePackManager, Proxy proxy, DataFixer dataFixer, ServerResourceManager serverResourceManager, MinecraftSessionService minecraftSessionService, GameProfileRepository gameProfileRepository, UserCache userCache, WorldGenerationProgressListenerFactory worldGenerationProgressListenerFactory, CallbackInfo ci) {

        //Gets blacklisted biome IDs for each structure type
        //Done here so the map can be garbage collected later
        Map<String, List<String>> allBiomeBlacklists = new HashMap<>();
        allBiomeBlacklists.put("dungeon", Arrays.asList(RepurposedStructures.RSAllConfig.RSDungeonsConfig.blacklistedDungeonBiomes.split(",")));
        allBiomeBlacklists.put("boulder", Arrays.asList(RepurposedStructures.RSAllConfig.RSMainConfig.misc.blacklistedBoulderBiomes.split(",")));
        allBiomeBlacklists.put("swamp_tree", Arrays.asList(RepurposedStructures.RSAllConfig.RSMainConfig.misc.blacklistedSwampTreeBiomes.split(",")));
        allBiomeBlacklists.put("fortress", Arrays.asList(RepurposedStructures.RSAllConfig.RSMainConfig.jungleFortress.blacklistedFortressBiomes.split(",")));
        allBiomeBlacklists.put("igloo", Arrays.asList(RepurposedStructures.RSAllConfig.RSMainConfig.igloos.blacklistedIglooBiomes.split(",")));
        allBiomeBlacklists.put("mineshaft", Arrays.asList(RepurposedStructures.RSAllConfig.RSMineshaftsConfig.blacklistedMineshaftBiomes.split(",")));
        allBiomeBlacklists.put("outpost", Arrays.asList(RepurposedStructures.RSAllConfig.RSOutpostsConfig.blacklistedOutpostBiomes.split(",")));
        allBiomeBlacklists.put("shipwreck", Arrays.asList(RepurposedStructures.RSAllConfig.RSMainConfig.shipwrecks.blacklistedShipwreckBiomes.split(",")));
        allBiomeBlacklists.put("stronghold", Arrays.asList(RepurposedStructures.RSAllConfig.RSStrongholdsConfig.blacklistedStrongholdBiomes.split(",")));
        allBiomeBlacklists.put("temple", Arrays.asList(RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.blacklistedTempleBiomes.split(",")));
        allBiomeBlacklists.put("pyramid", Arrays.asList(RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.blacklistedPyramidBiomes.split(",")));
        allBiomeBlacklists.put("village", Arrays.asList(RepurposedStructures.RSAllConfig.RSVillagesConfig.blacklistedVillageBiomes.split(",")));
        allBiomeBlacklists.put("well", Arrays.asList(RepurposedStructures.RSAllConfig.RSWellsConfig.blacklistedWellBiomes.split(",")));

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
                RepurposedStructures.addFeaturesAndStructuresToBiomes(registryManager.getOptional(Registry.BIOME_KEY).get(), biome, Objects.requireNonNull(registryManager.getOptional(Registry.BIOME_KEY).get().getId(biome)), allBiomeBlacklists);
            }
        }
    }
}
