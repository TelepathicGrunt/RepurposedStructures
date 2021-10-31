### **(V.3.4.0 Changes) (1.16.5 Minecraft)**

##### Woodland Mansions:
All RS Mansions now spawns pieces by using template pools. This means you can edit the pool files by datapack to
 remove rooms you do not want to spawn. Or datapack the pools to make structure processors run for mansion pieces to randomize blocks.
 Or even datapack using RS's pool_addition folder to inject new rooms into mansions!
 Just make sure your new mansion pieces matches the size of the other pieces in the same pool. 
 No Jigsaw Block needed since Mansions are not Jigsaw Structures.

1x1_b5 room now spawns in RS mansions unlike vanilla which is bugged lol. https://bugs.mojang.com/browse/MC-240121

Adjusted looks of Savanna mansion to make it look nicer.


### **(V.3.3.4 Changes) (1.16.5 Minecraft)**

##### Mod Compat:
Adjusted piece spawn limiting for Tidbits compat datapack.


### **(V.3.3.3 Changes) (1.16.5 Minecraft)**

##### Mod Compat:
Modded loot will be imported into Better Stronghold Compat datapack's loot tables now.

##### Misc:
Fixed the structure offsetting to actually work for and only for RS strongholds so they do not get cutoff by world bottom.


### **(V.3.3.2 Changes) (1.16.5 Minecraft)**

##### Dungeons:
Added shulkerBoxInEndDungeons config option to let users make End Dungeons spawn chests instead of Shulker Boxes if set to false.
 Configuredfeatures are unable to be overridden by datapack due to a bad Forge hook placement. Hence this config option as a workaround.

##### Villages:
Slightly adjusted look of Church (temple) piece in Mushroom Villages.

##### Misc:
RS structures that would've been cut off by world bottom will be offset upward so that they are now longer cut off. 
 (Helps prevents End-themed Better Stronghold from being cut off when using Better Stronghold Compat datapack)


### **(V.3.3.1 Changes) (1.16.5 Minecraft)**

##### Villages:
Adjusted some zombie terminator pool files so that they are used now for many zombie variant RS Villages.

Adjusted many RS Village's terminator pieces to make absolutely sure they cannot spawn the wrong village's street to prevent other village's buildings from spawning in extremely rare edge cases.

Fixed Zombie Warped Village spawning non-zombified Piglins.

##### Mod Compat:
Added limit on piece spawning for future mod compat datapacks with Tidbits, Reosurceful Bees, and PneumaticCraft: Repressurized.
 I'm just future proofing a bit. 


### **(V.3.3.0 Changes) (1.16.5 Minecraft)**

##### Villages:
Added Mushroom Villages for Mushroom category biomes!

Fixed Mountains Villages very very rarely spawn Savanna Village pieces.

Fixed Swamp and Giant Tree Taiga Villages very very rarely spawn Plains Village pieces.

Removed a lot of the randomly placed Red Sand blocks from Badlands Villages to make them look a lot cleaner.

##### Mod Compat:
Houses added to RS's villages from the official mod compat datapacks will now only spawn more more than once for a single village.
 This will greatly help prevent RS's villages from becoming overloaded with multiple modded houses from the datapacks.