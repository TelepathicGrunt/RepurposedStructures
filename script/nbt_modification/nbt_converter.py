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
    "minecraft:hay_bale": "minecraft:hay_block"
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
        
        '''
        if 'palette' in nbt_list:
            for entry in nbt_list['palette'].value:
                blockPalette.add(entry.value['Name'].value)
        '''

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

            property_replacer(key, entry, "PersistenceRequired", 0, 1)
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
  print(x)

print("FINISHED!")
input()


#------------------------------------------------------------------------


'''
# End Better Strongholds
conversion_partial_dict = {
    "white": "magenta",
    "lime_bed": "purple_bed",
    "betterstrongholds:chests/": "betterstrongholds:chests/end/",
    "betterstrongholds:": "betterstrongholds:end/"
}
conversion_exact_dict = {
    "minecraft:stone_button":"minecraft:air",
    "minecraft:stone_bricks":"minecraft:obsidian",
    "minecraft:cobblestone_stairs":"minecraft:purpur_stairs",
    "minecraft:stone_brick_slab":"minecraft:purpur_slab",
    "minecraft:stone_brick_stairs":"minecraft:purpur_stairs",
    "minecraft:cobblestone":"minecraft:obsidian",
    "minecraft:cobblestone_slab":"minecraft:purpur_slab",
    "minecraft:cobblestone_bricks":"minecraft:purpur_block",
    "minecraft:chiseled_stone_bricks":"minecraft:purpur_block",
    "minecraft:polished_andesite_stairs":"minecraft:purpur_stairs",
    "minecraft:polished_andesite_slab":"minecraft:purpur_slab",
    "minecraft:smooth_stone_slab":"minecraft:purpur_slab",

    "minecraft:spruce_wall_sign":"minecraft:crimson_wall_sign",
    "minecraft:spruce_trapdoor":"minecraft:iron_trapdoor",
    "minecraft:spruce_planks":"minecraft:purpur_block",
    "minecraft:spruce_stairs":"minecraft:purpur_stairs",
    "minecraft:spruce_slab":"minecraft:purpur_slab",
    "minecraft:oak_stairs":"minecraft:purpur_stairs",
    "minecraft:oak_slab":"minecraft:purpur_slab",
    "minecraft:stripped_oak_log":"minecraft:purpur_pillar",
    "minecraft:bookshelf":"minecraft:amethyst_block",

    "minecraft:white_stained_glass":"minecraft:air",
    "minecraft:gray_stained_glass":"minecraft:air",
    "minecraft:yellow_stained_glass":"minecraft:obsidian",
    "minecraft:purpur_block":"minecraft:diamond_block",
    "minecraft:cyan_concrete":"minecraft:air",
    "minecraft:cyan_terracotta":"minecraft:air",

    "minecraft:water":"minecraft:amethyst_block",
    "minecraft:flowing_water":"minecraft:amethyst_block",
    "minecraft:lava":"minecraft:amethyst_block",
    "minecraft:flowing_lava":"minecraft:amethyst_block"
}
'''


