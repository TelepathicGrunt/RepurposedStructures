package com.telepathicgrunt.repurposedstructures.configs.neoforge;

import com.telepathicgrunt.repurposedstructures.configs.RSMainConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSMainModdedLootConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class RSConfig {
	public static final ModConfigSpec GENERAL_SPEC;
	public static ModConfigSpec.BooleanValue disableLogspamHiding;

	static {
		ModConfigSpec.Builder configBuilder = new ModConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ModConfigSpec.Builder builder) {
		disableLogspamHiding = builder
				.comment("Allows vanilla bugs to resume spamming the logs")
				.translation("repurposedstructures.disablelogspamhiding")
				.define("disableLogspamHiding", false);
	}

	public static void copyToCommon() {
		RSMainConfig.disableLogspamHiding = disableLogspamHiding.get();
	}
}
