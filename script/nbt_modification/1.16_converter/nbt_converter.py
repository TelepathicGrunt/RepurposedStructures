import python_nbt.nbt as nbt
from pathlib import Path
import collections.abc
import os
import sys

# https://pypi.org/project/Python-NBT/

'''
# birch
conversion_exact_dict = {
    "minecraft:village/plains":"repurposed_structures:village/birch",
    "minecraft:stripped_oak_log":"minecraft:stripped_birch_log",
    "minecraft:oak_log":"minecraft:stripped_birch_log",
    "minecraft:oak_leaves":"minecraft:birch_leaves",
    "minecraft:oak_planks":"minecraft:birch_planks",
    "minecraft:oak_door":"minecraft:birch_door",
    "minecraft:oak_stairs":"minecraft:birch_stairs",
    "minecraft:oak_slab":"minecraft:birch_slab",
    "minecraft:oak_pressure_plate":"minecraft:birch_pressure_plate",
    "minecraft:oak_fence":"minecraft:birch_fence",
    "minecraft:oak_trapdoor":"minecraft:birch_trapdoor",
    "minecraft:chest/village/village_plains_house":"repurposed_structures:chest/village/village_birch_house"
}
'''

'''
# dark forest
originalBiome = "plains"
newBiome = "dark_forest"
string_blacklist = []
conversion_partial_dict = {
    "minecraft:village/"+originalBiome:"repurposed_structures:village/"+newBiome
}
conversion_partial_dict["minecraft:oak"] = "minecraft:dark_oak"
conversion_partial_dict["minecraft:stripped_oak"] = "minecraft:stripped_dark_oak"
conversion_partial_dict["minecraft:cobblestone_stairs"] = "minecraft:granite_stairs"
conversion_exact_dict = {
    "minecraft:cobblestone":"minecraft:dark_oak_planks",
    "minecraft:cobblestone_slab":"minecraft:granite_slab",
    "minecraft:cobblestone_wall":"minecraft:granite_wall",
    "minecraft:white_terracotta":"minecraft:brown_terracotta",
    "minecraft:chest/village/village_plains_house":"repurposed_structures:chest/village/village_"+newBiome+"_house"
}
'''

