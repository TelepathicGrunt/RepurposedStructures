package com.telepathicgrunt.repurposedstructures.utils;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

// Created by Won-Ton. Credit to him
public class CodecCache<T> {
    public static final int DEFAULT_CACHE_SIZE = 4096;

    private final Codec<T> codec;
    private final Map<T, Optional<JsonElement>> cache;
    private final AtomicInteger requestCount = new AtomicInteger(0);

    private CodecCache(Codec<T> codec, Map<T, Optional<JsonElement>> backing) {
        this.codec = codec;
        this.cache = backing;
    }

    public void clear() {
        cache.clear();
        requestCount.set(0);
    }

    public Optional<JsonElement> get(T value) {
        requestCount.incrementAndGet();
        return cache.computeIfAbsent(value, this::encode);
    }

    public String getStats() {
        int size = cache.size();
        int requests = requestCount.get();
        return String.format("Size: %s, Requests: %s, Hits: %s", size, requests, requests - size);
    }

    private Optional<JsonElement> encode(T value) {
        return codec.encodeStart(JsonOps.INSTANCE, value).result();
    }

    public static <T> CodecCache<T> of(Codec<T> codec) {
        return new CodecCache<>(codec, new IdentityHashMap<>(DEFAULT_CACHE_SIZE));
    }

    public static <T> CodecCache<T> concurrent(Codec<T> codec) {
        return new CodecCache<>(codec, new ConcurrentHashMap<>(DEFAULT_CACHE_SIZE));
    }
}
