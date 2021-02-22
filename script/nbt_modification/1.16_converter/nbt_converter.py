import python_nbt.nbt as nbt
from pathlib import Path
import collections.abc
import os
import sys

# https://pypi.org/project/Python-NBT/

'''
string_blacklist = ["minecraft:village/taiga/villagers"]
conversion_partial_dict = {
    
}
conversion_exact_dict = {
    "minecraft:chests/pillager_outpost":"repurposed_structures:chests/outpost/badlands_chest",
    "minecraft:dark_oak_log":"minecraft:chiseled_red_sandstone",
    "minecraft:dark_oak_planks":"minecraft:smooth_red_sandstone",
    "minecraft:dark_oak_fence":"minecraft:dark_oak_fence",
    "minecraft:dark_oak_slab":"minecraft:dark_oak_slab",
    "minecraft:dark_oak_stairs":"minecraft:dark_oak_stairs",
    "minecraft:birch_planks":"minecraft:terracotta",
    "minecraft:cobblestone":"minecraft:cut_red_sandstone",
    "minecraft:cobblestone_stairs":"minecraft:cut_red_sandstone",
    "minecraft:potted_dead_bush":"minecraft:potted_dead_bush",
    "minecraft:potted_brown_mushroom":"minecraft:potted_brown_mushroom",
    "minecraft:potted_red_mushroom":"minecraft:potted_red_mushroom",
    "minecraft:lantern":"minecraft:lantern"
}
conversion_exact_dict = {
    "minecraft:chests/pillager_outpost":"repurposed_structures:chests/outpost/badlands_chest",
    "minecraft:dark_oak_log":"minecraft:chiseled_red_sandstone",
    "minecraft:dark_oak_planks":"minecraft:red_sandstone",
    "minecraft:dark_oak_fence":"minecraft:dark_oak_fence",
    "minecraft:dark_oak_slab":"minecraft:red_sandstone_slab",
    "minecraft:dark_oak_stairs":"minecraft:red_sandstone_stairs",
    "minecraft:birch_planks":"minecraft:terracotta",
    "minecraft:cobblestone":"minecraft:red_sandstone",
    "minecraft:cobblestone_stairs":"minecraft:red_sandstone_stairs",
    "minecraft:cobblestone_slab":"minecraft:red_sandstone_slab",
    "minecraft:potted_dead_bush":"minecraft:potted_dead_bush",
    "minecraft:potted_brown_mushroom":"minecraft:potted_dead_bush",
    "minecraft:potted_red_mushroom":"minecraft:potted_cactus",
    "minecraft:lantern":"minecraft:lantern"
}

'''

#--------------------------------------------------------------------------------------------

blockPalette = {""}

originalBiome = "savage"
newBiome = "nether_brick"
string_blacklist = []
conversion_partial_dict = {
}
conversion_exact_dict = {
    "minecraft:pillager_outpost/towers":"repurposed_structures:outposts/"+newBiome+"/towers",
    "minecraft:pillager_outpost/feature_plates":"repurposed_structures:outposts/"+newBiome+"/plates"
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