

/**
 * @author tuhongchang
 * @version 1.0
 * @create 2022/6/8 下午10:40
 */
@SuppressWarnings({"all"})
public class ArrayDeque<T> {
    private final int INIT_CAPACITY = 8;
    //array to save data
    private T[] array;
    //size of the deque
    private int size;
    //front index
    private int front;
    //back index
    private int back;

    public ArrayDeque() {
        array = (T[]) new Object[INIT_CAPACITY];
        front = 0;
        back = 1;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        resize();
        array[front] = item;
        front = minusOne(front);
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        resize();
        array[back] = item;
        back = plusOne(back, array.length);
        size++;
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        resize();
        T removedItem = array[plusOne(front, array.length)];
        front = plusOne(front, array.length);
        array[front] = null;
        size--;
        return removedItem;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        resize();
        T removedItem = array[minusOne(back)];
        back = minusOne(back);
        array[back] = null;
        size--;
        return removedItem;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (index < 0 || index >= size || size == 0) {
            return null;
        }
        index = Math.floorMod(plusOne(front, array.length) + index, array.length);
        return array[index];
    }

    /* get the last index */
    private int minusOne(int index) {
        int b = Math.floorMod(index - 1, array.length);
        return b;
    }

    /* get the next index */
    private int plusOne(int index, int length) {
        int n = Math.floorMod(index + 1, length);
        return n;
    }

    /**
     * resize
     */
    private void resize() {
        if (size == array.length) {
            grow();
        }
        if (size < (array.length * 0.25) && array.length > 0) {
            shrink();
        }
    }

    /**
     * double the length of array
     */
    private void grow() {
        resizeHelper(array.length * 2);
    }

    /**
     * halve the length of array
     */
    private void shrink() {
        resizeHelper(array.length / 2);
    }

    private void resizeHelper(int capacity) {
        T[] tempArr = array;
        //set the front index + 1, the loop should start from the begin
        int begin = plusOne(front, array.length);
        //set the back index - 1, the loop should end in the end
        int end = minusOne(back);
        array = (T[]) new Object[capacity];
        front = 0;
        back = 1;
        //Rearrange the old queue(from begin to end) to new queue(from back to front)
        for (int i = begin; i != end; i = plusOne(i, tempArr.length)) {
            array[back] = tempArr[i];
            back = plusOne(back, array.length);
        }
        array[back] = tempArr[end];
        back = plusOne(back, array.length);
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        for (int i = plusOne(front, array.length); i != back; i = plusOne(i, array.length)) {
            System.out.print(array[i] + " ");
            System.out.println();
        }
    }
}
