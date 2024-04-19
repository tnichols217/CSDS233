package csds233.tln32;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RandomQuickSortTest {
    @Test
    public void quickSort() {
        {
            Integer[] arr = new Integer[] {1, 2, 3, 4, 5};
            RandomQuickSort.quickSort(arr);
            assertArrayEquals(
                new Integer[] {1, 2, 3, 4, 5},
                arr
            );
        }
        {
            Integer[] arr = new Integer[] {5, 4, 3, 2, 1};
            RandomQuickSort.quickSort(arr);
            assertArrayEquals(
                new Integer[] {1, 2, 3, 4, 5},
                arr
            );
        }
        {
            Integer[] arr = new Integer[] {0, 0, 0, 0, 0};
            RandomQuickSort.quickSort(arr);
            assertArrayEquals(
                new Integer[] {0, 0, 0, 0, 0},
                arr
            );
        }
        {
            Integer[] arr = new Integer[] {-100, 100, -99, 99, -98, 98};
            RandomQuickSort.quickSort(arr);
            assertArrayEquals(
                new Integer[] {-100, -99, -98, 98, 99, 100},
                arr
            );
        }
    }
}