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