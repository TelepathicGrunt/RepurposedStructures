package com.telepathicgrunt.repurposedstructures.misc;

import com.mojang.datafixers.util.Either;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.JigsawReplacementProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public final class CharmFix {
    private CharmFix() {}

    public static void UndoCharmProcessors(Either<ResourceLocation, StructureTemplate> template, boolean isNotJigsaw, StructurePlaceSettings placementsettings) {
        if(RepurposedStructures.isCharmOn && template.left().isPresent()) {
            ResourceLocation rl = template.left().get();
            if(!rl.getNamespace().equals("charm")) {
                placementsettings.clearProcessors();
                placementsettings.addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
                if (!isNotJigsaw) {
                    placementsettings.addProcessor(JigsawReplacementProcessor.INSTANCE);
                }
            }
        }
    }
}