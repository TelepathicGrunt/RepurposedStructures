### **(V.3.0.1 Changes) (1.18.1 Minecraft)**

#### Cities:
Changed default config value for Overworld Cities from 1200 to 180.
  You'll have to edit the config yourself if you already started game with older v4.2.X versions of RS.

Improved Overworld City's resistance to lightning from thunderstorms.

Adjusted insides of two rooms in Overworld City to look a tad better.

Fixed some top pieces not spawning in Overworld Cities because of bad logic in my required/maximum count piece controlling code.

Fixed Overworld Cities not checking properly to not spawn near any RS or Vanilla Mansions.

Fixed Bridge End pieces not spawning sometimes in Overworld and Nether Cities.

Nether Cities now always have 3 branches at minimum.

#### Outposts:
RS Outposts will not spawn near Overworld Cities now.

#### Ruins:
Cold and Icy Land Ruins will not let lake features spawn in them now.

#### Misc:
Fixed some processors that were placing blocks multiple times.

Fixed required/maximum count piece controlling code (for rs_pieces_spawn_counts) so it now limits amount of pieces properly and not cause weird behavior.


### **(V.3.3.0 Changes) (1.18.1 Minecraft)**

#### Lang:
Added en_us entries for explorer maps to any RS structures.

#### Cities:
Added Overworld City! If you are lucky, you can find a map to one from a Wandering Trader! Very expensive tho...

May have fixed a bug that causes a very very rare chance of Nether Cities missing top pieces on their tower piece.

Replaced some full blocks with stairs in one of Nether City's steep bridge piece.

#### Pyramids:
Added Dark Forest Pyramid!

#### Ruins:
Added Cold Land Ruins and Icy Land Ruins!

Made the config default value for the spawnrate of Warm Land Ruins and Hot Land Ruins slightly less.

#### Igloos:
Added Mushroom Igloos!

Made the config default value for the spawnrate of Grassy Igloo and Stone Igloo slightly less.

#### Witch Huts:
Expanded RS Witch Hit bounding boxes 2 blocks higher to better match vanilla's Witch Hut bounding box for spawning Witches and black Cats.

#### Villages:
Slightly reduced cost of maps to Mushroom Villages in Wandering Trader's trades.

#### Mineshafts:
Fixed Birch Mineshaft not closing off fluids properly for some pieces.

RS Mineshaft's 4 way piece keeps columns now.

#### Misc:
Fixed some structures not properly avoiding water.

Changed all RS ConfiguredStructure registry names to match the base structure registry names.
  This has no impact on player's worlds. It is safe. This is only for internal use or people creating their own datapacks replacing my structures for whatever reason.

RS Structures will not spawn now if they will get cut off by top of world's limit.

#### Mod Compat:
Fixed modded loot not being imported into some of RS chests.


### **(V.3.2.0 Changes) (1.18.1 Minecraft)**

#### Dungeons:
Deep Dungeons are now added that spawn from -10 to -60. Will replace Vanilla Dungeons below y = 0.

Nether Dungeon's secret banner is now red colored while Deep Dungeon has the dark grey banner instead.

#### Mineshafts:
RS Mineshaft arches should not be broken up weirdly as much anymore.

Fixed several RS Mineshafts having 1 incorrectly rotated arch block.

Fixed some arches placing incorrect blocks in RS Mineshafts.
