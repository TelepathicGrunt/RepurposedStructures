### **(V.3.0.1 Changes) (1.16.5 Minecraft)**

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
