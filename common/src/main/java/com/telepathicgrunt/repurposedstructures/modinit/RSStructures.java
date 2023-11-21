package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.registry.RegistryEntry;
import com.telepathicgrunt.repurposedstructures.modinit.registry.ResourcefulRegistries;
import com.telepathicgrunt.repurposedstructures.modinit.registry.ResourcefulRegistry;
import com.telepathicgrunt.repurposedstructures.world.structures.CityNetherStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.GenericJigsawStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.GenericNetherJigsawStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.MansionStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.MineshaftEndStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.MineshaftStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.MonumentStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.ShipwreckNetherStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.StrongholdEndStructure;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.StructureType;


public final class RSStructures {
    public static final ResourcefulRegistry<StructureType<?>> STRUCTURE_TYPE = ResourcefulRegistries.create(BuiltInRegistries.STRUCTURE_TYPE, RepurposedStructures.MODID);

    public static RegistryEntry<StructureType<GenericJigsawStructure>, StructureType<GenericJigsawStructure>> GENERIC_JIGSAW_STRUCTURE = STRUCTURE_TYPE.register("generic_jigsaw_structure", () -> () -> GenericJigsawStructure.CODEC);
    public static RegistryEntry<StructureType<MansionStructure>, StructureType<MansionStructure>> MANSION_STRUCTURE = STRUCTURE_TYPE.register("mansion_structure", () -> () -> MansionStructure.CODEC);
    public static RegistryEntry<StructureType<MonumentStructure>, StructureType<MonumentStructure>> MONUMENT_STRUCTURE = STRUCTURE_TYPE.register("monument_structure", () -> () -> MonumentStructure.CODEC);
    public static RegistryEntry<StructureType<MineshaftStructure>, StructureType<MineshaftStructure>> GENERIC_MINESHAFT = STRUCTURE_TYPE.register("generic_mineshaft", () -> () -> MineshaftStructure.CODEC);
    public static RegistryEntry<StructureType<MineshaftEndStructure>, StructureType<MineshaftEndStructure>> MINESHAFT_END = STRUCTURE_TYPE.register("mineshaft_end", () -> () -> MineshaftEndStructure.CODEC);
    public static RegistryEntry<StructureType<StrongholdEndStructure>, StructureType<StrongholdEndStructure>> STRONGHOLD_END = STRUCTURE_TYPE.register("stronghold_end", () -> () -> StrongholdEndStructure.CODEC);
    public static RegistryEntry<StructureType<ShipwreckNetherStructure>, StructureType<ShipwreckNetherStructure>> SHIPWRECK_NETHER_STRUCTURE = STRUCTURE_TYPE.register("shipwreck_nether_structure", () -> () -> ShipwreckNetherStructure.CODEC);
    public static RegistryEntry<StructureType<CityNetherStructure>, StructureType<CityNetherStructure>> CITY_NETHER_STRUCTURE = STRUCTURE_TYPE.register("city_nether_structure", () -> () -> CityNetherStructure.CODEC);
    public static RegistryEntry<StructureType<GenericNetherJigsawStructure>, StructureType<GenericNetherJigsawStructure>> GENERIC_NETHER_JIGSAW_STRUCTURE = STRUCTURE_TYPE.register("generic_nether_jigsaw_structure", () -> () -> GenericNetherJigsawStructure.CODEC);
}


