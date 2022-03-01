package com.telepathicgrunt.repurposedstructures.mixin.structures;

import net.minecraft.server.commands.LocateCommand;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LocateCommand.class)
public class LocateCommandMixin {

    /**
     * Increases the radius that locate command works with
     * @author - TelepathicGrunt
     */
    @ModifyConstant(
            method = "locate(Lnet/minecraft/commands/CommandSourceStack;Lnet/minecraft/commands/arguments/ResourceOrTagLocationArgument$Result;)I",
            constant = @Constant(intValue = 100),
            require = 0
    )
    private static int repurposedstructures_increaseLocateRadius(int constant) {
        return 2000;
    }
}