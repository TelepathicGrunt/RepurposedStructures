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