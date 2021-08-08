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