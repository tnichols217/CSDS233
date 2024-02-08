package csds233.tln32;

import java.util.*;

public class DemoBag {
    /**
     * Removes all of a certain element from a given bag
     * @param <T> type of elements in the bag
     * @param bag the bag to remove elements from
     * @param element the element to remove from the bag
     */
    public static <T> void removeAll(DualImplementationBag<T> bag, T element) {
        for (int i=bag.size()-1; i>=0; i--) {
            if (bag.get(i).equals(element)) {
                bag.remove(i);
            }
        }
    }

    /**
     * Retains all of a certain element from a given bag
     * @param <T> type of elements in the bag
     * @param bag the bag to remove elements
     * @param element the element to retain from the bag
     */
    public static <T> void retainAll(DualImplementationBag<T> bag, T element) {
        for (int i=bag.size()-1; i>=0; i--) {
            if (!bag.get(i).equals(element)) {
                bag.remove(i);
            }
        }
    }

    /**
     * Takes the merge of two bags,
     * containing all the items from both bags
     * (simply concatenates the two bags)
     * @param <T> type of elements in the two bags
     * @param bag1 first bag to merge
     * @param bag2 second bag to merge
     * @return A new bag containing the merge of the two bags.
     */
    public static <T> DualImplementationBag<T> union(DualImplementationBag<T> bag1, DualImplementationBag<T> bag2) {
        DualImplementationBag<T> merge = new DualImplementationBag<T>(bag1.getUseArrayList());
        for (T element: bag1.iterator()) {
            merge.add(element);
        }
        for (T element: bag2.iterator()) {
            merge.add(element);
        }
        return merge;
    }

    /**
     * Takes the union of two bags,
     * without doubling elements in both bags.
     * If a bag has multiple of occurrences of
     * the same element, then the resulting bag
     * will contain the higher amount of occurrences
     * amongst each bag.
     * @param <T> type of elements in the two bags
     * @param bag1 first bag to union
     * @param bag2 second bag to union
     * @return A new bag containing the union of the two bags.
     */
    public static <T> DualImplementationBag<T> setUnion(DualImplementationBag<T> bag1, DualImplementationBag<T> bag2) {
        Map<T, Integer> d1 = bag1.getFrequencyMap();
        Map<T, Integer> d2 = bag2.getFrequencyMap();
        Map<T, Integer> union = new HashMap<T, Integer>();

        for (T k: d1.keySet()) {
            union.put(k, d1.get(k));
        }

        for (T k: d2.keySet()) {
            boolean put = true;
            for (T l: union.keySet()) {
                if (k.equals(l)) {
                    Integer n = Math.max(union.get(k), d2.get(l));
                    union.put(k, n);
                    put = false;
                    break;
                }
            }
            if (put) {
                union.put(k, d2.get(k));
            }
        }

        return DualImplementationBag.fromFrequencymap(union, bag1.getUseArrayList());
    }

    /**
     * Takes the intersection of two bags.
     * If a bag has multiple of occurrences of
     * the same element, then the resulting bag
     * will contain the lesser amount of occurrences
     * amongst each bag.
     * @param <T> the type of the elements in the two bags
     * @param bag1 the first bag to intersect
     * @param bag2 the second bag to intersect
     * @return A new bag containing the intersection of the two bags.
     */
    public static <T> DualImplementationBag<T> intersection(DualImplementationBag<T> bag1, DualImplementationBag<T> bag2) {
        Map<T, Integer> d1 = bag1.getFrequencyMap();
        Map<T, Integer> d2 = bag2.getFrequencyMap();
        Map<T, Integer> intersection = new HashMap<T, Integer>();

        for (T k: d1.keySet()) {
            for (T l: d2.keySet()) {
                if (k.equals(l)) {
                    intersection.put(k, Math.min(d1.get(k), d2.get(l)));
                    break;
                }
            }
        }

        return DualImplementationBag.fromFrequencymap(intersection, bag1.getUseArrayList());
    }

    /**
     * Takes the difference between two bags.
     * Any elements present in the second bag
     * will be eliminated one-for-one with
     * elements present in the first bag.
     * @param <T> the type of the elements in the two bags
     * @param bag1 the first bag to difference
     * @param bag2 the second bag to difference
     * @return A new bag containing the difference between the two bags.
     */
    public static <T> DualImplementationBag<T> difference(DualImplementationBag<T> bag1, DualImplementationBag<T> bag2) {
        Map<T, Integer> d1 = bag1.getFrequencyMap();
        Map<T, Integer> d2 = bag2.getFrequencyMap();
        Map<T, Integer> difference = new HashMap<T, Integer>();

        for (T k: d1.keySet()) {
            boolean put = true;
            for (T l: d2.keySet()) {
                if (k.equals(l)) {
                    Integer diff = d1.get(k) - d2.get(l);
                    difference.put(k, diff > 0 ? diff : 0);
                    put = false;
                    break;
                }
            }
            if (put) {
                difference.put(k, d1.get(k));
            }
        }

        return DualImplementationBag.fromFrequencymap(difference, bag1.getUseArrayList());
    }
}
