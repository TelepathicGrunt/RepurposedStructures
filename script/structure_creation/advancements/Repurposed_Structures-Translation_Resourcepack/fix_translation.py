import json
from pathlib import Path
import collections.abc
import os
import sys
import random
import re

translations = []
with open(os.path.join('assets', 'repurposed_structures', 'lang', 'en_us.json'), "r") as file:
        translations = json.load(file)
        
directory = os.path.dirname(os.path.dirname(__file__))
for subdir, dirs, files in os.walk(directory):
    for entry in files:
        if entry.endswith(".json"):
            newText = ""
            with open(os.path.join(subdir, entry), encoding="utf8") as f:
                newText = f.read()
                for key in translations.keys():
                    if "advancements" in key:
                        newText = newText.replace(key, translations[key])

            with open(os.path.join(subdir, entry), "w", encoding="utf8") as f:
                f.write(newText)
    