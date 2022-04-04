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


### **(V.5.0.4 Changes) (1.18.2 Minecraft)**

#### Misc:
Updated to work on 40.0.18 Forge. They did some breaking changes.


### **(V.5.0.3 Changes) (1.18.2 Minecraft)**

#### Configs:
Clarified in the dimension/biome configs that they do not work for RS structures because those are datapack stuff. 
 These dimension/biome configs only affect Repurposed Structures's Wells and Dungeons.
 The config names for the dimension/biome config file is also changed to make it painfully clear it's for features.

#### Temples:
Changed up the traps for Wasteland, Crimson, Warped, and Soul Sand Temples.

#### Villages:
Slightly reduced how messy Giant taiga Villages are.

Fixed town center pieces for Swamp Villages being a block too high.

Mountain Villages can spawn in Jagged Peaks biome now.

#### Tags:
Simplified some configured structure feature tags internally.

Added tag to make sure RS underground bastion, villages, mansions, and witch huts would not let PneumaticCraft's oil lake spawn inside them. 


### **(V.5.0.2 Changes) (1.18.2 Minecraft)**

NOTE: Do not update any 1.18.1 or older world to 1.18.2 because all chunks with ANY modded structure from any mod will be nuked and  regenerated.
 Wait till Forge fixes this MC bug before updating old worlds.

#### Villages:
Optimized finding Mushroom Villages a bit.

#### Tags:
Fixed tags so now Eyes of Ender locates Nether/End Stronghold and Dolphins can find Ocean Pyramids.

Updated tags to include modded biomes much better.


### **(V.5.0.1 Changes) (1.18.2 Minecraft)**

#### Major:
Fixed world unable to be entered with this mod on.

#### Strongholds:
Fixed Nether and End Stronghold unable to be found with Eyes of Ender.

#### Pyramids:
Fixed Ocean Pyramid unable to be found by dolphins.

### **(V.5.0.0 Changes) (1.18.2 Minecraft)**

#### BETA:
Please report issues and bugs to me! This version is experimental and for people to test and report any problems.

#### Major:
Ported to 1.18.2 mc and rewritten a massive portion of this mod to use the new json structure system mojang added!
 You can now make entirely new structures with datapack!

#### Loot:
Added Ukraine flag banner as a luck based loot to all RS chests.
 You can donate to help Ukraine! There are lots of charities! See this for some: https://kyivindependent.com/national/heres-how-to-support-ukrainian-military/

#### Config:
Most configs were deleted and moved to the new json system. This include spawnrates, disabling structures,
 what biomes a structure can spawn in, adding mob map trades, and more! There's many new configurations also
 available by datapack in the new json system that was previously not avaliable before.

Structures are now based on biome tags for what biomes they spawn in. Please let me know if RS structures are not
 spawning in a modded or datapack biome as they will need to add their biomes to specific biome tags.

#### Witch Huts:
All Witch Huts' internal ConfiguredStructure name is changed from `witch_huts_` to now `witch_hut_`.
 Plural `witch_huts_` will continue to be used for all other json files.

#### Villages:
Dark Forest Village's internal name was changed from `village_dark_oak` to now `village_dark_forest`.