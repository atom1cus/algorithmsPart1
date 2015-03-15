import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class RandomizedQueueTest {

    public static final String I_WILL = "I'll";
    public static final String BE = "be";
    public static final String BACK = "back";
    public static final String EXCLAMATION_MARKS = "!!!";
    public static final String TEST = "test";
    public static final int TEST_SIZE = 100;
    public static final int COUNT_OF_EXPERIMENTS = 10000;
    public static final int COUNT_OF_ITEMS = 10;

    @org.junit.Test
    public void testIsEmpty() throws Exception {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        assertTrue(queue.isEmpty());
        queue.enqueue(TEST);
        assertFalse(queue.isEmpty());
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @org.junit.Test
    public void testSize() throws Exception {
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

    @org.junit.Test
    public void testQueue() throws Exception {
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

    @org.junit.Test
    public void testSample() throws Exception {
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

    @org.junit.Test(expected = NullPointerException.class)
    public void testNullPointerException() throws Exception {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.enqueue(null);
    }

    @org.junit.Test(expected = NoSuchElementException.class)
    public void testDequeueNoSuchElementException() throws Exception {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.dequeue();
    }

    @org.junit.Test(expected = NoSuchElementException.class)
    public void testSampleNoSuchElementException() throws Exception {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.sample();
    }

    @org.junit.Test
    public void testIterator() throws Exception {
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

    @org.junit.Test(expected = UnsupportedOperationException.class)
    public void testIteratorUnsupportedOperationException() throws Exception {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.enqueue(TEST);
        queue.iterator().remove();
    }

    @org.junit.Test(expected = NoSuchElementException.class)
         public void testIteratorNoSuchElementException() throws Exception {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.enqueue(I_WILL);
        queue.enqueue(TEST);
        Iterator<String> iterator = queue.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
    }

    @org.junit.Test
    public void testIndependentIterator() throws Exception {
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
    public void testRandomnessOfIterator() throws Exception {
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