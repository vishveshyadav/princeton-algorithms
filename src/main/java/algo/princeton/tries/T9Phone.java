package algo.princeton.tries;

import java.util.ArrayList;
import java.util.List;

public class T9Phone {

    private static final int R = 26;
    private static final int OFFSET = 2;
    private static final String[] mapping = new String[]{
            "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    private static class Node {
        private boolean eof;
        private Node[] items = new Node[R];
    }

    private Node root = new Node();

    public void put(String word) {
        root = put(root, word, 0);
    }

    private Node put(Node x, String word, int index) {

        if (null == x) {
            x = new Node();
        }
        if (index == word.length()) {
            x.eof = true;
            return x;
        }
        var c = word.charAt(index) - 'a';
        x.items[c] = put(x.items[c], word, index + 1);
        return x;
    }

    public List<String> searchT9(String digits) {
        List<String> result = new ArrayList<>();
        search(root, digits, 0, result, "");
        return result;
    }

    public boolean contains(String word) {
        return contains(root, word, 0);
    }

    private boolean contains(Node x, String word, int index) {

        if (null == x) {
            return false;
        }

        if (index == word.length()) {
            return x.eof;
        }

        int c = word.charAt(index) - 'a';
        return contains(x.items[c], word, index + 1);
    }

    private void search(Node node, String digits, int d, List<String> result, String prefix) {

        if (node == null) {
            return;
        }
        if (node.eof) {
            result.add(prefix);
        }

        if (d == digits.length()) {
            return;
        }

        var digit = digits.charAt(d);
        if (digit - '0' == 1 || digit - '0' == 2) {
            return;
        }
        String map = mapping[(digit - '0') - OFFSET];
        for (char i : map.toCharArray()) {
            search(node.items[i - 'a'], digits, d + 1, result, prefix + i);
        }

    }

    public static void main(String[] args) {
        T9Phone phone = new T9Phone();
        phone.put("hello");
        phone.put("help");
        phone.put("hell");
        phone.put("geek");
        phone.put("tree");
        phone.put("jell");
        System.out.println(phone.contains("tree"));
        for (String word : phone.searchT9("43556")) {
            System.out.print(word + "\t");
        }

    }

}
