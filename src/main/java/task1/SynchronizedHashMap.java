package task1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SynchronizedHashMap<K, V> extends ConcurrentHashMap<K, V> implements Map<K, V> {

    private final Map<K, V> provider;
    private final Object monitor;

    public SynchronizedHashMap(Map<K, V> provider) {
        this.provider = provider;
        monitor = this;
    }

    @Override
    public V put(K key, V value) {
        synchronized (monitor) {
            return provider.put(key, value);
        }
    }

    @Override
    public V get(Object key) {
        synchronized (monitor) {
            return provider.get(key);
        }
    }

    @Override
    public int size() {
        synchronized (monitor) {
            return provider.size();
        }
    }
}
