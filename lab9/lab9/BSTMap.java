package lab9;

import java.util.*;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if(p == null){
            return null;
        }
        int cmp = key.compareTo(p.key);
        if(cmp > 0){
            return getHelper(key,p.right);
        }else if(cmp < 0){
            return getHelper(key,p.left);
        }else{
            return p.value;
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key,root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if(p == null){
            size++;
            return new Node(key,value);
        }
        int cmp = key.compareTo(p.key);
        if(cmp > 0){
            p.right = putHelper(key,value,p.right);
        }else if(cmp < 0){
            p.left = putHelper(key,value,p.left);
        }else{
            p.value = value;
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key,value,root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return this.size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////
    private void keySetHelper(Set<K> s,Node p){
        if(p == null){
            return;
        }
        s.add(p.key);
        keySetHelper(s,p.left);
        keySetHelper(s,p.right);
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> s = new HashSet<>();
        keySetHelper(s,root);
        return s;
    }

    private Node min(Node node){
        if(node.left == null){
            return node;
        }
        return min(node.left);
    }

    public K min(){
        return min(root).key;
    }


    private Node deleteMin(Node node){
        if(node.left == null){
            return node.right;
        }
        node.left = deleteMin(node.left);
        size--;
        return node;
    }

    public void deleteMin(){
        this.root = deleteMin(root);
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */

    private Node removeHelper(Node node,K key){
        if(node == null){
            return null;
        }
        int cmp = key.compareTo(node.key);
        if(cmp > 0){
            node.right = removeHelper(node.right,key);
        }else if(cmp < 0){
            node.left = removeHelper(node.left,key);
        }else{
            if(node.left == null){
                return node.right;
            }
            if(node.right == null){
                return node.left;
            }
            Node tmp = node;
            node = min(tmp.right);
            node.right = deleteMin(tmp.right);
            node.left = tmp.left;
        }
        return node;
    }
    @Override
    public V remove(K key) {
        V v = get(key);
        this.root = removeHelper(root,key);
        return v;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        V v = get(key);
        if(v == value){
            root = removeHelper(root,key);
        }
        return v;
    }

    private void IteratorHelper(Queue q,Node node){
        if(node == null){
            return;
        }
        q.add(node.key);
        IteratorHelper(q,node.left);
        IteratorHelper(q,node.right);
    }
    @Override
    public Iterator<K> iterator() {
//        throw new UnsupportedOperationException();
        return keySet().iterator();
    }
}
