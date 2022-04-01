package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.structures.StructureFeatureAccessor;
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
import com.telepathicgrunt.repurposedstructures.world.structures.MonumentStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.ShipwreckEndStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.ShipwreckNetherStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.StrongholdEndStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSAdvancedConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSAdvancedDistanceConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSBuriableConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSGenericConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSGenericNetherConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSMansionConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSMineshaftConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSMineshaftEndConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSMonumentConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSShipwreckEndConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSShipwreckNetherConfig;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.StructureFeature;


public final class RSStructures {
    private RSStructures() {}

    public static StructureFeature<?> GENERIC_JIGSAW_STRUCTURE = new GenericJigsawStructure<>(RSGenericConfig.CODEC);
    public static StructureFeature<?> GENERIC_UNDERGROUND_JIGSAW_STRUCTURE = new GenericJigsawStructure<>(RSGenericConfig.CODEC);
    public static StructureFeature<?> GENERIC_NETHER_JIGSAW_STRUCTURE = new GenericNetherJigsawStructure<>(RSGenericNetherConfig.CODEC);
    public static StructureFeature<?> BURIABLE_STRUCTURE = new BuriableStructure<>(RSBuriableConfig.CODEC);
    public static StructureFeature<?> LAND_BASED_END_STRUCTURE = new LandBasedEndStructure<>(RSGenericConfig.CODEC);
    public static StructureFeature<?> ADVANCED_JIGSAW_STRUCTURE = new AdvancedJigsawStructure<>(RSAdvancedConfig.CODEC);
    public static StructureFeature<?> MINESHAFT_GENERIC = new MineshaftStructure<>(RSMineshaftConfig.CODEC);
    public static StructureFeature<?> MINESHAFT_NETHER_GENERIC = new MineshaftStructure<>(RSMineshaftConfig.CODEC);
    public static StructureFeature<?> MINESHAFT_END = new MineshaftEndStructure<>(RSMineshaftEndConfig.CODEC);
    public static StructureFeature<?> STRONGHOLD_NETHER = new AdvancedDistanceJigsawStructure<>(RSAdvancedDistanceConfig.CODEC);
    public static StructureFeature<?> STRONGHOLD_END = new StrongholdEndStructure<>(RSAdvancedDistanceConfig.CODEC);
    public static StructureFeature<?> SHIPWRECK_NETHER_STRUCTURE = new ShipwreckNetherStructure<>(RSShipwreckNetherConfig.CODEC);
    public static StructureFeature<?> SHIPWRECK_END_STRUCTURE = new ShipwreckEndStructure<>(RSShipwreckEndConfig.CODEC);
    public static StructureFeature<?> CITY_NETHER_STRUCTURE = new CityNetherStructure<>(RSGenericNetherConfig.CODEC);
    public static StructureFeature<?> MANSION_STRUCTURE = new MansionStructure<>(RSMansionConfig.CODEC);
    public static StructureFeature<?> MONUMENT_GENERIC = new MonumentStructure<>(RSMonumentConfig.CODEC);
    public static StructureFeature<?> MONUMENT_NETHER = new MonumentStructure<>(RSMonumentConfig.CODEC);

