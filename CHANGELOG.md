### **(V.4.1.0 Changes) (1.18.1 Minecraft)**

#### Dungeons:
Deep Dungeons are now added that spawn from -10 to -60. Will replace Vanilla Dungeons below y = 0.

Nether Dungeon's secret banner is now red colored while Deep Dungeon has the dark grey banner instead. 

Fixed Icy Dungeon configs not showing up.

#### Mineshafts:
RS Mineshaft arches should not be broken up weirdly as much anymore.

Fixed several RS Mineshafts having 1 incorrectly rotated arch block.

Fixed some arches placing incorrect blocks in RS Mineshafts.


### **(V.4.0.10 Changes) (1.18.1 Minecraft)**

#### Configs:
Changed the default config spawnrate for Mushroom Wells, Mushroom Villages, and Mushroom Pyramids to be more rare.
 This is because Mushroom biomes are larger in 1.18.

#### Mineshafts:
Tried improving Mineshafts to have a bit less broken arches. More work may be done in the future for the other RS Mineshaft arches not affected by this change.

Mineshafts will try to make certain they do not place a ton of blocks in view of the sky even in rivers and oceans.

Attempt to try and make Mineshaft pillars and chains spawn better and 4 way pieces will now replace air to look better in caves.

Fixed Birch Mineshaft stairs not closing off fluids properly.

#### Outposts:
Nerfed amount of loot from End Outpost's Shulker Boxes and made armor/swords more uncommon.

#### Pyramids:
End Pyramid chests spawns slightly less Ender Pearls. 

#### Strongholds:
In End Strongholds, reduce amount of loot Shulker Boxes being spawn just a bit.

#### Misc:
Optimized structure bounds checking for my mixins that prevents some features from spawning inside my structures.

Optimized the Jigsaw Blocks attaching part of my structure layout generation a bit.


### **(V.4.0.9 Changes) (1.18.1 Minecraft)**

#### Misc:
Fixed incompatibility with Cyanide mod for good. Hopefully.

Locating stuff like Locate Command or Explorer Maps will now no longer spend more than 30 seconds trying to find an RS structure.
  This time before aborting the locating can be increased or decreased by editing the locateMaxTime config in the repurposed_structures-forge/misc.toml file.


### **(V.4.0.8 Changes) (1.18.1 Minecraft)**

#### Configs:
Corrected configs to say that RS Mineshafts and Dungeons will NOT replace vanilla's.

#### Mineshafts:
Nether Mineshafts can exposed Ancient Debris again now.

Wall blocks in Mineshafts will connect to top wall properly now.

Mineshafts will be sunken into terrain a bit better to reduce destruction of surfaces.

#### Lang:
With special help from others, ko_kr.json is added and zh_cn.json is improved!

#### Misc:
Bumped mixin compat level to java 17.

Fixed a potential incompatibility with Cyanide mod.


### **(V.4.0.7 Changes) (1.18.1 Minecraft)**

#### Misc:
Fixed rs_pieces_spawn_counts resourcelistener not actually hooked up.
 This is required for specific RS datapacks to work properly and restrict how many added buildings can spawn in a single village to prevent overcrowding.
 Example of this is the 1.18.1 More Villagers Datapack: https://www.curseforge.com/minecraft/texture-packs/repurposed-structures-more-villagers-datapack
 This fix will make sure that datapack does not spawn more than 1 florist house in a single RS village.


### **(V.4.0.6 Changes) (1.18.1 Minecraft)**

#### Misc:
Hard require 1.18.1 MC and 39.0.0 Forge to help keep people safer.

#### Cities:
Fixed Nether City having some rooms with an empty chest instead of having loot.

Nerfed rate of Netherite Ingot quite a bit from Nether Cities loot.

#### Fortresses:
Jungle Fortress loot now has its tools and armor slightly damaged. The extremely rare diamond tools/armor is usually heavily damaged.

#### Villages:
Fixed some Zombified Piglins not spawning in Zombified Crimson/Warped Villages.

Crimson/Warped Villages's tannery building now has Lava Cauldrons.

#### Dungeons:
Added rare chance of Otherside Music Disc to all RS Dungeons loot tables.

#### Strongholds:
Added rare chance of Otherside Music Disc to all RS Strongholds's hallway loot table.

