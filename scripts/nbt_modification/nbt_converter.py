import python_nbt.nbt as nbt
from pathlib import Path
import collections.abc
import os

# https://pypi.org/project/Python-NBT/
#--------------------------------------------------------------------------------------------

blockPalette = {""}

originalBiome = ""
newBiome = ""
string_blacklist = []
conversion_partial_dict = {
}
conversion_exact_dict = {
}
#-------------------------------------------------------------------------------------------

def string_replacer(nbt_string):
    if nbt_string.value not in string_blacklist:
        for key, replacement in conversion_exact_dict.items():
            if nbt_string.value == key:
                nbt_string.value = nbt_string.value.replace(key, replacement)
                return
        for key, replacement in conversion_partial_dict.items():
            if key in nbt_string.value:
                nbt_string.value = nbt_string.value.replace(key, replacement)

def property_replacer(nbt_key, nbt_string, property_name, value_to_replace, new_value):
    if nbt_key == property_name:
        if nbt_string.value == value_to_replace:
            nbt_string.value = new_value
            return



def traverse_dicts(nbt_list):
    if isinstance(nbt_list, collections.abc.Mapping):
        '''
        if 'size' in nbt_list:
            nbt_list['size'][1].value = 32
        '''
        
        if 'palette' in nbt_list:
            for entry in nbt_list['palette'].value:
                blockPalette.add(entry.value['Name'].value)

        '''
        for key, entry in nbt_list.items():
            if key == "pos" or key == "blockPos" or key == "size":
                entry[1].value = entry[1].value + 2
        '''

        nbt_list.pop('SleepingX', None)
        nbt_list.pop('SleepingY', None)
        nbt_list.pop('SleepingZ', None)
        
        if 'Attributes' in nbt_list:
            attributes = nbt_list['Attributes']
            for entry in attributes:
                if entry["Name"] == "forge:entity_gravity" or entry["Name"] == "forge:step_height_addition":
                    nbt_list['Attributes'].remove(entry)
        
        for key, entry in nbt_list.items():

            if isinstance(entry, nbt.NBTTagList) or isinstance(entry, nbt.NBTTagCompound):
                traverse_dicts(entry)
            elif isinstance(entry, nbt.NBTTagString):
                string_replacer(entry)

            #property_replacer(key, entry, "PersistenceRequired", 0, 1)
            #property_replacer(key, entry, "waterlogged", "true", "false")
            #property_replacer(key, entry, "joint", "rollable", "aligned")


    elif isinstance(nbt_list, nbt.NBTTagList) or isinstance(nbt_list, nbt.NBTTagCompound):
        for entry in nbt_list:
            if isinstance(entry, nbt.NBTTagInt):
                continue

            if isinstance(nbt_list, nbt.NBTTagList) or isinstance(entry, nbt.NBTTagCompound):
                traverse_dicts(entry)
            elif isinstance(entry, nbt.NBTTagString):
                string_replacer(entry)

for (subdir, dirs, files) in os.walk("toconvert", topdown=True):
    for file in files:
        directory = subdir + os.sep
        filepath = directory + file

        if filepath.endswith(".nbt"): 
            nbtfile = nbt.read_from_nbt_file(filepath)
            traverse_dicts(nbtfile)

            directory = directory.replace("toconvert", "converted").replace(originalBiome, newBiome)
            Path(directory).mkdir(parents=True, exist_ok=True)
            newFile = directory + file.replace(originalBiome, newBiome)

            nbt.write_to_nbt_file(newFile, nbtfile)
            continue
        else:
            continue

for x in sorted(blockPalette):
  print("\""+x+"\": \""+x+"\",")

print("FINISHED!")
input()


