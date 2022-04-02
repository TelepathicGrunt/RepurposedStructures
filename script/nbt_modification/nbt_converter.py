import python_nbt.nbt as nbt
from pathlib import Path
import collections.abc
import os

# https://pypi.org/project/Python-NBT/
'''
  "red_nether_brick": "oak",
    "nether_brick": "oak",
    "polished_blackstone_brick": "spruce",
    "redstone_": "",
    "red_stained_glass": "glass",
    "city/nether": "city/overworld",
    "cities/nether": "cities/overworld"

    "minecraft:red_wall_banner": "minecraft:brown_wall_banner",
    "minecraft:crimson_hyphae": "minecraft:oak_wood",
    "minecraft:red_nether_bricks": "minecraft:oak_wood",
    "minecraft:polished_blackstone_bricks": "minecraft:oak_planks"
'''
#--------------------------------------------------------------------------------------------

blockPalette = {""}

originalBiome = ""
newBiome = ""
string_blacklist = []
conversion_partial_dict = {
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


for x in blockPalette:
  print(x)

print("FINISHED!")
input()


#------------------------------------------------------------------------

'''
# Caves and Cliffs backporting
conversion_partial_dict = {
}
conversion_exact_dict = {
    "minecraft:moss_carpet": "cavesandcliffs:moss_carpet",
    "minecraft:moss_block": "cavesandcliffs:moss_block",
    "minecraft:spore_blossom": "cavesandcliffs:spore_blossom",
    "minecraft:small_dripleaf": "cavesandcliffs:small_dripleaf",
    "minecraft:purple_candle": "cavesandcliffs:purple_candle",
    "minecraft:magenta_candle": "cavesandcliffs:magenta_candle",
    "minecraft:cracked_deepslate_bricks": "cavesandcliffs:cracked_deepslate_bricks",
    "minecraft:deepslate_bricks": "cavesandcliffs:deepslate_bricks",
    "minecraft:polished_deepslate": "cavesandcliffs:polished_deepslate",
    "minecraft:chiseled_deepslate": "cavesandcliffs:chiseled_deepslate",
    "minecraft:deepslate": "cavesandcliffs:deepslate",
    "minecraft:cobbled_deepslate": "cavesandcliffs:cobbled_deepslate",
    "minecraft:small_amethyst_bud": "cavesandcliffs:small_amethyst_bud",
    "minecraft:medium_amethyst_bud": "cavesandcliffs:medium_amethyst_bud",
    "minecraft:large_amethyst_bud": "cavesandcliffs:large_amethyst_bud",
    "minecraft:amethyst_cluster": "cavesandcliffs:amethyst_cluster",
    "minecraft:amethyst_block": "cavesandcliffs:amethyst_block",
    "minecraft:copper_block": "cavesandcliffs:copper_block",
    "minecraft:exposed_copper": "cavesandcliffs:exposed_copper",
    "minecraft:weathered_copper": "cavesandcliffs:weathered_copper",
    "minecraft:oxidized_copper": "cavesandcliffs:oxidized_copper",
    "minecraft:cut_copper": "cavesandcliffs:cut_copper",
    "minecraft:exposed_cut_copper": "cavesandcliffs:exposed_cut_copper",
    "minecraft:weathered_cut_copper": "cavesandcliffs:weathered_cut_copper",
    "minecraft:oxidized_cut_copper": "cavesandcliffs:oxidized_cut_copper",
    "minecraft:waxed_copper_block": "cavesandcliffs:waxed_copper_block",
    "minecraft:waxed_exposed_copper": "cavesandcliffs:waxed_exposed_copper",
    "minecraft:waxed_weathered_copper": "cavesandcliffs:waxed_weathered_copper",
    "minecraft:waxed_oxidized_copper": "cavesandcliffs:waxed_oxidized_copper",
    "minecraft:waxed_cut_copper": "cavesandcliffs:waxed_cut_copper",
    "minecraft:waxed_exposed_cut_copper": "cavesandcliffs:waxed_exposed_cut_copper",
    "minecraft:waxed_weathered_cut_copper": "cavesandcliffs:waxed_weathered_cut_copper",
    "minecraft:waxed_oxidized_cut_copper": "cavesandcliffs:waxed_oxidized_cut_copper",
    "minecraft:raw_copper_block": "cavesandcliffs:raw_copper_block",
    "minecraft:raw_gold_block": "cavesandcliffs:raw_gold_block",
    "minecraft:raw_iron_block": "cavesandcliffs:raw_iron_block",
    "minecraft:budding_amethyst": "cavesandcliffs:budding_amethyst",
    "minecraft:deepslate_redstone_ore": "cavesandcliffs:deepslate_redstone_ore",
    "minecraft:deepslate_gold_ore": "cavesandcliffs:deepslate_gold_ore",
    "minecraft:deepslate_diamond_ore": "cavesandcliffs:deepslate_diamond_ore",
    "minecraft:deepslate_emerald_ore": "cavesandcliffs:deepslate_emerald_ore",
    "minecraft:deepslate_iron_ore": "cavesandcliffs:deepslate_iron_ore",
    "minecraft:deepslate_coal_ore": "cavesandcliffs:deepslate_coal_ore",
    "minecraft:deepslate_lapis_ore": "cavesandcliffs:deepslate_lapis_ore",
    "minecraft:deepslate_copper_ore": "cavesandcliffs:deepslate_copper_ore",
    "minecraft:deepslate_tiles": "cavesandcliffs:deepslate_tiles",
    "minecraft:cracked_deepslate_tiles": "cavesandcliffs:cracked_deepslate_tiles",
    "minecraft:cobbled_deepslate_slab": "cavesandcliffs:cobbled_deepslate_slab",
    "minecraft:polished_deepslate_slab": "cavesandcliffs:polished_deepslate_slab",
    "minecraft:deepslate_brick_slab": "cavesandcliffs:deepslate_brick_slab",
    "minecraft:deepslate_tile_slab": "cavesandcliffs:deepslate_tile_slab",
    "minecraft:cobbled_deepslate_stairs": "cavesandcliffs:cobbled_deepslate_stairs",
    "minecraft:polished_deepslate_stairs": "cavesandcliffs:polished_deepslate_stairs",
    "minecraft:deepslate_brick_stairs": "cavesandcliffs:deepslate_brick_stairs",
    "minecraft:deepslate_tile_stairs": "cavesandcliffs:deepslate_tile_stairs",
    "minecraft:cobbled_deepslate_wall": "cavesandcliffs:cobbled_deepslate_wall",
    "minecraft:polished_deepslate_wall": "cavesandcliffs:polished_deepslate_wall",
    "minecraft:deepslate_brick_wall": "cavesandcliffs:deepslate_brick_wall",
    "minecraft:deepslate_tile_wall": "cavesandcliffs:deepslate_tile_wall",
    "minecraft:copper_ore": "cavesandcliffs:copper_ore",
    "minecraft:big_dripleaf_stem": "cavesandcliffs:big_dripleaf_stem",
    "minecraft:big_dripleaf": "cavesandcliffs:big_dripleaf",
    "minecraft:dripstone_block": "cavesandcliffs:dripstone_block",
    "minecraft:glow_lichen": "cavesandcliffs:glow_lichen",
    "minecraft:infested_deepslate": "cavesandcliffs:infested_deepslate",
    "minecraft:hanging_roots": "cavesandcliffs:hanging_roots",
    "minecraft:pointed_dripstone": "cavesandcliffs:pointed_dripstone",
    "minecraft:potted_azalea_bush": "cavesandcliffs:potted_azalea_bush",
    "minecraft:potted_flowering_azalea_bush": "cavesandcliffs:potted_flowering_azalea_bush",
    "minecraft:powder_snow": "cavesandcliffs:powder_snow",
    "minecraft:rooted_dirt": "cavesandcliffs:rooted_dirt",
    "minecraft:smooth_basalt": "cavesandcliffs:smooth_basalt",
    "minecraft:tuff": "cavesandcliffs:tuff",
    "minecraft:amethyst_shard": "cavesandcliffs:amethyst_shard",
    "minecraft:bundle": "cavesandcliffs:bundle",
    "minecraft:copper_ingot": "cavesandcliffs:copper_ingot",
    "minecraft:glow_berries": "cavesandcliffs:glow_berries",
    "minecraft:cave_vines": "cavesandcliffs:cave_vines",
    "minecraft:cave_vines_plant": "cavesandcliffs:cave_vines_plant",
    "minecraft:glow_ink_sac": "cavesandcliffs:glow_ink_sac",
    "minecraft:powder_snow_bucket": "cavesandcliffs:powder_snow_bucket",
    "minecraft:raw_copper": "cavesandcliffs:raw_copper",
    "minecraft:raw_gold": "cavesandcliffs:raw_gold",
    "minecraft:raw_iron": "cavesandcliffs:raw_iron",
    "minecraft:spyglass": "cavesandcliffs:spyglass",

    "minecraft:dirt_path": "minecraft:grass_path",
    "minecraft:water_cauldron": "minecraft:cauldron"
}

'''


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
