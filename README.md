# MAVEN

For developers that want to add RS to their mod's workspace:

<blockquote>
repositories {

&nbsp;&nbsp;&nbsp;maven {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;url "https://nexus.resourcefulbees.com/repository/telepathicgrunt/"

&nbsp;&nbsp;&nbsp;}

}
</blockquote>

&nbsp;

Don't forget to change \<modversion> with the actual latest version of this mod.

<blockquote>
dependencies {

...

&nbsp;&nbsp;&nbsp;&nbsp;modImplementation "com.telepathicgrunt:RepurposedStructures-Fabric:\<modversion>+1.19"

}</blockquote>

**____________________________________________________________________________**

&nbsp;

<img src="https://i.imgur.com/lD8Pa6U.png" alt="Picture that shows the title of this mod with a Stonebrick Fortress during sunset behind the text" width="1519" height="516"></img>
## CURRENTLY FOR 1.17.0 MC
#### Works serverside too so vanilla clients can connect!
###### CLICK HERE FOR FORGE 1.16.5 MC PORT: https://modrinth.com/mod/repurposed-structures-forge

**Recent News:** Updated to 1.17!!! Huge changes to config files including ability to change what mobs spawns over time in my structures! Advancements are condensed into fewer files and less spammy! Underground Bastion is now made of Deepslate! And a ton of bug fixes and file paths renamed for loot tables and stuff to be more consistent! See changelog for more info.

Also, Charm Compatibility datapack is now released in the datapack section further down to add Charm houses to RS's villages!

<br/>
____________________________________________________________________________

# WHAT IS REPURPOSED STRUCTURES?
<br/>

Repurposed Structures is a mod about taking existing vanilla features and structures and creating new variants or modifications to them! Originally, most of the structures and features in this mod was actually made for my Ultra Amplified Dimension mod. but then I realized these features and structures would look great in the Overworld as well so I decided to take those structures and features and split them into their own mod and continue to add MORE biome variants of vanilla structures! And over time, some other people even help contribute more vanilla styled structures!

<br/>

### Currently, this mod has:
<details>

- Jungle Fortresses which are Nether Fortresses using Stonebrick blocks and spawns in Jungles!
- 10 Dungeons variants
- 14 new Mineshaft types
- 10 new biome variants of Villages
- 15 new temples variants
- 2 new biome variants of Igloos
- 13 new Pillager Outposts
- 4 new types of Shipwrecks
- 7 new Mansion types
- 5 new Witch Huts types
- 3 new kinds of Ruins! (2 on land. 1 in Nether)
- A Nether themed End City
- A Nether Strongholds
- An Underground Bastion in Overworld
- 6 new Desert Well variations were made for other biomes
</details>
<br/>
<br/>

If you have trouble finding one of these structures, just start typing `/locate repu` into the chat and you should see my structures pop up! For example, typing `/locate repurposed_structures:stronghold_nether` will find the closest RS's Nether Stronghold to your location in the Nether dimension. All structures can be found easily from anywhere!
<br/>
<br/>

### Frequently Asked Questions:
<details>


Q: Does this mod work with [YUNG's Better Strongholds](https://www.curseforge.com/minecraft/mc-mods/yungs-better-strongholds-fabric) mod?

<br/>
A: Yes indeed it will. Do note, RS's Nether Stronghold will still spawn in Nether biomes and be unchanged.

<br/>
<br/>
<br/>
<br/>

Q: Does this mod work with [YUNG's Better Mineshafts](https://www.curseforge.com/minecraft/mc-mods/yungs-better-mineshafts-fabric) mod?

<br/>
A: Yep! This mod works with YUNG's Better Mineshafts lol. I added code to make our mods compatible. Both of our mineshafts should spawn in the world.

<br/>
<br/>
<br/>
<br/>

Q: Does this mod conflict with ___ mod?

<br/>
A: Highly unlikely. My mod adds structures to biomes directly so any conflict would be extremely rare. Best way to know is to just try my mod with the other mod. If you find they do conflict somehow, PLEASE let me know so I can investigate and release a fix! :)

<br/>
<br/>
<br/>
<br/>

Q: The advancements are annoying! I want to turn them off!

<br/>
A: There are datapacks to turn off the advancements. Scroll down to the "PRE-MADE DATAPACKS" section farther down and open the spoiler to get the disable advancements datapacks!

<br/>
<br/>
<br/>
<br/>

Q: How can I change the language that this mod uses?

<br/>

A: You can use a Resourcepack to add new languages and help contribute! [Click here for more info!](https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/releases/tag/0.0.8)

<br/>
<br/>
<br/>
<br/>

Q: Can I use this mod in a modpack?

<br/>
A: Yep! You can use this mod in a modpack :)

