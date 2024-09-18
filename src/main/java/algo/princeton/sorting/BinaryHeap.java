package algo.princeton.sorting;

public class BinaryHeap {

    private final int[] key;
    private int size;

    public BinaryHeap(int capacity) {
        key = new int[capacity];
        size = 0;
    }


    public int getSize() {
        return size;
    }

    private void swim(int k) {

        while (k >= 1 && key[k / 2] < key[k]) {
            int swap = key[k / 2];
            key[k / 2] = key[k];
            key[k] = swap;
            k = k / 2;
        }
    }

    private void sink(int k) {

        while (k <= size) {
            int j = 2 * k;
            int biggerChild = key[j + 1] > key[j + 2] ? j + 1 : j + 2;
            if (key[j] > key[biggerChild]) {
                break;
            }
            int swap = key[j];
            key[j] = key[biggerChild];
            key[biggerChild] = swap;
            k = biggerChild;
        }
    }

    public void insert(int k) {
        key[size] = k;
        swim(size);
        size++;
    }

    public int delMax() {
        int max = key[0];
        int swap = key[size--];
        key[0] = swap;
        sink(0);
        return max;
    }

    public static void main(String[] args) {
        BinaryHeap heap = new BinaryHeap(10);
        heap.insert(4);
        heap.insert(8);
        heap.insert(2);
        System.out.println(heap.delMax());
        System.out.println(heap.delMax());
        System.out.println(heap.delMax());
        heap.insert(99);
        heap.insert(98);
        heap.insert(100);
        System.out.println(heap.delMax());
        System.out.println(heap.getSize());
    }

}
