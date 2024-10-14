package algo.princeton.dp;

public class KnuthMorrisPrat {

    private static final int R = 256;

    public int[] computeLPS(String pattern) {
        int[] lps = new int[pattern.length()];
        int length = 0;
        int i = 1;
        lps[0] = 0;
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = lps[length - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    public int search(String text, String pattern) {

        int i = 0;
        int j = 0;
        int[] lps = computeLPS(pattern);

        while (i < text.length()) {

            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }

            if (j == pattern.length()) {
                return i - j;
            } else if (i < text.length() && text.charAt(i) != pattern.charAt(j)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return text.length();
    }

    public static void main(String[] args) {

        KnuthMorrisPrat kmp = new KnuthMorrisPrat();
        String text = "ababcabcabababd";
        String pattern = "ababd";
        System.out.println(kmp.search(text, pattern));
    }
}
