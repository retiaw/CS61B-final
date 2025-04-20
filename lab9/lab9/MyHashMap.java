package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Your name here
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size;
    private int capacity;

    private double loadFactor() {
        return 1.0 * size / capacity;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        capacity = DEFAULT_SIZE;
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.capacity; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = capacity;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        int hashCode = hash(key);
        V ret = null;
        for (K element : buckets[hashCode]) {
            if (element.equals(key)) {
                ret = buckets[hashCode].get(key);
            }
        }
        return ret;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (loadFactor() > MAX_LF) {
            // double capacity:
            int oldCapacity = capacity;
            capacity *= 2;
            // reassign buckets:
            ArrayMap<K, V>[] newBuckets = new ArrayMap[capacity];
            for (int i = 0; i < capacity; i++) {
                newBuckets[i] = new ArrayMap<>();
            }

            for (int i = 0; i < oldCapacity; i++) {
                for (K element : buckets[i]) {
                    int hashCode = hash(element);
                    newBuckets[hashCode].put(element, buckets[i].get(element));
                }
            }
            buckets = newBuckets;
        }

        if (!containsKey(key)) {
            size++;
        }
        int hashCode = hash(key);
        buckets[hashCode].put(key, value);
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
        Set<K> ret = new HashSet<>();
        for (int i = 0; i < capacity; i++) {
            for (K element : buckets[i]) {
                ret.add(element);
            }
        }
        return ret;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        V ret = get(key);
        if (ret != null) {
            int hashCode = hash(key);
            for (K element : buckets[hashCode]) {
                if (element.equals(key)) {
                    buckets[hashCode].remove(key);
                    size--;
                }
            }
        }
        return ret;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        V ret = get(key);
        if (ret == value) {
            int hashCode = hash(key);
            for (K element : buckets[hashCode]) {
                if (element.equals(key)) {
                    buckets[hashCode].remove(key);
                    size--;
                }
            }
            return ret;
        } else {
            return null;
        }
    }

    private class HashMapIterator implements Iterator<K> {

        private Set<K> set = keySet();

        @Override
        public boolean hasNext() {
            return !set.isEmpty();
        }

        @Override
        public K next() {
            K ret = null;
            for (K key : set) {
                ret = key;
                set.remove(key);
                break;
            }
            return ret;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new HashMapIterator();
    }
}
