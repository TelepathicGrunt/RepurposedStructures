### **(V.6.3.27 Changes) (1.19.2 Minecraft)**

#### Misc:
Fixed backend code not rerolling the start piece if a layout fails to generate a required piece.
  This will fix an ultra long lag spike when using Waystones mods and a zombie RS village tries to spawn.
  The side-effect of this fix means zombie villages will not spawn unless Waystones updates to add
  the required Waystones piece to the zombie village's house pool for RS.
