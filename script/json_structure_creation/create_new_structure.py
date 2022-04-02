from os.path import exists
import os
import json

fabric_src = "C:\\Users\\MSI Laptop\\Documents\\ModWorkspace\\RepurposedStructures-Fabric\\src"
forge_src = "C:\\Users\\MSI Laptop\\Documents\\ModWorkspace\\RepurposedStructures\\src"
data_folder = os.path.join('main', 'resources', 'data', 'repurposed_structures')

def createFile(input_path, output_path, regex_list):
    file_content = ''
    with open(input_path, 'r') as file:
        file_content = file.read()
        for i in range(len(regex_list)):
           file_content = file_content.replace(f'${i + 1}', regex_list[i])

    path = os.path.join(forge_src, output_path)
    if not exists(path):
        os.makedirs(os.path.dirname(path), exist_ok=True)
        with open(path, 'w') as codefile1:
            codefile1.write(file_content)

    path = os.path.join(fabric_src, output_path)
    if not exists(path):
        os.makedirs(os.path.dirname(path), exist_ok=True)
        with open(path, 'w') as codefile2:
            codefile2.write(file_content)
        

#--------------------------------------------------------------------------------------------

restart = True
while restart:
    
    structure_type = input("\nstructure type (village, etc)\n").strip()
    structure_type_plural = structure_type if structure_type.endswith('s') else ((structure_type[:-1] + 'ies') if structure_type.endswith('y') else (structure_type + 's'));
    structure_variant = input("\nstructure variant (jungle, etc)\n").strip()

    start_pool = input("\nstart pool path (What's after the :)\n").strip()
    structure_size = input("\nsize of structure\n")
    adjusts_surface = input("\nadjusts surface (y/n)\n")

    structure_set = input("\nstructure set path to add to (name of json file without .json)\n")

    #-------------------------------------------------------------------------------------------

    # Configured Structure Feature
    createFile(
        os.path.join('template', 'configured_structure_feature.json'),
        os.path.join(data_folder, 'worldgen', 'configured_structure_feature', f'{structure_type}_{structure_variant}.json'),
        [structure_type_plural, structure_variant, start_pool, structure_size, 'true' if adjusts_surface == 'y' else 'false'])

    # Structure Set
    path = os.path.join(forge_src, data_folder, 'worldgen', 'structure_set', f'{structure_set}.json')
    with open(path, 'r+') as file:
        jsonData = json.load(file)
        jsonData['structures'].append({ 
            'structure': f'repurposed_structures:{structure_type}_{structure_variant}',
            'weight': 1
        })
        file.seek(0)
        file.write(json.dumps(jsonData, indent=2))
        file.truncate()
    path = os.path.join(fabric_src, data_folder, 'worldgen', 'structure_set', f'{structure_set}.json')
    with open(path, 'r+') as file:
        jsonData = json.load(file)
        jsonData['structures'].append({ 
            'structure': f'repurposed_structures:{structure_type}_{structure_variant}',
            'weight': 1
        })
        file.seek(0)
        file.write(json.dumps(jsonData, indent=2))
        file.truncate()

    # Template Pool
    createFile(
        os.path.join('template', 'template_pool.json'),
        os.path.join(data_folder, 'worldgen', 'template_pool', f'{start_pool}.json'),
        [start_pool])

    # Configured Structure Feature Tag
    path = os.path.join(forge_src, data_folder, 'tags', 'worldgen', 'configured_structure_feature', 'collections', f'{structure_type_plural}.json')
    with open(path, 'r+') as file:
        jsonData = json.load(file)
        jsonData['values'].append(f'repurposed_structures:{structure_type}_{structure_variant}')
        file.seek(0)
        file.write(json.dumps(jsonData, indent=2))
        file.truncate()
    path = os.path.join(fabric_src, data_folder, 'tags', 'worldgen', 'configured_structure_feature', 'collections', f'{structure_type_plural}.json')
    with open(path, 'r+') as file:
        jsonData = json.load(file)
        jsonData['values'].append(f'repurposed_structures:{structure_type}_{structure_variant}')
        file.seek(0)
        file.write(json.dumps(jsonData, indent=2))
        file.truncate()

    # Biome Tags
    createFile(
        os.path.join('template', 'biome_tag.json'),
        os.path.join(data_folder, 'tags', 'worldgen', 'biome', 'has_structure', structure_type_plural, f'{structure_variant}.json'),
        [])

    # Advancements
    path = os.path.join(forge_src, data_folder, 'advancements', f'{structure_type_plural}.json')
    if exists(path):
        with open(path, 'r+') as file:
            jsonData = json.load(file)
            jsonData['criteria'][f'in_{structure_type}_{structure_variant}'] = {
                "trigger": "minecraft:location",
                "conditions": {
                    "feature": f'repurposed_structures:{structure_type}_{structure_variant}'
                }
            }
            jsonData['requirements'].append(
                [f'in_{structure_type}_{structure_variant}']
            )
            file.seek(0)
            file.write(json.dumps(jsonData, indent=2))
            file.truncate()
    path = os.path.join(fabric_src, data_folder, 'advancements', f'{structure_type_plural}.json')
    if exists(path):
        with open(path, 'r+') as file:
            jsonData = json.load(file)
            jsonData['criteria'][f'in_{structure_type}_{structure_variant}'] = {
                "trigger": "minecraft:location",
                "conditions": {
                    "feature": f'repurposed_structures:{structure_type}_{structure_variant}'
                }
            }
            jsonData['requirements'].append(
                [f'in_{structure_type}_{structure_variant}']
            )
            file.seek(0)
            file.write(json.dumps(jsonData, indent=2))
            file.truncate()

    print('\n\nFINISHED!')
    print('\nRESTARTING!\n\n')
