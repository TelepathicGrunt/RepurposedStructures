package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.codecs.YRangeAllowance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;


public class StrongholdEndStructure extends GenericJigsawStructure {

    public static final MapCodec<StrongholdEndStructure> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            StrongholdEndStructure.settingsCodec(instance),
            StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
            Codec.intRange(0, 30).fieldOf("size").forGetter(structure -> structure.size),
            YRangeAllowance.CODEC.optionalFieldOf("y_allowance").forGetter(structure -> structure.yAllowance),
            HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
            Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
            Codec.BOOL.fieldOf("cannot_spawn_in_liquid").orElse(false).forGetter(structure -> structure.cannotSpawnInLiquid),
            Codec.intRange(1, 100).optionalFieldOf("terrain_height_radius_check").forGetter(structure -> structure.terrainHeightCheckRadius),
            Codec.intRange(1, 1000).optionalFieldOf("allowed_terrain_height_range").forGetter(structure -> structure.allowedTerrainHeightRange),
            Codec.intRange(1, 100).optionalFieldOf("valid_biome_radius_check").forGetter(structure -> structure.biomeRadius),
            Identifier.CODEC.listOf().fieldOf("pools_that_ignore_boundaries").orElse(new ArrayList<>()).xmap(HashSet::new, ArrayList::new).forGetter(structure -> structure.poolsThatIgnoreBoundaries),
            Codec.intRange(1, 128).optionalFieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter),
            StringRepresentable.fromEnum(BURYING_TYPE::values).optionalFieldOf("burying_type").forGetter(structure -> structure.buryingType),
            Codec.BOOL.fieldOf("use_bounding_box_hack").orElse(false).forGetter(structure -> structure.useBoundingBoxHack),
            LiquidSettings.CODEC.optionalFieldOf("liquid_settings", JigsawStructure.DEFAULT_LIQUID_SETTINGS).forGetter(structure -> structure.liquidSettings)
    ).apply(instance, StrongholdEndStructure::new));

    public StrongholdEndStructure(Structure.StructureSettings config,
                              Holder<StructureTemplatePool> startPool,
                              int size,
                              Optional<YRangeAllowance> yAllowance,
                              HeightProvider startHeight,
                              Optional<Heightmap.Types> projectStartToHeightmap,
                              boolean cannotSpawnInLiquid,
                              Optional<Integer> terrainHeightCheckRadius,
                              Optional<Integer> allowedTerrainHeightRange,
                              Optional<Integer> biomeRadius,
                              HashSet<Identifier> poolsThatIgnoreBoundaries,
                              Optional<Integer> maxDistanceFromCenter,
                              Optional<BURYING_TYPE> buryingType,
                              boolean useBoundingBoxHack,
                              LiquidSettings liquidSettings)
    {
        super(config,
                startPool,
                size,
                yAllowance,
                startHeight,
                projectStartToHeightmap,
                cannotSpawnInLiquid,
                terrainHeightCheckRadius,
                allowedTerrainHeightRange,
                biomeRadius,
                poolsThatIgnoreBoundaries,
                maxDistanceFromCenter,
                buryingType,
                useBoundingBoxHack,
                liquidSettings);
    }

    @Override
    protected boolean extraSpawningChecks(GenerationContext context, BlockPos blockPos) {
        boolean superCheck = super.extraSpawningChecks(context, blockPos);
        if(!superCheck)
            return false;

        ChunkPos chunkPos = context.chunkPos();
        int minLandHeight = Math.min(GeneralUtils.getMaxTerrainLimit(context.chunkGenerator()), context.chunkGenerator().getMinY() + 45);
        int xPos = chunkPos.getMinBlockX();
        int zPos = chunkPos.getMinBlockZ();
        int landHeight = Integer.MAX_VALUE;

        for(int i = 2; i >= 1; i--) {
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                Vector3f offsetPos = direction.step();
                offsetPos.mul(35f * i);
                landHeight = getHeightAt(context, xPos + (int)offsetPos.x(), zPos + (int)offsetPos.z(), landHeight);
                if(landHeight < minLandHeight) return false;
            }
        }

        landHeight = getHeightAt(context, xPos, zPos, landHeight);
        return landHeight >= minLandHeight;
    }

    private static int getHeightAt(GenerationContext context, int xPos, int zPos, int landHeight) {
        landHeight = Math.min(landHeight, GeneralUtils.getCachedFreeHeight(context.chunkGenerator(), xPos, zPos, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState())) - 1;
        return landHeight;
    }

    @Override
    public StructureType<?> type() {
        return RSStructures.STRONGHOLD_END.get();
    }
}
