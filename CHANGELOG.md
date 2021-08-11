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
