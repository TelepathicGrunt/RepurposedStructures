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