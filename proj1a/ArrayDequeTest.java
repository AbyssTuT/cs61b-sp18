import org.junit.Test;

/**
 * @author tuhongchang
 * @version 1.0
 * @create 2022/6/9 上午1:41
 */
public class ArrayDequeTest {
    @Test
    public void emptyaddTest() {
        ArrayDeque<String> ad = new ArrayDeque<>();
        System.out.println("Test isEmpty = " + ad.isEmpty());

        ad.addFirst("firstItem");
        System.out.println("ArrayDeque size = " + ad.size());

        ad.addLast("lastItem");
        System.out.println("ArrayDeque size = " + ad.size());

        ad.printDeque();

        String firstItem = ad.removeFirst();
        System.out.println("removed first item = " + firstItem);

        String lastItem = ad.removeLast();
        System.out.println("removed last item =" + lastItem);
    }

    @Test
    public void resizeTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.addLast(1);
        ad.addLast(2);
        Integer integer = ad.get(1);
        Integer integer1 = ad.removeLast();

        for (int i = 0; i < 16; i++) {
            ad.addLast(i);
        }
        System.out.println(ad.get(0));
        for (int i = -16; i < 0; i++) {
            ad.addFirst(i);
        }
        System.out.println(ad.size());

        for (int i = 0; i < 30; i++) {
            ad.removeFirst();
        }

        System.out.println(ad.size());
    }
}
