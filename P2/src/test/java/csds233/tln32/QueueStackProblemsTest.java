package csds233.tln32;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QueueStackProblemsTest {
    public int[] testSizes = new int[]{10, 100, 1000, 10000};

    @Test
    public void testEvaluatePostFix() {
        Assertions.assertEquals(53, QueueStackProblems.evaluatePostFix("27 3 9 * + 1 -"));
        Assertions.assertEquals(2, QueueStackProblems.evaluatePostFix("1 1 +"));
        for (int s : testSizes) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s; i++) {
                sb.append(i).append(" ");
            }
            for (int i = 1; i < s; i++) {
                sb.append("+ ");
            }
            Assertions.assertEquals(s*(s-1)/2, QueueStackProblems.evaluatePostFix(sb.toString()));
        }
    }

    @Test
    public void testReverseWords() {
        Assertions.assertEquals("olleh", QueueStackProblems.reverseWords("hello"));
        Assertions.assertEquals("dlrow olleh", QueueStackProblems.reverseWords("world hello"));
        Assertions.assertEquals("sihT si a .gnirts olleH !dlroW", QueueStackProblems.reverseWords("This is a string. Hello World!"));
    }

    @Test
    public void testReverseK() {
        for (int s: testSizes) {
            SuperDeque<Integer> deque = new SuperDeque<>();
            for (int i = 1; i <= s; i++) {
                deque.push(i);
            }
            QueueStackProblems.reverseK(deque, s/2);
            for (int i = 0; i < s/2; i++) {
                Assertions.assertEquals(s/2-i, deque.unqueue());
            }
            for (int i = 1; i <= s/2; i++) {
                Assertions.assertEquals(s/2+i, deque.unqueue());
            }
        }
    }

    @Test
    public void testPlayGame() {
        Assertions.assertEquals(1, QueueStackProblems.playGame(1, 3));
        Assertions.assertEquals(5, QueueStackProblems.playGame(6, 3));
        Assertions.assertEquals(281, QueueStackProblems.playGame(1000, 5));
    }
}