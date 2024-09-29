package algo.princeton.unionfind;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnion {

    private int[] id;

    public QuickUnion(int size) {
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    private int root(int p) {
        int root = p;
        while (id[root] != root) {
            root = id[root];
        }
        return root;
    }

    public boolean isConnected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int rootp = root(p);
        int rootq = root(q);
        id[rootp] = rootq;
    }

    public static void main(String[] args) {
        int count = StdIn.readInt();
        QuickUnion qu = new QuickUnion(count);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!qu.isConnected(p, q)) {
                qu.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
    }
}