'''
#Nether Better Strongholds

conversion_partial_dict = {
    "spruce": "crimson",
    "oak": "crimson",
    "betterstrongholds:": "betterstrongholds:nether/",
    "minecraft:lantern": "minecraft:soul_lantern",
    "andesite_wall": "blackstone_wall"
}
conversion_exact_dict = {
    "minecraft:water": "minecraft:lava",
    "minecraft:white_stained_glass": "minecraft:cave_air",
    "minecraft:gray_stained_glass": "minecraft:cave_air",
    "minecraft:cyan_concrete": "minecraft:blackstone",
    "minecraft:cyan_terracotta": "minecraft:black_terracotta",
    "minecraft:smooth_stone_slab": "minecraft:red_nether_brick_slab",
    "minecraft:red_bed": "minecraft:orange_bed",
    "minecraft:lime_bed": "minecraft:black_bed",
    "minecraft:gray_bed": "minecraft:red_bed",
    "minecraft:iron_ore": "minecraft:nether_quartz_ore",
    "minecraft:stone_pressure_plate": "minecraft:crimson_pressure_plate",
    "minecraft:skeleton_skull": "minecraft:wither_skeleton_skull",
    "minecraft:cobblestone_stairs": "minecraft:nether_brick_stairs",
    "minecraft:stone_brick_stairs": "minecraft:polished_blackstone_brick_stairs",
    "minecraft:chiseled_stone_bricks": "minecraft:chiseled_nether_bricks",
    "minecraft:white_stained_glass_pane": "minecraft:gray_stained_glass_pane",
    "minecraft:andesite_wall": "minecraft:blackstone_wall",
    "minecraft:polished_andesite": "minecraft:polished_blackstone",
    "minecraft:polished_andesite_slab": "minecraft:polished_blackstone_slab",
    "minecraft:polished_andesite_stairs": "minecraft:polished_blackstone_stairs",
    "minecraft:stone_brick_slab": "minecraft:polished_blackstone_brick_slab",
    "minecraft:coal_block": "minecraft:quartz_block",
    "minecraft:cobblestone": "minecraft:nether_bricks",
    "minecraft:stone_bricks": "minecraft:polished_blackstone_bricks",
    "minecraft:petrified_oak_slab": "minecraft:red_nether_brick_slab",
    "minecraft:nether_brick_slab": "minecraft:red_nether_brick_slab",
    "minecraft:nether_brick_stairs": "minecraft:red_nether_brick_stairs",
    "minecraft:stripped_oak_wood": "minecraft:stripped_crimson_hyphae",
    "minecraft:birch_pressure_plate": "minecraft:crimson_pressure_plate",
    "minecraft:bookshelves": "minecraft:gilded_blackstone",
    "minecraft:yellow_stained_glass": "minecraft:chiseled_polished_blackstone",
    "minecraft:stripped_spruce_wood": "minecraft:stripped_crimson_hyphae",
    "minecraft:stripped_spruce_log": "minecraft:stripped_crimson_hyphae",
    "minecraft:bookshelf": "minecraft:gilded_blackstone",
    "minecraft:stone_brick_wall": "minecraft:polished_blackstone_brick_wall",
    "minecraft:torch": "minecraft:soul_torch",
    "minecraft:wall_torch": "minecraft:wall_soul_torch",
    "stone_brick_wall[east=none,south=none,north=tall,up=true,west=none]": "minecraft:polished_blackstone_brick_wall[east=none,south=none,north=tall,up=true,west=none]",
    "stone_brick_wall[east=none,south=tall,north=none,up=true,west=none]": "minecraft:polished_blackstone_brick_wall[east=none,south=tall,north=none,up=true,west=none]",
    "stone_brick_wall[east=tall,south=none,north=none,up=true,west=none]": "minecraft:polished_blackstone_brick_wall[east=tall,south=none,north=none,up=true,west=none]",
    "stone_brick_wall[east=none,south=none,north=none,up=true,west=tall]": "minecraft:polished_blackstone_brick_wall[east=none,south=none,north=none,up=true,west=tall]",
    "stone_brick_wall[east=none,north=tall,south=none,up=true,west=none]": "minecraft:polished_blackstone_brick_wall[east=none,north=tall,south=none,up=true,west=none]",
    "stone_brick_wall[east=tall,north=none,south=none,up=true,west=none]": "minecraft:polished_blackstone_brick_wall[east=tall,north=none,south=none,up=true,west=none]",
    "stone_brick_wall[east=none,south=none,north=none,up=true,west=tall]": "minecraft:polished_blackstone_brick_wall[east=none,north=none,south=none,up=true,west=tall]",
    "stone_brick_wall[east=none,north=none,south=tall,up=true,west=none]": "minecraft:polished_blackstone_brick_wall[east=none,north=none,south=tall,up=true,west=none]"
}
'''
