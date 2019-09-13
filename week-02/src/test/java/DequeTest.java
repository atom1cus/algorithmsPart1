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

class DequeTest {

    private static final String I_WILL = "I'll";
    private static final String BE = "be";
    private static final String BACK = "back";
    private static final String EXCLAMATION_MARKS = "!!!";
    private static final String TEST = "test";

    @Test
    void testDequeAsQueue() {
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

    @Test
    void testDequeAsStack() {
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

    @Test
    void testDeque() {
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

    @Test
    void testIsEmpty() {
        Deque<String> deque = new Deque<>();
        assertTrue(deque.isEmpty());
        deque.addFirst(TEST);
        assertFalse(deque.isEmpty());
        deque.removeFirst();
        assertTrue(deque.isEmpty());
    }

    @Test
    void testFirstNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            Deque<String> deque = new Deque<>();
            deque.addFirst(null);
        });
    }

    @Test
    void testLastNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            Deque<String> deque = new Deque<>();
            deque.addLast(null);
        });
    }

    @Test
    void testFirstNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> {
            Deque<String> deque = new Deque<>();
            deque.removeFirst();
        });
    }

    @Test
    void testLastNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> {
            Deque<String> deque = new Deque<>();
            deque.removeLast();
        });
    }

    @Test
    void testIterator() {
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

    @Test
    void testIteratorUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> {
            Deque<String> deque = new Deque<>();
            deque.addFirst(TEST);
            deque.iterator().remove();
        });
    }

    @Test
    void testIteratorNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> {
            Deque<String> deque = new Deque<>();
            deque.addFirst(I_WILL);
            deque.addFirst(TEST);
            Iterator<String> iterator = deque.iterator();
            iterator.next();
            iterator.next();
            iterator.next();
        });
    }

    @Test
    void testIteratorAfterIntermixedCalls() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.removeFirst();
        deque.addFirst(3);
        deque.addLast(4);
        deque.removeLast();
        for (Integer integer : deque) {
            assertEquals(3, (int) integer);
        }
    }
}