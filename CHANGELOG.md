### **(V.6.3.20 Changes) (1.19.2 Minecraft) (Config Datapack Updated)**

#### Ancient City:
Removed invalid reference to non-existent wall piece for all RS Ancient Cities. Mojang bug that I imported: https://bugs.mojang.com/browse/MC-249771

#### Explorer Maps:
Fixed Explorer Maps getting stuck saying "Working..." if it pointed to an RS structure that the user disabled from spawning.
 Now the trade entry will just disappear. It may reduce the lines of trades by 1 or so in Cartographer or Wandering Trader trades.
 Still, it is preferable for users to override the `data/repurposed_structures/structure_map_trades/default_maps.json` file to remove maps for disabled structures.

#### Mod Compat:
Made RS Structures that spawn only in basalt, crimson, soul, or warped-like nether biomes now spawn in more modded Nether biomes by default.