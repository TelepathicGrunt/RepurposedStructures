package com.telepathicgrunt.repurposedstructures.misc;

import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class StructureSpacingDebugging {
    private StructureSpacingDebugging() {}

    public static <C extends FeatureConfiguration> void registerStructureDebugging(RegistryObject<StructureFeature<C>> structure){
        MinecraftForge.EVENT_BUS.addListener(
            (PlayerInteractEvent.RightClickBlock event) -> {
                if(!event.getWorld().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND){
                    RepurposedStructures.LOGGER.info("Started search");

                    ServerLevel serverWorld = (ServerLevel) event.getWorld();
                    ChunkGenerator chunkGenerator = serverWorld.getChunkSource().getGenerator();
                    StructureFeature<?> structureToFind = structure.get();
                    StructureFeatureConfiguration structureseparationsettings = chunkGenerator.getSettings().getConfig(structureToFind);
                    List<Pair<BlockPos, Double>> structureStarts = new ArrayList<>();

                    int spacing = structureseparationsettings.spacing();
                    int startX = 0;
                    int startZ = 0;
                    int maxRadius = 20;

                    for(int currentRadius = 0; currentRadius <= maxRadius; ++currentRadius) {
                        for(int xRadius = -currentRadius; xRadius <= currentRadius; ++xRadius) {
                            boolean onXEdge = xRadius == -currentRadius || xRadius == currentRadius;

                            for(int zRadius = -currentRadius; zRadius <= currentRadius; ++zRadius) {
                                boolean onZEdge = zRadius == -currentRadius || zRadius == currentRadius;
                                if (onXEdge || onZEdge) {
                                    int k1 = startX + spacing * xRadius;
                                    int l1 = startZ + spacing * zRadius;
                                    ChunkPos chunkpos = structureToFind.getPotentialFeatureChunk(structureseparationsettings, serverWorld.getSeed(), k1, l1);
                                    ChunkAccess chunk = serverWorld.getChunk(chunkpos.x, chunkpos.z, ChunkStatus.STRUCTURE_STARTS);
                                    StructureStart<?> structurestart = serverWorld.structureFeatureManager().getStartForFeature(SectionPos.of(chunk.getPos(), 0), structureToFind, chunk);
                                    if (structurestart != null && structurestart.isValid()) {
                                        BlockPos pos = structurestart.getChunkPos().getWorldPosition();
                                        double x = pos.getX();
                                        double z = pos.getZ();
                                        structureStarts.add(Pair.of(pos, Math.floor(Math.sqrt((x * x) + (z * z)))));
                                    }

                                    if (currentRadius == 0) {
                                        break;
                                    }
                                }
                                else{
                                    zRadius = currentRadius - 1;
                                }
                            }

                            if (currentRadius == 0) {
                                break;
                            }
                        }
                    }

                    structureStarts.sort(Comparator.comparingDouble(Pair::getSecond));
                    structureStarts.forEach(pair -> RepurposedStructures.LOGGER.info(
                            "position: {} - distance: {}", pair.getFirst(), pair.getSecond()
                    ));

                    boolean breakpointHere = true;
                }
            }
        );
    }
}
