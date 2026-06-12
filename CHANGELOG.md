### **(V.7.5.21 Changes) (1.21.1 Minecraft)**

#### Misc:
Fixed an incredibly rare concurrency modification exception crash when Villagers/Wandering Traders have explorer maps 
 locating an RS structure while something else is also searching for structures at same time.


### **(V.7.5.20 Changes) (1.21.1 Minecraft) (Config Datapack Updated)**

##### Configs:
Added item tag `repurposed_structures:blacklisted_from_modded_loot_importing` for packmakers or other mods to prevent
 RS from importing certain modded items from vanilla loot tables while still importing other modded items.


### **(V.7.5.19 Changes) (1.21.1 Minecraft)**

##### Mod Compat:
(Fabric): Adjusted loot table manipulation mixin so it fixes the incompat with Artifact loot not showing in Repurposed Structures loot tables.