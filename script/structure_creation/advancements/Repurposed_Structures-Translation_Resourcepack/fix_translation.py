import json
from pathlib import Path
import collections.abc
import os
import sys
import random
import re

translations = ""
with open(os.path.join('assets', 'repurposed_structures', 'lang', 'en_us.json'), "r") as file:
        translations = json.load(file)
        
directory = os.path.dirname(os.path.dirname(__file__))
for entry in os.scandir(directory):
    if (entry.path.endswith(".json") and entry.is_file()):
        newText = ""
        with open(entry) as f:
            fileText = f.read()
            for key in translations.keys():
                newText = fileText.replace(key, translations[key])

        with open(entry, "w") as f:
            f.write(newText)
    