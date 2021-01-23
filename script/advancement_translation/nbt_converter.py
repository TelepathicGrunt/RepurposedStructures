from pathlib import Path
import collections.abc
import os
import sys
import re

translation = {}

#-------------------------------------------------------------------------------------------

with open('en_us.json', 'r') as reader:
    line = reader.readline()
    while line != '': 
        matches = re.search("\"(.+)\":\s*\"(.+)\"", line)
        if matches:
            translation[matches.group(1)] = matches.group(2)
        line = reader.readline()

for subdir, dirs, files in os.walk("toconvert"):
    for file in files:
        directory = subdir + os.sep
        filepath = directory + file

        if filepath.endswith(".json"): 
            directory = directory.replace("toconvert", "converted")
            Path(directory).mkdir(parents=True, exist_ok=True)
            newFile = directory + file
            
            with open(filepath, 'r') as reader:
                with open(newFile, 'w') as writer:
                    line = reader.readline()
                    while line != '': 
                        replacedLine = line
                        for key in translation.keys():
                            replacedLine = replacedLine.replace(key, translation[key])
                        writer.write(replacedLine.replace(key, translation[key]))
                        line = reader.readline()

            continue
        else:
            continue


print("FINISHED!")
input()