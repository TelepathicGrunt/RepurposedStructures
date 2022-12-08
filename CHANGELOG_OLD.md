### **(V.6.3.9 Changes) (1.19.0-1.19.2 Minecraft)**

#### Ancient Cities:
Fixed End Ancient Cities having Barrier Blocks somehow on the top parts of two pieces.


### **(V.6.3.8 Changes) (1.19.0-1.19.2 Minecraft) (Config Datapack updated)**

#### Ancient Cities:
Fixed End Ancient Cities able to spawn on dragon island in center of End dimension.
Now it is restricted to only spawn past 1000 blocks of world center.

#### Villages:
Added pillar support for Crimson and Warped Villages so they stop looking funky when
the Nether's ravine carver eats away the land under the houses and roads.

#### Outposts:
Added pillar support for Crimson, Warped, and Nether Brick Outposts so they stop looking funky when
the Nether's ravine carver eats away the land under the tower, tents, cages, and stuff.

#### Ruins:
Added mini pillar support for Nether Ruins so they look less funky when
the Nether's ravine carver eats away the land under the pieces.


### **(V.6.3.7 Changes) (1.19.0-1.19.2 Minecraft)**

#### End Structures:
Fixed several End Structures not fully generating properly.

#### Villages:
Non-zombified Badland Village houses now has pillar support to first land.
(Was a test. Will extend this feature to all remaining RS village houses. Is a lot of work to get working)


### **(V.6.3.6 Changes) (1.19.0-1.19.2 Minecraft)**

#### Villages:
Removed a Badlands house spawning accidentally in Birch Village.


### **(V.6.3.5 Changes) (1.19.0-1.19.2 Minecraft) (Config Datapack updated)**

#### Strongholds:
Fixed Nether Strongholds not spawning all of its pieces.


### **(V.6.3.4 Changes) (1.19.0-1.19.2 Minecraft) (Config Datapack updated)**

#### Ruins:
Nether Ruins are slightly more common now.

Nether Ruins chests has a higher chance of a map and now can have a map to a Nether Ancient City.

#### Ancient Cities:
Ocean Ancient Cities are now very slightly more common.

#### Cities:
Overworld Cities are now very slightly more common.

#### Monuments:
The 3 Overworld Monuments are now very slightly more common.

#### Villages:
Crimson Villages and Warped Villages Cartographer houses has a higher chance of spawning now.

Slightly adjusted one Badlands Village house.

Added pillars downward to first land for many village lamposts and decor. This should prevent floating lamp posts from legacy cave carvers.


### **(V.6.3.3 Changes) (1.19.0-1.19.2 Minecraft)**

#### Mod Compat:
Fixed LootJS incompatibility.


### **(V.6.3.2 Changes) (1.19.0-1.19.2 Minecraft)**

#### Strongholds:
Fixed Nether Strongholds allowing Nether, Crimson, and Warped Mineshafts to place blocks inside it.

#### Misc:
Fixed crash with datapacks that are using filters.

Fixed min_y_allowed setting in structure json not working. Necessary for Repurposed Structures - Better Strongholds compat data pack to work

#### Misc:
Updated uk_ua.json file. Thank you Smollet777!


### **(V.6.3.1 Changes) (1.19.0-1.19.2 Minecraft)**

#### Temples:
Fixed Powered Rail/Detector Rail rotation fix mixins blowing up.

Fixed Taiga Temple being filled with water when spawned in a lake or water.


### **(V.6.3.0 Changes) (1.19.0-1.19.2 Minecraft) (Config Datapack updated)**

#### Villages:
Ocean Village now added! Spawns only in deep ocean biomes. Explore the bottom of the sea to see this village's tragic ending!
Can be located with Dolphins

#### Temples:
Added Taiga Temple! Spawns in most taiga based biomes.

Added Ocean Temple! Spawns only in deep ocean biomes and can be found with Dolphins.

