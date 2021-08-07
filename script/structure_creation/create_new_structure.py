from pathlib import Path
import os
import sys
import random
import json

# Helper method from https://stackoverflow.com/a/49001704
def bend(w, s):
    s = s.split(" ") #creates list of all the words (any sequence between characters)
    lst = filter(None, s) # removes the repeated spaces from list
    new_lst = [""]
    i = 0
    for word in lst:
        line = new_lst[i] + " " + word #possible line
        if(new_lst[i] == ""): #first time is different
            line = "\""+word
        if(len(word) > w): #splits words that are too large
            while(len(word) > w):
                new_lst.append(word[:w])
                i += 1
                word = word[w:]
            i += 1
            new_lst.append(word)
        elif(len(line) > w):
           new_lst.append(word) #line length reached, start new line
           i += 1        
        else:
            new_lst[i] = line
    return "\"\n                + \"\\n".join(new_lst) + "\"" #insert new line characters

# how to insert before a line in a file: https://stackoverflow.com/a/44338469
def insertLine(filepath, string_match, string_content):
    contents = []
    with open(filepath, "r") as codefile:
        contents = codefile.readlines()
        if string_match in contents[-1]:  # Handle last line to prevent IndexError
            try:
                contents.insert(len(contents)-1, string_content)
            except:
                e = sys.exc_info()[0]
                print("Failed to append to end of file:  %s" % e )
        else:
            for index, line in enumerate(contents):
                if string_match in line and string_content not in contents[index + 1]:
                    contents.insert(index, string_content + "\n")
                    break
    with open(filepath, "w") as codefile:
        codefile.writelines(contents)


#--------------------------------------------------------------------------------------------

