package algo.princeton.unionfind;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnion {

    private int[] id;
    private int[] sz;

    public WeightedQuickUnion(int length) {
        id = new int[length];
        sz = new int[length];

        for (int i = 0; i < length; i++) {
            id[i] = i;
        }
        for (int i = 0; i < length; i++) {
            sz[i] = 0;
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

        int sizep = sz[rootp];
        int sizeq = sz[rootq];

        if (sizep > sizeq) {
            id[rootq] = rootp;
            sz[rootp] += sizeq;
        } else {
            id[rootp] = rootq;
            sz[rootq] += sizep;
        }
    }


    public static void main(String[] args) {
        int count = StdIn.readInt();
        WeightedQuickUnion wqu = new WeightedQuickUnion(count);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!wqu.isConnected(p, q)) {
                wqu.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
    }
}
