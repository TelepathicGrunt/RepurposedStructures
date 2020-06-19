package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class RSWellsConfig
{

	public static class RSWellsConfigValues
	{
	    	public ConfigValueListener<Boolean> addWellsToModdedBiomes;
		public ConfigValueListener<Boolean> canHaveBells;
		public ConfigValueListener<Integer> badlandsWellSpawnrate;
		public ConfigValueListener<Integer> netherWellSpawnrate;
		public ConfigValueListener<Integer> snowWellSpawnrate;
		public ConfigValueListener<Integer> mossyStoneWellSpawnrate;
		public ConfigValueListener<Integer> forestWellSpawnrate;

		public RSWellsConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{

        	    	builder.push("Small Wells");
                		
                		addWellsToModdedBiomes = subscriber.subscribe(builder
                				.comment("\r\n Add the custom wells to modded biomes of the same categories/type.")
                			.translation("repurposedstructures.config.feature.smallwells.addwellstomoddedbiomes")
                			.define("addWellsToModdedBiomes", false));
                
                		canHaveBells = subscriber.subscribe(builder
                				.comment("\r\n Determines if Wells can have a chance of spawning a Bell.")
                			.translation("repurposedstructures.config.feature.smallwells.canhavebells")
                			.define("canHaveBells", true));

                		
				builder.push("Spawnrate");
				
                        		badlandsWellSpawnrate  = subscriber.subscribe(builder
                        				.comment("\r\n Adds Badlands themed wells to Badlands biomes."
                        					+ "\r\n This effects how often wells will attempt to spawn per chunk." 
                        					+ "\r\n The chance of a well generating at a chunk is 1/spawnrate."
                        					+ "\r\n 1 for wells spawning in every chunk and 10000 for no wells.")
                        			.translation("repurposedstructures.config.feature.smallwells.badlandswellspawnrate")
                        			.defineInRange("badlandsWellSpawnrate", 250, 1, 10000));
                        		
                        		netherWellSpawnrate = subscriber.subscribe(builder
                        				.comment("\r\n Adds Nether themed wells to Nether biomes."
                        					+ "\r\n This effects how often wells will attempt to spawn per chunk." 
                        					+ "\r\n The chance of a well generating at a chunk is 1/spawnrate."
                        					+ "\r\n 1 for wells spawning in every chunk and 10000 for no wells.")
                        			.translation("repurposedstructures.config.feature.smallwells.netherwellspawnrate")
                        			.defineInRange("netherWellSpawnrate", 200, 1, 10000));
                        		
                        		snowWellSpawnrate = subscriber.subscribe(builder
                        				.comment("\r\n Adds Snow themed wells to snowy and icy biomes."
                        					+ "\r\n This effects how often wells will attempt to spawn per chunk." 
                        					+ "\r\n The chance of a well generating at a chunk is 1/spawnrate."
                        					+ "\r\n 1 for wells spawning in every chunk and 10000 for no wells.")
                        			.translation("repurposedstructures.config.feature.smallwells.snowwellspawnrate")
                        			.defineInRange("snowWellSpawnrate", 350, 1, 10000));
                        		
                        		mossyStoneWellSpawnrate = subscriber.subscribe(builder
                        				.comment("\r\n Adds mossy stone themed wells to Jungles, Dark Oak, and Swamp biomes."
                        					+ "\r\n This effects how often wells will attempt to spawn per chunk." 
                        					+ "\r\n The chance of a well generating at a chunk is 1/spawnrate."
                        					+ "\r\n 1 for wells spawning in every chunk and 10000 for no wells.")
                        			.translation("repurposedstructures.config.feature.smallwells.mossystonewellspawnrate")
                        			.defineInRange("mossyStoneWellSpawnrate", 350, 1, 10000));
                        
                        		forestWellSpawnrate = subscriber.subscribe(builder
                        				.comment("\r\n Adds a wood themed wells to Forest and Birch Forest biomes."
                        					+ "\r\n This effects how often wells will attempt to spawn per chunk." 
                        					+ "\r\n The chance of a well generating at a chunk is 1/spawnrate."
                        					+ "\r\n 1 for wells spawning in every chunk and 10000 for no wells.")
                        			.translation("repurposedstructures.config.feature.smallwells.forestwellspawnrate")
                        			.defineInRange("forestWellSpawnrate", 350, 1, 10000));

                                builder.pop();
                                
                	builder.pop();
		}
	}
}
