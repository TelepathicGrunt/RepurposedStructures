import python_nbt.nbt as nbt
from pathlib import Path
import collections.abc
import os

# https://pypi.org/project/Python-NBT/

'''
string_blacklist = ["minecraft:village/taiga/villagers"]
conversion_partial_dict = {
}
conversion_exact_dict = {
    "minecraft:chests/pillager_outpost":"repurposed_structures:chests/outpost/badlands_chest"
}
conversion_exact_dict = {
    "minecraft:blackstone_slab":"minecraft:cobblestone_slab",
    "minecraft:polished_blackstone_brick_stairs":"minecraft:cobblestone_stairs",
    "minecraft:blackstone_stairs":"minecraft:cobblestone_stairs",
    "minecraft:blackstone_wall":"minecraft:cobblestone_wall",
    "minecraft:gold_block":"minecraft:emerald_block",
    "minecraft:blackstone":"minecraft:dark_oak_planks",
    "minecraft:magma_block":"minecraft:dark_oak_planks",
    "minecraft:polished_blackstone_bricks":"minecraft:dark_oak_wood",
    "minecraft:gilded_blackstone":"minecraft:emerald_ore",
    "minecraft:cracked_polished_blackstone_bricks":"minecraft:dark_oak_wood",
    "minecraft:basalt":"minecraft:dark_oak_log",
    "minecraft:glowstone":"minecraft:orange_wool",
    "minecraft:lava":"minecraft:water",
    "minecraft:soul_sand":"minecraft:yellow_wool",
    "minecraft:polished_basalt":"minecraft:stripped_dark_oak_log",
    "minecraft:chiseled_polished_blackstone":"minecraft:chiseled_stone_bricks",
    "minecraft:netherrack":"minecraft:coarse_dirt",
    "minecraft:bastion/mob/hoglin":"repurposed_structures:bastion/pillager/mob/ravager",
    "minecraft:bastion/mob/piglin":"repurposed_structures:bastion/pillager/mob/pillagers",
    "minecraft:bastion/mob/piglin_melee":"repurposed_structures:bastion/pillager/mob/deadly_illagers",
    "minecraft:chests/bastion_other":"repurposed_structures:chests/bastion/pillager/other",
    "minecraft:chests/bastion_treasure":"repurposed_structures:chests/bastion/pillager/treasure",
    bastion_hoglin_stable
    bastion_bridge
}

'''

#--------------------------------------------------------------------------------------------

blockPalette = {""}