<br/>
<br/>
<br/>
<br/>

Q: Can I modify the source code of this mod to make changes?

<br/>
A: You can but just make sure you respect my license which is LGPL. That means, if you plan on distributing a modified Repurposed Structures jar, you should open source it by making the modified source code visible and licensed under an open source license. But truthfully, if you want to make a change, ask me first or make a PR into my mod as I might actually add it to the base mod itself! 
</details>
<br/>
<br/>

### Extra Details about everything in this mod!

<details>
<br/>

Note: all chest loot info can be found here in the loot table files for the structure! : [https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/tree/1.16/src/main/resources/data/repurposed_structures/loot_tables/chests](https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/tree/1.16/src/main/resources/data/repurposed_structures/loot_tables/chests)

<br/>
<br/>

### Village Variants

<details>
Note: Some village houses will have custom loot to match their biome better but nothing too crazy for balance reasons. The Nether based villages are full of fully armored Piglins and have fully unique chest loot.

<br/>
<br/>

Variants:

<br/>

-Badlands Village

-Birch Village

-Dark Forest Village

-Jungle Village

-Swamp Village

-Mountains Village

-Giant Taiga Village

-Oak Village

-Crimson Village

-Warped Village

</details>

<br/>

### Dungeons

<details>

#### Badlands Dungeons spawner's mob rates

48% : Husk

25% : Cave Spider

17% : Skeleton

8% : Spider

2% : Illusioner

<br/>

#### Dark Forest Dungeons spawner's mob rates

43% : Zombie

20% : Spider

20% : Skeleton

15% : Vex

2% : Illusioner

1% : Creeper

<br/>

#### Desert Dungeons spawner's mob rates

73% : Husk

13% : Spider

12% : Skeleton

1% : Llama

1% : Illusioner

<br/>

#### End Dungeons spawner's mob rates

86% : Endermite

12% : Enderman

2% : Phantom

<br/>

Spawns Shulker Box instead of Chests to hold the loot but spawns less frequently than Chests.

<br/>

#### Icy Dungeons spawner's mob rates

75% : Stray

25% : Cave Spider

<br/>

#### Jungle Dungeons spawner's mob rates

37% : Zombie

25% : Parrot

18% : Spider

18% : Skeleton

1% : Chicken

1% : Creeper

<br/>

#### Mushroom Dungeons spawner's mob rates

#### if above Y = 64

96% : Rabbit

4% : Mooshroom

<br/>

#### if below Y = 64

96% : Bat

4% : Mooshroom

<br/>

#### Nether Dungeons spawner's mob rates

#### if above Y = 30

44% : Zombified Piglin

20% : Strider

26% : Magma Cube

10% : Blaze

<br/>

#### if below Y = 30

44% : Zombified Piglin

20% : Strider

25% : Magma Cube

10% : Blaze

1% : Wither Skeleton

<br/>

#### Snow Dungeons spawner's mob rates

37% : Stray

25% : Cave Spider

24% : Zombie

12% : Spider

2% : Snow Golem

<br/>

#### Swamp Dungeons spawner's mob rates

24% : Zombie

24% : Drowned

16% : Spider

16% : Skeleton

9% : Vex

