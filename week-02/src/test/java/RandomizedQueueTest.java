import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class RandomizedQueueTest {

    private static final String I_WILL = "I'll";
    private static final String BE = "be";
    private static final String BACK = "back";
    private static final String EXCLAMATION_MARKS = "!!!";
    private static final String TEST = "test";
    private static final int TEST_SIZE = 100;
    private static final int COUNT_OF_EXPERIMENTS = 10000;
    private static final int COUNT_OF_ITEMS = 10;

    @Test
    void testIsEmpty() {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        assertTrue(queue.isEmpty());
        queue.enqueue(TEST);
        assertFalse(queue.isEmpty());
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    void testSize() {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        assertTrue(queue.isEmpty());
        queue.enqueue(I_WILL);
        queue.enqueue(BE);
        queue.enqueue(BACK);
        assertEquals(3, queue.size());
        queue.dequeue();
        assertEquals(2, queue.size());
        queue.enqueue(EXCLAMATION_MARKS);
        queue.enqueue(TEST);
        assertEquals(4, queue.size());
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assertEquals(0, queue.size());
    }

    @Test
    void testQueue() {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.enqueue(I_WILL);
        queue.enqueue(BE);
        queue.enqueue(BACK);
        queue.enqueue(EXCLAMATION_MARKS);
        List<String> strings = Arrays.asList(I_WILL, BE, BACK, EXCLAMATION_MARKS);
        assertTrue(strings.contains(queue.dequeue()));
        assertTrue(strings.contains(queue.dequeue()));
        assertTrue(strings.contains(queue.dequeue()));
        assertTrue(strings.contains(queue.dequeue()));
        assertEquals(0, queue.size());
        queue.enqueue(I_WILL);
        queue.enqueue(TEST);
        strings = Arrays.asList(I_WILL, TEST);
        assertTrue(strings.contains(queue.dequeue()));
        assertTrue(strings.contains(queue.dequeue()));
        assertEquals(0, queue.size());
    }

    @Test
    void testSample() {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.enqueue(I_WILL);
        queue.enqueue(BE);
        queue.enqueue(BACK);
        queue.enqueue(EXCLAMATION_MARKS);
        queue.enqueue(TEST);
        List<String> strings = Arrays.asList(I_WILL, BE, BACK, EXCLAMATION_MARKS, TEST);
        for (int i = 0; i < 100; i++) {
            assertTrue(strings.contains(queue.sample()));
        }
    }

    @Test
    void testNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            RandomizedQueue<String> queue = new RandomizedQueue<>();
            queue.enqueue(null);
        });
    }

    @Test
    void testDequeueNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> {
            RandomizedQueue<String> queue = new RandomizedQueue<>();
            queue.dequeue();
        });
    }

    @Test
    void testSampleNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> {
            RandomizedQueue<String> queue = new RandomizedQueue<>();
            queue.sample();
        });
    }

    @Test
    void testIterator() {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.enqueue(I_WILL);
        queue.enqueue(BE);
        queue.enqueue(BACK);
        queue.enqueue(EXCLAMATION_MARKS);
        assertEquals(4, queue.size());

        List<String> strings = new ArrayList<>();
        for (String item : queue) {
            strings.add(item);
        }
        assertEquals(4, strings.size());
        assertTrue(strings.containsAll(Arrays.asList(I_WILL, BE, BACK, EXCLAMATION_MARKS)));
    }

    @Test
    void testIteratorUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> {
            RandomizedQueue<String> queue = new RandomizedQueue<>();
            queue.enqueue(TEST);
            queue.iterator().remove();
        });
    }

    @Test
    void testIteratorNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> {
            RandomizedQueue<String> queue = new RandomizedQueue<>();
            queue.enqueue(I_WILL);
            queue.enqueue(TEST);
            Iterator<String> iterator = queue.iterator();
            iterator.next();
            iterator.next();
            iterator.next();
        });
    }

    @Test
    void testIndependentIterator() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < TEST_SIZE; i++) {
            queue.enqueue(i);
        }
        Iterator<Integer> iteratorOne = queue.iterator();
        Iterator<Integer> iteratorTwo = queue.iterator();
        int sameItemCount = 0;
        for (int i = 0; i < TEST_SIZE; i++) {
            if (iteratorOne.next().equals(iteratorTwo.next())) {
                sameItemCount++;
            }
        }
        assertTrue(sameItemCount < TEST_SIZE);
    }

    @Test
    void testRandomnessOfIterator() {
        int[] countOfReturnedA = new int[COUNT_OF_ITEMS];
        for (int i = 0; i < COUNT_OF_EXPERIMENTS; i++) {
            RandomizedQueue<Integer> queue = new RandomizedQueue<>();
            for (int j = 0; j < COUNT_OF_ITEMS; j++) {
                queue.enqueue(j + 1);

            }

            Iterator<Integer> iterator = queue.iterator();
            for (int j = 0; j < COUNT_OF_ITEMS; j++) {
                if (Integer.valueOf(1).equals(iterator.next())) {
                    countOfReturnedA[j]++;
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < COUNT_OF_ITEMS; i++) {
            sum += countOfReturnedA[i];
            assertTrue(countOfReturnedA[i] > 0);
        }
        assertEquals(COUNT_OF_EXPERIMENTS, sum);

        for (int count : countOfReturnedA) {
            System.out.print(count + " ");
        }
        System.out.println();
    }
}