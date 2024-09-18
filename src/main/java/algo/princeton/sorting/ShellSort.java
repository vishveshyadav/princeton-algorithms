package algo.princeton.sorting;

public class ShellSort {
    //h = 3x + 1

    public int[] sort(int[] input) {

        int N = input.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (input[j] < input[j - h]) {
                        int swap = input[j - h];
                        input[j - h] = input[j];
                        input[j] = swap;
                    }
                }
            }
            h = h / 3;
        }
        return input;
    }

    public static void main(String[] args) {
        ShellSort sort = new ShellSort();
        for (int a : sort.sort(new int[]{35, 29, 45, 65, 99})) {
            System.out.println(a);
        }
    }
}
