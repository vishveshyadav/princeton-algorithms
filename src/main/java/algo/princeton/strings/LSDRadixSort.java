package algo.princeton.strings;

public class LSDRadixSort {

    /*
    Sorts an array 'a' of 'N' strings each having a fixed length W
     */
    public void sort(String[] a, int W) {

        int R = 256;
        int N = a.length;
        String[] aux = new String[N];

        for (int w = W - 1; w >= 0; w--) {
            int[] count = new int[R + 1]; // 1 based indexing
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(w) + 1]++;
            }
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }
            for (int i = 0; i < N; i++) {
                aux[count[a[i].charAt(w)]++] = a[i];
            }
            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }
        }

    }

    public static void main(String[] args) {
        LSDRadixSort sort = new LSDRadixSort();
        String[] a = new String[]{"klm", "hij", "efg", "cde", "abc"};
        sort.sort(a, 2);
        for (String i : a) {
            System.out.print(i + "\t");
        }
    }
}
