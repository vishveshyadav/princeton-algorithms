package algo.princeton.sorting;

/**
 * Takes two loop, first loop traverse overall all the elements, second loop traverse from i till 0 and
 * keeps on swapping each element at `j` which is smaller than the element at `j-1`
 */
public class InsertionSort {

    public int[] sort(int[] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = i; j > 0; j--) {
                if (input[j] < input[j - 1]) {
                    int swap = input[j];
                    input[j] = input[j - 1];
                    input[j - 1] = swap;
                }
            }
        }
        return input;
    }

    public static void main(String[] args) {
        InsertionSort sort = new InsertionSort();
        for (int a : sort.sort(new int[]{35, 29, 45, 65, 99})) {
            System.out.println(a);
        }
    }

}
