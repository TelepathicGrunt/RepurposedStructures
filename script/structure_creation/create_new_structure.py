from pathlib import Path
import collections.abc
import os
import sys
import random
import re

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
    biome_spacing = input("\nbiome spacing\n")
    structure_spacing = input("\navoid structure spacing\n")

    avoid_tags = ""
    avoid_tag = input("\nEnter structure tags to avoid. Type 'exit' to quit\n")
    while(avoid_tag != 'exit'): 
        avoid_tags = avoid_tags + ", RSStructureTagMap.STRUCTURE_TAGS." + avoid_tags.upper().strip()
        avoid_tag = input()
    avoid_tags = avoid_tags[2:]

    generation_step = input("\ngeneration step\n").upper().strip()

    config_category = input("\nconfig category\n")
    config_subcategory = input("\nconfig subcategory\n")

    config_modded_biome_entry = "add" + structure_registry_name.replace("_", " ").title().replace(" ", "") +"ToModdedBiomes"
    config_modded_biome_comment = input("\nconfig modded biome comment\n")

    config_spawnrate_entry = structure_registry_name[0].lower() + structure_registry_name.replace("_", " ").title().replace(" ", "")[1:] +"MaxChunkDistance"
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

    advancement_title = input("\nadvancement title\n")
    advancement_description = input("\nadvancement description\n")
    advancement_icon = input("\nadvancement icon\n")
    advancement_exp = str(input("\nadvancement exp\n"))
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
        file_content = file.read().replace("$1", structure_variable_name).replace("$2", start_pool).replace("$3", structure_size)  \
                                .replace("$4", biome_spacing).replace("$5", structure_spacing) \
                                .replace("$6", ("new HashSet<>()", "Stream.of("+avoid_tags+").collect(Collectors.toSet())")[bool(avoid_tags)])
    with open(os.path.join('code', 'fabric', structure_registry_name+'_structure_init.txt'), "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            insertLine(os.path.join(fabric_src, 'main','java','com','telepathicgrunt','repurposedstructures','modinit','RSStructures.java'), \
                "regexpos1", file_content)

    with open(os.path.join('template', 'fabric_structure_registration.txt'), "r") as file:
        file_content = file.read().replace("$1", structure_registry_name).replace("$2", structure_variable_name).replace("$3", generation_step)  \
                                .replace("$4", config_category).replace("$5", config_subcategory).replace("$6", config_spawnrate_entry) \
                                .replace("$7", spacing_seed).replace("$8", ("", ".adjustsSurface()")[adjusts_surface == 'y'])
    with open(os.path.join('code', 'fabric', structure_registry_name+'_structure_registration.txt'), "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            insertLine(os.path.join(fabric_src, 'main','java','com','telepathicgrunt','repurposedstructures','modinit','RSStructures.java'), \
                "regexpos2", file_content)

    with open(os.path.join('template', 'fabric_configured_structure_init.txt'), "r") as file:
        file_content = file.read().replace("$1", structure_variable_name)
    with open(os.path.join('code', 'fabric', structure_registry_name+'_configured_structure_init.txt'), "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            insertLine(os.path.join(fabric_src, 'main','java','com','telepathicgrunt','repurposedstructures','modinit','RSConfiguredStructures.java'), \
                "regexpos1", file_content)

    with open(os.path.join('template', 'fabric_configured_structure_registration.txt'), "r") as file:
        file_content = file.read().replace("$1", structure_registry_name).replace("$2", structure_variable_name)
    with open(os.path.join('code', 'fabric', structure_registry_name+'_configured_structure_registration.txt'), "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            insertLine(os.path.join(fabric_src, 'main','java','com','telepathicgrunt','repurposedstructures','modinit','RSConfiguredStructures.java'), \
                "regexpos2", file_content)

    with open(os.path.join('template', 'tags.txt'), "r") as file:
        file_content = file.read().replace("$1", structure_variable_name).replace("$2", innate_tags)
    with open(os.path.join('code', 'fabric', structure_registry_name+'_tags.txt'), "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            insertLine(os.path.join(fabric_src, 'main','java','com','telepathicgrunt','repurposedstructures','modinit','RSStructureTagMap.java'), \
                "regexpos1", file_content)


    with open(os.path.join('template', 'fabric_biome_spawn.txt'), "r") as file:
        file_content = file.read().replace("$1", structure_registry_name).replace("$2", config_subcategory).replace("$3", config_category) \
                                .replace("$4", config_spawnrate_entry).replace("$5", config_modded_biome_entry).replace("$6", structure_variable_name)
    with open(os.path.join('code', 'fabric', structure_registry_name+'_biome_spawn.txt'), "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            insertLine(os.path.join(fabric_src, 'main','java','com','telepathicgrunt','repurposedstructures','RSAddFeaturesAndStructures.java'), \
                "regexpos1", file_content)

    with open(os.path.join('template', 'fabric_config.txt'), "r") as file:
        file_content = file.read().replace("$1", bend(50, config_modded_biome_comment)).replace("$2", config_modded_biome_entry) \
                                .replace("$3", bend(50, config_spawnrate_comment)).replace("$4", config_spawnrate_entry).replace("$5", spacing_default_value)
    with open(os.path.join('code', 'fabric', structure_registry_name+'_config.txt'), "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            directory = os.path.join(fabric_src, 'main','java','com','telepathicgrunt','repurposedstructures','configs')
            for filename in os.listdir(directory):
                with open(os.path.join(directory, filename), 'r+') as f:
                    if "regexpos1" in f.read():
                        insertLine(os.path.join(directory, filename), \
                            "regexpos1", file_content[:5])
                    if "regexpos2" in f.read():
                        insertLine(os.path.join(directory, filename), \
                            "regexpos2", file_content[5:])


    with open(os.path.join('template', 'fabric_en_us_translations.json'), "r") as file:
        file_content = file.read().replace("$1", structure_registry_name).replace("$2", advancement_title).replace("$3", advancement_description) \
                                .replace("$4", config_category).replace("$5", config_subcategory).replace("$6", config_spawnrate_entry) \
                                .replace("$7", config_modded_biome_entry).replace("$9", config_spawnrate_comment).replace("$B", config_modded_biome_comment) \
                                .replace("$8", config_spawnrate_entry[0].upper()+re.sub('([A-Z])', r' \1', config_spawnrate_entry)[1:]) \
                                .replace("$A", config_modded_biome_entry[0].upper()+re.sub('([A-Z])', r' \1', config_modded_biome_entry)[1:])
    with open(os.path.join('code', 'fabric', structure_registry_name+'_en_us_translations.json'), "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            insertLine(os.path.join(fabric_src, 'main','resources','assets','repurposed_structures','lang','en_us.json'), \
                "}", ",\n" + file_content)

    raw_output += "\n\n--------------FORGE-------------"

   
    with open(os.path.join('template', 'forge_structure_init.txt'), "r") as file:
        file_content = file.read().replace("$1", structure_variable_name).replace("$2", structure_registry_name).replace("$3", start_pool) \
                                .replace("$4", structure_size).replace("$5", biome_spacing).replace("$6", structure_spacing) \
                                .replace("$7", ("new HashSet<>()", "Stream.of("+avoid_tags+").collect(Collectors.toSet())")[bool(avoid_tags)])
    with open(os.path.join('code', 'forge', structure_registry_name+'_structure_init.txt'), "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            insertLine(os.path.join(forge_src, 'main','java','com','telepathicgrunt','repurposedstructures','modinit','RSStructures.java'), \
                "regexpos1", file_content)

    with open(os.path.join('template', 'forge_structure_registration.txt'), "r") as file:
        file_content = file.read().replace("$1", ("addToStructureMaps", "addToTerraformingAndStructureMaps")[adjusts_surface == 'y']) \
                                .replace("$2", structure_registry_name).replace("$3", structure_variable_name).replace("$4", generation_step)  \
                                .replace("$5", config_category).replace("$6", config_spawnrate_entry).replace("$7", spacing_seed)
    with open(os.path.join('code', 'forge', structure_registry_name+'_structure_registration.txt'), "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            insertLine(os.path.join(forge_src, 'main','java','com','telepathicgrunt','repurposedstructures','modinit','RSStructures.java'), \
                "regexpos2", file_content)

    with open(os.path.join('template', 'forge_configured_structure_init.txt'), "r") as file:
        file_content = file.read().replace("$1", structure_variable_name)
    with open(os.path.join('code', 'forge', structure_registry_name+'_configured_structure_init.txt'), "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            insertLine(os.path.join(forge_src, 'main','java','com','telepathicgrunt','repurposedstructures','modinit','RSConfiguredStructures.java'), \
                "regexpos1", file_content)

    with open(os.path.join('template', 'forge_configured_structure_registration.txt'), "r") as file:
        file_content = file.read().replace("$1", structure_registry_name).replace("$2", structure_variable_name)
    with open(os.path.join('code', 'forge', structure_registry_name+'_configured_structure_registration.txt'), "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            insertLine(os.path.join(forge_src, 'main','java','com','telepathicgrunt','repurposedstructures','modinit','RSConfiguredStructures.java'), \
                "regexpos2", file_content)

    with open(os.path.join('template', 'tags.txt'), "r") as file:
        file_content = file.read().replace("$1", structure_variable_name+".get()").replace("$2", innate_tags)
    with open(os.path.join('code', 'forge', structure_registry_name+'_tags.txt'), "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            insertLine(os.path.join(forge_src, 'main','java','com','telepathicgrunt','repurposedstructures','modinit','RSStructureTagMap.java'), \
                "regexpos1", file_content)

    with open(os.path.join('template', 'forge_biome_spawn.txt'), "r") as file:
        file_content = file.read().replace("$1", config_spawnrate_entry).replace("$2", config_category) \
                                .replace("$3", config_modded_biome_entry).replace("$4", structure_variable_name)
    with open(os.path.join('code', 'forge', structure_registry_name+'_biome_spawn.txt'), "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            insertLine(os.path.join(forge_src, 'main','java','com','telepathicgrunt','repurposedstructures','RSAddFeaturesAndStructures.java'), \
                "regexpos1", file_content)

    with open(os.path.join('template', 'forge_config.txt'), "r") as file:
        file_content = file.read().replace("$1", config_spawnrate_entry).replace("$2", config_spawnrate_entry.lower()) \
                                .replace("$3", bend(50, config_modded_biome_comment)).replace("$4", config_subcategory).replace("$5", spacing_default_value) \
                                .replace("$6", config_modded_biome_entry).replace("$7", config_modded_biome_entry.lower()) \
                                .replace("$8", bend(50, config_modded_biome_comment))
    with open(os.path.join('code', 'forge', structure_registry_name+'_config.txt'), "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            directory = os.path.join(forge_src, 'main','java','com','telepathicgrunt','repurposedstructures','configs')
            for filename in os.listdir(directory):
                with open(os.path.join(directory, filename), 'r+') as f:
                    if "regexpos1" in f.read():
                        insertLine(os.path.join(directory, filename), \
                            "regexpos1", file_content[:2])
                    if "regexpos2" in f.read():
                        insertLine(os.path.join(directory, filename), \
                            "regexpos2", file_content[2:])

    with open(os.path.join('template', 'forge_en_us_translations.json'), "r") as file:
        file_content = file.read().replace("$1", structure_registry_name).replace("$2", advancement_title).replace("$3", advancement_description)
    with open(os.path.join('code', 'forge', structure_registry_name+'_en_us_translations.json'), "w") as file:
        raw_output += "\n\n" + file_content
        file.write(file_content)
        if bool(inject_into_code):
            insertLine(os.path.join(forge_src, 'main','resources','assets','repurposed_structures','lang','en_us.json'), \
                "}", ",\n" + file_content)


    #  --------------ADVANCEMENTS--------------

    #[:-1]

    with open(os.path.join('template', 'translated_advancement.json'), "r") as file:
        file_content = file.read().replace("$1", structure_registry_name).replace("$2", advancement_icon).replace("$3", advancement_title) \
                                .replace("$4", advancement_description).replace("$5", advancement_exp).replace("$6", config_subcategory)
    
    filename = os.path.join('advancements', 'Repurposed_Structures-English_Translated_Advancements', "data", "repurposed_structures", "advancements", config_subcategory, structure_registry_name+'.json')
    os.makedirs(os.path.dirname(filename), exist_ok=True)
    with open(filename, "w") as file:
        file.write(file_content)

    with open(os.path.join('template', 'hidden_advancement.json'), "r") as file:
        file_content = file.read().replace("$1", structure_registry_name).replace("$2", advancement_icon).replace("$3", advancement_title) \
                                .replace("$4", advancement_description).replace("$5", advancement_exp).replace("$6", config_subcategory)
    filename = os.path.join('advancements', 'Repurposed_Structures-Hidden_Advancements', "data", "repurposed_structures", "advancements", config_subcategory, structure_registry_name+'.json')
    os.makedirs(os.path.dirname(filename), exist_ok=True)
    with open(filename, "w") as file:
        file.write(file_content)


    with open(os.path.join('template', 'translation_advancement.json'), "r") as file:
        file_content = file.read().replace("$1", structure_registry_name).replace("$2", advancement_icon).replace("$3", advancement_title) \
                                .replace("$4", advancement_description).replace("$5", advancement_exp).replace("$6", config_subcategory)
    filename = os.path.join('advancements', 'Repurposed_Structures-Translation_Advancements', "data", "repurposed_structures", "advancements", config_subcategory, structure_registry_name+'.json')
    os.makedirs(os.path.dirname(filename), exist_ok=True)
    with open(filename, "w") as file:
        file.write(file_content)


    with open(os.path.join('template', 'disabled_advancement.json'), "r") as file:
        file_content = file.read().replace("$1", structure_registry_name).replace("$2", advancement_icon).replace("$3", advancement_title) \
                                .replace("$4", advancement_description).replace("$5", advancement_exp).replace("$6", config_subcategory)
    filename = os.path.join('advancements', 'Repurposed_Structures-Disabled_Advancements', "data", "repurposed_structures", "advancements", config_subcategory, structure_registry_name+'.json')
    os.makedirs(os.path.dirname(filename), exist_ok=True)
    with open(filename, "w") as file:
        file.write(file_content)



    with open(os.path.join('code', 'raw_output', structure_registry_name+'.txt'), "w") as file:
        file.write(raw_output)

    print("\n\nFINISHED!")
    print("\nRESTARTING!\n\n")
