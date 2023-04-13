### **(V.6.3.24 Changes) (1.19.4 Minecraft)**

#### Misc:
(Forge): Fixed mod logo not showing up in mod list screen.


### **(V.6.3.23 Changes) (1.19.4 Minecraft)**

#### Misc:
(Quilt): Fixed potential issue where one mixin was not loading. (The loot table item injection one for RS loot tables)


### **(V.6.3.22 Changes) (1.19.4 Minecraft)**

#### Misc:
Fixed startup crash due to improper init of Mixin Extras (I updated internal dependency but forgot to remove now unneeded init code for it)


### **(V.6.3.21 Changes) (1.19.4 Minecraft) (Config Datapack Updated)**

#### Cities:
Nerfed Netherite loot from Nether Cities a bit and reduced the variation in amount of Netherite loot from city to city.

#### Mansions:
Fixed a vanilla bug that caused RS's Mansions to not spawn Red and Brown Mushrooms. This impacted vanilla's Woodland Mansion as well but I left that untouched.
 https://bugs.mojang.com/browse/MC-213695
 https://bugs.mojang.com/browse/MC-160169

#### Fortresses:
Fixed mushrooms blocks in stairs piece dropping themselves as items when below sealevel in Jungle Fortress.

Fixed vanilla bug that could prevent mushrooms from spawning in stairs piece in Jungle Fortress.

#### Igloos:
Added Bowls and a Shear to Mushroom Igloo's chest

Fixed vanilla bug that could prevent mushrooms from spawning in Mossy and Mangrove Igloo top piece.


### **(V.6.3.20 Changes) (1.19.4 Minecraft) (Config Datapack Updated)**

#### Ancient City:
Removed invalid reference to non-existent wall piece for all RS Ancient Cities. Mojang bug that I imported: https://bugs.mojang.com/browse/MC-249771

#### Explorer Maps:
Fixed Explorer Maps getting stuck saying "Working..." if it pointed to an RS structure that the user disabled from spawning.
 Now the trade entry will just disappear. It may reduce the lines of trades by 1 or so in Cartographer or Wandering Trader trades.
 Still, it is preferable for users to override the `data/repurposed_structures/structure_map_trades/default_maps.json` file to remove maps for disabled structures.

#### Mod Compat:
Made RS Structures that spawn only in basalt, crimson, soul, or warped-like nether biomes now spawn in more modded Nether biomes by default.


