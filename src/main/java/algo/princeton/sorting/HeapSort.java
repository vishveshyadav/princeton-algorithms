package algo.princeton.sorting;


import java.util.stream.IntStream;

public class HeapSort {

    private final int[] heap;
    private int n;

    public HeapSort(int capacity) {
        heap = new int[capacity];
        n = 0;
    }

    public void sort(int[] in) {

        int N = in.length - 1;

        for (int i = N / 2; i >= 0; i--) {
            sink(in, i, N);
        }

        while (N >= 0) {
            int temp = in[0];
            in[0] = in[N];
            in[N] = temp;
            sink(in, 0, --N);
        }

    }

    public int get() {
        int max = heap[0];
        int temp = heap[--n];
        heap[0] = temp;
        sink(heap, 0, n);
        return max;
    }

    public void add(int k) {
        heap[n] = k;
        swim(heap, n++);
    }

    private void swim(int[] in, int n) {
        while (n > 0 && in[n] > in[(n - 1) / 2]) {
            int temp = in[n];
            in[n] = in[(n - 1) / 2];
            in[(n - 1) / 2] = temp;
            n = (n - 1) / 2;
        }
    }

    private void sink(int[] in, int k, int n) {

        while (2 * k + 1 < n) {
            int maxChild = 2 * k + 1;
            if (maxChild + 1 <= n && in[maxChild + 1] > in[maxChild]) {
                maxChild++;
            }
            if (in[maxChild] <= in[k]) {
                break;
            }
            int temp = in[maxChild];
            in[maxChild] = in[k];
            in[k] = temp;
            k = maxChild;
        }
    }


    public static void main(String[] args) {
        int[] in = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        HeapSort heapSort = new HeapSort(11);
        heapSort.sort(in);
        IntStream.range(0, in.length).forEach(val -> System.out.print(in[val] + "\t"));
        IntStream.range(0, 10).forEach(heapSort::add);
        System.out.println();
        IntStream.range(0, 10).forEach(val -> System.out.print(heapSort.get() + "\t"));
    }

}
