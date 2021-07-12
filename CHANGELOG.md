### **(V.2.1.0 Changes) (1.17.1 Minecraft)**

##### Mineshafts:
* Redesigned RS Mineshafts so they all now can have pillars or chain supports when in midair! (except for End Mineshafts)

* All overworld RS Mineshafts (except for Ocean Mineshafts) will now no longer place blocks that are in view of the sky to match vanilla Mineshaft behavior better
  
* Nerfed Blue Ice rates in Icy Mineshafts.

* Adjusted rates of Shroomlight in Warped Mineshafts

##### RS Spawners:
* Made it so that setting a mob to a weight of 0 in an RS_Spawner file by datapack will no longer crash. 
  Instead, that mob won't be picked anymore. I did add a more detailed error message if a weight of -1 or lower is attempted.

* Made empty rs_spawner files in datapacks now give a clear error instead.
  It'll still crash later but scroll up and you'll see the error msg explaining why the files need at least 1 entitytype with a weight of 1 or more

##### Mod Compat:
* Fixed possible crash with mods that makes their blocks implement Waterloggable but doesn't actually have the Waterloggable block property...

##### Configs:
* Fixed possible race condition with Fabric API that breaks RS's structure dimension deny list config.


### **(V.2.0.6 Changes) (1.17.0 Minecraft)**

##### Villages:
* Fixed Oak Villages using Birch Village pieces by mistake. Oops...


### **(V.2.0.5 Changes) (1.17.0 Minecraft)**

##### Mineshafts:
* Fixed wall blocks or rails in Ocean Mineshafts not always being waterlogged
  
* Fixed walls blocks in Ocean Mineshafts sometimes being in a weird state

##### Mod Compat:
* Added Cubic Chunks file to make Mineshafts and Nether Strongholds spawn repeatedly downward when Cubic Chunks is on

* Added code to made sure RS will not remove dungeons or mineshafts if BetterDungeons or BetterMineshafts mod is on


### **(V.2.0.4 Changes) (1.17.0 Minecraft)**

##### Villages:
* Fixed some villages not spawning or having trouble spawning when William Wythers Overhauled datapack/mod is on

##### Ruins:
* Fixed a Warm Land Ruins piece so it can spawn again


### **(V.2.0.3 Changes) (1.17.0 Minecraft)**

##### Mansions:
* Adjusted the default config value for mansion's average chunk distance between spawn attempts so mansions are much more rarer

##### Mineshafts:
* Added Glowberries as possible Chest Minecart loot for Birch, Dark Forest, Jungle, Savanna, Stone, Swamp, and Taiga Mineshafts
  
* Fixed issue where the ending pieces of some Mineshafts won't have decorative blocks like vines or seagrass

##### Ruins:
* Adjusted looks of Warm Land Ruins so it doesn't look as bad as it was before
  
* Fixed Warm Land Ruins not having enough Tall Grass placed and Hot Land Ruins not having enough Dead Bush placed
  
* Removed the random Grass Block that Hot Land Ruins spawned by mistake

##### Pyramids:
* Fixed Tropical Fish dying in Mushroom Pyramid's pit at generation

##### Bastions:
* Underground Bastion maps are now sold by Wandering Traders rarely and very expensive

##### Advancements:
* Added experience reward for completing each of RS's advancements

##### Misc:
* Redid a lot of code behind the scenes to clean up the codebase significantly. Hopefully I didn't miss any bugs.
  
* Maps to Jungle Fortress, Underground Bastion, and all RS Mansions will no longer be added to Cartographers and 
  Wandering Trader's trades if you set the structure's average chunk distance to the 'turn off' value in the config.


### **(V.2.0.2 Changes) (1.17.0 Minecraft)**

##### Mineshafts:
* Fixed Birch Mineshafts being flooded with water.

##### Pyramids:
* Fixed chests in dry Pyramids from being flooded if they replace a water block in the world.

##### Configs:
* Added agape mod's dimension to the dimension disallow config's default value.


### **(V.2.0.1 Changes) (1.17.0 Minecraft)**

