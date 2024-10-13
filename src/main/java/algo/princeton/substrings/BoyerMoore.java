package algo.princeton.substrings;

public class BoyerMoore {

    private static final int R = 256;
    private final int[] right;
    private final String pat;

    public BoyerMoore(String pattern) {
        this.right = new int[R];
        this.pat = pattern;
        for (int c = 0; c < R; c++) {
            right[c] = -1;
        }
        for (int j = 0; j < pattern.length(); j++) {
            right[pattern.charAt(j)] = j;
        }
    }


    public int search(String txt) {
        int N = txt.length();
        int M = pat.length();
        int skip;
        for (int i = 0; i <= N - M; i+=skip) {
            skip = 0;
            for (int j = M - 1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    skip = Math.max(1, j - right[txt.charAt(i + j)]);
                    break;
                }

            }
            if (skip == 0)
                return i;
        }
        return N;
    }

    public static void main(String[] args) {
        BoyerMoore boyerMoore = new BoyerMoore("ADABR");
        System.out.println(boyerMoore.search("ABACADABRAC"));
    }
}
