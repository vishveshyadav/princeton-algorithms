package algo.princeton.sorting;

public class MergeSort {

    public void sort(int[] input, int[] auxiliary, int low, int high) {
        //Recursive approach
        if (low < high) {
            int mid = (low + high) / 2;
            sort(input, auxiliary, low, mid);
            sort(input, auxiliary, mid + 1, high);
            merge(input, auxiliary, low, mid, high);
        }
    }

    public void sort(int[] input) {
        //Bottom up approach
        int N = input.length;
        int[] auxiliary = new int[N];
        for (int size = 1; size < N; size += size) {
            for (int lo = 0; lo < N - size; lo += (2 * size)) {
                merge(input, auxiliary, lo, lo + size - 1, Math.min(lo + (2 * size) + 1, N - 1));
            }
        }
    }

    private void merge(int[] input, int[] auxiliary, int low, int mid, int high) {

        for (int i = low; i <= high; i++) {
            auxiliary[i] = input[i];
        }
        int i = low;
        int j = mid + 1;
        for (int k = low; k <= high; k++) {

            if (i > mid) {
                input[k] = auxiliary[j++];
            } else if (j > high) {
                input[k] = auxiliary[i++];
            } else if (auxiliary[i] < auxiliary[j]) {
                input[k] = auxiliary[i++];
            } else {
                input[k] = auxiliary[j++];
            }
        }

    }

    public static void main(String[] args) {
        int[] input = new int[]{35, 29, 45, 65, 99};
        int[] auxiliary = new int[input.length];
        MergeSort sort = new MergeSort();
        sort.sort(input, auxiliary, 0, input.length - 1);
        for (int a : input) {
            System.out.print(a + "\t");
        }
        System.out.println();
        input = new int[]{35, 29, 45, 65, 99};
        sort.sort(input);
        for (int a : input) {
            System.out.print(a + "\t");
        }
    }
}
