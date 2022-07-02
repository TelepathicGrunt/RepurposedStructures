### **(V.6.0.8 Changes) (1.19.0 Minecraft)**

#### Village:
Fixed Badlands Village center piece spawning at y = 0 due to missing heightmap check in its structure json file.


### **(V.6.0.7 Changes) (1.19.0 Minecraft)**

#### Mansions:
Added Allays to RS mansion prison pieces. Each prison room cell can spawn 1 to 3 Allays.

#### Outposts:
Added Allays to RS outpost cage pieces. The Allay cages have a chance of spawning.

Added possible "Singing" Goat Horn to all RS Outpost loot tables except for End Outpost.

#### Ruins:
Made Overworld Ruins more spread out and uncommon instead of everywhere.

#### Misc:
Added `repurposed_structures:no_waterfalls` tag to prevent waterfalls in Snowy Mansion, Snowy Pyramid, and Snowy Outposts.


### **(V.6.0.6 Changes) (1.19.0 Minecraft)**

#### Processors:
Significantly improved my spawner_randomizing_processor so it now can specify all nbt for spawners.
This will be utilized to make RS be better with spawner behavior.
Currently, all overworld structures with spawners now will let their mobs spawn up to light level 7 to make
the mobs spawn more consistently in the presence of nearby light sources that spawns within RS structures.

#### Mineshafts:
Fixed a typo in several rs_pieces_spawn_counts files with Mineshafts.
Now Mineshafts should always have 1 spawner tunnel.
Config datapack is updated with this fix as well.

#### Misc:
Fixed a bug where some of my rng stuff was not being randomized properly at y = 0.


### **(V.6.0.5 Changes) (1.19.0 Minecraft)**

#### Misc:
Fixed Minecraft and Pneumaticcraft tags in my mod to use the correct path so they actually work now.
Config datapack is updated with this fix too.

#### Dungeons:
Significantly nerfed the chances of Vex spawners in Swamp Dungeons and Dark Forest Dungeons.
They are now at 1% chance for the spawners to be a vex spawner instead of like 16% in the past.
Override the rs_spawner files in the config datapack to remove them entirely if you don't want them at all.


### **(V.6.0.4 Changes) (1.19.0 Minecraft)**

#### Dungeons:
Changed dungeons to use `repurposed_structures:unlimited_count` so you can set dungeon spawnrates insanely high (like a thousand or so)
Config datapack is updated with these changes.

Fixed Ocean Dungeons not spawning.

#### Wells:
Added `repurposed_structures:unlimited_count` to wells to make turning them off easier (just set count to 0)
Config datapack is updated with these changes.

#### Bastions:
Allow Underground Bastions to spawn in Deep Dark biome and increased range of structure check to prevent it from spawning in Ancient Cities.

#### Villages:
Dark Forest Village now has Red and Black Beds instead of White and Yellow Beds.

Mountain Villages now spawns Stone Brick Stairs instead of Cobblestone Stairs at various spots.

#### Mod Compat:
Added more Terralith biomes to RS biome tags for RS structures to spawn in.


### **(V.6.0.3 Changes) (1.19.0 Minecraft)**

#### Dungeons:
Fixed RS Dungeons not spawning. I got no one to blame but myself.

#### Wells:
Fixed RS Wells not spawning.

#### Mineshafts:
Fixed End Mineshaft's avoid structure set tag being misnamed and thus, broken.

#### Lang:
ru_ru.json lang file updated by DrHesperus!


### **(V.6.0.2 Changes) (1.19.0 Minecraft)**

#### Misc:
Fixed crash when Waystone mod is on or RS Compat Datapacks are on.


### **(V.6.0.1 Changes) (1.19.0 Minecraft)**

#### Loot Tables:
Fixed modded loot importing mixin to not nuke loot tables by mistake... oops.

#### Pyramids:
Fixed Pyramids spawning underwater when they are land pyramids (hopefully got this fixed)


### **(V.6.0.0 Changes) (1.19.0 Minecraft)**

#### Major:
Updated to 1.19! Beta as there could be issues. Please report anything funky or broken!
Note: previous config datapacks will not work as many changes were done to structure json by Mojang.
The updated RS Config datapack for 1.19 will be posted here: https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/releases/tag/2.0.1

Version 5.x.x is skipped to 6.0.0 so this fabric version syncs up with the forge versioning.
Makes it easier to see who's up-to-date at a glance in logs.

#### Config:
The Dungeons and Wells config files that used to be in the mod config folder are now deleted.
Instead, they are now made with json and can be disabled by editing the config datapack listed above.

#### Dungeons:
Fixed End Dungeons always having 2 Shulker Boxes. Now it's typically 1 but can be 0 or 2 at times.

#### Mineshafts:
Swamp Mineshaft now uses blocks from Mangrove Swamp and will spawn in that biome.

#### Villages:
Swamp Village now uses blocks from Mangrove Swamp and will spawn in that biome.


### **(V.4.1.14 Changes) (1.18.2 Minecraft)**

#### Config:
Fixed repurposed_structures-fabric/biome_dimension_allow_disallow_configs.json5 config file so it actually applies to RS dungeons.
Now the config should work properly... It's been broken for a few months and no one reported it till now...............


### **(V.4.1.13 Changes) (1.18.2 Minecraft)**

#### Lang:
Added uk_ua lang created by Kezunya. Special thanks to them!


### **(V.4.1.12 Changes) (1.18.2 Minecraft)**

#### Mod Compat:
Added Betterend and Betternether biomes to the biome tags that my mod uses so my structures spawns in their biomes.

Added Magical Forest's biome to the forest biome tags that my mod uses so my structures spawns in their biomes.

Added ProjectVibrantJourney's biomes to the biome tags that my mod uses so my structures spawns in their biomes.
(In case they port to Fabric)

#### Mineshafts:
Lowered Crimson, Warped, and Nether Mineshaft's max y value their center room can spawn at. (Lowers entire mineshaft by 4 blocks)
Config datapack is updated with this change now.


### **(V.4.1.11 Changes) (1.18.2 Minecraft)**

#### Mineshafts:
Fixed Desert and Swamp Mineshafts not having pillars/chains support.
(Config datapack now updated to include the small changes for this fix)

#### Misc:
Slightly improved the error message that shows when a RS structure fails to generate with certain required pieces.


### **(V.4.1.10 Changes) (1.18.2 Minecraft)**

#### Config:
Autoupdate the dimension blacklist config entry for RS's dungeons and wells so that it has `twilightforest:twilight_forest`
This will make RS dungeons and wells no longer spawn in Twilight Forest's dimension when they port to Fabric.


### **(V.4.1.9 Changes) (1.18.2 Minecraft)**

#### Locate:
/locate command mixin that RS does now only increase the search radius for structures under the
`repurposed_structures:larger_locate_search` configured structure feature tag.
Has RS's Mineshafts by default and will make the command's search radius go from 100 structure region radius to 2000 radius.
With this tag, the command should not time out as often when locating other mod's/datapack's structures that are having issues spawning.
Config datapack updated to include this new tag.


### **(V.4.1.8 Changes) (1.18.2 Minecraft)**

#### End Structures:
End Pyramid, End Shipwreck, End Mineshaft, End Outpost, and End Ruined Portal all now generate only past 1000 blocks from the world center in the End.

#### Dungeons:
Set the default value for Ocean Dungeon's spawnrate from 3 to 2 in config/repurposed_structures-fabric/general_configs.json5
(The in-game config menu will show the new default value as well)


### **(V.4.1.7 Changes) (1.18.2 Minecraft)**

#### Outposts:
Fixed Warped Outposts being very rare.

#### Shipwrecks:
Fixed Crimson Shipwreck being very rare.

#### Nether Structures:
Slightly decreased the spawnrates of all Nether Outposts, Nether Pyramid, Nether Ruins,
Nether Shipwrecks, Nether Temples, Nether Strongholds, Nether Mineshafts, and Nether Villages.
Hopefully this helps make the Nether a little less crowded.

#### Villages:
Fixed Badland Village's trapdoor plant pots to have grass block/podzol inside instead of air.

Added stable_1 building to spawn in non-zombied Badlands Villages rarely.

Dark Forest Village Churches and Stable buildings has some Granite Stairs/Slabs replaced with Dark Oak Stairs/Slabs

Swamp Villages overall has their Moss Blocks on ground replaced with various terracotta blocks.
Moss Block and Moss Carpet has been removed from the outside of the Swamp Village's mud igloo houses and instead, has Moss Carpet on floor inside.

Fixed Swamp Villages spawning Structure Void blocks sometimes.

#### Lang:
it_it.json lang file added by Zano1999! Thank you!


### **(V.4.1.6 Changes) (1.18.2 Minecraft)**

#### Misc:
Fixed crash when certain features are spawned outside of worldgen.
(Made some mixins safer and only apply their changes when during worldgen)

Fixed mixins that reduces amount of jungle bushes in some RS structures.

Fixed mixins that removes lava lakes from spawning in some RS structures.


### **(V.4.1.5 Changes) (1.18.2 Minecraft)**

#### Map Trades:
Fixed crash with Villager mods that add Villagers with more than 5 trade tiers.


### **(V.4.1.4 Changes) (1.18.2 Minecraft)**

#### Biome Tags:
Fixed "#byg:is_icy" entry in repurposed_structures:collections/icy biome tag so it includes icy byg biomes properly.


### **(V.4.1.3 Changes) (1.18.2 Minecraft)**

#### Biome Tags:
Fixed the biome tags that RS uses so it now spawns structures in modded/datapack biomes that are using forge's or c's namespaced tags.
If you're using an RS config datapack, the new v5 of the config datapack should have the updated data/repurposed_structures/tags/worldgen/biome/collections tags that should work now.


### **(V.4.1.2 Changes) (1.18.2 Minecraft)**

#### Shipwrecks:
Fixed End Shipwrecks so they now actually spawn. Was off by one in calculation for checking for land.


### **(V.4.1.1 Changes) (1.18.2 Minecraft)**

