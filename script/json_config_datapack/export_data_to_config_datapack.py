from os.path import exists
import os
import shutil
import json

fabric_resources = "C:\\Users\\MSI Laptop\\Documents\\ModWorkspace\\RepurposedStructures-Fabric\\src\\main\\resources"
compat_datapack = "C:\\Users\\MSI Laptop\\Documents\\ModWorkspace\\.Extra\\Backroom\\repurposed_structures\\datapacks\\Repurposed_Structures-Config_Datapack"

# Source: https://thispointer.com/python-how-to-get-list-of-files-in-directory-and-sub-directories/
def getListOfFiles(dirName):
    listOfFile = os.listdir(dirName)
    allFiles = list()
    for entry in listOfFile:
        fullPath = os.path.join(dirName, entry)
        if os.path.isdir(fullPath):
            allFiles = allFiles + getListOfFiles(fullPath)
        else:
            allFiles.append(fullPath)
    return allFiles
    
#--------------------------------------------------------------------------------------------

restart = True
while restart:
    mc_version = "1.18.2"
    version = input("\ncompat_datapack_version\n").strip()

    #-------------------------------------------------------------------------------------------
    
    shutil.rmtree(f"{compat_datapack}\\Repurposed_Structures-Config_Datapack", ignore_errors=True) 

    listOfFiles = getListOfFiles(fabric_resources)
    listOfFiles = [x for x in listOfFiles if not any(substring in x for substring in ["data\\repurposed_structures\\structures", ".png", "fabric.mod.json", "repurposed_structures.mixins.json", "repurposed_structures.accesswidener"])]
    for srcFile in listOfFiles:
        targetFile = srcFile.replace(fabric_resources, f"{compat_datapack}\\Repurposed_Structures-Config_Datapack")
        os.makedirs(os.path.dirname(targetFile), exist_ok=True)
        shutil.copyfile(srcFile, targetFile)

    path = os.path.join(f"{compat_datapack}\\Repurposed_Structures-Config_Datapack", "pack.mcmeta")
    shutil.copyfile("pack.mcmeta", path)
    with open(path, 'r+') as file:
        jsonData = json.load(file)
        jsonData['pack']['description'] = f"Repurposed Structures - Config Datapack {mc_version} v{version}"
        file.seek(0)
        file.write(json.dumps(jsonData, indent=2))
        file.truncate()

    shutil.make_archive(f"{compat_datapack}\\Repurposed_Structures-Config_Datapack-{mc_version.replace('.', '_')}-v{version}", 'zip', f"{compat_datapack}\\Repurposed_Structures-Config_Datapack")

    print('\n\nFINISHED!')
    print('\nRESTARTING!\n\n')
