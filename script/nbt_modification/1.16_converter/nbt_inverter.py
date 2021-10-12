import python_nbt.nbt as nbt
import json
from pathlib import Path
import collections.abc
import os
from pprint import pprint

# https://pypi.org/project/Python-NBT/

'''

'''

#--------------------------------------------------------------------------------------------

blockPalette = []

originalBiome = ""
newBiome = ""
string_blacklist = []
conversion_partial_dict = {}
conversion_exact_dict = {}

#-------------------------------------------------------------------------------------------

ySize = 0
isWall = False

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
            return True
    return False


def traverse_dicts(nbt_list):
    if isinstance(nbt_list, collections.abc.Mapping):
        '''
        if 'palette' in nbt_list:
            for entry in nbt_list['palette'].value:
                blockPalette.append(entry)
        '''

        if "Name" in nbt_list:
            isWall = "wall" in nbt_list["Name"].value

        for key, entry in nbt_list.items():
            if key == "pos" or key == "blockPos":
                entry[1].value = ySize.value - entry[1].value - 1

            if property_replacer(key, entry, "up", "false", "true"):
                continue
            if not isWall and property_replacer(key, entry, "up", "true", "false"):
                continue
            if property_replacer(key, entry, "down", "false", "true"):
                continue
            if property_replacer(key, entry, "down", "true", "false"):
                continue
            if property_replacer(key, entry, "orientation", "up_north", "down_north"):
                continue
            if property_replacer(key, entry, "orientation", "up_south", "down_south"):
                continue
            if property_replacer(key, entry, "orientation", "up_west", "down_west"):
                continue
            if property_replacer(key, entry, "orientation", "up_east", "down_east"):
                continue
            if property_replacer(key, entry, "orientation", "down_north", "up_north"):
                continue
            if property_replacer(key, entry, "orientation", "down_south", "up_south"):
                continue
            if property_replacer(key, entry, "orientation", "down_west", "up_west"):
                continue
            if property_replacer(key, entry, "orientation", "down_east", "up_east"):
                continue
            if property_replacer(key, entry, "half", "bottom", "top"):
                continue
            if property_replacer(key, entry, "half", "top", "bottom"):
                continue
            if property_replacer(key, entry, "type", "bottom", "top"):
                continue
            if property_replacer(key, entry, "type", "top", "bottom"):
                continue
            if property_replacer(key, entry, "facing", "up", "down"):
                continue
            if property_replacer(key, entry, "facing", "down", "up"):
                continue
            if property_replacer(key, entry, "face", "ceiling", "floor"):
                continue
            if property_replacer(key, entry, "face", "floor", "ceiling"):
                continue

            if isinstance(entry, nbt.NBTTagList) or isinstance(entry, nbt.NBTTagCompound):
                traverse_dicts(entry)
            elif isinstance(entry, nbt.NBTTagString):
                string_replacer(entry)


    elif isinstance(nbt_list, nbt.NBTTagList) or isinstance(nbt_list, nbt.NBTTagCompound):
        for index, entry in enumerate(nbt_list):
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
            if "size" in nbtfile:
                ySize = nbtfile["size"][1]
            traverse_dicts(nbtfile)

            directory = directory.replace("toconvert", "converted").replace(originalBiome, newBiome)
            Path(directory).mkdir(parents=True, exist_ok=True)
            newFile = directory + file.replace(originalBiome, newBiome)

            nbt.write_to_nbt_file(newFile, nbtfile)
            continue
        else:
            continue


for x in blockPalette:
  pprint(x)

print("FINISHED!")
input()