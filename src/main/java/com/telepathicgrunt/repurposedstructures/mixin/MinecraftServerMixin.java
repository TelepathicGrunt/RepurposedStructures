package com.telepathicgrunt.repurposedstructures.mixin;

import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.datafixers.DataFixer;
import com.telepathicgrunt.repurposedstructures.RSStructures;
import net.minecraft.resources.DataPackRegistries;
import net.minecraft.resources.ResourcePackList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.chunk.listener.IChunkStatusListenerFactory;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.storage.IServerConfiguration;
import net.minecraft.world.storage.SaveFormat;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.net.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    @Shadow @Final
    protected DynamicRegistries.Impl registryManager;

    @Shadow @Final
    private TemplateManager structureManager;

    @Inject(
            method = "<init>",
            at = @At(value = "TAIL")
    )
    private void modifyBiomeRegistry(Thread thread, DynamicRegistries.Impl dynamicRegistryManager, SaveFormat.LevelSave levelSave,
                                     IServerConfiguration iServerConfiguration, ResourcePackList resourcePackList,
                                     Proxy proxy, DataFixer dataFixer, DataPackRegistries dataPackRegistries,
                                     MinecraftSessionService minecraftSessionService, GameProfileRepository gameProfileRepository,
                                     PlayerProfileCache playerProfileCache, IChunkStatusListenerFactory iChunkStatusListenerFactory,
                                     CallbackInfo ci)
    {
        //add our structure spacing to all chunkgenerators including modded one and datapack ones.
        for (DimensionSettings dimensionSettings : registryManager.get(Registry.NOISE_SETTINGS_WORLDGEN)) {
            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(dimensionSettings.getStructuresConfig().structures);
            tempMap.putAll(RSStructures.RS_STRUCTURES);
            dimensionSettings.getStructuresConfig().structures = tempMap;
        }


        // Load up the nbt files for several structures at startup instead of during worldgen.
        for(ResourceLocation identifier : RSStructures.RS_STRUCTURE_START_PIECES){
            JigsawPattern structurePool = dynamicRegistryManager.get(Registry.TEMPLATE_POOL_WORLDGEN).getOrDefault(identifier);
            if(structurePool != null){
                List<JigsawPiece> elements = structurePool.getShuffledPieces(new Random());
                for(JigsawPiece element: elements){
                    // This loads the structure piece to nbt
                    element.getBoundingBox(structureManager, new BlockPos(0,0,0), Rotation.NONE);
                }
            }
        }
    }
}