1% : Slime

<br/>

#### Ocean Dungeons spawner's mob and loot rates

Chest loot

45% : Normal Dungeon loot

33% : Shipwreck Supplies loot

13% : Small Ruins loot

8% : Big Ruins loot

1% : Buried Treasure loot

plus a few various other ocean-themed loot

<br/>

#### Mob Spawner in Frozen Ocean biomes

96% : Drowned

3% : Squid

1% : Turtle

<br/>

#### Mob Spawner in Cold Ocean biomes

96% : Drowned

3% : Salmon

1% : Turtle

<br/>

#### Mob Spawner in neutral Ocean biomes

96% : Drowned

3% : Cod

1% : Turtle

<br/>

#### Mob Spawner in Lukewarm Ocean biomes

96% : Drowned

3% : Pufferfish

1% : Turtle

<br/>

#### Mob Spawner in Warm Ocean biomes

96% : Drowned

3% : Tropical Fish

1% : Turtle

</details>

<br/>

### Mineshafts

<details>
All other Mineshafts has Cave Spider Spawner unless otherwise specified. And chest loot is specifically tailored to every Mineshaft with some having very unique loot and others having loot that's barely different.

<br/>
<br/>

#### Variants:

<br/>

-Birch Mineshafts

-Desert Mineshafts

-Jungle Mineshafts

-Savanna Mineshafts

-Stone Mineshafts

-Taiga Mineshafts

-Swamp Mineshafts

-Dark Forest Mineshafts

-Icy Mineshafts (has Stray Spawners)

-Ocean Mineshafts (has Drowned Spawners)

-Nether Mineshafts (has Blaze Spawners)

-Warped Mineshafts (has Blaze Spawners)

-Crimson Mineshafts (has Blaze Spawners)

-End Mineshafts (has Endermite Spawners) and will also naturally spawn Endermites over time)

</details>

<br/>

### Mansions
<details>

Chest loot is using a barely modified Woodland Mansion chest loot with most changes being the food. Mansion spawnrates are set to be very rare by default as to not clutter your world. This can be changed in configs.

<br/>
<br/>

Cartographers may sell maps to these mansions in their level 4 trades

<br/>
<br/>

Variants:

<br/>

-Birch Mansion

-Taiga Mansion

-Jungle Mansion

-Oak Mansion

-Savanna Mansion

-Desert Mansion

-Snowy Mansion
</details>

<br/>

### Nether City (Nether themed End City without the ship)
<details>

Will spawn Blazes often and Wither Skeletons very rarely inside the city over time

<br/>
<br/>

Spawns several Wither skeletons upon first generation

<br/>
<br/>

Maps to Nether Cities can be found in Nether Ruins or very rarely, in chest in Cartographer buildings in Warped/Crimson Villages

<br/>
<br/>

Chest loot is highly Netherite based to reflect the danger of the city
</details>

<br/>

### Bastions

<details>
#### Underground Bastions

<details>
Spawns only Skeletons over time inside instead of other monsters

Skeletons at initial spawning may have swords, be slightly faster, and wearing stronger armor

Reduced the chances of Underground Bastions not having a stable, unit, or treasure room. Most of time, there will be a massive room underground for the bastion.
</details>
</details>

<br/>

### Ruins

<details>

#### Warm Land Ruins

<details>
May contain maps to Underground Bastions in ungenerated chunks

<br/>

High amounts of Wheat surrounding the ruins
</details>

<br/>

#### Hot Land Ruins

<details>
May contain maps to Underground Bastions in ungenerated chunks
</details>

<br/>

#### Nether Ruins

<details>
May contain maps to Bastion Remnants or Nether Cities
</details>
</details>

<br/>

### Jungle Fortress
<details>
Can naturally spawn Wither Skeletons over time but it is extremely rare

<br/>

May spawn Drowned upon first generation with some armor in the flooded hallways

<br/>

Will have 2 Silverfish Mob Spawners as well as random blocks sometimes being infested already
</details>

