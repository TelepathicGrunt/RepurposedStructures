package com.telepathicgrunt.repurposedstructures.mixin;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.data.DataGenerators;
import net.minecraftforge.fml.DatagenModLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class RepurposedStructuresMixinPlugin implements IMixinConfigPlugin {

    @Override
    public void onLoad(String mixinPackage) {}

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if(!DataGenerators.datagenLootTableModeOn) {
            if(mixinClassName.equals("com.telepathicgrunt.repurposedstructures.mixin.datagen.LootPoolMixin")) return false;
            if(mixinClassName.equals("com.telepathicgrunt.repurposedstructures.mixin.datagen.LootPoolAccessor")) return false;
            if(mixinClassName.equals("com.telepathicgrunt.repurposedstructures.mixin.datagen.ItemLootEntryMixin")) return false;
            if(mixinClassName.equals("com.telepathicgrunt.repurposedstructures.mixin.datagen.ItemLootEntryAccessor")) return false;
            if(mixinClassName.equals("com.telepathicgrunt.repurposedstructures.mixin.datagen.StandaloneLootEntryMixin")) return false;
            if(mixinClassName.equals("com.telepathicgrunt.repurposedstructures.mixin.datagen.StandaloneLootEntryAccessor")) return false;
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
