package csds233.tln32;

import java.util.Random;

public class RandomQuickSort {
    private static Random r = new Random();
    public static <T extends Comparable<?super T>> void quickSort(T[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    public static <T extends Comparable<?super T>> void quickSort(T[] nums, int first, int last) {
        if (first >= last) {
            return;
        }
        if (first + 1 == last) {
            if (nums[first].compareTo(nums[last]) > 0) {
                T swap = nums[first];
                nums[first] = nums[last];
                nums[last] = swap;
            }
            return;
        }
        int pivot = r.nextInt(first, last + 1);
        T swap = nums[last];
        nums[last] = nums[pivot];
        nums[pivot] = swap;

        int left = first;
        for (int i = first; i < last; i++) {
            if (nums[i].compareTo(nums[last]) <= 0) {
                swap = nums[left];
                nums[left] = nums[i];
                nums[i] = swap;
                left++;
            }
        }

        swap = nums[left];
        nums[left] = nums[last];
        nums[last] = swap;
        quickSort(nums, first, left - 1);
        quickSort(nums, left, last);
    }
}
