package algo.princeton.intro;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int index = 0;
        String champion = "";

        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            index++;
            if (StdRandom.bernoulli(1 / (index * 1.0))) {
                champion = word;
            }
        }
        StdOut.println(champion);
    }
}
