from pathlib import Path
import os
import sys
import random
import json
from os.path import exists
import shutil
import random
import os
 
# Get the list of all files and directories
for (subdir, dirs, files) in os.walk("C:\\Users\\MSI Laptop\\Documents\\ModWorkspace\\RepurposedStructures\\common\\src\\main\\resources\\data\\repurposed_structures\\worldgen\\structure", topdown=True):
    for file in files:
        fileName = os.path.basename(file).split(".")[0]
        translatedNameSplit = fileName.replace("_", " ").title().split(" ")
        translatedName = translatedNameSplit[len(translatedNameSplit) - 1] + " " + ' '.join(translatedNameSplit[:-1])
        print(f'\"structure.repurposed_structures.{fileName}\": "{translatedName}",')

print("FINISHED!")
input()