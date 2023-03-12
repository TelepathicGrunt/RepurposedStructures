### **(V.6.3.17 Changes) (1.19.3 Minecraft)**

#### Misc:
(Forge): Fixed crash when using Yung's Better WItch Hut Compat Datapack.
 Was caused by me using the wrong Forge event for my pool merger code.


### **(V.6.3.16 Changes) (1.19.3 Minecraft)**

#### Dungeons:
Deep Dungeons now has y-axis facing Deepslate blocks instead of being in a different axis

#### Misc:
Optimized pool_additions code for checking if a piece exists so it does not need to run through DFU.

Updated RS's NBT files to make loading the NBT files no longer need to run through DFU when the nbt files first loads.
 Special thanks to: https://github.com/SuperCoder7979/structure-fixer

Fixed logging so correct piece is reported if it tries to spawn a non-existent pool.