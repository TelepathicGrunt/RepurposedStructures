package com.telepathicgrunt.repurposedstructures.data;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;

public final class LootTableUtils {
    private LootTableUtils() {}

    public static CompoundNBT getTransBannerNBT() {
        CompoundNBT bannerNBT = new CompoundNBT();
        try {
            bannerNBT.put("BlockEntityTag", JsonToNBT.parseTag("{Patterns:[{Pattern:cs,Color:0},{Pattern:cs,Color:0},{Pattern:cs,Color:0},{Pattern:bo,Color:3},{Pattern:bo,Color:3},{Pattern:bo,Color:3}]}"));
        }
        catch (CommandSyntaxException e) {
            e.printStackTrace();
        }
        return bannerNBT;
    }

    public static CompoundNBT getBannerNBT(String tag) {
        CompoundNBT bannerNBT = new CompoundNBT();
        try {
            bannerNBT.put("BlockEntityTag", JsonToNBT.parseTag(tag));
        }
        catch (CommandSyntaxException e) {
            e.printStackTrace();
        }
        return bannerNBT;
    }

    public static CompoundNBT getPotionNbt(String tag) {
        CompoundNBT potionNBT = new CompoundNBT();
        potionNBT.putString("Potion", tag);
        return potionNBT;
    }
}