#### Pyramids:
Made many Overworld Pyramids slightly more spaced out on their spawn attempts (slightly more uncommon)

#### Bastions:
Underground Bastion are now a bit more common

#### Ruins:
Land Ruins chests now has an increased chance of having a map to an Underground Bastion

Fixed all 4 Land Ruins types having difficulty spawning

Fixed Icy Land Ruins placing snow layers on top of invalid blocks. Powder Snow may sometimes be two blocks deep now.


### **(V.6.2.3 Changes) (1.19.0-1.19.2 Minecraft) (Config Datapack updated)**

#### Loot Tables:
Cleaned up and organized loot table files. Rebalanced lucky banner chances. Added more lucky banners variants!


### **(V.6.2.2 Changes) (1.19.0-1.19.2 Minecraft)**

#### Mod Compat:
Improved compat with Villager Configs by changing the internal data type for my custom map trade class that Villager Configs serializes.


### **(V.6.2.1 Changes) (1.19.0-1.19.2 Minecraft) (Config Datapack updated)**

#### Ancient Cities:
End Ancient Cities's End Gateway Block will now teleport you to 0,0 coordinate in the End dimension on topmost land at that spot.


### **(V.6.2.0 Changes) (1.19.0-1.19.2 Minecraft) (Config Datapack updated)**

#### Ancient Cities:
Added an Ocean, Nether, and End variants of the Ancient City! Very rare structure!
Ocean Ancient City can be located with a map from Wandering Traders!

#### Loot Tables:
All RS loot table's secret lucky banner pool has been standardized and rebalanced.

More secret banners have been added to all RS loot table's lucky banner pool!

#### Advancements:
Changed the name and description of many advancements for RS so it is less boring lol.

#### Mod Compat:
Fixed villages and a few other structures that would not spawn if William Wyther's Overhauled Overworld mod/datapack is on.
Also, please update WWOO to v3.1.2 for best compatibility with Repurposed Structures.


### **(V.6.1.1 Changes) (1.19.0 Minecraft)**

#### Mansions:
All RS Mansions now use template pool files to spawn the Illagers.
Now it is possible to easily add new mobs to any specific RS mansion by datapack!
Also made sure that RS Mansion rooms will have same number of Illagers across all biome variants of it.
(Some RS Mansions now will spawn slightly more Illagers than before in rooms that were missing Illagers)

Made Mangrove Mansions have their own structure set and made them much more common.
Should generally be around 6000-15000 blocks away on average now instead of like 20000-40000 blocks away.

#### Pyramids:
Redid the Icy Pyramid trap so it is far more stable and less likely to set off the trap due to Observers not being usable in structures safely.

#### Mineshafts:
Support pillars/chains can now replace many more non-solid blocks and is controlled by the `repurposed_structures:mineshaft_support_replaceables` block tag.
This means RS Mineshafts have pillars/chains support more often in Dripstone Caves and Lush Caves.

#### RS Spawners:
Fixed bug where removing all mobs for an RS Spawner will cause air pockets to spawn in place of the spawner in
Ocean Mineshafts and Ocean Dungeons. Also causes Pig Spawners to spawn in End Dungeons.
Now, removing all mobs will remove the spawner block properly without placing air pockets or Pig Spawners
for Ocean Mineshafts, Ocean Dungeons, and End Dungeons.

#### Mod Compat:
Added some William Wythers' Overhauled Overworld biomes to RS's biome tags to improve compat.
Also, update to v3.0.3 or newer Fabric William Wythers' Overhauled Overworld have maximum compat with RS.

Allowed Flower Forest Pyramid to spawn in more Terralith biomes.

Fixed issue where Icy RS Structures could spawn in Frozen Ocean, Frozen River, and Deep Frozen Ocean due to using c:icy tag.
Don't ask why Fabric included those biomes in icy tag.


### **(V.6.1.0 Changes) (1.19.0 Minecraft)**

#### Misc:
Ported to Quilt Modloader