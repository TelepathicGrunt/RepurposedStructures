package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

import net.minecraft.world.gen.feature.IFeatureConfig;


public class RSMineshaftConfig implements IFeatureConfig
{
	public final RSMineshaftStructure.Type type;


	public RSMineshaftConfig(RSMineshaftStructure.Type type)
	{
		this.type = type;
	}


	@Override
	public <T> Dynamic<T> serialize(DynamicOps<T> ops)
	{
		return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(ops.createString("type"), ops.createInt(this.type.ordinal()))));
	}


	public static <T> RSMineshaftConfig deserialize(Dynamic<T> p_214679_0_)
	{
		RSMineshaftStructure.Type s = RSMineshaftStructure.Type.byId(p_214679_0_.get("type").asInt(0));
		return new RSMineshaftConfig(s);
	}
}