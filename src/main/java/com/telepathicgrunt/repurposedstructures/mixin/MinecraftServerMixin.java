package com.telepathicgrunt.repurposedstructures.mixin;

import com.telepathicgrunt.repurposedstructures.RSFeatures;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.configs.RSAllConfig;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.LakeFeature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;


@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    @ModifyVariable(
            method = "<init>",
            at = @At(value = "HEAD"),
            index = 1,
            argsOnly = true
    )
    private DynamicRegistryManager.Impl checkForRSVillages(DynamicRegistryManager.Impl impl) {

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

        if(impl.getOptional(Registry.BIOME_KEY).isPresent()) {
            for (Biome biome : impl.getOptional(Registry.BIOME_KEY).get()) {
                RepurposedStructures.addFeaturesAndStructuresToBiomes(impl.getOptional(Registry.BIOME_KEY).get(), biome, Objects.requireNonNull(impl.getOptional(Registry.BIOME_KEY).get().getId(biome)), allBiomeBlacklists);
            }
        }

        return impl;
    }
}
