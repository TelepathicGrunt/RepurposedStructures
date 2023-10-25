### **(V.6.3.28 Changes) (1.19.2 Minecraft)**

#### Major:
Fixed issues where terrain adaption for various RS structures was not properly working.
 This should fix the holes of lava eating away at Bedrock and exposing the void at bottom of Nether.
 Was commonly found under Nether Villages and Nether Outposts.

#### Misc:
Fixed backend code not rerolling the start piece if a layout fails to generate a required piece. 
 This will fix an ultra long lag spike when using Waystones mods and a zombie RS village tries to spawn.
 The side-effect of this fix means zombie villages will not spawn unless Waystones updates to add the required Waystones piece to the zombie village's house pool for RS.