<br/>

### End Ruined Portals

<details>
Chest Loot is heavily high-leveled gold based. May also contain maps or End City maps.
</details>

<br/>

### Outposts
<details>

#### All Overworld Outposts

<br/>

All Overworld Repurposed Structures Outpost will spawn Pillagers over time. And chest loot is nearly identical to the normal Pillager Outpost loot but some changes has been done to make the loot fit the biome theme they are in.

<br/>
<br/>

Some of the "aged" version of the Outpost towers are more unique in their decay or aged looks. This is based on how Vanilla Outpost tower can be either normal or "overgrown" with Mossy Cobblestone.

<br/>
<br/>

#### Overworld Variants:

<br/>

-Birch Outposts

-Desert Outposts

-Jungle Outposts

-Giant Tree Taiga Outposts

-Stone Mineshafts

-Badlands Mineshafts

-Taiga Outposts

-Oak Mineshafts

-Icy Mineshafts

-Snowy Outposts (can have trapped Snow Golems as well)

<br/>

#### End Outpost

<br/>

Can naturally spawn Phantoms over time

<br/>

Spawns with Chorus Plants and some Shulkers mobs

<br/>

Chests loot is heavily combat based with stuff that is effective against Phantoms

<br/>

#### Nether Brick Outpost

<br/>

Can naturally spawn Piglins over time

<br/>

Chests loot is heavily combat based with gold gear

<br/>

#### Warped Outpost

<br/>

Can naturally spawn Piglins over time

<br/>

Chests loot is heavily combat based with gold gear

<br/>

#### Crimson Outpost

<br/>

Can naturally spawn Piglins over time

<br/>

Chests loot is heavily combat based with gold gear
</details>

<br/>

### Temples
<details>

#### Nether Wasteland Temple

<br/>

Dispensers: 5 to 14 Arrows of Harming II

<br/>

Has one hidden Zombified Piglin spawner

<br/>

#### Nether Basalt Temple

<br/>

Dispensers: 5 to 14 Arrows of Weakness I

<br/>

Has one hidden Magma Cube spawner

<br/>

#### Nether Crimson Temple

<br/>

Dispensers: 5 to 14 Arrows of Harming II

<br/>

Has one hidden Zoglin spawner

<br/>

#### Nether Warped Temple

<br/>

Dispensers: 5 to 14 Arrows of Harming II

<br/>

Has one hidden Enderman spawner and 1 Strider spawner

<br/>

#### Nether Soul Temple

<br/>

Dispensers: A mix of Arrows of Slowness and Fire Charges

<br/>

Has two hidden Skeleton spawners
</details>

<br/>

### Pyramids
<details>

#### Nether Pyramid

<br/>

Has two hidden Zombified Piglin spawners

<br/>

#### End Pyramid

<br/>

Has great loot but also has hidden Endermite spawners. Also has custom banners!

<br/>

#### Badlands Pyramid

<br/>

Has two hidden Husk spawners

<br/>

#### Icy Pyramid

<br/>

#### Snowy Pyramid

<br/>

Has two hidden Stray spawners

<br/>

#### Flower Forest Pyramid

<br/>

Has lots of bigger explosion radius Creepers

<br/>

#### Ocean Pyramid

<br/>

Has 4 Drowned with Tridents. They have a small chance of dropping Trident or their Turtle Helmet

<br/>

#### Jungle Pyramid

<br/>

Has a high amount of Silverfish infested blocks

<br/>

#### Giant Tree Taiga Pyramid

<br/>

#### Mushroom Pyramid

<br/>

Has no traps and much greater quantity of loot!

</details>

<br/>

### Shipwrecks
<details>

#### End Shipwreck

<br/>

You may find maps and if you are EXTREMELY lucky, maybe an Elytra in their chests! They can also have unique Firework Stars and rarely, Dragon's Breath too!

<br/>

#### Nether Bricks Shipwreck

<br/>

(Can naturally spawn Wither Skeletons over time)

