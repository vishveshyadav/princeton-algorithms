package algo.princeton.sorting;

import java.util.Arrays;

public class QuickSort {

    private int partition(String[] input, int low, int high) {

        int i = low;
        int j = high + 1;

        while (true) {

            while (input[++i].compareTo(input[low]) < 0) {
                if (i == high) {
                    break;
                }
            }

            while (input[--j].compareTo(input[low]) > 0) {
                if (j == low) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }
            String swap = input[i];
            input[i] = input[j];
            input[j] = swap;
        }

        String swap = input[j];
        input[j] = input[low];
        input[low] = swap;
        return j;
    }

    private void sort(String[] input, int low, int high) {
        if (low < high) {
            int partition = partition(input, low, high);
            sort(input, low, partition - 1);
            sort(input, partition + 1, high);
        }

    }

    public void sort(String[] input) {
        //shuffle
        sort(input, 0, input.length-1);
    }

    public static void main(String[] args) {
        QuickSort qs = new QuickSort();
        String[] input = new String[]{"K", "R", "A", "T", "E", "L", "E", "P", "U", "I", "M", "Q", "C", "X", "O", "S"};
        qs.sort(input);
        System.out.println(Arrays.toString(input));
    }
}
