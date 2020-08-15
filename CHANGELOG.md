# Made for Minecraft v.1.16.1

## Created by TelepathicGrunt

Welcome to the Github! If you are looking for the most recent stable version, then checkout the master branch! Branches dedicated to the latest version of Minecraft may be unstable or broken as I test and experiment so stick with the master branch instead.


------------------------------------------------
# | Repurposed Structures changelog |

## (V.1.5.1 Changes) (1.16.2 Minecraft)

##### Misc:

- Bumped LibStructure version to v1.5

## (V.1.5.0 Changes) (1.16.2 Minecraft)

##### Major:

- Ported to 1.16.2 Minecraft and had to change a lot of backend stuff. Please report any bug!

## (V.1.4.5 Changes) (1.16.1 Minecraft)

##### Dungeons:

- Fixed crash on servers due to me accidentally using a clientsided method in the dungeon spawner code...

## (V.1.4.4 Changes) (1.16.1 Minecraft)

##### Dungeons:

- Added a null check for if the game is unable to find rs_spawner json files. 
  Will instead use vanilla's default mobs and write to the log about the error 
  if it fails to read the json file instead of crashing.

## (V.1.4.3 Changes) (1.16.1 Minecraft)

##### Villages:

- Fixed Nether Villages spawning above ceiling in Nether.

## (V.1.4.2 Changes) (1.16.1 Minecraft)

##### Configs:

- Set default value for all add___ToModdedBiomes to true except for Giant Boulders which will remain in vanilla biomes by default (unless manually changed in config)

- Fixed several untranslated descriptions, tooltips, and names in configs.

##### Mineshafts:

- Raised the default minimum Y height up a bit for several Mineshafts in the config.

##### Shipwrecks:

- Made End Shipwrecks spawn a bit more frequently

##### Villages:

- Made Nether Villages slightly more spaced out by default in config.

##### Outposts:

- Made Nether Outposts slightly more spaced out by default in config.

##### Temples:

- Made Nether Temples and Pyramids slightly more spaced out by default in config.

##### Other:

- Cleaned up code in backend... Made sure that Outpost, Igloo, Shipwreck, and Temple's pieces can be overridden by datapacks.

## (V.1.4.1 Changes) (1.16.1 Minecraft)

##### Villages:

- Center of Swamp Villages are now lowered by 1 block to fit better with terrain.

##### Misc:

- Fixed /locate to not rarely skip over a closer structure. 
  Special thanks ontrigger for finding the fix!

- Large swamp trees should not have tall grass replacing their trunks now.

##### Config:

- Added configs to allow entire RS's types of structures to be blacklisted from specified biomes.

- Split addMiscToModdedBiomes into addSwampTreeToModdedBiomes and addBoulderToModdedBiomes.

## (V.1.4.0 Changes) (1.16.1 Minecraft)

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

## (V.1.3.3 Changes) (1.16.1 Minecraft)

##### Outposts:

-Mobs that initially spawn in Warped, Crimson, and Nether Brick Outposts will not despawn over time now.

##### Villages:

-Mobs that initially spawn in Warped and Crimson Villages will not despawn over time now.

##### Dungeons:

-Slightly decrease the default config spawnrate for Ocean Dungeons.

## (V.1.3.2 Changes) (1.16.1 Minecraft)

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

## (V.1.3.1 Changes) (1.16.1 Minecraft)

##### Villages:

-Moved the Cartographer code to later to prevent crash with Better Wandering Traders mod.

-Fixed name of Jungle Fortress Map from Cartographers.

-Replaced Grass in the town center pieces of Crimson and Warped Villages with Crimson or Warped Roots.

-Removed small chance of large patches of fire in Crimson and Warped Villages.

## (V.1.3.0 Changes) (1.16.1 Minecraft)

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

## (V.1.2.1 Changes) (1.16.1 Minecraft)

##### Wells:

-Fixed Nether Wells crashing when a mod adds a biome that returns null for their top block in their surfacebuilder. Dunno why anyone would do that but someone did lol.

##### Outposts:

-Fixed Warped and Crimson Outpost's config tooltip not showing.

## (V.1.2.0 Changes) (1.16.1 Minecraft)

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

## (V.1.1.2 Changes) (1.16.1 Minecraft)

##### Major:

-Forgot to uncomment out the structures from testing...

## (V.1.1.1 Changes) (1.16.1 Minecraft)

##### Major:

-Attempted to fix a Mojang bug that breaks worlds and is caused by missing structures. hopefully this works...

##### Fortresses:

-Fixed vines looking weird when it tries attaching to block above in Jungle Fortress.

-Slightly increased breakage of Jungle Fortress.

## (V.1.1.0 Changes) (1.16.1 Minecraft)

##### Mod Compat: 

-Fixed config for adding structures to modding biomes as it was not working before.

##### Datapacks: 

-Added a new folder called data.repurposed_structures.rs_spawners where you can specify what kind of mob spawner can be in RS's dungeons, mineshafts, strongholds, and jungle fortress! You can specify more than one mob and what the chances are of the spawner being that mob.

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

## (V.1.0.1 Changes) (1.16.1 Minecraft)

##### Mineshafts: 

- End Mineshafts no longer spawn in Small End Island Biome

##### Dungeons: 

- Mushroom and Badlands dungeons now spawn again instead of Icy Dungeons.

##### Misc: 

- Moved logs in Horned Swamp tree up one to stop leave decay.

##### Configs:

- Fixed Dungeon's config tooltips

- Fixed Mineshaft's config tooltips

- Fixed Stronghold's config tooltips

- Fixed Wells's config tooltips

- Fixed Village's config tooltips

- Fixed Misc's config tooltips


## (V.1.0.0 Changes) (1.16.1 Minecraft)

##### Major: 

-First release of this mod for Fabric!!!!! PARTY TIME!! 

-Ported v1.7.2 Forge version to Fabric! So many tweaks and fixes was done that basically, don't compare this to the Forge version anymore lol. 