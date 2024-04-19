package csds233.tln32;

import java.util.Arrays;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;

public class Solution {

    public final static int[] compPoint = new int[] {233, 233};

    public static int[][] kClosest(int[][] points, int k) {
        return Arrays.stream(points).parallel()
            .map((int[] point) -> {
                int a = point[0] - compPoint[0];
                int b = point[1] - compPoint[1];
                return new pointDistance(a * a + b * b, point) ;
            })
            .collect(new kDistanceSelectionSortCollector(k));
    }

    public static class pointDistance implements Comparable<pointDistance> {
        public int d;
        public int[] point;
        public pointDistance(int d, int[] point) {
            this.d = d;
            this.point = point;
        }

        public int compareTo(pointDistance other) {
            return Integer.compare(this.d, other.d);
        }
    }

    public static class kDistanceSelectionSortCollector implements Collector<pointDistance, pointDistance[], int[][]> {
        private int k;
        public kDistanceSelectionSortCollector(int k) {
            this.k = k;
        }

        @Override
        public Supplier<pointDistance[]> supplier() {
            return () -> {
                pointDistance[] r = new pointDistance[k+1];
                r[0] = new pointDistance(1, null);
                return r;
            };

        }

        @Override
        public BiConsumer<pointDistance[], pointDistance> accumulator() {
            return (pointDistance[] accumulator, pointDistance b) -> {
                if (k < 1 || b == null) {
                    return;
                }
                if (accumulator[0].d > k) {
                    int max = accumulator[1].d;
                    int j = 1;
                    for (int i = j; i < accumulator.length; i++) {
                        if (accumulator[i].d > max) {
                            max = accumulator[i].d;
                            j = i;
                        }
                    }
                    accumulator[0].d = j;
                }
                if (accumulator[accumulator[0].d] == null) {
                    accumulator[accumulator[0].d] = b;
                    accumulator[0].d++;
                    return;
                }
                if (accumulator[accumulator[0].d].d > b.d) {
                    accumulator[accumulator[0].d] = b;
                    accumulator[0].d = k+1;
                    return;
                }
            };
        }

        @Override
        public BinaryOperator<pointDistance[]> combiner() {
            return (left, right) -> {
                BiConsumer<pointDistance[], pointDistance> merger = accumulator();
                for (int j = 1; j < right.length; j++) {
                    merger.accept(left, right[j]);
                }
                return left;
            };
        }

        @Override
        public Function<pointDistance[], int[][]> finisher() {
            return (pointDistance[] accumulator) -> {
                int[][] out = new int[k][];
                for (int i = 1; i < accumulator.length && accumulator[i] != null; i++) {
                    out[i-1] = accumulator[i].point;
                }
                return out;
            };
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Set.of(Collector.Characteristics.UNORDERED);
        }
    }
}
