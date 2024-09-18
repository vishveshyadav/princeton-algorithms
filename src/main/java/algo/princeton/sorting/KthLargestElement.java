package algo.princeton.sorting;

public class KthLargestElement {

    private int partition(int[] input, int low, int high) {

        int i = low;
        int j = high + 1;

        while (true) {

            while (input[++i] < input[low]) {
                if (i == high) {
                    break;
                }
            }

            while (input[--j] > input[low]) {
                if (j == low) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }

            int swap = input[i];
            input[i] = input[j];
            input[j] = swap;
        }

        int swap = input[low];
        input[low] = input[j];
        input[j] = swap;

        return j;
    }

    public int kthLargest(int[] input, int k) {

        int low = 0;
        int high = input.length -1 ;

        while (high > low) {
            int partition = partition(input, low, high);
            if (partition < k) {
                low = partition + 1;
            } else if (partition > k) {
                high = partition - 1;
            } else {
                return input[k];
            }
        }
        return input[k];
    }

    public static void main(String[] args) {
        KthLargestElement kth = new KthLargestElement();
        int[] input = new int[]{
                7, 2, 6, 3, 5, 4, 1, 8
        };
        int N = input.length;
        int k = 5;
        System.out.println(kth.kthLargest(input, N - k));
    }
}
