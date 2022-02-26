package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.AdvancedDistanceJigsawStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.AdvancedJigsawStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.BuriableStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.CityNetherStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.GenericJigsawStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.GenericNetherJigsawStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.LandBasedEndStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.MansionStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.MineshaftEndStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.MineshaftStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.ShipwreckEndStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.ShipwreckNetherStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.StrongholdEndStructure;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;


public final class RSStructures {
    private RSStructures() {}

    public static StructureFeature<NoneFeatureConfiguration> GENERIC_JIGSAW_STRUCTURE = new GenericJigsawStructure();
    public static StructureFeature<NoneFeatureConfiguration> GENERIC_UNDERGROUND_JIGSAW_STRUCTURE = new GenericJigsawStructure();
    public static StructureFeature<NoneFeatureConfiguration> GENERIC_NETHER_JIGSAW_STRUCTURE = new GenericNetherJigsawStructure();
    public static StructureFeature<NoneFeatureConfiguration> BURIABLE_STRUCTURE = new BuriableStructure();
    public static StructureFeature<NoneFeatureConfiguration> LAND_BASED_END_STRUCTURE = new LandBasedEndStructure();
    public static StructureFeature<NoneFeatureConfiguration> ADVANCED_JIGSAW_STRUCTURE = new AdvancedJigsawStructure();
    public static StructureFeature<NoneFeatureConfiguration> MINESHAFT_GENERIC = new MineshaftStructure();
    public static StructureFeature<NoneFeatureConfiguration> MINESHAFT_NETHER_GENERIC = new MineshaftStructure();
    public static StructureFeature<NoneFeatureConfiguration> MINESHAFT_END = new MineshaftEndStructure();
    public static StructureFeature<NoneFeatureConfiguration> STRONGHOLD_NETHER = new AdvancedDistanceJigsawStructure();
    public static StructureFeature<NoneFeatureConfiguration> STRONGHOLD_END = new StrongholdEndStructure();
    public static StructureFeature<NoneFeatureConfiguration> SHIPWRECK_NETHER_STRUCTURE = new ShipwreckNetherStructure();
    public static StructureFeature<NoneFeatureConfiguration> SHIPWRECK_END_STRUCTURE = new ShipwreckEndStructure();
    public static StructureFeature<NoneFeatureConfiguration> CITY_NETHER_STRUCTURE = new CityNetherStructure();
    public static StructureFeature<NoneFeatureConfiguration> MANSION_STRUCTURE = new MansionStructure();

    public static void registerStructures() {
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "generic_jigsaw_structure"), GENERIC_JIGSAW_STRUCTURE).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "generic_underground_jigsaw_structure"), GENERIC_UNDERGROUND_JIGSAW_STRUCTURE).step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "generic_nether_jigsaw_structure"), GENERIC_NETHER_JIGSAW_STRUCTURE).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "buriable_structure"), BURIABLE_STRUCTURE).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "land_based_end_structure"), LAND_BASED_END_STRUCTURE).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "advanced_jigsaw_structure"), ADVANCED_JIGSAW_STRUCTURE).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_generic"), MINESHAFT_GENERIC).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_nether_generic"), MINESHAFT_NETHER_GENERIC).step(GenerationStep.Decoration.FLUID_SPRINGS).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_end"), MINESHAFT_END).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "stronghold_nether"), STRONGHOLD_NETHER).step(GenerationStep.Decoration.TOP_LAYER_MODIFICATION).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "stronghold_end"), STRONGHOLD_END).step(GenerationStep.Decoration.STRONGHOLDS).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "shipwreck_nether_structure"), SHIPWRECK_NETHER_STRUCTURE).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "shipwreck_end_structure"), SHIPWRECK_END_STRUCTURE).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "city_nether_structure"), CITY_NETHER_STRUCTURE).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "mansion_structure"), MANSION_STRUCTURE).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();

        //registers the structure pieces.
        RSStructurePieces.registerStructurePieces();
    }
}
