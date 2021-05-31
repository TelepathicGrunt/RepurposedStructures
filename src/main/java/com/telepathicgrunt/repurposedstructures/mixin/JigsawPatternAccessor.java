package com.telepathicgrunt.repurposedstructures.mixin;

import com.mojang.datafixers.util.Pair;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(JigsawPattern.class)
public interface JigsawPatternAccessor {
    @Accessor("rawTemplates")
    List<Pair<JigsawPiece, Integer>> repurposedstructures_getRawTemplates();

    @Mutable
    @Accessor("rawTemplates")
    void repurposedstructures_setRawTemplates(List<Pair<JigsawPiece, Integer>> elementCounts);

    @Accessor("templates")
    List<JigsawPiece> repurposedstructures_getTemplates();

    @Mutable
    @Accessor("templates")
    void repurposedstructures_setTemplates(List<JigsawPiece> elements);
}
