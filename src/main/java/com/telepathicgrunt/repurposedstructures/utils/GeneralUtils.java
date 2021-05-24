package com.telepathicgrunt.repurposedstructures.utils;

import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.RegistryObject;

import java.util.*;
import java.util.stream.Collectors;

public class GeneralUtils {

    // Weighted Random from: https://stackoverflow.com/a/6737362
    public static <T> T getRandomEntry(List<Pair<T, Integer>> rlList, Random random) {
        double totalWeight = 0.0;

        // Compute the total weight of all items together.
        for (Pair<T, Integer> pair : rlList) {
            totalWeight += pair.getSecond();
        }

        // Now choose a random item.
        int index = 0;
        for (double randomWeightPicked = random.nextFloat() * totalWeight; index < rlList.size() - 1; ++index) {
            randomWeightPicked -= rlList.get(index).getSecond();
            if (randomWeightPicked <= 0.0) break;
        }

        return rlList.get(index).getFirst();
    }

    //////////////////////////////
    private static final Map<BlockState, Boolean> IS_FULLCUBE_MAP = new HashMap<>();

    public static boolean isFullCube(IWorldReader world, BlockPos pos, BlockState state){
        if(!IS_FULLCUBE_MAP.containsKey(state)){
            boolean isFullCube = Block.isShapeFullBlock(state.getOcclusionShape(world, pos)) || state.getBlock() instanceof LeavesBlock;
            IS_FULLCUBE_MAP.put(state, isFullCube);
        }
        return IS_FULLCUBE_MAP.get(state);
    }

    //////////////////////////////

    // Helper method to make chests always face away from walls
    public static BlockState orientateChest(IServerWorld blockView, BlockPos blockPos, BlockState blockState) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        Direction bestDirection = blockState.getValue(HorizontalBlock.FACING);

        for(Direction facing : Direction.Plane.HORIZONTAL) {
            mutable.set(blockPos).move(facing);

            // Checks if wall is in this side
            if (isFullCube(blockView, mutable, blockView.getBlockState(mutable))) {
                bestDirection = facing;

                // Exit early if facing open space opposite of wall
                mutable.move(facing.getOpposite(), 2);
                if(!blockView.getBlockState(mutable).getMaterial().isSolid()){
                    break;
                }
            }
        }

        // Make chest face away from wall
        return blockState.setValue(HorizontalBlock.FACING, bestDirection.getOpposite());
    }

    ///////////////////////////////////////////

    /**
     * Will serialize (if possible) both features and check if they are the same feature.
     * If cannot serialize, compare the feature itself to see if it is the same.
     */
    public static boolean serializeAndCompareFeature(ConfiguredFeature<?, ?> configuredFeature1, ConfiguredFeature<?, ?> configuredFeature2) {

        Optional<JsonElement> configuredFeatureJSON1 = ConfiguredFeature.DIRECT_CODEC.encode(configuredFeature1, JsonOps.INSTANCE, JsonOps.INSTANCE.empty()).get().left();
        Optional<JsonElement> configuredFeatureJSON2 = ConfiguredFeature.DIRECT_CODEC.encode(configuredFeature2, JsonOps.INSTANCE, JsonOps.INSTANCE.empty()).get().left();

        // One of the configuredfeatures cannot be serialized
        if(!configuredFeatureJSON1.isPresent() || !configuredFeatureJSON2.isPresent()) {
            return false;
        }

        // Compare the JSON to see if it's the same ConfiguredFeature in the end.
        return configuredFeatureJSON1.equals(configuredFeatureJSON2);
    }

    //////////////////////////////////////////////

    private static Set<RegistryKey<World>> BLACKLISTED_WORLDS = null;
    public static boolean isWorldBlacklisted(IServerWorld world){
        if(BLACKLISTED_WORLDS == null){
            BLACKLISTED_WORLDS = Arrays.stream(RepurposedStructures.RSMainConfig.blacklistedDimensions.get().split(","))
                    .map(String::trim).map(dimensionName -> RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(dimensionName)))
                    .collect(Collectors.toSet());
        }
        return BLACKLISTED_WORLDS.contains(world.getLevel().dimension());
    }

    //////////////////////////////////////////////

    public static <T extends IFeatureConfig> void registerStructureDebugging(RegistryObject<Structure<T>> structure){
        MinecraftForge.EVENT_BUS.addListener(
                (PlayerInteractEvent.RightClickBlock event) -> {
                    if(!event.getWorld().isClientSide() && event.getHand() == Hand.MAIN_HAND){
                        RepurposedStructures.LOGGER.info("Started search");

                        ServerWorld serverWorld = (ServerWorld) event.getWorld();
                        ChunkGenerator chunkGenerator = serverWorld.getChunkSource().getGenerator();
                        StructureSeparationSettings structureseparationsettings = chunkGenerator.getSettings().getConfig(RSStructures.NETHER_STRONGHOLD.get());
                        Structure<?> structureToFind = structure.get();
                        List<Pair<BlockPos, Integer>> structureStarts = new ArrayList<>();

                        int spacing = structureseparationsettings.spacing();
                        int startX = 0;
                        int startZ = 0;
                        int currentRadius = 0;
                        int maxRadius = 20;

                        for(SharedSeedRandom sharedseedrandom = new SharedSeedRandom(); currentRadius <= maxRadius; ++currentRadius) {
                            for(int xRadius = -currentRadius; xRadius <= currentRadius; ++xRadius) {
                                boolean onXEdge = xRadius == -currentRadius || xRadius == currentRadius;

                                for(int zRadius = -currentRadius; zRadius <= currentRadius; ++zRadius) {
                                    boolean onZEdge = zRadius == -currentRadius || zRadius == currentRadius;
                                    if (onXEdge || onZEdge) {
                                        int k1 = startX + spacing * xRadius;
                                        int l1 = startZ + spacing * zRadius;
                                        ChunkPos chunkpos = structureToFind.getPotentialFeatureChunk(structureseparationsettings, serverWorld.getSeed(), sharedseedrandom, k1, l1);
                                        IChunk ichunk = serverWorld.getChunk(chunkpos.x, chunkpos.z, ChunkStatus.STRUCTURE_STARTS);
                                        StructureStart<?> structurestart = serverWorld.structureFeatureManager().getStartForFeature(SectionPos.of(ichunk.getPos(), 0), structureToFind, ichunk);
                                        if (structurestart != null && structurestart.isValid()) {
                                            BlockPos pos = structurestart.getLocatePos();
                                            structureStarts.add(Pair.of(pos, (int)Math.sqrt((pos.getX() * pos.getX()) + (pos.getZ() * pos.getZ()))));
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

                        structureStarts.sort(Comparator.comparingInt(Pair::getSecond));
                        structureStarts.forEach(pair -> RepurposedStructures.LOGGER.info(
                                "position: {} - distance: {}", pair.getFirst(), pair.getSecond()
                        ));

                        boolean breakpointHere = true;
                    }
                }
        );
    }
}