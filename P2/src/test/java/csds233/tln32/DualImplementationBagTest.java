package csds233.tln32;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class DualImplementationBagTest {
    @Test
    public void constructor() {
        // Test constructing with Integers
        {
            DualImplementationBag<Integer> bag = new DualImplementationBag<Integer>(true);
            bag.add(1);
            bag.add(2);
            Assertions.assertEquals(2, bag.size());
        }
        // Test constructing with Strings
        {
            DualImplementationBag<String> bag = new DualImplementationBag<String>(false);
            bag.add("one");
            bag.add("two");
            bag.add("three");
            Assertions.assertEquals(3, bag.size());
        }
        // Test constructing with Integer arrays
        {
            DualImplementationBag<Integer[]> bag = new DualImplementationBag<Integer[]>(false);
            bag.add(new Integer[]{1,2,3});
            bag.add(new Integer[]{4,5,6});
            bag.add(new Integer[]{7,8,9});
            bag.add(new Integer[]{10,11,12});
            Assertions.assertEquals(4, bag.size());
        }
    }

    @Test
    public void setUseArrayList() {
        // Test constructing starting with ArrayLists
        {
            DualImplementationBag<Integer> bag = new DualImplementationBag<Integer>(true);
            bag.add(1);
            bag.add(2);
            Assertions.assertEquals(2, bag.size());
            Assertions.assertEquals(1, bag.get(0));
            Assertions.assertEquals(2, bag.get(1));
            Assertions.assertTrue(bag.getUseArrayList());
            bag.setUseArrayList(false);
            Assertions.assertEquals(2, bag.size());
            Assertions.assertEquals(1, bag.get(0));
            Assertions.assertEquals(2, bag.get(1));
            Assertions.assertFalse(bag.getUseArrayList());
        }
        // Test constructing starting with LinkedLists
        {
            DualImplementationBag<Integer> bag = new DualImplementationBag<Integer>(false);
            bag.add(1);
            bag.add(2);
            Assertions.assertEquals(2, bag.size());
            Assertions.assertEquals(1, bag.get(0));
            Assertions.assertEquals(2, bag.get(1));
            Assertions.assertFalse(bag.getUseArrayList());
            bag.setUseArrayList(true);
            Assertions.assertEquals(2, bag.size());
            Assertions.assertEquals(1, bag.get(0));
            Assertions.assertEquals(2, bag.get(1));
            Assertions.assertTrue(bag.getUseArrayList());
        }
    }

    @Test
    public void add() {
        // Test creating a list with the add function
        int[] testSizes = new int[]{0,1,100,1000,10000000};
        for (int i : testSizes) {
            // Test LinkedList implementation
            {
                DualImplementationBag<Integer> bag = new DualImplementationBag<Integer>(false);
                for (int j = 0; j < i; j++) {
                    Assertions.assertTrue(bag.add(j));
                }
                Assertions.assertEquals(i, bag.size());
            }
            // Test ArrayList implementation
            {
                DualImplementationBag<Integer> bag = new DualImplementationBag<Integer>(true);
                for (int j = 0; j < i; j++) {
                    Assertions.assertTrue(bag.add(j));
                }
                Assertions.assertEquals(i, bag.size());
            }
        }
    }

    @Test
    public void remove() {
        // Test Bulk inserts and removals
        {
            int[][] testSizes = new int[][]{{100,0},{100,1},{1000,100},{1000,1000},{1000,1001}};
            for (int[] i : testSizes) {
                // Test LinkedList implementation
                DualImplementationBag<Integer> bag1 = new DualImplementationBag<Integer>(false);
                // Test ArrayList implementation
                DualImplementationBag<Integer> bag2 = new DualImplementationBag<Integer>(true);
                // Test LinkedList implementation (by element)
                DualImplementationBag<String> bag3 = new DualImplementationBag<String>(false);
                // Test ArrayList implementation (by element)
                DualImplementationBag<String> bag4 = new DualImplementationBag<String>(true);

                // Bulk insert and remove tests
                for (int j = 0; j < i[0]; j++) {
                    Assertions.assertTrue(bag1.add(j));
                    Assertions.assertTrue(bag2.add(j));
                    Assertions.assertTrue(bag3.add(String.valueOf(j)));
                    Assertions.assertTrue(bag4.add(String.valueOf(j)));
                }
                for (int j = 0; j < i[1]; j++) {
                    try {
                        Assertions.assertEquals(bag1.get(0), j);
                        Assertions.assertTrue(bag1.remove(0));
                        Assertions.assertEquals(bag2.get(0), j);
                        Assertions.assertTrue(bag2.remove(0));
                        Assertions.assertEquals(bag3.get(0), String.valueOf(j));
                        Assertions.assertTrue(bag3.remove(bag3.get(0)));
                        Assertions.assertEquals(bag4.get(0), String.valueOf(j));
                        Assertions.assertTrue(bag4.remove(bag4.get(0)));
                    } catch (IndexOutOfBoundsException e) {
                        Assertions.assertTrue(i[1]>i[0]);
                    }
                }
                Assertions.assertEquals(Math.max(i[0]-i[1],0), bag1.size());
                Assertions.assertEquals(Math.max(i[0]-i[1],0), bag2.size());
                Assertions.assertEquals(Math.max(i[0]-i[1],0), bag3.size());
                Assertions.assertEquals(Math.max(i[0]-i[1],0), bag4.size());
            }
        }

        // Test random deletion
        int[] testSizes = new int[]{0,1,100,1000};
        for (int i : testSizes) {
            // Test LinkedList implementation
            {
                for (int j = 0; j < i; j++) {
                    DualImplementationBag<Integer> bag1 = new DualImplementationBag<Integer>(false);
                    DualImplementationBag<Integer> bag2 = new DualImplementationBag<Integer>(true);
                    for (int k = 0; k < i; k++) {
                        Assertions.assertTrue(bag1.add(k));
                        Assertions.assertTrue(bag2.add(k));
                    }
                    Assertions.assertTrue(bag1.remove(j));
                    Assertions.assertTrue(bag2.remove(j));
                    Assertions.assertEquals(i-1, bag1.size());
                    Assertions.assertEquals(i-1, bag2.size());
                    for (int k = 0; k < i-1; k++) {
                        Assertions.assertEquals(bag1.get(k), k>=j ? k+1 : k);
                        Assertions.assertEquals(bag2.get(k), k>=j ? k+1 : k);
                    }
                }
            }
        }
    }

    @Test
    public void contains() {
        // Test LinkedList implementation
        DualImplementationBag<Integer> bag1 = new DualImplementationBag<Integer>(false);
        // Test ArrayList implementation
        DualImplementationBag<Integer> bag2 = new DualImplementationBag<Integer>(true);
        Assertions.assertFalse(bag1.contains(1));
        Assertions.assertFalse(bag2.contains(1));
        for (int i=0; i<1000; i++) {
            Assertions.assertTrue(bag1.add(i));
            Assertions.assertTrue(bag2.add(i));
            Assertions.assertTrue(bag1.contains(i));
            Assertions.assertTrue(bag2.contains(i));
        }
    }

    @Test
    public void isEmpty() {
        // Test LinkedList implementation
        DualImplementationBag<Integer> bag1 = new DualImplementationBag<Integer>(false);
        // Test ArrayList implementation
        DualImplementationBag<Integer> bag2 = new DualImplementationBag<Integer>(true);
        Assertions.assertTrue(bag1.isEmpty());
        Assertions.assertTrue(bag2.isEmpty());
        for (int i=0; i<1000; i++) {
            Assertions.assertTrue(bag1.add(i));
            Assertions.assertTrue(bag2.add(i));
            Assertions.assertFalse(bag1.isEmpty());
            Assertions.assertFalse(bag2.isEmpty());
        }
    }

    @Test
    public void size() {
        for (int i=0; i<1000; i++) {
            // Test LinkedList implementation
            DualImplementationBag<Integer> bag1 = new DualImplementationBag<Integer>(false);
            // Test ArrayList implementation
            DualImplementationBag<Integer> bag2 = new DualImplementationBag<Integer>(true);
            for (int k=0; k<i; k++) {
                Assertions.assertTrue(bag1.add(k));
                Assertions.assertTrue(bag2.add(k));
                Assertions.assertEquals(bag1.size(), k+1);
                Assertions.assertEquals(bag2.size(), k+1);
            }
            Assertions.assertEquals(bag1.size(), i);
            Assertions.assertEquals(bag2.size(), i);
        }
    }

    @Test
    public void getFrequencyOf() {
        int[] testSizes = new int[]{0,1,100};
        int[] altGaps = new int[]{2,3,5,7,11};
        for (int i : testSizes) {
            for (int a : altGaps) {
                // Test LinkedList implementation
                DualImplementationBag<Integer> bag1 = new DualImplementationBag<Integer>(false);
                // Test ArrayList implementation
                DualImplementationBag<Integer> bag2 = new DualImplementationBag<Integer>(true);
                for (int j=1; j<=i; j++) {
                    Assertions.assertTrue(bag1.add(j%a == 0 ? a : 0));
                    Assertions.assertTrue(bag2.add(j%a == 0 ? a : 0));
                }
                Assertions.assertEquals(bag1.getFrequencyOf(a), i/a);
                Assertions.assertEquals(bag2.getFrequencyOf(a), i/a);
            }
        }
    }

    @Test
    public void get() {
        int[] testSizes = new int[]{0,1,100,1000};
        for (int i : testSizes) {
            // Test LinkedList implementation
            DualImplementationBag<Integer> bag1 = new DualImplementationBag<Integer>(false);
            // Test ArrayList implementation
            DualImplementationBag<Integer> bag2 = new DualImplementationBag<Integer>(true);
            for (int j=1; j<=i; j++) {
                Assertions.assertTrue(bag1.add(j));
                Assertions.assertTrue(bag2.add(j));
            }
            for (int j=0; j<i; j++) {
                Assertions.assertEquals(bag1.get(j), j+1);
                Assertions.assertEquals(bag2.get(j), j+1);
            }
        }
    }

    @Test
    public void iterator() {
        int[] testSizes = new int[]{0,1,100,1000};
        for (int i : testSizes) {
            // Test LinkedList implementation
            DualImplementationBag<Integer> bag1 = new DualImplementationBag<Integer>(false);
            // Test ArrayList implementation
            DualImplementationBag<Integer> bag2 = new DualImplementationBag<Integer>(true);
            for (int j=1; j<=i; j++) {
                Assertions.assertTrue(bag1.add(j));
                Assertions.assertTrue(bag2.add(j));
            }
            int count1 = 0;
            int count2 = 0;
            for (int j : bag1.iterator()) {
                count1 += j;
            }
            for (int j : bag2.iterator()) {
                count2 += j;
            }
            Assertions.assertEquals(count1, i*(i+1)/2);
            Assertions.assertEquals(count2, i*(i+1)/2);
        }
    }

    @Test
    public void getFrequencyMap() {
        int[] testSizes = new int[]{0,1,100,1000};
        for (int i : testSizes) {
            // Test LinkedList implementation
            DualImplementationBag<Integer> bag1 = new DualImplementationBag<Integer>(false);
            // Test ArrayList implementation
            DualImplementationBag<Integer> bag2 = new DualImplementationBag<Integer>(true);
            for (int j=1; j<=i; j++) {
                for (int k=0; k<j; k++) {
                    Assertions.assertTrue(bag1.add(j));
                    Assertions.assertTrue(bag2.add(j));
                }
            }
            Map<Integer, Integer> map1 = bag1.getFrequencyMap();
            Map<Integer, Integer> map2 = bag1.getFrequencyMap();
            for (int j=1; j<=i; j++) {
                Assertions.assertEquals(map1.get(j), j);
                Assertions.assertEquals(map2.get(j), j);
            }
        }
    }

    @Test
    public void fromFrequencymap() {
        int[] testSizes = new int[]{0,1,100};
        for (int i : testSizes) {
            // Test LinkedList implementation
            DualImplementationBag<Integer> bag1 = new DualImplementationBag<Integer>(false);
            // Test ArrayList implementation
            DualImplementationBag<Integer> bag2 = new DualImplementationBag<Integer>(true);
            for (int j=1; j<=i; j++) {
                for (int k=0; k<j; k++) {
                    Assertions.assertTrue(bag1.add(j));
                    Assertions.assertTrue(bag2.add(j));
                }
            }
            Map<Integer, Integer> map1 = bag1.getFrequencyMap();
            Map<Integer, Integer> map2 = bag1.getFrequencyMap();
            DualImplementationBag<Integer> bag1r = DualImplementationBag.fromFrequencymap(map1, false);
            DualImplementationBag<Integer> bag2r = DualImplementationBag.fromFrequencymap(map2, true);
            Assertions.assertEquals(map1, bag1r.getFrequencyMap());
            Assertions.assertEquals(map2, bag2r.getFrequencyMap());
        }
    }
}