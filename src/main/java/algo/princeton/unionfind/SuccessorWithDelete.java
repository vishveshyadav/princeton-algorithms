package algo.princeton.unionfind;

public class SuccessorWithDelete {

    private final int[] id;
    private final int[] rank;

    public SuccessorWithDelete(int size) {
        id = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
            rank[i] = 0;
        }
    }

    public void remove(int x) {
        union(x); //union x with x+1

    }

    private void union(int x) {

        int idX = find(x);
        int idY = find(x + 1);
        int rankX = rank[idX];
        int rankY = rank[idY];
        if (rankX > rankY) {
            id[idY] = idX;
        }
        else if(rankX < rankY){
            id[idX] = idY;
        }
        else {
            id[idX] = idY;
            rank[idX]+=1;
        }
    }

    public int find(int x) {
        int root = x;
        while (id[root] != root){
            root = find(id[root]);
        }
        return root;
    }

    public static void main(String[] args) {
        /*
        Given a set of n integers S={0,1,...,n−1} and a sequence of requests of the following form:
        Remove x from S
        Find the successor of x: the smallest y in S such that y≥x.
        */
        SuccessorWithDelete ss = new SuccessorWithDelete(10);
        ss.remove(3);
        ss.remove(4);
        System.out.println(ss.find(2));
        System.out.println(ss.find(3));
    }
}
