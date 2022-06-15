package lab9;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * A hash table-backed Map implementation. Provides amortized constant time
 * access to elements via get(), remove(), and put() in the best case.
 *
 * @author Your name here
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;
    private HashSet<K> keySet;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
        keySet = new HashSet<>();
    }

    public MyHashMap(int newSize) {
        buckets = new ArrayMap[newSize];
        this.clear();
        keySet = new HashSet<>();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /**
     * Computes the hash function of the given key. Consists of
     * computing the hashcode, followed by modding by the number of buckets.
     * To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        ArrayMap<K, V> bucket = buckets[hash(key)];
        return bucket.get(key);
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        resize();
        ArrayMap<K, V> bucket = buckets[hash(key)];
        int size1 = bucket.size();
        bucket.put(key, value);
        int size2 = bucket.size();
        size += size2 - size1;
        keySet.add(key);
    }

    private void resize() {
        if (loadFactor() > MAX_LF) {
            MyHashMap<K, V> newHashMap = new MyHashMap<>(size * 2);
            for (ArrayMap<K, V> bucket : buckets) {
                for (K key : bucket) {
                    newHashMap.put(key, bucket.get(key));
                }
            }
            this.buckets = newHashMap.buckets;
            this.size = newHashMap.size;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keySet;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        ArrayMap<K, V> bucket = buckets[hash(key)];
        V v = bucket.get(key);
        int size1 = bucket.size();
        bucket.remove(key);
        int size2 = bucket.size();
        size += size1 - size2;
        return v;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        ArrayMap<K, V> bucket = buckets[hash(key)];
        V v = bucket.get(key);
        int size1 = bucket.size();
        bucket.remove(key, value);
        int size2 = bucket.size();
        size += size1 - size2;
        return v;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet.iterator();
    }

    public static void main(String[] args) {
        MyHashMap<Integer, String> c = new MyHashMap<Integer, String>();
        for (int i = 0; i < 100; i++) {
            c.put(i, "a");
        }
    }
}
