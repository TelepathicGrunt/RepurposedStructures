package com.telepathicgrunt.repurposedstructures.utils;

import com.google.common.base.Suppliers;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Lifecycle;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.WritableRegistry;
import net.minecraft.resources.RegistryReadOps;
import net.minecraft.resources.RegistryResourceAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * This is a modified RegistryOps that takes a DynamicRegistryManager instead of a DynamicRegistryManager.Impl
 * and does exactly what I need in decodeOrId without the extra stuff that could mess with the DynamicRegistryManager itself.
 * We also have to extend RegistryOps because RegistryLookupCodec does an instanceof check for RegistryOps before deciding if it should call decodeOrId.
 */
public class SafeDecodingRegistryOps<T> extends RegistryReadOps<T> {

    private final RegistryAccess dynamicRegistryManager;
    private final Map<ResourceKey<? extends Registry<?>>, SafeDecodingRegistryOps.ValueHolder<?>> valueHolders;

    public SafeDecodingRegistryOps(DynamicOps<T> delegate, ResourceManager resourceManager, RegistryAccess dynamicRegistryManager) {
        super(delegate, RegistryResourceAccess.forResourceManager(resourceManager), dynamicRegistryManager, Maps.newIdentityHashMap());
        this.dynamicRegistryManager = dynamicRegistryManager;
        this.valueHolders = Maps.newIdentityHashMap();
    }

    /**
     * Encode an id for a registry element than a full object if possible.
     *
     * <p>This method is called by casting an arbitrary dynamic ops to a registry reading ops.</p>
     */
    @Override
    protected <E> DataResult<Pair<Supplier<E>, T>> decodeElement(T object, ResourceKey<? extends Registry<E>> registryKey, Codec<E> codec, boolean allowInlineDefinitions) {
        Optional<WritableRegistry<E>> optional = this.dynamicRegistryManager.ownedRegistry(registryKey);
        if (optional.isEmpty()) {
            return DataResult.error("(Repurposed Structures SafeDecodingRegistryOps) Unknown registry: " + registryKey);
        }
        else {
            DataResult<Pair<ResourceLocation, T>> dataResult = ResourceLocation.CODEC.decode(this.delegate, object);
            if (dataResult.result().isEmpty()) {
                return !allowInlineDefinitions ?
                        DataResult.error("(Repurposed Structures SafeDecodingRegistryOps) Inline definitions not allowed here") :
                        codec.decode(this, object).map((pair) -> pair.mapFirst((object2) -> () -> object2));
            }
            else {
                WritableRegistry<E> mutableRegistry = optional.get();
                Pair<ResourceLocation, T> pair = dataResult.result().get();
                ResourceLocation identifier = pair.getFirst();

                try {
                    return this.readAndRegisterElement(registryKey, mutableRegistry, codec, identifier)
                            .map((supplier) -> Pair.of(supplier, pair.getSecond()));
                }
                catch (Exception e) {
                    RepurposedStructures.LOGGER.error(
                            """

                            Repurposed Structures: Crash is about to occur because an entry in a datapack does not exist in a registry or failed to resolve an entry.
                            Entry failed to be resolved: {}
                            Registry being used: {}""".indent(1),
                            registryKey, object);
                    throw e;
                }
            }
        }
    }

    /**
     * Reads a supplier for a registry element.
     * <p>This logic is used by both {@code decodeOrId} and {@code loadToRegistry}.</p>
     */
    private <E> DataResult<Supplier<E>> readAndRegisterElement(ResourceKey<? extends Registry<E>> registryRegistryKey, WritableRegistry<E> mutableRegistry, Codec<E> codec, ResourceLocation elementId) {
        ResourceKey<E> elementRegistryKey = ResourceKey.create(registryRegistryKey, elementId);
        SafeDecodingRegistryOps.ValueHolder<E> valueHolder = this.getValueHolder(registryRegistryKey);
        DataResult<Supplier<E>> dataResult = valueHolder.values.get(elementRegistryKey);
        if (dataResult != null) {
            return dataResult;
        }
        else {
            Supplier<E> supplier = Suppliers.memoize(() -> {
                E object = mutableRegistry.get(elementRegistryKey);
                if (object == null) {
                    throw new RuntimeException("(Repurposed Structures SafeDecodingRegistryOps) Error during recursive registry parsing, element resolved too early: " + elementRegistryKey);
                }
                else {
                    return object;
                }
            });
            valueHolder.values.put(elementRegistryKey, DataResult.success(supplier));

            DataResult<Supplier<E>> dataResult4 = null;
            if (mutableRegistry.get(elementRegistryKey) != null) {
                dataResult4 = DataResult.success(() -> mutableRegistry.get(elementRegistryKey), Lifecycle.stable());
            }

            valueHolder.values.put(elementRegistryKey, dataResult4);
            return dataResult4;
        }
    }

    private <E> SafeDecodingRegistryOps.ValueHolder<E> getValueHolder(ResourceKey<? extends Registry<E>> registryRef) {
        return (ValueHolder<E>) this.valueHolders.computeIfAbsent(registryRef, (registryKey) -> new ValueHolder());
    }

    static final class ValueHolder<E> {
        private final Map<ResourceKey<E>, DataResult<Supplier<E>>> values;

        private ValueHolder() {
            this.values = Maps.newIdentityHashMap();
        }
    }
}