<br/>

You may find maps and if you are EXTREMELY lucky, maybe an Elytra in their chests! Also, they got unique banners!

<br/>

#### Crimson Shipwreck

<br/>

(Can naturally spawn Wither Skeletons over time)

<br/>

You may find maps and unique Firework Stars in their chests! Also, they got unique banners!

<br/>

#### Warped Shipwreck

<br/>

(Can naturally spawn Wither Skeletons over time)

<br/>

You may find maps and unique Firework Stars in their chests! Also, they got unique banners!
</details>

<br/>


### Strongholds
<details>

#### Nether Stronghold Chest rates and Blaze Spawner locations

<br/>

(Can naturally spawn Blaze, Zombified Piglins, Wither Skeletons, Skeletons, and Magma Cubes over time)

<br/>

Storage rooms has 4 chests instead of 1 due to them not having much loot naturally.

<br/>

The Silverfish spawner at the Portal Room is now a Blaze spawner.

<br/>

Extra spawners are added to storage room, the room with the pillar that holds 4 Torches, and fountain room.

<br/>

#### The mob rates are the following:

<br/>

50% : Blaze

30% : Zoglin

20% : Zombiefied Piglin
</details>

<br/>


### Witch Huts

<details>
Will spawn Witches and Cats over time like vanilla Witch Huts.

<br/>

#### Variants:

<br/>

- Oak Witch Hut

- Taiga Witch Hut

- Birch Witch Hut

- Dark Forest Witch Hut

- Giant Tree Taiga Witch Hut
</details>

<br/>

### Grassy Igloo and Stone Igloo loot

<details>
The basement's chest and Brewing Stand uses the normal Igloo's.

<br/>

Grassy Igloo has a Bed, Crafting Table, Furnace, and a Lantern.

<br/>

Stone Igloo has a Blast Furnace, Campfire, 1 Iron Ore, a Lantern, a Bed, and a Stone Pickaxe on Crafting Table.

</details>
<br/>

### Wells

<details>

#### Badlands Well

1% chance of having a Bell

Each of the 5 blocks inside has a 15% chance of being a Gold Ore

<br/>

#### Forest Well

1% chance of having a Bell

Each of the 5 blocks inside has a 30% chance of being an Iron Ore

<br/>

#### Mossy Stone Well

1% chance of having a Bell

Each of the 5 blocks inside has a 12% chance of being an Emerald Ore

<br/>

#### Snow Well

1% chance of having a Bell

Each of the 5 blocks inside has a 30% chance of being a Lapis Ore

<br/>

#### Nether Well

1% chance of having a Bell

Each of the 5 blocks inside has a 8% chance of being a Chiseled Quartz block and a 50% chance of being a Quartz Ore

<br/>

#### Mushroom Well

1% chance of having a Bell

Each of the 5 blocks inside has a 30% chance of being a Redstone Ore or Iron Ore
</details>
</details>
<br/>
<br/>

______________________________________________________________________________

# HOW CAN I CONFIGURE THIS MOD?

<br/>
<br/>

You can configure this mod with configs and datapacks! See the "PRE-MADE DATAPACKS" section further down for already made datapacks that do a bunch of stuff such as turning off advancements!

<br/>
<br/>

You can change the configs by using Mod Menu mod with this mod. Just go into my mod in the list and click the gear. Just be sure to restart you game when you're finished changing configs! If you want to find this mod's config file directly, go into the config folder that is above the mods folder and there will be 1 config file for this mod (but without comments). Then exit/restart Minecraft for all changes to take effect. Do note that the config will affect all worlds so keep that in mind!

<br/>
<br/>

### Config details:

<details>
Right now, there are lots of config options. The big one that you may be interested in is the spawnrate config for every structure and yes, you can even fully turn off the structures too. The config comments will help walk you through on how to adjust the rates. Furthermore, you can even blacklist dimensions or biomes as well! For some structures, you can change the maximum and minimum y value that they can start their generation at and far more! Check out the config for everything you can do!

