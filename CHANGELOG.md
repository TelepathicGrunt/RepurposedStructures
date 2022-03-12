### **(V.4.0.2 Changes) (1.18.2 Minecraft)**

#### Configs:
Clarified in the dimension/biome configs that they do not work for RS structures because those are datapack stuff.
 These dimension/biome configs only affect Repurposed Structures's Wells and Dungeons.

#### Temples:
Changed up the traps for Wasteland, Crimson, Warped, and Soul Sand Temples.

#### Villages:
Slightly reduced how messy Giant taiga Villages are.

Fixed town center pieces for Swamp Villages being a block too high.

Mountain Villages can spawn in Jagged Peaks biome now.

#### Tags:
Simplified some configured structure feature tags internally.


### **(V.4.0.1 Changes) (1.18.2 Minecraft)**

#### Mineshafts:
Fixed Jungle Mineshafts having too much Cobwebs.

#### Villages:
Optimized finding Mushroom Villages a bit.

#### Tags:
Fixed tags so now Eyes of Ender locates Nether/End Stronghold and Dolphins can find Ocean Pyramids.

Updated tags to include modded biomes much better.


### **(V.4.0.0 Changes) (1.18.2 Minecraft)**

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