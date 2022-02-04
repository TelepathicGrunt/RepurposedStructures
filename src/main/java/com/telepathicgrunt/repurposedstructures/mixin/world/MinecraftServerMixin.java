package com.telepathicgrunt.repurposedstructures.mixin.world;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.misc.NoiseSettingsDeepCopier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.progress.ChunkProgressListener;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.ServerLevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.concurrent.Executor;

@Mixin(value = ServerLevel.class)
public class MinecraftServerMixin {

    @Shadow
    public ServerChunkCache getChunkSource() {
        throw new RuntimeException();
    }

    @Inject(method = "<init>",
            at = @At(value = "RETURN"))
    private void repurposedstructures_deepCopyNoiseSettings(MinecraftServer minecraftServer, Executor executor,
                                                            LevelStorageSource.LevelStorageAccess levelStorageAccess,
                                                            ServerLevelData serverLevelData, ResourceKey resourceKey,
                                                            DimensionType dimensionType, ChunkProgressListener chunkProgressListener,
                                                            ChunkGenerator chunkGenerator, boolean bl, long l, List list, boolean bl2,
                                                            CallbackInfo ci)
    {
        StructureSettings structureSettings = NoiseSettingsDeepCopier.deepCopyDimensionStructuresSettings(getChunkSource().getGenerator().getSettings());
        ((ChunkGeneratorAccessor)getChunkSource().getGenerator()).setSettings(structureSettings);
    }
}