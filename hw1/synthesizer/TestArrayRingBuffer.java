package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        assertTrue(arb.isEmpty());
        arb.enqueue(1);
        arb.enqueue(7);
        arb.enqueue(3);
        assertEquals(arb.fillCount,3);
        assertFalse(arb.isEmpty());
        int item = arb.dequeue();
        assertEquals(item,1);
        item = arb.dequeue();
        assertEquals(item,7);
        item = arb.dequeue();
        assertEquals(item,3);
        assertTrue(arb.isEmpty());


        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        for (Integer n : arb) {
            System.out.println(n);
        }


    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
