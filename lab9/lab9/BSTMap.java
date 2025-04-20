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

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        } else {
            V ret = null;
            if (key.compareTo(p.key) > 0) {
                ret = getHelper(key, p.right);
            } else if (key.compareTo(p.key) < 0) {
                ret = getHelper(key, p.left);
            } else {
                ret = p.value;
            }
            return ret;
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            return new Node(key, value);
        } else {
            if (key.compareTo(p.key) > 0) {
                p.right = putHelper(key, value, p.right);
            } else if (key.compareTo(p.key) < 0) {
                p.left = putHelper(key, value, p.left);
            }
            return p;
        }
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        if (!containsKey(key)) {
            size++;
        }
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    private Set<K> keySetHelper(Node node) {
        if (node == null) {
            return new TreeSet<>();
        } else {
            Set<K> subset = new TreeSet<>();
            subset.add(node.key);
            subset.addAll(keySetHelper(node.left));
            subset.addAll(keySetHelper(node.right));
            return subset;
        }
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keySetHelper(root);
    }

    private Node removeHelper(K key, Node node) {
        if (node == null) {
            return null;
        } else {
            if (key.compareTo(node.key) > 0) {
                node.right = removeHelper(key, node.right);
            } else if (key.compareTo(node.key) < 0) {
                node.left = removeHelper(key, node.left);
            } else {
                // isLeaf:
                if (node.left == null && node.right == null) {
                    return null;
                // only right child:
                } else if (node.left == null) {
                    return node.right;
                // only left child:
                } else if (node.right == null) {
                    return node.left;
                // two children:
                } else {
                    // predecessor:
                    Node predecessor = node.left;
                    while (predecessor.right != null) {
                        predecessor = predecessor.right;
                    }
                    // sign:
                    node.key = predecessor.key;
                    node.value = predecessor.value;
                    // remove:
                    removeHelper(predecessor.key, node.left);
                    return node;
                }
            }
            return node;
        }
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        V ret = get(key);
        if (ret != null) {
            root = removeHelper(key, root);
            size--;
        }
        return ret;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        V ret = get(key);
        if (ret == value) {
            size--;
            removeHelper(key, root);
            return ret;
        } else {
            return null;
        }
    }

    private class BSTIterator implements Iterator<K> {

        private Stack<Node> stack;

        private void addLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        private BSTIterator() {
            stack = new Stack<>();
            addLeft(root);
        }



        @Override
        public boolean hasNext() {
            return !stack.empty();
        }

        @Override
        public K next() {
            Node ret = stack.pop();
            if (ret.right != null) {
                addLeft(ret.right);
            }
            return ret.key;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTIterator();
    }
}
