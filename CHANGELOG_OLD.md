### **(V.3.4.6 Changes) (1.16.5 Minecraft)**

##### Mod Compat:
Updated mods.toml so if Waystones is on, RS will require a newer version of Waystones if it is too out of date to prevent a crash.

Added piece limiting amount for RS - Simply Cats compat datapack.


### **(V.3.4.5 Changes) (1.16.5 Minecraft)**

##### Mod Compat:
Vanilla Mineshafts will not be removed by Repurposed Structures if Caves And Cliffs Backport mod is on.
This is because CCB added their own Mineshafts that replaces vanilla's so RS will try to not interfere with that now.

##### Configs:
Fixed a typo in a config comment.

Added thebeginning:.+ to the disallowed dimension config entry's default values to keep RS structures from that mod's dimensions by default.


### **(V.3.4.4 Changes) (1.16.5 Minecraft)**

##### Mod Compat:
Auto added vampirism:vampire_forest entries to RS's disallowedBiomes config entry to make oak village, oak witch hut, oak well, and oak outposts no longer spawn in that Vampirism biome.

##### Configs:
Adjusted wording of some config comments to be a bit more clear.

##### Igloos:
Adjusted code to make sure Grassy Igloos won't put falling blocks like sand as part of the structure's roof.

##### Villages:
Trying something new by increasing the bounding box of all RS Overworld Village's roads from 2 to 32.
This should make villages look better on mountains and hills and help reduce village roads or farms eating away the ground under houses. (In theory)

##### Misc:
Fixed a swallowed crash with the log spam filter if another mod causes missing structure piece type log spam with their own structures.


### **(V.3.4.3 Changes) (1.16.5 Minecraft)**

##### Configs:
Cleaned up RS configs to not be bloated with redundant comments and fixed a lot of incorrect/typos in some comments.


### **(V.3.4.2 Changes) (1.16.5 Minecraft)**

##### Configs:
Significantly cleaned up the config system backend code for my mod. Let me know if any config no longer works but it should be ok!

##### Mod Compat:
Blay's Waystone and Bountiful now cannot spawn more than 1 of their injected structure piece in RS villages. I did the restricting on my end.
(Note, Bountiful boards does not spawn in RS villages right now. They will fix it next update of their mod)

Blay Waystone's spawnInVillages and forceSpawnInVillages config are now read directly by Repurposed Structures and will control the waystones in RS villages.


### **(V.3.4.1 Changes) (1.16.5 Minecraft)**

##### Mineshafts:
Fixed a broken End Mineshaft check so it now properly space itself from End Strongholds and doesn't deadlock /locate command rarely.

##### Dungeons:
Fixed Mushroom Dungeons basically never spawning. Oops.

Fixed a missing block from corner of one Snow Dungeon nbt file. (no user would notice any change so idk why I am listing this fix here lol)

##### Outposts:
Changed all RS Outposts to better match Vanilla Outpost that can spawn a ton of tents and cages and stuff in 1.17.
Yeah, Mojang made a change to outposts and no one noticed lol. I don't think it was reported anywhere.
As for why this change was backported to 1.16.5 RS, I did it because it looks so much better having more tents and stuff lol.


### **(V.3.4.0 Changes) (1.16.5 Minecraft)**

##### Woodland Mansions:
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


### **(V.3.3.4 Changes) (1.16.5 Minecraft)**

##### Mod Compat:
Adjusted piece spawn limiting for Tidbits compat datapack.


### **(V.3.3.3 Changes) (1.16.5 Minecraft)**

##### Mod Compat:
Modded loot will be imported into Better Stronghold Compat datapack's loot tables now.

##### Misc:
Fixed the structure offsetting to actually work for and only for RS strongholds so they do not get cutoff by world bottom.


### **(V.3.3.2 Changes) (1.16.5 Minecraft)**

##### Dungeons:
Added shulkerBoxInEndDungeons config option to let users make End Dungeons spawn chests instead of Shulker Boxes if set to false.
Configuredfeatures are unable to be overridden by datapack due to a bad Forge hook placement. Hence this config option as a workaround.

##### Villages:
Slightly adjusted look of Church (temple) piece in Mushroom Villages.

##### Misc:
RS structures that would've been cut off by world bottom will be offset upward so that they are now longer cut off.
(Helps prevents End-themed Better Stronghold from being cut off when using Better Stronghold Compat datapack)


### **(V.3.3.1 Changes) (1.16.5 Minecraft)**

##### Villages:
Adjusted some zombie terminator pool files so that they are used now for many zombie variant RS Villages.

Adjusted many RS Village's terminator pieces to make absolutely sure they cannot spawn the wrong village's street to prevent other village's buildings from spawning in extremely rare edge cases.

Fixed Zombie Warped Village spawning non-zombified Piglins.

##### Mod Compat:
Added limit on piece spawning for future mod compat datapacks with Tidbits, Reosurceful Bees, and PneumaticCraft: Repressurized.
I'm just future proofing a bit.


### **(V.3.3.0 Changes) (1.16.5 Minecraft)**

##### Villages:
Added Mushroom Villages for Mushroom category biomes!

Fixed Mountains Villages very very rarely spawn Savanna Village pieces.

Fixed Swamp and Giant Tree Taiga Villages very very rarely spawn Plains Village pieces.

Removed a lot of the randomly placed Red Sand blocks from Badlands Villages to make them look a lot cleaner.

##### Mod Compat:
Houses added to RS's villages from the official mod compat datapacks will now only spawn more more than once for a single village.
This will greatly help prevent RS's villages from becoming overloaded with multiple modded houses from the datapacks.


### **(V.3.2.10 Changes) (1.16.5 Minecraft)**

##### Lang:
Fixed several zh_cn.json translations.


### **(V.3.2.9 Changes) (1.16.5 Minecraft)**

##### Mansions:
RS Mansions will no longer spawn at world bottom and will not make support pillars to the very bottom of the world.
This makes mansions spawn much better for floating island worlds.

##### Pyramids:
Many pyramids will now no longer make a support pillar to the very bottom of the world in worlds like floating island worldtypes.

Fixed several pyramid's check for terrain to check at correct spots for burying themselves.

##### Fortresses:
Jungle Fortress will now no longer make a support pillar to the very bottom of the world in worlds like floating island worldtypes.


### **(V.3.2.8 Changes) (1.16.5 Minecraft)**

##### Misc:
Slightly rebalanced loot tables in all structures to be a bit better.


### **(V.3.2.7 Changes) (1.16.5 Minecraft)**

