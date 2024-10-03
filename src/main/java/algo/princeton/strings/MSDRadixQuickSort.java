package algo.princeton.strings;

public class MSDRadixQuickSort {


    public void sort(String[] a, int low, int high, int d) {

        if (high < low) {
            return;
        }
        int lt = low;
        int gt = high;
        int v = charAt(a[low], d);
        int i = low + 1;
        while (i <= gt) {

            int t = charAt(a[i], d);
            if (t < v) {
                String temp = a[lt];
                a[lt] = a[i];
                a[i] = temp;
                lt++;
                i++;
            } else if (t > v) {
                String temp = a[gt];
                a[gt] = a[i];
                a[i] = temp;
                gt--;
                i++;
            } else {
                i++;
            }
        }

        sort(a, low, lt - 1, d);
        if (v >= 0) {
            sort(a, lt, gt, d + 1);
        }
        sort(a, gt + 1, high, d);

    }

    private int charAt(String s, int d) {
        if (d < s.length()) {
            return s.charAt(d);
        }
        return -1;
    }

    public static void main(String[] args) {
        MSDRadixQuickSort sort = new MSDRadixQuickSort();
        String[] a = new String[]{"klm", "hij", "efg", "cde", "abc"};
        sort.sort(a,0, a.length - 1, 0);
        for (String i : a) {
            System.out.print(i + "\t");
        }
    }
}
