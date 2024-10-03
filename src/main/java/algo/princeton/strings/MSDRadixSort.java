package algo.princeton.strings;

public class MSDRadixSort {


    public void sort(String[] a, String[] aux, int low, int high, int d) {

        if (high < low) {
            return;
        }
        int R = 256;
        int[] count = new int[R + 2]; // 1 based indexing

        for (int i = low; i <= high; i++) {
            count[charAt(a[i], d) + 2]++;
        }
        for (int r = 0; r < R + 1; r++) {
            count[r + 1] += count[r];
        }
        for (int i = low; i <= high; i++) {
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }
        for (int i = low; i <= high; i++) {
            a[i] = aux[i - low];
        }

        for (int r = 0; r < R; r++) {
            sort(a, aux, low + count[r], low + count[r + 1] - 1, d + 1);
        }

    }

    private int charAt(String s, int d) {
        if (d < s.length()) {
            return s.charAt(d);
        }
        return -1;
    }

    public static void main(String[] args) {
        MSDRadixSort sort = new MSDRadixSort();
        String[] a = new String[]{"klm", "hij", "efg", "cde", "abc"};
        String[] aux = new String[a.length];
        sort.sort(a, aux, 0, a.length - 1, 0);
        for (String i : a) {
            System.out.print(i + "\t");
        }
    }
}
