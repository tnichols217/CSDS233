package csds233.tln32;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SuperDequeTest {
    public int[] testSizes = new int[]{0, 1, 10, 100, 1000, 10000};
    @Test
    public void testSuperDeque() {
        SuperDeque<Integer> deque = new SuperDeque<>();
        Assertions.assertTrue(deque.isEmpty());
    }

    @Test
    public void testDelIsEmpty() {
        for (int s: testSizes) {
            // Test removing first element until empty
            SuperDeque<Integer> deque1 = new SuperDeque<>();
            // Test removing last element until empty
            SuperDeque<Integer> deque2 = new SuperDeque<>();
            for (int i = 0; i < s; i++) {
                deque1.push(i);
                deque2.push(i);
            }
            // Ensure attempting to remove out of bounds element returns null
            Assertions.assertNull(deque1.del(s+1));
            Assertions.assertNull(deque2.del(s+1));
            for (int i = 0; i < s; i++) {
                Assertions.assertEquals(i, deque1.del(0));
            }
            for (int i = s-1; i >= 0; i--) {
                Assertions.assertEquals(i, deque2.del(i));
            }
            // Ensure attempting to remove non-existent element returns null
            Assertions.assertNull(deque1.del(0));
            Assertions.assertNull(deque2.del(0));
            Assertions.assertTrue(deque1.isEmpty());
            Assertions.assertTrue(deque2.isEmpty());
        }
    }

    @Test
    public void testPushPopPeek() {
        for (int s: testSizes) {
            SuperDeque<Integer> deque = new SuperDeque<>();
            for (int i = 0; i < s; i++) {
                deque.push(i);
            }
            for (int i = s-1; i >= 0; i--) {
                Assertions.assertEquals(i, deque.peek());
                Assertions.assertEquals(i, deque.pop());
            }
            Assertions.assertNull(deque.pop());
            Assertions.assertTrue(deque.isEmpty());
        }
    }

    @Test
    public void testEnqueueDequeueUnqueue() {
        for (int s: testSizes) {
            SuperDeque<Integer> deque1 = new SuperDeque<>();
            SuperDeque<Integer> deque2 = new SuperDeque<>();
            for (int i = 0; i < s; i++) {
                deque1.enqueue(i);
                deque2.enqueue(i);
            }
            for (int i = 0; i < s; i++) {
                Assertions.assertEquals(i, deque1.dequeue());
            }
            for (int i = s-1; i >= 0; i--) {
                Assertions.assertEquals(i, deque2.unqueue());
            }
            Assertions.assertNull(deque1.dequeue());
            Assertions.assertNull(deque2.unqueue());
            Assertions.assertTrue(deque1.isEmpty());
            Assertions.assertTrue(deque2.isEmpty());
        }
    }

    @Test
    public void testToString() {
        for (int s: testSizes) {
            SuperDeque<Integer> deque = new SuperDeque<>();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s - 1; i++) {
                deque.push(i);
                sb.append(i).append(", ");
            }
            deque.push(s);
            sb.append(s);
            Assertions.assertEquals(sb.toString(), deque.toString());
        }
    }
}
