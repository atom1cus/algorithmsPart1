import java.util.*;

import static org.junit.Assert.*;

public class DequeTest {

    public static final String I_WILL = "I'll";
    public static final String BE = "be";
    public static final String BACK = "back";
    public static final String EXCLAMATION_MARKS = "!!!";
    public static final String TEST = "test";

    @org.junit.Test
    public void testDequeAsQueue() throws Exception {
        Deque<String> deque = new Deque<>();
        deque.addFirst(I_WILL);
        deque.addFirst(BE);
        deque.addFirst(BACK);
        deque.addFirst(EXCLAMATION_MARKS);
        assertEquals(4, deque.size());

        assertEquals(I_WILL, deque.removeLast());
        assertEquals(BE, deque.removeLast());
        assertEquals(BACK, deque.removeLast());
        assertEquals(1, deque.size());

        deque.addFirst(TEST);
        assertEquals(2, deque.size());

        assertEquals(EXCLAMATION_MARKS, deque.removeLast());
        assertEquals(TEST, deque.removeLast());
        assertEquals(0, deque.size());
    }

    @org.junit.Test
    public void testDequeAsStack() throws Exception {
        Deque<String> deque = new Deque<>();
        deque.addFirst(EXCLAMATION_MARKS);
        deque.addFirst(BACK);
        deque.addFirst(BE);
        deque.addFirst(I_WILL);
        assertEquals(4, deque.size());

        assertEquals(I_WILL, deque.removeFirst());
        assertEquals(BE, deque.removeFirst());
        assertEquals(BACK, deque.removeFirst());
        assertEquals(1, deque.size());

        deque.addFirst(TEST);
        assertEquals(2, deque.size());

        assertEquals(TEST, deque.removeFirst());
        assertEquals(EXCLAMATION_MARKS, deque.removeFirst());
        assertEquals(0, deque.size());
    }

    @org.junit.Test
    public void testDeque() throws Exception {
        Deque<String> deque = new Deque<>();
        deque.addFirst(EXCLAMATION_MARKS);
        deque.addFirst(BACK);
        deque.addLast(I_WILL);
        deque.addFirst(BE);
        assertEquals(4, deque.size());

        assertEquals(I_WILL, deque.removeLast());
        assertEquals(BE, deque.removeFirst());
        deque.addLast(TEST);
        assertEquals(TEST, deque.removeLast());
        assertEquals(BACK, deque.removeFirst());
        assertEquals(EXCLAMATION_MARKS, deque.removeLast());
        assertEquals(0, deque.size());
    }

    @org.junit.Test
    public void testIsEmpty() throws Exception {
        Deque<String> deque = new Deque<>();
        assertTrue(deque.isEmpty());
        deque.addFirst(TEST);
        assertFalse(deque.isEmpty());
        deque.removeFirst();
        assertTrue(deque.isEmpty());
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void testFirstNullPointerException() throws Exception {
        Deque<String> deque = new Deque<>();
        deque.addFirst(null);
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void testLastNullPointerException() throws Exception {
        Deque<String> deque = new Deque<>();
        deque.addLast(null);
    }

    @org.junit.Test(expected = NoSuchElementException.class)
    public void testFirstNoSuchElementException() throws Exception {
        Deque<String> deque = new Deque<>();
        deque.removeFirst();
    }

    @org.junit.Test(expected = NoSuchElementException.class)
    public void testLastNoSuchElementException() throws Exception {
        Deque<String> deque = new Deque<>();
        deque.removeLast();
    }

    @org.junit.Test
    public void testIterator() throws Exception {
        Deque<String> deque = new Deque<>();
        deque.addFirst(I_WILL);
        deque.addFirst(BE);
        deque.addFirst(BACK);
        deque.addFirst(EXCLAMATION_MARKS);
        assertEquals(4, deque.size());

        List<String> strings = new ArrayList<>();
        for (String item : deque) {
            strings.add(item);
        }
        assertEquals(4, strings.size());
        assertTrue(strings.containsAll(Arrays.asList(I_WILL, BE, BACK, EXCLAMATION_MARKS)));
    }

    @org.junit.Test(expected = UnsupportedOperationException.class)
    public void testIteratorUnsupportedOperationException() throws Exception {
        Deque<String> deque = new Deque<>();
        deque.addFirst(TEST);
        deque.iterator().remove();
    }

    @org.junit.Test(expected = NoSuchElementException.class)
    public void testIteratorNoSuchElementException() throws Exception {
        Deque<String> deque = new Deque<>();
        deque.addFirst(I_WILL);
        deque.addFirst(TEST);
        Iterator<String> iterator = deque.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
    }

    @org.junit.Test
    public void testIteratorAfterIntermixedCalls() throws Exception {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.removeFirst();
        deque.addFirst(3);
        deque.addLast(4);
        deque.removeLast();
        for (Integer integer : deque) {
            assertTrue(integer == 3);
        }
    }
}