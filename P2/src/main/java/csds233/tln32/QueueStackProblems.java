package csds233.tln32;

import java.util.*;

public class QueueStackProblems {
    /**
     * Evaluates a postfix expression
     * @param postfix is a space delimited string with a postfix expression
     * @return The result of evaluating the postfix expression, or 0 if the expression is blank
     */
    public static int evaluatePostFix(String postfix) {
        SuperDeque<Integer> dq = new SuperDeque<>();
        // For every element in the SuperDeque, apply operators or move it to the new queue
        postfix = postfix.trim();
        if (postfix.equals("")) {
            return 0;
        }
        for (String s : postfix.split(" ")) {
            switch (s) {
                case "+":
                    dq.push(dq.pop() + dq.pop());
                    break;
                case "-":
                    dq.push(- dq.pop() + dq.pop());
                    break;
                case "*":
                    dq.push(dq.pop() * dq.pop());
                    break;
                case "/":
                    dq.push((1 / dq.pop()) * dq.pop());
                    break;
                default:
                    dq.push(Integer.parseInt(s));
                    break;
            }
        }
        return dq.pop();
    }

    /**
     * Reverses the letters within each space delimited word in a string
     * @param s is a space delimited string with words to reverse
     * @return The word-reversed string
     */
    public static String reverseWords(String s) {
        return Arrays
                .stream(s.split(" "))
                .map(st -> (String) new StringBuilder(st).reverse().toString())
                .collect(
                    StringBuilder::new,
                    (acc, el) -> acc.append(el).append(" "),
                    StringBuilder::append)
                .toString()
                .trim();
    }

    /**
     * Reverses the first k elements of a SuperDeque
     * @param <E> is the type of elements in the SuperDeque
     * @param dq is the SuperDeque to reverse
     * @param k is the number of elements to reverse
     * @return The k-reversed SuperDeque
     */
    public static <E> SuperDeque<E> reverseK(SuperDeque<E> dq, int k) {
        SuperDeque<E> dqn = new SuperDeque<>();
        // Get the first k elements of the SuperDeque and insert them into the new SuperDeque
        for (int i = 0; i < k && !dq.isEmpty(); i++) {
            dqn.enqueue(dq.unqueue());
        }
        // Reverse the elements in the new SuperDeque and insert them into the original SuperDeque
        while (!dqn.isEmpty()) {
            dq.enqueue(dqn.pop());
        }
        return dq;
    }

    /**
     * Repetitively removed an element offset away from the current position
     * until there is only one element left
     * @param n is the size of the initial array
     * @param offset is the offset between removals
     * @return The result of the removal operation
     */
    public static int playGame(int n, int offset) {
        if (n <= 0) {
            return -1;
        }
        SuperDeque<Integer> dq = new SuperDeque<>();
        // Construct the initial SuperDeque
        for (int i = 1; i <= n; i++) {
            dq.push(i);
        }
        int pos = 0;
        int s = n;
        // Remove an element from the initial SuperDeque until there is only one element left
        for (int i = 1; i < n; i++) {
            pos = (pos + offset) % s;
            s--;
            dq.del(pos);
        }
        return dq.pop();
    }
}
