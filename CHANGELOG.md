### **(V.3.0.1 Changes) (1.18.1 Minecraft)**

#### Cities:
Changed default config value for Overworld Cities from 1200 to 700.
  You'll have to edit the config yourself if you already started game with older v4.2.X versions of RS.

Improved Overworld City's resistance to lightning from thunderstorms.

Adjusted insides of two rooms in Overworld City to look a tad better.

Fixed some top pieces not spawning in Overworld Cities because of bad logic in my required/maximum count piece controlling code.

Fixed Overworld Cities not checking properly to not spawn near any RS or Vanilla Mansions.

Fixed Bridge End pieces not spawning sometimes in Overworld and Nether Cities.

Nether Cities now always have 3 branches at minimum.

#### Outposts:
RS Outposts will not spawn near Overworld Cities now.

#### Ruins:
Cold and Icy Land Ruins will not let lake features spawn in them now.

#### Misc:
Fixed some processors that were placing blocks multiple times.

Fixed required/maximum count piece controlling code (for rs_pieces_spawn_counts) so it now limits amount of pieces properly and not cause weird behavior.


### **(V.3.3.0 Changes) (1.18.1 Minecraft)**

#### Lang:
Added en_us entries for explorer maps to any RS structures.

#### Cities:
Added Overworld City! If you are lucky, you can find a map to one from a Wandering Trader! Very expensive tho...

May have fixed a bug that causes a very very rare chance of Nether Cities missing top pieces on their tower piece.

Replaced some full blocks with stairs in one of Nether City's steep bridge piece.

#### Pyramids:
Added Dark Forest Pyramid!

#### Ruins:
Added Cold Land Ruins and Icy Land Ruins!

Made the config default value for the spawnrate of Warm Land Ruins and Hot Land Ruins slightly less.

#### Igloos:
Added Mushroom Igloos!

Made the config default value for the spawnrate of Grassy Igloo and Stone Igloo slightly less.

#### Witch Huts:
Expanded RS Witch Hit bounding boxes 2 blocks higher to better match vanilla's Witch Hut bounding box for spawning Witches and black Cats.

#### Villages:
Slightly reduced cost of maps to Mushroom Villages in Wandering Trader's trades.

#### Mineshafts:
Fixed Birch Mineshaft not closing off fluids properly for some pieces.

RS Mineshaft's 4 way piece keeps columns now.

#### Misc:
Fixed some structures not properly avoiding water.

Changed all RS ConfiguredStructure registry names to match the base structure registry names.
  This has no impact on player's worlds. It is safe. This is only for internal use or people creating their own datapacks replacing my structures for whatever reason.

RS Structures will not spawn now if they will get cut off by top of world's limit.

#### Mod Compat:
Fixed modded loot not being imported into some of RS chests.


### **(V.3.2.0 Changes) (1.18.1 Minecraft)**

#### Dungeons:
Deep Dungeons are now added that spawn from -10 to -60. Will replace Vanilla Dungeons below y = 0.

Nether Dungeon's secret banner is now red colored while Deep Dungeon has the dark grey banner instead.

#### Mineshafts:
RS Mineshaft arches should not be broken up weirdly as much anymore.

Fixed several RS Mineshafts having 1 incorrectly rotated arch block.

Fixed some arches placing incorrect blocks in RS Mineshafts.


### **(V.3.1.13 Changes) (1.18.1 Minecraft)**

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

Optimized the Jigsaw Blocks attaching part of my structures layout generation a bit.


### **(V.3.1.12 Changes) (1.18.1 Minecraft)**

#### Misc:
Fixed incompatibility with Cyanide mod for good. Hopefully.

Locating stuff like Locate Command or Explorer Maps will now no longer spend more than 30 seconds trying to find an RS structure.
 This time before aborting the locating can be increased or decreased by editing the locateMaxTime config in the Misc section of my config.


### **(V.3.1.11 Changes) (1.18.1 Minecraft)**

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


### **(V.3.1.10 Changes) (1.18.1 Minecraft)**

#### Misc:
Fixed issue where adding a rs_pieces_spawn_counts entry in a datapack that does not have a required piece would make the RS structure not fully generate.
 Now the 1.18.1 More Villagers Datapack works properly: https://www.curseforge.com/minecraft/texture-packs/repurposed-structures-more-villagers-datapack


### **(V.3.1.9 Changes) (1.18.1 Minecraft)**

#### Misc:
Hard require 1.18.1 Minecraft and 0.12.9 Fabric Loader to help keep people safer.

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


### **(V.3.1.8 Changes) (1.18.0 Minecraft)**

#### Configs:
Refined RS config default entries for Terralith compat

#### Misc:
Fixed issue where several structures could be placed wrong if world height is changed significantly in the dimension.


### **(V.3.1.7 Changes) (1.18.0 Minecraft)**

#### Configs:
Fix the autoupdating for configs to have best compat with Terralith not working


### **(V.3.1.6 Changes) (1.18.0 Minecraft)**

