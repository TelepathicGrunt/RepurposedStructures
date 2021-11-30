### **(V.3.1.1 Changes) (1.18-rc3 Minecraft)**

#### Igloos:
Grassy Igloo and Stone Igloo basements and ladder pieces has properly randomized stone brick variant blocks and randomized infested blocks.
 Amount of infested stone brick blocks was reduced a bit.

#### Mansions:
Icy Mansion pieces now has Polished Diorite under floor carpet to make traversing the mansion easier.

#### Misc:
Fixed logspam that may appear saying setblock out of bounds due to some PlacedFeatures having randomization inside their placements.
 Also fixed some PlacedFeature's spawn positions and rates in RS structures.

#### Misc:
Added conditions to entries in rs_pieces_spawn_counts and rs_pieces_spawn_counts_additions files so mods can register a condition to
 "repurposed_structures:json_conditions" registry and have their entries only apply if certain configs are on.

Conditions entry is also added to pool_additions json files and an option too.


### **(V.3.1.0 Changes) (1.18-rc3 Minecraft)**

#### Misc:
rs_spawner's json file now uses floats instead of ints so you can do fractional weights for mob spawner's mob type rates if you desire.

rs_pieces_spawn_counts folder is now added that lets you control what pieces in RS structures should always spawn and limit how often a piece spawns.

rs_pieces_spawn_counts_additions folder is created to make it easier for me to create RS datapacks with new pieces and have them not spawn excessively.

Improved error message a tad when a required structure piece fails to generate.

#### Dungeons:
Fixed dungeons spawners not being randomized properly.

#### Fortresses:
Jungle Fortress mushroom stairs room has slightly reduced amount of mushrooms.


### **(V.3.0.2 Changes) (1.18-rc3 Minecraft)**

#### Major:
Now works with modded biomes and the biome allow/disallow configs now works again. Enjoy!

#### Mineshafts:
Slightly buffed rates of Minecarts in all Overworld Mineshafts.

Nerfed Minecart rate in Icy Mineshafts to match rate of other Overworld Mineshafts.

Nerfed End Mineshaft's Minecart rates a bit to balance it a bit better.

#### Strongholds:
End Strongholds have slightly less loot Shulker Boxes and slightly less Shulker mobs to 
 help reduce amount of Shulker boxes a bit that can be collected from this structure.


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