##### Misc:
* Fixed my fabric.mod.json file so it now requires an up-to-date Fabric API in order to prevent issues.

##### Pyramids:
* Changed the Drowned used for debugging in Ocean Pyramid to not have enchanted boots anymore or green armor.
  
* Fixed Icy Pyramid's chests being able to be opened safely. Now it is actually harder to not trigger trap lol. 


### **(V.2.0.0 Changes) (1.17.0 Minecraft)**

##### Major:
* Updated to 1.17!!! Note, putting this mod on a world made with 1.16.5 or older Repurposed Structures may cause weird stuff.

* File paths in the resources/data folder has been significantly redone to be more consistent and cleaner. 
  1.16.5 or older datapacks for Repurposed Structures may not work anymore and have to be updated.

* Added mixin to make a deep copy of the Noise Settings of each dimension to assign to those dimensions.
  This may automatically fix the dimension whitelisting/blacklisting that some structure mods do so it now works properly.

##### Configs:
* Heavily cleaned up and improved the configs. Fixed some structures and features not working with the dimension/biome configs correctly.
  For allow/disallowing structures in dimension/biome, see the actual config file itself as Cloth Config API cannot show maps in GUI screen.
  NOTE: As of v5.0.34 Cloth Config API, the maps for dimension/biome allowing and disallowing is not working. Please wait for future Cloth update to fix this.

* Added configs to control natural mob spawning over time in structures!

##### Advancements:
* All advancements has been condensed. Now there's one advancement for finding all variants of one structure type. 
  For example, you have to find and enter all Repurposed Structures villages to get the advancement for RS Villages.

##### Bastions:
* Underground Bastions now uses Deepslate instead of Stone Bricks
  
* Fixed Bastion Treasure room chest not having loot

* Copper Ore spawns in Underground Bastions like Redstone Ores and Redstone Block do
  
* Nerfed the rate of randomly placed Redstone Block and Redstone Ore in Underground Bastions

* Added Deepslate layer to bottom of Unit and Stables giant rooms in Underground Bastions to reduce chances of it getting burned down by lava

##### Cities:
* In Nether Cities, the topmost small tower tops will now spawn Wither Skeletons with Bows
  
* Made the average spawn distance config's default value for Nether Cities be a bit smaller to make them be more common

##### Mineshafts:
* Ocean Mineshafts now uses waterlogged Rails instead of waterlogged 
  
* Swamp Mineshaft's floor now uses Moss Blocks instead of Grass Blocks

##### Pyramids:
* Jungle Pyramids now has a bit of Moss Carpet
  
* Flower Forest Pyramids now has Moss Block, Spore Blossom, Small Dripleaf, and Flowering Azalea

* Mushroom Pyramid's pit is slightly adjusted to not be so cramped

* Slightly adjusted Icy Pyramid's pit to make it harder to not set off trap
  
* Added Chains to Ocean Pyramids so the Drowned won't walk into the Magma Block and take damage
  
* Made Icy and Flower Forest Pyramid's average spawn distance config's default value be slightly smaller to make them be more common 

* Overworld land pyramids will try to not spawn in middle of large bodies of 

##### Mansions:
* Made the average spawn distance config's default value for many mansions be a bit larger to space out the mansions more

##### Ruins:
* Overworld land ruins will try to not spawn in middle of large bodies of water

##### Igloos:
* Igloos will try to not spawn in middle of large bodies of water

##### Villages:
* Swamp Villages now utilizes Moss Blocks heavily and has more Vines

##### Outposts:
* Jungle Outposts tents is made of Moss Blocks and has Moss Carpet under them
  
* Made the average spawn distance config's default value for Overworld outposts be a tiny bit larger to space out the outposts more

##### Dungeons:
* Fixed vanilla dungeons being removed from cold, frozen, warm, and lukewarm ocean biomes.

##### Wells:
* All wells now use processor lists to randomize their chances of having ores or bells. 
  The well's block tags and bell config entry have been deleted as they are no longer needed.
  
* Increase default chance of Nether Wells spawning.

##### Pool Additions:
* Will now print out to the logs if a merging pool has a typo in its nbt file entries.