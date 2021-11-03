import os
import errno

conversion_dict = {
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
    "minecraft:flowering_azalea": "cavesandcliffs:flowering_azalea",
    "minecraft:azalea": "cavesandcliffs:azalea",
    
    "repurposed_structures:bastions/underground": "repurposed_structures:bastions_underground",
    "repurposed_structures:mineshafts/swamp": "repurposed_structures:mineshaft_swamp",
    "minecraft:dirt_path": "minecraft:grass_path",
    "1.0E-4": "0.0001"
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