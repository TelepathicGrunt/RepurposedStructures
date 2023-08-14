import python_nbt.nbt as nbt
from pathlib import Path
import collections.abc
import os

# https://pypi.org/project/Python-NBT/
#--------------------------------------------------------------------------------------------

blockPalette = {}

originalBiome = ""
newBiome = ""
string_blacklist = []
conversion_partial_dict = {
    "spruce":"cherry",
    "mountains":"cherry"
}
conversion_exact_dict = {
    "minecraft:stone_brick_wall":"minecraft:brick_wall",
    "minecraft:stone_brick_stairs":"minecraft:brick_stairs",
    "minecraft:stone_brick_slab":"minecraft:brick_slab",
    "minecraft:mossy_cobblestone":"minecraft:bricks",
    "minecraft:cobblestone":"minecraft:bricks",
    "minecraft:cobblestone_slab":"minecraft:brick_slab",
    "minecraft:cobblestone_wall":"minecraft:brick_wall",
    "minecraft:stone_bricks":"minecraft:cherry_wood",
    "minecraft:stone":"minecraft:cherry_planks",
    "minecraft:stone_bricks":"minecraft:cherry_wood",
    "minecraft:tall_grass":"minecraft:lilac",
    "minecraft:large_fern":"minecraft:peony",
    "minecraft:fern":"minecraft:pink_petals",
    "minecraft:poppy":"minecraft:pink_tulip",
    "minecraft:dandelion":"minecraft:allium",
    "minecraft:grass":"minecraft:allium",
    "minecraft:potted_poppy":"minecraft:potted_pink_tulip",
    "minecraft:purple_carpet":"minecraft:pink_carpet",
    "minecraft:purple_bed":"minecraft:pink_bed",
    "minecraft:green_carpet":"minecraft:yellow_carpet",
    "minecraft:green_bed":"minecraft:yellow_bed",
    "minecraft:gray_stained_glass_pane":"minecraft:white_stained_glass_pane"
}
#-------------------------------------------------------------------------------------------

def string_replacer(nbt_string):
    if nbt_string.value not in string_blacklist:
        for key, replacement in conversion_exact_dict.items():
            if nbt_string.value == key:
                replacementValue = nbt_string.value.replace(key, replacement)
                blockPalette[nbt_string.value] = replacementValue
                nbt_string.value = replacementValue
                return
        for key, replacement in conversion_partial_dict.items():
            if key in nbt_string.value:
                replacementValue = nbt_string.value.replace(key, replacement)
                blockPalette[nbt_string.value] = replacementValue
                nbt_string.value = replacementValue

def property_replacer(nbt_key, nbt_string, property_name, value_to_replace, new_value):
    if nbt_key == property_name:
        if nbt_string.value == value_to_replace:
            blockPalette[nbt_string.value] = new_value
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
                if entry.value['Name'].value not in blockPalette.keys():
                    blockPalette[entry.value['Name'].value] = entry.value['Name'].value

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

for x in sorted(blockPalette.items()):
  printString = "\""+x[0]+"\":"
  for y in range(max(40 - len(x[0]), 2)):
    printString = printString + " "
  printString = printString + "\""+x[1]+"\","
  print(printString)

print("FINISHED!")
input()
