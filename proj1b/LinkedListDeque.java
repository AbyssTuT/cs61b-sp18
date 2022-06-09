import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * @author tuhongchang
 * @version 1.0
 * @create 2022/6/9 下午9:15
 */
public class LinkedListDeque<Item> extends LinkedList<Item> implements Deque<Item> {

    @Override
    public void printDeque() {
        System.out.println("dummy");
    }

    public Item getRecursive(int i) {
        return get(i);
    }

    @Override
    public Item removeFirst() {
        try {
            return super.removeFirst();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public Item removeLast() {
        try {
            return super.removeLast();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

}
