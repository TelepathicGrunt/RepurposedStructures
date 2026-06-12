package com.telepathicgrunt.repurposedstructures.mixins.world;

import com.mojang.datafixers.DataFixer;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.storage.ChunkScanAccess;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.StructureCheck;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(StructureCheck.class)
public interface StructureCheckAccessor {
    @Accessor("storageAccess")
    ChunkScanAccess repurposedstructures$getStorageAccess();

    @Accessor("registryAccess")
    RegistryAccess repurposedstructures$getRegistryAccess();

    @Accessor("structureTemplateManager")
    StructureTemplateManager repurposedstructures$getStructureTemplateManager();

    @Accessor("dimension")
    ResourceKey<Level> repurposedstructures$getDimension();

    @Accessor("chunkGenerator")
    ChunkGenerator repurposedstructures$getChunkGenerator();

    @Accessor("randomState")
    RandomState repurposedstructures$getRandomState();

    @Accessor("heightAccessor")
    LevelHeightAccessor repurposedstructures$getHeightAccessor();

    @Accessor("biomeSource")
    BiomeSource repurposedstructures$getBiomeSource();

    @Accessor("seed")
    long repurposedstructures$getSeed();

    @Accessor
    DataFixer getFixerUpper();
}
