package csds233.tln32;

import java.util.*;

public class DualImplementationBag<T> {
    private boolean useArrayList;
    private List<T> internalList;

    /**
     * Construct a Dual Implementation Bag
     * A List with type T with the ability
     * to switch its internal working with
     * ArrayList or LinkedList.
     * @param useArrayList if true, use ArrayList, else use LinkedList.
     * @return An empty Dual Implementation Bag
     */
    public DualImplementationBag(boolean useArrayList) {
        this.useArrayList = useArrayList;
        this.internalList = this.useArrayList ? new ArrayList<T>() : new LinkedList<T>();
    }

    /**
     * Set the internal list method
     * @param useArrayList if true, use ArrayList, else use LinkedList.
     */
    public void setUseArrayList(boolean useArrayList) {
        this.useArrayList = useArrayList;
        this.internalList = this.useArrayList ? new ArrayList<T>(this.internalList) : new LinkedList<T>(this.internalList);
    }

    /**
     * Get the internal list method
     * @return a boolean indicating whether the internal list is using ArrayList or LinkedList
     */
    public boolean getUseArrayList() {
        return this.useArrayList;
    }

    /**
     * Adds an element to the list
     * @param element the element to add
     * @return true if the element was added
     */
    public boolean add(T element) {
        return this.internalList.add(element);
    }

    /**
     * Removes an element from the list
     * @param element the element to remove
     * @return true if the element was removed
     */
    public boolean remove(T element) {
        return this.internalList.remove(element);
    }

    /**
     * Removes an element from the list at a given index
     * @param index the index of the element to remove
     * @return true if the element was removed
     */
    public boolean remove(int index) {
        return (this.internalList.remove(index)) != null;
    }

    /**
     * Check if the list contains an element
     * @param element The element to search for
     * @return true if the list contains the element
     */
    public boolean contains(T element) {
        for (T t : this.internalList) {
            if (t.equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the list is empty
     * @return true if the list is empty
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Gets the size of the list
     * @return the size of the list
     */
    public int size() {
        int out = 0;
        for (Iterator<T> iter = this.internalList.iterator(); iter.hasNext(); iter.next()) {
            out++;
        }
        return out;
    }

    /**
     * Counts the number of occurrences of an element in the list
     * @param element the element to search for
     * @return the number of occurrences of the element in
     */
    public int getFrequencyOf(T element) {
        int out = 0;
        for (T t : this.internalList) {
            if (t.equals(element)) {
                out++;
            }
        }
        return out;
    }

    /**
     * Gets an element from the list at an index
     * @param index the index of the element to get
     * @return the element from the list
     */
    public T get(int index) {
        return this.internalList.get(index);
    }

    /**
     * Creates an iterable over the list
     * @return an iterable over the list
     */
    public Iterable<T> iterator() {
        return () -> this.internalList.iterator();
    }

    /**
     * Gets the frequencies of each unique element of the list
     * @return a Map containing the frequencies of each unique element
     */
    public Map<T, Integer> getFrequencyMap() {
        Map<T, Integer> out = new HashMap<T, Integer>();
        for (T t : this.internalList) {
            boolean put = false;
            for (T k: out.keySet()) {
                if (t.equals(k)) {
                    out.put(k, out.get(k) + 1);
                    put = true;
                    break;
                }
            }
            if (!put) {
                out.put(t, 1);
            }
        }
        return out;
    }

    /**
     * Creates a Dual Implementation Bag from a frequency map
     * @param <T> Type of the elements in the list
     * @param freq Frequency map
     * @param useArrayList Type of internal list to use
     * @return A new Dual Implementation Bag
     */
    public static <T> DualImplementationBag<T> fromFrequencymap(Map<T, Integer> freq, boolean useArrayList) {
        DualImplementationBag<T> out = new DualImplementationBag<T>(useArrayList);
        for (T k: freq.keySet()) {
            for (int i=0; i<freq.get(k); i++) {
                out.add(k);
            }
        }
        return out;
    }
}