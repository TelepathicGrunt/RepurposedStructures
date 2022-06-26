### **(V.6.0.7 Changes) (1.19.0 Minecraft)**

#### Mansions:
Added Allays to RS mansion prison pieces. Each prison room cell can spawn 1 to 3 Allays.

#### Outposts:
Added Allays to RS outpost cage pieces. The Allay cages have a chance of spawning.

Added possible "Singing" Goat Horn to all RS Outpost loot tables except for End Outpost.

#### Ruins:
Made Overworld Ruins more spread out and uncommon instead of everywhere.

#### Misc:
Added `repurposed_structures:no_waterfalls` tag to prevent waterfalls in Snowy Mansion, Snowy Pyramid, and Snowy Outposts.


### **(V.6.0.6 Changes) (1.19.0 Minecraft)**

#### Processors:
Significantly improved my spawner_randomizing_processor so it now can specify all nbt for spawners.
 This will be utilized to make RS be better with spawner behavior.
 Currently, all overworld structures with spawners now will let their mobs spawn up to light level 7 to make
 the mobs spawn more consistently in the presence of nearby light sources that spawns within RS structures.

#### Mineshafts:
Fixed a typo in several rs_pieces_spawn_counts files with Mineshafts.
 Now Mineshafts should always have 1 spawner tunnel.
 Config datapack is updated with this fix as well.

#### Misc:
Fixed a bug where some of my rng stuff was not being randomized properly at y = 0.


### **(V.6.0.5 Changes) (1.19.0 Minecraft)**

#### Misc:
Fixed Minecraft and Pneumaticcraft tags in my mod to use the correct path so they actually work now.
 Config datapack is updated with this fix too.

#### Dungeons:
Significantly nerfed the chances of Vex spawners in Swamp Dungeons and Dark Forest Dungeons.
 They are now at 1% chance for the spawners to be a vex spawner instead of like 16% in the past.
 Override the rs_spawner files in the config datapack to remove them entirely if you don't want them at all.


### **(V.6.0.4 Changes) (1.19.0 Minecraft)**

#### Dungeons:
Changed dungeons to use `repurposed_structures:unlimited_count` so you can set dungeon spawnrates insanely high (like a thousand or so)
 Config datapack is updated with these changes.

Fixed Ocean Dungeons not spawning.

#### Wells:
Added `repurposed_structures:unlimited_count` to wells to make turning them off easier (just set count to 0)
 Config datapack is updated with these changes.

#### Bastions:
Allow Underground Bastions to spawn in Deep Dark biome and increased range of structure check to prevent it from spawning in Ancient Cities.

#### Villages:
Dark Forest Village now has Red and Black Beds instead of White and Yellow Beds.

Mountain Villages now spawns Stone Brick Stairs instead of Cobblestone Stairs at various spots.

#### Mod Compat:
Added more Terralith biomes to RS biome tags for RS structures to spawn in.


### **(V.6.0.3 Changes) (1.19.0 Minecraft)**

#### Dungeons:
Fixed RS Dungeons not spawning. I got no one to blame but myself.

#### Wells:
Fixed RS Wells not spawning.

#### Mineshafts:
Fixed End Mineshaft's avoid structure set tag being misnamed and thus, broken.

#### Lang:
ru_ru.json lang file updated by DrHesperus!


### **(V.6.0.2 Changes) (1.19.0 Minecraft)**

#### Misc:
Fixed crash when Waystone mod is on or RS Compat Datapacks are on.


### **(V.6.0.1 Changes) (1.19.0 Minecraft)**

#### Loot Tables:
Fixed modded loot importing mixin to not nuke loot tables by mistake... oops.

#### Pyramids:
Fixed Pyramids spawning underwater when they are land pyramids (hopefully got this fixed)


### **(V.6.0.0 Changes) (1.19.0 Minecraft)**

#### Major:
Updated to 1.19! Beta as there could be issues. Please report anything funky or broken!
 Note: previous config datapacks will not work as many changes were done to structure json by Mojang.
 The updated RS Config datapack for 1.19 will be posted here: https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/releases/tag/2.0.1

Version 5.x.x is skipped to 6.0.0 so this fabric version syncs up with the forge versioning. 
 Makes it easier to see who's up-to-date at a glance in logs.

#### Config:
The Dungeons and Wells config files that used to be in the mod config folder are now deleted.
 Instead, they are now made with json and can be disabled by editing the config datapack listed above.

#### Dungeons:
Fixed End Dungeons always having 2 Shulker Boxes. Now it's typically 1 but can be 0 or 2 at times.

#### Mineshafts:
Swamp Mineshaft now uses blocks from Mangrove Swamp and will spawn in that biome.

#### Villages:
Swamp Village now uses blocks from Mangrove Swamp and will spawn in that biome.