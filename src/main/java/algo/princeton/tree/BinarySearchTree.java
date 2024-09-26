package algo.princeton.tree;

public class BinarySearchTree {

    private Node root;

    private static class Node {

        private final int key;
        private int value;
        private Node left;
        private Node right;
        private int count;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.count = 0;
        }
    }

    private int size(Node node) {
        if (null == node) {
            return 0;
        }
        return node.count;
    }


    public int get(int key) {
        Node node = root;
        while (node != null) {

            int currKey = node.key;
            if (currKey == key) {
                return node.value;
            } else if (currKey < key) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        return -1;
    }

    private Node put(int key, int value, Node root) {

        if (root == null) {
            return new Node(key, value);
        }
        if (root.key < key) {
            root.right = put(key, value, root.right);
        } else if (root.key > key) {
            root.left = put(key, value, root.left);
        } else {
            root.value = value;
        }
        root.count = 1 + size(root.left) + size(root.right);
        return root;
    }

    public void add(int key, int value) {
        root = put(key, value, root);
    }


    public int max() {
        Node max = maxNode();
        return max == null ? -1 : max.value;
    }

    private Node maxNode() {
        Node node = root;

        if (root == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public int min() {
        Node max = minNode();
        return max == null ? -1 : max.value;
    }

    private Node minNode() {
        Node node = root;

        if (root == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public int rank(int key) {
        return rank(key, root);
    }

    private int rank(int key, Node root) {

        if (root == null) {
            return 0;
        }

        if (root.key == key) {
            return size(root.left);
        } else if (root.key > key) {
            return rank(key, root.left);
        } else {
            return 1 + size(root.left) + rank(key, root.right);
        }
    }

    private void iterate(Node root) {
        if (root != null) {
            iterate(root.left);
            System.out.print(root.value + "\t");
            iterate(root.right);
        }
    }

    public void iterate() {
        iterate(root);
    }

    private Node delete(int key, Node node) {

        if (node == null) {
            return null;
        }
        if (node.key < key) {
            node.right = delete(key, node.right);
        } else if (node.key > key) {
            node.left = delete(key, node.left);
        } else {

            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node t = node;
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            node.left = t.left;
            node.right = delete(key, t.right);
        }
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    public void delete(int key) {
        root = delete(key, root);
    }

    private void rangeSearch(Node root, int low, int high) {

        if (root == null) {
            return;
        }

        if (root.key > low) {
            rangeSearch(root.left, low, high);
        }
        if (root.key >= low && root.key <= high) {
            System.out.print(root.key + "\t");
        }
        if(root.key < high){
            rangeSearch(root.right, low, high);
        }
    }


    public void rangeSearch(int low, int high) {
        rangeSearch(root, low, high);
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(9, 9);
        bst.add(5, 5);
        bst.add(1, 1);
        bst.add(7, 7);
        bst.add(10, 10);
        bst.iterate();
        bst.delete(5);
        bst.rangeSearch(1, 5);
    }
}
