package com.telepathicgrunt.repurposedstructures.mixin.structures;

import net.minecraft.server.commands.LocateCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LocateCommand.class)
public class LocateCommandMixin {

    /**
     * Increases the search radius for locating structurss
     * @author - TelepathicGrunt
     * @return - The higher weight that is a more reasonable limit.
     */
    @ModifyConstant(
            method = "locate(Lnet/minecraft/commands/CommandSourceStack;Lnet/minecraft/world/level/levelgen/feature/StructureFeature;)I",
            constant = @Constant(intValue = 100),
            remap = false,
            require = 0
    )
    private static int repurposedstructures_increaseLocateSearchRadius(int constant) {
        return 10000;
    }
}