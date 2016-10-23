package org.moita.keep.collection.internal;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.moita.keep.keeper.IKeeper;

public class KeepMapProxy<K, V> implements Map<K, V> {

    private transient IKeeper<Map<K, V>> keeper = null;

    private Map<K, V> map = null;

    public KeepMapProxy(Map<K, V> map) {
        this.map = map;
    }

    /**
     * Allows to set a different method to store and recover data from disk/database/network ...
     * 
     * @param keeper
     *            instance of IPersistence that contains the implementation to persist and restore data to and from PArrayList.
     */
    public void setKeeper(IKeeper<Map<K, V>> keeper) {
        this.keeper = keeper;
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return this.map.get(key);
    }

    @Override
    public V put(K key, V value) {
        V m = this.map.put(key, value);
        this.keeper.persist(this.map);
        return m;
    }

    @Override
    public V remove(Object key) {
        V k = this.map.remove(key);
        this.keeper.persist(this.map);
        return k;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        this.map.putAll(m);
        this.keeper.persist(this.map);
    }

    @Override
    public void clear() {
        this.map.clear();
    }

    @Override
    public Set<K> keySet() {
        return this.map.keySet();
    }

    @Override
    public Collection<V> values() {
        return this.map.values();
    }

    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet() {
        return this.map.entrySet();
    }
}
