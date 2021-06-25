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
  
##### Advancements:
* All advancements has been condensed. Now there's one advancement for finding all variants of one structure type. 
  For example, you have to find and enter all Repurposed Structures villages to get the advancement for RS Villages.

##### Bastions:
* Underground Bastions now uses Deepslate instead of Stone Bricks
  
* Fixed Bastion Treasure room chest not having loot

* Copper Ore spawns in Underground Bastions like Redstone Ores and Redstone Block do
  
* Nerfed the rate of randomly placed Redstone Block and Redstone Ore a 
  
* Added Deepslate layer to bottom of Unit and Stables giant rooms in Underground Bastions to reduce chances of it getting burned down by lava

##### Nether Cities:
* The topmost small tower tops will now spawn Wither Skeletons with Bows

##### Mineshafts:
* Ocean Mineshafts now uses waterlogged Rails instead of waterlogged 
  
* Swamp Mineshaft's floor now uses Moss Blocks instead of Grass Blocks

##### Igloos:
* Grassy Igloos are now made of Moss Blocks

##### Pyramids:
* Jungle Pyramids now has a bit of Moss Carpet
  
* Flower Forest Pyramids now has Moss Block, Spore Blossom, Small Dripleaf, and Flowering Azalea

* Mushroom Pyramid's pit is slightly adjusted to not be so cramped

* Slightly adjusted Icy Pyramid's pit to make it harder to not set off trap

##### Villages:
* Swamp Villages now utilizes Moss Blocks heavily and has more Vines

##### Outposts:
* Jungle Outposts tents is made of Moss Blocks and has Moss Carpet under them

##### Wells:
* All wells now use processor lists to randomize their chances of having ores or bells. 
  The well's block tags and bell config entry have been deleted as they are no longer needed.
  
* Increase default chance of Nether Wells spawning.

##### Pool Additions:
* Will now print out to the logs if a merging pool has a typo in its nbt file entries.