#### Loot:
Fixed all structure maps in RS loot tables (chests and shulker boxes) so that the structure maps actually locates structures now.
Issue was the way explorer maps gets what structure to track was changed in 1.18.2 and I did not realize it.
If you downloaded a config datapack already, replace the loot table folder in it with v4 config datapack's loot table folder.
(Note: structures at the edge of the map's area may not show the icon for the structure's location. This is vanilla behavior. Don't ask why)

#### Shipwrecks:
Fixed End Shipwrecks not spawning.


### **(V.4.1.0 Changes) (1.18.2 Minecraft)**

#### Monuments:
Added Jungle, Icy, Desert, and Nether Monuments! Can be found from Cartographer's map trades too!

#### Tags:
Updated several biome tags so some RS structures can spawn in the correct modded/datapack biomes better.

#### Mod Compat:
Added dedicated compat with importing End Remastered's eyes into correct Repurposed Structures's structures.

The rs_spawner json file's entries now can have `"optional": true` to make the entry no longer error if the mob is not present in the registry.
Good for marking modded mobs as optional when overriding the rs_spawner json files so that you can later remove that mod and not cause the json file to explode.

#### Lang:
Fixed typo in zh_cn.json file that prevented it from loading properly.


### **(V.4.0.2 Changes) (1.18.2 Minecraft)**

#### Configs:
Clarified in the dimension/biome configs that they do not work for RS structures because those are datapack stuff.
These dimension/biome configs only affect Repurposed Structures's Wells and Dungeons.

#### Temples:
Changed up the traps for Wasteland, Crimson, Warped, and Soul Sand Temples.

#### Villages:
Slightly reduced how messy Giant taiga Villages are.

Fixed town center pieces for Swamp Villages being a block too high.

Mountain Villages can spawn in Jagged Peaks biome now.

#### Tags:
Simplified some configured structure feature tags internally.


### **(V.4.0.1 Changes) (1.18.2 Minecraft)**

#### Mineshafts:
Fixed Jungle Mineshafts having too much Cobwebs.

#### Villages:
Optimized finding Mushroom Villages a bit.

#### Tags:
Fixed tags so now Eyes of Ender locates Nether/End Stronghold and Dolphins can find Ocean Pyramids.

Updated tags to include modded biomes much better.


### **(V.4.0.0 Changes) (1.18.2 Minecraft)**

#### BETA:
Please report issues and bugs to me! This version is experimental and for people to test and report any problems.

#### Major:
Ported to 1.18.2 mc and rewritten a massive portion of this mod to use the new json structure system mojang added!
You can now make entirely new structures with datapack!

#### Loot:
Added Ukraine flag banner as a luck based loot to all RS chests.
You can donate to help Ukraine! There are lots of charities! See this for some: https://kyivindependent.com/national/heres-how-to-support-ukrainian-military/

#### Config:
Most configs were deleted and moved to the new json system. This include spawnrates, disabling structures,
what biomes a structure can spawn in, adding mob map trades, and more! There's many new configurations also
available by datapack in the new json system that was previously not avaliable before.

Structures are now based on biome tags for what biomes they spawn in. Please let me know if RS structures are not
spawning in a modded or datapack biome as they will need to add their biomes to specific biome tags.

#### Witch Huts:
All Witch Huts' internal ConfiguredStructure name is changed from `witch_huts_` to now `witch_hut_`.
Plural `witch_huts_` will continue to be used for all other json files.

#### Villages:
Dark Forest Village's internal name was changed from `village_dark_oak` to now `village_dark_forest`.


### **(V.3.3.11 Changes) (1.18.1 Minecraft)**

#### Optimization:
Slightly optimized natural mob spawning over time for RS's structure checks.

#### Config:
Fixed Savanna Mansion maps not showing up as a villager trade due to typo in config.
(Config should auto fix that one typo next time you run the game)


### **(V.3.3.10 Changes) (1.18.1 Minecraft)**

#### Config:
Added config/repurposed_structures-fabric/map_trade_configs.json5 file.
This config lets you add any structure map to any villager or wandering trader's available trades.
Note: Disabling a structure will not remove the map from the trades. You'll have to edit that config to remove the map for the disabled RS structure.


### **(V.3.3.9 Changes) (1.18.1 Minecraft)**

#### Mod Compat:
Deep/Icy Dungeon, Underground Bastion's treasure room chest, and Mushroom Villages now gets imported loot.
Hopefully I got all structures this time.


### **(V.3.3.8 Changes) (1.18.1 Minecraft)**

#### Mineshafts:
Fixed End Mineshaft not spawning Minecart.

Adjusted look of the arch in End Mineshafts.

Fixed Ocean Mineshafts sometimes having Rails that aren't waterlogged.

Fixed Icy Mineshaft having Diorite Walls and not making support pillars/chains.

Fixed Crimson Mineshaft not having fences at times.

Reduces amount of Shroomlight in Crimson Mineshafts.

Reduced amount of pieces in backend that RS Mineshafts need in order to function. Cleaner this way.

Fixed support pillars for RS Mineshafts not replacing plants marked as replaceable that are water based/fire proof.

Tried cleaning up the wall states for Ocean, Desert, Stone, and Swamp Mineshafts.

Randomized removal of some rails in all RS Mineshafts for slightly better looks.


### **(V.3.3.7 Changes) (1.18.1 Minecraft)**

#### Hotfix:
Fixed issue where due to Fabric API 0.46.3+, Repurposed Structures was preventing other structure mod's from spawning.
Now RS is using more of Fabric API's stuff and moves my own code to fix this issue.
As a result, Repurposed Structures now requires Fabric API to be 0.46.3 or newer.


### **(V.3.3.6 Changes) (1.18.1 Minecraft)**

#### Mineshafts:
Fixed Ocean Mineshafts chests accidentally spawning Birch Mineshaft's loot tables


### **(V.3.3.5 Changes) (1.18.1 Minecraft)**

#### Lang:
Updated ru_ru.json lang file. (Special thanks to DrHesperus!)


### **(V.3.3.4 Changes) (1.18.1 Minecraft)**

#### Mineshafts:
Ocean Mineshafts and End Mineshafts will no longer place blocks if it has an unobstructed view of the sky above.

Ocean Mineshaft's default min y config value is now set to 5.

#### Misc:
Tried doing a slight optimization to make sure RS structures do not do any checks for spawning if the world's biome source
cannot spawn a biome that contains said structure. Let me know if any issues arise. (Probably not noticable)

Fixed a rare null crash from my GeneralUtils.isFullCube code that is supposed to cache if a block is a full cube or not.


### **(V.3.3.3 Changes) (1.18.1 Minecraft)**

#### Major:
Fixed system for uploading to curseforge so curseforge gets proper non-dev jar. Sorry about that.


### **(V.3.3.2 Changes) (1.18.1 Minecraft)**

#### Major:
Now hard requires No Null Processor mod and NBT Deadlock Be Gone mod to help prevent issues during chunk pregenning or multiplayer gameplay.
These two mods fixes two nasty rare Minecraft bugs that impact structures/features. But it does affect some people so these two mods should help.

#### Bastions:
Changed default config value for Underground Bastions from 400 to 180.
You'll have to edit the config yourself to the new value if you already started game with any versions of RS older than v3.3.2.

#### Mineshafts:
End Mineshafts loot table no longer has Chorus Flower or Chorus Plant which is not supposed to be obtainable in survival.
Instead, it'll have Beetroot Soup and more Chorus Fruit as replacements.

#### Dungeons:
End Dungeons loot table no longer has Chorus Flower or Chorus Plant which is not supposed to be obtainable in survival.
Instead, it'll have Beetroot Soup and more Chorus Fruit as replacements.

End Dungeon's End Portal block is now replaced with Crying Obsidian.


### **(V.3.3.1 Changes) (1.18.1 Minecraft)**

#### Cities:
Changed default config value for Overworld Cities from 1200 to 180.
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


### **(V.2.5.8 Changes) (1.17.1 Minecraft)**

#### Mod Compat:
Added support for Notify - https://www.curseforge.com/minecraft/mc-mods/notify
Notify will let you know if a new update for this mod is out on the Mod Menu screen.


### **(V.2.5.7 Changes) (1.17.1 Minecraft)**

##### Igloos:
Adjusted code to make sure Grassy Igloos won't put falling blocks like sand as part of the structure's roof.

##### Villages:
Trying something new by increasing the bounding box of all RS Overworld Village's roads from 2 to 32.
This should make villages look better on mountains and hills and help reduce village roads or farms eating away the ground under houses. (In theory)

##### Misc:
This mod now recommends that it is ran with No Null Processors mod on. Especially for pre-genning chunks to prevent a crash.


### **(V.2.5.6 Changes) (1.17.1 Minecraft)**

##### Misc:
Updated internal code to use event priorities instead of a hack around Fabric API.
As a result, Repurposed Structures requires Fabric API v0.42.0 or newer to work.

Reverted mod version checking code to use the deprecated SemanticVersionImpl package.
This will allow RS to run on Fabric Loader v0.11.0 or newer as some modpacks cannot update to v0.12.0+ Fabric Loader.
However, this means that when Fabric Loader updates in the future, they could remove the old code I am using now and cause my mod to crash.
If this happens, PLEASE let me know so I know when it is time for users to be forced to update Fabric Loader and I have to use the new system too.


### **(V.2.5.5 Changes) (1.17.1 Minecraft)**

##### Mod Compat:
Removed Fabric Waystones compat code from RS because Fabric Waystones will do the compat code on their end instead.
Use this version of RS or newer when using Fabric Waystones v2.2.0 or newer.


### **(V.2.5.4 Changes) (1.17.1 Minecraft)**

##### Misc:
Updated fabric.mod.json file to say that Repurposed Structures requires fabric Loader 0.12.0 or newer to work.
Will cut down on confusion due to crashes with outdated Fabric Loader versions.


### **(V.2.5.3 Changes) (1.17.1 Minecraft)**

##### Mod Compat:
Blay's Waystone and Waystones Fabric now cannot spawn more than 1 waystone structure piece in RS villages. I did the restricting on my end.
(Note, Blay Waystones needs to be newer than v8.1.1+0 to add their waystones to RS villages.)
(Note, Fabric Waystones needs to be newer than v2.1.2 to add their waystones to RS mushroom villages.)

Blay Waystone's forceSpawnInVillages config is now read directly by Repurposed Structures and will force the waystones to spawn in RS villages.

Added code to do better compat with Bountiful once it ports to Fabric.


### **(V.2.5.2 Changes) (1.17.1 Minecraft)**

##### Bastions:
In Underground Bastions, Polished Andesite Stairs, Stone Brick Stairs, Stone Brick Slab, and Stone Brick Wall has
been replaced by Polished Deepslate Stairs, Deepslate Brick Slab, and Deepslate Tiled Wall.

##### Mineshafts:
Fixed the structure processor for several RS Mineshafts 4 way pieces to place blocks correctly.
Also makes Swamp Mineshaft look a bit better.


### **(V.2.5.1 Changes) (1.17.1 Minecraft)**

##### Villages:
Fixed a Badlands Village street not spawning houses or decor due to typos in the nbt file.

##### Dungeons:
Fixed Mushroom Dungeons basically never spawning. Oops.

Fixed a missing block from corner of one Snow Dungeon nbt file. (no user would notice any change so idk why I am listing this fix here lol)

##### Outposts:
Changed all RS Outposts to better match Vanilla Outpost that can spawn a ton of tents and cages and stuff in 1.17.
Yeah, Mojang made a change to outposts and no one noticed lol. I don't think it was reported anywhere.


### **(V.2.5.0 Changes) (1.17.1 Minecraft)**

##### Mansions:
All RS Mansions now spawns pieces by using template pools. This means you can edit the pool files by datapack to
remove rooms you do not want to spawn. Or datapack the pools to make structure processors run for mansion pieces to randomize blocks.
Or even datapack using RS's pool_addition folder to inject new rooms into mansions!
Just make sure your new mansion pieces matches the size of the other pieces in the same pool.
No Jigsaw Block needed since Mansions are not Jigsaw Structures.

1x1_b5 room now spawns in RS mansions unlike vanilla which is bugged lol. https://bugs.mojang.com/browse/MC-240121

Adjusted looks of Savanna mansion to make it look nicer.

Fixed vanilla bug in RS mansions where terrain can be found floating on second and third floor hallways of the mansions. https://bugs.mojang.com/browse/MC-107594

Fixed vanilla mansion bug for RS mansions where there can be a hole in the second floor's wall to the outside if there's a 3rd floor above. https://bugs.mojang.com/browse/MC-240221

Fixed vanilla mansion bug for RS mansions where there can be a 3 block high hole on outside wall right side where 2nd floor meets the 3rd floor. https://bugs.mojang.com/browse/MC-110098

##### Outposts:
Crimson, Warped, and Nether Brick Outposts now can have target pieces with either Wither Skeleton Skulls or regular Skeleton Skulls.
The Wither Skeleton Skull piece only has a 23% chance of appearing for a single Outpost. Before, Nether Brick Outpost had a 50% chance of spawning one.

##### Misc:
Adjusted several loot table files.

##### Compat:
Added a mixin to undo a Charm's structure processor for non-charm buildings so that Charm does not break several RS structures that uses Data Mode Structure Blocks.


### **(V.2.4.4 Changes) (1.17.1 Minecraft)**

##### Misc:
Mixin into Basalt Columns will not crash when a broken mod tries to place basalt columns outside the safe 3x3 chunk area for features.
Will instead print into log to go find who is misplacing their basalt columns feature and ask them to fix it.
(Note: if you are using Wyther's datapack or mod, update that to the latest so their Shattered Savanna Plateau's basalt columns are placed properly)


### **(V.2.4.3 Changes) (1.17.1 Minecraft)**

##### Mod Compat:
Modded loot will be imported into Better Stronghold Compat datapack's loot tables now.


### **(V.2.4.2 Changes) (1.17.1 Minecraft)**

##### Villages:
Slightly adjusted look of Church (temple) piece in Mushroom Villages.

##### Misc:
RS structures that would've been cut off by world bottom will be offset upward so that they are now longer cut off.
(Helps prevents End-themed Better Stronghold from being cut off when using Better Stronghold Compat datapack)


### **(V.2.4.1 Changes) (1.17.1 Minecraft)**

##### Villages:
Fixed a street not spawning in Badlands Villages.

Adjusted some zombie terminator pool files so that they are used now for many zombie variant RS Villages.

Adjusted many RS Village's terminator pieces to make absolutely sure they cannot spawn the wrong village's street to prevent other village's buildings from spawning in extremely rare edge cases.

Fixed Zombie Giant Taiga Village spawning non-zombie pieces.

Fixed Zombie Warped Village spawning non-zombified Piglins.


### **(V.2.4.0 Changes) (1.17.1 Minecraft)**

##### Villages:
Mushroom Villages are now added to Mushroom category biomes!

Fixed Mountains Villages very very rarely spawn Savanna Village pieces.

Fixed Swamp and Giant Tree Taiga Villages very very rarely spawn Plains Village pieces.

Removed a lot of the randomly placed Red Sand blocks from Badlands Villages to make them look a lot cleaner.

##### Mod Compat:
Houses added to RS's villages from the official mod compat datapacks will now only spawn more more than once for a single village.
This will greatly help prevent RS's villages from becoming overloaded with multiple modded houses from the datapacks.


### **(V.2.3.10 Changes) (1.17.1 Minecraft)**

##### Lang:
Fixed several zh_cn.json translations.


### **(V.2.3.9 Changes) (1.17.1 Minecraft)**

##### Mansions:
RS Mansions will no longer spawn at world bottom and will not make support pillars to the very bottom of the world.
This makes mansions spawn much better for floating island worlds.

##### Pyramids:
Many pyramids will now no longer make a support pillar to the very bottom of the world in worlds like floating island worldtypes.

Fixed several pyramid's check for terrain to check at correct spots for burying themselves.

##### Fortresses:
Jungle Fortress will now no longer make a support pillar to the very bottom of the world in worlds like floating island worldtypes.


### **(V.2.3.8 Changes) (1.17.1 Minecraft)**

##### Misc:
Slightly rebalanced loot tables in all structures to be a bit better.


### **(V.2.3.7 Changes) (1.17.1 Minecraft)**

##### Misc:
Improved the error reporting a bit if a datapack has a typo within its pool_additions folder for Repurposed Structures.


### **(V.2.3.6 Changes) (1.17.1 Minecraft)**

##### Villages:
Removed some dropped items from one Birch Village house that was not supposed to be there.

Replaced most Moss Blocks with Grass Blocks on the ground of many Swamp Village houses.


### **(V.2.3.5 Changes) (1.17.1 Minecraft)**

##### Misc:
Setting an rs_spawner json file by datapack to have no mobs in it or have a total weight
of 0 will now replace the mob spawner block in that structure/feature with an air block.
A great new way to quickly remove spawners if you don't want them in RS structures/features.

##### Pyramids:
Fixed Flower Forest Pyramid's pit part from sometimes having plants not spawning.

Vanilla lakes should now not spawn inside Flower Forest Pyramids to help prevent floating plants.

Basalt Delta's basalt and lava features will not spawn in the Nether Pyramid for good.

Nether Pyramid is now in SURFACE_STRUCTURES generation stage.

##### Mineshafts:
Nether, Crimson, and Warped Mineshaft are now in UNDERGROUND_STRUCTURES generation stage.

##### Temples:
Basalt Temple is now in SURFACE_STRUCTURES generation stage.

Basalt Delta's basalt and lava features will not spawn in the Basalt Temple for good.

##### Ruins:
Basalt Delta's basalt and lava features will not spawn in Nether Ruins's area.

##### Outposts:
Basalt Delta's basalt and lava features will not spawn within the tower for Nether Bricks Outpost structure.

##### Villages:
Added a longer and bigger street piece to Badlands Villages so their larger temple piece can actually have a better chance of spawning.

Adjusted rates of some street pieces to help make birch, dark forest, giant taiga, mountains, and oak villages have a few more branching paths.


### **(V.2.3.4 Changes) (1.17.1 Minecraft)**

##### Igloos:
Grassy Igloo will no longer replace its blocks with air or fluid blocks if the biome's surfacebuilder uses air or fluid blocks.

##### Mineshafts:
Fixed crash if End Mineshaft is attempted to be spawned in a dimension where terrain reaches down to world bottom.

##### Configs:
Min and Max height spawn configs are now unbounded for Mineshafts, Dungeons, Fortresses, and Strongholds.
You can now specify for them to spawn below y = 0 if you wish.

Fixed missing lang entry for End Stronghold Vertical Range config.


### **(V.2.3.3 Changes) (1.17.1 Minecraft)**

##### Misc:
Fixed a memory leak from the processor used on Grassy Igloos. The leak only happens if you keep finding Grassy Igloos.
Special thanks to BlueAmulet for catching this leak.


### **(V2.3.2 Changes) (1.17.1 Minecraft)**

##### Misc:
Tried to fix three processors that randomly crashes with certain datapacks due to `Accessing PalettedContainer from multiple threads`.
Note: My fix for this mimics what vanilla ores do. But they skip checking the lock on the PalettedContainer so I have no idea how safe this fix actually is.
It should be fine but let me know if any issues comes up!

##### Mineshafts:
End Mineshafts now will try to spawn within islands a bit better.
The minY and maxY config for End Mineshafts was deleted in favor of endMineshaftMinIslandThickness config entry.

##### Dungeons:
Slightly lowered Ocean Dungeons default config value for spawn attempts per chunk from 5 to 4.

##### Shripwrecks:
Crimson, Warped, and Nether Bricks Shipwreck's spawnrate default config value has been changed to make them even more common.
The old config values will not change when updating so either delete the RS Shipwreck config file or edit the rates yourself to be 18 or 19.

End Shipwrecks treasure chest cannot have more than 1 Elytra and the Elytra is now more rare (from ~1/17 chance to now 1/20)

Increased chances of a lucky banner from End Shipwreck's map chest.

##### Strongholds:
Nether Strongholds library chest cannot have more than 1 kind of explorer map at a time in a single chest now.

##### Ruined Portals:
End Ruined Portals chests cannot ever have more than 1 End City explorer map in a single chest.

##### Igloos:
Stone Igloo chest should have loot now. The chest nbt was typo'ed again.

##### Loot Tables:
Explorer maps in the RS structures now only have zoom level 2 or 1 to massively speed up creation of these maps in chests.
This is most noticeable with the explorer maps created in the End themed RS structures. May even prevent servers from stalling.

Changed the explorer maps in the RS structures to no longer skip existing chunks for finding structures.
This means maps may locate structures that you already found before but this should reduce the lag on pre-generated servers.
By now checking already generated chunks, maps should load much faster from RS's loot blocks.
If you wish for the maps to find unexplored structures again, download the loot table datapack from here: https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/releases/tag/0.0.11
Then change `"skip_existing_chunks": false` to `"skip_existing_chunks": true` for all minecraft:exploration_map entries.

Cleaned up the lucky banner pools in RS loot tables so that it is 100% vanilla now and the updated loot table datapack can work on servers without RS on.

Removed treasure enchant possibilities for tools in many non-End RS loot tables for better balance. (Mending is a treasure enchantment for example)


### **(V2.3.1 Changes) (1.17.1 Minecraft)**

##### Configs:
Added configs to allow changing of RS Villages size. Note, you will need to re-add the changes you have done to RS Village config for spawnrates if you edit it before.


### **(V2.3.0 Changes) (1.17.1 Minecraft)**

##### Loot Tables:
Added special "bonus_rolls" pool to all RS's structure loot! If you have a mod that gives you the luck attribute or luck status effect,
you may start to see certain kinds of new items in RS's chests! Especially new banner designs! Every RS Structure has a unique banner
so get a mod or datapack that gives you luck and start collecting all the banners! See what you can find when you are lucky!

##### Mineshafts:
Harshly nerfed the rates of Minecarts in all RS Mineshafts as it was spawning too many Minecarts.

##### Mansions:
Fixed Snowy, Desert, and Birch Mansions using the wrong loot table or had the wrong loot table path in the chests.

All RS Mansions storage room chests may contain loot, make the dirt room chest have loot, and fixed a non-moisturized farmland block for farm room.

##### Pyramids:
Added a secret extra chest to End Pyramids that will have loot based on luck.
(The other chest's loot are generated automatically at creation due to the Comparator blocks)

##### Igloos:
Fixed Stone Igloo basement chest not using the right loot table file.

##### lang:
Corrected two entries in the ru_ru.json lang file (Thank you DrHesperus!)


### **(V2.2.2 Changes) (1.17.1 Minecraft)**

##### Major:
Fixed Dolphin mixin crashing because I screw up again in my rush to release a fix aaaaaa


### **(V2.2.1 Changes) (1.17.1 Minecraft)**

##### Pyramids:
Fed Dolphins have a 24% chance of leading the player to an Ocean Pyramid!

Hard nerfed the Enchanted Golden Apple rates for End Pyramids.

##### Temples:
Removed chance of spawning Enderman spawners in Warped Nether Temples.

Soul Nether Temple and Warped Nether Temple now only have 1 mob spawners instead of 2.

Warped Nether Temple now has 2 hidden puzzle chests instead of 1.

##### Igloos:
Grassy Igloos will now utilize the surface blocks of biomes it is in. So if the Grassy Igloo is added to a modded biome
with their own custom grass block, the Grassy Igloo will be made of that modded block now to blend in better!

##### Misc:
Fixed potential problems that could arise internally in code if user is using Turkish language settings.
(Java's toUpperCase/toLowerCase infamous interaction with Turkish)

Fixed a possible crash if trying to spawn spawn certain structure pieces by Jigsaw Blocks manually placed by players.
Worldgen was safe. This is just a rare edge case scenario 99.9% of people won't know about lol.


### **(V2.2.0 Changes) (1.17.1 Minecraft)**

##### Strongholds:
Added End Strongholds! Super rare and only spawns on within islands beyond 8000 blocks of the center of the End Dimension!
You better bring your Elytra as the entrance is actually not on the surface but underside!
But beware! It is very dangerous and massive to explore! And it also seems... off somehow... Can you figure out what is strange about it?

##### Bastions:
Buffed Underground Bastion loot a bit more and added "bonus_roll" to more of their loot tables.
If you have increased Luck attribute (usually from another mod), you may find increased loot in Underground Bastion's chests.

##### Dungeons:
In End Dungeons, Shulker Boxes now won't try and spawn on top of Shulker Boxes or turn the below Shulker Box into a Pig Spawner.

##### Shripwrecks:
Crimson, Warped, and Nether Bricks Shipwreck's spawnrate default config value has been changed to make them significantly more common.
The old config values will not change when updating so either delete the RS Shipwreck config file or edit the rates yourself to be 27 or 29.

##### Ruins:
Made the default config value for Hot Land Ruins and Warm Land Ruins higher so those ruins are a bit more rare.
The old config values will not change when updating so either delete the RS Ruins config file or edit the rates yourself to be 42 or 45.

##### Mineshafts:
Increased maximum config value allowed for RS Mineshaft size up to 30.

##### Fortresses:
Increased maximum config value allowed for Jungle Fortresses size up to 30.


### **(V2.1.11 Changes) (1.17.1 Minecraft)**

##### Pyramids:
Epic TelepathicGrunt moment lol. Hotfix to fix the broken Ocean Pyramid Bubble Processor fix.


### **(V2.1.10 Changes) (1.17.1 Minecraft)**

##### Pyramids:
Restrict y range for the Bubble Column processor so that Ocean Pyramids do not crash if it tries to spawn at world bottom or top cutoff.


### **(V.2.1.9 Changes) (1.17.1 Minecraft)**

##### Optimization:
Optimized RS Mineshafts, Nether Stronghold, and Jungle Fortress so they check their piece's bounding boxes more efficiently at generation.

##### Lang:
Russian translation added by DrHesperus! Thank you!

##### Dungeons:
Fixed double chests only having loot for 1 chest instead of 2 in RS Dunegons.

Added config entry to remove RS Ocean Dungeons from Terrestria's Lush Desert biome.

##### Bastions:
Fixed Skeletons in Underground Bastions so they spawn with correct amount of health.

##### Strongholds:
Fixed prison trapped Wither Skeletons in Nether Strongholds so they spawn with more health now.

##### Mineshafts:
Fixed Ocean Mineshaft center room sometimes have random Prismarine placed awkwardly.

##### Misc:
Fix potential issue where the structure check code may not work for preventing two specific structures from spawning on top of each other.


### **(V.2.1.8 Changes) (1.17.1 Minecraft)**

##### Villages:
Fixed Oak Villages placing Birch Planks when roads go into water

##### Mineshafts:
Significantly sped up /locate for RS Mineshafts

Slightly improved End Mineshaft's behavior when raising the minimum y value of the dimension by datapack

Lowered the default config height values for End Mineshafts

##### Advancement/Lang:
Fixed typo with Underground Bastion advancement name and typo with Igloo advancement description


### **(V.2.1.7 Changes) (1.17.1 Minecraft)**

##### Pyramids:
Fixed Bubble Columns in Ocean Pyramids not fully creating their column after pyramid generation

Prevent Ocean Pyramids from generating above sealevel.
Instead, they may be entirely buried below sealevel if the land is above sealevel.

##### Villages:
Increased the default separation config value for RS's Overworld Villages to make them less cluttered in the world

RS Overworld Villages centers will not spawn within 6 chunks of Vanilla Villages or Outposts.

##### Misc:
Improved error message from structures that can fail to spawn their pieces due to the height range being set to be too narrow in RS's configs.


### **(V.2.1.6 Changes) (1.17.1 Minecraft)**

##### Misc:
Fixed crash with Structure Block name field mixin (I was switching to Mojmap which is why these errors came up)


### **(V.2.1.5 Changes) (1.17.1 Minecraft)**

##### Misc:
Fixed crash with other mods due to a modifyconstant mixin being marked as required when it shouldn't be


### **(V.2.1.4 Changes) (1.17.1 Minecraft)**

##### Fortresses:
Jungle Fortresses end pieces now can spawn decoration properly

Nerfed rate of Drowned in Jungle Fortresses a bit

##### Mineshafts:
Fixed internals so now you can have multiple RS mineshafts spawn in a single biome if you chose to do so by config.

##### Dungeons:
Fixed internals so now you can have multiple RS dungeons spawn in a single biome if you chose to do so by config.

##### Wells:
Fixed internals so now you can have multiple RS wells spawn in a single biome if you chose to do so by config.

Wells are more likely to have a Bell now.

##### Configs:
Added optionalSpawn entry that you can put into the natural mob spawning over time config entries.
If optionalSpawn is added and set to true, that mob entry will not print a warning to the log if the mob is not found.
This is good for optional mod compat if you want to add another mod's entity to spawn in RS's structure but you think you might remove the mod later.

##### Misc:
Properly centered several structures so they match up with their land/water/terrain checks centered on the structure itself.


### **(V.2.1.3 Changes) (1.17.1 Minecraft)**

##### Configs:
Fixed natural mob spawning config's error message to actually say which mob entity name was unable to be resolved into an entity.

Fixed End Mineshaft's natural mob spawning having "minecraft:endermen" instead of "minecraft:enderman".
Now they can spawn Endermen again and this fix will retroactively fix old config files as well automatically.


### **(V.2.1.2 Changes) (1.17.1 Minecraft)**

##### Misc:
Fixed Vines features in various structures being able to cause a hang/deadlock.


### **(V.2.1.1 Changes) (1.17.1 Minecraft)**

##### Villages:
Fixed Mountains and Giant Tree Taiga Villages using Birch Village pieces by mistake.
Special thanks to SidraKlyara for catching the mistake and fixing it in a PR!


### **(V.2.1.0 Changes) (1.17.1 Minecraft)**

##### Mineshafts:
Redesigned RS Mineshafts so they all now can have pillars or chain supports when in midair! (except for End Mineshafts)

All overworld RS Mineshafts (except for Ocean Mineshafts) will now no longer place blocks that are in view of the sky to match vanilla Mineshaft behavior better

Nerfed Blue Ice rates in Icy Mineshafts.

Adjusted rates of Shroomlight in Warped Mineshafts

##### RS Spawners:
Made it so that setting a mob to a weight of 0 in an RS_Spawner file by datapack will no longer crash.
Instead, that mob won't be picked anymore. I did add a more detailed error message if a weight of -1 or lower is attempted.

Made empty rs_spawner files in datapacks now give a clear error instead.
It'll still crash later but scroll up and you'll see the error msg explaining why the files need at least 1 entitytype with a weight of 1 or more

##### Mod Compat:
Fixed possible crash with mods that makes their blocks implement Waterloggable but doesn't actually have the Waterloggable block property...

##### Configs:
Fixed possible race condition with Fabric API that breaks RS's structure dimension deny list config.


### **(V.2.0.6 Changes) (1.17.0 Minecraft)**

##### Villages:
Fixed Oak Villages using Birch Village pieces by mistake. Oops...


### **(V.2.0.5 Changes) (1.17.0 Minecraft)**

##### Mineshafts:
Fixed wall blocks or rails in Ocean Mineshafts not always being waterlogged

Fixed walls blocks in Ocean Mineshafts sometimes being in a weird state

##### Mod Compat:
Added Cubic Chunks file to make Mineshafts and Nether Strongholds spawn repeatedly downward when Cubic Chunks is on

Added code to made sure RS will not remove dungeons or mineshafts if BetterDungeons or BetterMineshafts mod is on


### **(V.2.0.4 Changes) (1.17.0 Minecraft)**

##### Villages:
Fixed some villages not spawning or having trouble spawning when William Wythers Overhauled datapack/mod is on

##### Ruins:
Fixed a Warm Land Ruins piece so it can spawn again


### **(V.2.0.3 Changes) (1.17.0 Minecraft)**

##### Mansions:
Adjusted the default config value for mansion's average chunk distance between spawn attempts so mansions are much more rarer

##### Mineshafts:
Added Glowberries as possible Chest Minecart loot for Birch, Dark Forest, Jungle, Savanna, Stone, Swamp, and Taiga Mineshafts

Fixed issue where the ending pieces of some Mineshafts won't have decorative blocks like vines or seagrass

##### Ruins:
Adjusted looks of Warm Land Ruins so it doesn't look as bad as it was before

Fixed Warm Land Ruins not having enough Tall Grass placed and Hot Land Ruins not having enough Dead Bush placed

Removed the random Grass Block that Hot Land Ruins spawned by mistake

##### Pyramids:
Fixed Tropical Fish dying in Mushroom Pyramid's pit at generation

##### Bastions:
Underground Bastion maps are now sold by Wandering Traders rarely and very expensive

##### Advancements:
Added experience reward for completing each of RS's advancements

##### Misc:
Redid a lot of code behind the scenes to clean up the codebase significantly. Hopefully I didn't miss any bugs.

Maps to Jungle Fortress, Underground Bastion, and all RS Mansions will no longer be added to Cartographers and
Wandering Trader's trades if you set the structure's average chunk distance to the 'turn off' value in the config.


### **(V.2.0.2 Changes) (1.17.0 Minecraft)**

##### Mineshafts:
Fixed Birch Mineshafts being flooded with water.

##### Pyramids:
Fixed chests in dry Pyramids from being flooded if they replace a water block in the world.

##### Configs:
Added agape mod's dimension to the dimension disallow config's default value.


### **(V.2.0.1 Changes) (1.17.0 Minecraft)**

##### Misc:
Fixed my fabric.mod.json file so it now requires an up-to-date Fabric API in order to prevent issues.

##### Pyramids:
Changed the Drowned used for debugging in Ocean Pyramid to not have enchanted boots anymore or green armor.

Fixed Icy Pyramid's chests being able to be opened safely. Now it is actually harder to not trigger trap lol.


### **(V.2.0.0 Changes) (1.17.0 Minecraft)**

##### Major:
Updated to 1.17!!! Note, putting this mod on a world made with 1.16.5 or older Repurposed Structures may cause weird stuff.

File paths in the resources/data folder has been significantly redone to be more consistent and cleaner.
1.16.5 or older datapacks for Repurposed Structures may not work anymore and have to be updated.

Added mixin to make a deep copy of the Noise Settings of each dimension to assign to those dimensions.
This may automatically fix the dimension whitelisting/blacklisting that some structure mods do so it now works properly.

##### Configs:
Heavily cleaned up and improved the configs. Fixed some structures and features not working with the dimension/biome configs correctly.
For allow/disallowing structures in dimension/biome, see the actual config file itself as Cloth Config API cannot show maps in GUI screen.
NOTE: As of v5.0.34 Cloth Config API, the maps for dimension/biome allowing and disallowing is not working. Please wait for future Cloth update to fix this.

Added configs to control natural mob spawning over time in structures!

##### Advancements:
All advancements has been condensed. Now there's one advancement for finding all variants of one structure type.
For example, you have to find and enter all Repurposed Structures villages to get the advancement for RS Villages.

##### Bastions:
Underground Bastions now uses Deepslate instead of Stone Bricks

Fixed Bastion Treasure room chest not having loot

Copper Ore spawns in Underground Bastions like Redstone Ores and Redstone Block do

Nerfed the rate of randomly placed Redstone Block and Redstone Ore in Underground Bastions

Added Deepslate layer to bottom of Unit and Stables giant rooms in Underground Bastions to reduce chances of it getting burned down by lava

##### Cities:
In Nether Cities, the topmost small tower tops will now spawn Wither Skeletons with Bows

Made the average spawn distance config's default value for Nether Cities be a bit smaller to make them be more common

##### Mineshafts:
Ocean Mineshafts now uses waterlogged Rails instead of waterlogged

Swamp Mineshaft's floor now uses Moss Blocks instead of Grass Blocks

##### Pyramids:
Jungle Pyramids now has a bit of Moss Carpet

Flower Forest Pyramids now has Moss Block, Spore Blossom, Small Dripleaf, and Flowering Azalea

Mushroom Pyramid's pit is slightly adjusted to not be so cramped

Slightly adjusted Icy Pyramid's pit to make it harder to not set off trap

Added Chains to Ocean Pyramids so the Drowned won't walk into the Magma Block and take damage

Made Icy and Flower Forest Pyramid's average spawn distance config's default value be slightly smaller to make them be more common

Overworld land pyramids will try to not spawn in middle of large bodies of water

##### Mansions:
Made the average spawn distance config's default value for many mansions be a bit larger to space out the mansions more

##### Ruins:
Overworld land ruins will try to not spawn in middle of large bodies of water

##### Igloos:
Igloos will try to not spawn in middle of large bodies of water

##### Villages:
Swamp Villages now utilizes Moss Blocks heavily and has more Vines

##### Outposts:
Jungle Outposts tents is made of Moss Blocks and has Moss Carpet under them

Made the average spawn distance config's default value for Overworld outposts be a tiny bit larger to space out the outposts more

##### Dungeons:
Fixed vanilla dungeons being removed from cold, frozen, warm, and lukewarm ocean biomes.

##### Wells:
All wells now use processor lists to randomize their chances of having ores or bells.
The well's block tags and bell config entry have been deleted as they are no longer needed.

Increase default chance of Nether Wells spawning.

##### Pool Additions:
Will now print out to the logs if a merging pool has a typo in its nbt file entries.


### **(V.1.11.6 Changes) (1.16.5 Minecraft)**

##### Mineshafts:
* Lowered default config value for the max height of Ocean mineshafts from 31 to 26 to reduce amount of Ocean Mineshafts exposed on ocean floor.


### **(V.1.11.5 Changes) (1.16.5 Minecraft)**

##### Misc:
* Fixed serverside crash when trying to use the pool_addition merger system.


### **(V.1.11.4 Changes) (1.16.5 Minecraft)**

##### Mansions:
* Adjusted RS Mansions so their biome check doesn't conflict with any mod that adds underground biomes in a certain way.


### **(V.1.11.3 Changes) (1.16.5 Minecraft)**

##### Misc:
* Silenced logspam about missing structure pieces that was removed in v1.11.


### **(V.1.11.2 Changes) (1.16.5 Minecraft)**

##### Villages:
* Remove entry in Zombie Jungle Village Pool that was pointing to a non-existent nbt file.

* Fixed butcher house pieces not spawning in zombie swamp villages.


### **(V.1.11.1 Changes) (1.16.5 Minecraft)**

##### Misc:
* Fixed close air off processor not separating the structure's fluid from mismatched environmental fluids as well.
  Ocean Mineshafts should no longer have floating lava at edges at times anymore.

##### Lang:
* Special thanks to invalid2tk for the Portuguese translations!

* Fixed translations not working for many of the newer structure's advancements.


### **(V.1.11.0 Changes) (1.16.5 Minecraft)**

##### Bastions:
* Added rare Underground Bastions in the Overworld! Fight through many powerful Skeletons and loot all the Redstone!

##### Ruins:
* Added land Ruins to Overworld! They come in a stonebrick and sandstone variant. If you are lucky, you may find a map to an Underground Bastion!

##### Mineshafts:
* All of RS's Mineshafts are now Jigsaw Structures so you can use datapacks to add new nbt pieces to it or customize it even more!

* Mineshaft size is now a config option. All Mineshafts are a bit larger now with End Mineshafts especially giant now.

* All 3 Nether Mineshafts have higher chance of Chest Minecarts.

* End Mineshafts have much higher rate of Chest Minecarts but also has far more Endermite Spawners to make it dangerous.

* Overworld Mineshafts are less likely to be cutoff by water and instead, will attempt to go through the liquid and wall it off instead.
  Ocean Mineshafts will do the same but for air instead.

* Swamp And Dark Forest Mineshaft has been split up into two separate Mineshafts. Swamp Mineshaft and Dark Forest Mineshaft.

* Crimson, Warped, Jungle, Dark Forest, and Swamp Mineshafts will have a higher chance of taller climbable plants in their 2 floor pillar piece so you can climb to the next floor.

##### Fortresses:
* Jungle Fortress is now a Jigsaw Structures so you can use datapacks to add new nbt pieces to it or customize it even more!

* Jungle Fortress's size and the height range it can spawn at are now config options.

* Drowned in Jungle Fortresses now may hold Stone Swords that could be enchanted and will wear chainmail armor more often than iron armor.

* The breakage and decay in Jungle Fortress can be found throughout the fortress. Even in underground tunnels now.

##### Strongholds:
* Removed Stonebricks Strongholds because Better Strongholds mod exists!
  Use that epic mod instead of upgrade the Overworld Stronghold!

* Nether Strongholds are now Jigsaw Structures so you can use datapacks to add new nbt pieces to it or customize it even more!
  The Portal Room will now always be attached properly to the rest of the Stronghold!

* Nether Stronghold prison pieces may have a chance of a trapped stronger Wither Skeleton.

* Nether Stronghold Libraries now has a rare chance for Ancient Debris and the blocks are more randomized and rebalanced.

* The ends of the Nether Stronghold will now never have a wall of flowing lava anymore!

* Nether Strongholds will now only spawn further than roughly 2000 blocks from spawn.
  Their config default distance between spawn attempts was changed from 85 to 100 as well to spread them out a bit.

* Nether Stronghold is now in the last generation stage in order to try and prevent other structures from eating at the stronghold.

##### Outposts:
* Modernized all Outposts Pieces which should help make their small feature pieces spawn more.

* Expanded all RS's Overworld Outposts boundaries so that Pillagers should now spawn more often over time.

* Fixed Jungle Outpost not importing modded loot.

##### Pyramids:
* Added Jungle, Ocean, Giant Tree Taiga, Flower Forest, and Mushroom Pyramids!

* Buffed loot a bit for Badlands, Icy, and Snowy Pyramids and added Horse Armor back into their loot table.

* Snowy Pyramid Pit slightly changed to help hint at what spots are safe to stand on.

* Fixed Snowy Pyramids making a base of Red Sandstone instead of Snow Block by mistake.

* Icy Pyramid Pit trap is now redesigned to be a bit more dangerous.

* Fixed lang entry for End and Icy Pyramids.

* Increased the config default value for Badlands and Snowy Pyramid spawn attempt separation from 37 to 40.

##### Ruins:
* Added translation for Nether Ruins Map names.

##### Dungeons:
* Fixed bug that prevented RS's Dungeons from ever having 2 chests. Now they have a chance of spawning 2 chests.

##### Villages:
* Fixed Zombie Jungle Village not spawning any buildings and all Jungle Villages using the wrong terminator piece.

* Fixed Giant Tree Taiga Village not spawning butcher buildings.

* Jungle Villages now has much less Jungle Bush trees blocking houses and paths.

##### Wells:
* Added Mushroom Wells to mushroom biomes. Has a chance of Iron or Redstone Ores inside and yes, it also has an extremely rare chance of having a Bell.

##### Mansions:
* Added lang entry for Savanna Mansions Maps.

##### Misc:
* Removed the giant boulder feature because it isn't close to a structure and doesn't fit this mod.

* Borrowed Lithium's VoxelShapes mixin for LithiumDoublePairList in order to optimize creation of jigsaw structure bounding boxes a bit when Lithium isn't on. Use that mod tho if you can!

* Added a new system where multiple datapacks can add new structure pieces to any pool file!
  Instead of overwriting the pool file at
  `data\repurposed_structures\worldgen\template_pool\village\birch\houses.nbt`
  datapacks can swap the worldgen\template_pool part of the path to pool_additions like this
  `data\repurposed_structures\pool_additions\village\birch\houses.nbt`
  Now my mod will detect these pool files and merge its entries to the template_pool of the same path!
  This allows you to stack multiple datapacks using this systme without needing to merge their template_pool files together by hand.
  (Fun fact, this actually will work for other mod's template_pools too if Repurposed Structures is on)

##### Lang:
* Special thanks to BlueDemonTR for the Turkish translations!

* Special thanks to Fabidrums for the new and more accurate German translations!


### **(V.1.10.5 Changes) (1.16.5 Minecraft)**

##### Config:
* Fixed dimension blacklisting not working for dungeons, boulders, wells, and other features.

##### Strongholds:
* Chest Corridors now will always have a chest instead of a 1/3rd chance.

##### Witch Huts:
* Fixed Giant Tree Taiga Witch Hut having a stripped spruce log leg instead of Stone Bricks leg.


### **(V.1.10.4 Changes) (1.16.5 Minecraft)**

##### Misc:
* Stopped using Fabric API's TradeOfferHelper for adding structure maps to Cartographer trades.
  This is due to the API being broken and duplicating old trades like crazy. And due to it breaking
  any mod that adds trades without using the API. However, this means my mod's trades will be nuked if
  you have on another mod that is using the API to add trades.


### **(V.1.10.3 Changes) (1.16.5 Minecraft)**

##### Dungeons:
* Fixed potential crash if someone set RS's dungeon spawners to not have any nbt.


### **(V.1.10.2 Changes) (1.16.5 Minecraft)**

##### Dungeons:
* Fixed Dungeons being able to replace Chests and Spawners of other dungeons.

* Improved the RNG for Dungeon processors.

##### Strongholds:
* Moved Eyes of Ender mixin code out of mixin to be compatible with other mods that mixin that item.


### **(V.1.10.1 Changes) (1.16.5 Minecraft)**

##### Outposts:
* Fixed End Outposts able to spawn on the void in modded biomes.


### **(V.1.10.0 Changes) (1.16.5 Minecraft)**

##### Cities:
* Change Nether City's default config spawnrate from 160 to 120 to make them more common and less traveling to reach.

* Made large room with double chest in Nether Cities now a bit more common and have 3 Wither Skeletons defending it now.

* Added Wither Skeleton with enchanted bow to Nether Cities's tiny tower top pieces.

* Buffed Nether City loot a lot to have higher chance of Netherite loot and more chests.

* Removed random stray Red Nether Bricks block in one of the Nether City's stair piece.

##### Strongholds:
* Repurposed Structure's Stonebrick Strongholds will now generate only in rings around world origin like Vanilla stronghold's do.
  The rings of valid spots that RS Stonebrick Strongholds can spawn in are the same size and distance from spawn as vanilla's.
  However, there are an unlimited number of rings for RS's Stonebrick Stronghold beyond the 8 rings that Vanilla's does.
  See here for what I mean about rings: https://minecraft.fandom.com/wiki/Stronghold#Generation

* Bumped Stonebrick Stronghold's default config spawnrate from 85 to 110.

* Stonebrick Strongholds now will spawn in ocean biomes like vanilla Strongholds do.
  There's a weird quirk with how vanilla Stronghold will spawn in biomes that doesn't have the Stronghold added to it which I am trying to copy here.

* Buffed Nether Stronghold loot and added custom loot table for its library too.

* Nether Stronghold Libraries can spawn bookshelves of even more kinds of blocks.

* Made Repurposed Structures's Stonebrick Strongholds now use custom loot tables and can have better loot than vanilla loot.

##### Dungeons:
* All Dungeons now use nbt files. You can override the looks of dungeons with a datapack!

* Icy dungeon is now actually icy and spawns only in non-ocean biomes that are super cold or has frozen/ice in the name.
  Loot and spawner mobs have been adjusted as well.

* Snow Dungeon is now added and will spawn in all snowy biomes!

* Nether Dungeons at any height now has the 1% chance of a Wither Skeleton Spawner.

* Nether Dungeons no long spawn attached to lava pockets underground.

* Nether Dungeons are in Vegetal Generation Stage now to be able to spawn in Nether Mineshafts again and not be filled with Basalt as much.

* Bumped up the config default spawnrates for Nether and End Dungeons from 8 to 12. Slightly restricted the spawn condition for overworld dungeons.

##### Shipwrecks:
* Hard nerfed the Netherite loot from Nether Bricks Shipwrecks.

* In Nether Bricks Shipwrecks, they now have more Wither Skeletons upon first generation.

* Adjusted the stairway in Nether Bricks Shipwrecks so that Wither Skeletons can walk though them.

* 1 Wither Skeleton with an enchanted bow has been added to Warped, Crimson, and Nether Bricks Shipwreck. (Crimson Shipwreck mast now has platform)

* All 3 Nether Shipwrecks now properly checks to make sure they won't spawn inside land.

* Fixed End Shipwrecks spawning so they no longer can spawn on the Enderdragon island.

* Change End Shipwrecks's default config spawnrate from 15 to 24 to make them less common.

##### Outposts:
* Added End Outposts to the End! Be careful as they are swarming with Phantoms!

##### Pyramids:
* Added End Pyramids to the End! A shrine for the dragon of the End!

* Added Icy Pyramids to frozen biomes! Stay warm!

* Snowy Pyramid chests will have Snow Blocks instead of Ice Blocks in its loot.

* Fixed https://bugs.mojang.com/browse/MC-130584 by using a special processor.
  This means this mod's pyramid's chests will no longer be waterlogged if their pits replaces water in the world.

* Nether and Badlands Pyramids now has a rs_spawner entry where people can randomize the mob spawner's mob much easier!

##### Temples:
* All Nether Temples now has a rs_spawner entry where people can randomize the mob spawner's mob much easier!

##### Igloos:
* Fixed https://bugs.mojang.com/browse/MC-130584 by using a special processor.
  This means this mod's igloo's chests, ladders, and other blocks will no longer be waterlogged if their basement places water in the world.

##### Ruined Portals:
* Now will be less likely to spawn off the island and will bury itself a bit.

##### Mansions:
* Fixed up a massive amount of RS Mansion pieces so that they look nicer.
  (Also fixed some torches in Snowy mansion melting ice and fixed the leaf block that was decaying within the mini-tree in bedroom)

* Changed Snowy Mansion's achievement description.

##### Loot Tables:
* Fixed blacklistedRSLoottablesFromImportingModdedItems config entry not actually being read.


### **(V.1.9.0 Changes) (1.16.5 Minecraft)**

##### Loot Tables:
* Added code that now will import modded items from vanilla structure's loot tables into Repurposed Structures's own loot tables.
  (Snowy Pyramid gets all modded items that vanilla Desert Temple can have)

##### Language:
* Fixed some Spanish translations being cut-off.

* Added German translations by elloellie and partially Google Translate! Special thanks to them!

##### Config:
* Fixed some config comments missing the phrase: '1 for spawning in most chunks and 1001 for none.'


### **(V.1.8.7 Changes) (1.16.5 Minecraft)**

##### Major:
* I'm sorry everyone. The config API dependency I use now has migrated to Cloth Config API.
  Download link is here: https://www.curseforge.com/minecraft/mc-mods/cloth-config

##### Language:
* Fixed bundled lang files that were missing map translations for 5 mansions.

* Changed how translations are done for this mod and now you no longer need the translation datapack.
  Just a resourcepack for your translations to take effect! This mod is now bundled with Spanish and Chinese translations files.
  The trick is that the English translations are now the keys for the other language files in order to make them work.

* Spanish translation provided by 16N1C0! Special thanks to them!


### **(V.1.8.6 Changes) (1.16.5 Minecraft)**

##### Fortresses:
* Stopped scheduling duplicate fluid ticks for water in Jungle Fortresses. Also fixed a rare possible crash.

##### Dungeons:
* End Dungeons now have more Shulker Boxes and will have an additional Spawners per Shulker Box that spawns.


### **(V.1.8.5 Changes) (1.16.5 Minecraft)**

##### General:
* Added null check for logspam filtering code.


### **(V.1.8.4 Changes) (1.16.5 Minecraft)**

##### General:
* Found a way to silence logspam about the old structure pieces names being unknown in old worlds. Thank you MutantGumdrop for the suggestion!


### **(V.1.8.3 Changes) (1.16.5 Minecraft)**

##### Strongholds:
* So uh, I just learned that removing the Vanilla Stronghold from all biomes doesn't actually stop it from spawning.
  To fix this, turnOffVanillaStrongholds config value is now set to true by default and it will now correctly make
  Vanilla Strongholds no longer spawn anymore. Repurposed Structures's Stonebrick Stronghold will take its place properly.
  (Yes Eyes of Ender will find RS's Strongholds as well)

* Moved RS Strongholds from UNDERGROUND_STRUCTURES generation stage to STRONGHOLDS.
  Will prevent some features/structures from breaking strongholds.

##### Outposts:
* Nerfed the enchantment range of armor and weapons in Crimson, Warped, and Nether Bricks Outpost's loot.
  Armor and Weapons will be damaged now to encourage fusing gears together to repair and strengthen enchantments.

* All Overworld Repurposed Structures Outposts now generates with 8 Pillagers (Vanilla spawns with none btw)
  All 8 Pillagers will not despawn and 1 of the Pillager has a Raid Banner.
  These outposts will still spawn additional Pillagers over time.

* Crimson, Warped, and Nether Bricks Outpost Chest blocks now faces the correct direction.

##### Mansions:
* Fixed logspam from Snowy Mansions about Diorite Walls having wrong properties.

##### Fortresses:
* Lowered Jungle Fortresses a bit to increase the chances of flooded hallways.

##### Pyramids:
* Made Badlands Pyramid rarer by default in configs.

##### Villages:
* Made Giant Tree Taiga a bit less messy and fixed several pieces not having its Stone Pressure Plates randomly replaced.

* Jungle Villages will now try not to spawn too close to Jungle Fortresses.

##### Mineshafts:
* Lavafalls will no longer spawn in Icy Mineshafts!


### **(V.1.8.2 Changes) (1.16.5 Minecraft)**

##### Cities:
* made Nether Cities have Netherite Scraps and Netherite Ingot in their chests.

##### Outposts:
* Adjusted looks of Crimson and Warped Outposts so their aged tower variants are different. Also renamed tower_glowing piece to tower_aged for both.

* Added aged variant for Nether Bricks Outpost.

##### Pyramids:
* Fixed pyramid not having pillar of their blocks below their pits when there is space below.

##### Strongholds:
* Added turnOffVanillaStrongholds config option to let people be able to turn off vanilla Strongholds to only have Nether Strongholds easier.

* Fixed Stone Shores still having Vanilla Strongholds instead of Repurposed Structures's which was causing false positives with /locate stronghold and Eyes of Ender as a result.

##### Villages:
* Fixed Zombie Badlands Village having 2 Birch Village houses by mistake.

##### General:
* Made all the checks I have for separating some structures from other kinds of structures now ignore those checks if the structure spacing is set super low in configs.
  Now if you make all outposts spawn in every chunk by config, Repurposed Structures villages will still be able to spawn.

* Made all hardcoded nether height structures now uses the chunk generator's maximum height.
  So if you use a datapack to expand the Nether's roof, these structures should automatically now use the increased range!
  Some checks for Nether sealevel now uses the chunk generator's sealevel instead of a hardcoded value.

* Made all structures that checks surrounding biomes for valid biomes before spawning will now ignore that check if Checkered Biome Provider is used.


### **(V.1.8.1 Changes) (1.16.5 Minecraft)**

##### Witch Huts:
* Fixed Taiga Witch Hut Advancement triggering on world entering.

##### Outposts:
* Adjusted looks of Crimson and Warped Outposts so their aged tower variants are different. Also renamed tower_glowing piece to tower_aged for both.

* Added aged variant for Nether Bricks Outpost.

##### Misc:
* Moved StructureBlockScreenMixin so it only applies client side. Oops. It is not suppose to run on servers.


### **(V.1.8.0 Changes) (1.16.5 Minecraft)**

##### Dependencies:
* Cloth and AutoConfigu are no longer packaged with this mod to cut down on file size. Download those two lib mods separately.

##### Cities:
* Added extremely rare Nether Cities to all Nether biomes! Beware of the Blazes! Maps to this city are very rare but can be found in Nether Ruins or Crimson/Warped Cartographer chests.

##### Ruins:
* Added Nether Ruins to all Nether biomes! They are a great source of maps to undiscovered Bastion Remnants! Special thanks to miguelforge for the design!

##### Mansions:
* Added Birch Mansions! They are rarer than vanilla mansion to try and not crowd out the world.
  Cartographers may sell the mansion maps in Level 4 trades!

##### Witch Huts:
* Added Birch, Taiga, Giant Tree Taiga, Dark Forest, and Oak Witch Huts! They spawn Witches and Cats over time!

##### Mineshafts:
* Added Crimson and Warped Mineshafts!

* Tried to fixed Vines being improperly placed in Jungle and SwampAndDark Mineshafts and also added Leaves blocks to them for a more overgrown look.

* Changed Nether Mineshafts so they now carve into lava but places blocks to prevent floating lava. Tunnels should be less disconnected now.

* Moved Nether Mineshafts from UNDERGROUND_STRUCTURES to VEGETAL_DECORATION stage to allow Ancient Debris to be exposed in Mineshafts.
  As a bonus, Basalt Delta's Basalt will not clog up Mineshafts anymore too!

##### Pyramids:
* Added Snowy Pyramids to snowy biomes!

* Bumped default config spawnrate of Badlands Pyramids from 20 to 25 to make them a bit more rare.

* Badlands Pyramids now properly create pillar of Red Sandstone in parity to vanilla's Desert Temple.

* Rounded the bottom of Nether Pyramids so they look better even when the Nether's caves and ravines carves out the land from under the pyramid.

* Nerfed Golden Swords enchantment level range from 20-39 to 10-30 in chest's loottable for Nether Pyramids.

##### Outposts:
* Added Taiga Outposts to non-snowy and non-giant Taiga biomes!

* Added Oak Outposts to non-birch and non-dark Forest biomes!

* Badlands Outposts can spawn on Badland's Plateaus now using a special heightmap check to not spawn it on plateau walls.

* Removed Forge's gravity attribute from Brute in Nether Bricks Outpost to reduce logspam.

* Set default config for all Outposts to be slightly more rare to compensate for Outposts spawning in more biomes.

* Bumped default spawnrate of Birch, Jungle, Giant Tree Taiga, Desert, Badlands, Snowy, Oak, Taiga Outposts from 41/43 to 50 to make them slightly more rare.

* Fixed all Outposts spawning too close to vanilla villages.

##### Fortresses:
* Fixed Jungle Fortresses not getting extra vines or breakage.

* Attempt to reduce the amount of floating Vines in Jungle Fortress.

* Fixed Drowned not spawning in flooded Jungle Fortresses by removing them from the mob-spawning-over-time list and now just directly
  spawns some Drowned on fortress creation. These Drowned will not despawn and will sometimes have Iron or Chainmail armor.

* Bumped default config spawnrate of Jungle Fortresses from 32 to 50 to make them more rare.

* Nerfed Jungle Fortress loottables to pick less loot and diamond armor is removed. Leather armor is added instead.

##### Shipwrecks:
* Nether Bricks Shipwrecks will only be flying in any biome with "soul" in the name.

##### Strongholds:
* Lowered netherStrongholdMinHeight config default value to 10.

* Slightly adjusted Stonebrick Strongholds so they now won't be added to None-category biomes.

##### Temples:
* Moved Nether Basalt Temple from SURFACE_STRUCTURES to VEGETAL_DECORATION stage to prevent Basalt Delta's Basalt features from clogging up the insides.

* Adjusted Nether Basalt Temple's processor to randomize the temple's blocks a bit better.

* Nerfed Golden Swords enchantment level range from 20-39 to 10-30 in all chest's loottable for temples.

* Badlands Pyramids now properly create pillar of Red Sandstone in parity to vanilla's Desert Temple.

* Rounded the bottom of Nether Pyramids so they look better even when the Nether's caves and ravines carves out the land from under the pyramid.

##### Dungeons:
* Nerfed all golden tools and armor enchantment level range from 15-30 to 5-25 in Nether Dungeon chest's loottable.

* Lowered default config spawnrate for Ocean Dungeons from 6 to 5.

##### Villages:
* Replaced a lot of Cobblestone with wood in Oak Villages to make them more distinct from Plains Villages and made Path Blocks more common.

* Added zombie variant of Badlands Villages.

* Removed Forge's gravity attribute from Brute in Crimson and Warped Villages to reduce logspam.

* Bumped default config spawnrate of Birch, Oak, Dark Forest, Jungle, Swamp, Mountains, and Giant Tree Taiga Villages from 29/31/33 to 38 to make them more rare.

* Bumped default config spawnrate of Badlands Villages from 23 to 30 to make them more rare.

##### Misc:
* Deleted 2x2 Swamp Trees. They didn't fit this mod at all. They still exist in Ultra Amplified Dimension mod

* Made Structure Blocks name field now hold 128 characters like Jigsaw Blocks instead of 64 characters.
  Now loading up Repurposed Structures's village pieces are much easier.

##### General:
* Cleaned up biome selection code backend a lot and adjusted some biome selections conditions for a few structures.

* Fixed structure pieces being registered under minecraft's namespace.

* Cleaned up en_us.json file to be nearly alphabetical.


### **(V.1.7.4 Changes) (1.16.4 Minecraft)**

##### General:
* Improved biome targeting so some structures types get the right biome better.

##### Outposts:
* Replaced Dark Oak blocks with Spruce blocks in Snow Outposts

* Changed some blocks for the cages in badlands, Desert, Icy, and Snowy Outposts.  

##### Strongholds:
* Strongholds now will fit much better between the min and max y set in the config. 
  Rooms too low or too high will now be deleted (though the Portal Room will never be deleted).
  As a result, you may find a few rooms or hallways cutoff from the rest of the stronghold but that was
  a sacrifice I was willing to make to fix Strongholds sometimes spawning way aboveground than they should.
  
* Nether Strongholds no longer has Cobwebs in libraries and instead, may have fire.

* Strongholds will now not spawn in the End Dimension even when you add non-End category biomes to the dimension. With a datapack, you can
  add the RS stronghold spacing to the End Noise Setting and to the End category biomes to force the Stronghold to spawn in that dimension.

##### Villages:
* Badlands Villages now can spawn in non-plateau Badlands with a terrain check to try and keep them from generating on plateau walls.

* Badlands Villages now only contain Desert clothed Villagers instead of sometimes having Plains clothed Villagers.

* Fixed Birch Villages so they no longer have Oak Stripped Wood, Oak Fence Gate, or Oak Stairs.
 
* Fixed Churches not having doors in Birch, Dark Forest, and Oak Villages.

* Fixed Jungle Villages having Acacia blocks by mistake in the Weaponsmith house and fixed door in Butcher house being the wrong blockstate.

* Mountain Villages farms now have waterlogged Cobblestone Slab instead of Water Blocks to prevent them from turning into ice when too cold.

##### Wells:
* Fixed crash if the wells nbt file ever somehow goes missing.

##### Dungeons:
* Fixed mob spawners losing their mob data after doing `/reload` or `/datapack disable vanilla` commands.

##### Mineshafts:
* Fixed mob spawners losing their mob data after doing `/reload` or `/datapack disable vanilla` commands.

##### Igloos:
* Stony Igloos now uses Spruce Signs and their Villager/Zombie Villager now wears taiga clothing.

* Grassy Igloos basements now use Oak blocks instead of Spruce.

##### Fortresses:
* Added Drowned to Jungle Fortresses possible mob spawns to help populate the water filled hallways below sealevel.

##### Backend:
* Structure spacing for RS structures set by JSON will now no longer be overwritten. 
  With datapacks, you can make RS structures now spawn more or less frequently as a result.

* Redid code on how mobs spawn over time are added to RS structures to be cleaner and less likely I forget to add mob spawns later. 

-Prefixed all my accessor and invoker mixins due to this bug in mixins that could cause a crash with other mods for same named mixins.
 https://github.com/SpongePowered/Mixin/issues/430


### **(V.1.7.3 Changes) (1.16.4 Minecraft)**

##### Dungeons:
* Fixed world not loading due to setting dungeon config spawnrate above 128 due to a Mojang limit. 
  Now you can set spawnrate above 128 safely!
  
* Improved the looks of Mushroom Dungeons.
  
* Removed Turtle from Ocean Dungeon Spawners because Turtles Spawners 
  only spawn Turtles if above sealevel, in light, and on Sand.

##### Stronghold:
* Fixed world not loading due to setting chain config spawnrate above 128 due to a Mojang limit. 
  Now you can set spawnrate above 128 safely!

##### Outposts:
* Fixed English translation for Giant Tree Taiga Outpost Advancement.

##### Igloos:
* Fixed missing Ladder block at top of some basements in Grassy and Stone Igloos.


### **(V.1.7.2 Changes) (1.16.4 Minecraft)**

##### Outposts:
* Fixed Warped Outposts not having 6 Piglins and a Brute at first spawning.


### **(V.1.7.1 Changes) (1.16.4 Minecraft)**

##### Outposts:
* Replaced some Birch Logs with Birch Wood in Birch Outposts to make it look better. 

* Overworld Outposts now mesh much better with the surrounding terrain.

* Lowered default value for outpostBadlandsAverageChunkDistance config entry from 37 to 31
  so that the Badlands Outpost isn't as extremely rare as it was before.

##### Backend:
* Updated the Angerable Patch dependency so that Dog's collar color is saved properly.


### **(V.1.7.0 Changes) (1.16.4 Minecraft)**

##### New additions!:
* End themed Ruined Portals now spawn in the End dimension!
  Special thanks to miguelforge for creating them!

* Added Birch, Jungle, Icy, Snowy, Giant Tree Taiga, Desert, and Badlands Pillager Outposts!

* Added Oak Village that spawns in any forest category biome that isn't birch or dark forest!

##### Backend:
* Reworked and cleaned up backend to now 
  use Fabric API's Biome Modification API.

* Fixed many structures not using the speedy locate method.

* Fixed a bug that could still spawn Repurposed Structures's stuff in superflat.

##### Advancements:
* Advancements now have English translations built in so vanilla clients do not 
  need any en_us.json resource pack anymore! If you want clients to have different
  translations from each other, put Repurposed_Structures-Translation_Advancements
  datapack on the server and now each client can make their own en_us.json resourcepack
  for their own language translations.
  
* Organized advancements into subfolders now so it is cleaner.

##### Strongholds:
* Fixed Strongholds being cutoff by Bedrock.

* Stonebrick Strongholds no longer can be added to End category modded biomes anymore.

* Added allowStonebrickStrongholdToVanillaBiomes config option for Stonebrick Strongholds. 
  It now now possible to have Repurposed Structures's Stonebrick Strongholds to only spawn 
  in modded biomes, or only in vanilla biomes, or both by changing the values of
  addStonebrickStrongholdToModdedBiomes and allowStonebrickStrongholdToVanillaBiomes.

* Fixed Stronghold Chains placing floating lanterns at y = 3.

##### Mineshafts:
* End Mineshafts will now be much more buried in the End's islands 
  if barrensIslandsEndMineshafts config option is turned off.

##### Shipwrecks:
* Fixed addWarpedShipwreckToModdedBiomes config not working.

##### Fortresses:
* Jungle Fortresses now need to be fully enclosed in a jungle category biome to spawn.

* Increased chance of a hallway having a chest in Jungle Fortresses from
  1/9th chance to 1/5th chance. Nether Fortress is 1/3rd chance for chest 
  in hallway for reference.

* Nerfed Jungle Fortress chest loot a bit

##### Igloos:
* Grassy and Stone Igloos are now added to modded 
  biomes by default unless turned off in config.

##### Outposts:
* Buffed Nether Outposts's loot a bit.

##### Villages:
* Adjusted Dark Villages so they are added to biomes with "dark" in the name and is 
  forest category instead of only be added to biome with "dark_forest" in name.

* Adjusted some villages pieces to allow Villagers to reach workstation as
  Mojang broke the Villager AI with a bug lol.
  
* Fix several instances of stairs and fences being in impossible blockstates 
  in the Villages. Also fixed some random Dirt blocks being in Villages.
  
* Fixed a Dark Oak Village house being fused with a farm for no reason lmao.

* Reduced chances of Village's Iron Golem spawning inside a tree and dying.
  
* Added a new Badlands Library made by miguelforge!
  
* Nerfed the default spacing config value for all overworld villages.

##### Miscellaneous:
* Giant Boulders in Giant Tree Taiga biomes will now no longer float on water.

##### General Configs Changes:
* Changed "spawnrate" for dungeon entries to say "attemptsPerChunk" instead.

* Changed "spawnrate" for well entries to say "rarityPerChunk" instead.

* Changed "spawnrate" for majority of structures entries to say "maxChunkDistance" instead.

* Removed "JF" from some Jungle Fortress entries.

* Biome blacklisting configs now will ignore spaces between entries.


### **(V.1.6.9 Changes) (1.16.3 Minecraft)**

##### Outposts:

* Nether Outposts now has 1 Piglin Brute.

##### Dungeons:

* Ocean Dungeons chests will now have Prismarine placed under 
  them if they are floating. Should reduce the amount of 
  floating chests by a lot. 

* Removed floating plants from above Ocean Dungeons as best I can.

##### Shipwreck:

* Fixed map chest loot not showing up in Warped Shipwreck.

##### Misc:

* Giant boulders in Giant Tree Taiga biomes now are more varied in size.

* Default config spawnrate of giant boulders was reduced.


### **(V.1.6.8 Changes) (1.16.3 Minecraft)**

##### Villages:

* Fixed typos in template_pools so that now ALL village pieces can spawn in villages!

##### Config:

* Dimension blacklist will now ignore any spaces you leave between entries in it.


### **(V.1.6.7 Changes) (1.16.3 Minecraft)**

##### Misc:

* Fixed crash with some mod's custom ChunkGenerator.
  Just be careful to not use /locate in these custom dimensions
  as that could have a chance of causing the game to hang 
  depending on how they coded the ChunkGenerator. 
  Will try and think of some sort of solution to this...


### **(V.1.6.6 Changes) (1.16.3 Minecraft)**

##### Misc:

* Added check to automatically blacklist any dimension using the
  FlatChunkGenerator (Superflat worldtype) because that chunk generator
  will spawn all RS's structures at once and make /locate on certain 
  RS structure cause the server to hang forever.

##### Backend:

* Cleaned up codebase a bit.


### **(V.1.6.5 Changes) (1.16.3 Minecraft)**

##### Mod Compat:

* Fixed a crash with Applied Energistics 2.


### **(V.1.6.4 Changes) (1.16.3 Minecraft)**

##### Mod Compat:

* Fixed possible crash with some biome mods if they do 
  strange stuff with their biome provider.
  
  
### **(V.1.6.3 Changes) (1.16.3 Minecraft)**

##### Major:

* Fixed possible crash when re-entering superflat worlds or 
  blacklisting a RS structure from a vanilla dimension.

##### Igloos:

* Fixed bug where Grassy Igloos had 2 Villages and 2 Zombie Villages instead of 1 of each.
 

### **(V.1.6.2 Changes) (1.16.3 Minecraft)**

##### Configs:

* Added config option to blacklist all Repurposed Structures's structures from
  any dimension(s) you specify. Finally figured out how to do it safely!
  Config is in the Main config Biome.BiomeCategory.

##### Major:

* All structures now will spawn away from themselves with spacing being between
  the spawnrate's number and half that number (in chunks). So a spawnrate of 50
  means the structure will not be more that 50 chunk apart from another and cannot 
  be closer than 25 chunks to itself.

* The 'salt' used for structure placement has now been fully randomized due to some
  weird quirks in Java's RNG that causes different structures to have similar effects
  from their salts if the salts are too similar.
  
##### Mineshafts:

* Made it so Mineshafts will not spawn in exact same coordinates 
  if multiple are added to the same biome.

##### Igloos:

* Grassy and Stone Igloos are now Jigsaw structures! You can use datapacks to change their
  ladder and basements nbt pieces now. And their basement chests now has its own loottable!


### **(V.1.6.1 Changes) (1.16.3 Minecraft)**

##### Misc:

* Jar-in-jar'd Modmuss's Voyager to fix a rare bug that crashes servers
  running Java 11 when they try to load two chunks with structures in 
  two dimensions at the same time.

##### Fortress:

* Fixed Jungle Fortress Map not being translated properly

##### Mineshafts:

* Now has compat with Yungs Better Mineshafts. Neither Mineshafts will go missing.
  The Mineshafts will now be added to modded biomes of the right type
  even if said biomes do not have Vanilla Mineshafts. Use the config
  blacklist to prevent Repurposed Mineshafts in certain biomes.

### **(V.1.6.0 Changes) (1.16.3 Minecraft)**

##### Misc:

* Load up some structure nbt files at world startup instead of during worldgen.

* Fixed registry names of all features (not structures) to not be in minecraft namespace by mistake.

* Organized all structure loottables file path. Will break datapacks still using old path.

* Organized config screen to say what entries does instead of hiding it in tooltips.

* Adjusted spacing between two different structures in many nether structures.

##### Shipwrecks:

* Added Warped and Crimson Shipwreck to their respective Nether biomes! 
  Special thanks to miguelforge for providing the designs and nbt file!
  
* Added Nether Bricks Shipwreck to all other Nether biomes! 
  Special thanks to cannon_foddr for providing the designs and nbt file!

##### Villages:

* Saloon spawnrate increased

* Fixed name of Nether Fortress and Bastion Remnant maps from Nether Villages.

##### Pyramids:

* Fixed Badlands Pyramid's trap not always fully activating.

##### Mineshafts:

* Increased default spawnrate of End Mineshafts

### **(V.1.5.4 Changes) (1.16.2 Minecraft)**

##### Misc:

* Registered all ConfiguredFeatures and converted all Template Pools/Structure Processors 
  into JSON so they can be used or overridden more easily by datapacks.

* Significantly cleaned up code in backend again.

##### Villages:

* Adjusted the weights for straight roads and houses with beds to try
  and make Villages have more bed houses and not be just empty with only
  clusters of roads. Affects all Repurposed Structures villages.

* Fixed waterlogging issue in Animal Pen 1 in Giant Tree Taiga Village.

* Fixed Item Frames with Potions missing from Saloons in Badlands Villages.

##### Boulders:

* Giant Boulders in Giant Tree Taiga Hills and Giant Spruce Taiga Hills now
  are place lower to fit on terrain better and their surfaces are roughed up.

##### Swamp Trees:

* Increased spawnrate of Horned Swamp Trees in Swamp Hills biome

### **(V.1.5.3 Changes) (1.16.2 Minecraft)**

##### Structures:

* Fixed Warped Village not spawning.

### **(V.1.5.2 Changes) (1.16.2 Minecraft)**

##### Backend:

* Removed LibStructure and is now using Fabric API's built in structure registration. (Fabric API must be latest in order to work)

##### Fortresses:

* Fixed bug where one type of crossing in Jungle Fortress would not get filled with water properly when below sealevel.

##### Wells:

* Nether Well's maximum height lower to 120 so it cannot generate on bedrock ceiling anymore.

##### Swamp Trees:

* Swamp Trees now gets added as an addition to modded biomes instead of replacing the other mod's swamp trees.

### **(V.1.5.1 Changes) (1.16.2 Minecraft)**

##### Misc:

* Bumped LibStructure version to v1.5

##### Villages:

* Added a single Piglin Brute to Villages in Nether.

* Increased Netherrack Gold Ore and Blackstone Gold Ore rates slightly in Nether Villages.

* Slightly made Nether Villages more rare in config default.

##### Outposts:

* Slightly made Nether Outposts more rare in config default.

##### Temples:

* Slightly made Nether Temples more common in config default.

### **(V.1.5.0 Changes) (1.16.2 Minecraft)**

##### Major:

* Ported to 1.16.2 Minecraft and had to change a lot of backend stuff. Please report any bug!

### **(V.1.4.5 Changes) (1.16.1 Minecraft)**

##### Dungeons:

* Fixed crash on servers due to me accidentally using a clientsided method in the dungeon spawner code...

### **(V.1.4.4 Changes) (1.16.1 Minecraft)**

##### Dungeons:

* Added a null check for if the game is unable to find rs_spawner json files. 
  Will instead use vanilla's default mobs and write to the log about the error 
  if it fails to read the json file instead of crashing.

### **(V.1.4.3 Changes) (1.16.1 Minecraft)**

##### Villages:

* Fixed Nether Villages spawning above ceiling in Nether.

### **(V.1.4.2 Changes) (1.16.1 Minecraft)**

##### Configs:

* Set default value for all add___ToModdedBiomes to true except for Giant Boulders which will remain in vanilla biomes by default (unless manually changed in config)

* Fixed several untranslated descriptions, tooltips, and names in configs.

##### Mineshafts:

* Raised the default minimum Y height up a bit for several Mineshafts in the config.

##### Shipwrecks:

* Made End Shipwrecks spawn a bit more frequently

##### Villages:

* Made Nether Villages slightly more spaced out by default in config.

##### Outposts:

* Made Nether Outposts slightly more spaced out by default in config.

##### Temples:

* Made Nether Temples and Pyramids slightly more spaced out by default in config.

##### Other:

* Cleaned up code in backend... Made sure that Outpost, Igloo, Shipwreck, and Temple's pieces can be overridden by datapacks.

### **(V.1.4.1 Changes) (1.16.1 Minecraft)**

##### Villages:

* Center of Swamp Villages are now lowered by 1 block to fit better with terrain.

##### Misc:

* Fixed /locate to not rarely skip over a closer structure. 
  Special thanks ontrigger for finding the fix!

* Large swamp trees should not have tall grass replacing their trunks now.

##### Config:

* Added configs to allow entire RS's types of structures to be blacklisted from specified biomes.

* Split addMiscToModdedBiomes into addSwampTreeToModdedBiomes and addBoulderToModdedBiomes.

### **(V.1.4.0 Changes) (1.16.1 Minecraft)**

##### Strongholds:

-In Nether Strongholds, Black Terracotta is now removed. Instead the Nether Stronghold will be 
 made of mostly Blackstone variant blocks with some Nether Bricks and Magma Blocks scattered throughout. 
 Magma Blocks rate is cut in half now.

-In Nether Strongholds, the following blocks have been changed: 
   Dark Oak Planks is now Crimson Hyphae
   Dark Oak Fences is now Crimson Fence
   Iron Doors is now Crimson Doors
   Redstone Torches is now Soul Torches

-In Nether Strongholds Libraries now have a variety of Blackstone blocks instead of Bookcases.

##### Mineshafts:

-In Nether Mineshafts, Redstone Lamp is now replaced with Shroomlight. 

-In Nether Mineshafts, Redstone Torches is now replaced with Soul Torches.

-Fixed End Mineshafts generating completely in midair when barrensIslandsEndMineshafts config is off. 
 (Was due to End Midlands Biome not always making land)
 (When barrensIslandsEndMineshafts config is off, the mineshaft spot must have land at y = 20 or higher now)

##### Villages:

-Fixed it so that Warped and Crimson Villages cannot be within 10 chunks of any Nether Outposts now for real.

-Added missing loot to chests in Warped Villages.

-Fixed Zombified Piglins not spawning in zombie Crimson and zombie Warped Villages.

-Zombie Crimson and zombie Warped Villages should now have Soul Torches instead of Redstone Torches.

##### Outposts:

-Nether Outposts cannot be within 3 chunks of Nether Pyramids.

##### Temples:

-Fixed it so that Nether Temples cannot be within 6 chunks of any Nether Outposts now for real.

-Nether Temples cannot be within 3 chunks of Nether Pyramids.

##### Pyramids:

-Fixed it so that Nether Pyramids cannot be within 6 chunks of any Nether Outposts now for real.

##### Shipwrecks:

-Shipwrecks now shouldn't spawn ever in the void at bottom of world and less often on edges of islands. 
 (Their spot must have land at y = 20 or higher now)

### **(V.1.3.3 Changes) (1.16.1 Minecraft)**

##### Outposts:

-Mobs that initially spawn in Warped, Crimson, and Nether Brick Outposts will not despawn over time now.

##### Villages:

-Mobs that initially spawn in Warped and Crimson Villages will not despawn over time now.

##### Dungeons:

-Slightly decrease the default config spawnrate for Ocean Dungeons.

### **(V.1.3.2 Changes) (1.16.1 Minecraft)**

##### All structures:

-Fixed locateStructure speed up to truly return closest structure now and villages now uses the speed up method.

##### Strongholds:

-addStonebrickStrongholdToModdedBiomes is now true by default

-Nether Strongholds now uses Polished Blackstone Buttons instead of Stone Buttons.

-Eyes of Ender will now point to closest Stronghold of all 3 types (vanilla, RS's stonebrick, RS's nether) when more than one is present in a dimension.

##### Temples:

-All Nether Temples cannot be within 6 chunks of any Nether Outposts.

##### Pyramids:

-Nether Pyramids cannot be within 6 chunks of any Nether Outposts.

##### Villages:

-Warped and Crimson Villages cannot be within 10 chunks of any Nether Outposts.

##### Misc:

-Added config to change the chance of Diamond Ore in Giant Boulders. (Default rate reduced from 1/3000 to 1/7000 now)

-Added config to allow control of how many Giant Boulders to spawn per chunk. (Default reduced for Vanilla biomes now)

-Decreased Iron Ore rates heavily and slightly reduced Coal Ore rates in Giant Boulders.

### **(V.1.3.1 Changes) (1.16.1 Minecraft)**

##### Villages:

-Moved the Cartographer code to later to prevent crash with Better Wandering Traders mod.

-Fixed name of Jungle Fortress Map from Cartographers.

-Replaced Grass in the town center pieces of Crimson and Warped Villages with Crimson or Warped Roots.

-Removed small chance of large patches of fire in Crimson and Warped Villages.

### **(V.1.3.0 Changes) (1.16.1 Minecraft)**

##### All Structures:

-Greatly improved the /locate command's and treasure map's speed at finding Repurposed Structures's structures at any distance (including the Mineshafts too)!

##### Advancements:

-Improved the Advancement layout greatly!

##### Villages:

-Added Crimson Village to Crimson Forest biome in the Nether!

-Added Warped Village to Warped Forest biome in the Nether!

-Moved the village pool registration to during worldgen to make sure datapacks can replace the village piece nbt files.

-Added Jungle Fortress Maps to Cartographer tier 3 and 4 trades!

##### Fortress:

-Jungle Fortress now needs to be more inside a Jungle biome to spawn instead of close to the edge.

##### Shipwrecks:

-Fixed End City Map's name in End Shipwrecks.

### **(V.1.2.1 Changes) (1.16.1 Minecraft)**

##### Wells:

-Fixed Nether Wells crashing when a mod adds a biome that returns null for their top block in their surfacebuilder. Dunno why anyone would do that but someone did lol.

##### Outposts:

-Fixed Warped and Crimson Outpost's config tooltip not showing.

### **(V.1.2.0 Changes) (1.16.1 Minecraft)**

##### Major:

-Removed code from v1.1.1 and v1.1.2 as the patch didn't fix the broken vanilla world issue with missing structures. 
Upvote the issue on Mojang's issue tracker here to help get them to see the bug! https://bugs.mojang.com/projects/MC/issues/MC-194811

##### Advancements:

-Added advancements for entering all structures!

##### Config:

-Added config to allow End Mineshafts to spawn in End Islands and End Barrens biomes.

##### Strongholds:

-Both Overworld and Nether Strongholds no longer will spawn within 1600 blocks of world origin.

-Added Chains to both Overworld and Nether Strongholds and the chains has a small chance of having a Lantern or Soul Lantern!

##### Shipwrecks:

-Added End Shipwreck to End Highlands! Check out their amazing loot! (Special thanks to cannon_foddr for the design!)

##### Fortress:

-Nerfed Jungle Fortress loot significantly and changed them to use their own loottables (fortress_jungle_xxxxx_chest.json) so you cna change them with datapacks now

-Jungle Fortress's plant room will not place plants if it is below sea level now. (No more floating mushrooms items when the plant room is flooded)

##### Villages:

-Fixed Giant Tree Villages being added to vanilla's Giant Tree Taiga Hills and Giant Spruce Taiga Hills biomes when the addVillagesToModdedBiomes config is on. 

-Fixed missing zombie villager pool in Mountains Village.

##### Mineshafts:

-End Mineshafts will now spawn Endermites over time in them naturally.

##### Outposts:

-Changed name of Nether Bricks Outposts chest's loot table to outpost_nether_brick_chest.json .

##### Pyramids:

-Changed name of Badlands Pyramid chest's loot table to pyramid_badlands_chest.json .

##### Misc:

-Fixed Vines have horizontal vine part floating when stacked on each other.

-Fixed vines to not be strangely attached to nothing anymore.

-Slightly nerfed giant boulders in vanilla's Giant Tree Taiga and Giant Spruce Taiga biomes.

### **(V.1.1.2 Changes) (1.16.1 Minecraft)**

##### Major:

-Forgot to uncomment out the structures from testing...

### **(V.1.1.1 Changes) (1.16.1 Minecraft)**

##### Major:

-Attempted to fix a Mojang bug that breaks worlds and is caused by missing structures. hopefully this works...

##### Fortresses:

-Fixed vines looking weird when it tries attaching to block above in Jungle Fortress.

-Slightly increased breakage of Jungle Fortress.

### **(V.1.1.0 Changes) (1.16.1 Minecraft)**

##### Mod Compat: 

-Fixed config for adding structures to modding biomes as it was not working before.

##### Datapacks: 

-Added a new folder called data.repurposed_structures.repurposedstructures_spawners where you can specify what kind of mob spawner can be in RS's dungeons, mineshafts, strongholds, and jungle fortress! You can specify more than one mob and what the chances are of the spawner being that mob.

##### Outpost:

-Added Nether Bricks Outpost to the Nether biomes! They will spawn Piglins naturally over time.

##### Temples: 

-Renamed Nether Temple to Nether Wasteland Temple.

-Renamed Badlands Temple to Badlands Pyramid.

-Added Nether Warped, Basalt, Crimson, and Soul Temples to the other Nether Biomes! The Nether Wasteland Temple now only spawns in Wastelands.

-Nether Wasteland Temple chests now face the correct way and part of the temple made of Cracked Nether Bricks. Also fixed their Redstone puzzle that broke when Redstone mechanics were changed.

-Nether Wasteland Temples and other nether temples are now an NBT file under structures/temples/ and can be replaced with datapacks. 

-Nether Pyramids now has rotation and land will be generated around them.

-Badlands Pyramids are now made with NBT files under structures/temples/.

##### Fortresses:

-Jungle Fortresses will now very rarely spawn Wither Skeletons inside over time like how Nether Fortresses do.

-Fixed Vines in Jungle Fortress so it is not strangely placed and that they can sometimes replace the Fortress's blocks.

-Parts of the Jungle Fortress will be broken to help give a more ancient and old feel while letting more light inside.

-Jungle Fortresses's hallways will be flooded with water when below sea level.

##### Strongholds: 

-Nether Strongholds now will spawn Blaze, Wither Skeletons, Zombified Piglin, Magma Cube, and Skeletons naturally over time like Nether Fortresses do.

##### Villages: 

-Fixed Giant Tiaga, Mountains, and Swamp Village's zombie houses not spawning.

##### Wells: 

-Fixed some wells from having their fences attached to air.

-Adjusted Nether Wells so they don't hang as much on ledges.

### **(V.1.0.1 Changes) (1.16.1 Minecraft)**

##### Mineshafts: 

* End Mineshafts no longer spawn in Small End Island Biome

##### Dungeons: 

* Mushroom and Badlands dungeons now spawn again instead of Icy Dungeons.

##### Misc: 

* Moved logs in Horned Swamp tree up one to stop leave decay.

##### Configs:

* Fixed Dungeon's config tooltips

* Fixed Mineshaft's config tooltips

* Fixed Stronghold's config tooltips

* Fixed Wells's config tooltips

* Fixed Village's config tooltips

* Fixed Misc's config tooltips


### **(V.1.0.0 Changes) (1.16.1 Minecraft)**

##### Major: 

-First release of this mod for Fabric!!!!! PARTY TIME!! 

-Ported v1.7.2 Forge version to Fabric! So many tweaks and fixes was done that basically, don't compare this to the Forge version anymore lol. 