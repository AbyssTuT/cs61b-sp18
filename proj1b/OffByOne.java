/**
 * @author tuhongchang
 * @version 1.0
 * @create 2022/6/9 下午10:26
 */
public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x-y) == 1;
    }
}
