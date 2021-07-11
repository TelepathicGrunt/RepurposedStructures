package com.telepathicgrunt.repurposedstructures.utils;

import com.google.common.base.Suppliers;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Lifecycle;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.RegistryOps;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * This is a modified RegistryOps that takes a DynamicRegistryManager instead of a DynamicRegistryManager.Impl
 * and does exactly what I need in decodeOrId without the extra stuff that could mess with the DynamicRegistryManager itself.
 * We also have to extend RegistryOps because RegistryLookupCodec does an instanceof check for RegistryOps before deciding if it should call decodeOrId.
 */
public class SafeDecodingRegistryOps<T> extends RegistryOps<T> {

    private final DynamicRegistryManager dynamicRegistryManager;
    private final Map<RegistryKey<? extends Registry<?>>, SafeDecodingRegistryOps.ValueHolder<?>> valueHolders;

    public SafeDecodingRegistryOps(DynamicOps<T> delegate, DynamicRegistryManager dynamicRegistryManager) {
        super(delegate, null, null, Maps.newIdentityHashMap());
        this.dynamicRegistryManager = dynamicRegistryManager;
        this.valueHolders = Maps.newIdentityHashMap();
    }

    /**
     * Encode an id for a registry element than a full object if possible.
     *
     * <p>This method is called by casting an arbitrary dynamic ops to a registry reading ops.</p>
     */
    @Override
    protected <E> DataResult<Pair<Supplier<E>, T>> decodeOrId(T object, RegistryKey<? extends Registry<E>> registryKey, Codec<E> codec, boolean allowInlineDefinitions) {
        Optional<MutableRegistry<E>> optional = this.dynamicRegistryManager.getOptionalMutable(registryKey);
        if (!optional.isPresent()) {
            return DataResult.error("(Repurposed Structures SafeDecodingRegistryOps) Unknown registry: " + registryKey);
        } else {
            DataResult<Pair<Identifier, T>> dataResult = Identifier.CODEC.decode(this.delegate, object);
            if (!dataResult.result().isPresent()) {
                return !allowInlineDefinitions ?
                        DataResult.error("(Repurposed Structures SafeDecodingRegistryOps) Inline definitions not allowed here") :
                        codec.decode(this, object).map((pair) -> pair.mapFirst((object2) -> () -> object2));
            } else {
                MutableRegistry<E> mutableRegistry = optional.get();
                Pair<Identifier, T> pair = dataResult.result().get();
                Identifier identifier = pair.getFirst();
                return this.readSupplier(registryKey, mutableRegistry, codec, identifier)
                        .map((supplier) -> Pair.of(supplier, pair.getSecond()));
            }
        }
    }

    /**
     * Reads a supplier for a registry element.
     * <p>This logic is used by both {@code decodeOrId} and {@code loadToRegistry}.</p>
     */
    private <E> DataResult<Supplier<E>> readSupplier(RegistryKey<? extends Registry<E>> registryRegistryKey, MutableRegistry<E> mutableRegistry, Codec<E> codec, Identifier elementId) {
        RegistryKey<E> elementRegistryKey = RegistryKey.of(registryRegistryKey, elementId);
        SafeDecodingRegistryOps.ValueHolder<E> valueHolder = this.getValueHolder(registryRegistryKey);
        DataResult<Supplier<E>> dataResult = valueHolder.values.get(elementRegistryKey);
        if (dataResult != null) {
            return dataResult;
        } else {
            Supplier<E> supplier = Suppliers.memoize(() -> {
                E object = mutableRegistry.get(elementRegistryKey);
                if (object == null) {
                    throw new RuntimeException("(Repurposed Structures SafeDecodingRegistryOps) Error during recursive registry parsing, element resolved too early: " + elementRegistryKey);
                } else {
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

    private <E> SafeDecodingRegistryOps.ValueHolder<E> getValueHolder(RegistryKey<? extends Registry<E>> registryRef) {
        return (ValueHolder<E>) this.valueHolders.computeIfAbsent(registryRef, (registryKey) -> new ValueHolder());
    }

    static final class ValueHolder<E> {
        private final Map<RegistryKey<E>, DataResult<Supplier<E>>> values;

        private ValueHolder() {
            this.values = Maps.newIdentityHashMap();
        }
    }
}
