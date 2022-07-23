import python_nbt.nbt as nbt
from pathlib import Path
import collections.abc
import os

# https://pypi.org/project/Python-NBT/
'''
conversion_partial_dict = {
    "betterdeserttemples:entrance": "betterdeserttemples:dark_forest/entrance",
    "betterdeserttemples:hall": "betterdeserttemples:dark_forest/hall",
    "betterdeserttemples:main": "betterdeserttemples:dark_forest/main",
    "betterdeserttemples:middle": "betterdeserttemples:dark_forest/middle",
    "betterdeserttemples:mobs": "betterdeserttemples:dark_forest/mobs",
    "betterdeserttemples:parkour": "betterdeserttemples:dark_forest/parkour",
    "betterdeserttemples:pillar": "betterdeserttemples:dark_forest/pillar",
    "betterdeserttemples:room": "betterdeserttemples:dark_forest/room",
    "betterdeserttemples:slopes": "betterdeserttemples:dark_forest/slopes",
    "betterdeserttemples:statue": "betterdeserttemples:dark_forest/statue",
    "betterdeserttemples:top": "betterdeserttemples:dark_forest/top",
    "betterdeserttemples:walls": "betterdeserttemples:dark_forest/walls"
}
conversion_exact_dict = {   
    "minecraft:birch_wall_sign": "minecraft:jungle_wall_sign",
    
    "minecraft:birch_button": "minecraft:dark_oak_button",
    "minecraft:birch_fence": "minecraft:dark_oak_fence",
    "minecraft:birch_planks": "minecraft:dark_oak_planks",
    "minecraft:birch_pressure_plate": "minecraft:dark_oak_pressure_plate",    
    "minecraft:birch_slab": "minecraft:dark_oak_slab",
    "minecraft:birch_trapdoor": "minecraft:dark_oak_trapdoor",
    "minecraft:oak_planks": "minecraft:jungle_planks",
    "minecraft:oak_trapdoor": "minecraft:jungle_trapdoor",
    "minecraft:spruce_trapdoor": "minecraft:spruce_trapdoor",
    "minecraft:crimson_button": "minecraft:crimson_button",
    "minecraft:crimson_slab": "minecraft:crimson_slab",
    "minecraft:warped_pressure_plate": "minecraft:crimson_pressure_plate",

    "minecraft:blackstone": "minecraft:blackstone",
    "minecraft:blackstone_slab": "minecraft:blackstone_slab",
    "minecraft:blackstone_wall": "minecraft:blackstone_wall",
    "minecraft:polished_blackstone": "minecraft:polished_blackstone",
    "minecraft:polished_blackstone_button": "minecraft:polished_blackstone_button",
    "minecraft:polished_blackstone_stairs": "minecraft:polished_blackstone_stairs",
    "minecraft:sand": "minecraft:red_sand",
    "minecraft:sandstone": "minecraft:red_sandstone",
    "minecraft:sandstone_slab": "minecraft:red_sandstone_slab",
    "minecraft:sandstone_stairs": "minecraft:red_sandstone_stairs",        
    "minecraft:sandstone_wall": "minecraft:red_sandstone_wall",
    "minecraft:smooth_sandstone": "minecraft:smooth_red_sandstone",
    "minecraft:smooth_sandstone_slab": "minecraft:smooth_red_sandstone_slab",
    "minecraft:smooth_sandstone_stairs": "minecraft:smooth_red_sandstone_stairs",
    "minecraft:cut_sandstone": "minecraft:cut_red_sandstone",
    "minecraft:cut_sandstone_slab": "minecraft:cut_red_sandstone_slab",
    "minecraft:chiseled_sandstone": "minecraft:chiseled_red_sandstone",
    "minecraft:stone_button": "minecraft:crimson_button",
    "minecraft:stone_pressure_plate": "minecraft:crimson_pressure_plate",
    
    "minecraft:black_carpet": "minecraft:black_carpet",
    "minecraft:black_concrete": "minecraft:black_concrete",
    "minecraft:black_stained_glass": "minecraft:black_stained_glass",
    "minecraft:black_wall_banner": "minecraft:black_wall_banner",
    "minecraft:black_wool": "minecraft:black_wool",
    "minecraft:blue_carpet": "minecraft:orange_carpet",
    "minecraft:blue_concrete": "minecraft:orange_concrete",
    "minecraft:blue_glazed_terracotta": "minecraft:orange_glazed_terracotta",
    "minecraft:blue_wall_banner": "minecraft:orange_wall_banner",        
    "minecraft:blue_wool": "minecraft:orange_wool",
    "minecraft:green_glazed_terracotta": "minecraft:yellow_glazed_terracotta",
    "minecraft:green_wool": "minecraft:yellow_wool",
    "minecraft:red_concrete_powder": "minecraft:red_concrete_powder",
    "minecraft:red_wall_banner": "minecraft:red_wall_banner",
    "minecraft:yellow_carpet": "minecraft:brown_carpet",
    "minecraft:yellow_concrete": "minecraft:brown_terracotta",
    "minecraft:yellow_glazed_terracotta": "minecraft:brown_glazed_terracotta",
    "minecraft:yellow_terracotta": "minecraft:brown_terracotta",
    "minecraft:yellow_wall_banner": "minecraft:brown_wall_banner",

    "minecraft:terracotta": "minecraft:terracotta",
    "minecraft:clay": "minecraft:clay",
    "minecraft:coarse_dirt": "minecraft:coarse_dirt",
    "minecraft:podzol": "minecraft:podzol",
    "minecraft:flower_pot": "minecraft:flower_pot",
    "minecraft:potted_blue_orchid": "minecraft:potted_red_tulip",
    "minecraft:potted_red_nether": "minecraft:potted_red_nether",
    "minecraft:glow_lichen": "minecraft:glow_lichen",

    "minecraft:redstone_ore": "minecraft:redstone_ore",
    "minecraft:gilded_blackstone": "minecraft:gilded_blackstone",
    "minecraft:gold_block": "minecraft:gold_block",
    "minecraft:gold_ore": "minecraft:gold_ore",
    "minecraft:raw_gold_block": "minecraft:raw_gold_block",

    "minecraft:magma_block": "minecraft:magma_block",
    "minecraft:fire": "minecraft:fire",
    "minecraft:lava": "minecraft:lava",
    "minecraft:water": "minecraft:water",
    "minecraft:water_cauldron": "minecraft:water_cauldron",
    "minecraft:kelp": "minecraft:kelp",
    "minecraft:kelp_plant": "minecraft:kelp_plant",
    "minecraft:seagrass": "minecraft:seagrass",

    "minecraft:torch": "minecraft:torch",
    "minecraft:wall_torch": "minecraft:wall_torch",

    "minecraft:skeleton_skull": "minecraft:skeleton_skull",
    "minecraft:zombie_head": "minecraft:zombie_head",

    "minecraft:sponge": "minecraft:candle",

    "minecraft:chests/desert_pyramid": "repurposed_structures:trapped_chests/pyramids/dark_forest"
}
'''
'''
conversion_partial_dict = {
    "minecraft:spruce_stairs": "minecraft:mangrove_stairs",
}
conversion_exact_dict = {
    "minecraft:moss_block": "minecraft:mangrove_roots",
    "minecraft:moss_carpet": "minecraft:mangrove_roots",
    "minecraft:stripped_spruce_wood": "minecraft:mangrove_wood",
    "minecraft:oak_wood": "minecraft:muddy_mangrove_roots",
    "minecraft:black_terracotta": "minecraft:packed_mud",
    "minecraft:gray_terracotta": "minecraft:mud",
    "minecraft:brown_terracotta": "minecraft:muddy_mangrove_roots",
    "minecraft:spruce_stairs": "minecraft:mangrove_stairs",
    "minecraft:spruce_slab": "minecraft:mangrove_slab",
    "minecraft:oak_stairs": "minecraft:spruce_stairs",
    "minecraft:oak_planks": "minecraft:spruce_planks",
    "minecraft:oak_door": "minecraft:mangrove_door",
    "minecraft:oak_fence": "minecraft:mangrove_fence",
    "minecraft:oak_fence_gate": "minecraft:mangrove_fence_gate",
    "minecraft:andesite": "minecraft:mud_bricks",
    "minecraft:andesite_stairs": "minecraft:mud_brick_stairs",
    "minecraft:andesite_slab": "minecraft:mud_brick_slab",
    "minecraft:andesite_wall": "minecraft:mud_brick_wall",
    "minecraft:cobblestone": "minecraft:mud_bricks",
    "minecraft:cobblestone_stairs": "minecraft:mud_brick_stairs",
    "minecraft:cobblestone_slab": "minecraft:mud_brick_slab",
    "minecraft:cobblestone_wall": "minecraft:mud_brick_wall",
    "minecraft:red_carpet": "minecraft:brown_carpet",
    "minecraft:blue_carpet": "minecraft:green_carpet"
}


--------------------------


conversion_partial_dict = {
    "betterdeserttemples:entrance": "betterdeserttemples:flower_forest/entrance",
    "betterdeserttemples:hall": "betterdeserttemples:flower_forest/hall",
    "betterdeserttemples:main": "betterdeserttemples:flower_forest/main",
    "betterdeserttemples:middle": "betterdeserttemples:flower_forest/middle",
    "betterdeserttemples:mobs": "betterdeserttemples:flower_forest/mobs",
    "betterdeserttemples:parkour": "betterdeserttemples:flower_forest/parkour",
    "betterdeserttemples:pillar": "betterdeserttemples:flower_forest/pillar",
    "betterdeserttemples:room": "betterdeserttemples:flower_forest/room",
    "betterdeserttemples:slopes": "betterdeserttemples:flower_forest/slopes",
    "betterdeserttemples:statue": "betterdeserttemples:flower_forest/statue",
    "betterdeserttemples:top": "betterdeserttemples:flower_forest/top",
    "betterdeserttemples:walls": "betterdeserttemples:flower_forest/walls"
}
conversion_exact_dict = {   
    "minecraft:birch_wall_sign": "minecraft:crimson_wall_sign",
    
    "minecraft:birch_button": "minecraft:birch_button",
    "minecraft:birch_fence": "minecraft:birch_fence",
    "minecraft:birch_planks": "minecraft:birch_planks",
    "minecraft:birch_pressure_plate": "minecraft:birch_pressure_plate",    
    "minecraft:birch_slab": "minecraft:birch_slab",
    "minecraft:birch_trapdoor": "minecraft:birch_trapdoor",
    "minecraft:oak_planks": "minecraft:oak_planks",
    "minecraft:oak_trapdoor": "minecraft:oak_trapdoor",
    "minecraft:spruce_trapdoor": "minecraft:oak_trapdoor",
    "minecraft:crimson_button": "minecraft:oak_button",
    "minecraft:crimson_slab": "minecraft:oak_slab",
    "minecraft:warped_pressure_plate": "minecraft:oak_pressure_plate",

    "minecraft:gilded_blackstone": "minecraft:gilded_blackstone",
    "minecraft:blackstone": "minecraft:blackstone",
    "minecraft:blackstone_slab": "minecraft:blackstone_slab",
    "minecraft:blackstone_wall": "minecraft:blackstone_wall",
    "minecraft:polished_blackstone": "minecraft:polished_blackstone",
    "minecraft:polished_blackstone_button": "minecraft:polished_blackstone_button",
    "minecraft:polished_blackstone_stairs": "minecraft:polished_blackstone_stairs",
    "minecraft:sand": "minecraft:brown_concerete_powder",
    "minecraft:sandstone": "minecraft:grass_block",
    "minecraft:sandstone_slab": "minecraft:spruce_slab",
    "minecraft:sandstone_stairs": "minecraft:spruce_stairs",        
    "minecraft:sandstone_wall": "minecraft:andesite_wall",
    "minecraft:smooth_sandstone": "minecraft:black_terracotta",
    "minecraft:smooth_sandstone_slab": "minecraft:spruce_slab",
    "minecraft:smooth_sandstone_stairs": "minecraft:spruce_stairs",
    "minecraft:cut_sandstone": "minecraft:brown_terracotta",
    "minecraft:cut_sandstone_slab": "minecraft:spruce_slab",
    "minecraft:chiseled_sandstone": "minecraft:yellow_terracotta",
    "minecraft:stone_button": "minecraft:spruce_button",
    "minecraft:stone_pressure_plate": "minecraft:spruce_pressure_plate",
    
    "minecraft:black_carpet": "minecraft:brown_carpet",
    "minecraft:black_concrete": "minecraft:brown_concrete",
    "minecraft:black_stained_glass": "minecraft:brown_stained_glass",
    "minecraft:black_wall_banner": "minecraft:brown_wall_banner",
    "minecraft:black_wool": "minecraft:brown_wool",
    "minecraft:blue_carpet": "minecraft:green_carpet",
    "minecraft:blue_concrete": "minecraft:yellow_terracotta",
    "minecraft:blue_glazed_terracotta": "minecraft:green_glazed_terracotta",
    "minecraft:blue_wall_banner": "minecraft:green_wall_banner",        
    "minecraft:blue_wool": "minecraft:green_wool",
    "minecraft:green_glazed_terracotta": "minecraft:lime_glazed_terracotta",
    "minecraft:green_wool": "minecraft:lime_wool",
    "minecraft:red_concrete_powder": "minecraft:lime_concrete_powder",
    "minecraft:red_wall_banner": "minecraft:lime_wall_banner",
    "minecraft:yellow_carpet": "minecraft:yellow_carpet",
    "minecraft:yellow_concrete": "minecraft:yellow_terracotta",
    "minecraft:yellow_glazed_terracotta": "minecraft:yellow_glazed_terracotta",
    "minecraft:yellow_terracotta": "minecraft:brown_terracotta",
    "minecraft:yellow_wall_banner": "minecraft:yellow_wall_banner",

    "minecraft:terracotta": "minecraft:terracotta",
    "minecraft:clay": "minecraft:clay",
    "minecraft:coarse_dirt": "minecraft:grass_block",
    "minecraft:podzol": "minecraft:podzol",
    "minecraft:flower_pot": "minecraft:flower_pot",
    "minecraft:potted_blue_orchid": "minecraft:potted_flowering_azalea",
    "minecraft:potted_red_nether": "minecraft:potted_red_tulip",
    "minecraft:glow_lichen": "minecraft:glow_lichen",

    "minecraft:redstone_ore": "minecraft:redstone_ore",
    "minecraft:gold_block": "minecraft:gold_block",
    "minecraft:gold_ore": "minecraft:emerald_ore",
    "minecraft:raw_gold_block": "minecraft:raw_gold_block",

    "minecraft:magma_block": "minecraft:magma_block",
    "minecraft:fire": "minecraft:fire",
    "minecraft:lava": "minecraft:lava",
    "minecraft:water": "minecraft:water",
    "minecraft:water_cauldron": "minecraft:water_cauldron",
    "minecraft:kelp": "minecraft:kelp",
    "minecraft:kelp_plant": "minecraft:kelp_plant",
    "minecraft:seagrass": "minecraft:seagrass",


    "minecraft:torch": "minecraft:torch",
    "minecraft:wall_torch": "minecraft:wall_torch",

    "minecraft:skeleton_skull": "minecraft:creeper_head",
    "minecraft:zombie_head": "minecraft:zombie_head",

    "minecraft:sponge": "minecraft:candle",

    "minecraft:chests/desert_pyramid": "repurposed_structures:chests/pyramids/flower_forest"
    #"minecraft:gold_ore": "minecraft:redstone_ore_ore"
    #"minecraft:blue_carpet": "minecraft:blackcarpet",
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
    "repurposed_structures:mansions/vindicators": "repurposed_structures:mansions/taiga/mobs/vindicators",
    "repurposed_structures:mansions/evokers": "repurposed_structures:mansions/taiga/mobs/evokers",
    "repurposed_structures:mansions/allays": "repurposed_structures:mansions/taiga/mobs/allays"
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
        
        '''
        if 'palette' in nbt_list:
            for entry in nbt_list['palette'].value:
                blockPalette.add(entry.value['Name'].value)
        '''

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

            property_replacer(key, entry, "PersistenceRequired", 0, 1)
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
  print(x)

print("FINISHED!")
input()


#------------------------------------------------------------------------


'''
# End Better Strongholds
conversion_partial_dict = {
    "white": "magenta",
    "lime_bed": "purple_bed",
    "betterstrongholds:chests/": "betterstrongholds:chests/end/",
    "betterstrongholds:": "betterstrongholds:end/"
}
conversion_exact_dict = {
    "minecraft:stone_button":"minecraft:air",
    "minecraft:stone_bricks":"minecraft:obsidian",
    "minecraft:cobblestone_stairs":"minecraft:purpur_stairs",
    "minecraft:stone_brick_slab":"minecraft:purpur_slab",
    "minecraft:stone_brick_stairs":"minecraft:purpur_stairs",
    "minecraft:cobblestone":"minecraft:obsidian",
    "minecraft:cobblestone_slab":"minecraft:purpur_slab",
    "minecraft:cobblestone_bricks":"minecraft:purpur_block",
    "minecraft:chiseled_stone_bricks":"minecraft:purpur_block",
    "minecraft:polished_andesite_stairs":"minecraft:purpur_stairs",
    "minecraft:polished_andesite_slab":"minecraft:purpur_slab",
    "minecraft:smooth_stone_slab":"minecraft:purpur_slab",

    "minecraft:spruce_wall_sign":"minecraft:crimson_wall_sign",
    "minecraft:spruce_trapdoor":"minecraft:iron_trapdoor",
    "minecraft:spruce_planks":"minecraft:purpur_block",
    "minecraft:spruce_stairs":"minecraft:purpur_stairs",
    "minecraft:spruce_slab":"minecraft:purpur_slab",
    "minecraft:oak_stairs":"minecraft:purpur_stairs",
    "minecraft:oak_slab":"minecraft:purpur_slab",
    "minecraft:stripped_oak_log":"minecraft:purpur_pillar",
    "minecraft:bookshelf":"minecraft:amethyst_block",

    "minecraft:white_stained_glass":"minecraft:air",
    "minecraft:gray_stained_glass":"minecraft:air",
    "minecraft:yellow_stained_glass":"minecraft:obsidian",
    "minecraft:purpur_block":"minecraft:diamond_block",
    "minecraft:cyan_concrete":"minecraft:air",
    "minecraft:cyan_terracotta":"minecraft:air",

    "minecraft:water":"minecraft:amethyst_block",
    "minecraft:flowing_water":"minecraft:amethyst_block",
    "minecraft:lava":"minecraft:amethyst_block",
    "minecraft:flowing_lava":"minecraft:amethyst_block"
}
'''


'''
#Nether Better Strongholds

