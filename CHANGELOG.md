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