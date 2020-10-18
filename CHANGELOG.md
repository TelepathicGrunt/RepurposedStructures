# Made for Minecraft v.1.16.3

## Created by TelepathicGrunt

Welcome to the Github! If you are looking for the most recent stable version, then checkout the master branch! Branches dedicated to the latest version of Minecraft may be unstable or broken as I test and experiment so stick with the master branch instead.


------------------------------------------------
# | Repurposed Structures changelog |

## (V.2.2.10 Changes) (1.16.3 Minecraft)

##### Dungeons:

- Ocean Dungeons chests will now have Prismarine placed under 
  them if they are floating. Should reduce the amount of 
  floating chests by a lot. 
  
  
## (V.2.2.9 Changes) (1.16.3 Minecraft)

##### Villages:

- Fixed typos in template_pools so that now ALL village pieces can spawn in villages!

##### Misc:

- Fixed log spam about stuff being registered multiple times. Sorry about that!

##### Config:

- Dimension blacklist will now ignore any spaces you leave between entries in it.


## (V.2.2.8 Changes) (1.16.3 Minecraft)

##### Misc:

- Fixed crash with some mod's custom ChunkGenerator.
  Just be careful to not use /locate in these custom dimensions
  as that could have a chance of causing the game to hang 
  depending on how they coded the ChunkGenerator. 
  Will try and think of some sort of solution to this...


## (V.2.2.7 Changes) (1.16.3 Minecraft)

##### Misc:

- Added check to automatically blacklist any dimension using the
  FlatChunkGenerator (superflat worldtype) because that chunk generator
  will spawn all RS's structures at once and make /locate on certain 
  RS structure cause the server to hang forever.

##### Backend:

- Cleaned up codebase a bit.

##### Mineshafts:

- Adjusted the default config values for the maximum Y spawnheight 
  of Mineshafts to match the Fabric version of Repurposed Structures.


## (V.2.2.6 Changes) (1.16.3 Minecraft)

##### Mod Compat:

- Fixed possible crash with some biome mods if they do 
  strange stuff with their biome provider.
  

## (V.2.2.5 Changes) (1.16.3 Minecraft)

##### Major:

- Fixed possible crash when re-entering superflat worlds or 
  blacklisting a RS structure from a vanilla dimension.

##### Igloos:

- Fixed bug where Grassy Igloos had 2 Villages and 2 Zombie Villages instead of 1 of each. 


## (V.2.2.4 Changes) (1.16.3 Minecraft)

##### Misc:

- Used the registered ConfiguredFeatures for Horned Swamp Trees properly now.


## (V.2.2.3 Changes) (1.16.3 Minecraft)

##### Configs:

- Added config option to blacklist all Repurposed Structures's structures from
  any dimension(s) you specify. Finally figured out how to do it safely!

##### Major:

- All structures now will spawn away from themselves with spacing being between
  the spawnrate's number and half that number (in chunks). So a spawnrate of 50
  means the structure will not be more that 50 chunk apart from another and cannot 
  be closer than 25 chunks to itself.
  
- The 'salt' used for structure placement has now been fully randomized due to some
  weird quirks in Java's RNG that causes different structures to have similar effects
  from their salts if the salts are too similar.

##### Mineshafts:

- Made it so Mineshafts will not spawn in exact same coordinates 
  if multiple are added to the same biome.

## (V.2.2.2 Changes) (1.16.3 Minecraft)

##### Mineshafts:

- Overworld Repurposed Structures Mineshafts now still spawns and 
  won't conflict with Yung's Better Mineshafts when that mod is on.
  The Mineshafts will now be added to modded biomes of the right type
  even if said biomes do not have Vanilla Mineshafts. Use the config
  blacklist to prevent Repurposed Mineshafts in certain biomes.

## (V.2.2.1 Changes) (1.16.3 Minecraft)

##### Shipwrecks:

- Ah shoot. I forgot to add naturally spawning Wither Skeleton to the Nether Shipwrecks!

## (V.2.2.0 Changes) (1.16.3 Minecraft)

##### Misc:

- Load up some structure nbt files at world startup instead of during worldgen.

- Fixed registry names of all features (not structures) to not be in minecraft namespace by mistake.

- Organized all structure loottables file path. Will break datapacks still using old path.

- Organized config screen to say what entries does instead of hiding it in tooltips.

- Adjusted spacing between two different structures in many nether structures.

##### Shipwrecks:

- Added Warped and Crimson Shipwreck to their respective Nether biomes! 
  Special thanks to miguelforge for providing the designs and nbt file!
  
- Added Nether Bricks Shipwreck to all other Nether biomes! 
  Special thanks to cannon_foddr for providing the designs and nbt file!

##### Villages:

- Saloon spawnrate increased

- Fixed name of Nether Fortress and Bastion Remnant maps from Nether Villages.

##### Pyramids:

- Fixed Badlands Pyramid's trap not always fully activating.

##### Mineshafts:

- Increased default spawnrate of End Mineshafts

- Fixed bug that really screwed up the spawning of End Mineshafts (made them spawn waaaaaaaayyyyy too far out)


## (V.2.1.4 Changes) (1.16.2 Minecraft)

##### Misc:

- Updated workspace to 1.16.3 but should still work on 1.16.2. Also cleaned up 1 piece of code.

##### Villages:

- Fixed Swamp Village centers being 1 block too high. Again...


## (V.2.1.3 Changes) (1.16.2 Minecraft)

##### Misc:

- Registered all ConfiguredFeatures and converted all Template Pools/Structure Processors 
  into JSON so they can be used or overridden more easily by datapacks.

