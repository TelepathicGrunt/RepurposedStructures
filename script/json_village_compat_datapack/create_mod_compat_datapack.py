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
        

#--------------------------------------------------------------------------------------------

restart = True
while restart:
    
    mod_name = input("\nThe name of the mod to make compat with\n").strip()
    mod_namespace = input("\nThe modid of the mod to make compat with\n").strip()
    piece_name = input("\nName of the nbt file\n").strip()
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
        createFile(
            os.path.join('template', 'piece_count.json'),
            os.path.join(data_location, 'repurposed_structures', 'rs_pieces_spawn_counts_additions', f'{structure_type}_{variant}.json'),
            [mod_namespace, variant, piece_name])

        createFile(
            os.path.join('template', 'template_pool.json'),
            os.path.join(data_location, 'repurposed_structures', 'pool_additions', 'villages', variant, 'houses.json'),
            [f'villages/{variant}/houses', mod_namespace, variant, piece_name, structure_variants[variant]])

        os.makedirs(os.path.dirname(os.path.join(data_location, mod_namespace, "structures", "villages", variant, "")), exist_ok=True)


    print('\n\nFINISHED!')
    print('\nRESTARTING!\n\n')
