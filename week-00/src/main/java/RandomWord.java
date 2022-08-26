import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {

    /**
     * Reads a sequence of words from standard input and prints one of those words uniformly at random using Knuth’s method:
     * When reading the i-th word, select it with probability 1/i to be the champion, replacing the previous champion.
     * After reading all the words, print the surviving champion.
     */
    public static void main(String[] args) {
        int wordCounter = 0;
        String randomWord = "";

        while (!StdIn.isEmpty()) {
            String currentWord = StdIn.readString();
            ++wordCounter;
            if (StdRandom.bernoulli(1.0F / wordCounter)) {
                randomWord = currentWord;
            }
        }

        StdOut.println(randomWord);
    }
}