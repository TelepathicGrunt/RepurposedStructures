package com.telepathicgrunt.repurposedstructures.mixin.structures;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

@Mixin(StructureTemplate.class)
public interface TemplateAccessor {

    @Accessor("palettes")
    List<StructureTemplate.Palette> repurposedstructures_getPalettes();
}
