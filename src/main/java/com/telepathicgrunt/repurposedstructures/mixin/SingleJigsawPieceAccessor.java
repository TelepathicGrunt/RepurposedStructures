package com.telepathicgrunt.repurposedstructures.mixin;

import com.mojang.datafixers.util.Either;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
import net.minecraft.world.gen.feature.template.Template;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SingleJigsawPiece.class)
public interface SingleJigsawPieceAccessor {
    @Accessor("template")
    Either<ResourceLocation, Template> repurposedstructures_getTemplate();
}
