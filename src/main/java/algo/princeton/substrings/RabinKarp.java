package algo.princeton.substrings;

public class RabinKarp {

    private static final int R = 256;
    private final long patHash;
    private final int M;
    private final long Q = 9223372036854775783L;
    private long RM;

    public RabinKarp(String pattern) {
        this.M = pattern.length();
        RM = 1;
        for (int i = 1; i <= M - 1; i++) {
            RM = (R * RM) % Q;
        }
        patHash = hash(pattern, M);
    }

    //Monte Carlo version
    public int search(String text) {

        int N = text.length();
        long txtHash = hash(text, M);
        if (patHash == txtHash) {
            return 0;
        }
        for (int i = M; i < N; i++) {
            txtHash = (txtHash + Q - RM * text.charAt(i - M) % Q) % Q;
            txtHash = (txtHash * R + text.charAt(i)) % Q;
            if (patHash == txtHash) {
                return i - M + 1;
            }
        }
        return N;
    }

    private long hash(String key, int M) {
        long h = 0;
        for (int j = 0; j < M; j++) {
            h = (R * h + key.charAt(j)) % Q;

        }
        return h;
    }

    public static void main(String[] args) {
        RabinKarp rk = new RabinKarp("ADABR");
        System.out.println(rk.search("ABACADABRAC"));
    }
}
