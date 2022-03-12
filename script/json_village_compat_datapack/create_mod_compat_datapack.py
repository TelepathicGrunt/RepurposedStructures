from os.path import exists
import os
import json

datapack_location = "C:\\Users\\MSI Laptop\\Documents\\ModWorkspace\\.Extra\\Backroom\\repurposed_structures\\datapacks"

def createFile(input_path, output_path, regex_list):
    file_content = ''
    with open(input_path, 'r') as file:
        file_content = file.read()
        for i in range(len(regex_list)):
           file_content = file_content.replace(f'${i + 1}', regex_list[i])

    path = os.path.join(datapack_location, output_path)
    if not exists(path):
        os.makedirs(os.path.dirname(path), exist_ok=True)
        with open(path, 'w') as codefile1:
            codefile1.write(file_content)
        

def createPoolFile(input_path, output_path, regex_list):
    file_content = ''
    with open(input_path, 'r') as file:
        file_content = file.read()
        for i in range(len(regex_list)):
           file_content = file_content.replace(f'${i + 1}', regex_list[i])

    path = os.path.join(datapack_location, output_path)
    if not exists(path):
        os.makedirs(os.path.dirname(path), exist_ok=True)
        with open(path, 'w') as codefile1:
            codefile1.write(file_content)
        

#--------------------------------------------------------------------------------------------

restart = True
while restart:
    
    mod_name = input("\nThe name of the mod to make compat with\n").strip()
    mod_namespace = input("\nThe modid of the mod to make compat with\n").strip()
    
    n = int(input("Enter number of pieces per village: "))
    piece_names = []
    for i in range(0, n):
        piece_names.append(input("\nName of the nbt file\n").strip())

    structure_type = "village"
    structure_type_plural = structure_type if structure_type.endswith('s') else ((structure_type[:-1] + 'ies') if structure_type.endswith('y') else (structure_type + 's'));
    structure_variants = {
        "badlands": "minecraft:empty",
        "birch": "repurposed_structures:villages/birch/mossify",
        "crimson": "repurposed_structures:villages/crimson/randomizer",
        "dark_forest": "repurposed_structures:villages/dark_forest/mossify",
        "giant_taiga": "repurposed_structures:villages/giant_taiga/mossify",
        "jungle": "repurposed_structures:villages/jungle/mossify",
        "mountains": "repurposed_structures:villages/mountains/mossify",
        "mushroom": "repurposed_structures:villages/mushroom/general_randomizer",
        "oak": "repurposed_structures:villages/oak/mossify",
        "swamp": "repurposed_structures:villages/swamp/mossify",
        "warped": "repurposed_structures:villages/warped/randomizer"
    }

    folderName = f"{datapack_location}\\Repurposed_Structures-{mod_name.replace(' ', '_')}"
    data_location = f"{folderName}\\data"

    #-------------------------------------------------------------------------------------------
    
    createFile(
            os.path.join('template', 'pack.mcmeta'),
            os.path.join(folderName, 'pack.mcmeta'),
            [mod_name])
    
    for variant in structure_variants:
        path = os.path.join(os.path.join('template', 'piece_count.json'))
        with open(path, 'r+') as file:
            jsonData = json.load(file)
            for piece_name in piece_names:
                jsonData['pieces_spawn_counts'].append({
                    "nbt_piece_name" : f"{mod_namespace}:villages/{variant}/{piece_name}",
                    "never_spawn_more_than_this_many": 1
                })
            path = os.path.join(datapack_location, os.path.join(data_location, 'repurposed_structures', 'rs_pieces_spawn_counts_additions', f'{structure_type}_{variant}.json'))
            if not exists(path):
                os.makedirs(os.path.dirname(path), exist_ok=True)
                with open(path, 'w') as outputFile:
                    outputFile.write(json.dumps(jsonData, indent=2))
                        

        path = os.path.join(os.path.join('template', 'template_pool.json'))
        with open(path, 'r+') as file:
            jsonData = json.load(file)
            jsonData['name'] = f"repurposed_structures:villages/{variant}/houses"
            for piece_name in piece_names:
                jsonData['elements'].append({
                    "weight": 6,
                    "element": {
                        "location": f"{mod_namespace}:villages/{variant}/{piece_name}",
                        "processors": f"{structure_variants[variant]}",
                        "projection": "rigid",
                        "element_type": "minecraft:legacy_single_pool_element"
                    }
                })
            path = os.path.join(datapack_location, os.path.join(data_location, 'repurposed_structures', 'pool_additions', 'villages', variant, 'houses.json'))
            if not exists(path):
                os.makedirs(os.path.dirname(path), exist_ok=True)
                with open(path, 'w') as outputFile:
                    outputFile.write(json.dumps(jsonData, indent=2))
                        
        os.makedirs(os.path.dirname(os.path.join(data_location, mod_namespace, "structures", "villages", variant, "")), exist_ok=True)


    print('\n\nFINISHED!')
    print('\nRESTARTING!\n\n')
