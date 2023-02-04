package lab9;

import java.sql.Array;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    private void resize(int size){
        MyHashMap<K,V> temp = new MyHashMap<>();
        temp.buckets = new ArrayMap[size * 2];
        temp.clear();
        for(int i = 0;i < buckets.length;i++){
            for(K key : buckets[i]){
                temp.buckets[i].put(key,buckets[i].get(key));
            }
        }

        this.buckets = temp.buckets;
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
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

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if(key == null) throw new IllegalArgumentException("argument to get() is null.");
        int pos = hash(key);
        return buckets[pos].get(key);
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
//        throw new UnsupportedOperationException();
        if(key == null) throw new IllegalArgumentException("argument key to put() is null.");

        if(value == null) throw new IllegalArgumentException("argument value to put() is null.");

        if(loadFactor() >= MAX_LF){
            resize(buckets.length);
        }

        int pos = hash(key);
        if(!buckets[pos].containsKey(key)){
            this.size++;
            buckets[pos].put(key,value);
        }
        buckets[pos].put(key,value);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return this.size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> S = new HashSet<>();

        for(int i = 0;i < buckets.length;i++){
            for(K key : buckets[i]){
                S.add(key);
            }
        }
        return S;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