#### Bastions:
Underground Bastion's bridge, other, and skeleton horse stable chest loot tables now has a tiny chance for Amethyst Shards.


### **(V.4.0.5 Changes) (1.18.0 Minecraft)**

#### Configs:
Refined RS config default entries for Terralith compat

#### Misc:
Fixed issue where several structures could be placed wrong if world height is changed significantly in the dimension.

Fixed several Nether Structures being set too low and even inside the lava ocean.


### **(V.4.0.4 Changes) (1.18.0 Minecraft)**

#### Configs:
Fix the autoupdating for configs to have best compat with Terralith not working


### **(V.4.0.3 Changes) (1.18.0 Minecraft)**

#### Misc:
Fixed issue with deep copying the Structure Settings in chunk generators. 
 (This is needed to allow RS's dimension allow/disallow configs to work)


### **(V.4.0.2 Changes) (1.18.0 Minecraft)**

#### Configs:
Autoupdated configs to have best compat with Terralith.

#### Outposts:
Fixed Snowy and Icy Outposts spawning in non-cold biomes by mistake. Should be better now.

#### Pyramids:
Fixed Snowy and Icy Pyramids spawning in non-cold biomes by mistake. Should be better now.

#### Dungeons:
Fixed Snowy and Icy Dungeons spawning in wrong biomes by mistake. Should be better now.

Fixed multiple kinds of RS dungeons unable to spawn in same biome when using config to force them into same biome.

#### Mansions:
Fixed multiple kinds of RS Mansions unable to spawn in same biome when using config to force them into same biome.


### **(V.4.0.1 Changes) (1.18.0 Minecraft)**

#### Misc:
Fixed pool weight increase mixin not applying outside of dev.

#### Dungeons:
Fixed Ocean Dungeons not spawning because default min y config value was 205 instead of 20. Oops.
 If you already launched the game in 1.18 with v4.0.0 RS, you'll have to fix the Ocean Dungeon's config entry yourself.


### **(V.4.0.0 Changes) (1.18.0 Minecraft)**

##### Major:
Ported to 1.18 and is in parity to 1.18 fabric version.

#### Configs:
Cleaned up, renamed a bit, and many range restrictions on some values have been removed.

#### Mineshafts:
Will no longer replace Vanilla Mineshafts. Will spawn just under the surface of biomes and some variants may spawn way up in mountains.

Slightly improved look of Desert and Icy Dungeons

Slightly buffed rates of Minecarts in all Overworld Mineshafts.

Nerfed Minecart rate in Icy Mineshafts to match rate of other Overworld Mineshafts.

Nerfed End Mineshaft's Minecart rates a bit to balance it a bit better.

#### Igloos:
Grassy Igloo and Stone Igloo basements and ladder pieces has properly randomized stone brick variant blocks and randomized infested blocks.
  Amount of infested stone brick blocks was reduced a bit.

#### Mansions:
Icy Mansion pieces now has Polished Diorite under floor carpet to make traversing the mansion easier.

#### Strongholds:
End Strongholds have slightly less loot Shulker Boxes and slightly less Shulker mobs to
  help reduce amount of Shulker boxes a bit that can be collected from this structure.

#### Dungeons:
Will no longer replace Vanilla Dungeons. Will only spawn above y = 45 in Overworld (Ocean Dungeon spawns lower to be under ocean floor)

Slightly improved spawner placement in Ocean Dungeons.

#### Fortresses:
Jungle Fortress mushroom stairs room has slightly reduced amount of mushrooms.

#### Bastions:
Underground Bastions spawn lower into deepslate area and uses Deepslate Iron Blocks in their Unit rooms.

#### Misc:
rs_spawner's json file now uses floats instead of ints so you can do fractional weights for mob spawner's mob type rates if you desire.

rs_pieces_spawn_counts folder is now added that lets you control what pieces in RS structures should always spawn and limit how often a piece spawns.

rs_pieces_spawn_counts_additions folder is created to make it easier for me to create RS datapacks with new pieces and have them not spawn excessively.

Added conditions to entries in rs_pieces_spawn_counts and rs_pieces_spawn_counts_additions files so mods can register a condition to
  "repurposed_structures:json_conditions" registry and have their entries only apply if certain configs are on.

Conditions entry is also added to pool_additions json files and an option too.

Improved error message a tad when a required structure piece fails to generate.
