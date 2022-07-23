import python_nbt.nbt as nbt
import os

# https://pypi.org/project/Python-NBT/
#--------------------------------------------------------------------------------------------

for (subdir, dirs, files) in os.walk("toconvert", topdown=True):
    for file in files:
        directory = subdir + os.sep
        filepath = directory + file

        if filepath.endswith(".nbt"): 
            nbtfile = nbt.read_from_nbt_file(filepath)
            if 'entities' in nbtfile:
                entities = nbtfile['entities']
                if len(entities) > 0:
                    print(f'{subdir}/{file.split(".nbt")[0]}')
            continue
        else:
            continue

print("FINISHED!")
input()