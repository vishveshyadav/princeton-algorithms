package algo.princeton.strings;

import java.util.Arrays;

/*
Given a string of 'N' characters, find the longest repeated substring
 */
public class LongestRepeatedSubString {

    public String lrs(String in) {
        int N = in.length();
        String[] suffixes = new String[N];
        for (int i = 0; i < N; i++) {
            suffixes[i] = in.substring(i);
        }
        Arrays.sort(suffixes);
        String lrs = "";
        for (int i = 0; i < N - 1; i++) {
            int len = lcp(suffixes[i], suffixes[i + 1]);
            if (len > lrs.length()) {
                lrs = suffixes[i].substring(0, len);
            }
        }
        return lrs;
    }

    private int lcp(String a, String b) {
        int len = Math.min(a.length(), b.length());
        int i = 0;
        for (; i < len; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return i;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        LongestRepeatedSubString lrs = new LongestRepeatedSubString();
        System.out.println(lrs.lrs("aacaagtttacaagc"));
    }

}