'''
# jungle
originalBiome = "savanna"
newBiome = "jungle"
string_blacklist = []
conversion_partial_dict = {
    "minecraft:village/"+originalBiome:"repurposed_structures:village/"+newBiome
}


conversion_partial_dict["minecraft:acacia"] = "minecraft:jungle"
conversion_exact_dict = {
    "minecraft:chest/village/village_savanna_house":"repurposed_structures:chest/village/village_"+newBiome+"_house",
    "minecraft:village/plains/villagers":"minecraft:village/jungle/villagers",
    "minecraft:brown_wall_banner":"minecraft:yellow_wall_banner",
    "minecraft:orange_terracotta":"minecraft:yellow_terracotta",
    "minecraft:yellow_terracotta":"minecraft:stone_bricks",
    "minecraft:smooth_stone":"minecraft:stone_bricks",
    "minecraft:village/plains/zombie/villagers":"minecraft:village/jungle/zombie/villagers"
}
'''
'''
# Badlands
originalBiome = "badlands"
newBiome = "badlands"
string_blacklist = []
conversion_partial_dict = {
    "minecraft:village/"+originalBiome:"repurposed_structures:village/"+newBiome,
    "minecraft:oak":"minecraft:dark_oak",
    "minecraft:stripped_oak":"minecraft:stripped_dark_oak",
    "minecraft:acacia":"minecraft:oak",
    "minecraft:stripped_acacia":"minecraft:stripped_oak",
    "minecraft:spruce":"minecraft:dark_oak"
}
conversion_exact_dict = {
    "minecraft:chests/village/village_taiga_house":"repurposed_structures:chest/village/village_"+newBiome+"_house",
    "minecraft:chests/village/village_plains_house":"repurposed_structures:chest/village/village_"+newBiome+"_house",
    "minecraft:chests/village/village_desert_house":"repurposed_structures:chest/village/village_"+newBiome+"_house",
    "minecraft:cyan_terracotta":"minecraft:gray_terracotta",
    "minecraft:smooth_sandstone":"minecraft:orange_terracotta"
}
'''
'''
# Swamp

        planks = False
        snowyGrass = False
                if(key != "final_state" and entry.value == "minecraft:spruce_planks"):
                    planks = True
                if(key == "layers" and entry.value == "1"):
                    snowyGrass = True
        if planks:
            axisvalue = nbt.NBTTagString("y")
            woodname = nbt.NBTTagString("minecraft:oak_wood")
            propertytag = nbt.NBTTagCompound()
            propertytag['axis'] = axisvalue
            propertyenclosingtag = nbt.NBTTagCompound()
            nbt_list['Properties'] = propertytag
            nbt_list['Name'] = woodname
        if snowyGrass:
            axisvalue = nbt.NBTTagString("0")
            nbt_list['layers'] = axisvalue

originalBiome = "snowy"
newBiome = "swamp"
string_blacklist = []
conversion_partial_dict = {
    "minecraft:village/"+originalBiome:"repurposed_structures:village/"+newBiome,
    "minecraft:spruce" : "minecraft:oak",
    "minecraft:diorite" : "minecraft:andesite"
}
conversion_exact_dict = {
    "minecraft:chests/village/village_"+originalBiome+"_house":"repurposed_structures:chests/village/village_"+newBiome+"_house",
    "minecraft:spruce_slab" : "minecraft:brown_mushroom_block",
    "minecraft:stripped_spruce_log" : "minecraft:gray_terracotta",
    "minecraft:spruce_log" : "minecraft:gray_terracotta",
    "minecraft:oak_log" : "minecraft:gray_terracotta",
    "minecraft:packed_ice":"minecraft:gray_terracotta",
    "minecraft:blue_ice":"minecraft:brown_terracotta",
    "minecraft:snow_block":"minecraft:brown_terracotta",
    "minecraft:snow":"minecraft:grass_block",
    "minecraft:white_bed":"minecraft:brown_bed",
    "minecraft:blue_bed":"minecraft:brown_bed",
    "minecraft:red_bed":"minecraft:brown_bed",
    "minecraft:glass_pane":"minecraft:black_stained_glass_pane"
}          
'''
'''
# Mountains

originalBiome = "taiga"
newBiome = "mountains"
string_blacklist = ["minecraft:village/taiga/villagers"]
conversion_partial_dict = {
    "minecraft:village/"+originalBiome:"repurposed_structures:village/"+newBiome
}
conversion_exact_dict = {
    "minecraft:chests/village/village_"+originalBiome+"_house":"repurposed_structures:chests/village/village_"+newBiome+"_house",
    "minecraft:spruce_planks" : "minecraft:stone",
    "minecraft:spruce_fence" : "minecraft:stone_brick_wall",
    "minecraft:cobblestone_stairs" : "minecraft:stone_brick_stairs",
    "minecraft:cobblestone_wall" : "minecraft:stone_brick_wall",
    "minecraft:cobblestone" : "minecraft:stone_bricks",
    "minecraft:blue_orchid" : "minecraft:dandelion",
    "minecraft:pumpkin" : "minecraft:cobblestone",
    "minecraft:stripped_spruce_log" : "minecraft:cobblestone",
    "minecraft:pumpkin_stem" : "minecraft:wheat",
    "minecraft:white_bed":"minecraft:gray_bed",
    "minecraft:blue_bed":"minecraft:green_bed",
    "minecraft:red_bed":"minecraft:gray_bed",
    "minecraft:glass_pane":"minecraft:gray_stained_glass_pane"
}
'''
'''
# Giant Tree Taiga
originalBiome = "snowy"
newBiome = "giant_taiga"
string_blacklist = ["minecraft:village/taiga/villagers"]
conversion_partial_dict = {
    "minecraft:village/"+originalBiome:"repurposed_structures:village/"+newBiome
}
conversion_exact_dict = {
    "minecraft:chests/village/village_"+originalBiome+"_house":"repurposed_structures:chests/village/village_"+newBiome+"_house",
    "minecraft:cobblestone":"minecraft:stone",
    "minecraft:spruce_stairs":"minecraft:cobblestone_stairs",
    "minecraft:spruce_fence":"minecraft:cobblestone_wall",
    "minecraft:spruce_log":"minecraft:stripped_spruce_wood",
    "minecraft:stripped_spruce_log":"minecraft:cobblestone",
    "minecraft:stripped_spruce_wood":"minecraft:stone",
    "minecraft:snow":"minecraft:stone_pressure_plate",
    "minecraft:snow_block":"minecraft:cobblestone",
    "minecraft:blue_ice":"minecraft:mossy_cobblestone",
    "minecraft:packed_ice":"minecraft:cobblestone",
    "minecraft:ice":"minecraft:cobblestone"
}



//mansion

string_blacklist = ["minecraft:village/taiga/villagers"]
conversion_partial_dict = {
    
}
conversion_exact_dict = {
    "minecraft:polished_andesite" : "minecraft:smooth_sandstone",
    "minecraft:light_gray_wool" : "minecraft:light_gray_terracotta",
    "minecraft:coarse_dirt" : "minecraft:sand",
    "minecraft:cobblestone_wall" : "minecraft:stone_brick_wall",
    "minecraft:cobblestone" : "minecraft:stone_bricks",
    "minecraft:blue_orchid" : "minecraft:dandelion",
    "minecraft:pumpkin" : "minecraft:cobblestone",
    "minecraft:stripped_spruce_log" : "minecraft:cobblestone",
    "minecraft:pumpkin_stem" : "minecraft:wheat",
    "minecraft:white_bed":"minecraft:gray_bed",
    "minecraft:blue_bed":"minecraft:green_bed",
    "minecraft:red_bed":"minecraft:gray_bed",
    "minecraft:glass_pane":"minecraft:gray_stained_glass_pane"
}


# outposts

string_blacklist = []
conversion_partial_dict = {
    "minecraft:mossy_cobblestone" : "minecraft:blackstone",
    "minecraft:cobblestone" : "minecraft:blackstone",
    "minecraft:dark_oak" : "minecraft:crimson"
}
conversion_exact_dict = {
    "minecraft:mossy_cobblestone" : "minecraft:crimson_stem",
    "minecraft:dark_oak_log" : "minecraft:crimson_stem",
    "minecraft:white_wool" : "minecraft:polished_blackstone_bricks",
    "minecraft:birch_planks" : "minecraft:warped_planks",
    "minecraft:torch" : "minecraft:redstone_torch",
    "minecraft:crafting_table" : "minecraft:smithing_table"
}


#crimson

blockPalette = {""}

originalBiome = "plains"
newBiome = "crimson"
string_blacklist = []
conversion_partial_dict = {
    "minecraft:village/"+originalBiome:"repurposed_structures:village/"+newBiome,
    "villager" : "piglin",
    "villagers" : "piglins",
    "minecraft:cobblestone" : "minecraft:blackstone",
    "minecraft:oak" : "minecraft:crimson",
}
conversion_exact_dict = {
    "minecraft:village/"+originalBiome+"/villager":"repurposed_structures:village/"+newBiome+"/piglins",
    "minecraft:village/common/animals":"repurposed_structures:village/"+newBiome+"/mobs/hoglins",
    "minecraft:village/common/sheep":"repurposed_structures:village/"+newBiome+"/mobs/hoglins",
    "minecraft:village/common/cats":"repurposed_structures:village/"+newBiome+"/mobs/stiders",
    "minecraft:village/common/iron_golem":"repurposed_structures:village/"+newBiome+"/mobs/wither_skeletons",
    "minecraft:cobblestone":"minecraft:blackstone",
    "minecraft:cobblestone_slab":"minecraft:blackstone_slab",
    "minecraft:cobblestone_wall":"minecraft:blackstone",
    "minecraft:oak_log":"minecraft:crimson_hyphae",
    "minecraft:oak_planks":"minecraft:crimson_planks",
    "minecraft:oak_stairs":"minecraft:crimson_stairs",
    "minecraft:oak_slab":"minecraft:crimson_slab",
    "minecraft:stripped_oak_log":"minecraft:stripped_crimson_hyphae",
    "minecraft:stripped_oak_wood":"minecraft:basalt",
    "minecraft:oak_fence":"minecraft:crimson_fence",
    "minecraft:oak_fence_gate":"minecraft:crimson_fence_gate",
    "minecraft:dirt":"minecraft:soul_soil",
    "minecraft:grass_block":"minecraft:crimson_nylium",
    "minecraft:white_terracotta":"minecraft:black_terracotta",
    "minecraft:clay":"minecraft:gray_terracotta",
    "minecraft:terracotta":"minecraft:purple_terracotta",
    "minecraft:yellow_carpet":"minecraft:red_carpet",
    "minecraft:white_carpet":"minecraft:gray_carpet",
    "minecraft:green_carpet":"minecraft:light_gray_carpet",
    "minecraft:yellow_wool":"minecraft:red_wool",
    "minecraft:white_wool":"minecraft:gray_wool",
    "minecraft:smooth_stone":"minecraft:obsidian",
    "minecraft:wheat":"minecraft:nether_wart",
    "minecraft:farmland":"minecraft:soul_sand",
    "minecraft:grass_path":"minecraft:netherrack",
    "minecraft:mossy_cobblestone":"minecraft:basalt",
    "minecraft:hay_block":"minecraft:nether_wart_block",
    "minecraft:water":"minecraft:lava",
    "minecraft:torch":"minecraft:redstone_torch",
    "minecraft:wall_torch":"minecraft:redstone_wall_torch",
    "minecraft:glass_pane":"minecraft:orange_stained_glass_pane",
    "minecraft:white_stained_glass_pane":"minecraft:gray_stained_glass_pane",
    "minecraft:yellow_stained_glass_pane":"minecraft:red_stained_glass_pane",
    "minecraft:chest/village/village_plains_house":"repurposed_structures:chest/village/village_"+newBiome+"_house"
}


'''

