package algo.princeton.strings;

public class KeyIndexSort {

    /*
    Sorts an array 'a' of 'N' integers between 0 and R-1
     */
    public void sort(int[] a, int R) {
        int N = a.length;
        int[] count = new int[R + 1]; // 1 based indexing
        int[] aux = new int[N];

        for (int i = 0; i < N; i++) {
            count[a[i] + 1]++;
        }

        for (int r = 1; r < R; r++) {
            count[r + 1] += count[r];
        }

        for (int i = 0; i < N; i++) {
            aux[count[a[i]]++] = a[i];
        }

        for (int i = 0; i < N; i++) {
            a[i] = aux[i];
        }

    }

    public static void main(String[] args) {
        KeyIndexSort sort = new KeyIndexSort();
        int[] a = new int[]{0, 1, 0, 1, 0, 1, 0, 1};
        sort.sort(a, 2);
        for (int i : a) {
            System.out.print(i + "\t");
        }
    }

}
