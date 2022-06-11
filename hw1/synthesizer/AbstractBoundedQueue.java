package synthesizer;

/**
 * @author tuhongchang
 * @version 1.0
 * @create 2022/6/11 上午8:23
 */
public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }
}