<br/>
<br/>

Furthermore, you can make datapacks to modify or change a huge amount of stuff in my mod including modifying how structures look! Here's a flat world of all the pieces of my nbt-based structures. Use this to make editing and overriding structure pieces easier with datapacks:
[Repurposed Structures - pieces world (click here)](https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/releases/tag/0.0.10)

<br/>
<br/>

All the tags this mod uses are under data.repurposed_structures.tags.blocks and you can specify more than one block. The json tag files you can override can be found at:
[Block Tags for Repurposed Structures (click here)](https://github.com/TelepathicGrunt/RepurposedStructures/tree/master/src/main/resources/data/repurposed_structures/tags/blocks)

<br/>
<br/>

If you want to change the loot tables that the structures in this mod uses, you can override my loot tables with a data pack! Here's a datapack with the default loot tables: [
Repurposed_Structures-Loottables (click here)](https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/releases/tag/0.0.11)

<br/>
<br/>

The mob spawner for all none-nbt based structures can be changed with datapacks as well! You can download the spawner datapack here and change the entries inside the json files to add, remove, or change mob chances in spawners!:
[Repurposed_Structures-Mob_Spawners (click here)](https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/releases/tag/0.0.5)

<br/>
<br/>

And some structure pieces such as Wells, Villages, Outposts, Shipwrecks, Pyramids, and Igloos can also be overridden with datapacks. Furthermore, the template pools that holds structure pieces for Villages and other structures can also be overridden with datapacks to add your own pieces to the villages or other structures! You can find what pieces or pools can be replaced and their filenames here:
[Overridable structure nbt files (click here)](https://github.com/TelepathicGrunt/RepurposedStructures/tree/master/src/main/resources/data/repurposed_structures/structures)
[Overridable structure pool files (click here)](https://github.com/TelepathicGrunt/RepurposedStructures/tree/master/src/main/resources/data/repurposed_structures/worldgen)

<br/>
<br/>

For the expert datapackers, there's actually a hidden neat way to add new pieces to any Jigsaw Structure without overriding their pool file! (Villages, outposts, all of RS's structures minus mansions, etc) For example, instead of overriding `data\repurposed_structures\worldgen\template_pool\village\birch\houses.json` to add new houses to my Birch Village, replace `worldgen\template` in the datapack with `pool_additions` and have the template pool file only have the entries you want merged into the main pool file. Repurposed Structures will detect these files from ALL datapacks and merge the pool_additions pool entries into the actual template pool file that the path points to. Yes, Repurposed Structures will read all pool_additions files from all datapacks that even have the same path so that the datapacks do not override each other's pool_additions! Neat stuff! And it works with other pools too! Just make sure the piece you are adding has a Jigsaw Block with the right name so the actual structure can connect and spawn the piece.

To help you with this cool pool_additions feature, this 18 minute tutorial should help walk you through creating a building, saving it, and adding it to RS's structures with a datapack! https://youtu.be/kzRQrQqlYjw
</details>

<br/>
<br/>

Let me know if you think of a new config option that you would need and I will try and add it! :)

______________________________________________________________________________

# PRE-MADE DATAPACKS