    public static void registerStructures() {
        Registry.register(Registry.STRUCTURE_FEATURE, new ResourceLocation(RepurposedStructures.MODID, "generic_jigsaw_structure"), GENERIC_JIGSAW_STRUCTURE);
        Registry.register(Registry.STRUCTURE_FEATURE, new ResourceLocation(RepurposedStructures.MODID, "generic_underground_jigsaw_structure"), GENERIC_UNDERGROUND_JIGSAW_STRUCTURE);
        Registry.register(Registry.STRUCTURE_FEATURE, new ResourceLocation(RepurposedStructures.MODID, "generic_nether_jigsaw_structure"), GENERIC_NETHER_JIGSAW_STRUCTURE);
        Registry.register(Registry.STRUCTURE_FEATURE, new ResourceLocation(RepurposedStructures.MODID, "buriable_structure"), BURIABLE_STRUCTURE);
        Registry.register(Registry.STRUCTURE_FEATURE, new ResourceLocation(RepurposedStructures.MODID, "land_based_end_structure"), LAND_BASED_END_STRUCTURE);
        Registry.register(Registry.STRUCTURE_FEATURE, new ResourceLocation(RepurposedStructures.MODID, "advanced_jigsaw_structure"), ADVANCED_JIGSAW_STRUCTURE);
        Registry.register(Registry.STRUCTURE_FEATURE, new ResourceLocation(RepurposedStructures.MODID, "mineshaft_generic"), MINESHAFT_GENERIC);
        Registry.register(Registry.STRUCTURE_FEATURE, new ResourceLocation(RepurposedStructures.MODID, "mineshaft_nether_generic"), MINESHAFT_NETHER_GENERIC);
        Registry.register(Registry.STRUCTURE_FEATURE, new ResourceLocation(RepurposedStructures.MODID, "mineshaft_end"), MINESHAFT_END);
        Registry.register(Registry.STRUCTURE_FEATURE, new ResourceLocation(RepurposedStructures.MODID, "stronghold_nether"), STRONGHOLD_NETHER);
        Registry.register(Registry.STRUCTURE_FEATURE, new ResourceLocation(RepurposedStructures.MODID, "stronghold_end"), STRONGHOLD_END);
        Registry.register(Registry.STRUCTURE_FEATURE, new ResourceLocation(RepurposedStructures.MODID, "shipwreck_nether_structure"), SHIPWRECK_NETHER_STRUCTURE);
        Registry.register(Registry.STRUCTURE_FEATURE, new ResourceLocation(RepurposedStructures.MODID, "shipwreck_end_structure"), SHIPWRECK_END_STRUCTURE);
        Registry.register(Registry.STRUCTURE_FEATURE, new ResourceLocation(RepurposedStructures.MODID, "city_nether_structure"), CITY_NETHER_STRUCTURE);
        Registry.register(Registry.STRUCTURE_FEATURE, new ResourceLocation(RepurposedStructures.MODID, "mansion_structure"), MANSION_STRUCTURE);
        Registry.register(Registry.STRUCTURE_FEATURE, new ResourceLocation(RepurposedStructures.MODID, "monument_generic"), MONUMENT_GENERIC);
        Registry.register(Registry.STRUCTURE_FEATURE, new ResourceLocation(RepurposedStructures.MODID, "monument_nether"), MONUMENT_NETHER);

        StructureFeatureAccessor.getSTEP().put(GENERIC_JIGSAW_STRUCTURE, GenerationStep.Decoration.SURFACE_STRUCTURES);
        StructureFeatureAccessor.getSTEP().put(GENERIC_UNDERGROUND_JIGSAW_STRUCTURE, GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
        StructureFeatureAccessor.getSTEP().put(GENERIC_NETHER_JIGSAW_STRUCTURE, GenerationStep.Decoration.SURFACE_STRUCTURES);
        StructureFeatureAccessor.getSTEP().put(BURIABLE_STRUCTURE, GenerationStep.Decoration.SURFACE_STRUCTURES);
        StructureFeatureAccessor.getSTEP().put(LAND_BASED_END_STRUCTURE, GenerationStep.Decoration.SURFACE_STRUCTURES);
        StructureFeatureAccessor.getSTEP().put(ADVANCED_JIGSAW_STRUCTURE, GenerationStep.Decoration.SURFACE_STRUCTURES);
        StructureFeatureAccessor.getSTEP().put(MINESHAFT_GENERIC, GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
        StructureFeatureAccessor.getSTEP().put(MINESHAFT_NETHER_GENERIC, GenerationStep.Decoration.FLUID_SPRINGS);
        StructureFeatureAccessor.getSTEP().put(MINESHAFT_END, GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
        StructureFeatureAccessor.getSTEP().put(STRONGHOLD_NETHER, GenerationStep.Decoration.TOP_LAYER_MODIFICATION);
        StructureFeatureAccessor.getSTEP().put(STRONGHOLD_END, GenerationStep.Decoration.STRONGHOLDS);
        StructureFeatureAccessor.getSTEP().put(SHIPWRECK_NETHER_STRUCTURE, GenerationStep.Decoration.SURFACE_STRUCTURES);
        StructureFeatureAccessor.getSTEP().put(SHIPWRECK_END_STRUCTURE, GenerationStep.Decoration.SURFACE_STRUCTURES);
        StructureFeatureAccessor.getSTEP().put(CITY_NETHER_STRUCTURE, GenerationStep.Decoration.SURFACE_STRUCTURES);
        StructureFeatureAccessor.getSTEP().put(MANSION_STRUCTURE, GenerationStep.Decoration.SURFACE_STRUCTURES);
        StructureFeatureAccessor.getSTEP().put(MONUMENT_GENERIC, GenerationStep.Decoration.SURFACE_STRUCTURES);
        StructureFeatureAccessor.getSTEP().put(MONUMENT_NETHER, GenerationStep.Decoration.FLUID_SPRINGS);

        //registers the structure pieces.
        RSStructurePieces.registerStructurePieces();
    }
}
