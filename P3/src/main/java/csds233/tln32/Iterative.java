package csds233.tln32;

public class Iterative {
    /**
     * Sums all the digits in a base 10 number
     * @param n is the number of which to sum the digits
     * @return the sum of the digits in the number
     */
    public static int sumDigits(int n) {
        int sum = 0;
        for (int i = n; i != 0; sum += i % 10, i /= 10);
        return sum;
    }

    /**
     * Find the greatest common divisor of two numbers
     * @param a
     * @param b
     * @return the greatest common divisor of a and b
     */
    public static int gcd(int a, int b) {
        int r = -1;
        for (a = a < b ? a ^ b ^ (b = a) : a ; r != 0; r = a % b, a = b, b = r);
        return a;
    }

    /**
     * Finds if a string is a palindrome (reverse of itself is the same as itself)
     * @param s is the string to check
     * @return a boolean indicating whether the string is a palindrome
     */
    public static boolean isPalindrome(String s) {
        for (int i = 0; i < s.length()/2; i++) if (s.charAt(i) != s.charAt(s.length() - i - 1)) return false;
        return true;
    }

    /**
     * Swaps every other node with the node immediately after it
     * @param head is the head of the linked list of which to swap every other node
     * @return the same linked list with every other node swapped with the node immediately after it
     */
    public static Node swapNodesInPairs(Node head) {
        throw new Error("Not implemented");
    }

    /**
     * Calculates the binomial choose of n and k
     * @param n
     * @param k
     * @return an int representing nCk
     */
    public static int binomial(int n, int k) {
        if (n < 0 || k < 0 || k > n) throw new IllegalArgumentException();
        k = k > n/2 ? n - k : k;
        if (k == 0) return 1;
        int out = 1;
        for (int i = 1, j = n-k+1; j <= n; out = out * j++ / i++);
        return out;
    }
}
