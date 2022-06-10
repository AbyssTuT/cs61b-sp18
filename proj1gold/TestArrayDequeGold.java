import static org.junit.Assert.*;

import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void test() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ad = new ArrayDequeSolution<>();
        String message = "";
        for (int i = 0; i < 500; i++) {
            double n = StdRandom.uniform();
            if (sad.size() == 0) {
                int headOrBack = StdRandom.uniform(2);
                if (headOrBack == 0) {
                    message = message + "addFirst(" + i + ")\n";
                    sad.addFirst(i);
                    ad.addFirst(i);
                } else {
                    message = message + "addLast(" + i + ")\n";
                    sad.addLast(i);
                    ad.addLast(i);
                }
            } else {
                Integer expected = 0;
                Integer actual = 0;
                if (n < 0.3) {
                    message = message + "addLast(" + i + ")\n";
                    sad.addLast(i);
                    ad.addLast(i);
                } else if (n < 0.5 && sad.size() > 0) {
                    message = message + "removeFirst()\n";
                    expected = sad.removeFirst();
                    actual = ad.removeFirst();
                } else if (n < 0.7 && sad.size() > 0) {
                    message = message + "removeLast()\n";
                    expected = sad.removeLast();
                    actual = ad.removeLast();
                } else {
                    message = message + "addFirst(" + i + ")\n";
                    sad.addFirst(i);
                    ad.addFirst(i);
                }
                assertEquals(message, expected, actual);
            }
        }
    }
}
