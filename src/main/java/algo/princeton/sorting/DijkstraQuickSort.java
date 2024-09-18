package algo.princeton.sorting;

//Also called 3-way quick sort
public class DijkstraQuickSort {

    public void sort(int[] input, int low, int high) {

        if (low < high) {
            int pivot = input[low];
            int i = low;
            int lt = low;
            int gt = high;
            while (i <= gt) {
                if (input[i] > pivot) {
                    int swap = input[gt];
                    input[gt] = input[i];
                    input[i] = swap;
                    gt--;
                } else if (input[i] < pivot) {
                    int swap = input[lt];
                    input[lt] = input[i];
                    input[i] = swap;
                    lt++;
                    i++;
                } else {
                    i++;
                }

                sort(input, low, lt - 1);
                sort(input, gt + 1, high);
            }
        }

    }

    public static void main(String[] args) {
        DijkstraQuickSort sort = new DijkstraQuickSort();
        int[] input = new int[]{
                7, 2, 6, 3, 5, 4, 1, 8
        };
        int N = input.length;
        sort.sort(input, 0, N - 1);
        for (int a : input) {
            System.out.print(a+"\t");
        }
    }
}