#------------------------------------------------------------------------
'''

    "create:andesite_casing": "create:andesite_casing",
    "create:andesite_encased_cogwheel": "create:andesite_encased_cogwheel",
    "create:andesite_encased_large_cogwheel": "create:andesite_encased_large_cogwheel",
    "create:andesite_encased_shaft": "create:andesite_encased_shaft",
    "create:encased_chain_drive": "create:encased_chain_drive",
    "create:gearbox": "create:gearbox",
    "create:hand_crank": "create:hand_crank",
    "create:large_cogwheel": "create:large_cogwheel",
    "create:mechanical_bearing": "create:mechanical_bearing",
    "create:pulley_magnet": "create:pulley_magnet",
    "create:rope": "create:rope",
    "create:rope_pulley": "create:rope_pulley",
    "create:sequenced_gearshift": "create:sequenced_gearshift",
    "create:speedometer": "create:speedometer",
    "create:water_wheel": "create:water_wheel",
    "hexfortress:blaze_cluster": "minecraft:stone_brick_wall",
    "hexfortress:chiseled_golden_heavy_nether_bricks": "minecraft:chiseled_stone_bricks",    
    "hexfortress:chiseled_heavy_nether_bricks": "minecraft:chiseled_stone_bricks",
    "hexfortress:chiseled_polished_heavy_nether_bricks": "minecraft:chiseled_stone_bricks",
    "hexfortress:dungeon_chest": "minecraft:chest",
    "hexfortress:faded_nether_bricks": "minecraft:andesite",
    "hexfortress:fortress_book_pile": "minecraft:bookshelf",
    "hexfortress:fortress_explosive_barrel": "minecraft:tnt",
    "hexfortress:fortress_pot": "minecraft:flower_pot",
    "hexfortress:gold_ingot_pile": "minecraft:raw_gold_block",
    "hexfortress:heavy_nether_brick_pillar": "minecraft:polished_basalt",
    "hexfortress:heavy_nether_brick_slab": "minecraft:andesite_slab",
    "hexfortress:heavy_nether_brick_stairs": "minecraft:andesite_stairs",
    "hexfortress:heavy_nether_brick_wall": "minecraft:andesite_wall",
    "hexfortress:heavy_nether_bricks": "minecraft:stone_bricks",
    "hexfortress:locked_chest": "minecraft:chest",
    "hexfortress:mixed_nether_bricks": "minecraft:andesite",
    "hexfortress:mixed_nether_tiles": "minecraft:andesite",
    "hexfortress:mixed_red_nether_bricks": "minecraft:mossy_stone_bricks",
    "hexfortress:mixed_soul_sand_nether_tiles_heavy": "minecraft:tuff",
    "hexfortress:mixed_soul_sand_nether_tiles_light": "minecraft:tuff",
    "hexfortress:nether_brick_pillar": "minecraft:polished_basalt_pillar",
    "hexfortress:nether_tile_slab": "minecraft:stone_brick_slab",
    "hexfortress:nether_tile_stairs": "minecraft:stone_brick_stairs",
    "hexfortress:nether_tiles": "minecraft:stone_bricks",
    "hexfortress:polished_heavy_nether_brick_slab": "minecraft:stone_brick_slab",
    "hexfortress:polished_heavy_nether_brick_stairs": "minecraft:stone_brick_stairs",
    "hexfortress:polished_heavy_nether_brick_wall": "minecraft:stone_brick_wall",
    "hexfortress:polished_heavy_nether_bricks": "minecraft:stone_bricks",
    "hexfortress:polished_nether_bricks": "minecraft:polished_andesite",
    "hexfortress:resistance_canceler": "minecraft:spawner",
    "hexfortress:wither_leaves": "minecraft:jungle_leaves",
    "hexfortress:wither_vine": "minecraft:glow_lichen",
    "minecraft:air": "minecraft:air",
    "minecraft:ancient_debris": "minecraft:diamond_ore",
    "minecraft:barrel": "minecraft:barrel",
    "minecraft:basalt": "minecraft:clay",
    "minecraft:black_wool": "minecraft:brown_wool",
    "minecraft:blackstone": "minecraft:tuff",
    "minecraft:blackstone_stairs": "minecraft:stone_brick_stairs",
    "minecraft:bone_block": "minecraft:bone_block",
    "minecraft:cauldron": "minecraft:cauldron",
    "minecraft:cave_air": "minecraft:cave_air",
    "minecraft:chain": "minecraft:chain",
    "minecraft:chest": "minecraft:chest",
    "minecraft:chiseled_nether_bricks": "minecraft:chiseled_stone_bricks",
    "minecraft:coal_block": "minecraft:iron_block",
    "minecraft:comparator": "minecraft:comparator",
    "minecraft:cracked_nether_bricks": "minecraft:cracked_stone_bricks",
    "minecraft:crimson_button": "minecraft:stone_button",
    "minecraft:crimson_door": "minecraft:jungle_door",
    "minecraft:crimson_planks": "minecraft:jungle_planks",
    "minecraft:crimson_slab": "minecraft:jungle_slab",
    "minecraft:crimson_stairs": "minecraft:jungle_stairs",
    "minecraft:crimson_trapdoor": "minecraft:jungle_trapdoor",
    "minecraft:crying_obsidian": "minecraft:mud",
    "minecraft:diamond_block": "minecraft:emerald_block",
    "minecraft:diorite": "minecraft:diorite",
    "minecraft:fire": "minecraft:red_mushroom",
    "minecraft:glowstone": "minecraft:redstone_lamp",
    "minecraft:gold_block": "minecraft:iron_block",
    "minecraft:iron_bars": "minecraft:iron_bars",
    "minecraft:jigsaw": "minecraft:jigsaw",
    "minecraft:lantern": "minecraft:lantern",
    "minecraft:lava": "minecraft:water",
    "minecraft:lava_cauldron": "minecraft:water_cauldron",
    "minecraft:magma_block": "minecraft:mud",
    "minecraft:nether_brick_fence": "minecraft:iron_bars",
    "minecraft:nether_brick_slab": "minecraft:stone_brick_slab",
    "minecraft:nether_brick_stairs": "minecraft:stone_brick_stairs",
    "minecraft:nether_brick_wall": "minecraft:stone_brick_wall",
    "minecraft:nether_bricks": "minecraft:stone_bricks",
    "minecraft:nether_wart": "minecraft:brown_mushroom",
    "minecraft:netherrack": "minecraft:coarse_dirt",
    "minecraft:obsidian": "minecraft:mud",
    "minecraft:orange_concrete": "minecraft:brown_concrete",
    "minecraft:orange_terracotta": "minecraft:brown_terracotta",
    "minecraft:orange_wool": "minecraft:brown_wool",
    "minecraft:polished_basalt": "minecraft:polished_basalt",
    "minecraft:polished_blackstone": "minecraft:polished_andesite",
    "minecraft:polished_blackstone_brick_stairs": "minecraft:polished_andesite_stairs",
    "minecraft:polished_blackstone_bricks": "minecraft:polished_andesite",
    "minecraft:polished_blackstone_button": "minecraft:stone_button",
    "minecraft:polished_blackstone_pressure_plate": "minecraft:stone_pressure_plate",
    "minecraft:polished_blackstone_slab": "minecraft:polished_andesite_slab",
    "minecraft:polished_blackstone_stairs": "minecraft:polished_andesite_stairs",
    "minecraft:polished_blackstone_wall": "minecraft:andesite_wall",
    "minecraft:prismarine_brick_stairs": "minecraft:mossy_stone_brick_stairs",
    "minecraft:prismarine_stairs": "minecraft:mossy_stone_brick_stairs",
    "minecraft:purple_terracotta": "minecraft:green_terracotta",
    "minecraft:purpur_stairs": "minecraft:mossy_stone_brick_stairs",
    "minecraft:red_nether_brick_slab": "minecraft:mossy_stone_brick_slab",
    "minecraft:red_nether_brick_stairs": "minecraft:mossy_stone_brick_stairs",
    "minecraft:red_nether_brick_wall": "minecraft:mossy_stone_brick_wall",
    "minecraft:red_nether_bricks": "minecraft:mossy_stone_bricks",
    "minecraft:red_sandstone": "minecraft:stone_bricks",
    "minecraft:red_sandstone_stairs": "minecraft:stone_brick_stairs",
    "minecraft:red_terracotta": "minecraft:green_terracotta",
    "minecraft:red_wool": "minecraft:green_wool",
    "minecraft:redstone_wall_torch": "minecraft:redstone_wall_torch",
    "minecraft:redstone_wire": "minecraft:redstone_wire",
    "minecraft:repeater": "minecraft:repeater",
    "minecraft:shroomlight": "minecraft:torch",
    "minecraft:smooth_basalt": "minecraft:calcite",
    "minecraft:soul_fire": "minecraft:red_mushroom",
    "minecraft:soul_lantern": "minecraft:lantern",
    "minecraft:soul_sand": "minecraft:coarse_dirt",
    "minecraft:soul_soil": "minecraft:dirt",
    "minecraft:soul_wall_torch": "minecraft:wall_torch",
    "minecraft:spawner": "minecraft:spawner",
    "minecraft:stone_brick_stairs": "minecraft:stone_brick_stairs",
    "minecraft:structure_block": "minecraft:structure_block",
    "minecraft:tnt": "minecraft:tnt",
    "minecraft:trapped_chest": "minecraft:trapped_chest",
    "minecraft:warped_button": "minecraft:jungle_button",
    "minecraft:warped_planks": "minecraft:jungle_planks",
    "minecraft:warped_slab": "minecraft:jungle_slab",
    "minecraft:white_wool": "minecraft:gray_wool",
    "minecraft:wither_rose": "minecraft:rose",
    "minecraft:wither_skeleton_skull": "minecraft:skeleton_skull",
    "minecraft:yellow_terracotta": "minecraft:gray_terracotta"


'''