import os
import errno

conversion_dict = {
    "minecraft:moss_carpet": "cavesandcliffs:moss_carpet"
}

#-------------------------------------------------------------------------------------------

def string_replacer(stringLine):
    for key, replacement in conversion_dict.items():
        if key in stringLine:
            return stringLine.replace(key, replacement)
    return stringLine

for (subdir, dirs, files) in os.walk("toconvert", topdown=True):
    for file in files:
        oldDirectory = subdir + os.sep
        oldFilepath = oldDirectory + file
        newDirectory = oldDirectory.replace("toconvert", "converted")
        newFilepath = newDirectory + file
        
        if not os.path.exists(os.path.dirname(newFilepath)):
            try:
                os.makedirs(os.path.dirname(newFilepath))
            except OSError as exc: # Guard against race condition
                if exc.errno != errno.EEXIST:
                    raise

        fin = open(oldFilepath, "rt")
        fout = open(newFilepath, "wt")
        for line in fin:
            fout.write(string_replacer(line))

        fin.close()
        fout.close()



print("FINISHED!")
input()