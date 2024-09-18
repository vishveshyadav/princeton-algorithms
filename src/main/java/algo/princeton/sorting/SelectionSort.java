package algo.princeton.sorting;

/**
 * Takes two loop, first loop traverse overall all the elements, second loop traverse from i+1 till N and
 * finds the minimum element which is then swapped with `i`.
 * With each inner loop traversal one element gets to its correct position
 */
public class SelectionSort {

    public int[] sort(int[] input) {

        for (int i = 0; i < input.length; i++) {
            int min = i;
            for (int j = i + 1; j < input.length; j++) {
                if (input[j] < input[min]) {
                    min = j;
                }
            }
            int swap = input[min];
            input[min] = input[i];
            input[i] = swap;
        }
        return input;
    }

    public static void main(String[] args) {
        SelectionSort sort = new SelectionSort();
        for (int a : sort.sort(new int[]{35, 29, 45, 65, 99})) {
            System.out.println(a);
        }
    }

}