restart = True
while restart:
    
    structure_registry_name = input("\nregistry name of structure\n").strip()
    structure_variable_name = structure_registry_name.upper().strip()

    structure_size = input("\nsize of structure\n")
    start_pool = input("\nstart pool path\n").strip()

    generation_step = input("\ngeneration step\n").upper().strip()

    config_category = input("\nconfig category\n")

    config_spawnrate_entry = structure_registry_name[0].lower() + structure_registry_name.replace("_", " ").title().replace(" ", "")[1:] +"AverageChunkDistance"
    config_spawnrate_comment = input("\nconfig spawnrate comment\n")

    spacing_default_value = input("\nspacing default value\n")
    spacing_seed = str(random.getrandbits(31))
    adjusts_surface = input("\nadjusts surface (y/n)\n")

    innate_tags = ""
    innate_tag = input("\nEnter innate tags. Type 'exit' to quit\n")
    while(innate_tag != 'exit'): 
        innate_tags = innate_tags + ", STRUCTURE_TAGS." + innate_tag.upper().strip()
        innate_tag = input()
    innate_tags = innate_tags[2:]

    advancement_file = input("\nadvancement file name\n")
    inject_into_code = input("\nInject generated code into codebase directly? (y/n)\n")
    fabric_src = ""
    forge_src = ""
    if bool(inject_into_code):
        fabric_src = str(input("\nFabric src filepath\n"))
        forge_src = str(input("\Forge src filepath\n"))

    #-------------------------------------------------------------------------------------------
    file_content = ""
    raw_output = "--------------FABRIC-------------"

    with open(os.path.join('template', 'fabric_structure_init.txt'), "r") as file:
        file_content = file.read().replace("$1", structure_variable_name).replace("$2", start_pool).replace("$3", structure_size)
    path = os.path.join('code', 'fabric', structure_registry_name+'_structure_init.txt')
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            insertLine(os.path.join(fabric_src, 'main','java','com','telepathicgrunt','repurposedstructures','modinit','RSStructures.java'), \
                "regexpos1", file_content)

    with open(os.path.join('template', 'fabric_structure_registration.txt'), "r") as file:
        file_content = file.read().replace("$1", structure_registry_name).replace("$2", structure_variable_name).replace("$3", generation_step)  \
                                .replace("$4", config_category).replace("$5", config_spawnrate_entry) \
                                .replace("$6", spacing_seed).replace("$7", ("", ".adjustsSurface()")[adjusts_surface == 'y'])
    path = os.path.join('code', 'fabric', structure_registry_name+'_structure_registration.txt')
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            insertLine(os.path.join(fabric_src, 'main','java','com','telepathicgrunt','repurposedstructures','modinit','RSStructures.java'), \
                "regexpos2", file_content)

    with open(os.path.join('template', 'fabric_configured_structure_init.txt'), "r") as file:
        file_content = file.read().replace("$1", structure_variable_name)
    path = os.path.join('code', 'fabric', structure_registry_name+'_configured_structure_init.txt')
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            insertLine(os.path.join(fabric_src, 'main','java','com','telepathicgrunt','repurposedstructures','modinit','RSConfiguredStructures.java'), \
                "regexpos1", file_content)

    with open(os.path.join('template', 'fabric_configured_structure_registration.txt'), "r") as file:
        file_content = file.read().replace("$1", structure_registry_name).replace("$2", structure_variable_name)
    path = os.path.join('code', 'fabric', structure_registry_name+'_configured_structure_registration.txt')
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            insertLine(os.path.join(fabric_src, 'main','java','com','telepathicgrunt','repurposedstructures','modinit','RSConfiguredStructures.java'), \
                "regexpos2", file_content)

    with open(os.path.join('template', 'tags.txt'), "r") as file:
        file_content = file.read().replace("$1", structure_variable_name).replace("$2", innate_tags)
    path = os.path.join('code', 'fabric', structure_registry_name+'_tags.txt')
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            insertLine(os.path.join(fabric_src, 'main','java','com','telepathicgrunt','repurposedstructures','modinit','RSStructureTagMap.java'), \
                "regexpos1", file_content)


    with open(os.path.join('template', 'fabric_biome_spawn.txt'), "r") as file:
        file_content = file.read().replace("$1", structure_registry_name).replace("$2", config_category).replace("$3", config_spawnrate_entry)
    path = os.path.join('code', 'fabric', structure_registry_name+'_biome_spawn.txt')
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            directory = os.path.join(fabric_src, 'main','java','com','telepathicgrunt','repurposedstructures','biomeinjection')
            for filename in os.listdir(directory):
                with open(os.path.join(directory, filename), 'r+') as f:
                    tempRead = f.read()
                    if "regexpos1" in tempRead:
                        f.seek(0)
                        insertLine(os.path.join(directory, filename), \
                            "regexpos1", file_content)

    with open(os.path.join('template', 'fabric_config.txt'), "r") as file:
        file_content = file.read().replace("$1", "\"" + config_spawnrate_comment + "\"").replace("$2", config_spawnrate_entry).replace("$3", spacing_default_value)
    path = os.path.join('code', 'fabric', structure_registry_name+'_config.txt')
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            directory = os.path.join(fabric_src, 'main','java','com','telepathicgrunt','repurposedstructures','configs')
            for filename in os.listdir(directory):
                with open(os.path.join(directory, filename), 'r+') as f:
                    tempRead = f.read()
                    if "regexpos1" in tempRead:
                        f.seek(0)
                        insertLine(os.path.join(directory, filename), \
                            "regexpos1", file_content)


    raw_output += "\n\n--------------FORGE-------------"

   
    with open(os.path.join('template', 'forge_structure_init.txt'), "r") as file:
        file_content = file.read().replace("$1", structure_variable_name).replace("$2", structure_registry_name).replace("$3", start_pool).replace("$4", structure_size)
    path = os.path.join('code', 'forge', structure_registry_name+'_structure_init.txt')
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            insertLine(os.path.join(forge_src, 'main','java','com','telepathicgrunt','repurposedstructures','modinit','RSStructures.java'), \
                "regexpos1", file_content)

    with open(os.path.join('template', 'forge_structure_registration.txt'), "r") as file:
        file_content = file.read().replace("$1", ("addToStructureMaps", "addToTerraformingAndStructureMaps")[adjusts_surface == 'y']) \
                                .replace("$2", structure_registry_name).replace("$3", structure_variable_name).replace("$4", generation_step)  \
                                .replace("$5", config_category).replace("$6", config_spawnrate_entry).replace("$7", spacing_seed)
    path = os.path.join('code', 'forge', structure_registry_name+'_structure_registration.txt')
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            insertLine(os.path.join(forge_src, 'main','java','com','telepathicgrunt','repurposedstructures','modinit','RSStructures.java'), \
                "regexpos2", file_content)

    with open(os.path.join('template', 'forge_configured_structure_init.txt'), "r") as file:
        file_content = file.read().replace("$1", structure_variable_name)
    path = os.path.join('code', 'forge', structure_registry_name+'_configured_structure_init.txt')
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            insertLine(os.path.join(forge_src, 'main','java','com','telepathicgrunt','repurposedstructures','modinit','RSConfiguredStructures.java'), \
                "regexpos1", file_content)

    with open(os.path.join('template', 'forge_configured_structure_registration.txt'), "r") as file:
        file_content = file.read().replace("$1", structure_registry_name).replace("$2", structure_variable_name)
    path = os.path.join('code', 'forge', structure_registry_name+'_configured_structure_registration.txt')
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            insertLine(os.path.join(forge_src, 'main','java','com','telepathicgrunt','repurposedstructures','modinit','RSConfiguredStructures.java'), \
                "regexpos2", file_content)

    with open(os.path.join('template', 'tags.txt'), "r") as file:
        file_content = file.read().replace("$1", structure_variable_name+".get()").replace("$2", innate_tags)
    path = os.path.join('code', 'forge', structure_registry_name+'_tags.txt')
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            insertLine(os.path.join(forge_src, 'main','java','com','telepathicgrunt','repurposedstructures','modinit','RSStructureTagMap.java'), \
                "regexpos1", file_content)

    with open(os.path.join('template', 'forge_biome_spawn.txt'), "r") as file:
        file_content = file.read().replace("$1", config_category).replace("$2", structure_variable_name)
    path = os.path.join('code', 'forge', structure_registry_name+'_biome_spawn.txt')
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            directory = os.path.join(forge_src, 'main','java','com','telepathicgrunt','repurposedstructures','biomeinjection')
            for filename in os.listdir(directory):
                with open(os.path.join(directory, filename), 'r+') as f:
                    tempRead = f.read()
                    if "regexpos1" in tempRead:
                        f.seek(0)
                        insertLine(os.path.join(directory, filename), \
                            "regexpos1", file_content)

    with open(os.path.join('template', 'forge_config.txt'), "r") as file:
        file_content = file.read().replace("$1", config_spawnrate_entry).replace("$2", config_spawnrate_entry.lower()) \
                                .replace("$3", config_spawnrate_comment).replace("$4", structure_variable_name).replace("$5", spacing_default_value)
    path = os.path.join('code', 'forge', structure_registry_name+'_config.txt')
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            directory = os.path.join(forge_src, 'main','java','com','telepathicgrunt','repurposedstructures','configs')
            for filename in os.listdir(directory):
                if os.path.isfile(os.path.join(directory, filename)):
                    pass
                with open(os.path.join(directory, filename), 'r+') as f:
                    tempRead = f.read()
                    if "regexpos1" in tempRead:
                        f.seek(0)
                        groups = file_content.split('(;)')
                        result = ''.join(groups[:1])
                        insertLine(os.path.join(directory, filename), \
                            "regexpos1", result)
                    if "regexpos2" in tempRead:
                        f.seek(0)
                        groups = file_content.split('(;)')
                        result = ''.join(groups[1:])
                        insertLine(os.path.join(directory, filename), \
                            "regexpos2", result[1:])


    #  --------------ADVANCEMENTS--------------

    path = os.path.join(fabric_src, 'main','resources','data','repurposed_structures','advancements', advancement_file)
    with open(path, "r+") as file:
        jsonData = json.load(file)
        jsonData["criteria"]["in_"+structure_registry_name] = { 
            "trigger": "minecraft:location",
            "conditions": {
                "feature": "repurposed_structures:"+structure_registry_name
            } 
        }
        jsonData["requirements"].append(["in_"+structure_registry_name])
        file.seek(0)
        file.write(jsonData, indent=4, sort_keys=True)
        file.truncate()

    path = os.path.join(forge_src, 'main','resources','data','repurposed_structures','advancements', advancement_file)
    with open(path, "r+") as file:
        jsonData = json.load(file)
        jsonData["criteria"]["in_"+structure_registry_name] = { 
            "trigger": "minecraft:location",
            "conditions": {
                "feature": "repurposed_structures:"+structure_registry_name
            } 
        }
        jsonData["requirements"].append(["in_"+structure_registry_name])
        file.seek(0)
        file.write(jsonData, indent=4, sort_keys=True)
        file.truncate()


    path = os.path.join('code', 'raw_output', structure_registry_name+'.txt')
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w") as file:
        file.write(raw_output)

    print("\n\nFINISHED!")
    print("\nRESTARTING!\n\n")
