import python_nbt.nbt as nbt
from pathlib import Path
import collections.abc
import os
import sys

# https://pypi.org/project/Python-NBT/

'''
//mansion

string_blacklist = ["minecraft:village/taiga/villagers"]
conversion_partial_dict = {
    
}
conversion_exact_dict = {
    "minecraft:polished_andesite" : "minecraft:smooth_sandstone",
    "minecraft:light_gray_wool" : "minecraft:light_gray_terracotta",
    "minecraft:coarse_dirt" : "minecraft:sand",
    "minecraft:cobblestone_wall" : "minecraft:stone_brick_wall",
    "minecraft:cobblestone" : "minecraft:stone_bricks",
    "minecraft:blue_orchid" : "minecraft:dandelion",
    "minecraft:pumpkin" : "minecraft:cobblestone",
    "minecraft:stripped_spruce_log" : "minecraft:cobblestone",
    "minecraft:pumpkin_stem" : "minecraft:wheat",
    "minecraft:white_bed":"minecraft:gray_bed",
    "minecraft:blue_bed":"minecraft:green_bed",
    "minecraft:red_bed":"minecraft:gray_bed",
    "minecraft:glass_pane":"minecraft:gray_stained_glass_pane"
}

'''

#--------------------------------------------------------------------------------------------

blockPalette = {""}

originalBiome = ""
newBiome = ""
string_blacklist = []
conversion_partial_dict = {
    "repurposed_structures:mansion/":"repurposed_structures:chests/mansion/"
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

for subdir, dirs, files in os.walk("toconvert"):
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