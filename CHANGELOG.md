### **(V.6.3.12 Changes) (1.19.0-1.19.2 Minecraft)**

#### Misc:
Optimized pool_additions code for checking if a piece exists so it does not need to run through DFU.

Updated RS's NBT files to make loading the NBT files no longer need to run through DFU when the nbt files first loads.
 Special thanks to: https://github.com/SuperCoder7979/structure-fixer


### **(V.6.3.11 Changes) (1.19.0-1.19.2 Minecraft)**

#### Villages:
Made sure all Villages now pull from their own villager template pools for villagers to spawn.

Fixed Zombie Badlands, Birch, and Oak Villages not spawning any Zombie Villagers.

Fixed Crimson and Warped zombified villages spawning cobblestone well bottoms.

Fixed RS Village well bottoms being rotated incorrectly. (Mojang bug inherited https://bugs.mojang.com/browse/MC-194518 )
 Well bottoms now can spawn with gold! (Mushroom Village well can spawn Amethyst Blocks instead)

Fixed Swamp Village's and Giant Taiga Village's Shepherd house not spawning. (Mojang bug inherited https://bugs.mojang.com/browse/MC-170550 )

Fixed Mountains Village Temple and Cartographer houses having a missing ladder block. (Mojang bug inherited https://bugs.mojang.com/browse/MC-151536 )

Fixed misrotated block in Jungle Village Weaponsmith house. (Mojang bug https://bugs.mojang.com/browse/MC-199309 )

Fixed Crimson, Birch, Dark Forest, Oak, Ocean, and Warped Village's Weaponsmith having hollow roof that can spawn mobs inside. (Mojang bug https://bugs.mojang.com/browse/MC-140448 )

Fixed Jungle Village's Small Farm having 1 unhydrated farmland block. (Mojang bug https://bugs.mojang.com/browse/MC-199137 )

#### Mod Compat:
Fixed explorer maps that point to Ocean Monuments and Nether Fortresses in RS loot tables from breaking if 
 Yung's Better Ocean Monument or Apocalyptic Fortresses is on. Instead, the maps will point to the new structures.



### **(V.6.3.10 Changes) (1.19.0-1.19.2 Minecraft)**

#### Lang:
Japanese lang file added. Special thanks to a player for creating this file and translations!


### **(V.6.3.9 Changes) (1.19.0-1.19.2 Minecraft)**

#### Villages:
Fixed many Badlands Houses having a random Orange Terracotta blocking their door.

#### Ancient Cities:
Fixed logs mentioning "No key selector in MapLike" when Nether and End Ancient Cities spawn a Sculk Sensor.

Decreased the amount of randomly removed blocks from Nether Ancient City to make it slightly less messy.


### **(V.6.3.8 Changes) (1.19.0-1.19.2 Minecraft)**

#### Ancient Cities:
Fixed End Ancient Cities having Barrier Blocks somehow on the top parts of two pieces.


### **(V.6.3.7 Changes) (1.19.0-1.19.2 Minecraft) (Config Datapack updated)**

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


### **(V.6.3.6 Changes) (1.19.0-1.19.2 Minecraft)**

#### End Structures:
Fixed several End Structures not fully generating properly.

#### Villages:
Non-zombified Badland Village houses now has pillar support to first land.
 (Was a test. Will extend this feature to all remaining RS village houses. Is a lot of work to get working)


### **(V.6.3.5 Changes) (1.19.0-1.19.2 Minecraft)**

#### Villages:
Removed a Badlands house spawning accidentally in Birch Village.


### **(V.6.3.4 Changes) (1.19.0-1.19.2 Minecraft) (Config Datapack updated)**

#### Strongholds:
Fixed Nether Strongholds not spawning all of its pieces.


### **(V.6.3.3 Changes) (1.19.0-1.19.2 Minecraft) (Config Datapack updated)**

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
Underground Bastions are now a bit more common

#### Ruins:
Land Ruins chests now has an increased chance of having a map to an Underground Bastion

Fixed all 4 Land Ruins types having difficulty spawning

Fixed Icy Land Ruins placing snow layers on top of invalid blocks. Powder Snow may sometimes be two blocks deep now.