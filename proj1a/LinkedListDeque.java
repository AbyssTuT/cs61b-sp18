/**
 * @author tuhongchang
 * @version 1.0
 * @create 2022/6/8 下午7:40
 */
public class LinkedListDeque<V> {
    private T sentinel;
    private int size;

    class T {
        private T prev;
        private V item;
        private T next;
    }

    public LinkedListDeque() {
        sentinel = new T();
        sentinel.item = (V) new Object();
        sentinel.prev = sentinel;
        sentinel.next = sentinel.prev;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(V item) {
        T t = new T();
        t.item = item;
        t.next = sentinel.next;
        t.prev = sentinel;
        sentinel.next.prev = t;
        sentinel.next = t;

        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(V item) {
        T t = new T();
        t.item = item;
        t.prev = sentinel.prev;
        t.next = sentinel;
        sentinel.prev = t.next;
        sentinel.prev.next = t;

        size++;
    }

    public T removeFirst() {
        if (size == 0) {
            System.out.println("no item in the deque!");
            return null;
        }
        T p = sentinel.next;

        p.next.prev = sentinel;
        sentinel.next = p.next;
        size--;
        return p;
    }

    public T removeLast() {
        if (size == 0) {
            System.out.println("no item in the deque!");
            return null;
        }
        T p = sentinel.prev;

        p.prev.next = sentinel;
        sentinel.prev = p.prev;
        size--;
        return p;
    }

    public T get(int index) {
        if (size == 0) {
            System.out.println("no item in the deque!");
            return null;
        }

        T p = sentinel;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return p.next;
            }
            p = p.next;
        }
        return null;
    }

    /**
     * Same as get, but uses recursion.
     */
    public T getRecursive(int index) {
        if (size == 0) {
            System.out.println("no item in the deque!");
            return null;
        }

        T p = sentinel;
        if (index == 0) {
            return p.next;
        }
        return getRecursive(index - 1);
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0 ? true : false;
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
        T p = sentinel.next;
        while (p.next != sentinel) {
            System.out.print(p.item + " ");
        }
    }
}