##### Misc:
Improved the error reporting a bit if a datapack has a typo within its pool_additions folder for Repurposed Structures.
(If you were using the Farmer's Delight datapack or Environmental datapack from here: https://github.com/TelepathicGrunt/RepurposedStructures/releases
Please go redownload them as the Farmer's Delight pack and Environmental pack were broken. They should work properly now and not cause crashes)


### **(V.3.2.6 Changes) (1.16.5 Minecraft)**

##### Misc:
Setting an rs_spawner json file by datapack to have no mobs in it or have a total weight
of 0 will now replace the mob spawner block in that structure/feature with an air block.
A great new way to quickly remove spawners if you don't want them in RS structures/features.

##### Fortresses:
Fixed Jungle Fortress spawner not being affected by the rs_spawner datapack. Sorry about that.

##### Pyramids:
Fixed Flower Forest Pyramid's top part from sometimes having stacked flowers.

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


### **(V.3.2.5 Changes) (1.16.5 Minecraft)**

##### Misc:
Fixed crash if trying to use Java 11+ with this 1.16.5 Forge Repurposed Structures (forgot to do @mutable on an accessor mixin)

##### Igloos:
Grassy Igloo will no longer replace its blocks with air or fluid blocks if the biome's surfacebuilder uses air or fluid blocks.

##### Mineshafts:
Fixed crash if End Mineshaft is attempted to be spawned in a dimension where terrain reaches down to world bottom.


### **(V.3.2.4 Changes) (1.16.5 Minecraft)**

##### Misc:
Fixed a memory leak from the processor used on Grassy Igloos. The leak only happens if you keep finding Grassy Igloos.
Special thanks to BlueAmulet for catching this leak.


### **(V.3.2.3 Changes) (1.16.5 Minecraft)**

##### Mod compat:
Turned off the surrounding biome checks for RS structures when the Hexlands II mod is on.
This will prevent using /locate or explorer maps on Jungle Fortress from freezing the game since there isn't a big enough area of jungle to pass the original checks.
Now all RS structures should spawn when Hexlands II mod is on and active. https://www.curseforge.com/minecraft/mc-mods/hexlands-ii


### **(V.3.2.2 Changes) (1.16.5 Minecraft)**

##### Mineshafts:
End Mineshafts now will try to spawn within islands a bit better.
The minY and maxY config for End Mineshafts was deleted in favor of endMineshaftMinIslandThickness config entry.
The End Mineshaft config default value for spawnrate was bumped up to 60 due to the new more restrictive terrain checks.

##### Dungeons:
Slightly lowered Ocean Dungeons default config value for spawn attempts per chunk from 5 to 4.

##### Shipwrecks:
Crimson, Warped, and Nether Bricks Shipwreck's spawnrate default config value has been changed to make them even more common.
The old config values will not change when updating so either delete the RS Shipwreck config file or edit the rates yourself to be 18 or 19.

End Shipwrecks treasure chest cannot have more than 1 Elytra and the Elytra is now more rare (from ~1/17 chance to now 1/20)

Increased chances of a lucky banner from End Shipwreck's map chest.

##### Strongholds:
Nether Strongholds library chest cannot have more than 1 kind of explorer map at a time now.

##### Ruined Portals:
End Ruined Portals chests cannot ever have more than 1 End City explorer map in a single chest.

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


### **(V.3.2.1 Changes) (1.16.5 Minecraft)**

##### Configs:
Fixed config values not properly working for changing size of RS Mineshafts, RS Fortresses, and RS Strongholds structures. Should be working correctly now.

Added configs to allow changing of RS Villages size. Note, you will need to re-add the changes you have done to RS Village config for spawnrates if you edit it before.

Renamed netherStrongholdSizeSH and endStrongholdSizeSH configs entries to netherStrongholdSize and endStrongholdSize.


### **(V.3.2.0 Changes) (1.16.5 Minecraft)**

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

##### Configs:
Fixed typos in 2 of the default values in the allowDimensions config entry.

##### lang:
Corrected two entries in the ru_ru.json lang file (Thank you DrHesperus!)


### **(V.3.1.3 Changes) (1.16.5 Minecraft)**

##### Major:
Fixed ANOTHER serverside crash due to clientsided code called.
RS should be stable now hopefully and I ran it on server to make double sure.


### **(V.3.1.2 Changes) (1.16.5 Minecraft)**

##### Major:
Fixed Dolphin mixin crashing because I screw up again in my rush to release a fix aaaaaa


### **(V.3.1.1 Changes) (1.16.5 Minecraft)**

##### Major:
Fixed serverside crash when generating End Mineshafts and End Strongholds. Sorry about that!

##### Mansions:
All RS Mansions spawnrate default config value has been changed to make them much less common and better spaced out in the world.
The old config values will not change when updating so either delete the RS Mansions config file or edit the rates yourself to be around 225.

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


### **(V.3.1.0 Changes) (1.16.5 Minecraft)**

##### Mod Compat:
Fixed using the wrong path for importing End Remastered's loot into RS's structures chests.

##### Strongholds:
Added End Strongholds! Super rare and only spawns on within islands beyond 8000 blocks of the center of the End Dimension!
You better bring your Elytra as the entrance is actually not on the surface but underside!
But beware! It is very dangerous and massive to explore! And it also seems... off somehow... Can you figure out what is strange about it?

##### Bastions:
Buffed Underground Bastion loot a bit more and added "bonus_roll" to more of their loot tables.
If you have increased Luck attribute (usually from another mod), you may find increased loot in Underground Bastion's chests.

Wandering Traders will now have a rare chance to sell a map to an Underground Bastion.

##### Dungeons:
In End Dungeons, Shulker Boxes now won't try and spawn on top of Shulker Boxes or turn the below Shulker Box into a Pig Spawner.

##### Shripwrecks:
Crimson, Warped, and Nether Bricks Shipwreck's spawnrate default config value has been changed to make them significantly more common.
The old config values will not change when updating so either delete the RS Shipwreck config file or edit the rates yourself to be 27 or 29.

##### Ruins:
Made the default config value for Hot Land Ruins and Warm Land Ruins higher so those ruins are a bit more rare.
The old config values will not change when updating so either delete the RS Ruins config file or edit the rates yourself to be 42 or 45.

##### Mansions:
If RS Mansions are turned off in the spawnrate configs, they will remove their map trades from Cartographers.

##### Fortresses:
If Jungle Fortresses are turned off in the spawnrate configs, they will remove their map trades from Cartographers.

Increased maximum config value allowed for Jungle Fortresses size up to 30.

Fixed vertical range config for Jungle Fortresses not actually applying when changed.

##### Mineshafts:
Increased maximum config value allowed for RS Mineshaft size up to 30.


### **(V.3.0.4 Changes) (1.16.5 Minecraft)**

##### Pyramids:
Restrict y range for the Bubble Column processor so that Ocean Pyramids do not crash if it tries to spawn below y = 0 or above y = 255.


### **(V.3.0.3 Changes) (1.16.5 Minecraft)**

##### Optimization:
Optimized many RS structures so they check their piece's bounding boxes more efficiently at generation!
Some people reported having lag spikes once in a while when a RS Mineshaft spawns. This should reduce that significantly.

##### Lang:
Russian translation added by DrHesperus! Thank you!

##### Dungeons:
Fixed double chests only having loot for 1 chest instead of 2 in RS Dunegons.

##### Bastions:
Fixed Skeletons in Underground Bastions so they spawn with correct amount of health.

Adjusted bottom of Unit Rooms in Underground Bastions to be a bit less ugly in center.

##### Strongholds:
Fixed prison trapped Wither Skeletons in Nether Strongholds so they spawn with more health now.

##### Mineshafts:
Fixed Ocean Mineshaft center room sometimes have random Prismarine placed awkwardly.

##### Config:
Simplified and finalized the default configs for what structure spawns in lotr mod's dimension.
No RS Structure with mobs will now spawn in that dimension.
The modified entries will be automatically added to your current entries the first time you launch v3.0.3 RS.

Fixed spawnrate, min y, and max y configs not working for Mineshafts.

Fixed spawnrate, min y, and max y configs not working for Nether Strongholds.

Fixed spawnrate, min y, and max y configs not working for Jungle Fortresses.

##### Misc:
Fix potential issue where the structure check code may not work for preventing two specific structures from spawning on top of each other.


### **(V.3.0.2 Changes) (1.16.5 Minecraft)**

##### Villages:
Increased the default separation config value for RS's Overworld Villages to make them less cluttered in the world

RS Overworld Villages centers will not spawn within 6 chunks of Vanilla Villages or Outposts

Fixed Oak Villages placing Birch Planks when roads go into water

##### Mineshafts:
Significantly sped up /locate for RS Mineshafts

Lowered the default config height values for End Mineshafts

##### Config:
Added more dimension allow/disallow config entries.
The new entries will be automatically added to your current entries the first time you launch v3.0.2 RS.

##### Advancement/Lang:
Fixed typo with Underground Bastion advancement name and typo with Igloo advancement description


### **(V.3.0.1 Changes) (1.16.5 Minecraft)**

##### Mod Compat:
Updated loot table references so compat with End Remastered's loot now works again for newer versions of that mod

Added End Remastered eyes to the rest of the Overworld RS Pyramids and to Underground Bastions as well

##### Fortresses:
Jungle Fortresses end pieces now can spawn decoration properly

Nerfed rate of Drowned in Jungle Fortresses a bit

##### Pyramids:
Fixed Bubble Columns in Ocean Pyramids not fully creating their column after pyramid generation

Prevent Ocean Pyramids from generating above sealevel.
Instead, they may be entirely buried below sealevel if the land is above sealevel.

##### Ruins:
Removed the random Grass Block that Hot Land Ruins spawned by mistake

Fixed Dead Bush not generating in side pieces of Hot Land Ruins

Fixed Tall Grass not generating in side pieces of Warm Land Ruins

##### Bastions:
Added Stonebricks layer to bottom of Unit and Stables giant rooms in Underground Bastions to reduce chances of it getting burned down by lava

##### Misc:
Changed versioning format in jar and jar name to match semantic versioning format

Improved error message from structures that can fail to spawn their pieces due to the height range being set to be too narrow in RS's configs.


### **(V.3.0.0 Changes) (1.16.5 Minecraft)**

##### Configs:
Heavily cleaned up and improved the configs. They now exist in config/repurposed_structures-forge folder

Fixed some structures and features not working with the dimension/biome configs correctly

Added configs to allow dimensional/biome allowing or disallowing much better!

Added configs to control natural mob spawning over time in structures!

Added ModdedLoot configs so users can turn off Repurposed Structures's modded loot importing system easier for its structures.

##### Mineshafts:
Fixed issue where the ending pieces of some Mineshafts won't have decorative blocks like vines or seagrass or minecarts

##### Pyramids:
Fixed Tropical Fish dying in Mushroom Pyramid's pit at generation

Mushroom Pyramid's pit is slightly adjusted to not be so cramped

Added Chains to Ocean Pyramids so the Drowned won't walk into the Magma Block and take damage

##### Ruins:
Adjusted looks of Warm Land Ruins so it doesn't look as bad as it was before

##### Wells:
All wells now use processor lists to randomize their chances of having ores or bells.
The well's block tags and bell config entry have been deleted as they are no longer needed.

Increase default chance of Nether Wells spawning.

Wells are more likely to have a Bell now.

##### Advancements:
Forgot to add xp reward for several advancements.

##### Misc:
Cleaned up backend code. Might had introduced a bug or two. Please report the bugs you find!

Properly centered several structures so they match up with their land/water/terrain checks centered on the structure itself.


### **(V.2.7.12 Changes) (1.16.5 Minecraft)**

##### Misc:
* Fixed Vines features in various structures being able to cause a hang/deadlock.

##### Mod Compat:
* Forgot to remove the mixin mentioned in v2.7.11 changelog lol. Oops


### **(V.2.7.11 Changes) (1.16.5 Minecraft)**

##### Mod Compat:
* Turned the Noise Setting Deep Copying mixin into a high priority WorldEvent.Load.
  This is so that RS will not break a potential future Forge PR that is currently being made in Forge.


### **(V.2.7.10 Changes) (1.16.5 Minecraft)**

##### Mod Compat:
* Fixed a check so that it checks for Better Dungeons mod instead of Better Mineshafts to know when to not remove vanilla dungeons


### **(V.2.7.9 Changes) (1.16.5 Minecraft)**

##### RS Spawners:
* Made it so that setting a mob to a weight of 0 in an RS_Spawner file by datapack will no longer crash.
  Instead, that mob won't be picked anymore. I did add a more detailed error message if a weight of -1 or lower is attempted.

* Made empty rs_spawner files in datapacks now give a clear error instead.
  It'll still crash later but scroll up and you'll see the error msg explaining why the files need at least 1 entitytype with a weight of 1 or more

##### Mod Compat:
* Fixed possible crash with mods that makes their blocks implement IWaterLoggable but doesn't actually have the Waterloggable block property...


### **(V.2.7.8 Changes) (1.16.5 Minecraft)**

##### Mod Compat:
* Added code to made sure RS will not remove dungeons if BetterDungeons mod is on


### **(V.2.7.7 Changes) (1.16.5 Minecraft)**

##### Mineshafts:
* Fixed id for Swamp Mineshafts from "repurposed_structures:mineshaft_swamp_forest" to "repurposed_structures:mineshaft_swamp"


### **(V.2.7.6 Changes) (1.16.5 Minecraft)**

##### Misc:
* Decided to bite the bullet and implement the #7777 Forge PR into my mod directly https://github.com/MinecraftForge/MinecraftForge/pull/7777
  I take full responsibility for any noise setting issues this cause for other mods but honestly, no mod should be negatively hurt by it.
  Instead, this PR-implemented-as-mixin will make Repurposed Structures's and a few other mod's dimensional whitelisting/blacklisting now work properly for structures.

##### Advancements:
* All advancements has been condensed. Now there's one advancement for finding all variants of one structure type.
  For example, you have to find and enter all Repurposed Structures villages to get the advancement for RS Villages.

##### Bastions:
* Fixed Underground Bastion config max value so it is 10001. Now you can make Underground Bastion rarer and properly turn them off as I check for 10001 to know when to not spawn them.

* Fixed Bastion Treasure room chest not having loot

##### Nether Cities:
* The topmost small tower tops will now spawn Wither Skeletons with Bows

##### Pyramids:
* Mushroom Pyramid's pit is slightly adjusted to not be so cramped

* Slightly adjusted Icy Pyramid's pit to make it harder to not set off trap

* Added Chains to Ocean Pyramids so the Drowned won't walk into the Magma Block and take damage

##### Mineshafts:
* Lowered default config value for the max height of Ocean mineshafts from 31 to 26 to reduce amount of Ocean Mineshafts exposed on ocean floor.


### **(V.2.7.5 Changes) (1.16.5 Minecraft)**

##### Misc:
* Fixed serverside crash when trying to use the pool_addition merger system.


### **(V.2.7.4 Changes) (1.16.5 Minecraft)**

##### Mansions:
* Adjusted RS Mansions so their biome check doesn't conflict with Caves and Cliffs Backport mod by BlackGear.


### **(V.2.7.3 Changes) (1.16.5 Minecraft)**

##### Configs:
* Fixed Blacklisted Outpost Biomes config not being in right config section

##### Misc:
* Silenced possible logspam about missing structure pieces that was removed in v2.4.


### **(V.2.7.2 Changes) (1.16.5 Minecraft)**

##### Misc:
* Fixed a crash when a feature spawned from a structure tries to check if it is within structure bounds.

##### Villages:
* Remove entry in Zombie Jungle Village Pool that was pointing to a non-existent nbt file.

* Fixed butcher house pieces not spawning in zombie swamp villages.


### **(V.2.7.1 Changes) (1.16.5 Minecraft)**

##### Misc:
* Fixed close air off processor not separating the structure's fluid from mismatched environmental fluids as well.
  Ocean Mineshafts should no longer have floating lava at edges at times anymore.

##### Lang:
* Special thanks to invalid2tk for the Portuguese translations!

* Fixed translations not working for many of the newer structure's advancements.


### **(V.2.7.0 Changes) (1.16.5 Minecraft)**

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

* Fixed Cartographer maps to Jungle Fortresses sometimes showing a house icon instead of a green banner icon.

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

* Fixed comments on the RS Outpost configs being on the wrong config entries.

* Fixed Jungle Outpost not importing modded loot.

##### Pyramids:
* Added Jungle, Ocean, Giant Tree Taiga, Flower Forest, and Mushroom Pyramids!

* Buffed loot a bit for Badlands, Icy, and Snowy Pyramids and added Horse Armor back into their loot table.

* Snowy Pyramid Pit slightly changed to help hint at what spots are safe to stand on.

* Fixed Snowy Pyramids making a base of Red Sandstone instead of Snow Block by mistake.

* Icy Pyramid Pit trap is now redesigned to be a bit more dangerous.

* Increased the config default value for Badlands and Snowy Pyramid spawn attempt separation from 37 to 40.

##### Dungeons:
* Fixed bug that prevented RS's Dungeons from ever having 2 chests. Now they have a chance of spawning 2 chests.

##### Villages:
* Fixed Zombie Jungle Village not spawning any buildings and all Jungle Villages using the wrong terminator piece.

* Fixed Giant Tree Taiga Village not spawning butcher buildings.

* Jungle Villages now has much less Jungle Bush trees blocking houses and paths.

##### Wells:
* Added Mushroom Wells to mushroom biomes. Has a chance of Iron or Redstone Ores inside and yes, it also has an extremely rare chance of having a Bell.

##### Misc:
* Removed the giant boulder feature because it isn't close to a structure and doesn't fit this mod.

* Added dedicated mod compat with End Remastered so that their loot appears in the correct RS structures now.

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


### **(V.2.6.6 Changes) (1.16.5 Minecraft)**

##### Config:
* Fixed dimension blacklisting not working for dungeons, boulders, wells, and other features.

##### Strongholds:
* Chest Corridors now will always have a chest instead of a 1/3rd chance.

##### Witch Huts:
* Fixed Giant Tree Taiga Witch Hut having a stripped spruce log leg instead of Stone Bricks leg.


### **(V.2.6.5 Changes) (1.16.5 Minecraft)**

##### Dungeons:
* Fixed potential crash if someone set RS's dungeon spawners to not have any nbt.


### **(V.2.6.4 Changes) (1.16.5 Minecraft)**

##### Dungeons:
* Improved the RNG for Dungeon processors.

##### Strongholds:
* Moved Eyes of Ender mixin code out of mixin to be compatible with Better Strongholds.


### **(V.2.6.3 Changes) (1.16.5 Minecraft)**

##### Dungeons:
* Fixed Dungeons being able to replace Chests and Spawners of other dungeons.


### **(V.2.6.3 Changes) (1.16.5 Minecraft) (36.0.25+ Forge)**

##### Dungeons:
* Fixed Nether Dungeon spawners not being set correctly. 
  (Why can't I just have an update without things blowing up lmao)


### **(V.2.6.2 Changes) (1.16.5 Minecraft) (36.0.25+ Forge)**

##### Outposts:
* Fixed End Outposts able to spawn on the void in modded biomes.


### **(V.2.6.1 Changes) (1.16.5 Minecraft) (36.0.25+ Forge)**

##### Pyramids:
* Fixed End Pyramid pits not spawning.


### **(V.2.6.0 Changes) (1.16.5 Minecraft) (36.0.25+ Forge)**

##### Cities:
* Change Nether City's default config spawnrate from 160 to 120 to make them more common and less traveling to reach.

* Made large room with double chest in Nether Cities now a bit more common and have 3 Wither Skeletons defending it now.

* Added Wither Skeleton with enchanted bow to Nether Cities's tiny tower top pieces.

* Buffed Nether City loot a bit to have higher chance of Netherite loot and more chests.

* Removed random stray Red Nether Bricks block in one of the Nether City's stair piece.

##### Strongholds:
* Repurposed Structure's Stonebrick Strongholds will now generate only in rings around world origin like Vanilla stronghold's do.
  The rings of valid spots that RS Stonebrick Strongholds can spawn in are the same size and distance from spawn as vanilla's.
  However, there are an unlimited number of rings for RS's Stonebrick Stronghold beyond the 8 rings that Vanilla's does.
  See here for what I mean about rings: https://minecraft.fandom.com/wiki/Stronghold#Generation

* Bumped Stonebrick Stronghold's default config spawnrate from 85 to 110.

* Stonebrick Strongholds will not spawn at all if Yung's Better Strongholds is on.

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

* Fixed https://bugs.mojang.com/browse/MC-130584 by using a special processor.
  This means this mod's pyramid's chests will no longer be waterlogged if their pits replaces water in the world.

* Fixed Snowy Pyramid ignoring the pyramid biome blacklist and was using Witch Hut's instead.

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

  
### **(V.2.5.1 Changes) (1.16.5 Minecraft) (36.0.25+ Forge)**

##### Mineshafts:
* Fixed all Mineshaft height and spawnrate configs so they now actually work. 
  Apparently they were broken for months but no one told me lol.

* Changed Mineshaft spawnrate from int to double. Meaning you can do 55.34 instead of just 55.


### **(V.2.5.0 Changes) (1.16.5 Minecraft) (36.0.25+ Forge)**

##### Loot Tables:
* Added a Global Loot Modifier that now will import modded items from vanilla structure's loot tables
  into Repurposed Structures's own loot tables. (Snowy Pyramid gets all modded items that vanilla Desert Temple can have) 

##### Language:
* Fixed some Spanish translations being cut-off.

* Added German translations by elloellie and partially Google Translate! Special thanks to them!

##### Config:
* Fixed some config comments missing the phrase: '1 for spawning in most chunks and 1001 for none.'


### **(V.2.4.8 Changes) (1.16.5 Minecraft) (36.0.25+ Forge)**

##### Language:
* Fixed bundled lang files that were missing map translations for 5 mansions.

* Changed how translations are done for this mod and now you no longer need the translation datapack. 
  Just a resourcepack for your translations to take effect! This mod is now bundled with Spanish and Chinese translations files.
  The trick is that the English translations are now the keys for the other language files in order to make them work.
  
* Spanish translation provided by 16N1C0! Special thanks to them!


### **(V.2.4.7 Changes) (1.16.5 Minecraft) (36.0.25+ Forge)**

##### Config:
* Cleaned up config even more to be neater. Mineshaft spawnrate config comments is a bit more clear now.

##### Fortresses:
* Stopped scheduling duplicate fluid ticks for water in Jungle Fortresses. Also fixed a rare possible crash.

##### Dungeons:
* End Dungeons now have more Shulker Boxes and will have an additional Spawners per Shulker Box that spawns.


### **(V.2.4.6 Changes) (1.16.5 Minecraft) (36.0.25+ Forge)**

##### General:
* Added null check for logspam filtering code.


### **(V.2.4.5 Changes) (1.16.5 Minecraft) (36.0.25+ Forge)**

##### Config:
* Found a workaround for a Forge bug which was creating random bak files of my configs in the config folder.

* Cleaned up the comment formatting significantly for my configs. Should be easier to read now.

##### General:
* Found a way to silence logspam about the old structure pieces names being unknown in old worlds. Thank you MutantGumdrop for the suggestion!


### **(V.2.4.4 Changes) (1.16.5 Minecraft) (36.0.25+ Forge)**

##### Strongholds:
* So uh, I just learned that removing the Vanilla Stronghold from all biomes doesn't actually stop it from spawning.
  To fix this, turnOffVanillaStrongholds config value is now set to true by default and it will now correctly make 
  Vanilla Strongholds no longer spawn anymore. Repurposed Structures's Stonebrick Stronghold will take its place properly.
  (Yes Eyes of Ender will find RS's Strongholds as well)
  
* Moved RS Strongholds from UNDERGROUND_STRUCTURES generation stage to STRONGHOLDS. 
  Will prevent some features/structures from breaking strongholds.
  
* Fixed Nether Stronghold Libraries having floating fire.

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

##### Villages:
* Jungle Villages will now try not to spawn too close to Jungle Fortresses.

##### Mineshafts:
* Lavafalls will no longer spawn in Icy Mineshafts!


### **(V.2.4.3 Changes) (1.16.5 Minecraft)**

##### Pyramids:
* Made Badlands Pyramid rarer by default in configs.

##### Villages:
* Made Giant Tree Taiga a bit less messy and fixed several pieces not having its Stone Pressure Plates randomly replaced. 

##### Mansions:
* Fixed Mansions not spawning Illagers.


### **(V.2.4.2 Changes) (1.16.5 Minecraft) (36.0.25+ Forge)**

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

##### Mod Compat:
* Adjusted Stronghold removal code to not conflict with Yung's Better Stronghold mod when he releases it!

##### General:
* Made all the checks I have for separating some structures from other kinds of structures now ignore those checks if the structure spacing is set super low in configs.
  Now if you make all outposts spawn in every chunk by config, Repurposed Structures villages will still be able to spawn.
  
* Made all hardcoded nether height structures now uses the chunk generator's maximum height. 
  So if you use a datapack to expand the Nether's roof, these structures should automatically now use the increased range!
  Some checks for Nether sealevel now uses the chunk generator's sealevel instead of a hardcoded value.
  
* Made all structures that checks surrounding biomes for valid biomes before spawning will now ignore that check if Checkered Biome Provider is used.


### **(V.2.4.1 Changes) (1.16.5 Minecraft) (36.0.25+ Forge)**

##### Misc:
* Moved StructureBlockScreen mixin so it only applies client side. Oops. It is not suppose to run on servers.


### **(V.2.4.0 Changes) (1.16.5 Minecraft) (36.0.25+ Forge)**

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

##### Outposts:
* Added Taiga Outposts to non-snowy and non-giant Taiga biomes!

* Added Oak Outposts to non-birch and non-dark Forest biomes!

* Badlands Outposts can spawn on Badland's Plateaus now using a special heightmap check to not spawn it on plateau walls.

* Set default config for all Outposts to be slightly more rare to compensate for Outposts spawning in more biomes.

* Bumped default spawnrate of Birch, Jungle, Giant Tree Taiga, Desert, Badlands, Snowy, Oak, Taiga Outposts from 41/43 to 50 to make them slightly more rare.

* Fixed all Outposts spawning too close to vanilla villages.

##### Pyramids:
* Added Snowy Pyramids to snowy biomes!

* Bumped default config spawnrate of Badlands Pyramids from 20 to 25 to make them a bit more rare.

* Badlands Pyramids now properly create pillar of Red Sandstone in parity to vanilla's Desert Temple.

* Rounded the bottom of Nether Pyramids so they look better even when the Nether's caves and ravines carves out the land from under the pyramid.

* Nerfed Golden Swords enchantment level range from 20-39 to 10-30 in chest's loottable for Nether Pyramids.

##### Fortresses:
* Attempt to reduce the amount of floating Vines in Jungle Fortress.

* Fixed Drowned not spawning in flooded Jungle Fortresses by removing them from the mob-spawning-over-time list and now just directly
  spawns some Drowned on fortress creation. These Drowned will not despawn and will sometimes have Iron or Chainmail armor.

* Fixed Fortress maps being sold in Level 4 and 5 trades by Cartographer instead of 3 and 4.

* Bumped default spawnrate of Jungle Fortresses from 32 to 50 to make them more rare.

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

##### Villages:
* Fixed Swamp Villages bypassing surrounding biome check that would've prevented it from spawning on biome edges.

* Replaced a lot of Cobblestone with wood in Oak Villages to make them more distinct from Plains Villages and made Path Blocks more common.

* Added zombie variant of Badlands Villages.

* Bumped default config spawnrate of Birch, Oak, Dark Forest, Jungle, Swamp, Mountains, and Giant Tree Taiga Villages from 29/31/33 to 38 to make them more rare.

* Bumped default config spawnrate of Badlands Villages from 23 to 30 to make them more rare.

##### Dungeons:
* Nerfed all golden tools and armor enchantment level range from 15-30 to 5-25 in Nether Dungeon chest's loottable.

* Lowered default config spawnrate for Ocean Dungeons from 6 to 5.

##### Misc:
* Deleted 2x2 Swamp Trees. They didn't fit this mod at all. They still exist in Ultra Amplified Dimension mod!

* Made Structure Blocks name field now hold 128 characters like Jigsaw Blocks instead of 64 characters.
  Now loading up Repurposed Structures's village pieces are much easier.
  
##### General:
* Cleaned up biome selection code backend a lot and adjusted some biome selections conditions for a few structures. 

* Fixed structure pieces being registered under minecraft's namespace. 
  You may see a bit of logspam about unknown piece ID in existing worlds but this will not break your world.
  It means old structure on edges of unexplored chunks might be cutoff. Just ignore the logspam.
  
* Cleaned up en_us.json file to be nearly alphabetical. 


### **(V.2.3.4 Changes) (1.16.4 Minecraft)**

##### General:
* Improved biome targeting so some structures types get the right biome better.

##### Outposts:
* Overworld Outposts now spawn Pillagers over time. 

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
* Badlands Villages now can spawn in Badlands Plateaus with a terrain check to try and keep them from generating on plateau walls.

* Badlands Villages now only contain Desert clothed Villagers instead of sometimes having Plains clothed Villagers.

* Fixed Birch Villages so they no longer have Oak Stripped Wood, Oak Fence Gate, or Oak Stairs.
 
* Fixed Churches not having doors in Birch, Dark Forest, and Oak Villages.

* Fixed Jungle Villages having Acacia blocks by mistake in the Weaponsmith house and fixed door in Butcher house being the wrong blockstate.

* Mountain Villages farms now have waterlogged Cobblestone Slab instead of Water Blocks to prevent them from turning into ice when too cold.

* Removed extra space at the end of addNetherStrongholdToModdedBiomes config name. (Will cause config to default back to true unless changed again)

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


### **(V.2.3.3 Changes) (1.16.4 Minecraft)**

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


### **(V.2.3.2 Changes) (1.16.4 Minecraft)**

##### Outposts:
* Fixed Warped Outposts not having 6 Piglins and a Brute at first spawning.


### **(V.2.3.1 Changes) (1.16.4 Minecraft)**

##### Outposts:
* Replaced some Birch Logs with Birch Wood in Birch Outposts to make it look better. 

* Overworld Outposts now mesh much better with the surrounding terrain.

* Lowered default value for outpostBadlandsMaxChunkDistance config entry from 37 to 31
  so that the Badlands Outpost isn't as extremely rare as it was before.


### **(V.2.3.0 Changes) (1.16.4 Minecraft)**

##### New additions!:
* End themed Ruined Portals now spawn in the End dimension!
  Special thanks to miguelforge for creating them!

* Added Birch, Jungle, Icy, Snowy, Giant Tree Taiga, Desert, and Badlands Pillager Outposts!

* Added Oak Village that spawns in any forest category biome that isn't birch or dark forest!

##### Backend:
* Reworked and cleaned up backend a bit.

* Fixed many structures not using the speedy locate method.

* Fixed a bug that could still spawn Repurposed Structures's stuff in superflat.

* Ported Voyager's fix for Java 11+'s ConcurrentModificationException crash when the 
  game tries to grab multiple structure's pieces from TemplateManager at the same time.
  Source: https://github.com/modmuss50/Voyager

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

* Removed logo blur from logo in mod list

##### Configs:
* Changed "spawnrate" for dungeon entries to say "attemptsPerChunk" instead.

* Changed "spawnrate" for well entries to say "rarityPerChunk" instead.

* Changed "spawnrate" for majority of structures entries to say "maxChunkDistance" instead.

* Removed "JF" from some Jungle Fortress entries.

* Biome blacklisting configs now will ignore spaces between entries.


### **(V.2.2.11 Changes) (1.16.3 Minecraft)**

##### Shipwreck:

* Fixed map chest loot not showing up in Warped Shipwreck.


### **(V.2.2.10 Changes) (1.16.3 Minecraft)**

##### Major:
* Register to Forge registry instead of vanilla due to a breaking 
  change done by Forge. Special thanks to andrew0030, dev of 
  Pandoras Creatures, for helping with fixing Repurposed Structures!

##### Outposts:

* Nether Outposts now has 1 Piglin Brute.

##### Dungeons:

* Ocean Dungeons chests will now have Prismarine placed under 
  them if they are floating. Should reduce the amount of 
  floating chests by a lot. 
  
* Removed floating plants from above Ocean Dungeons as best I can.
  
##### Misc:

* Giant boulders in Giant Tree Taiga biomes now are more varied in size.

* Default config spawnrate of giant boulders was reduced.

  
### **(V.2.2.9 Changes) (1.16.3 Minecraft)**

##### Villages:

* Fixed typos in template_pools so that now ALL village pieces can spawn in villages!

##### Misc:

* Fixed log spam about stuff being registered multiple times. Sorry about that!

##### Config:

* Dimension blacklist will now ignore any spaces you leave between entries in it.


### **(V.2.2.8 Changes) (1.16.3 Minecraft)**

##### Misc:

* Fixed crash with some mod's custom ChunkGenerator.
  Just be careful to not use /locate in these custom dimensions
  as that could have a chance of causing the game to hang 
  depending on how they coded the ChunkGenerator. 
  Will try and think of some sort of solution to this...


### **(V.2.2.7 Changes) (1.16.3 Minecraft)**

##### Misc:

* Added check to automatically blacklist any dimension using the
  FlatChunkGenerator (superflat worldtype) because that chunk generator
  will spawn all RS's structures at once and make /locate on certain 
  RS structure cause the server to hang forever.

##### Backend:

* Cleaned up codebase a bit.

##### Mineshafts:

* Adjusted the default config values for the maximum Y spawnheight 
  of Mineshafts to match the Fabric version of Repurposed Structures.


### **(V.2.2.6 Changes) (1.16.3 Minecraft)**

##### Mod Compat:

* Fixed possible crash with some biome mods if they do 
  strange stuff with their biome provider.
  

### **(V.2.2.5 Changes) (1.16.3 Minecraft)**

##### Major:

* Fixed possible crash when re-entering superflat worlds or 
  blacklisting a RS structure from a vanilla dimension.

##### Igloos:

* Fixed bug where Grassy Igloos had 2 Villages and 2 Zombie Villages instead of 1 of each. 


### **(V.2.2.4 Changes) (1.16.3 Minecraft)**

##### Misc:

* Used the registered ConfiguredFeatures for Horned Swamp Trees properly now.


### **(V.2.2.3 Changes) (1.16.3 Minecraft)**

##### Configs:

* Added config option to blacklist all Repurposed Structures's structures from
  any dimension(s) you specify. Finally figured out how to do it safely!

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

### **(V.2.2.2 Changes) (1.16.3 Minecraft)**

##### Mineshafts:

* Overworld Repurposed Structures Mineshafts now still spawns and 
  won't conflict with Yung's Better Mineshafts when that mod is on.
  The Mineshafts will now be added to modded biomes of the right type
  even if said biomes do not have Vanilla Mineshafts. Use the config
  blacklist to prevent Repurposed Mineshafts in certain biomes.

### **(V.2.2.1 Changes) (1.16.3 Minecraft)**

##### Shipwrecks:

* Ah shoot. I forgot to add naturally spawning Wither Skeleton to the Nether Shipwrecks!

### **(V.2.2.0 Changes) (1.16.3 Minecraft)**

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

* Fixed bug that really screwed up the spawning of End Mineshafts (made them spawn waaaaaaaayyyyy too far out)


### **(V.2.1.4 Changes) (1.16.2 Minecraft)**

##### Misc:

* Updated workspace to 1.16.3 but should still work on 1.16.2. Also cleaned up 1 piece of code.

##### Villages:

* Fixed Swamp Village centers being 1 block too high. Again...


### **(V.2.1.3 Changes) (1.16.2 Minecraft)**

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


### **(V.2.1.2 Changes) (1.16.2 Minecraft)**

##### Misc:

* Fixed crash with datapack chunkgenerators.

### **(V.2.1.1 Changes) (1.16.2 Minecraft)**

##### Temples:

* Added mixin to fix Mojang bug where Enderman spawners won't show Endermen in them and spam the logs to infinity.

##### Fortresses:

* Fixed bug where one type of crossing in Jungle Fortress would not get filled with water properly when below sealevel.

##### Wells:

* Nether Well's maximum height lower to 120 so it cannot generate on bedrock ceiling anymore.

##### Swamp Trees:

* Swamp Trees now gets added as an addition to modded biomes instead of replacing the other mod's swamp trees.

### **(V.2.1.0 Changes) (1.16.2 Minecraft)**

##### Major:

* Updated to 1.16.2!

##### Dungeons:

* Fixed crash on servers due to me accidentally using a clientsided method in the dungeon spawner code...

* Added a null check for if the game is unable to find rs_spawner json files. 
  Will instead use vanilla's default mobs and write to the log about the error 
  instead of crashing when it fails to read the json files.

##### Villages:

* Added a single Piglin Brute to Villages in Nether.

* Increased Netherrack Gold Ore and Blackstone Gold Ore rates slightly in Nether Villages.

* Slightly made Nether Villages more rare in config default.

##### Outposts:

* Slightly made Nether Outposts more rare in config default.

##### Temples:

* Slightly made Nether Temples more common in config default.

### **(V.2.0.5 Changes) (1.16.1 Minecraft)**

##### Configs:

* Set default value for all add___ToModdedBiomes to true except for Giant Boulders which will remain in vanilla biomes by default (unless manually changed in config)

* Fixed giantBouldersPerChunk config minimum being 1. 0 Should've been the minimum so you can set very low spawnrates.

##### Mineshafts:

* Raised the default minimum Y height up a bit for several Mineshafts in the config

##### Shipwrecks:

* Made End Shipwrecks spawn more frequently.

##### Villages:

* Made Nether Villages slightly more spaced out by default in config.

##### Outposts:

* Made Nether Outposts slightly more spaced out by default in config.

##### Temples:

* Made Nether Temples and Pyramids slightly more spaced out by default in config.

* Fixed Badlands Pyramid config name being incorrect inside the config file.

##### Other:

* Cleaned up code in backend... Made sure that Outpost, Igloo, Shipwreck, and Temple's pieces can be overridden by datapacks.

### **(V.2.0.4 Changes) (1.16.1 Minecraft)**

##### Villages:

* Center of Swamp Villages are now lowered by 1 block to fit better with terrain.

##### Misc:

* Fixed en_us.json being missing causing untranslated names.

### **(V.2.0.3 Changes) (1.16.1 Minecraft)**

##### Pyramids:

-Fixed Pyramids not spawning.

### **(V.2.0.2 Changes) (1.16.1 Minecraft)**

##### Configs:

-Added biome blacklist for each type of structure in the configs.

##### Fortress:

-Fixed bug where the Jungle Fortress would not fully generate.

##### Misc:

* Fixed /locate to not rarely skip over a closer structure. 
  Special thanks ontrigger for finding the fix!

### **(V.2.0.1 Changes) (1.16.1 Minecraft)**

##### Mod compat:

-Fixed crash with Structure Gel API mod by moving my mixin's 
 target up one line to avoid their javascript coremod in Lake Feature.
 Crash should be no more now!

##### Misc:

-Large swamp trees should not have tall grass replacing their trunks now.

### **(V.2.0.0 Changes) (1.16.1 Minecraft)**
DO NOT LOAD THIS 1.16.1 MOD IN A 1.15.2 WORLD ALREADY MADE WITH 
THE PREVIOUS VERSION OF REPURPOSED STRUCTURES!!!
However, any world that never had Repurposed Structures on before will
work just fine in 1.16.1 with this version of it.

##### Major:
-1.16.1 Fabric version now directly ported to Forge! Report any bugs please!
See the changelog on the Fabric version to know what exactly changed and was added! 
https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/blob/1.16/CHANGELOG.md

Some additions are:
Advancements for all structures!
Nether Outposts!
Nether Pyramid!
More Nether Temples!
Nether Stronghold re-themed!
Chains in Strongholds!
Cartographers can sell maps to Jungle Fortresses!
End Shipwrecks that can have maps to unexplored End Cities!
And more!

### **(V.1.7.3 Changes) (1.15.2 Minecraft)**

##### Structures:

-Stone Igloos now reads from their Stone Igloo spawnrate config instead of Grassy Igloo's config.

-Cleaned up code that adds structures/features to biomes. 2x2 Swamp Trees will be added to all modded biomes in the swamp category regardless if they have "swamp" in the biome name or not (when the add addLargeSwampTreeModdedBiomes is turned on).

### **(V.1.7.2 Changes) (1.15.2 Minecraft)**
     
##### Datapack/Loot Table:

-Fixed Village and Wells not being overridden properly by datapacks in the world's folder.

##### Structures:

-Fixed Nether Well not replacing it's Data Structure Block with air.

-Replaced Oak Fence and Oak Stairs in Swamp Villages with Spruce Fence and Spruce Stairs.

-Extra vines will now be placed over Swamp Villages' area.

### **(V.1.7.1 Changes) (1.15.2 Minecraft)**
  
##### Structures:

-Fixed issue where Giant Taiga Village's animal pen piece and zombify version will be full of Pressure Plates instead of having it broken up and replaced with air/buttons.

-Fixed Dark Forest Village's Blacksmith from burning itself down. Whoops!


### **(V.1.7.0 Changes) (1.15.2 Minecraft)**
    
##### Configs/Loot Table:

-Fixed Stone Mineshaft turning off when you turn off Birch Mineshaft's spawnrate.

-Split the add-to-modded-biomes config for misc features into separate config entries for each feature. (giant boulders, 2x2 swamp trees) 
 
-Cleaned up some comments on Mineshaft configs.

-Changed default spawnrate for Badlands Well from 350 to 250.

-Changed default spawnrate for Nether Well from 350 to 200.

##### Structures:

-Improved Badlands Village looks! (changed Acacia blocks/Spruce Doors to Dark Oak blocks to match Badlands Mineshafts and randomized some  blocks)

-Added Birch Villages to Birch biomes.

-Added Dark Forest Villages to Dark Forest biomes.

-Added Jungle Villages to Jungle biomes.

-Added Swamp Villages to Swamp biomes.

-Added Mountains Villages to Mountains biomes.

-Added Giant Taiga Village to Giant Taiga biomes.

-Added custom loot table to Badlands and other villages for their Villager houses that doesn't have any job workstation.

-Added chests to more houses in Badlands Village.

-Fixed bug where Ocean Dungeons will not replace terrain inside them with air if they spawn above sea level.

##### NBT files:

-Changed repurposed_structures:grassy_igloo/top.nbt to repurposed_structures:igloos/grassy_top.nbt

-Changed repurposed_structures:stone_igloo/top.nbt to repurposed_structures:igloos/stone_top.nbt

-Turned all wells into nbt files and put under repurposed_structures:wells/ so anyone can override them with datapacks.

### **(V.1.6.0 Changes) (1.15.2 Minecraft)**
  
##### Configs/Loot Table:

-Split Wells, Strongholds, Mineshafts, and Dungeons configs into their own config file.
 
##### Structures:

-Added Badlands Village that spawns in Badlands biomes.

### **(V.1.5.0 Changes) (1.15.2 Minecraft)**
  
##### Configs/Loot Table:

-Added configs to change the minimum and maximum height that Strongholds, Mineshafts, and Dungeons can spawn at.
   
-Added custom loottables for dungeons, mineshafts, and nether stronghold. (Each type now has more tailored loot to fit their theme and players can change the loot with datapacks!)
   
##### Structures:

-Added a 1% chance of Creeper Spawner to Jungle/Dark Forest Dungeons.

-Fixed bug where dungeons has a rare chance of breaking through bedrock at y = 1.

-Fixed Jungle Dungeon vines breaking often.

-Added Netherwart to Nether Mineshaft's Soul Sand room.

-Nether Dungeons will now make lava flow into it instead of making floating walls of lava if it generates into lava.

-Made Chorus Plants in End Mineshafts be more grown right off the bat.

-End Mineshafts will now have walls of Purple Stained Glass Panes scattered through the Mineshaft.

-Significantly increased the amount of Endermite Spawner in End Mineshaft to balance out the better loot in the mineshaft. 
   
### **(V.1.4.4 Changes) (1.15.2 Minecraft)**
  
##### Structures:

-Changed config for dungeons to let users change the spawnrate of each type of dungeon separately.

-Changed config for mineshafts to let users change the spawnrate of each type of mineshaft separately.

-Changed config for wells to let users change the spawnrate of each type of well separately.
   
### **(V.1.4.3 Changes) (1.15.2 Minecraft)**
  
##### Structures:

-Fix crash when updating this mod from pre-1.4.0 to 1.4.x for an old world. Sorry about that!
   
### **(V.1.4.2 Changes) (1.15.2 Minecraft)**
  
##### Structures:

-Fixed Ocean Dungeons creating a ring of water slightly above sealevel if it generates in shallow water.
   
### **(V.1.4.1 Changes) (1.15.2 Minecraft)**
  
##### Config: 

-Added tags for the ores in wells. You can use datapack to override the default blocks in these tag files and even specify more than 1 block to use. All the tags are under data.repurposed_structures.tags.blocks and the files are called badlands_well_ores.json, forest_well_ores.json, mossy_well_ores.json, nether_well_ores.json, snow_well_ores.json. 

-Added tags for the plants/soil in Jungle Fortresses staircase room. You can use datapack to override the default blocks in these tag files and even specify more than 1 block to use. All the tags are under data.repurposed_structures.tags.blocks and the files are called jungle_fortress_staircase_plants.json and jungle_fortress_staircase_soils.json. Crops will be placed at a random age if they have an age property.

-Added tags for the bookshelf blocks in Nether Strongholds. You can use datapack to override the default blocks in these tag files and even specify more than 1 block to use. All the tags are under data.repurposed_structures.tags.blocks and the files are called nether_stronghold_bookshelves.json. 
   
### **(V.1.4.0 Changes) (1.15.2 Minecraft)**
  
##### Structures: 
  
-Added Grassy Igloos to Plains, Flower Forest, Forest, Birch Forest, and Dark Oak Forest! They can be hard to spot as they look like tiny hills.

-Added Stone Igloos to Giant Tree Taiga biomes! 
  
-Split up all Mineshafts into separate distinct structures in backend so now the /locate command can find specific types of Repurposed Structures Mineshafts that are within 1600 blocks of you! (the command cannot search further than that due to a hardcoded search radius in MC's code) Though this locate command won't find old Mineshafts in worlds made with previous versions of Repurposed Structures.
  
-Jungle Mineshafts and Swamp/Dark Forest Mineshafts now has Vines scattered inside!

-Changed Icey Mineshaft name to Icy Mineshaft.

-Locate command now lists Repurposed Structure's stuff as type of structure first and then the biome. Example: instead of jungle_mineshaft, locate command will show mineshaft_jungle instead.
  
##### Misc:

-Quick fix for a concurrent modification crash that I kept forgetting to fix........................................ 

   
### **(V.1.3.0 Changes) (1.15.2 Minecraft)**
  
##### Structures: 

-Added Badlands Temple! They look similar to Desert Temples but are slightly more dangerous...

-Added Ocean Dungeons that can appear underground in oceans or on the ocean floor!

-Ocean Mineshafts are now filled with water by default and uses Prismarine Walls for the arch support now. They will now also generate through water in Ocean floors, water filled ravines, and water filled caves but will cut off before entering any air space.

-Ocean Mineshafts has Drowned Spawners with Seagrass around, Hell Mineshafts has Blaze Spawner with Fire around, Icey Mineshafts has Stray Spawner with Ice around, End Mineshafts has Endermite Spawner with Chorus Fruits around that will grow and block pathways!

-Slightly increased chance of a hallway being filled with spawner block or decorative blocks.

-Fixed bug where setting the useVanillaStronghold config to true will spawn Vanilla Strongholds that are missing Hallway Chests and Silverfish Spawner in Portal Room. These two blocks will now generate correctly.

-Moved a Nether Brick block that was placed wrong and basically broke the Nether Temple's puzzle mechanism.

-Wells now generates in the Surface Structures generation stage instead of Underground Structures generation stage. Players most likely will not see a change but it's better that it is in the correct category in backend.

-Fixed bug where Snow Dungeons were replacing Zombie spawners with Stray spawners instead of replacing Skeleton spawners.

-Improved registry names of custom structure pieces in backend.

   
### **(V.1.2.2 Changes) (1.15.2 Minecraft)**
  
##### Structures: 

-Fixed crash with generating modded Strongholds when the world already has a partially made Vanilla Stronghold.

-In Stronghold's Storage Room, the Torch is now moved down one block so it rests on the Mob Spawner instead of disappearing and becoming a lit air space.
 
-Removed extra and useless Ladder from above a Dispenser in Nether Temple.

-Greatly reduced amount of Magma Blocks in Nether Temple.


  
### **(V.1.2.1 Changes) (1.15.2 Minecraft)**
  
##### Config: 

-Fixed config entry name for well spawnrates. It now says wellSpawnrate instead of dungeonSpawnrate.


  
### **(V.1.2.0 Changes) (1.15.2 Minecraft)**



##### Config: 

-Rename allowExtraSilverfishSpawnerSH config entry to allowExtraSpawnersSH as it will apply to Nether Strongholds too which uses Blaze Spawners instead of Silverfish spawners.

-Fixed bug where setting useVanillaStronghold config to true made using Eye of Ender and /locate command fail when trying to find a Vanilla Stronghold.

-Changed useVanillaStronghold config value to be false by default.
  
-Changed default value for Well spawnrate config from 800 to 350.
  
-Added config value to allow disabling Bells from spawning in Wells.


##### Structures: 

-Added Nether styled Strongholds to Nether biomes! They use a mix of Nether Bricks, Red Nether bricks, Magma, and Black Terracotta for the majority of the structure. Silverfish Spawners are replaced with Blaze Spawners and all non-library chests contains Nether Fortress loot.

-Added Nether styled Jungle Temples to Nether biomes! They use a mix of Nether Bricks, Red Nether bricks, Magma, and Black Terracotta for the majority of the structure. They also contain a Pigmen spawner, unique chest loot, and the traps uses Harming tipped arrows.

-Fixed bug where Nether Well's 5 blocks under lava are always the same blocks picked instead of being a mix of Nether Bricks, Quartz Ore, and Chiseled Quartz Block.
  
-Nether Wells now has less of a chance of spawning buried in nether terrain.
  
-Mossy Stone Wells now are properly waterlogged when underwater.
  
  
### **(V.1.1.0 Changes) (1.15.2 Minecraft)**



##### Config: 

-Added a config option to allow the option to turn off replacing Vanilla Stronghold with this mod's Stronghold. 



##### Structures/Features: 

-Fixed Crash when loading this mod with another mod that affects the biome's list of features. 

-Fixed bug where Dungeons fused together will sometimes fail to set their spawner's mob and so, they will end up with a Pig Spawner by default instead of the correct mob spawner.

-Fixed vines not generating correctly or at all in Dark Forest Dungeons.

-Added Badlands Wells to Badlands biomes with a small chance of Gold Ores inside the water like it is coins tossed into a well! 1% chance of having a Bell too.

-Added Snow Wells to snowy and icy biomes with a chance of having Lapis Ores under its ice. 1% chance of having a Bell too.

-Added Nether Wells to Nether biomes. Has 1 glowstone block and sometimes has Quartz Ore or Chiseled Quartz block under the lava. 1% chance of having a Bell too.

-Added Mossy Stone Wells to Jungles, Dark Oak, and Swamp biomes with a chance of having Emerald Ores under the water in it. 1% chance of having a Bell too.

-Added Forest Wells to Forests, Flower Forests, and Birch Forests biomes with a chance of having Iron Ores inside. 1% chance of having a Bell too.




### **(V.1.0.0 Changes) (1.15.2 Minecraft)**



##### Major: 

-First release of this mod!!!!! PARTY TIME!! 

-Moved several structures and features from Ultra Amplified Dimension mod to this mod. See Curseforge page for details on all features, structures, and config options there are for this mod.