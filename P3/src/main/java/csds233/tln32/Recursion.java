package csds233.tln32;

public class Recursion {
    /**
     * Sums all the digits in a base 10 number
     * @param n is the number of which to sum the digits
     * @return the sum of the digits in the number
     */
    public static int sumDigits(int n) {
        return n == 0 ? 0 : n % 10 + sumDigits(n / 10);
    }

    /**
     * Find the greatest common divisor of two numbers
     * @param a
     * @param b
     * @return the greatest common divisor of a and b
     */
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    /**
     * Finds if a string is a palindrome (reverse of itself is the same as itself)
     * @param s is the string to check
     * @return a boolean indicating whether the string is a palindrome
     */
    public static boolean isPalindrome(String s) {
        if (s.length() < 2) {
            return true;
        }
        int end = s.length() - 1;
        if (s.charAt(0) != s.charAt(end)) {
            return false;
        }
        return isPalindrome(s.substring(1, end));
    }

    /**
     * Swaps every other node with the node immediately after it
     * @param head is the head of the linked list of which to swap every other node
     * @return the same linked list with every other node swapped with the node immediately after it
     */
    public static Node swapNodesInPairs(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node out = head.next;
        head.next = swapNodesInPairs(out.next);
        out.next = head;
        return out;
    }

    /**
     * Calculates the binomial choose of n and k
     * @param n
     * @param k
     * @return an int representing nCk
     */
    public static int binomial(int n, int k) {
        if (k == 1) {
            return n;
        }
        if (n < 0 || k < 0 || k > n) {
            throw new IllegalArgumentException();
        }
        return binomial(n - 1, k - 1) * n / k;
    }
}