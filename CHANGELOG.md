# Made for Minecraft v.1.16.1

## Created by TelepathicGrunt

Welcome to the Github! If you are looking for the most recent stable version, then checkout the master branch! Branches dedicated to the latest version of Minecraft may be unstable or broken as I test and experiment so stick with the master branch instead.


------------------------------------------------
# | Repurposed Structures changelog |

## (V.1.1.3 Changes) (1.16.1 Minecraft)

##### Major:

-Removed code from v1.1.1 and v1.1.2 as the patch didn't fix the broken vanilla issue with missing structures.

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