conversion_partial_dict = {
    "spruce": "crimson",
    "oak": "crimson",
    "betterstrongholds:": "betterstrongholds:nether/",
    "minecraft:lantern": "minecraft:soul_lantern",
    "andesite_wall": "blackstone_wall"
}
conversion_exact_dict = {
    "minecraft:water": "minecraft:lava",
    "minecraft:white_stained_glass": "minecraft:cave_air",
    "minecraft:gray_stained_glass": "minecraft:cave_air",
    "minecraft:cyan_concrete": "minecraft:blackstone",
    "minecraft:cyan_terracotta": "minecraft:black_terracotta",
    "minecraft:smooth_stone_slab": "minecraft:red_nether_brick_slab",
    "minecraft:red_bed": "minecraft:orange_bed",
    "minecraft:lime_bed": "minecraft:black_bed",
    "minecraft:gray_bed": "minecraft:red_bed",
    "minecraft:iron_ore": "minecraft:nether_quartz_ore",
    "minecraft:stone_pressure_plate": "minecraft:crimson_pressure_plate",
    "minecraft:skeleton_skull": "minecraft:wither_skeleton_skull",
    "minecraft:cobblestone_stairs": "minecraft:nether_brick_stairs",
    "minecraft:stone_brick_stairs": "minecraft:polished_blackstone_brick_stairs",
    "minecraft:chiseled_stone_bricks": "minecraft:chiseled_nether_bricks",
    "minecraft:white_stained_glass_pane": "minecraft:gray_stained_glass_pane",
    "minecraft:andesite_wall": "minecraft:blackstone_wall",
    "minecraft:polished_andesite": "minecraft:polished_blackstone",
    "minecraft:polished_andesite_slab": "minecraft:polished_blackstone_slab",
    "minecraft:polished_andesite_stairs": "minecraft:polished_blackstone_stairs",
    "minecraft:stone_brick_slab": "minecraft:polished_blackstone_brick_slab",
    "minecraft:coal_block": "minecraft:quartz_block",
    "minecraft:cobblestone": "minecraft:nether_bricks",
    "minecraft:stone_bricks": "minecraft:polished_blackstone_bricks",
    "minecraft:petrified_oak_slab": "minecraft:red_nether_brick_slab",
    "minecraft:nether_brick_slab": "minecraft:red_nether_brick_slab",
    "minecraft:nether_brick_stairs": "minecraft:red_nether_brick_stairs",
    "minecraft:stripped_oak_wood": "minecraft:stripped_crimson_hyphae",
    "minecraft:birch_pressure_plate": "minecraft:crimson_pressure_plate",
    "minecraft:bookshelves": "minecraft:gilded_blackstone",
    "minecraft:yellow_stained_glass": "minecraft:chiseled_polished_blackstone",
    "minecraft:stripped_spruce_wood": "minecraft:stripped_crimson_hyphae",
    "minecraft:stripped_spruce_log": "minecraft:stripped_crimson_hyphae",
    "minecraft:bookshelf": "minecraft:gilded_blackstone",
    "minecraft:stone_brick_wall": "minecraft:polished_blackstone_brick_wall",
    "minecraft:torch": "minecraft:soul_torch",
    "minecraft:wall_torch": "minecraft:wall_soul_torch",
    "stone_brick_wall[east=none,south=none,north=tall,up=true,west=none]": "minecraft:polished_blackstone_brick_wall[east=none,south=none,north=tall,up=true,west=none]",
    "stone_brick_wall[east=none,south=tall,north=none,up=true,west=none]": "minecraft:polished_blackstone_brick_wall[east=none,south=tall,north=none,up=true,west=none]",
    "stone_brick_wall[east=tall,south=none,north=none,up=true,west=none]": "minecraft:polished_blackstone_brick_wall[east=tall,south=none,north=none,up=true,west=none]",
    "stone_brick_wall[east=none,south=none,north=none,up=true,west=tall]": "minecraft:polished_blackstone_brick_wall[east=none,south=none,north=none,up=true,west=tall]",
    "stone_brick_wall[east=none,north=tall,south=none,up=true,west=none]": "minecraft:polished_blackstone_brick_wall[east=none,north=tall,south=none,up=true,west=none]",
    "stone_brick_wall[east=tall,north=none,south=none,up=true,west=none]": "minecraft:polished_blackstone_brick_wall[east=tall,north=none,south=none,up=true,west=none]",
    "stone_brick_wall[east=none,south=none,north=none,up=true,west=tall]": "minecraft:polished_blackstone_brick_wall[east=none,north=none,south=none,up=true,west=tall]",
    "stone_brick_wall[east=none,north=none,south=tall,up=true,west=none]": "minecraft:polished_blackstone_brick_wall[east=none,north=none,south=tall,up=true,west=none]"
}
'''
