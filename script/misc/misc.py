from pathlib import Path
import os
import sys
import random
import json
from os.path import exists
import shutil
import random

print(random.randint(0, 2147483646))
print(random.randint(0, 2147483646))
print(random.randint(0, 2147483646))

structure_type = input("\nType: \n").lower().strip()
initial_variant = input("\nInitial Variant: \n").lower().strip()
restart = True
while restart:
    variant = input("\nNew Variant: \n").lower().strip()
    replacing_registry_name = structure_type + '_' + initial_variant
    registry_name = structure_type + '_' + variant
    file_content = ""

    biomeTagPath = os.path.join('result', 'has_structure', structure_type + "s", variant + '.json');
    os.makedirs(os.path.dirname(biomeTagPath), exist_ok=True)
    shutil.copyfile(os.path.join('src', 'biome_tag.json'), biomeTagPath)

    if not exists(os.path.join('result', 'spawnrate.json')):
        shutil.copyfile(os.path.join('src', 'spawnrate.json'), os.path.join('result', 'spawnrate.json'))

    with open(os.path.join('result', 'spawnrate.json'), "r+") as file:
        jsonData = json.load(file)
        jsonData["structures"].append({
            "structure": "repurposed_structures:" + registry_name,
            "weight": 1
        })
        jsonData["placement"]["salt"] = random.randint(0, 2147483646)
        file.seek(0)
        file.write(json.dumps(jsonData, indent=2))
        file.truncate()

    with open(os.path.join('src', replacing_registry_name + '.json'), "r") as file:
        file_content = file.read().replace(initial_variant, variant)

    path = os.path.join('result', registry_name +'.json')
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w") as file:
        file.write(file_content)

print("FINISHED!")
input()