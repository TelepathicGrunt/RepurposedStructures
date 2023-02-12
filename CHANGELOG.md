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