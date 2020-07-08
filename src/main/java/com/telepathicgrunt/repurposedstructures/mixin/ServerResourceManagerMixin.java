package com.telepathicgrunt.repurposedstructures.mixin;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.utils.MobSpawnerManager;
import com.telepathicgrunt.repurposedstructures.world.features.RSFeatures;
import net.minecraft.resource.ReloadableResourceManager;
import net.minecraft.resource.ServerResourceManager;
import net.minecraft.server.command.CommandManager;
import net.minecraft.tag.RegistryTagManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.LakeFeature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;


@Mixin(ServerResourceManager.class)
public class ServerResourceManagerMixin {

    @Shadow @Final private ReloadableResourceManager resourceManager;

    @Inject(
            method = "<init>(Lnet/minecraft/server/command/CommandManager$RegistrationEnvironment;I)V",
            at = @At(value = "TAIL")
    )
    private void registerDataManagers(CommandManager.RegistrationEnvironment registrationEnvironment, int i, CallbackInfo ci) {
        RepurposedStructures.mobSpawnerManager = new MobSpawnerManager();
        this.resourceManager.registerListener(RepurposedStructures.mobSpawnerManager);
    }
}
