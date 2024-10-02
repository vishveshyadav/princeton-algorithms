package algo.princeton.mst;

public class IndexMinPQ<K extends Comparable<K>> {

    private int S;
    private final int[] pq;
    private final int[] qp;
    private final K[] keys;

    public IndexMinPQ(int N) {
        this.S = 0;
        this.pq = new int[N];
        this.qp = new int[N];
        this.keys = (K[]) new Comparable[N];
        for (int i = 0; i < N; i++) {
            qp[i] = -1;
        }
    }

    public void insert(int i, K key) {
        pq[S] = i;
        qp[i] = S;
        keys[i] = key;
        swim(S++);
    }

    public void decreaseKey(int i, K key) {
        keys[i] = key;
        swim(qp[i]);
    }

    public void  increaseKey(int i, K key) {
        keys[i] = key;
        sink(qp[i]);
    }

    public boolean contains(int i) {
        return qp[i] != -1;
    }

    public int delMin() {
        int result = pq[0];
        pq[0] = pq[--S];
        sink(0);
        qp[result] = -1;
        keys[result] = null;
        return result;
    }


    public boolean isEmpty() {
        return S == 0;

    }

    public int size() {
        return S;
    }

    private void swim(int i) {
        while (i > 0 && keys[pq[(i - 1) / 2]].compareTo(keys[pq[i]]) > 0) {
            int temp = pq[i];
            pq[i] = pq[(i - 1) / 2];
            pq[(i - 1) / 2] = temp;
            qp[pq[i]] = i;
            qp[pq[(i - 1) / 2]] = (i - 1) / 2;
            i = (i - 1) / 2;
        }
    }

    private void sink(int i) {
        while (2 * i + 1 < S) {
            int m = i;
            if (keys[pq[2 * i + 1]].compareTo(keys[pq[i]]) < 0 ){
                m = 2 * i + 1;
            }
            if ((2 * i + 2) < S && keys[pq[2 * i + 2]].compareTo(keys[pq[m]]) < 0) {
                m = 2 * i + 2;
            }
            if (m == i) {
                break;
            }

            int temp = pq[m];
            pq[m] = pq[i];
            pq[i] = temp;
            qp[pq[i]] = m;
            qp[pq[m]] = i;
            i = m;
        }
    }

    public static void main(String[] args) {
        String[] strings = {"it", "was", "the", "best", "of", "times", "it", "was", "the", "worst"};

        IndexMinPQ<String> pq = new IndexMinPQ<String>(strings.length);
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        while(!pq.isEmpty()){
            System.out.println(strings[pq.delMin()]);
        }
    }

}
