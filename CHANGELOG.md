### **(V.5.1.4 Changes) (1.18.2 Minecraft)**

#### Biome Tags:
Fixed the biome tags that RS uses so it now spawns structures in modded/datapack biomes that are using forge's or c's namespaced tags.
 If you're using an RS config datapack, the new v5 of the config datapack should have the updated data/repurposed_structures/tags/worldgen/biome/collections tags that should work now.


### **(V.5.1.3 Changes) (1.18.2 Minecraft)**

#### Loot:
Fixed all structure maps in RS loot tables (chests and shulker boxes) so that the structure maps actually locates structures now.
 Issue was the way explorer maps gets what structure to track was changed in 1.18.2 and I did not realize it. 
 If you downloaded a config datapack already, replace the loot table folder in it with v4 config datapack's loot table folder.
 (Note: structures at the edge of the map's area may not show the icon for the structure's location. This is vanilla behavior. Don't ask why)

#### Shipwrecks:
Fixed End Shipwrecks not spawning.


### **(V.5.1.2 Changes) (1.18.2 Minecraft)**

#### Misc:
Fixed crash at startup due to missing mixin refmap. Don't know why it keeps disappearing on Forge builds... (I can't catch a break lol)


### **(V.5.1.1 Changes) (1.18.2 Minecraft)**

#### Mod Compat:
Fixed issue where Blay's Waystone may not be able to inject into RS Villages.
 Issue was that I was creating the conditions registry at mod init and Waystones was registering their conditions in mod init as well.
 Meaning there was a rare chance that Blay may run their registry code before I make the registry which causes the RS json files in Waystones to fail later.
 Solution, RS now makes the conditions registry at game startup way before any mod init is ran.


### **(V.5.1.0 Changes) (1.18.2 Minecraft)**

#### Monuments:
Added Jungle, Icy, Desert, and Nether Monuments! Can be found from Cartographer's map trades too!

#### Tags:
Updated several biome tags so some RS structures can spawn in the correct modded/datapack biomes better.

#### Mod Compat:
The rs_spawner json file's entries now can have `"optional": true` to make the entry no longer error if the mob is not present in the registry.
  Good for marking modded mobs as optional when overriding the rs_spawner json files so that you can later remove that mod and not cause the json file to explode.

#### Lang:
Fixed typo in zh_cn.json file that prevented it from loading properly.
