package algo.princeton.dp;

public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String string, String subsequence) {
        int m = string.length();
        int n = subsequence.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (string.charAt(i - 1) == subsequence.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);

                }
            }
        }

        return dp[m][n];
    }

    public int longestCommonSubsequenceRecursive(String string, String subsequence, int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }

        if (string.charAt(m - 1) == subsequence.charAt(n - 1)) {
            return 1 + longestCommonSubsequenceRecursive(string, subsequence, m - 1, n - 1);
        } else {
            return Math.max(longestCommonSubsequenceRecursive(string, subsequence, m, n - 1), longestCommonSubsequenceRecursive(string, subsequence, m - 1, n));
        }
    }

    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        System.out.println(lcs.longestCommonSubsequence("ABCBDAB", "BDCAB"));
        System.out.println(lcs.longestCommonSubsequenceRecursive("ABCBDAB", "BDCAB", 7, 5));
    }
}
