package algo.princeton.tries;

import java.util.ArrayDeque;
import java.util.Queue;

public class TernarySearchTrie<V> {

    private static class Node<V> {
        private V value;
        private char c;
        private Node<V> left;
        private Node<V> right;
        private Node<V> middle;
    }

    private Node<V> root;

    public void put(String key, V value) {
        root = put(key, value, root, 0);
    }

    private Node<V> put(String key, V value, Node<V> node, int d) {

        if (key.length() == d) {
            return node;
        }

        char c = key.charAt(d);
        if (node == null) {
            node = new Node<V>();
            node.c = c;
        }
        if (node.c < c) {
            node.right = put(key, value, node.right, d);
        } else if (node.c > c) {
            node.left = put(key, value, node.left, d);
        } else if (d < key.length() - 1) {
            node.middle = put(key, value, node.middle, d + 1);
        } else {
            node.value = value;
        }
        return node;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public V get(String key) {
        var node = get(key, root, 0);
        return node == null ? null : node.value;
    }

    public String longestPrefixOf(String query) {
        Node<V> node = root;
        int i = 0;
        int length = i;
        while (node != null && i < query.length()) {

            char c = query.charAt(i);
            if (c > node.c) {
                node = node.right;
            } else if (c < node.c) {
                node = node.left;
            } else {
                i++;
                if (node.value != null) {
                    length = i;
                }
                node = node.middle;
            }
        }

        return query.substring(0, length);
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> queue = new ArrayDeque<>();
        Node<V> node = get(prefix, root, 0);
        if (node == null) {
            return queue;
        }
        if (node.value != null) {
            queue.offer(prefix);
        }
        collect(node.middle, new StringBuilder(prefix), queue);
        return queue;
    }

    public Iterable<String> keys() {
        Queue<String> queue = new ArrayDeque<>();
        collect(root, new StringBuilder(), queue);
        return queue;
    }


    private void collect(Node<V> node, StringBuilder prefix, Queue<String> queue) {
        if (node == null) {
            return;
        }
        collect(node.left, prefix, queue);
        prefix.append(node.c);
        if (node.value != null) {
            queue.offer(prefix.toString());
        }
        collect(node.middle, prefix, queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(node.right, prefix, queue);
    }

    private Node<V> get(String key, Node<V> node, int d) {
        if (node == null) {
            return null;
        }
        if (d == key.length()) {
            return node;
        }
        char c = key.charAt(d);
        if (c > node.c) {
            return get(key, node.right, d);
        } else if (c < node.c) {
            return get(key, node.left, d);
        } else if (d < key.length() - 1) {
            return get(key, node.middle, d + 1);
        } else {
            return node;
        }
    }

    public static void main(String[] args) {
        TernarySearchTrie<Integer> tries = new TernarySearchTrie<>();
        tries.put("She", 0);
        tries.put("Shells", 5);
        tries.put("Sea", 7);
        tries.put("By", 6);
        System.out.println(tries.contains("BANANA"));
        for (String str : tries.keys()) {
            System.out.println(str + "\t");
        }
        for (String str : tries.keysWithPrefix("S")) {
            System.out.println(str + "\t");
        }

        System.out.println(tries.longestPrefixOf("Shellsdfggh"));
    }
}