#### Misc:
Fixed issue with deep copying the Structure Settings in chunk generators. (This is needed to allow RS's dimension allow/disallow configs to work) 
 This was keeping AE2 meteors from spawning. 


### **(V.3.1.5 Changes) (1.18.0 Minecraft)**

#### Configs:
Autoupdated configs to have best compat with Terralith.

#### Outposts:
Fixed Snowy and Icy Outposts spawning in non-cold biomes by mistake. Should be better now.

#### Pyramids:
Fixed Snowy and Icy Pyramids spawning in non-cold biomes by mistake. Should be better now.

#### Dungeons:
Fixed Snowy and Icy Dungeons spawning in wrong biomes by mistake. Should be better now.

Fixed dungeons not being affected by biome allow/disallow config.

#### Mansions:
Fixed multiple kinds of RS Mansions unable to spawn in same biome when using config to force them into same biome.

#### Wells:
Fixed wells not being affected by biome allow/disallow config.

Fixed multiple kinds of RS wells unable to spawn in same biome when using config to force them into same biome.


### **(V.3.1.4 Changes) (1.18.0 Minecraft)**

#### Wells:
Fixed Nether Well using Forest Wells's config value by mistake.

#### Dungeons:
Fixed default config value for Badlands Dungeon's min y value being 2 instead of 35.

#### Mineshafts:
Fixed some default config values for minecraft sizes not being correct.

#### Villages:
Reduced amount of candles in Mushroom Villages.

#### Misc:
Fixed an error reporting crashing when trying to print info about a crash about to happen in RS.


### **(V.3.1.3 Changes) (1.18.0 Minecraft)**

#### Misc:
Fixed fabric.mod.json being incorrect and requiring 1.18.1 or higher instead of 1.18.0 or higher. Oops.


### **(V.3.1.2 Changes) (1.18.0 Minecraft)**

#### Mineshafts:
Overworld RS Mineshafts are now lowered to always be below top land instead of spawning above land and not place any blocks.

#### Misc:
Updated mod's info to make it run only on 1.18.0 and newer.

Fixed codec for pool_additions files so it allows weights up to 5000 instead of just to 150.


### **(V.3.1.1 Changes) (1.18-rc3 Minecraft)**

#### Igloos:
Grassy Igloo and Stone Igloo basements and ladder pieces has properly randomized stone brick variant blocks and randomized infested blocks.
 Amount of infested stone brick blocks was reduced a bit.

#### Mansions:
Icy Mansion pieces now has Polished Diorite under floor carpet to make traversing the mansion easier.

#### Misc:
Fixed logspam that may appear saying setblock out of bounds due to some PlacedFeatures having randomization inside their placements.
 Also fixed some PlacedFeature's spawn positions and rates in RS structures.

#### Misc:
Added conditions to entries in rs_pieces_spawn_counts and rs_pieces_spawn_counts_additions files so mods can register a condition to
 "repurposed_structures:json_conditions" registry and have their entries only apply if certain configs are on.

Conditions entry is also added to pool_additions json files and an option too.


### **(V.3.1.0 Changes) (1.18-rc3 Minecraft)**

#### Misc:
rs_spawner's json file now uses floats instead of ints so you can do fractional weights for mob spawner's mob type rates if you desire.

rs_pieces_spawn_counts folder is now added that lets you control what pieces in RS structures should always spawn and limit how often a piece spawns.

rs_pieces_spawn_counts_additions folder is created to make it easier for me to create RS datapacks with new pieces and have them not spawn excessively.

Improved error message a tad when a required structure piece fails to generate.

#### Dungeons:
Fixed dungeons spawners not being randomized properly.

#### Fortresses:
Jungle Fortress mushroom stairs room has slightly reduced amount of mushrooms.


### **(V.3.0.2 Changes) (1.18-rc3 Minecraft)**

#### Major:
Now works with modded biomes and the biome allow/disallow configs now works again. Enjoy!

#### Mineshafts:
Slightly buffed rates of Minecarts in all Overworld Mineshafts.

Nerfed Minecart rate in Icy Mineshafts to match rate of other Overworld Mineshafts.

Nerfed End Mineshaft's Minecart rates a bit to balance it a bit better.

#### Strongholds:
End Strongholds have slightly less loot Shulker Boxes and slightly less Shulker mobs to 
 help reduce amount of Shulker boxes a bit that can be collected from this structure.


### **(V.3.0.1 Changes) (1.18-rc3 Minecraft)**

#### Misc:
Cleaned up code for injecting structures into biomes. Will pave the way for bringing back mod compat with modded biomes.


### **(V.3.0.0 Changes) (1.18-pre7 Minecraft)**

#### Major:
Ported to 1.18-pre7

NOTE: RS structures will not be added to modded biomes and the biome allow/disallow configs do not work. 
 This is due to Fabric API's BiomeModificationAPI not working for structures at the moment.

#### Mineshafts:
Will no longer replace Vanilla Mineshafts. Will spawn just under the surface of biomes and some variants may spawn way up in mountains.

Slightly improved look of Desert and Icy Dungeons

#### Dungeons:
Will no longer replace Vanilla Dungeons. Will only spawn above y = 45 in Overworld (Ocean Dungeon spawns lower to be under ocean floor)

Slightly improved spawner placement in Ocean Dungeons.

#### Bastions:
Underground Bastions spawn lower into deepslate area and uses Deepslate Iron Blocks in their Unit rooms.