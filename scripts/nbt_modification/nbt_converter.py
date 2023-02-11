import python_nbt.nbt as nbt
from pathlib import Path
import collections.abc
import os

# https://pypi.org/project/Python-NBT/
#--------------------------------------------------------------------------------------------

blockPalette = {""}

originalBiome = ""
newBiome = ""
string_blacklist = []
conversion_partial_dict = {
    "birch":"oak"
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

def property_replacer(nbt_key, nbt_string, property_name, value_to_replace, new_value):
    if nbt_key == property_name:
        if nbt_string.value == value_to_replace:
            nbt_string.value = new_value
            return



def traverse_dicts(nbt_list):
    if isinstance(nbt_list, collections.abc.Mapping):
        '''
        if 'size' in nbt_list:
            nbt_list['size'][1].value = 32
        '''
        
        if 'palette' in nbt_list:
            for entry in nbt_list['palette'].value:
                blockPalette.add(entry.value['Name'].value)

        '''
        for key, entry in nbt_list.items():
            if key == "pos" or key == "blockPos" or key == "size":
                entry[1].value = entry[1].value + 2
        '''

        nbt_list.pop('SleepingX', None)
        nbt_list.pop('SleepingY', None)
        nbt_list.pop('SleepingZ', None)
        
        if 'Attributes' in nbt_list:
            attributes = nbt_list['Attributes']
            for entry in attributes:
                if entry["Name"] == "forge:entity_gravity" or entry["Name"] == "forge:step_height_addition":
                    nbt_list['Attributes'].remove(entry)
        
        for key, entry in nbt_list.items():

            if isinstance(entry, nbt.NBTTagList) or isinstance(entry, nbt.NBTTagCompound):
                traverse_dicts(entry)
            elif isinstance(entry, nbt.NBTTagString):
                string_replacer(entry)

            #property_replacer(key, entry, "PersistenceRequired", 0, 1)
            #property_replacer(key, entry, "waterlogged", "true", "false")
            #property_replacer(key, entry, "joint", "rollable", "aligned")


    elif isinstance(nbt_list, nbt.NBTTagList) or isinstance(nbt_list, nbt.NBTTagCompound):
        for entry in nbt_list:
            if isinstance(entry, nbt.NBTTagInt):
                continue

            if isinstance(nbt_list, nbt.NBTTagList) or isinstance(entry, nbt.NBTTagCompound):
                traverse_dicts(entry)
            elif isinstance(entry, nbt.NBTTagString):
                string_replacer(entry)

for (subdir, dirs, files) in os.walk("toconvert", topdown=True):
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

for x in sorted(blockPalette):
  print("\""+x+"\": \""+x+"\",")

print("FINISHED!")
input()


#------------------------------------------------------------------------


'''

conversion_partial_dict = {
    "minecraft:ancient_city/": "repurposed_structures:ancient_cities/end/",
    "minecraft:ancient_city/sculk": "repurposed_structures:ancient_cities/end/chorus",
}
conversion_exact_dict = {
    "minecraft:chests/ancient_city": "repurposed_structures:chests/ancient_cities/end",
    "minecraft:chests/ancient_city_ice_box": "repurposed_structures:chests/ancient_cities/end_spawner_box",
    "minecraft:blue_carpet": "minecraft:magenta_carpet",
    "minecraft:blue_ice": "minecraft:obsidian",      
    "minecraft:blue_wool": "minecraft:magenta_wool",    
    "minecraft:campfire": "minecraft:end_stone",      
    "minecraft:candle": "minecraft:black_candle",
    "minecraft:cave_air": "minecraft:cave_air",
    "minecraft:chest": "minecraft:chest",
    "minecraft:chiseled_deepslate": "minecraft:purpur_pillar",
    "minecraft:cobbled_deepslate": "minecraft:end_stone_bricks",
    "minecraft:cobbled_deepslate_slab": "minecraft:end_stone_brick_slab",
    "minecraft:cobbled_deepslate_stairs": "minecraft:end_stone_brick_stairs",
    "minecraft:cobbled_deepslate_wall": "minecraft:end_stone_brick_wall",
    "minecraft:comparator": "minecraft:comparator",
    "minecraft:cracked_deepslate_bricks": "minecraft:end_stone_bricks",
    "minecraft:cracked_deepslate_tiles": "minecraft:end_stone_bricks",
    "minecraft:cyan_carpet": "minecraft:pink_carpet",
    "minecraft:cyan_wool": "minecraft:pink_wool",
    "minecraft:dark_oak_fence": "minecraft:purpur_pillar",
    "minecraft:dark_oak_log": "minecraft:purpur_pillar",
    "minecraft:dark_oak_planks": "minecraft:purpur_block",
    "minecraft:deepslate": "minecraft:end_stone_bricks",
    "minecraft:deepslate_brick_slab": "minecraft:end_stone_brick_slab",
    "minecraft:deepslate_brick_stairs": "minecraft:end_stone_brick_stairs",
    "minecraft:deepslate_brick_wall": "minecraft:end_stone_brick_wall",
    "minecraft:deepslate_bricks": "minecraft:end_stone_bricks",
    "minecraft:deepslate_tile_slab": "minecraft:purpur_slab",
    "minecraft:deepslate_tile_stairs": "minecraft:purpur_stairs",
    "minecraft:deepslate_tile_wall": "minecraft:end_stone_brick_wall",
    "minecraft:deepslate_tiles": "minecraft:purpur_block",
    "minecraft:dirt": "minecraft:end_stone",
    "minecraft:furnace": "minecraft:smithing_table",
    "minecraft:glass": "minecraft:magenta_stained_glass",
    "minecraft:glass_pane": "minecraft:magenta_stained_glass_pane",
    "minecraft:grass_block": "minecraft:end_stone",
    "minecraft:gray_carpet": "minecraft:yellow_carpet",
    "minecraft:gray_wool": "minecraft:yellow_wool",
    "minecraft:ice": "minecraft:obsidian",
    "minecraft:iron_trapdoor": "minecraft:iron_trapdoor",
    "minecraft:jigsaw": "minecraft:jigsaw",
    "minecraft:ladder": "minecraft:ladder",
    "minecraft:lectern": "minecraft:respawn_anchor",
    "minecraft:lever": "minecraft:lever",
    "minecraft:light_blue_carpet": "minecraft:purple_carpet",
    "minecraft:light_blue_wool": "minecraft:purple_wool",
    "minecraft:note_block": "minecraft:note_block",
    "minecraft:packed_ice": "minecraft:obsidian",
    "minecraft:piston_head": "minecraft:piston_head",
    "minecraft:polished_basalt": "minecraft:obsidian",
    "minecraft:polished_deepslate": "minecraft:purpur_block",
    "minecraft:polished_deepslate_slab": "minecraft:purpur_slab",
    "minecraft:polished_deepslate_stairs": "minecraft:purpur_stairs",
    "minecraft:polished_deepslate_wall": "minecraft:end_stone_brick_wall",
    "minecraft:redstone_block": "minecraft:redstone_block",
    "minecraft:redstone_lamp": "minecraft:redstone_lamp",
    "minecraft:redstone_torch": "minecraft:redstone_torch",
    "minecraft:redstone_wall_torch": "minecraft:redstone_wall_torch",
    "minecraft:redstone_wire": "minecraft:redstone_wire",
    "minecraft:reinforced_deepslate": "minecraft:end_gateway",
    "minecraft:repeater": "minecraft:repeater",
    "minecraft:sculk_sensor": "minecraft:cave_air",
    "minecraft:skeleton_skull": "minecraft:cave_air",
    "minecraft:smooth_basalt": "minecraft:obsidian",
    "minecraft:snow": "minecraft:cave_air",
    "minecraft:soul_fire": "minecraft:obsidian",
    "minecraft:soul_lantern": "minecraft:soul_lantern",
    "minecraft:soul_sand": "minecraft:obsidian",
    "minecraft:sticky_piston": "minecraft:sticky_piston",
    "minecraft:stone_pressure_plate": "minecraft:light_weighted_pressure_plate",
    "minecraft:target": "minecraft:target",
    "minecraft:torch": "minecraft:soul_torch",
    "minecraft:water": "minecraft:lava",
    "minecraft:white_candle": "minecraft:yellow_candle"
}





conversion_partial_dict = {
    "minecraft:ancient_city/": "repurposed_structures:ancient_cities/nether/",
}
conversion_exact_dict = {
    "minecraft:chests/ancient_city": "repurposed_structures:chests/ancient_cities/nether",
    "minecraft:chests/ancient_city_ice_box": "repurposed_structures:chests/ancient_cities/nether_magma_box",
    "minecraft:blue_carpet": "minecraft:warped_trapdoor",
    "minecraft:blue_ice": "minecraft:magma_block",      
    "minecraft:blue_wool": "minecraft:warped_hyphae",    
    "minecraft:campfire": "minecraft:soul_campfire",      
    "minecraft:candle": "minecraft:lantern",
    "minecraft:cave_air": "minecraft:cave_air",
    "minecraft:chest": "minecraft:chest",
    "minecraft:chiseled_deepslate": "minecraft:chiseled_nether_bricks",
    "minecraft:cobbled_deepslate": "minecraft:nether_bricks",
    "minecraft:cobbled_deepslate_slab": "minecraft:nether_brick_slab",
    "minecraft:cobbled_deepslate_stairs": "minecraft:nether_brick_stairs",
    "minecraft:cobbled_deepslate_wall": "minecraft:nether_brick_wall",
    "minecraft:comparator": "minecraft:comparator",
    "minecraft:cracked_deepslate_bricks": "minecraft:cracked_nether_bricks",
    "minecraft:cracked_deepslate_tiles": "minecraft:cracked_nether_bricks",
    "minecraft:cyan_carpet": "minecraft:warped_trapdoor",
    "minecraft:cyan_wool": "minecraft:warped_hyphae",
    "minecraft:dark_oak_fence": "minecraft:crimson_fence",
    "minecraft:dark_oak_log": "minecraft:crimson_log",
    "minecraft:dark_oak_planks": "minecraft:crimson_planks",
    "minecraft:deepslate": "minecraft:basalt",
    "minecraft:deepslate_brick_slab": "minecraft:red_nether_brick_slab",
    "minecraft:deepslate_brick_stairs": "minecraft:red_nether_brick_stairs",
    "minecraft:deepslate_brick_wall": "minecraft:red_nether_brick_wall",
    "minecraft:deepslate_bricks": "minecraft:red_nether_bricks",
    "minecraft:deepslate_tile_slab": "minecraft:polished_blackstone_brick_slab",
    "minecraft:deepslate_tile_stairs": "minecraft:polished_blackstone_brick_stairs",
    "minecraft:deepslate_tile_wall": "minecraft:polished_blackstone_brick_wall",
    "minecraft:deepslate_tiles": "minecraft:polished_blackstone_bricks",
    "minecraft:dirt": "minecraft:netherrack",
    "minecraft:furnace": "minecraft:blast_furnace",
    "minecraft:glass": "minecraft:black_stained_glass",
    "minecraft:glass_pane": "minecraft:black_stained_glass_pane",
    "minecraft:grass_block": "minecraft:gilded_blackstone",
    "minecraft:gray_carpet": "minecraft:crimson_trapdoor",
    "minecraft:gray_wool": "minecraft:crimson_hyphae",
    "minecraft:ice": "minecraft:magma_block",
    "minecraft:iron_trapdoor": "minecraft:iron_trapdoor",
    "minecraft:jigsaw": "minecraft:jigsaw",
    "minecraft:ladder": "minecraft:ladder",
    "minecraft:lectern": "minecraft:lodestone",
    "minecraft:lever": "minecraft:lever",
    "minecraft:light_blue_carpet": "minecraft:warped_trapdoor",
    "minecraft:light_blue_wool": "minecraft:warped_hyphae",
    "minecraft:note_block": "minecraft:note_block",
    "minecraft:packed_ice": "minecraft:magma_block",
    "minecraft:piston_head": "minecraft:piston_head",
    "minecraft:polished_basalt": "minecraft:polished_basalt",
    "minecraft:polished_deepslate": "minecraft:polished_blackstone",
    "minecraft:polished_deepslate_slab": "minecraft:polished_blackstone_slab",
    "minecraft:polished_deepslate_stairs": "minecraft:polished_blackstone_stairs",
    "minecraft:polished_deepslate_wall": "minecraft:polished_blackstone_wall",
    "minecraft:redstone_block": "minecraft:redstone_block",
    "minecraft:redstone_lamp": "minecraft:redstone_lamp",
    "minecraft:redstone_torch": "minecraft:redstone_torch",
    "minecraft:redstone_wall_torch": "minecraft:redstone_wall_torch",
    "minecraft:redstone_wire": "minecraft:redstone_wire",
    "minecraft:reinforced_deepslate": "minecraft:black_glazed_terracotta",
    "minecraft:repeater": "minecraft:repeater",
    "minecraft:sculk_sensor": "minecraft:cave_air",
    "minecraft:skeleton_skull": "minecraft:wither_skeleton_skull",
    "minecraft:smooth_basalt": "minecraft:smooth_basalt",
    "minecraft:snow": "minecraft:cave_air",
    "minecraft:soul_fire": "minecraft:soul_fire",
    "minecraft:soul_lantern": "minecraft:soul_lantern",
    "minecraft:soul_sand": "minecraft:soul_sand",
    "minecraft:sticky_piston": "minecraft:sticky_piston",
    "minecraft:stone_pressure_plate": "minecraft:crimson_pressure_plate",
    "minecraft:target": "minecraft:target",
    "minecraft:torch": "minecraft:soul_torch",
    "minecraft:water": "minecraft:lava",
    "minecraft:white_candle": "minecraft:lantern"
}
'''
