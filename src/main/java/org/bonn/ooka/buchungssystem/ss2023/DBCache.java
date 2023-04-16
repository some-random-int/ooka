package org.bonn.ooka.buchungssystem.ss2023;

import java.util.List;

public interface DBCache<T> {
    void cacheResult(String key, List<T> value);
    boolean isCached(String key);
    List<T> getValue(String key);
}
