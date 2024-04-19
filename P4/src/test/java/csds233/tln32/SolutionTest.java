package csds233.tln32;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SolutionTest {
    @Test
    void kClosest() {
        {
            int[][] points = Solution.kClosest(new int[][] {{234, 234}, {200, 200}, {100, 100}, {0, 0}, {50, 0}, {5000, 5001}}, 5);
            int[][] expected ={{234, 234}, {200, 200}, {100, 100}, {0, 0}, {50, 0}};
            e: for (int[] i : expected) {
                for (int[] j : points) {
                    if (i[0] == j[0] && i[1] == j[1]) {
                        continue e;
                    }
                }
                fail();
            }
        }
        {
            int[][] points = Solution.kClosest(new int[][] {{0, 0}}, 1);
            int[][] expected ={{0, 0}};
            e: for (int[] i : expected) {
                for (int[] j : points) {
                    if (i[0] == j[0] && i[1] == j[1]) {
                        continue e;
                    }
                }
                fail();
            }
        }
        {
            int[][] points = Solution.kClosest(new int[][] {{234, 234}, {200, 200}, {100, 100}, {0, 0}, {50, 0}, {5000, 5001}}, 0);
            int[][] expected ={};
            e: for (int[] i : expected) {
                for (int[] j : points) {
                    if (i[0] == j[0] && i[1] == j[1]) {
                        continue e;
                    }
                }
                fail();
            }
        }
        {
            int[][] points = Solution.kClosest(new int[][] {{234, 234}, {200, 200}, {100, 100}, {0, 0}, {50, 0}, {5000, 5001}}, 10);
            int[][] expected = {{234, 234}, {200, 200}, {100, 100}, {0, 0}, {50, 0}, {5000, 5001}};
            e: for (int[] i : expected) {
                for (int[] j : points) {
                    if (i[0] == j[0] && i[1] == j[1]) {
                        continue e;
                    }
                }
                fail();
            }
        }
    }
}
