### **(V.4.0.6 Changes) (1.18.1 Minecraft)**

#### Misc:
Hard require 1.18.1 MC and 38.0.17 Forge to help keep people safer.


### **(V.4.0.5 Changes) (1.18.0 Minecraft)**

#### Configs:
Refined RS config default entries for Terralith compat

#### Misc:
Fixed issue where several structures could be placed wrong if world height is changed significantly in the dimension.

Fixed several Nether Structures being set too low and even inside the lava ocean.


### **(V.4.0.4 Changes) (1.18.0 Minecraft)**

#### Configs:
Fix the autoupdating for configs to have best compat with Terralith not working


### **(V.4.0.3 Changes) (1.18.0 Minecraft)**

#### Misc:
Fixed issue with deep copying the Structure Settings in chunk generators. 
 (This is needed to allow RS's dimension allow/disallow configs to work)


### **(V.4.0.2 Changes) (1.18.0 Minecraft)**

#### Configs:
Autoupdated configs to have best compat with Terralith.

#### Outposts:
Fixed Snowy and Icy Outposts spawning in non-cold biomes by mistake. Should be better now.

#### Pyramids:
Fixed Snowy and Icy Pyramids spawning in non-cold biomes by mistake. Should be better now.

#### Dungeons:
Fixed Snowy and Icy Dungeons spawning in wrong biomes by mistake. Should be better now.

Fixed multiple kinds of RS dungeons unable to spawn in same biome when using config to force them into same biome.

#### Mansions:
Fixed multiple kinds of RS Mansions unable to spawn in same biome when using config to force them into same biome.


### **(V.4.0.1 Changes) (1.18.0 Minecraft)**

#### Misc:
Fixed pool weight increase mixin not applying outside of dev.

#### Dungeons:
Fixed Ocean Dungeons not spawning because default min y config value was 205 instead of 20. Oops.
 If you already launched the game in 1.18 with v4.0.0 RS, you'll have to fix the Ocean Dungeon's config entry yourself.


### **(V.4.0.0 Changes) (1.18.0 Minecraft)**

##### Major:
Ported to 1.18 and is in parity to 1.18 fabric version.

#### Configs:
Cleaned up, renamed a bit, and many range restrictions on some values have been removed.

#### Mineshafts:
Will no longer replace Vanilla Mineshafts. Will spawn just under the surface of biomes and some variants may spawn way up in mountains.

Slightly improved look of Desert and Icy Dungeons

Slightly buffed rates of Minecarts in all Overworld Mineshafts.

Nerfed Minecart rate in Icy Mineshafts to match rate of other Overworld Mineshafts.

Nerfed End Mineshaft's Minecart rates a bit to balance it a bit better.

#### Igloos:
Grassy Igloo and Stone Igloo basements and ladder pieces has properly randomized stone brick variant blocks and randomized infested blocks.
  Amount of infested stone brick blocks was reduced a bit.

#### Mansions:
Icy Mansion pieces now has Polished Diorite under floor carpet to make traversing the mansion easier.

#### Strongholds:
End Strongholds have slightly less loot Shulker Boxes and slightly less Shulker mobs to
  help reduce amount of Shulker boxes a bit that can be collected from this structure.

#### Dungeons:
Will no longer replace Vanilla Dungeons. Will only spawn above y = 45 in Overworld (Ocean Dungeon spawns lower to be under ocean floor)

Slightly improved spawner placement in Ocean Dungeons.

#### Fortresses:
Jungle Fortress mushroom stairs room has slightly reduced amount of mushrooms.

#### Bastions:
Underground Bastions spawn lower into deepslate area and uses Deepslate Iron Blocks in their Unit rooms.

#### Misc:
rs_spawner's json file now uses floats instead of ints so you can do fractional weights for mob spawner's mob type rates if you desire.

rs_pieces_spawn_counts folder is now added that lets you control what pieces in RS structures should always spawn and limit how often a piece spawns.

rs_pieces_spawn_counts_additions folder is created to make it easier for me to create RS datapacks with new pieces and have them not spawn excessively.

Added conditions to entries in rs_pieces_spawn_counts and rs_pieces_spawn_counts_additions files so mods can register a condition to
  "repurposed_structures:json_conditions" registry and have their entries only apply if certain configs are on.

Conditions entry is also added to pool_additions json files and an option too.

Improved error message a tad when a required structure piece fails to generate.
