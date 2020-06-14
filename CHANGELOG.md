# Made for Minecraft v.1.15.2

## Created by TelepathicGrunt

Welcome to the Github! If you are looking for the most recent stable version, then checkout the master branch! Branches dedicated to the latest version of Minecraft may be unstable or broken as I test and experiment so stick with the master branch instead.


------------------------------------------------
# | Repurposed Structures changelog |


## (V.1.7.0 Changes) (1.15.2 Minecraft)
    
##### Configs/Loot Table:

-Fixed Stone Mineshaft turning off when you turn off Birch Mineshaft's spawnrate.
 
##### Structures:

-Increased cactus spawnrate in Badlands Village.

-Added Birch Villages to Birch biomes.

-Added Dark Forest Village to Dark Forest biomes.

-

## (V.1.6.0 Changes) (1.15.2 Minecraft)
  
##### Configs/Loot Table:

-Split Wells, Strongholds, Mineshafts, and Dungeons configs into their own config file.
 
##### Structures:

-Added Badlands Village that spawns in Badlands biomes.

## (V.1.5.0 Changes) (1.15.2 Minecraft)
  
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
   
## (V.1.4.4 Changes) (1.15.2 Minecraft)
  
##### Structures:

-Changed config for dungeons to let users change the spawnrate of each type of dungeon separately.

-Changed config for mineshafts to let users change the spawnrate of each type of mineshaft separately.

-Changed config for wells to let users change the spawnrate of each type of well separately.
   
## (V.1.4.3 Changes) (1.15.2 Minecraft)
  
##### Structures:

-Fix crash when updating this mod from pre-1.4.0 to 1.4.x for an old world. Sorry about that!
   
## (V.1.4.2 Changes) (1.15.2 Minecraft)
  
##### Structures:

-Fixed Ocean Dungeons creating a ring of water slightly above sealevel if it generates in shallow water.
   
## (V.1.4.1 Changes) (1.15.2 Minecraft)
  
##### Config: 

-Added tags for the ores in wells. You can use datapack to override the default blocks in these tag files and even specify more than 1 block to use. All the tags are under data.repurposed_structures.tags.blocks and the files are called badlands_well_ores.json, forest_well_ores.json, mossy_well_ores.json, nether_well_ores.json, snow_well_ores.json. 

-Added tags for the plants/soil in Jungle Fortresses staircase room. You can use datapack to override the default blocks in these tag files and even specify more than 1 block to use. All the tags are under data.repurposed_structures.tags.blocks and the files are called jungle_fortress_staircase_plants.json and jungle_fortress_staircase_soils.json. Crops will be placed at a random age if they have an age property.

-Added tags for the bookshelf blocks in Nether Strongholds. You can use datapack to override the default blocks in these tag files and even specify more than 1 block to use. All the tags are under data.repurposed_structures.tags.blocks and the files are called nether_stronghold_bookshelves.json. 
   
## (V.1.4.0 Changes) (1.15.2 Minecraft)
  
##### Structures: 
  
-Added Grassy Igloos to Plains, Flower Forest, Forest, Birch Forest, and Dark Oak Forest! They can be hard to spot as they look like tiny hills.

-Added Stone Igloos to Giant Tree Taiga biomes! 
  
-Split up all Mineshafts into separate distinct structures in backend so now the /locate command can find specific types of Repurposed Structures Mineshafts that are within 1600 blocks of you! (the command cannot search further than that due to a hardcoded search radius in MC's code) Though this locate command won't find old Mineshafts in worlds made with previous versions of Repurposed Structures.
  
-Jungle Mineshafts and Swamp/Dark Forest Mineshafts now has Vines scattered inside!

-Changed Icey Mineshaft name to Icy Mineshaft.

-Locate command now lists Repurposed Structure's stuff as type of structure first and then the biome. Example: instead of jungle_mineshaft, locate command will show mineshaft_jungle instead.
  
##### Misc:

-Quick fix for a concurrent modification crash that I kept forgetting to fix........................................ 

   
## (V.1.3.0 Changes) (1.15.2 Minecraft)
  
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

   
## (V.1.2.2 Changes) (1.15.2 Minecraft)
  
##### Structures: 

-Fixed crash with generating modded Strongholds when the world already has a partially made Vanilla Stronghold.

-In Stronghold's Storage Room, the Torch is now moved down one block so it rests on the Mob Spawner instead of disappearing and becoming a lit air space.
 
-Removed extra and useless Ladder from above a Dispenser in Nether Temple.

-Greatly reduced amount of Magma Blocks in Nether Temple.


  
## (V.1.2.1 Changes) (1.15.2 Minecraft)
  
##### Config: 

-Fixed config entry name for well spawnrates. It now says wellSpawnrate instead of dungeonSpawnrate.


  
## (V.1.2.0 Changes) (1.15.2 Minecraft)



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
  
  
## (V.1.1.0 Changes) (1.15.2 Minecraft)



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




## (V.1.0.0 Changes) (1.15.2 Minecraft)



##### Major: 

-First release of this mod!!!!! PARTY TIME!! 

-Moved several structures and features from Ultra Amplified Dimension mod to this mod. See Curseforge page for details on all features, structures, and config options there are for this mod.