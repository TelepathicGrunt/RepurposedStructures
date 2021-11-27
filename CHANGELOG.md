### **(V.3.0.1 Changes) (1.18-rc3 Minecraft)**

#### Misc:
Cleaned up code for injecting structures into biomes. Will pave the way for bringing back mod compat with modded biomes.


### **(V.3.0.0 Changes) (1.18-pre7 Minecraft)**

#### Major:
Ported to 1.18-pre7

NOTE: RS structures will not be added to modded biomes and the biome allow/disallow configs do not work. 
 This is due to Fabric API's BiomeModificationAPI not working for structures at the moment.

#### Mineshafts:
Will no longer replace Vanilla Mineshafts. Will spawn just under the surface of biomes and some variants may spawn way up in mountains.

Slightly improved look of Desert and Icy Dungeons

#### Dungeons:
Will no longer replace Vanilla Dungeons. Will only spawn above y = 45 in Overworld (Ocean Dungeon spawns lower to be under ocean floor)

Slightly improved spawner placement in Ocean Dungeons.

#### Bastions:
Underground Bastions spawn lower into deepslate area and uses Deepslate Iron Blocks in their Unit rooms.