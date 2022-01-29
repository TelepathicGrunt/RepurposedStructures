### **(V.4.2.6 Changes) (1.18.1 Minecraft)**

#### Mineshafts:
Fixed Ocean Mineshafts chests accidentally spawning Birch Mineshaft's loot tables


### **(V.4.2.5 Changes) (1.18.1 Minecraft)**

#### Mineshafts:
Ocean Mineshafts and End Mineshafts will no longer place blocks if it has an unobstructed view of the sky above.

Ocean Mineshaft's default min y config value is now set to 5.

#### Misc:
Tried doing a slight optimization to make sure RS structures do not do any checks for spawning if the world's biome source
 cannot spawn a biome that contains said structure. Let me know if any issues arise. (Probably not noticable)

Fixed a rare null crash from my GeneralUtils.isFullCube code that is supposed to cache if a block is a full cube or not.


### **(V.4.2.4 Changes) (1.18.1 Minecraft)**

#### Major:
Now hard requires No Null Processor mod and NBT Deadlock Be Gone mod to help prevent issues during chunk pregenning or multiplayer gameplay.
  These two mods fixes two nasty rare Minecraft bugs that impact structures/features. But it does affect some people so these two mods should help.

#### Bastions:
Changed default config value for Underground Bastions from 400 to 180.
 You'll have to edit the config yourself to the new value if you already started game with any versions of RS older than v4.2.4.

#### Mineshafts:
End Mineshafts loot table no longer has Chorus Flower or Chorus Plant which is not supposed to be obtainable in survival.
  Instead, it'll have Beetroot Soup and more Chorus Fruit as replacements.

#### Dungeons:
End Dungeons loot table no longer has Chorus Flower or Chorus Plant which is not supposed to be obtainable in survival.
  Instead, it'll have Beetroot Soup and more Chorus Fruit as replacements.

End Dungeon's End Portal block is now replaced with Crying Obsidian.


### **(V.4.2.3 Changes) (1.18.1 Minecraft)**

#### Cities:
Changed default config value for Overworld Cities from 1200 to 180. 
  You'll have to edit the config yourself if you already started game with older v4.2.X versions of RS.

Improved Overworld City's resistance to lightning from thunderstorms.

Adjusted insides of two rooms in Overworld City to look a tad better.

Fixed some top pieces not spawning in Overworld Cities because of bad logic in my required/maximum count piece controlling code.

Fixed Bridge End pieces not spawning sometimes in Overworld and Nether Cities.

Nether Cities now always have 3 branches at minimum.

#### Outposts:
RS Outposts will not spawn near Overworld Cities now.

#### Ruins:
Cold and Icy Land Ruins will not let lake features spawn in them now.

#### Misc:
Fixed some processors that were placing blocks multiple times.

Fixed required/maximum count piece controlling code (for rs_pieces_spawn_counts) so it now limits amount of pieces properly and not cause weird behavior.


### **(V.4.2.2 Changes) (1.18.1 Minecraft)**

#### Major:
Turned off erroring on missing RS loot tables as I forgot to remove that debug code. Aaaaaaaaaaaa


### **(V.4.2.1 Changes) (1.18.1 Minecraft)**

#### Major:
Fixed crash due to missing refmap for mixins


### **(V.4.2.0 Changes) (1.18.1 Minecraft)**

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

Cleaned up the Igloo config file a bit. It will be reset to default values so edit the file if you want your igloo spawnrate changes back.

Made the config default value for the spawnrate of Grassy Igloo and Stone Igloo slightly less.

#### Witch Huts:
Expanded RS Witch Hit bounding boxes 2 blocks higher to better match vanilla's Witch Hut bounding box for spawning Witches and black Cats.

#### Villages:
Added maps to Mushroom Village to Wandering Trader's trade.

#### Misc:
Fixed some structures not properly avoiding water.

Changed all RS ConfiguredStructure registry names to match the base structure registry names.
  This has no impact on player's worlds. It is safe. This is only for internal use or people creating their own datapacks replacing my structures for whatever reason.

RS Structures will not spawn now if they will get cut off by top of world's limit. 

#### Mod Compat:
Fixed modded loot not being imported into RS chests.
