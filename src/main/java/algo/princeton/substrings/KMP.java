package algo.princeton.substrings;

public class KMP {

    private static final int R = 256;
    private final String pat;
    private final int[][] dfa;

    public KMP(String pat) {
        this.pat = pat;
        int N = pat.length();
        this.dfa = new int[R][N];
        dfa[pat.charAt(0)][0] = 1;
        for (int X = 0, j = 1; j < N; j++) {
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][X];
            }
            dfa[pat.charAt(j)][j] = j + 1;
            X = dfa[pat.charAt(j)][X];
        }
    }

    public int search(String text) {

        int M = text.length();
        int N = pat.length();
        int j = 0;
        int i;
        for (i =0; i < M && j < N; i++) {
            j = dfa[text.charAt(i)][j];
        }
        if (j == N) {
            return i - N;
        }
        return M;
    }

    public static void main(String[] args) {
        KMP kmp = new KMP("ADABR");
        System.out.println(kmp.search("ABACADABRAC"));
    }
}
