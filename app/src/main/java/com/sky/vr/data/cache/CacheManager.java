package com.sky.vr.data.cache;

/**
 * Created by sky on 16-10-27.
 */

public interface CacheManager {

    <T> T get(String key, Class<T> tClass);

    <T> boolean put(String key, T value);

    boolean remove(String key);

    void close();

    String buildKey(String value);
}
