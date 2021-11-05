### **(V.2.5.3 Changes) (1.17.1 Minecraft)**

##### Mod Compat:
Blay's Waystone and Waystones Fabric now cannot spawn more than 1 waystone structure piece in RS villages. I did the restricting on my end.
 (Note, Blay Waystones needs to be newer than v8.1.1+0 to add their waystones to RS villages.)
 (Note, Fabric Waystones needs to be newer than v2.1.2 to add their waystones to RS mushroom villages.)

Blay Waystone's forceSpawnInVillages config is now read directly by Repurposed Structures and will force the waystones to spawn in RS villages.

Added code to do better compat with Bountiful once it ports to Fabric.


### **(V.2.5.2 Changes) (1.17.1 Minecraft)**

##### Bastions:
In Underground Bastions, Polished Andesite Stairs, Stone Brick Stairs, Stone Brick Slab, and Stone Brick Wall has 
 been replaced by Polished Deepslate Stairs, Deepslate Brick Slab, and Deepslate Tiled Wall.

##### Mineshafts:
Fixed the structure processor for several RS Mineshafts 4 way pieces to place blocks correctly. 
 Also makes Swamp Mineshaft look a bit better.


### **(V.2.5.1 Changes) (1.17.1 Minecraft)**

##### Villages:
Fixed a Badlands Village street not spawning houses or decor due to typos in the nbt file.

##### Dungeons:
Fixed Mushroom Dungeons basically never spawning. Oops.

Fixed a missing block from corner of one Snow Dungeon nbt file. (no user would notice any change so idk why I am listing this fix here lol)

##### Outposts:
Changed all RS Outposts to better match Vanilla Outpost that can spawn a ton of tents and cages and stuff in 1.17.
 Yeah, Mojang made a change to outposts and no one noticed lol. I don't think it was reported anywhere.


### **(V.2.5.0 Changes) (1.17.1 Minecraft)**

##### Mansions:
All RS Mansions now spawns pieces by using template pools. This means you can edit the pool files by datapack to
 remove rooms you do not want to spawn. Or datapack the pools to make structure processors run for mansion pieces to randomize blocks.
 Or even datapack using RS's pool_addition folder to inject new rooms into mansions!
 Just make sure your new mansion pieces matches the size of the other pieces in the same pool.
 No Jigsaw Block needed since Mansions are not Jigsaw Structures.

1x1_b5 room now spawns in RS mansions unlike vanilla which is bugged lol. https://bugs.mojang.com/browse/MC-240121

Adjusted looks of Savanna mansion to make it look nicer.

Fixed vanilla bug in RS mansions where terrain can be found floating on second and third floor hallways of the mansions. https://bugs.mojang.com/browse/MC-107594

Fixed vanilla mansion bug for RS mansions where there can be a hole in the second floor's wall to the outside if there's a 3rd floor above. https://bugs.mojang.com/browse/MC-240221

Fixed vanilla mansion bug for RS mansions where there can be a 3 block high hole on outside wall right side where 2nd floor meets the 3rd floor. https://bugs.mojang.com/browse/MC-110098

##### Outposts:
Crimson, Warped, and Nether Brick Outposts now can have target pieces with either Wither Skeleton Skulls or regular Skeleton Skulls.
 The Wither Skeleton Skull piece only has a 23% chance of appearing for a single Outpost. Before, Nether Brick Outpost had a 50% chance of spawning one.

##### Misc:
Adjusted several loot table files.

##### Compat:
Added a mixin to undo a Charm's structure processor for non-charm buildings so that Charm does not break several RS structures that uses Data Mode Structure Blocks.


### **(V.2.4.4 Changes) (1.17.1 Minecraft)**

##### Misc:
Mixin into Basalt Columns will not crash when a broken mod tries to place basalt columns outside the safe 3x3 chunk area for features.
 Will instead print into log to go find who is misplacing their basalt columns feature and ask them to fix it.
 (Note: if you are using Wyther's datapack or mod, update that to the latest so their Shattered Savanna Plateau's basalt columns are placed properly)


### **(V.2.4.3 Changes) (1.17.1 Minecraft)**

##### Mod Compat:
Modded loot will be imported into Better Stronghold Compat datapack's loot tables now.


### **(V.2.4.2 Changes) (1.17.1 Minecraft)**

##### Villages:
Slightly adjusted look of Church (temple) piece in Mushroom Villages.

##### Misc:
RS structures that would've been cut off by world bottom will be offset upward so that they are now longer cut off.
 (Helps prevents End-themed Better Stronghold from being cut off when using Better Stronghold Compat datapack)


### **(V.2.4.1 Changes) (1.17.1 Minecraft)**

##### Villages:
Fixed a street not spawning in Badlands Villages.

Adjusted some zombie terminator pool files so that they are used now for many zombie variant RS Villages.

Adjusted many RS Village's terminator pieces to make absolutely sure they cannot spawn the wrong village's street to prevent other village's buildings from spawning in extremely rare edge cases.

Fixed Zombie Giant Taiga Village spawning non-zombie pieces.

Fixed Zombie Warped Village spawning non-zombified Piglins.


### **(V.2.4.0 Changes) (1.17.1 Minecraft)**

##### Villages:
Mushroom Villages are now added to Mushroom category biomes!

Fixed Mountains Villages very very rarely spawn Savanna Village pieces.

Fixed Swamp and Giant Tree Taiga Villages very very rarely spawn Plains Village pieces.

Removed a lot of the randomly placed Red Sand blocks from Badlands Villages to make them look a lot cleaner.

##### Mod Compat:
Houses added to RS's villages from the official mod compat datapacks will now only spawn more more than once for a single village.
 This will greatly help prevent RS's villages from becoming overloaded with multiple modded houses from the datapacks.