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
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSShipwreckEndConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSShipwreckNetherConfig;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public final class RSStructures {
    private RSStructures() {}

    public static final DeferredRegister<StructureFeature<?>> STRUCTURE_FEATURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, RepurposedStructures.MODID);

    public static RegistryObject<StructureFeature<?>> GENERIC_JIGSAW_STRUCTURE = STRUCTURE_FEATURES.register("generic_jigsaw_structure", () -> new GenericJigsawStructure<>(RSGenericConfig.CODEC));
    public static RegistryObject<StructureFeature<?>> GENERIC_UNDERGROUND_JIGSAW_STRUCTURE = STRUCTURE_FEATURES.register("generic_underground_jigsaw_structure", () -> new GenericJigsawStructure<>(RSGenericConfig.CODEC));
    public static RegistryObject<StructureFeature<?>> GENERIC_NETHER_JIGSAW_STRUCTURE = STRUCTURE_FEATURES.register("generic_nether_jigsaw_structure", () -> new GenericNetherJigsawStructure<>(RSGenericNetherConfig.CODEC));
    public static RegistryObject<StructureFeature<?>> BURIABLE_STRUCTURE = STRUCTURE_FEATURES.register("buriable_structure", () -> new BuriableStructure<>(RSBuriableConfig.CODEC));
    public static RegistryObject<StructureFeature<?>> LAND_BASED_END_STRUCTURE = STRUCTURE_FEATURES.register("land_based_end_structure", () -> new LandBasedEndStructure<>(RSGenericConfig.CODEC));
    public static RegistryObject<StructureFeature<?>> ADVANCED_JIGSAW_STRUCTURE = STRUCTURE_FEATURES.register("advanced_jigsaw_structure", () -> new AdvancedJigsawStructure<>(RSAdvancedConfig.CODEC));
    public static RegistryObject<StructureFeature<?>> MINESHAFT_GENERIC = STRUCTURE_FEATURES.register("mineshaft_generic", () -> new MineshaftStructure<>(RSMineshaftConfig.CODEC));
    public static RegistryObject<StructureFeature<?>> MINESHAFT_NETHER_GENERIC = STRUCTURE_FEATURES.register("mineshaft_nether_generic", () -> new MineshaftStructure<>(RSMineshaftConfig.CODEC));
    public static RegistryObject<StructureFeature<?>> MINESHAFT_END = STRUCTURE_FEATURES.register("mineshaft_end", () -> new MineshaftEndStructure<>(RSMineshaftEndConfig.CODEC));
    public static RegistryObject<StructureFeature<?>> STRONGHOLD_NETHER = STRUCTURE_FEATURES.register("stronghold_nether", () -> new AdvancedDistanceJigsawStructure<>(RSAdvancedDistanceConfig.CODEC));
    public static RegistryObject<StructureFeature<?>> STRONGHOLD_END = STRUCTURE_FEATURES.register("stronghold_end", () -> new StrongholdEndStructure<>(RSAdvancedDistanceConfig.CODEC));
    public static RegistryObject<StructureFeature<?>> SHIPWRECK_NETHER_STRUCTURE = STRUCTURE_FEATURES.register("shipwreck_nether_structure", () -> new ShipwreckNetherStructure<>(RSShipwreckNetherConfig.CODEC));
    public static RegistryObject<StructureFeature<?>> SHIPWRECK_END_STRUCTURE = STRUCTURE_FEATURES.register("shipwreck_end_structure", () -> new ShipwreckEndStructure<>(RSShipwreckEndConfig.CODEC));
    public static RegistryObject<StructureFeature<?>> CITY_NETHER_STRUCTURE = STRUCTURE_FEATURES.register("city_nether_structure", () -> new CityNetherStructure<>(RSGenericNetherConfig.CODEC));
    public static RegistryObject<StructureFeature<?>> MANSION_STRUCTURE = STRUCTURE_FEATURES.register("mansion_structure", () -> new MansionStructure<>(RSMansionConfig.CODEC));

    public static void setupStructures() {
        StructureFeatureAccessor.getSTEP().put(GENERIC_JIGSAW_STRUCTURE.get(), GenerationStep.Decoration.SURFACE_STRUCTURES);
        StructureFeatureAccessor.getSTEP().put(GENERIC_UNDERGROUND_JIGSAW_STRUCTURE.get(), GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
        StructureFeatureAccessor.getSTEP().put(GENERIC_NETHER_JIGSAW_STRUCTURE.get(), GenerationStep.Decoration.SURFACE_STRUCTURES);
        StructureFeatureAccessor.getSTEP().put(BURIABLE_STRUCTURE.get(), GenerationStep.Decoration.SURFACE_STRUCTURES);
        StructureFeatureAccessor.getSTEP().put(LAND_BASED_END_STRUCTURE.get(), GenerationStep.Decoration.SURFACE_STRUCTURES);
        StructureFeatureAccessor.getSTEP().put(ADVANCED_JIGSAW_STRUCTURE.get(), GenerationStep.Decoration.SURFACE_STRUCTURES);
        StructureFeatureAccessor.getSTEP().put(MINESHAFT_GENERIC.get(), GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
        StructureFeatureAccessor.getSTEP().put(MINESHAFT_NETHER_GENERIC.get(), GenerationStep.Decoration.FLUID_SPRINGS);
        StructureFeatureAccessor.getSTEP().put(MINESHAFT_END.get(), GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
        StructureFeatureAccessor.getSTEP().put(STRONGHOLD_NETHER.get(), GenerationStep.Decoration.TOP_LAYER_MODIFICATION);
        StructureFeatureAccessor.getSTEP().put(STRONGHOLD_END.get(), GenerationStep.Decoration.STRONGHOLDS);
        StructureFeatureAccessor.getSTEP().put(SHIPWRECK_NETHER_STRUCTURE.get(), GenerationStep.Decoration.SURFACE_STRUCTURES);
        StructureFeatureAccessor.getSTEP().put(SHIPWRECK_END_STRUCTURE.get(), GenerationStep.Decoration.SURFACE_STRUCTURES);
        StructureFeatureAccessor.getSTEP().put(CITY_NETHER_STRUCTURE.get(), GenerationStep.Decoration.SURFACE_STRUCTURES);
        StructureFeatureAccessor.getSTEP().put(MANSION_STRUCTURE.get(), GenerationStep.Decoration.SURFACE_STRUCTURES);

        //registers the structure pieces.
        RSStructurePieces.registerStructurePieces();
    }
}
