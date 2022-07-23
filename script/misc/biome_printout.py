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
for (subdir, dirs, files) in os.walk("biome", topdown=True):
    for file in files:
        print(f'\"wythers:{file.split(".json")[0]}\",')

print("FINISHED!")
input()