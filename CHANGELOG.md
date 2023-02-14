### **(V.6.3.15 Changes) (1.19.3 Minecraft)**

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

#### Misc:
Fixed crash when a Jigsaw Block somewhere points to a non-existent pool. Will log warning into log instead.

#### Mod Compat:
Fixed explorer maps that point to Ocean Monuments and Nether Fortresses in RS loot tables from breaking if
 Yung's Better Ocean Monument or Apocalyptic Fortresses is on. Instead, the maps will point to the new structures.

#### Quilt:
(Quilt): Fixed crash at startup due to misnamed package.


### **(V.6.3.14 Changes) (1.19.3 Minecraft)**

#### Major:
Switched to using Arch in backend to speed up development! Took some blood and tears...
 Please let me know if any bugs or issues arises as this porting could have introduced some bugs.

#### Misc:
Fixed having multiple compat datapacks not properly working together if they target the same structure by pool_additions folder.

NOTE TO OTHER DEVS: If you were adding new conditions to the RS condition registry, please note it has been moved now!
 See the comment in this file for how to safely register to the register now: 
 https://github.com/TelepathicGrunt/RepurposedStructures/blob/1.19.3-Arch/common/src/main/java/com/telepathicgrunt/repurposedstructures/modinit/RSConditionsRegistry.java#L20-L46

Silenced "Hanging entity at invalid position" logspam from vanilla by lowering the logging level of that event from error to debug level.
 Mojang bug report: https://bugs.mojang.com/browse/MC-252934
 The Item Frame spawned from nbt files still places as intended and functions as intended. This was just annoying logspam I decided to yeet.

#### Lang:
Japanese lang file added. Special thanks to a player for creating this file and translations!


### **(V.6.3.11 Changes) (1.19.3 Minecraft)**

#### Villages:
Fixed many Badlands Houses having a random Orange Terracotta blocking their door.

#### Ancient Cities:
Fixed logs mentioning "No key selector in MapLike" when Nether and End Ancient Cities spawn a Sculk Sensor.

Decreased the amount of randomly removed blocks from Nether Ancient City to make it slightly less messy.


### **(V.6.3.10 Changes) (1.19.3 Minecraft)**

#### Misc:
Fixed RS Compat datapacks not working.


### **(V.6.3.9 Changes) (1.19.3 Minecraft)**

#### Major:
Ported to 1.19.3