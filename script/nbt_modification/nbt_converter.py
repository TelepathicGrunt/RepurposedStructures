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
    "minecraft:red_sand": "minecraft:air"
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
                return

def property_replacer(nbt_key, nbt_string, property_name, value_to_replace, new_value):
    if nbt_key == property_name:
        if nbt_string.value == value_to_replace:
            nbt_string.value = new_value
            return



def traverse_dicts(nbt_list):
    '''
    for entry in nbt_list['palette'].value:
        blockPalette.add(entry.value['Name'].value)

    '''
    if isinstance(nbt_list, collections.abc.Mapping):
        for key, entry in nbt_list.items():
            if isinstance(entry, nbt.NBTTagList) or isinstance(entry, nbt.NBTTagCompound):
                traverse_dicts(entry)
            elif isinstance(entry, nbt.NBTTagString):
                string_replacer(entry)


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


for x in blockPalette:
  print(x)

print("FINISHED!")
input()


#------------------------------------------------------------------------

'''
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