package csds233.tln32;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DemoBagTest {
    @Test
    public void removeAll() {
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
            for (int j=i; j>0; j--) {
                Assertions.assertEquals(bag1.size(), j*(j+1)/2);
                Assertions.assertEquals(bag2.size(), j*(j+1)/2);
                DemoBag.removeAll(bag1, j);
                DemoBag.removeAll(bag2, j);
            }
        }
    }

    @Test
    public void retainAll() {
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
            Assertions.assertEquals(bag1.size(), i*(i+1)/2);
            Assertions.assertEquals(bag2.size(), i*(i+1)/2);
            DemoBag.retainAll(bag1, i);
            DemoBag.retainAll(bag2, i);
            Assertions.assertEquals(bag1.size(), i);
            Assertions.assertEquals(bag2.size(), i);
        }
    }

    @Test
    public void union() {
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
            Assertions.assertEquals(bag1.size(), i*(i+1)/2);
            Assertions.assertEquals(bag2.size(), i*(i+1)/2);
            DualImplementationBag<Integer> merge = DemoBag.union(bag1, bag2);
            Assertions.assertEquals(merge.size(), i*(i+1));
            for (int j=1; j<=i; j++) {
                Assertions.assertEquals(bag1.getFrequencyOf(j), j);
                Assertions.assertEquals(bag2.getFrequencyOf(j), j);
            }
        }
    }

    @Test
    public void setUnion() {
        int[] testSizes = new int[]{0,1,100};
        for (int i : testSizes) {
            {
                // Test LinkedList implementation
                DualImplementationBag<Integer> bag1 = new DualImplementationBag<Integer>(false);
                // Test ArrayList implementation
                DualImplementationBag<Integer> bag2 = new DualImplementationBag<Integer>(true);
                for (int j=1; j<=i; j++) {
                    Assertions.assertTrue(bag1.add(j));
                    Assertions.assertTrue(bag2.add(j+i));
                }
                Assertions.assertEquals(bag1.size(), i);
                Assertions.assertEquals(bag2.size(), i);
                DualImplementationBag<Integer> union = DemoBag.setUnion(bag1, bag2);
                Assertions.assertEquals(union.size(), 2*i);
            }
            {
                // Test LinkedList implementation
                DualImplementationBag<Integer> bag1 = new DualImplementationBag<Integer>(false);
                // Test ArrayList implementation
                DualImplementationBag<Integer> bag2 = new DualImplementationBag<Integer>(true);
                for (int j=1; j<=i; j++) {
                    Assertions.assertTrue(bag1.add(j));
                    Assertions.assertTrue(bag2.add(j));
                }
                Assertions.assertEquals(bag1.size(), i);
                Assertions.assertEquals(bag2.size(), i);
                DualImplementationBag<Integer> union = DemoBag.setUnion(bag1, bag2);
                Assertions.assertEquals(union.size(), i);
                for (int j=1; j<=i; j++) {
                    Assertions.assertEquals(union.getFrequencyOf(j), 1);
                }
            }
            {
                // Test LinkedList implementation
                DualImplementationBag<Integer> bag1 = new DualImplementationBag<Integer>(false);
                // Test ArrayList implementation
                DualImplementationBag<Integer> bag2 = new DualImplementationBag<Integer>(true);
                for (int j=1; j<=i; j++) {
                    Assertions.assertTrue(bag1.add(j));
                    Assertions.assertTrue(bag1.add(j));
                    Assertions.assertTrue(bag2.add(j));
                }
                Assertions.assertEquals(bag1.size(), 2*i);
                Assertions.assertEquals(bag2.size(), i);
                DualImplementationBag<Integer> union = DemoBag.setUnion(bag1, bag2);
                Assertions.assertEquals(union.size(), 2*i);
                for (int j=1; j<=i; j++) {
                    Assertions.assertEquals(union.getFrequencyOf(j), 2);
                }
            }
        }
    }

    @Test
    public void intersection() {
        int[] testSizes = new int[]{0,2,100};
        for (int i : testSizes) {
            {
                // Test LinkedList implementation
                DualImplementationBag<Integer> bag1 = new DualImplementationBag<Integer>(false);
                // Test ArrayList implementation
                DualImplementationBag<Integer> bag2 = new DualImplementationBag<Integer>(true);
                for (int j=1; j<=i; j++) {
                    Assertions.assertTrue(bag1.add(j));
                    Assertions.assertTrue(bag2.add(j+i/2));
                }
                Assertions.assertEquals(bag1.size(), i);
                Assertions.assertEquals(bag2.size(), i);
                DualImplementationBag<Integer> intersection = DemoBag.intersection(bag1, bag2);
                Assertions.assertEquals(intersection.size(), i/2);
            }
            {
                // Test LinkedList implementation
                DualImplementationBag<Integer> bag1 = new DualImplementationBag<Integer>(false);
                // Test ArrayList implementation
                DualImplementationBag<Integer> bag2 = new DualImplementationBag<Integer>(true);
                for (int j=1; j<=i; j++) {
                    Assertions.assertTrue(bag1.add(j));
                    Assertions.assertTrue(bag2.add(j));
                }
                Assertions.assertEquals(bag1.size(), i);
                Assertions.assertEquals(bag2.size(), i);
                DualImplementationBag<Integer> intersection = DemoBag.intersection(bag1, bag2);
                Assertions.assertEquals(intersection.size(), i);
                for (int j=1; j<=i; j++) {
                    Assertions.assertEquals(intersection.getFrequencyOf(j), 1);
                }
            }
            {
                // Test LinkedList implementation
                DualImplementationBag<Integer> bag1 = new DualImplementationBag<Integer>(false);
                // Test ArrayList implementation
                DualImplementationBag<Integer> bag2 = new DualImplementationBag<Integer>(true);
                for (int j=1; j<=i; j++) {
                    Assertions.assertTrue(bag1.add(j));
                    Assertions.assertTrue(bag1.add(j));
                    Assertions.assertTrue(bag2.add(j));
                }
                Assertions.assertEquals(bag1.size(), 2*i);
                Assertions.assertEquals(bag2.size(), i);
                DualImplementationBag<Integer> intersection = DemoBag.intersection(bag1, bag2);
                Assertions.assertEquals(intersection.size(), i);
                for (int j=1; j<=i; j++) {
                    Assertions.assertEquals(intersection.getFrequencyOf(j), 1);
                }
            }
        }
    }

    @Test
    public void difference() {
        int[] testSizes = new int[]{0,2,100};
        for (int i : testSizes) {
            {
                // Test LinkedList implementation
                DualImplementationBag<Integer> bag1 = new DualImplementationBag<Integer>(false);
                // Test ArrayList implementation
                DualImplementationBag<Integer> bag2 = new DualImplementationBag<Integer>(true);
                for (int j=1; j<=i; j++) {
                    Assertions.assertTrue(bag1.add(j));
                    Assertions.assertTrue(bag2.add(j+i/2));
                }
                Assertions.assertEquals(bag1.size(), i);
                Assertions.assertEquals(bag2.size(), i);
                DualImplementationBag<Integer> difference = DemoBag.difference(bag1, bag2);
                Assertions.assertEquals(difference.size(), i/2);
            }
            {
                // Test LinkedList implementation
                DualImplementationBag<Integer> bag1 = new DualImplementationBag<Integer>(false);
                // Test ArrayList implementation
                DualImplementationBag<Integer> bag2 = new DualImplementationBag<Integer>(true);
                for (int j=1; j<=i; j++) {
                    Assertions.assertTrue(bag1.add(j));
                    Assertions.assertTrue(bag2.add(j));
                }
                Assertions.assertEquals(bag1.size(), i);
                Assertions.assertEquals(bag2.size(), i);
                DualImplementationBag<Integer> difference = DemoBag.difference(bag1, bag2);
                Assertions.assertEquals(difference.size(), 0);
            }
            {
                // Test LinkedList implementation
                DualImplementationBag<Integer> bag1 = new DualImplementationBag<Integer>(false);
                // Test ArrayList implementation
                DualImplementationBag<Integer> bag2 = new DualImplementationBag<Integer>(true);
                for (int j=1; j<=i; j++) {
                    Assertions.assertTrue(bag1.add(j));
                    Assertions.assertTrue(bag1.add(j));
                    Assertions.assertTrue(bag2.add(j));
                }
                Assertions.assertEquals(bag1.size(), 2*i);
                Assertions.assertEquals(bag2.size(), i);
                DualImplementationBag<Integer> difference = DemoBag.difference(bag1, bag2);
                Assertions.assertEquals(difference.size(), i);
                for (int j=1; j<=i; j++) {
                    Assertions.assertEquals(difference.getFrequencyOf(j), 1);
                }
            }
            {
                // Test LinkedList implementation
                DualImplementationBag<Integer> bag1 = new DualImplementationBag<Integer>(false);
                // Test ArrayList implementation
                DualImplementationBag<Integer> bag2 = new DualImplementationBag<Integer>(true);
                for (int j=1; j<=i; j++) {
                    Assertions.assertTrue(bag1.add(j));
                    Assertions.assertTrue(bag1.add(j));
                    Assertions.assertTrue(bag2.add(j));
                }
                Assertions.assertEquals(bag1.size(), 2*i);
                Assertions.assertEquals(bag2.size(), i);
                DualImplementationBag<Integer> difference = DemoBag.difference(bag2, bag1);
                Assertions.assertEquals(difference.size(), 0);
            }
        }
    }
}
