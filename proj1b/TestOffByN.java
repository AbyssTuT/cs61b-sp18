/**
 * @author tuhongchang
 * @version 1.0
 * @create 2022/6/9 下午11:04
 */
import org.junit.Test;
import static org.junit.Assert.*;
public class TestOffByN {
    static CharacterComparator offByN = new OffByN(5);
    @Test
    public void equalCharsTest(){
        assertTrue(offByN.equalChars('a','f'));
        assertTrue(offByN.equalChars('b','g'));
        assertFalse(offByN.equalChars('a','c'));
    }
}
