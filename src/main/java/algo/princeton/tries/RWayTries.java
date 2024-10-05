package algo.princeton.tries;

import java.util.ArrayDeque;
import java.util.Queue;

public class RWayTries<V> {

    private final static int R = 256; //extended ASCII


    private static class Node<V> {
        private V value;
        private final Node<V>[] next = (Node<V>[]) new Node[R];
    }

    private Node<V> root = new Node<>();

    public void put(String key, V val) {
        root = put(root, key, val, 0);
    }

    private Node<V> put(Node<V> node, String key, V val, int d) {
        if (node == null) {
            node = new Node<>();
        }
        if (d == key.length()) {
            node.value = val;
            return node;
        }
        char c = key.charAt(d);
        node.next[c] = put(node.next[c], key, val, d + 1);
        return node;
    }

    public V get(String key) {
        Node<V> val = get(key, root, 0);
        return val == null ? null : val.value;
    }

    private Node<V> get(String key, Node<V> node, int d) {

        if (node == null) {
            return null;
        }
        if (key.length() == d) {
            return node;
        }
        char c = key.charAt(d);
        return get(key, node.next[c], d + 1);
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public void delete(String key) {
        root = delete(root, key, 0);

    }

    public Iterable<String> keys() {
        Queue<String> queue = new ArrayDeque<>();
        collect(root, "", queue);
        return queue;
    }

    public String longestPrefixOf(String query) {
        int length = search(root, query, 0, 0);
        return query.substring(0, length);
    }

    private int search(Node<V> node, String query, int d, int length) {
        if (null == node) {
            return length;
        }
        if (null != node.value) {
            length = d;
        }
        if (d == query.length()) {
            return length;
        }
        char c = query.charAt(d);
        return search(node.next[c], query, d + 1, length);
    }

    private void collect(Node<V> node, String prefix, Queue<String> queue) {
        if (node == null) {
            return;
        }
        if (node.value != null) {
            queue.offer(prefix);
        }
        for (char c = 0; c < R; c++) {
            collect(node.next[c], prefix + c, queue);
        }
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> queue = new ArrayDeque<>();
        Node<V> node = get(prefix, root, 0);
        collect(node, prefix, queue);
        return queue;
    }


    private Node<V> delete(Node<V> node, String key, int d) {
        if (node == null) {
            return null;
        }
        if (key.length() == d) {
            node.value = null;
        } else {
            char c = key.charAt(d);
            node.next[c] = delete(node.next[c], key, d + 1);
        }

        if (isNull(node)) {
            return null;
        }
        return node;
    }

    private boolean isNull(Node<V> node) {

        if (node == null) {
            return true;
        }
        if (node.value != null) {
            return false;
        }
        for (Node<V> child : node.next) {
            if (child != null) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        RWayTries<Integer> tries = new RWayTries<>();
        tries.put("She", 0);
        tries.put("Shells", 5);
        tries.put("Sea", 7);
        tries.put("By", 6);
        for (String str : tries.keys()) {
            System.out.print(str + "\t");
        }
        for (String str : tries.keysWithPrefix("B")) {
            System.out.print(str + "\t");
        }

        System.out.println(tries.longestPrefixOf("Shellsdfggh"));
    }

}
