package csds233.tln32;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LinkedListTest {
    @Test
    public void construct() {
        LinkedList list = new LinkedList();
        Assertions.assertNotNull(list);
    }

    @Test
    public void add() {
        LinkedList list = new LinkedList();
        list.add(1);
        Assertions.assertEquals(1, list.remove(0));
        list.add(1);
        list.add(2);
        list.add(3);
        Assertions.assertEquals(1, list.remove(0));
        Assertions.assertEquals(2, list.remove(0));
        Assertions.assertEquals(3, list.remove(0));
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        for (int i = 0; i < 1000; i++) {
            Assertions.assertEquals(i, list.remove(0));
        }
    }

    @Test
    public void addIndex() {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(3);
        list.add(2, 1);
        Assertions.assertEquals(1, list.remove(0));
        Assertions.assertEquals(2, list.remove(0));
        Assertions.assertEquals(3, list.remove(0));
        try {
            list.add(1, -1);
            Assertions.fail();
        } catch (IndexOutOfBoundsException e) {
        } catch (Exception e) {
            Assertions.fail();
        }
        for (int i = 0; i < 500; i++) {
            list.add(i * 2 + 1);
        }
        for (int i = 0; i < 500; i++) {
            list.add(i * 2, i * 2);
        }
        for (int i = 0; i < 1000; i++) {
            Assertions.assertEquals(i, list.remove(0));
        }
    }

    @Test
    public void indexOf() {
        LinkedList list = new LinkedList();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        for (int i = 0; i < 1000; i++) {
            Assertions.assertEquals(i, list.indexOf(i));
        }
        Assertions.assertEquals(-1, list.indexOf(1001));
        Assertions.assertEquals(-1, list.indexOf(-1));
    }

    @Test
    public void remove() {
        LinkedList list = new LinkedList();
        for (int i = 0; i < 1000; i++) {
            list.add(i + 1);
        }
        for (int i = 0; i < 1000; i++) {
            Assertions.assertEquals(i + 1, list.remove(0));
        }
        for (int i = 0; i < 1000; i++) {
            list.add(i + 1);
        }
        for (int i = 999; i >= 0; i--) {
            Assertions.assertEquals(i + 1, list.remove(i));
        }
    }

    @Test
    public void removeValue() {
        LinkedList list = new LinkedList();
        for (int i = 0; i < 1000; i++) {
            list.add(i + 1);
        }
        for (int i = 0; i < 1000; i++) {
            list.removeValue(i+1);
        }
        try {
            list.remove(0);
            Assertions.fail();
        } catch (IndexOutOfBoundsException e) {
        } catch (Exception e) {
            Assertions.fail();
        }
        for (int i = 0; i < 1000; i++) {
            list.add(i + 1);
        }
        for (int i = 999; i >= 0; i--) {
            list.removeValue(i+1);
        }
        try {
            list.remove(0);
            Assertions.fail();
        } catch (IndexOutOfBoundsException e) {
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    public void removeAll() {
        LinkedList list = new LinkedList();
        for (int i = 0; i < 1000; i++) {
            list.add(i + 1);
            list.add(i + 1);
        }
        for (int i = 0; i < 1000; i++) {
            list.removeAll(i+1);
        }
        try {
            list.remove(0);
            Assertions.fail();
        } catch (IndexOutOfBoundsException e) {
        } catch (Exception e) {
            Assertions.fail();
        }
        for (int i = 0; i < 1000; i++) {
            list.add(i + 1);
            list.add(i + 1);
        }
        for (int i = 999; i >= 0; i--) {
            list.removeAll(i+1);
        }
        try {
            System.out.println(list.remove(0));
            System.out.println(list.remove(0));
            Assertions.fail();
        } catch (IndexOutOfBoundsException e) {
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    public void mean() {
        LinkedList list = new LinkedList();
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j <= i; j++) {
                list.add(j);
            }
            Assertions.assertEquals((double) i / 2, list.mean());
            list = new LinkedList();
        }
    }

    @Test
    public void variance() {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(1);
        Assertions.assertEquals(0, list.variance());
        list = new LinkedList();
        for (int i : new int[]{1, 2, 2, 6, 8, 9, 24, 24, 57, 235, 346}) {
            list.add(i);
        }
        Assertions.assertEquals(13318, (int) list.variance());
    }

    @Test
    public void sublist() {
        LinkedList list = new LinkedList();
        for (int i = 0; i < 100; i++) {
            list.add(i + 1);
        }
        list = list.sublist(9, 21);
        for (int i = 10; i < 21; i++) {
            Assertions.assertEquals(i, list.remove(0));
        }
        try {
            list.remove(0);
            Assertions.fail();
        } catch (IndexOutOfBoundsException e) {
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    public void removeNoise() {
        LinkedList list = new LinkedList();
        for (int i : new int[]{-100, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 100}) {
            list.add(i);
        }
        list = list.removeNoise();
        for (int i = 0; i < 18; i++) {
            Assertions.assertEquals(5, list.remove(0));
        }
    }
}
