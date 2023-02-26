
### **(V.6.3.13 Changes) (1.19.0-1.19.2 Minecraft)**

#### Misc:
Optimized pool_additions code for checking if a piece exists so it does not need to run through DFU.

Updated RS's NBT files to make loading the NBT files no longer need to run through DFU when the nbt files first loads.
 Special thanks to: https://github.com/SuperCoder7979/structure-fixer


### **(V.6.1.6 Changes) (1.19.0-1.19.2 Minecraft)**

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



### **(V.6.1.5 Changes) (1.19.0-1.19.2 Minecraft)**

#### Lang:
Japanese lang file added. Special thanks to a player for creating this file and translations!


### **(V.6.1.4 Changes) (1.19.0-1.19.2 Minecraft)**

#### Villages:
Fixed many Badlands Houses having a random Orange Terracotta blocking their door.

#### Ancient Cities:
Fixed logs mentioning "No key selector in MapLike" when Nether and End Ancient Cities spawn a Sculk Sensor.

Fixed logspam about missing nether/end ancient city rs_spawner files.

Decreased the amount of randomly removed blocks from Nether Ancient City to make it slightly less messy.


### **(V.6.1.3 Changes) (1.19.0-1.19.2 Minecraft)**

#### Misc:
1.19.2 Quilt ported to fabric. Fabric is now in parity with Quilt which includes the Ancient City structure variants.


### **(V.6.1.2 Changes) (1.19.0-1.19.2 Minecraft)**

#### Mod Compat:
Fixed LootJS incompatibility.


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