import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Deque implementation.
 *
 * @author Artem Vegera
 */
public class Deque<Item> implements Iterable<Item> {

    /**
     * Size of deque.
     */
    private int size = 0;

    /**
     * First node.
     */
    private Node firstNode = null;

    /**
     * Last node.
     */
    private Node lastNode = null;

    /**
     * Constructs an empty deque
     */
    public Deque() {
    }

    /**
     * Is the deque empty?
     *
     * @return true if deque items is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items on the deque.
     *
     * @return the number of items on the deque
     */
    public int size() {
        return size;
    }

    /**
     * Adds the item to the front.
     *
     * @param item item for addition
     */
    public void addFirst(Item item) {
        checkForNullValue(item);
        if (isEmpty()) {
            addFirstEverItem(item);
        } else {
            Node newNode = new Node(item, firstNode, null);
            firstNode.previousNode = newNode;
            firstNode = newNode;
        }
        size++;
    }

    /**
     * Adds the item to the end.
     *
     * @param item item for addition
     */
    public void addLast(Item item) {
        checkForNullValue(item);
        if (isEmpty()) {
            addFirstEverItem(item);
        } else {
            Node newNode = new Node(item, null, lastNode);
            lastNode.nextNode = newNode;
            lastNode = newNode;
        }
        size++;
    }

    /**
     * Remove and return the item from the front.
     *
     * @return first item
     */
    public Item removeFirst() {
        checkEmptyDeque();
        Item itemForReturn = firstNode.item;
        if (firstNode.nextNode == null) {
            lastNode = null;
            firstNode = null;
        } else {
            firstNode = firstNode.nextNode;
            firstNode.previousNode = null;
        }
        size--;
        return itemForReturn;
    }

    /**
     * Remove and return the item from the end.
     *
     * @return last item
     */
    public Item removeLast() {
        checkEmptyDeque();
        Item itemForReturn = lastNode.item;
        if (lastNode.previousNode == null) {
            lastNode = null;
            firstNode = null;
        } else {
            lastNode = lastNode.previousNode;
            lastNode.nextNode = null;
        }
        size--;
        return itemForReturn;
    }

    /**
     * Returns an iterator over items in order from front to end.
     *
     * @return iterator object
     */
    public Iterator<Item> iterator() {
        return new DequeIterator(firstNode);
    }

    private void checkForNullValue(Item item) {
        if (item == null) {
            throw new NullPointerException("Item value cannot be null!");
        }
    }

    private void checkEmptyDeque() {
        if (isEmpty()) {
            String message = "Deque is empty! Can't delete item from deque.";
            throw new NoSuchElementException(message);
        }
    }

    private void addFirstEverItem(Item item) {
        firstNode = new Node(item, null, null);
        lastNode = firstNode;
    }

    /**
     * This class encapsulates node of linked deque implementation.
     */
    private class Node {

        private Item item;
        private Node nextNode;
        private Node previousNode;

        /**
         * Constructs node
         *
         * @param item item
         * @param nextNode next node
         * @param previousNode previous node
         */
        public Node(Item item, Node nextNode, Node previousNode) {
            this.item = item;
            this.nextNode = nextNode;
            this.previousNode = previousNode;
        }
    }

    /**
     * This class implements deque iterator.
     */
    private class DequeIterator implements Iterator<Item> {

        private Node currentItem;

        /**
         * Constructs deque iterator
         *
         * @param currentItem current item
         */
        public DequeIterator(Node currentItem) {
            this.currentItem = currentItem;
        }

        @Override
        public boolean hasNext() {
            return currentItem != null;
        }

        @Override
        public Item next() {
            if (currentItem == null) {
                throw new NoSuchElementException("No more items to return.");
            }
            Item nextItem = currentItem.item;
            currentItem = currentItem.nextNode;
            return nextItem;
        }

        @Override
        public void remove() {
            String message = "Remove operation doesn't supported.";
            throw new UnsupportedOperationException(message);
        }
    }
}