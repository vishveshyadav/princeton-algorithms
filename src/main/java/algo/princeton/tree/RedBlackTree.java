package algo.princeton.tree;

public class RedBlackTree {

    private static final Boolean RED = true;
    private static final Boolean BLACK = false;
    private Node root;

    private static class Node {

        private int key;
        private int value;
        private Node left;
        private Node right;
        private boolean color;

        public Node(int key, int value, boolean color) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.color = color;
        }
    }

    private boolean isRed(Node node) {
        if (node == null) { //All NULL links black
            return false;
        }

        return node.color == RED;
    }

    private Node rotateLeft(Node node) {

        if (isRed(node.right)) {

            Node temp = node.right;
            node.right = temp.left;
            temp.left = node;
            temp.color = node.color;
            node.color = RED;
            return temp;
        }

        return node;
    }

    private Node rotateRight(Node node) {

        if (isRed(node.left)) {

            Node temp = node.left;
            node.left = temp.right;
            temp.right = node;
            temp.color = node.color;
            node.color = RED;
            return temp;
        }

        return node;
    }

    private void flipColors(Node node) {

        if (!isRed(node) && isRed(node.right) && isRed(node.left)) {
            node.color = RED;
            node.right.color = BLACK;
            node.left.color = BLACK;
        }
    }

    private Node put(int key, int value, Node node) {

        if (node == null) {
            return new Node(key, value, RED);
        }

        if (key < node.key) {
            node.left = put(key, value, node.left);
        } else if (key > node.key) {
            node.right = put(key, value, node.right);
        } else {
            node.value = key;
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }

        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }

        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }


    public void add(int key, int value) {
        root = put(key, value, root);
    }

    public static void main(String[] args) {
        RedBlackTree rbTree = new RedBlackTree();
        rbTree.add(9, 9);
        rbTree.add(5, 5);
        rbTree.add(1, 1);
        rbTree.add(7, 7);
        rbTree.add(10, 10);
    }
}