- Significantly cleaned up code in backend again.

##### Villages:

- Adjusted the weights for straight roads and houses with beds to try
  and make Villages have more bed houses and not be just empty with only
  clusters of roads. Affects all Repurposed Structures villages.

- Fixed waterlogging issue in Animal Pen 1 in Giant Tree Taiga Village.

- Fixed Item Frames with Potions missing from Saloons in Badlands Villages.

##### Boulders:

- Giant Boulders in Giant Tree Taiga Hills and Giant Spruce Taiga Hills now
  are place lower to fit on terrain better and their surfaces are roughed up.

##### Swamp Trees:

- Increased spawnrate of Horned Swamp Trees in Swamp Hills biome


## (V.2.1.2 Changes) (1.16.2 Minecraft)

##### Misc:

- Fixed crash with datapack chunkgenerators.

## (V.2.1.1 Changes) (1.16.2 Minecraft)

##### Temples:

- Added mixin to fix Mojang bug where Enderman spawners won't show Endermen in them and spam the logs to infinity.

##### Fortresses:

- Fixed bug where one type of crossing in Jungle Fortress would not get filled with water properly when below sealevel.

##### Wells:

- Nether Well's maximum height lower to 120 so it cannot generate on bedrock ceiling anymore.

##### Swamp Trees:

- Swamp Trees now gets added as an addition to modded biomes instead of replacing the other mod's swamp trees.

## (V.2.1.0 Changes) (1.16.2 Minecraft)

##### Major:

- Updated to 1.16.2!

##### Dungeons:

- Fixed crash on servers due to me accidentally using a clientsided method in the dungeon spawner code...

- Added a null check for if the game is unable to find rs_spawner json files. 
  Will instead use vanilla's default mobs and write to the log about the error 
  instead of crashing when it fails to read the json files.

##### Villages:

- Added a single Piglin Brute to Villages in Nether.

- Increased Netherrack Gold Ore and Blackstone Gold Ore rates slightly in Nether Villages.

- Slightly made Nether Villages more rare in config default.

##### Outposts:

- Slightly made Nether Outposts more rare in config default.

##### Temples:

- Slightly made Nether Temples more common in config default.

## (V.2.0.5 Changes) (1.16.1 Minecraft)

##### Configs:

- Set default value for all add___ToModdedBiomes to true except for Giant Boulders which will remain in vanilla biomes by default (unless manually changed in config)

- Fixed giantBouldersPerChunk config minimum being 1. 0 Should've been the minimum so you can set very low spawnrates.

##### Mineshafts:

- Raised the default minimum Y height up a bit for several Mineshafts in the config

##### Shipwrecks:

- Made End Shipwrecks spawn more frequently.

##### Villages:

- Made Nether Villages slightly more spaced out by default in config.

##### Outposts:

- Made Nether Outposts slightly more spaced out by default in config.

##### Temples:

- Made Nether Temples and Pyramids slightly more spaced out by default in config.

- Fixed Badlands Pyramid config name being incorrect inside the config file.

##### Other:

- Cleaned up code in backend... Made sure that Outpost, Igloo, Shipwreck, and Temple's pieces can be overridden by datapacks.

## (V.2.0.4 Changes) (1.16.1 Minecraft)

##### Villages:

- Center of Swamp Villages are now lowered by 1 block to fit better with terrain.

##### Misc:

- Fixed en_us.json being missing causing untranslated names.

## (V.2.0.3 Changes) (1.16.1 Minecraft)

##### Pyramids:

-Fixed Pyramids not spawning.

## (V.2.0.2 Changes) (1.16.1 Minecraft)

##### Configs:

-Added biome blacklist for each type of structure in the configs.

##### Fortress:

-Fixed bug where the Jungle Fortress would not fully generate.

##### Misc:

- Fixed /locate to not rarely skip over a closer structure. 
  Special thanks ontrigger for finding the fix!

## (V.2.0.1 Changes) (1.16.1 Minecraft)

##### Mod compat:

-Fixed crash with Structure Gel API mod by moving my mixin's 
 target up one line to avoid their javascript coremod in Lake Feature.
 Crash should be no more now!

##### Misc:

-Large swamp trees should not have tall grass replacing their trunks now.

## (V.2.0.0 Changes) (1.16.1 Minecraft)
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

## (V.1.7.3 Changes) (1.15.2 Minecraft)

##### Structures:

-Stone Igloos now reads from their Stone Igloo spawnrate config instead of Grassy Igloo's config.

-Cleaned up code that adds structures/features to biomes. 2x2 Swamp Trees will be added to all modded biomes in the swamp category regardless if they have "swamp" in the biome name or not (when the add addLargeSwampTreeModdedBiomes is turned on).

## (V.1.7.2 Changes) (1.15.2 Minecraft)
     
##### Datapack/Loot Table:

-Fixed Village and Wells not being overridden properly by datapacks in the world's folder.

##### Structures:

-Fixed Nether Well not replacing it's Data Structure Block with air.

-Replaced Oak Fence and Oak Stairs in Swamp Villages with Spruce Fence and Spruce Stairs.

-Extra vines will now be placed over Swamp Villages' area.

## (V.1.7.1 Changes) (1.15.2 Minecraft)
  
##### Structures:

-Fixed issue where Giant Taiga Village's animal pen piece and zombify version will be full of Pressure Plates instead of having it broken up and replaced with air/buttons.

-Fixed Dark Forest Village's Blacksmith from burning itself down. Whoops!


## (V.1.7.0 Changes) (1.15.2 Minecraft)
    
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