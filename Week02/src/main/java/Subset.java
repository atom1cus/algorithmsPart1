/**
 * Subset class takes a command-line integer k;
 * reads in a sequence of N strings from standard input using StdIn.readString();
 * and prints out exactly k of them, uniformly at random.
 * Each item from the sequence can be printed out at most once.
 * You may assume that 0 ≤ k ≤ N, where N is the number of string on standard input.
 *
 * @author Artem Vegera
 */
public class Subset {

    /**
     * Test client.
     *
     * @param args array of args should have one argument: integer k
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            String message = "This method should have at least one argument";
            throw new IllegalArgumentException(message);
        }

        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> queue = new RandomizedQueue<>();

        String[] strings = StdIn.readAllLines();
        for (String string : strings) {
            queue.enqueue(string);
        }

        for (int i = 0; i < k; i++) {
            System.out.println(queue.dequeue());
        }
    }
}