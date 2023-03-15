### **(V.6.3.18 Changes) (1.19.0-1.19.2 Minecraft) (Config Datapack Updated)**

#### Misc:
Adjusted the Cartographer maps to use tags so that Jungle Fortresses from compat datapacks can still be found.
 New compat datapacks for Yung's Better Nether Fortress and Apocalyptic Fortress are created now (might not be approved and visible yet)

https://www.curseforge.com/minecraft/texture-packs/repurposed-structures-apocalyptic-fortress-compat

https://www.curseforge.com/minecraft/texture-packs/repurposed-structures-yungs-better-nether-fortress

Borrowed code from Async Locator mod so now RS's explorer maps in Wandering Traders and Cartographers are done off-thread!
 This means much less lag when the game is locating the structure for explorer maps trades that goes to RS structures.

https://www.curseforge.com/minecraft/mc-mods/async-locator


### **(V.6.3.17 Changes) (1.19.0-1.19.2 Minecraft) (Config Datapack Updated)**

#### Fortress:
Attempt to reduce floating vines in Jungle Fortresses.

#### Bastions:
Attempt to reduce amount of modded floating blocks in Underground Bastions

#### Lang:
Updated zh_cn.json file. Special thanks to 1mtwtfss1!


### **(V.6.3.16 Changes) (1.19.0-1.19.2 Minecraft)**

#### Dungeons:
Deep Dungeons now has y-axis facing Deepslate blocks instead of being in a different axis

#### Misc:
Optimized pool_additions code for checking if a piece exists so it does not need to run through DFU.

Updated RS's NBT files to make loading the NBT files no longer need to run through DFU when the nbt files first loads.
 Special thanks to: https://github.com/SuperCoder7979/structure-fixer

Bumped version number so RS is same version number across all modloaders now for some sanity in my mess.