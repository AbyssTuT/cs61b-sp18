/**
 * @author tuhongchang
 * @version 1.0
 * @create 2022/6/9 下午11:04
 */
public class OffByN implements CharacterComparator {
    private int n;

    public OffByN(int n) {
        this.n = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == n;
    }
}
