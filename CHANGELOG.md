### **(V.6.1.4 Changes) (1.19.0 Minecraft)**

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
 Also, update to v2.3 or newer Forge William Wythers' Overhauled Overworld have maximum compat with RS.

Allowed Flower Forest Pyramid to spawn in more Terralith biomes.

Fixed import_modded_loot GLM so it actually runs and properly imports modded loot into RS chests.


### **(V.6.1.3 Changes) (1.19.0 Minecraft)**

#### Misc:
Updated to work on v41.0.98 Forge.


### **(V.6.1.2 Changes) (1.19.0 Minecraft)**

#### Misc:
Updated to work on v41.0.85 Forge.


### **(V.6.1.1 Changes) (1.19.0 Minecraft)**

#### Loot:
Fixed it so Mangrove Outpost, Mangrove Mansion, and Mangrove Igloo can import modded loot.


### **(V.6.1.0 Changes) (1.19.0 Minecraft) (Config Datapack updated)**

#### Igloos:
Added Mangrove Igloos to Mangrove Swamp biome! Has a twist in its basement that sometimes spawns.

Organized RS Igloo worldgen files into folders to clean up.

#### Outposts:
Added Mangrove Outposts to Mangrove Swamp biome!

#### Mansions:
Added Mangrove Mansions to Mangrove Swamp biome!

Changed color of Birch Mansion's secret banner.

Hooked up all RS mansion's spider spawner piece to now be controlled by rs_spawner files for the mob type.

#### Witch Huts:
Added Mangrove Witch Huts to Mangrove Swamp biome! 
