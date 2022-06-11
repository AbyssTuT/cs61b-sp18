package synthesizer;
import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<Integer>(5);
        arb.enqueue(1);
        System.out.println(arb.capacity);
        System.out.println(arb.fillCount);
        arb.enqueue(12);
        arb.enqueue(3);
        arb.enqueue(6);
        System.out.println(arb.fillCount);
        System.out.println(arb.dequeue());
        System.out.println(arb.dequeue());
        System.out.println(arb.dequeue());
        System.out.println(arb.peek());
        arb.enqueue(6);
        arb.enqueue(5);
        arb.enqueue(4);
        arb.enqueue(6);

        Iterator<Integer> iterator = arb.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
