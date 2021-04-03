package com.telepathicgrunt.repurposedstructures.mixin;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(Template.class)
public interface TemplateAccessorInvoker {

    @Accessor("blocks")
    List<Template.Palette> rs_getBlocks();

    @Accessor("entities")
    List<Template.EntityInfo> rs_getEntities();

    @Accessor("size")
    BlockPos rs_getSize();

    @Invoker("addEntitiesToWorld")
    void rs_invokeSpawnEntities(IServerWorld serverIWorld, BlockPos pos, PlacementSettings placementIn);
}
