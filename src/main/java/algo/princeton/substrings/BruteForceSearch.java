package algo.princeton.substrings;

public class BruteForceSearch {

    public int search(String text, String pattern) {

        int M = text.length();
        int N = pattern.length();
        for (int i = 0; i <= M - N; i++) {

            int j = 0;
            for (; j < N; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == N) {
                return i;
            }
        }
        return M;
    }

    public int newSearch(String text, String pattern) {

        int i = 0;
        int j = 0;
        int M = text.length();
        int N = pattern.length();

        for (; i < M && j < N; i++) {

            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
            } else {
                i -= j;
                j = 0;
            }
        }
        if (j == N) {
            return i - N;
        }
        return M;
    }

    public static void main(String[] args) {
        BruteForceSearch bfSearch = new BruteForceSearch();
        System.out.println(bfSearch.search("ABACADABRAC", "ADABR"));
        System.out.println(bfSearch.newSearch("ABACADABRAC", "ADABR"));
    }
}
