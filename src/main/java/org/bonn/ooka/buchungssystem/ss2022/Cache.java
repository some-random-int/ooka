package org.bonn.ooka.buchungssystem.ss2022;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache<T> implements DBCache<T> {

    private Map<String, List<T>> cache;

    public Cache() {
        this.cache = new HashMap<>();
    }

    @Override
    public void cacheResult(String key, List<T> value) {
        this.cache.put(key, value);
    }

    @Override
    public boolean isCached(String key) {
        return this.cache.containsKey(key);
    }

    @Override
    public List<T> getValue(String key) {
        return this.cache.get(key);
    }
}
