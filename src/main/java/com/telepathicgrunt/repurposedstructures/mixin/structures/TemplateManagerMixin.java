package com.telepathicgrunt.repurposedstructures.mixin.structures;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//Source: https://github.com/modmuss50/Voyager/blob/master/src/main/java/me/modmuss50/voyager/mixin/StructureManagerMixin.java
@Mixin(TemplateManager.class)
public class TemplateManagerMixin {
	@Shadow
	@Final
	@Mutable
	private Map<ResourceLocation, Template> structureRepository;

	@Inject(method = "<init>", at = @At(value = "RETURN"))
	private void repurposedstructures_init(CallbackInfo info) {
		if(structureRepository instanceof HashMap){
			structureRepository = Collections.synchronizedMap(structureRepository);
		}
	}
}