Since the overwhelming majority of my structures uses nbt files, template pools, and/or processors, you can override all of this with datapacks! That means you can customize the blocks in those structures with other mod's blocks or add pieces of other mod's villages into RS's villages! If you decide to use the datapack below, download it, go into your world's save folder, and put the zipped folder into the datapack folder. Now when you load the game, the datapack should take effect! (Also, you can share mod-compat datapacks you made to me and I'll add it below after I check it out and like it!)

<br/>
<br/>

### Datapacks made:

<br/>

<details>

### [Charm Compatibility pack!](https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/releases/tag/2.0.0)

<br/>

Want lumberjacks and beekeeper houses in Repurposed Structures's villages in 1.17+ MC? No problem! Slap on this datapack and problem solved!

<br/>

Charm mod: [https://www.curseforge.com/minecraft/mc-mods/charm]

Download link: [Repurposed_Structures-Charm](https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/releases/tag/2.0.0)

<br/>
<br/>
<br/>

###  [Landmark Compatibility pack!](https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/releases/tag/0.0.4)
<br/>

Add this datapack to the world's datapack folder to add compatibility between Repurposed Structures and Landmark mod!

<br/>

Landmark mod: [https://www.curseforge.com/minecraft/mc-mods/landmark](https://www.curseforge.com/minecraft/mc-mods/landmark)

Download link: [Repurposed_Structures-Landmark](https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/releases/tag/0.0.4)

<br/>
<br/>
<br/>

### [Hidden Advancement pack!](https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/releases/tag/0.0.3)

<br/>

Add this datapack to the world's datapack folder to make Repurposed Structures's advancements no longer pop up on screen! The advancement will then only be able to be viewed in the advancements menu and nowhere else.

<br/>

Download link: [Repurposed_Structures-Hidden_Advancements](https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/releases/tag/0.0.3)

<br/>
<br/>
<br/>

### [Disabled Advancement pack!](https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/releases/tag/0.0.6)

<br/>

Add this datapack to the world's datapack folder to make Repurposed Structures's advancements never trigger ever! No popup. Nothing in Advancement screen. Use this if you want no more advancements at all.

<br/>

Download link: [Repurposed_Structures-Disabled_Advancements](https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/releases/tag/0.0.6)

<br/>
<br/>
<br/>

### [Vanilla Loottable pack!](https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/releases/tag/0.0.12)

<br/>

Add this datapack (the .zip file) to the world's datapack folder to make all RS structure's loot tables to use the same loot table as the vanilla structures that they are based upon. For example, Nether Pyramids now uses vanilla Desert Temple loot table. This can be helpful if you do not want RS structures to have customized loot tables

<br/>

Download link: [Repurposed_Structures-Vanilla_Loottables](https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/releases/tag/0.0.12)

<br/>
<br/>
<br/>

### [Translation Resourcepack!](https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/releases/tag/0.0.8)

<br/>

For clients without this mod on and is connecting to a server that does have Repurposed Structures on, the clients can put on this resourcepack (the .zip file). This allows the clients to switch to other languages that comes with the resourcepack such as Spanish or Simplified Chinese. More info in the link.

<br/>

Download link: [Repurposed_Structures-Translation_Resourcepack](https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/releases/tag/0.0.8)
</details>

______________________________________________________________________________

### Special thanks to:

/r/l-ll-ll-l_IsDisLoss from Reddit for allowing me to use his Nether Pyramid and Nether Crimson Outpost design!

<br/>

cannon_foddr for allowing me to use his Nether Brick Outpost and End/Nether Bricks Shipwreck design!

<br/>

miguelforge for allowing me to use his Crimson and Warped Shipwreck, End Ruined Portal, Nether Ruins, and other designs!
<br/>
<br/>
<br/>

*If you find an issue, glitch, or have a suggestion about my mod, let me know! But if you don't have a GitHub account to report in the Issue tab above, just comment what the problem is below and I'll try and get back to you ASAP! Or you can contact me on Discord at the link below :)
<br/>
<br/>

### Discord Link to #telepathicgrunt-mods channel for my mods! : https://discord.gg/SM7WBT6FGu
<a class="anchor-3Z-8Bb anchorUnderlineOnHover-2ESHQB" style="font-size: 24px;" tabindex="0" title="https://discord.gg/SM7WBT6FGu" role="button" href="https://discord.gg/SM7WBT6FGu" target="_blank" rel="noopener noreferrer"><img src="https://www.freepnglogos.com/uploads/discord-logo-png/concours-discord-cartes-voeux-fortnite-france-6.png" alt="discord-logo-png-free-transparent-png-logos-discord-png-logo-300_300 (PNG)  | BeeIMG" width="112" height="112" /></a>

&nbsp;
