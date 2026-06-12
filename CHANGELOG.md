### **(V.7.1.24 Changes) (1.20.1 Minecraft)**

#### Misc:
Fixed an incredibly rare concurrency modification exception crash when Villagers/Wandering Traders have explorer maps 
 locating an RS structure while something else is also searching for structures at same time.


### **(V.7.1.23 Changes) (1.20.1 Minecraft) (Config Datapack Updated)**

##### Configs:
Added item tag `repurposed_structures:blacklisted_from_modded_loot_importing` for packmakers or other mods to prevent
 RS from importing certain modded items from vanilla loot tables while still importing other modded items.


### **(V.7.1.22 Changes) (1.20.1 Minecraft)**

##### Misc:
Fixed rare potential race condition issue with ServiceLoaders loading.


### **(V.7.1.21 Changes) (1.20.1 Minecraft)**

##### Misc:
Removed debug mixins sorry


### **(V.7.1.20 Changes) (1.20.1 Minecraft)**

##### Misc:
Adjusted Skeletons spawning in RS structures to set position before calling finalizeSpawn.
May improve interactions with certain mods.