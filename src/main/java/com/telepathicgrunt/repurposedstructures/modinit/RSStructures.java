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
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import java.util.Arrays;
import java.util.List;


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

    public static ResourceKey<StructureSet> VILLAGES_OVERWORLD = ResourceKey.create(Registry.STRUCTURE_SET_REGISTRY, new ResourceLocation(RepurposedStructures.MODID, "villages_overworld"));
    public static ResourceKey<StructureSet> MANSIONS_OVERWORLD = ResourceKey.create(Registry.STRUCTURE_SET_REGISTRY, new ResourceLocation(RepurposedStructures.MODID, "mansions_overworld"));
    public static ResourceKey<StructureSet> PYRAMIDS_OVERWORLD = ResourceKey.create(Registry.STRUCTURE_SET_REGISTRY, new ResourceLocation(RepurposedStructures.MODID, "pyramids_overworld"));
    public static ResourceKey<StructureSet> RUINS_OVERWORLD = ResourceKey.create(Registry.STRUCTURE_SET_REGISTRY, new ResourceLocation(RepurposedStructures.MODID, "ruins_overworld"));
    public static ResourceKey<StructureSet> WITCH_HUTS_OVERWORLD = ResourceKey.create(Registry.STRUCTURE_SET_REGISTRY, new ResourceLocation(RepurposedStructures.MODID, "witch_huts_overworld"));
    public static ResourceKey<StructureSet> BASTIONS_OVERWORLD = ResourceKey.create(Registry.STRUCTURE_SET_REGISTRY, new ResourceLocation(RepurposedStructures.MODID, "bastions_overworld"));

    public static List<ResourceKey<ConfiguredStructureFeature<?,?>>> LESS_JUNGLE_BUSH = List.of(
            ResourceKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation(RepurposedStructures.MODID, "village_jungle"))
    );
    public static List<ResourceKey<ConfiguredStructureFeature<?,?>>> NO_LAVAFALLS = Arrays.asList(
            ResourceKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation(RepurposedStructures.MODID, "mineshaft_icy")),
            ResourceKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation(RepurposedStructures.MODID, "bastion_underground"))
    );
    public static List<ResourceKey<ConfiguredStructureFeature<?,?>>> NO_BASALT_COLUMNS = Arrays.asList(
            ResourceKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation(RepurposedStructures.MODID, "mineshaft_nether")),
            ResourceKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation(RepurposedStructures.MODID, "mineshaft_crimson")),
            ResourceKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation(RepurposedStructures.MODID, "mineshaft_warped")),
            ResourceKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation(RepurposedStructures.MODID, "temple_nether_basalt")),
            ResourceKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation(RepurposedStructures.MODID, "shipwreck_nether_bricks")),
            ResourceKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation(RepurposedStructures.MODID, "outpost_nether_brick")),
            ResourceKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation(RepurposedStructures.MODID, "village_crimson")),
            ResourceKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation(RepurposedStructures.MODID, "village_warped")),
            ResourceKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation(RepurposedStructures.MODID, "ruins_nether"))
    );

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
