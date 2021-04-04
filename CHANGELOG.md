# Made for Minecraft v.1.16.5

## Created by TelepathicGrunt

Welcome to the Github! If you are looking for the most recent stable version, then checkout the master branch! Branches dedicated to the latest version of Minecraft may be unstable or broken as I test and experiment so stick with the master branch instead.


------------------------------------------------
# | Repurposed Structures changelog |


## (V.1.10.0 Changes) (1.16.5 Minecraft)

##### Cities:
- Change Nether City's default config spawnrate from 160 to 120 to make them more common and less traveling to reach.

- Made large room with double chest in Nether Cities now a bit more common and have 3 Wither Skeletons defending it now.

- Added Wither Skeleton with enchanted bow to Nether Cities's tiny tower top pieces.

- Buffed Nether City loot a lot to have higher chance of Netherite loot and more chests.

- Removed random stray Red Nether Bricks block in one of the Nether City's stair piece.

##### Strongholds:
- Repurposed Structure's Stonebrick Strongholds will now generate only in rings around world origin like Vanilla stronghold's do.
  The rings of valid spots that RS Stonebrick Strongholds can spawn in are the same size and distance from spawn as vanilla's.
  However, there are an unlimited number of rings for RS's Stonebrick Stronghold beyond the 8 rings that Vanilla's does.
  See here for what I mean about rings: https://minecraft.fandom.com/wiki/Stronghold#Generation

- Bumped Stonebrick Stronghold's default config spawnrate from 85 to 110.

- Stonebrick Strongholds now will spawn in ocean biomes like vanilla Strongholds do.
  There's a weird quirk with how vanilla Stronghold will spawn in biomes that doesn't have the Stronghold added to it which I am trying to copy here.

- Buffed Nether Stronghold loot and added custom loot table for its library too.

- Nether Stronghold Libraries can spawn bookshelves of even more kinds of blocks.

- Made Repurposed Structures's Stonebrick Strongholds now use custom loot tables and can have better loot than vanilla loot.

##### Dungeons:
- All Dungeons now use nbt files. You can override the looks of dungeons with a datapack!
  
- Icy dungeon is now actually icy and spawns only in non-ocean biomes that are super cold or has frozen/ice in the name.
  Loot and spawner mobs have been adjusted as well.

- Snow Dungeon is now added and will spawn in all snowy biomes!

- Nether Dungeons at any height now has the 1% chance of a Wither Skeleton Spawner.

- Nether Dungeons no long spawn attached to lava pockets underground.

- Nether Dungeons are in Vegetal Generation Stage now to be able to spawn in Nether Mineshafts again and not be filled with Basalt as much.

- Bumped up the config default spawnrates for Nether and End Dungeons from 8 to 12. Slightly restricted the spawn condition for overworld dungeons.

##### Shipwrecks:
- Hard nerfed the Netherite loot from Nether Bricks Shipwrecks.

- In Nether Bricks Shipwrecks, they now have more Wither Skeletons upon first generation.

- Adjusted the stairway in Nether Bricks Shipwrecks so that Wither Skeletons can walk though them.

- 1 Wither Skeleton with an enchanted bow has been added to Warped, Crimson, and Nether Bricks Shipwreck. (Crimson Shipwreck mast now has platform)

- All 3 Nether Shipwrecks now properly checks to make sure they won't spawn inside land.
  
- Fixed End Shipwrecks spawning so they no longer can spawn on the Enderdragon island.
  
- Change End Shipwrecks's default config spawnrate from 15 to 24 to make them less common.

##### Outposts:
- Added End Outposts to the End! Be careful as they are swarming with Phantoms!

##### Pyramids:
- Added End Pyramids to the End! A shrine for the dragon of the End!
  
- Added Icy Pyramids to frozen biomes! Stay warm!

- Snowy Pyramid chests will have Snow Blocks instead of Ice Blocks in its loot.

- Fixed https://bugs.mojang.com/browse/MC-130584 by using a special processor.
  This means this mod's pyramid's chests will no longer be waterlogged if their pits replaces water in the world.

##### Igloos:
- Fixed https://bugs.mojang.com/browse/MC-130584 by using a special processor.
  This means this mod's igloo's chests, ladders, and other blocks will no longer be waterlogged if their basement places water in the world.

##### Ruined Portals:
- Now will be less likely to spawn off the island and will bury itself a bit.

##### Mansions:
- Fixed two rooms in Snowy Mansions so that they won't melt Ice blocks on the ceiling due to their torches.

##### Loot Tables:
- Fixed blacklistedRSLoottablesFromImportingModdedItems config entry not actually being read.


## (V.1.9.0 Changes) (1.16.5 Minecraft)

##### Loot Tables:
- Added code that now will import modded items from vanilla structure's loot tables into Repurposed Structures's own loot tables. 
(Snowy Pyramid gets all modded items that vanilla Desert Temple can have) 

##### Language:
- Fixed some Spanish translations being cut-off.

- Added German translations by elloellie and partially Google Translate! Special thanks to them!

##### Config:
- Fixed some config comments missing the phrase: '1 for spawning in most chunks and 1001 for none.'


## (V.1.8.7 Changes) (1.16.5 Minecraft)

##### Major:
- I'm sorry everyone. The config API dependency I use now has migrated to Cloth Config API.
  Download link is here: https://www.curseforge.com/minecraft/mc-mods/cloth-config

##### Language:
- Fixed bundled lang files that were missing map translations for 5 mansions.