originalBiome = ""
newBiome = ""
string_blacklist = []
conversion_partial_dict = {
}
conversion_exact_dict = {
    "repurposed_structures:chests/fortresses/jungle_center_chest":"repurposed_structures:chests/fortresses/jungle_center",
    "repurposed_structures:chests/fortresses/jungle_hallway_chest":"repurposed_structures:chests/fortresses/jungle_hallway",
    "repurposed_structures:chests/fortresses/jungle_shrine_chest":"repurposed_structures:chests/fortresses/jungle_shrine",
    "repurposed_structures:chests/igloos/grassy_chest":"repurposed_structures:chests/igloos/grassy",
    "repurposed_structures:chests/igloos/stone_chest":"repurposed_structures:chests/igloos/stone",
    "repurposed_structures:chests/outposts/badlands_chest":"repurposed_structures:chests/outposts/badlands",
    "repurposed_structures:chests/outposts/birch_chest":"repurposed_structures:chests/outposts/birch",
    "repurposed_structures:chests/outposts/crimson_chest":"repurposed_structures:chests/outposts/crimson",
    "repurposed_structures:chests/outposts/desert_chest":"repurposed_structures:chests/outposts/desert",
    "repurposed_structures:chests/outposts/giant_tree_taiga_chest":"repurposed_structures:chests/outposts/giant_tree_taiga",
    "repurposed_structures:chests/outposts/icy_chest":"repurposed_structures:chests/outposts/icy",
    "repurposed_structures:chests/outposts/jungle_chest":"repurposed_structures:chests/outposts/jungle",
    "repurposed_structures:chests/outposts/nether_brick_chest":"repurposed_structures:chests/outposts/nether_brick",
    "repurposed_structures:chests/outposts/oak_chest":"repurposed_structures:chests/outposts/oak",
    "repurposed_structures:chests/outposts/snowy_chest":"repurposed_structures:chests/outposts/snowy",
    "repurposed_structures:chests/outposts/taiga_chest":"repurposed_structures:chests/outposts/taiga",
    "repurposed_structures:chests/outposts/warped_chest":"repurposed_structures:chests/outposts/warped",
    "repurposed_structures:chests/outposts/end_shulker_box":"repurposed_structures:shulker_boxes/outposts/end",
    "repurposed_structures:chests/pyramids/badlands_chest":"repurposed_structures:chests/pyramids/badlands",
    "repurposed_structures:chests/pyramids/nether_chest":"repurposed_structures:chests/pyramids/nether",
    "repurposed_structures:chests/pyramids/snowy_chest":"repurposed_structures:chests/pyramids/snowy",
    "repurposed_structures:chests/pyramids/icy_chest":"repurposed_structures:chests/pyramids/icy",
    "repurposed_structures:chests/pyramids/jungle_chest":"repurposed_structures:chests/pyramids/jungle",
    "repurposed_structures:chests/pyramids/mushroom_chest":"repurposed_structures:chests/pyramids/mushroom",
    "repurposed_structures:chests/pyramids/ocean_chest":"repurposed_structures:chests/pyramids/ocean",
    "repurposed_structures:chests/pyramids/giant_tree_taiga_chest":"repurposed_structures:chests/pyramids/giant_tree_taiga",
    "repurposed_structures:chests/pyramids/flower_forest_chest":"repurposed_structures:chests/pyramids/flower_forest",
    "repurposed_structures:chests/pyramids/end_chest":"repurposed_structures:chests/pyramids/end",
    "repurposed_structures:chests/ruined_portals/large_portal_chest":"repurposed_structures:chests/ruined_portals/large_portal",
    "repurposed_structures:chests/ruined_portals/small_portal_chest":"repurposed_structures:chests/ruined_portals/small_portal",
    "repurposed_structures:chests/shipwrecks/crimson/map_chest":"repurposed_structures:chests/shipwrecks/crimson/map",
    "repurposed_structures:chests/shipwrecks/crimson/supply_chest":"repurposed_structures:chests/shipwrecks/crimson/supply",
    "repurposed_structures:chests/shipwrecks/crimson/treasure_chest":"repurposed_structures:chests/shipwrecks/crimson/treasure",
    "repurposed_structures:chests/shipwrecks/end/map_chest":"repurposed_structures:chests/shipwrecks/end/map",
    "repurposed_structures:chests/shipwrecks/end/supply_chest":"repurposed_structures:chests/shipwrecks/end/supply",
    "repurposed_structures:chests/shipwrecks/end/treasure_chest":"repurposed_structures:chests/shipwrecks/end/treasure",
    "repurposed_structures:chests/shipwrecks/warped/map_chest":"repurposed_structures:chests/shipwrecks/warped/map",
    "repurposed_structures:chests/shipwrecks/warped/supply_chest":"repurposed_structures:chests/shipwrecks/warped/supply",
    "repurposed_structures:chests/shipwrecks/warped/treasure_chest":"repurposed_structures:chests/shipwrecks/warped/treasure",
    "repurposed_structures:chests/shipwrecks/nether_bricks/treasure_chest":"repurposed_structures:chests/shipwrecks/nether_bricks/treasure",
    "repurposed_structures:chests/temples/nether_basalt_chest":"repurposed_structures:chests/temples/nether_basalt",
    "repurposed_structures:chests/temples/nether_crimson_chest":"repurposed_structures:chests/temples/nether_crimson",
    "repurposed_structures:chests/temples/nether_soul_chest":"repurposed_structures:chests/temples/nether_soul",
    "repurposed_structures:chests/temples/nether_warped_chest":"repurposed_structures:chests/temples/nether_warped",
    "repurposed_structures:chests/temples/nether_wasteland_chest":"repurposed_structures:chests/temples/nether_wasteland",
    "repurposed_structures:chests/temples/nether_warped_trapped_chest":"repurposed_structures:trapped_chests/temples/nether_warped"
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

def property_replacer(nbt_key, nbt_string, property_name, value_to_replace, new_value):
    if nbt_key == property_name:
        if nbt_string.value == value_to_replace:
            nbt_string.value = new_value
            return



def traverse_dicts(nbt_list):
    '''
    for entry in nbt_list['palette'].value:
        blockPalette.add(entry.value['Name'].value)

    '''
    if isinstance(nbt_list, collections.abc.Mapping):
        for key, entry in nbt_list.items():

            # property_replacer(key, entry, "east", "true", "false")
            # property_replacer(key, entry, "north", "true", "false")
            # property_replacer(key, entry, "south", "true", "false")
            # property_replacer(key, entry, "west", "true", "false")
            # property_replacer(key, entry, "east", "tall", "none")
            # property_replacer(key, entry, "north", "tall", "none")
            # property_replacer(key, entry, "south", "tall", "none")
            # property_replacer(key, entry, "west", "tall", "none")
            # property_replacer(key, entry, "east", "low", "none")
            # property_replacer(key, entry, "north", "low", "none")
            # property_replacer(key, entry, "south", "low", "none")
            # property_replacer(key, entry, "west", "low", "none")
            # property_replacer(key, entry, "up", "false", "true")

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


for x in blockPalette:
  print(x)

print("FINISHED!")
input()