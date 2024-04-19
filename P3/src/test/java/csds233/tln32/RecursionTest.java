package csds233.tln32;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecursionTest {
    @Test
    public void sumDigits() {
        Assertions.assertEquals(1, Recursion.sumDigits(1));
        Assertions.assertEquals(1, Recursion.sumDigits(10));
        Assertions.assertEquals(1, Recursion.sumDigits(100));
        Assertions.assertEquals(1, Recursion.sumDigits(1000));
        Assertions.assertEquals(1, Recursion.sumDigits(10000));
        for (int i = 0; i < 1000000; i++) {
            Assertions.assertEquals(Iterative.sumDigits(i), Recursion.sumDigits(i));
        }
    }

    @Test
    public void gcd() {
        Assertions.assertEquals(1, Recursion.gcd(1, 1));
        Assertions.assertEquals(1, Recursion.gcd(1, 2));
        Assertions.assertEquals(2, Recursion.gcd(2, 2));
        Assertions.assertEquals(614, Recursion.gcd(24562456, 3462346));
        for (int i = 1; i < 1000; i++) {
            for (int j = 1; j < 1000; j++) {
                Assertions.assertEquals(Iterative.gcd(i, j), Recursion.gcd(i, j));
            }
        }
    }

    @Test
    public void isPalindrome() {
        Assertions.assertTrue(Recursion.isPalindrome("121"));
        Assertions.assertTrue(Recursion.isPalindrome("1221"));
        Assertions.assertTrue(Recursion.isPalindrome("12321"));
        Assertions.assertTrue(Recursion.isPalindrome("racecar"));
        Assertions.assertTrue(Recursion.isPalindrome(""));
        Assertions.assertTrue(Recursion.isPalindrome("qwertyuiopoiuytrewq"));
        for (int i = 0; i < 1000000; i++) {
            Assertions.assertEquals(Iterative.isPalindrome(String.valueOf(i)), Recursion.isPalindrome(String.valueOf(i)));
        }
    }

    @Test
    public void swapNodesInPairs() {
        Assertions.assertNull(Recursion.swapNodesInPairs(null));
        Node a = new Node(1);
        Node b = new Node(2);
        a.next = b;
        a = Recursion.swapNodesInPairs(a);
        Assertions.assertEquals(2, a.value);
        Assertions.assertEquals(1, a.next.value);
        Node c = new Node(0);
        for (int i = 0; i < 1000; i++) {
            Node n = new Node(i);
            n.next = c;
            c = n;
        }
        c = Recursion.swapNodesInPairs(c);
        for (int i = 999; i >= 0; i--) {
            Assertions.assertEquals(i-2*(i/2) == 0 ? i + 1 : i - 1, c.value);
            c = c.next;
        }
    }

    @Test
    public void binomial() {
        Assertions.assertEquals(1, Recursion.binomial(1, 1));
        Assertions.assertEquals(10, Recursion.binomial(5, 3));
        for (int i = 1; i < 15; i++) {
            for (int j = i; j < 30; j++) {
                Assertions.assertEquals(Iterative.binomial(j, i), Recursion.binomial(j, i));
            }
        }
    }
}
