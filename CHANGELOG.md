### **(V.6.3.19 Changes) (1.19.3 Minecraft) (Config Datapack Updated)**

#### Misc:
Borrowed code from Async Locator mod so now RS's explorer maps in Wandering Traders and Cartographers are done off-thread!
 This means much less lag when the game is locating the structure for explorer maps trades that goes to RS structures.

https://www.curseforge.com/minecraft/mc-mods/async-locator


### **(V.6.3.18 Changes) (1.19.3 Minecraft) (Config Datapack Updated)**

#### Fortress:
Attempt to reduce floating vines in Jungle Fortresses.

#### Bastions:
Attempt to reduce amount of modded floating blocks in Underground Bastions

#### Lang:
Updated zh_cn.json file. Special thanks to 1mtwtfss1!


### **(V.6.3.17 Changes) (1.19.3 Minecraft)**

#### Misc:
(Forge): Fixed crash when using Yung's Better Witch Hut Compat Datapack.
 Was caused by me using the wrong Forge event for my pool merger code.


### **(V.6.3.16 Changes) (1.19.3 Minecraft)**

#### Dungeons:
Deep Dungeons now has y-axis facing Deepslate blocks instead of being in a different axis

#### Misc:
Optimized pool_additions code for checking if a piece exists so it does not need to run through DFU.

Updated RS's NBT files to make loading the NBT files no longer need to run through DFU when the nbt files first loads.
 Special thanks to: https://github.com/SuperCoder7979/structure-fixer

Fixed logging so correct piece is reported if it tries to spawn a non-existent pool.