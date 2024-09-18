package algo.princeton.unionfind;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnion {

    private class UF {
        private int[] id;

        public UF(int size) {
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
            return id[p] == id[q];
        }

        public void union(int p, int q) {
            int rootp = root(p);
            int rootq = root(q);
            id[rootp] = rootq;
        }
    }


    public static void main(String[] args) {
        int count = StdIn.readInt();
        QuickUnion qu = new QuickUnion();
        QuickUnion.UF uf = qu.new UF(count);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.isConnected(p, q)) {
                uf.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
    }
}
