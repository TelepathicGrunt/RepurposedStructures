package com.telepathicgrunt.repurposedstructures.mixin.structures;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.modinit.RSTags;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.ResourceOrTagLocationArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.server.commands.LocateCommand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LocateCommand.class)
public class LocateCommandMixin {

    @Final
    @Shadow
    private static DynamicCommandExceptionType ERROR_STRUCTURE_NOT_FOUND;

    /**
     * Increases the radius that locate command works with
     * @author - TelepathicGrunt
     */
    @Inject(
            method = "locateStructure(Lnet/minecraft/commands/CommandSourceStack;Lnet/minecraft/commands/arguments/ResourceOrTagLocationArgument$Result;)I",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/chunk/ChunkGenerator;findNearestMapStructure(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/HolderSet;Lnet/minecraft/core/BlockPos;IZ)Lcom/mojang/datafixers/util/Pair;", ordinal = 0),
            locals = LocalCapture.CAPTURE_FAILSOFT,
            cancellable = true,
            require = 0
    )
    private static void repurposedstructures_increaseLocateRadius(CommandSourceStack commandSourceStack,
                                                                  ResourceOrTagLocationArgument.Result<Structure> result,
                                                                  CallbackInfoReturnable<Integer> cir,
                                                                  Registry<Structure> registry,
                                                                  HolderSet<Structure> holderSet,
                                                                  BlockPos blockPos,
                                                                  ServerLevel serverLevel) throws CommandSyntaxException {
        if(holderSet.stream().anyMatch(configuredStructureFeatureHolder -> configuredStructureFeatureHolder.is(RSTags.LARGER_LOCATE_SEARCH))) {
            int increasedSearchRadius = 2000;
            Pair<BlockPos, Holder<Structure>> pair = serverLevel.getChunkSource().getGenerator().findNearestMapStructure(serverLevel, holderSet, blockPos, increasedSearchRadius, false);
            if (pair == null) {
                throw ERROR_STRUCTURE_NOT_FOUND.create(result.asPrintable());
            }
            else {
                cir.setReturnValue(LocateCommand.showLocateResult(commandSourceStack, result, blockPos, pair, "commands.locate.structure.success", false));
            }
        }
    }
}