#--------------------------------------------------------------------------------------------

blockPalette = {""}

originalBiome = ""
newBiome = ""
string_blacklist = []
conversion_partial_dict = {
    "minecraft:village/desert":"minecraft:village/desert/zombie",
    "repurposed_structures:village/badlands":"repurposed_structures:village/badlands/zombie"
}
conversion_exact_dict = {
}

#-------------------------------------------------------------------------------------------

def string_replacer(nbt_string):
    if nbt_string.value not in string_blacklist:
        for key, replacement in conversion_exact_dict.items():
            if nbt_string.value == key:
                nbt_string.value = nbt_string.value.replace(key, replacement)
                return
        for key, replacement in conversion_partial_dict.items():
            if key in nbt_string.value:
                nbt_string.value = nbt_string.value.replace(key, replacement)
                return


def traverse_dicts(nbt_list):
    '''
    for entry in nbt_list['palette'].value:
        blockPalette.add(entry.value['Name'].value)

    '''
    if isinstance(nbt_list, collections.abc.Mapping):
        for key, entry in nbt_list.items():
            if isinstance(entry, nbt.NBTTagList) or isinstance(entry, nbt.NBTTagCompound):
                traverse_dicts(entry)
            elif isinstance(entry, nbt.NBTTagString):
                string_replacer(entry)


    elif isinstance(nbt_list, nbt.NBTTagList) or isinstance(nbt_list, nbt.NBTTagCompound):
        for entry in nbt_list:
            if isinstance(entry, nbt.NBTTagInt):
                continue

            if isinstance(nbt_list, nbt.NBTTagList) or isinstance(entry, nbt.NBTTagCompound):
                traverse_dicts(entry)
            elif isinstance(entry, nbt.NBTTagString):
                string_replacer(entry)

for subdir, dirs, files in os.walk("toconvert"):
    for file in files:
        directory = subdir + os.sep
        filepath = directory + file

        if filepath.endswith(".nbt"): 
            nbtfile = nbt.read_from_nbt_file(filepath)
            traverse_dicts(nbtfile)

            directory = directory.replace("toconvert", "converted").replace(originalBiome, newBiome)
            Path(directory).mkdir(parents=True, exist_ok=True)
            newFile = directory + file.replace(originalBiome, newBiome)

            nbt.write_to_nbt_file(newFile, nbtfile)
            continue
        else:
            continue


for x in blockPalette:
  print(x)

print("FINISHED!")
input()