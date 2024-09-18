package algo.princeton.unionfind;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickFind {

    private class UF {

        private int[] id;

        public UF(int length) {
            this.id = new int[length];
            for (int i = 0; i < length; i++) {
                id[i] = i;
            }
        }

        public boolean isConnected(int p, int q) {
            return id[p] == id[q];
        }

        public void union(int p, int q) {
            int pid = id[p];
            int qid = id[q];
            for(int i = 0; i< id.length; i++){
               if(id[i] == pid){
                   id[i] = qid;
               }
            }
        }
    }

    public static void main(String[] args) {

        var count = StdIn.readInt();
        QuickFind qf = new QuickFind();
        QuickFind.UF uf = qf.new UF(count);

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
