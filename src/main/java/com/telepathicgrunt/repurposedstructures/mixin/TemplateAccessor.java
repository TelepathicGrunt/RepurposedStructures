package com.telepathicgrunt.repurposedstructures.mixin;

import net.minecraft.world.gen.feature.template.Template;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(Template.class)
public interface TemplateAccessor {

    @Accessor("blocks")
    List<Template.Palette> rs_getBlocks();
}
