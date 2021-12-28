package com.telepathicgrunt.repurposedstructures.world;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import net.minecraftforge.event.TickEvent;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

public class WaterloggingFixWorldSavedData extends SavedData {
    private static final String TELEPORTATION_DATA = RepurposedStructures.MODID + "waterloggingfix";
    private static final WaterloggingFixWorldSavedData CLIENT_DUMMY = new WaterloggingFixWorldSavedData(null);
    private final Map<ChunkPos, ObjectList<BlockPos>> blocksToUnwaterlog = new ConcurrentHashMap<>();

    public WaterloggingFixWorldSavedData() {}

    public WaterloggingFixWorldSavedData(CompoundTag tag) {
        if(tag != null && tag.contains("positions")) {
            ListTag listTag = tag.getList("positions", Tag.TAG_COMPOUND);
            for (Tag value : listTag) {
                addBlockPosToUnwaterlog(NbtUtils.readBlockPos((CompoundTag) value));
            }
        }
    }

    public static WaterloggingFixWorldSavedData get(Level world) {
        if (!(world instanceof ServerLevel)) {
            return CLIENT_DUMMY;
        }

        DimensionDataStorage storage = ((ServerLevel)world).getDataStorage();
        return storage.computeIfAbsent(WaterloggingFixWorldSavedData::new, WaterloggingFixWorldSavedData::new, TELEPORTATION_DATA);
    }

    @Override
    public CompoundTag save(CompoundTag data) {
        if(!blocksToUnwaterlog.isEmpty()) {
            ListTag listTag = new ListTag();
            blocksToUnwaterlog.values().forEach(val -> val.forEach(pos -> listTag.add(NbtUtils.writeBlockPos(pos))));
            data.put("positions", listTag);
            return data;
        }
        return null;
    }

    public void addBlockPosToUnwaterlog(BlockPos pos) {
        ChunkPos chunkPos = new ChunkPos(pos);
        blocksToUnwaterlog.putIfAbsent(chunkPos, ObjectList.of(pos));
        blocksToUnwaterlog.get(chunkPos).add(pos);
    }

    public static void worldTick(TickEvent.WorldTickEvent event) {
        if(event.phase == TickEvent.Phase.END && !event.world.isClientSide()) {
            WaterloggingFixWorldSavedData worldSavedData = get(event.world);
            worldSavedData.blocksToUnwaterlog.forEach((chunkPos, list) -> {
                try {
                    var future = ((ServerChunkCache)event.world.getChunkSource()).getChunkFuture(chunkPos.x, chunkPos.z, ChunkStatus.FULL, false);
                    if(future.get().left().isPresent()) {
                        setUnwaterlogged(chunkPos, list, future.get().left().get(), worldSavedData);
                        worldSavedData.setDirty();
                    }
                }
                catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void setUnwaterlogged(ChunkPos chunkPos, List<BlockPos> list, ChunkAccess chunk, WaterloggingFixWorldSavedData worldSavedData) {
        for(int i = list.size() - 1; i >= 0; i--) {
            BlockPos pos = list.get(i);
            BlockState state = chunk.getBlockState(pos);
            if(state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED)) {
                chunk.setBlockState(pos, state.setValue(BlockStateProperties.WATERLOGGED, false), false);
                list.remove(i);
            }
        }
        if(list.isEmpty()) {
            worldSavedData.blocksToUnwaterlog.remove(chunkPos);
        }
    }
}
