import java.util.*;

import static org.junit.Assert.*;

public class RandomizedQueueTest {

    public static final String I_WILL = "I'll";
    public static final String BE = "be";
    public static final String BACK = "back";
    public static final String EXCLAMATION_MARKS = "!!!";
    public static final String TEST = "test";
    public static final int TEST_SIZE = 10;

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
            Integer firstValue = iteratorOne.next();
            System.out.println("firstValue = " + firstValue);
            Integer secondValue = iteratorTwo.next();
            System.out.println("secondValue = " + secondValue);
            if (firstValue.equals(secondValue)) {
                sameItemCount++;
            }
        }
        assertTrue(sameItemCount < TEST_SIZE);
    }

    @org.junit.Test
    public void testName() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(31);
        queue.enqueue(49);
        assertEquals(2, queue.size());
        queue.enqueue(49);
        queue.enqueue(2);
        queue.enqueue(1);
        assertEquals(5, queue.size());
        Integer sample = queue.sample();
        System.out.println("sample = " + sample);
        assertNotNull(sample);

//        Test 4: Calls to enqueue(), dequeue(), sample(), isEmpty(), and size().
//                *     5 random calls (0.6, 0.1, 0.1, 0.1, 0.1)
//        *    50 random calls (0.6, 0.1, 0.1, 0.1, 0.1)
//        - failed on operation 8 of 50
//                - sample() returned null
//                - sequence of randomized queue operations was:
//        rq.enqueue(31)
//        rq.enqueue(49)
//        rq.size()        ==> 2
//        rq.enqueue(49)
//        rq.enqueue(2)
//        rq.enqueue(1)
//        rq.size()        ==> 5
//        rq.size()        ==> 5
//        rq.sample()      ==> null
//
//                *   500 random calls (0.6, 0.1, 0.1, 0.1, 0.1)
//        java.lang.NullPointerException
//        RandomizedQueue.dequeue(RandomizedQueue.java:39)
//        TestRandomizedQueue.random(TestRandomizedQueue.java:72)
//        TestRandomizedQueue.test4(TestRandomizedQueue.java:218)
//        TestRandomizedQueue.main(TestRandomizedQueue.java:831)
//
//        - sequence of dequeue operations was:
//        rq.enqueue(260)
//        rq.enqueue(321)
//        rq.dequeue()
//
//                *  1000 random calls (0.6, 0.1, 0.1, 0.1, 0.1)
//        - failed on operation 15 of 1000
//                - sample() returned null
//
//                *     5 random calls (0.1, 0.1, 0.6, 0.1, 0.1)
//        *    50 random calls (0.1, 0.1, 0.6, 0.1, 0.1)
//        - failed on operation 1 of 50
//                - sample() returned null
//                - sequence of randomized queue operations was:
//        rq.enqueue(43)
//        rq.sample()      ==> null
//
//                *   500 random calls (0.1, 0.1, 0.6, 0.1, 0.1)
//        java.lang.NullPointerException
//        RandomizedQueue.dequeue(RandomizedQueue.java:39)
//        TestRandomizedQueue.random(TestRandomizedQueue.java:72)
//        TestRandomizedQueue.test4(TestRandomizedQueue.java:222)
//        TestRandomizedQueue.main(TestRandomizedQueue.java:831)
//
//        - sequence of dequeue operations was:
//        rq.size()        ==> 0
//        rq.enqueue(224)
//        rq.dequeue()
//
//                *  1000 random calls (0.1, 0.1, 0.6, 0.1, 0.1)
//        - failed on operation 5 of 1000
//                - sample() returned null
//                - sequence of randomized queue operations was:
//        rq.size()        ==> 0
//        rq.size()        ==> 0
//        rq.size()        ==> 0
//        rq.isEmpty()     ==> true
//        rq.enqueue(713)
//        rq.sample()      ==> null

    }
}