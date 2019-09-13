import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Randomized queue implementation.
 *
 * @author Artem Vegera
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items = (Item[]) new Object[1];

    private int size;

    /**
     * Constructs an empty randomized queue
     */
    public RandomizedQueue() {
    }

    /**
     * Is the deque empty?
     *
     * @return true if queue items is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items on the queue.
     *
     * @return the number of items on the queue
     */
    public int size() {
        return size;
    }

    /**
     * Add the item.
     *
     * @param item item for addition
     */
    public void enqueue(Item item) {
        checkForNullValue(item);
        if (size == items.length) {
            resizeItemsArray(size * 2);
        }
        items[size++] = item;
    }

    /**
     * Removes and returns a random item
     *
     * @return random item
     */
    public Item dequeue() {
        checkEmptyQueue();
        if (size > 0 && size == items.length / 4) {
            resizeItemsArray(items.length / 2);
        }
        int randomIndex = getRandomIndex();
        Item itemForReturn = items[randomIndex];
        replaceRemovedItemWithLastItem(randomIndex);
        return itemForReturn;
    }

    /**
     * Returns (but do not removes) a random item
     *
     * @return random item
     */
    public Item sample() {
        checkEmptyQueue();
        return items[getRandomIndex()];
    }

    /**
     * Returns an independent iterator over items in random order.
     *
     * @return iterator object
     */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator(size);
    }

    private void resizeItemsArray(int newSize) {
        Item[] newItems = (Item[]) new Object[newSize];
        System.arraycopy(items, 0, newItems, 0, size);
        items = newItems;
    }

    private void replaceRemovedItemWithLastItem(int index) {
        size--;
        items[index] = items[size];
        items[size] = null;
    }

    private int getRandomIndex() {
        if (size != 1) {
            return StdRandom.uniform(0, size);
        } else {
            return 0;
        }
    }

    private void checkForNullValue(Item item) {
        if (item == null) {
            throw new NullPointerException("Item value cannot be null!");
        }
    }

    private void checkEmptyQueue() {
        if (isEmpty()) {
            String message = "Queue is empty! Can't delete item from queue.";
            throw new NoSuchElementException(message);
        }
    }

    /**
     * This class implements randomized queue iterator.
     */
    private class RandomizedQueueIterator implements Iterator<Item> {

        private int queueSize;

        private int currentIndex;

        private boolean[] iterateFlags;

        /**
         * Constructs deque iterator
         *
         * @param queueSize current queue size
         */
        public RandomizedQueueIterator(int queueSize) {
            this.currentIndex = 0;
            this.queueSize = queueSize;
            this.iterateFlags = new boolean[queueSize];
        }

        @Override
        public boolean hasNext() {
            return currentIndex < queueSize;
        }

        @Override
        public Item next() {
            if (currentIndex >= queueSize) {
                throw new NoSuchElementException("No more items to return.");
            }
            int nextIndex = getNextIndex();
            currentIndex++;
            iterateFlags[nextIndex] = true;
            return items[nextIndex];
        }

        private int getNextIndex() {
            int nextIndex;
            do {
                nextIndex = getRandomIndex();
            } while(iterateFlags[nextIndex]);
            return nextIndex;
        }

        @Override
        public void remove() {
            String message = "Remove operation doesn't supported.";
            throw new UnsupportedOperationException(message);
        }
    }
}