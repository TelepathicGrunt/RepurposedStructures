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