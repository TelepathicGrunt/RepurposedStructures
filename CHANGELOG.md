### **(V.4.1.7 Changes) (1.18.2 Minecraft)**

#### Outposts:
Fixed Warped Outposts being very rare.

#### Shipwrecks:
Fixed Crimson Shipwreck being very rare.

#### Nether Structures:
Slightly decreased the spawnrates of all Nether Outposts, Nether Pyramid, Nether Ruins,
 Nether Shipwrecks, Nether Temples, Nether Strongholds, Nether Mineshafts, and Nether Villages.
 Hopefully this helps make the Nether a little less crowded.

#### Villages:
Fixed Badland Village's trapdoor plant pots to have grass block/podzol inside instead of air.

Added stable_1 building to spawn in non-zombied Badlands Villages rarely.

Dark Forest Village Churches and Stable buildings has some Granite Stairs/Slabs replaced with Dark Oak Stairs/Slabs

Swamp Villages overall has their Moss Blocks on ground replaced with various terracotta blocks.
 Moss Block and Moss Carpet has been removed from the outside of the Swamp Village's mud igloo houses and instead, has Moss Carpet on floor inside.

Fixed Swamp Villages spawning Structure Void blocks sometimes.

#### Lang:
it_it.json lang file added by Zano1999! Thank you!


### **(V.4.1.6 Changes) (1.18.2 Minecraft)**

#### Misc:
Fixed crash when certain features are spawned outside of worldgen. 
 (Made some mixins safer and only apply their changes when during worldgen)

Fixed mixins that reduces amount of jungle bushes in some RS structures.

Fixed mixins that removes lava lakes from spawning in some RS structures.


### **(V.4.1.5 Changes) (1.18.2 Minecraft)**

#### Map Trades:
Fixed crash with Villager mods that add Villagers with more than 5 trade tiers. 


### **(V.4.1.4 Changes) (1.18.2 Minecraft)**

#### Biome Tags:
Fixed "#byg:is_icy" entry in repurposed_structures:collections/icy biome tag so it includes icy byg biomes properly.


### **(V.4.1.3 Changes) (1.18.2 Minecraft)**

#### Biome Tags:
Fixed the biome tags that RS uses so it now spawns structures in modded/datapack biomes that are using forge's or c's namespaced tags.
 If you're using an RS config datapack, the new v5 of the config datapack should have the updated data/repurposed_structures/tags/worldgen/biome/collections tags that should work now.


### **(V.4.1.2 Changes) (1.18.2 Minecraft)**

#### Shipwrecks:
Fixed End Shipwrecks so they now actually spawn. Was off by one in calculation for checking for land.


### **(V.4.1.1 Changes) (1.18.2 Minecraft)**

#### Loot:
Fixed all structure maps in RS loot tables (chests and shulker boxes) so that the structure maps actually locates structures now.
 Issue was the way explorer maps gets what structure to track was changed in 1.18.2 and I did not realize it.
 If you downloaded a config datapack already, replace the loot table folder in it with v4 config datapack's loot table folder.
 (Note: structures at the edge of the map's area may not show the icon for the structure's location. This is vanilla behavior. Don't ask why)

#### Shipwrecks:
Fixed End Shipwrecks not spawning.


### **(V.4.1.0 Changes) (1.18.2 Minecraft)**

#### Monuments:
Added Jungle, Icy, Desert, and Nether Monuments! Can be found from Cartographer's map trades too!

#### Tags:
Updated several biome tags so some RS structures can spawn in the correct modded/datapack biomes better.

#### Mod Compat:
Added dedicated compat with importing End Remastered's eyes into correct Repurposed Structures's structures.

The rs_spawner json file's entries now can have `"optional": true` to make the entry no longer error if the mob is not present in the registry.
  Good for marking modded mobs as optional when overriding the rs_spawner json files so that you can later remove that mod and not cause the json file to explode.

#### Lang:
Fixed typo in zh_cn.json file that prevented it from loading properly.