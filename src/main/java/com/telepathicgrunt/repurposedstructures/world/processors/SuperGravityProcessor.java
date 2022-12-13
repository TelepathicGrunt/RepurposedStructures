package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashSet;

public class SuperGravityProcessor extends StructureProcessor {

    public static final Codec<SuperGravityProcessor> CODEC = RecordCodecBuilder.create(
            (instance) -> instance.group(
                    Heightmap.Types.CODEC.fieldOf("heightmap").orElse(Heightmap.Types.WORLD_SURFACE_WG).forGetter((superGravityProcessor) -> superGravityProcessor.heightmap),
                    Codec.INT.fieldOf("offset").orElse(0).forGetter((superGravityProcessor) -> superGravityProcessor.offset),
                    Registry.BLOCK.byNameCodec().listOf().fieldOf("ignore_block").orElse(new ArrayList<>()).xmap(HashSet::new, ArrayList::new).forGetter(superGravityProcessor -> superGravityProcessor.blocksToIgnore),
                    Codec.BOOL.fieldOf("require_water_surface").orElse(false).forGetter((superGravityProcessor) -> superGravityProcessor.requireWaterSurface)
            ).apply(instance, SuperGravityProcessor::new));

    private final Heightmap.Types heightmap;
    private final int offset;
    private final HashSet<Block> blocksToIgnore;
    private final boolean requireWaterSurface;

    public SuperGravityProcessor(Heightmap.Types types, int offset, HashSet<Block> blocksToIgnore, boolean requireWaterSurface) {
        this.heightmap = types;
        this.offset = offset;
        this.blocksToIgnore = blocksToIgnore;
        this.requireWaterSurface = requireWaterSurface;
    }

    @Nullable
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader levelReader, BlockPos pos, BlockPos blockPos, StructureTemplate.StructureBlockInfo structureBlockInfoLocal, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings placeSettings) {
        Heightmap.Types heightmap$types;
        if (levelReader instanceof ServerLevel) {
            if (this.heightmap == Heightmap.Types.WORLD_SURFACE_WG) {
                heightmap$types = Heightmap.Types.WORLD_SURFACE;
            }
            else if (this.heightmap == Heightmap.Types.OCEAN_FLOOR_WG) {
                heightmap$types = Heightmap.Types.OCEAN_FLOOR;
            }
            else {
                heightmap$types = this.heightmap;
            }
        }
        else {
            heightmap$types = this.heightmap;
        }

        int heightmapY = levelReader.getHeight(heightmap$types, structureBlockInfoWorld.pos.getX(), structureBlockInfoWorld.pos.getZ());
        int localY = structureBlockInfoLocal.pos.getY();

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        mutable.set(structureBlockInfoWorld.pos.getX(), heightmapY, structureBlockInfoWorld.pos.getZ());
        BlockState aboveState = levelReader.getBlockState(mutable);
        mutable.move(Direction.DOWN);
        BlockState currentState = levelReader.getBlockState(mutable);
        while (blocksToIgnore.contains(currentState.getBlock()) || (requireWaterSurface && currentState.getMaterial() == Material.WATER)) {
            aboveState = currentState;
            mutable.move(Direction.DOWN);
            currentState = levelReader.getBlockState(mutable);
        }

        if (requireWaterSurface ? aboveState.getMaterial() == Material.WATER : aboveState.isAir()) {
            return new StructureTemplate.StructureBlockInfo(new BlockPos(structureBlockInfoWorld.pos.getX(), mutable.getY() + localY + this.offset, structureBlockInfoWorld.pos.getZ()), structureBlockInfoWorld.state, structureBlockInfoWorld.nbt);
        }

        return null;
    }

    protected StructureProcessorType<?> getType() {
        return RSProcessors.SUPER_GRAVITY_PROCESSOR;
    }
}