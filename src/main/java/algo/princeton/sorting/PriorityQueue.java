package algo.princeton.sorting;

/*
Implements a Min priority queue, changes the comparator logic inside sink and swim and the same code can behave as max priority queue.
 */
public class PriorityQueue<T extends Comparable<T>> {

    private T[] items;
    private int size;

    public PriorityQueue(int size) {
        items = (T[]) new Comparable[size];
    }

    public void add(T item) {
        items[size] = item;
        swim(size++);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T remove() {

        if (size == 0) {
            return null;
        }
        T item = items[0];
        items[0] = items[--size];
        items[size] = null;
        sink(0);
        return item;
    }


    private void sink(int index) {

        while (2 * index + 1 < size) {

            int maxChild = index;
            if (items[2 * index + 1].compareTo(items[index]) < 0) {
                maxChild = 2 * index + 1;
            }
            if (2 * index + 2 < size && items[2 * index + 2].compareTo(items[maxChild]) < 0) {
                maxChild = 2 * index + 2;
            }

            if (maxChild == index) {
                break;
            }
            T temp = items[maxChild];
            items[maxChild] = items[index];
            items[index] = temp;
            index = maxChild;

        }

    }

    private void swim(int index) {

        while (index > 0 && items[index].compareTo(items[(index - 1) / 2]) < 0) {
            int i = (index - 1) / 2;
            T temp = items[i];
            items[i] = items[index];
            items[index] = temp;
            index = i;
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Double> pq = new PriorityQueue<>(18);
        pq.add(0.32);
        pq.add(0.29);
        pq.add(0.52);
        pq.add(20.0);
        pq.add(0.35);
        pq.add(0.19);
        pq.add(0.16);
        pq.add(0.28);
        pq.add(0.37);
        pq.add(0.34);
        pq.add(0.26);
        pq.add(0.38);
        pq.add(0.58);
        pq.add(0.17);
        pq.add(0.40);
        pq.add(0.36);
        pq.add(0.16);
        pq.add(0.16);
        System.out.println(pq.remove());
        System.out.println(pq.remove());
        System.out.println(pq.remove());
        System.out.println(pq.remove());

    }

}
