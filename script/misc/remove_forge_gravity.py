import python_nbt.nbt as nbt
from pathlib import Path
import collections.abc
import os

# https://pypi.org/project/Python-NBT/
#--------------------------------------------------------------------------------------------

def traverse_dicts(nbt_list):
    if isinstance(nbt_list, collections.abc.Mapping):
        if 'Attributes' in nbt_list:
            attributes = nbt_list['Attributes']
            for entry in attributes:
                if entry["Name"] == "forge:entity_gravity":
                    nbt_list['Attributes'].remove(entry)
        
        for key, entry in nbt_list.items():
            if isinstance(entry, nbt.NBTTagList) or isinstance(entry, nbt.NBTTagCompound):
                traverse_dicts(entry)


    elif isinstance(nbt_list, nbt.NBTTagList) or isinstance(nbt_list, nbt.NBTTagCompound):
        for entry in nbt_list:
            if isinstance(entry, nbt.NBTTagInt):
                continue

            if isinstance(nbt_list, nbt.NBTTagList) or isinstance(entry, nbt.NBTTagCompound):
                traverse_dicts(entry)

for (subdir, dirs, files) in os.walk("toconvert", topdown=True):
    for file in files:
        directory = subdir + os.sep
        filepath = directory + file

        if filepath.endswith(".nbt"): 
            nbtfile = nbt.read_from_nbt_file(filepath)
            traverse_dicts(nbtfile)

            directory = directory.replace("toconvert", "converted")
            Path(directory).mkdir(parents=True, exist_ok=True)
            newFile = directory + file

            nbt.write_to_nbt_file(newFile, nbtfile)
            continue
        else:
            continue

print("FINISHED!")
input()