- Changed how translations are done for this mod and now you no longer need the translation datapack. 
  Just a resourcepack for your translations to take effect! This mod is now bundled with Spanish and Chinese translations files.
  The trick is that the English translations are now the keys for the other language files in order to make them work.
  
- Spanish translation provided by 16N1C0! Special thanks to them!
  

## (V.1.8.6 Changes) (1.16.5 Minecraft)

##### Fortresses:
- Stopped scheduling duplicate fluid ticks for water in Jungle Fortresses. Also fixed a rare possible crash.

##### Dungeons:
- End Dungeons now have more Shulker Boxes and will have an additional Spawners per Shulker Box that spawns.


## (V.1.8.5 Changes) (1.16.5 Minecraft)

##### General:
- Added null check for logspam filtering code.


## (V.1.8.4 Changes) (1.16.5 Minecraft)

##### General:
- Found a way to silence logspam about the old structure pieces names being unknown in old worlds. Thank you MutantGumdrop for the suggestion!


## (V.1.8.3 Changes) (1.16.5 Minecraft)

##### Strongholds:
- So uh, I just learned that removing the Vanilla Stronghold from all biomes doesn't actually stop it from spawning.
  To fix this, turnOffVanillaStrongholds config value is now set to true by default and it will now correctly make 
  Vanilla Strongholds no longer spawn anymore. Repurposed Structures's Stonebrick Stronghold will take its place properly.
  (Yes Eyes of Ender will find RS's Strongholds as well)
  
- Moved RS Strongholds from UNDERGROUND_STRUCTURES generation stage to STRONGHOLDS. 
  Will prevent some features/structures from breaking strongholds.

##### Outposts:
- Nerfed the enchantment range of armor and weapons in Crimson, Warped, and Nether Bricks Outpost's loot. 
  Armor and Weapons will be damaged now to encourage fusing gears together to repair and strengthen enchantments.

- All Overworld Repurposed Structures Outposts now generates with 8 Pillagers (Vanilla spawns with none btw)
  All 8 Pillagers will not despawn and 1 of the Pillager has a Raid Banner.
  These outposts will still spawn additional Pillagers over time.
  
- Crimson, Warped, and Nether Bricks Outpost Chest blocks now faces the correct direction.

##### Mansions:
- Fixed logspam from Snowy Mansions about Diorite Walls having wrong properties.

##### Fortresses:
- Lowered Jungle Fortresses a bit to increase the chances of flooded hallways.

##### Pyramids:
- Made Badlands Pyramid rarer by default in configs.

##### Villages:
- Made Giant Tree Taiga a bit less messy and fixed several pieces not having its Stone Pressure Plates randomly replaced. 

- Jungle Villages will now try not to spawn too close to Jungle Fortresses.

##### Mineshafts:
- Lavafalls will no longer spawn in Icy Mineshafts!


## (V.1.8.2 Changes) (1.16.5 Minecraft)

##### Cities:
- made Nether Cities have Netherite Scraps and Netherite Ingot in their chests.

##### Outposts:
- Adjusted looks of Crimson and Warped Outposts so their aged tower variants are different. Also renamed tower_glowing piece to tower_aged for both.

- Added aged variant for Nether Bricks Outpost.

##### Pyramids:
- Fixed pyramid not having pillar of their blocks below their pits when there is space below.

##### Strongholds:
- Added turnOffVanillaStrongholds config option to let people be able to turn off vanilla Strongholds to only have Nether Strongholds easier.

- Fixed Stone Shores still having Vanilla Strongholds instead of Repurposed Structures's which was causing false positives with /locate stronghold and Eyes of Ender as a result.

##### Villages:
- Fixed Zombie Badlands Village having 2 Birch Village houses by mistake.

##### General:
- Made all the checks I have for separating some structures from other kinds of structures now ignore those checks if the structure spacing is set super low in configs.
  Now if you make all outposts spawn in every chunk by config, Repurposed Structures villages will still be able to spawn.
  
- Made all hardcoded nether height structures now uses the chunk generator's maximum height. 
  So if you use a datapack to expand the Nether's roof, these structures should automatically now use the increased range!
  Some checks for Nether sealevel now uses the chunk generator's sealevel instead of a hardcoded value.
  
- Made all structures that checks surrounding biomes for valid biomes before spawning will now ignore that check if Checkered Biome Provider is used.


## (V.1.8.1 Changes) (1.16.5 Minecraft)

##### Witch Huts:
- Fixed Taiga Witch Hut Advancement triggering on world entering.

##### Outposts:
- Adjusted looks of Crimson and Warped Outposts so their aged tower variants are different. Also renamed tower_glowing piece to tower_aged for both.

- Added aged variant for Nether Bricks Outpost.

##### Misc:
- Moved StructureBlockScreenMixin so it only applies client side. Oops. It is not suppose to run on servers.


## (V.1.8.0 Changes) (1.16.5 Minecraft)

##### Dependencies:
- Cloth and AutoConfigu are no longer packaged with this mod to cut down on file size. Download those two lib mods separately. 

##### Cities:
- Added extremely rare Nether Cities to all Nether biomes! Beware of the Blazes! Maps to this city are very rare but can be found in Nether Ruins or Crimson/Warped Cartographer chests. 

##### Ruins:
- Added Nether Ruins to all Nether biomes! They are a great source of maps to undiscovered Bastion Remnants! Special thanks to miguelforge for the design!

##### Mansions:
- Added Birch Mansions! They are rarer than vanilla mansion to try and not crowd out the world.
  Cartographers may sell the mansion maps in Level 4 trades!

##### Witch Huts:
- Added Birch, Taiga, Giant Tree Taiga, Dark Forest, and Oak Witch Huts! They spawn Witches and Cats over time!

##### Mineshafts:
- Added Crimson and Warped Mineshafts!

- Tried to fixed Vines being improperly placed in Jungle and SwampAndDark Mineshafts and also added Leaves blocks to them for a more overgrown look.

- Changed Nether Mineshafts so they now carve into lava but places blocks to prevent floating lava. Tunnels should be less disconnected now.

- Moved Nether Mineshafts from UNDERGROUND_STRUCTURES to VEGETAL_DECORATION stage to allow Ancient Debris to be exposed in Mineshafts.
  As a bonus, Basalt Delta's Basalt will not clog up Mineshafts anymore too!
  
##### Pyramids:
- Added Snowy Pyramids to snowy biomes!

- Bumped default config spawnrate of Badlands Pyramids from 20 to 25 to make them a bit more rare.

- Badlands Pyramids now properly create pillar of Red Sandstone in parity to vanilla's Desert Temple.

- Rounded the bottom of Nether Pyramids so they look better even when the Nether's caves and ravines carves out the land from under the pyramid.

- Nerfed Golden Swords enchantment level range from 20-39 to 10-30 in chest's loottable for Nether Pyramids.

##### Outposts:
- Added Taiga Outposts to non-snowy and non-giant Taiga biomes!

- Added Oak Outposts to non-birch and non-dark Forest biomes!

- Badlands Outposts can spawn on Badland's Plateaus now using a special heightmap check to not spawn it on plateau walls.

- Removed Forge's gravity attribute from Brute in Nether Bricks Outpost to reduce logspam.

- Set default config for all Outposts to be slightly more rare to compensate for Outposts spawning in more biomes.

- Bumped default spawnrate of Birch, Jungle, Giant Tree Taiga, Desert, Badlands, Snowy, Oak, Taiga Outposts from 41/43 to 50 to make them slightly more rare.

- Fixed all Outposts spawning too close to vanilla villages.

##### Fortresses:
- Fixed Jungle Fortresses not getting extra vines or breakage.

- Attempt to reduce the amount of floating Vines in Jungle Fortress.

- Fixed Drowned not spawning in flooded Jungle Fortresses by removing them from the mob-spawning-over-time list and now just directly
  spawns some Drowned on fortress creation. These Drowned will not despawn and will sometimes have Iron or Chainmail armor.

- Bumped default config spawnrate of Jungle Fortresses from 32 to 50 to make them more rare.

- Nerfed Jungle Fortress loottables to pick less loot and diamond armor is removed. Leather armor is added instead.

##### Shipwrecks:
- Nether Bricks Shipwrecks will only be flying in any biome with "soul" in the name.

##### Strongholds:
- Lowered netherStrongholdMinHeight config default value to 10.

- Slightly adjusted Stonebrick Strongholds so they now won't be added to None-category biomes.

##### Temples:
- Moved Nether Basalt Temple from SURFACE_STRUCTURES to VEGETAL_DECORATION stage to prevent Basalt Delta's Basalt features from clogging up the insides.

- Adjusted Nether Basalt Temple's processor to randomize the temple's blocks a bit better.

- Nerfed Golden Swords enchantment level range from 20-39 to 10-30 in all chest's loottable for temples.

- Badlands Pyramids now properly create pillar of Red Sandstone in parity to vanilla's Desert Temple.

- Rounded the bottom of Nether Pyramids so they look better even when the Nether's caves and ravines carves out the land from under the pyramid.

##### Dungeons:
- Nerfed all golden tools and armor enchantment level range from 15-30 to 5-25 in Nether Dungeon chest's loottable.

- Lowered default config spawnrate for Ocean Dungeons from 6 to 5.

##### Villages:
- Replaced a lot of Cobblestone with wood in Oak Villages to make them more distinct from Plains Villages and made Path Blocks more common.

- Added zombie variant of Badlands Villages.

- Removed Forge's gravity attribute from Brute in Crimson and Warped Villages to reduce logspam.

- Bumped default config spawnrate of Birch, Oak, Dark Forest, Jungle, Swamp, Mountains, and Giant Tree Taiga Villages from 29/31/33 to 38 to make them more rare.

- Bumped default config spawnrate of Badlands Villages from 23 to 30 to make them more rare.

##### Misc:
- Deleted 2x2 Swamp Trees. They didn't fit this mod at all. They still exist in Ultra Amplified Dimension mod

- Made Structure Blocks name field now hold 128 characters like Jigsaw Blocks instead of 64 characters.
  Now loading up Repurposed Structures's village pieces are much easier.

##### General:
- Cleaned up biome selection code backend a lot and adjusted some biome selections conditions for a few structures. 

- Fixed structure pieces being registered under minecraft's namespace.

- Cleaned up en_us.json file to be nearly alphabetical. 


## (V.1.7.4 Changes) (1.16.4 Minecraft)

##### General:
- Improved biome targeting so some structures types get the right biome better.

##### Outposts:
- Replaced Dark Oak blocks with Spruce blocks in Snow Outposts

- Changed some blocks for the cages in badlands, Desert, Icy, and Snowy Outposts.  

##### Strongholds:
- Strongholds now will fit much better between the min and max y set in the config. 
  Rooms too low or too high will now be deleted (though the Portal Room will never be deleted).
  As a result, you may find a few rooms or hallways cutoff from the rest of the stronghold but that was
  a sacrifice I was willing to make to fix Strongholds sometimes spawning way aboveground than they should.
  
- Nether Strongholds no longer has Cobwebs in libraries and instead, may have fire.

- Strongholds will now not spawn in the End Dimension even when you add non-End category biomes to the dimension. With a datapack, you can
  add the RS stronghold spacing to the End Noise Setting and to the End category biomes to force the Stronghold to spawn in that dimension.

##### Villages:
- Badlands Villages now can spawn in non-plateau Badlands with a terrain check to try and keep them from generating on plateau walls.

- Badlands Villages now only contain Desert clothed Villagers instead of sometimes having Plains clothed Villagers.

- Fixed Birch Villages so they no longer have Oak Stripped Wood, Oak Fence Gate, or Oak Stairs.
 
- Fixed Churches not having doors in Birch, Dark Forest, and Oak Villages.

- Fixed Jungle Villages having Acacia blocks by mistake in the Weaponsmith house and fixed door in Butcher house being the wrong blockstate.

- Mountain Villages farms now have waterlogged Cobblestone Slab instead of Water Blocks to prevent them from turning into ice when too cold.

##### Wells:
- Fixed crash if the wells nbt file ever somehow goes missing.

##### Dungeons:
- Fixed mob spawners losing their mob data after doing `/reload` or `/datapack disable vanilla` commands.

##### Mineshafts:
- Fixed mob spawners losing their mob data after doing `/reload` or `/datapack disable vanilla` commands.

##### Igloos:
- Stony Igloos now uses Spruce Signs and their Villager/Zombie Villager now wears taiga clothing.

- Grassy Igloos basements now use Oak blocks instead of Spruce.

##### Fortresses:
- Added Drowned to Jungle Fortresses possible mob spawns to help populate the water filled hallways below sealevel.

##### Backend:
- Structure spacing for RS structures set by JSON will now no longer be overwritten. 
  With datapacks, you can make RS structures now spawn more or less frequently as a result.

- Redid code on how mobs spawn over time are added to RS structures to be cleaner and less likely I forget to add mob spawns later. 

-Prefixed all my accessor and invoker mixins due to this bug in mixins that could cause a crash with other mods for same named mixins.
 https://github.com/SpongePowered/Mixin/issues/430


## (V.1.7.3 Changes) (1.16.4 Minecraft)

##### Dungeons:
- Fixed world not loading due to setting dungeon config spawnrate above 128 due to a Mojang limit. 
  Now you can set spawnrate above 128 safely!
  
- Improved the looks of Mushroom Dungeons.
  
- Removed Turtle from Ocean Dungeon Spawners because Turtles Spawners 
  only spawn Turtles if above sealevel, in light, and on Sand.

##### Stronghold:
- Fixed world not loading due to setting chain config spawnrate above 128 due to a Mojang limit. 
  Now you can set spawnrate above 128 safely!

##### Outposts:
- Fixed English translation for Giant Tree Taiga Outpost Advancement.

##### Igloos:
- Fixed missing Ladder block at top of some basements in Grassy and Stone Igloos.


## (V.1.7.2 Changes) (1.16.4 Minecraft)

##### Outposts:
- Fixed Warped Outposts not having 6 Piglins and a Brute at first spawning.


## (V.1.7.1 Changes) (1.16.4 Minecraft)

##### Outposts:
- Replaced some Birch Logs with Birch Wood in Birch Outposts to make it look better. 

- Overworld Outposts now mesh much better with the surrounding terrain.

- Lowered default value for outpostBadlandsMaxChunkDistance config entry from 37 to 31
  so that the Badlands Outpost isn't as extremely rare as it was before.

##### Backend:
- Updated the Angerable Patch dependency so that Dog's collar color is saved properly.


## (V.1.7.0 Changes) (1.16.4 Minecraft)

##### New additions!:
- End themed Ruined Portals now spawn in the End dimension!
  Special thanks to miguelforge for creating them!

- Added Birch, Jungle, Icy, Snowy, Giant Tree Taiga, Desert, and Badlands Pillager Outposts!

- Added Oak Village that spawns in any forest category biome that isn't birch or dark forest!

##### Backend:
- Reworked and cleaned up backend to now 
  use Fabric API's Biome Modification API.

- Fixed many structures not using the speedy locate method.

- Fixed a bug that could still spawn Repurposed Structures's stuff in superflat.

##### Advancements:
- Advancements now have English translations built in so vanilla clients do not 
  need any en_us.json resource pack anymore! If you want clients to have different
  translations from each other, put Repurposed_Structures-Translation_Advancements
  datapack on the server and now each client can make their own en_us.json resourcepack
  for their own language translations.
  
- Organized advancements into subfolders now so it is cleaner.

##### Strongholds:
- Fixed Strongholds being cutoff by Bedrock.

- Stonebrick Strongholds no longer can be added to End category modded biomes anymore.

- Added allowStonebrickStrongholdToVanillaBiomes config option for Stonebrick Strongholds. 
  It now now possible to have Repurposed Structures's Stonebrick Strongholds to only spawn 
  in modded biomes, or only in vanilla biomes, or both by changing the values of
  addStonebrickStrongholdToModdedBiomes and allowStonebrickStrongholdToVanillaBiomes.

- Fixed Stronghold Chains placing floating lanterns at y = 3.

##### Mineshafts:
- End Mineshafts will now be much more buried in the End's islands 
  if barrensIslandsEndMineshafts config option is turned off.

##### Shipwrecks:
- Fixed addWarpedShipwreckToModdedBiomes config not working.

##### Fortresses:
- Jungle Fortresses now need to be fully enclosed in a jungle category biome to spawn.

- Increased chance of a hallway having a chest in Jungle Fortresses from
  1/9th chance to 1/5th chance. Nether Fortress is 1/3rd chance for chest 
  in hallway for reference.

- Nerfed Jungle Fortress chest loot a bit

##### Igloos:
- Grassy and Stone Igloos are now added to modded 
  biomes by default unless turned off in config.

##### Outposts:
- Buffed Nether Outposts's loot a bit.

##### Villages:
- Adjusted Dark Villages so they are added to biomes with "dark" in the name and is 
  forest category instead of only be added to biome with "dark_forest" in name.

- Adjusted some villages pieces to allow Villagers to reach workstation as
  Mojang broke the Villager AI with a bug lol.
  
- Fix several instances of stairs and fences being in impossible blockstates 
  in the Villages. Also fixed some random Dirt blocks being in Villages.
  
- Fixed a Dark Oak Village house being fused with a farm for no reason lmao.

- Reduced chances of Village's Iron Golem spawning inside a tree and dying.
  
- Added a new Badlands Library made by miguelforge!
  
- Nerfed the default spacing config value for all overworld villages.

##### Miscellaneous:
- Giant Boulders in Giant Tree Taiga biomes will now no longer float on water.

##### General Configs Changes:
- Changed "spawnrate" for dungeon entries to say "attemptsPerChunk" instead.

- Changed "spawnrate" for well entries to say "rarityPerChunk" instead.

- Changed "spawnrate" for majority of structures entries to say "maxChunkDistance" instead.

- Removed "JF" from some Jungle Fortress entries.

- Biome blacklisting configs now will ignore spaces between entries.


## (V.1.6.9 Changes) (1.16.3 Minecraft)

##### Outposts:

- Nether Outposts now has 1 Piglin Brute.

##### Dungeons:

- Ocean Dungeons chests will now have Prismarine placed under 
  them if they are floating. Should reduce the amount of 
  floating chests by a lot. 

- Removed floating plants from above Ocean Dungeons as best I can.

##### Shipwreck:

- Fixed map chest loot not showing up in Warped Shipwreck.

##### Misc:

- Giant boulders in Giant Tree Taiga biomes now are more varied in size.

- Default config spawnrate of giant boulders was reduced.


## (V.1.6.8 Changes) (1.16.3 Minecraft)

##### Villages:

- Fixed typos in template_pools so that now ALL village pieces can spawn in villages!

##### Config:

- Dimension blacklist will now ignore any spaces you leave between entries in it.


## (V.1.6.7 Changes) (1.16.3 Minecraft)

##### Misc:

- Fixed crash with some mod's custom ChunkGenerator.
  Just be careful to not use /locate in these custom dimensions
  as that could have a chance of causing the game to hang 
  depending on how they coded the ChunkGenerator. 
  Will try and think of some sort of solution to this...


## (V.1.6.6 Changes) (1.16.3 Minecraft)

##### Misc:

- Added check to automatically blacklist any dimension using the
  FlatChunkGenerator (Superflat worldtype) because that chunk generator
  will spawn all RS's structures at once and make /locate on certain 
  RS structure cause the server to hang forever.

##### Backend:

- Cleaned up codebase a bit.


## (V.1.6.5 Changes) (1.16.3 Minecraft)

##### Mod Compat:

- Fixed a crash with Applied Energistics 2.


## (V.1.6.4 Changes) (1.16.3 Minecraft)

##### Mod Compat:

- Fixed possible crash with some biome mods if they do 
  strange stuff with their biome provider.
  
  
## (V.1.6.3 Changes) (1.16.3 Minecraft)

##### Major:

- Fixed possible crash when re-entering superflat worlds or 
  blacklisting a RS structure from a vanilla dimension.

##### Igloos:

- Fixed bug where Grassy Igloos had 2 Villages and 2 Zombie Villages instead of 1 of each.
 

## (V.1.6.2 Changes) (1.16.3 Minecraft)

##### Configs:

- Added config option to blacklist all Repurposed Structures's structures from
  any dimension(s) you specify. Finally figured out how to do it safely!
  Config is in the Main config category.

##### Major:

- All structures now will spawn away from themselves with spacing being between
  the spawnrate's number and half that number (in chunks). So a spawnrate of 50
  means the structure will not be more that 50 chunk apart from another and cannot 
  be closer than 25 chunks to itself.

- The 'salt' used for structure placement has now been fully randomized due to some
  weird quirks in Java's RNG that causes different structures to have similar effects
  from their salts if the salts are too similar.
  
##### Mineshafts:

- Made it so Mineshafts will not spawn in exact same coordinates 
  if multiple are added to the same biome.

##### Igloos:

- Grassy and Stone Igloos are now Jigsaw structures! You can use datapacks to change their
  ladder and basements nbt pieces now. And their basement chests now has its own loottable!


## (V.1.6.1 Changes) (1.16.3 Minecraft)

##### Misc:

- Jar-in-jar'd Modmuss's Voyager to fix a rare bug that crashes servers
  running Java 11 when they try to load two chunks with structures in 
  two dimensions at the same time.

##### Fortress:

- Fixed Jungle Fortress Map not being translated properly

##### Mineshafts:

- Now has compat with Yungs Better Mineshafts. Neither Mineshafts will go missing.
  The Mineshafts will now be added to modded biomes of the right type
  even if said biomes do not have Vanilla Mineshafts. Use the config
  blacklist to prevent Repurposed Mineshafts in certain biomes.

## (V.1.6.0 Changes) (1.16.3 Minecraft)

##### Misc:

- Load up some structure nbt files at world startup instead of during worldgen.

- Fixed registry names of all features (not structures) to not be in minecraft namespace by mistake.

- Organized all structure loottables file path. Will break datapacks still using old path.

- Organized config screen to say what entries does instead of hiding it in tooltips.

- Adjusted spacing between two different structures in many nether structures.

##### Shipwrecks:

- Added Warped and Crimson Shipwreck to their respective Nether biomes! 
  Special thanks to miguelforge for providing the designs and nbt file!
  
- Added Nether Bricks Shipwreck to all other Nether biomes! 
  Special thanks to cannon_foddr for providing the designs and nbt file!

##### Villages:

- Saloon spawnrate increased

- Fixed name of Nether Fortress and Bastion Remnant maps from Nether Villages.

##### Pyramids:

- Fixed Badlands Pyramid's trap not always fully activating.

##### Mineshafts:

- Increased default spawnrate of End Mineshafts

## (V.1.5.4 Changes) (1.16.2 Minecraft)

##### Misc:

- Registered all ConfiguredFeatures and converted all Template Pools/Structure Processors 
  into JSON so they can be used or overridden more easily by datapacks.

- Significantly cleaned up code in backend again.

##### Villages:

- Adjusted the weights for straight roads and houses with beds to try
  and make Villages have more bed houses and not be just empty with only
  clusters of roads. Affects all Repurposed Structures villages.

- Fixed waterlogging issue in Animal Pen 1 in Giant Tree Taiga Village.

- Fixed Item Frames with Potions missing from Saloons in Badlands Villages.

##### Boulders:

- Giant Boulders in Giant Tree Taiga Hills and Giant Spruce Taiga Hills now
  are place lower to fit on terrain better and their surfaces are roughed up.

##### Swamp Trees:

- Increased spawnrate of Horned Swamp Trees in Swamp Hills biome

## (V.1.5.3 Changes) (1.16.2 Minecraft)

##### Structures:

- Fixed Warped Village not spawning.

## (V.1.5.2 Changes) (1.16.2 Minecraft)

##### Backend:

- Removed LibStructure and is now using Fabric API's built in structure registration. (Fabric API must be latest in order to work)

##### Fortresses:

- Fixed bug where one type of crossing in Jungle Fortress would not get filled with water properly when below sealevel.

##### Wells:

- Nether Well's maximum height lower to 120 so it cannot generate on bedrock ceiling anymore.

##### Swamp Trees:

- Swamp Trees now gets added as an addition to modded biomes instead of replacing the other mod's swamp trees.

## (V.1.5.1 Changes) (1.16.2 Minecraft)

##### Misc:

- Bumped LibStructure version to v1.5

##### Villages:

- Added a single Piglin Brute to Villages in Nether.

- Increased Netherrack Gold Ore and Blackstone Gold Ore rates slightly in Nether Villages.

- Slightly made Nether Villages more rare in config default.

##### Outposts:

- Slightly made Nether Outposts more rare in config default.

##### Temples:

- Slightly made Nether Temples more common in config default.

## (V.1.5.0 Changes) (1.16.2 Minecraft)

##### Major:

- Ported to 1.16.2 Minecraft and had to change a lot of backend stuff. Please report any bug!

## (V.1.4.5 Changes) (1.16.1 Minecraft)

##### Dungeons:

- Fixed crash on servers due to me accidentally using a clientsided method in the dungeon spawner code...

## (V.1.4.4 Changes) (1.16.1 Minecraft)

##### Dungeons:

- Added a null check for if the game is unable to find rs_spawner json files. 
  Will instead use vanilla's default mobs and write to the log about the error 
  if it fails to read the json file instead of crashing.

## (V.1.4.3 Changes) (1.16.1 Minecraft)

##### Villages:

- Fixed Nether Villages spawning above ceiling in Nether.

## (V.1.4.2 Changes) (1.16.1 Minecraft)

##### Configs:

- Set default value for all add___ToModdedBiomes to true except for Giant Boulders which will remain in vanilla biomes by default (unless manually changed in config)

- Fixed several untranslated descriptions, tooltips, and names in configs.

##### Mineshafts:

- Raised the default minimum Y height up a bit for several Mineshafts in the config.

##### Shipwrecks:

- Made End Shipwrecks spawn a bit more frequently

##### Villages:

- Made Nether Villages slightly more spaced out by default in config.

##### Outposts:

- Made Nether Outposts slightly more spaced out by default in config.

##### Temples:

- Made Nether Temples and Pyramids slightly more spaced out by default in config.

##### Other:

- Cleaned up code in backend... Made sure that Outpost, Igloo, Shipwreck, and Temple's pieces can be overridden by datapacks.

## (V.1.4.1 Changes) (1.16.1 Minecraft)

##### Villages:

- Center of Swamp Villages are now lowered by 1 block to fit better with terrain.

##### Misc:

- Fixed /locate to not rarely skip over a closer structure. 
  Special thanks ontrigger for finding the fix!

- Large swamp trees should not have tall grass replacing their trunks now.

##### Config:

- Added configs to allow entire RS's types of structures to be blacklisted from specified biomes.

- Split addMiscToModdedBiomes into addSwampTreeToModdedBiomes and addBoulderToModdedBiomes.

## (V.1.4.0 Changes) (1.16.1 Minecraft)

##### Strongholds:

-In Nether Strongholds, Black Terracotta is now removed. Instead the Nether Stronghold will be 
 made of mostly Blackstone variant blocks with some Nether Bricks and Magma Blocks scattered throughout. 
 Magma Blocks rate is cut in half now.

-In Nether Strongholds, the following blocks have been changed: 
   Dark Oak Planks is now Crimson Hyphae
   Dark Oak Fences is now Crimson Fence
   Iron Doors is now Crimson Doors
   Redstone Torches is now Soul Torches

-In Nether Strongholds Libraries now have a variety of Blackstone blocks instead of Bookcases.

##### Mineshafts:

-In Nether Mineshafts, Redstone Lamp is now replaced with Shroomlight. 

-In Nether Mineshafts, Redstone Torches is now replaced with Soul Torches.

-Fixed End Mineshafts generating completely in midair when barrensIslandsEndMineshafts config is off. 
 (Was due to End Midlands Biome not always making land)
 (When barrensIslandsEndMineshafts config is off, the mineshaft spot must have land at y = 20 or higher now)

##### Villages:

-Fixed it so that Warped and Crimson Villages cannot be within 10 chunks of any Nether Outposts now for real.

-Added missing loot to chests in Warped Villages.

-Fixed Zombified Piglins not spawning in zombie Crimson and zombie Warped Villages.

-Zombie Crimson and zombie Warped Villages should now have Soul Torches instead of Redstone Torches.

##### Outposts:

-Nether Outposts cannot be within 3 chunks of Nether Pyramids.

##### Temples:

-Fixed it so that Nether Temples cannot be within 6 chunks of any Nether Outposts now for real.

-Nether Temples cannot be within 3 chunks of Nether Pyramids.

##### Pyramids:

-Fixed it so that Nether Pyramids cannot be within 6 chunks of any Nether Outposts now for real.

##### Shipwrecks:

-Shipwrecks now shouldn't spawn ever in the void at bottom of world and less often on edges of islands. 
 (Their spot must have land at y = 20 or higher now)

## (V.1.3.3 Changes) (1.16.1 Minecraft)

##### Outposts:

-Mobs that initially spawn in Warped, Crimson, and Nether Brick Outposts will not despawn over time now.

##### Villages:

-Mobs that initially spawn in Warped and Crimson Villages will not despawn over time now.

##### Dungeons:

-Slightly decrease the default config spawnrate for Ocean Dungeons.

## (V.1.3.2 Changes) (1.16.1 Minecraft)

##### All structures:

-Fixed locateStructure speed up to truly return closest structure now and villages now uses the speed up method.

##### Strongholds:

-addStonebrickStrongholdToModdedBiomes is now true by default

-Nether Strongholds now uses Polished Blackstone Buttons instead of Stone Buttons.

-Eyes of Ender will now point to closest Stronghold of all 3 types (vanilla, RS's stonebrick, RS's nether) when more than one is present in a dimension.

##### Temples:

-All Nether Temples cannot be within 6 chunks of any Nether Outposts.

##### Pyramids:

-Nether Pyramids cannot be within 6 chunks of any Nether Outposts.

##### Villages:

-Warped and Crimson Villages cannot be within 10 chunks of any Nether Outposts.

##### Misc:

-Added config to change the chance of Diamond Ore in Giant Boulders. (Default rate reduced from 1/3000 to 1/7000 now)

-Added config to allow control of how many Giant Boulders to spawn per chunk. (Default reduced for Vanilla biomes now)

-Decreased Iron Ore rates heavily and slightly reduced Coal Ore rates in Giant Boulders.

## (V.1.3.1 Changes) (1.16.1 Minecraft)

##### Villages:

-Moved the Cartographer code to later to prevent crash with Better Wandering Traders mod.

-Fixed name of Jungle Fortress Map from Cartographers.

-Replaced Grass in the town center pieces of Crimson and Warped Villages with Crimson or Warped Roots.

-Removed small chance of large patches of fire in Crimson and Warped Villages.

## (V.1.3.0 Changes) (1.16.1 Minecraft)

##### All Structures:

-Greatly improved the /locate command's and treasure map's speed at finding Repurposed Structures's structures at any distance (including the Mineshafts too)!

##### Advancements:

-Improved the Advancement layout greatly!

##### Villages:

-Added Crimson Village to Crimson Forest biome in the Nether!

-Added Warped Village to Warped Forest biome in the Nether!

-Moved the village pool registration to during worldgen to make sure datapacks can replace the village piece nbt files.

-Added Jungle Fortress Maps to Cartographer tier 3 and 4 trades!

##### Fortress:

-Jungle Fortress now needs to be more inside a Jungle biome to spawn instead of close to the edge.

##### Shipwrecks:

-Fixed End City Map's name in End Shipwrecks.

## (V.1.2.1 Changes) (1.16.1 Minecraft)

##### Wells:

-Fixed Nether Wells crashing when a mod adds a biome that returns null for their top block in their surfacebuilder. Dunno why anyone would do that but someone did lol.

##### Outposts:

-Fixed Warped and Crimson Outpost's config tooltip not showing.

## (V.1.2.0 Changes) (1.16.1 Minecraft)

##### Major:

-Removed code from v1.1.1 and v1.1.2 as the patch didn't fix the broken vanilla world issue with missing structures. 
Upvote the issue on Mojang's issue tracker here to help get them to see the bug! https://bugs.mojang.com/projects/MC/issues/MC-194811

##### Advancements:

-Added advancements for entering all structures!

##### Config:

-Added config to allow End Mineshafts to spawn in End Islands and End Barrens biomes.

##### Strongholds:

-Both Overworld and Nether Strongholds no longer will spawn within 1600 blocks of world origin.

-Added Chains to both Overworld and Nether Strongholds and the chains has a small chance of having a Lantern or Soul Lantern!

##### Shipwrecks:

-Added End Shipwreck to End Highlands! Check out their amazing loot! (Special thanks to cannon_foddr for the design!)

##### Fortress:

-Nerfed Jungle Fortress loot significantly and changed them to use their own loottables (fortress_jungle_xxxxx_chest.json) so you cna change them with datapacks now

-Jungle Fortress's plant room will not place plants if it is below sea level now. (No more floating mushrooms items when the plant room is flooded)

##### Villages:

-Fixed Giant Tree Villages being added to vanilla's Giant Tree Taiga Hills and Giant Spruce Taiga Hills biomes when the addVillagesToModdedBiomes config is on. 

-Fixed missing zombie villager pool in Mountains Village.

##### Mineshafts:

-End Mineshafts will now spawn Endermites over time in them naturally.

##### Outposts:

-Changed name of Nether Bricks Outposts chest's loot table to outpost_nether_brick_chest.json .

##### Pyramids:

-Changed name of Badlands Pyramid chest's loot table to pyramid_badlands_chest.json .

##### Misc:

-Fixed Vines have horizontal vine part floating when stacked on each other.

-Fixed vines to not be strangely attached to nothing anymore.

-Slightly nerfed giant boulders in vanilla's Giant Tree Taiga and Giant Spruce Taiga biomes.

## (V.1.1.2 Changes) (1.16.1 Minecraft)

##### Major:

-Forgot to uncomment out the structures from testing...

## (V.1.1.1 Changes) (1.16.1 Minecraft)

##### Major:

-Attempted to fix a Mojang bug that breaks worlds and is caused by missing structures. hopefully this works...

##### Fortresses:

-Fixed vines looking weird when it tries attaching to block above in Jungle Fortress.

-Slightly increased breakage of Jungle Fortress.

## (V.1.1.0 Changes) (1.16.1 Minecraft)

##### Mod Compat: 

-Fixed config for adding structures to modding biomes as it was not working before.

##### Datapacks: 

-Added a new folder called data.repurposed_structures.rs_spawners where you can specify what kind of mob spawner can be in RS's dungeons, mineshafts, strongholds, and jungle fortress! You can specify more than one mob and what the chances are of the spawner being that mob.

##### Outpost:

-Added Nether Bricks Outpost to the Nether biomes! They will spawn Piglins naturally over time.

##### Temples: 

-Renamed Nether Temple to Nether Wasteland Temple.

-Renamed Badlands Temple to Badlands Pyramid.

-Added Nether Warped, Basalt, Crimson, and Soul Temples to the other Nether Biomes! The Nether Wasteland Temple now only spawns in Wastelands.

-Nether Wasteland Temple chests now face the correct way and part of the temple made of Cracked Nether Bricks. Also fixed their Redstone puzzle that broke when Redstone mechanics were changed.

-Nether Wasteland Temples and other nether temples are now an NBT file under structures/temples/ and can be replaced with datapacks. 

-Nether Pyramids now has rotation and land will be generated around them.

-Badlands Pyramids are now made with NBT files under structures/temples/.

##### Fortresses:

-Jungle Fortresses will now very rarely spawn Wither Skeletons inside over time like how Nether Fortresses do.

-Fixed Vines in Jungle Fortress so it is not strangely placed and that they can sometimes replace the Fortress's blocks.

-Parts of the Jungle Fortress will be broken to help give a more ancient and old feel while letting more light inside.

-Jungle Fortresses's hallways will be flooded with water when below sea level.

##### Strongholds: 

-Nether Strongholds now will spawn Blaze, Wither Skeletons, Zombified Piglin, Magma Cube, and Skeletons naturally over time like Nether Fortresses do.

##### Villages: 

-Fixed Giant Tiaga, Mountains, and Swamp Village's zombie houses not spawning.

##### Wells: 

-Fixed some wells from having their fences attached to air.

-Adjusted Nether Wells so they don't hang as much on ledges.

## (V.1.0.1 Changes) (1.16.1 Minecraft)

##### Mineshafts: 

- End Mineshafts no longer spawn in Small End Island Biome

##### Dungeons: 

- Mushroom and Badlands dungeons now spawn again instead of Icy Dungeons.

##### Misc: 

- Moved logs in Horned Swamp tree up one to stop leave decay.

##### Configs:

- Fixed Dungeon's config tooltips

- Fixed Mineshaft's config tooltips

- Fixed Stronghold's config tooltips

- Fixed Wells's config tooltips

- Fixed Village's config tooltips

- Fixed Misc's config tooltips


## (V.1.0.0 Changes) (1.16.1 Minecraft)

##### Major: 

-First release of this mod for Fabric!!!!! PARTY TIME!! 

-Ported v1.7.2 Forge version to Fabric! So many tweaks and fixes was done that basically, don't compare this to the Forge version anymore lol. 