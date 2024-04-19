package csds233.tln32;

public class ArrayList {
    /**
     * The data array
     */
    private int[] data;

    /**
     * The default size of the data array
     */
    public static final int DEFAULT_SIZE = 1;

    /**
     * The amount of items stored in the array
     */
    private int size = 0;

    /**
     * Constructs an empty array list with a default size of DEFAULT_SIZE
     */
    public ArrayList() {
        data = new int[DEFAULT_SIZE];
    }

    /**
     * Doubles the size of the internal data array
     */
    private void doubleSize() {
        int[] newData = new int[data.length * 2];
        for (int i = 0; i < data.length; newData[i] = data[i], i++);
        this.data = newData;
    }

    /**
     * Adds an item to the end of the array
     * @param n is the item to be added
     */
    public void add(int n) {
        if (data.length == size) doubleSize();
        data[size] = n;
        size++;
    }

    /**
     * Adds an item to index index of the array
     * @param n is the item to be added
     * @param index is the index at which to add the item
     */
    public void add(int n, int index) {
        index = Math.min(index, size);
        if (index < 0) throw new IllegalArgumentException();
        if (data.length == size) doubleSize();
        for (int i = size; i > index; data[i] = data[i - 1], i--);
        data[index] = n;
        size++;
    }

    /**
     * Gets the index of item n in the array
     * @param n is the item to be found
     * @return the index of item n in the array
     */
    public int indexOf(int n) {
        for (int i = 0; i < size; i++) if (data[i] == n) return i;
        return -1;
    }

    /**
     * Removes the item at index index of the array
     * @param index is the index of the item to be removed
     * @return the removed item
     */
    public int remove(int index) {
        index = Math.min(index, size);
        if (index < 0 || size == 0) throw new IllegalArgumentException();
        int out = data[index];
        for (int i = index; i < size - 1; data[i] = data[i + 1], i++);
        size--;
        return out;
    }

    /**
     * Removes the first occurrence of item n in the array
     * @param n is the item to be removed
     */
    public void removeValue(int n) {
        remove(indexOf(n));
    }

    /**
     * Removes all occurrences of item n in the array
     * @param n is the item to be removed
     */
    public void removeAll(int n) {
        for (int i = 0; i < size; i++) if (data[i] == n) remove(i);
    }

    /**
     * Finds the mean of the array
     * @return the mean of the array
     */
    public double mean() {
        int sum = 0;
        for (int i = 0; i < size; sum += data[i], i++);
        if (sum == 0) return 0;
        return ((double) sum) / size;
    }

    /**
     * Finds the variance of the array
     * @return the variance of the array
     */
    public double variance() {
        double mean = this.mean();
        int sum = 0;
        for (int i = 0; i < size; sum += (data[i] - mean) * (data[i] - mean), i++);
        return ((double) sum) / (size-1);
    }

    /**
     * Generates a sublist of the array
     * @param lower is the lower bound of items in the sublist
     * @param upper is the upper bound of items in the sublist
     * @return a new ArrayList containing the sublist from lower to upper, not including upper
     */
    public ArrayList sublist(int lower, int upper) {
        ArrayList out = new ArrayList();
        for (int i = 0; i < size; i++) if (data[i] > lower && data[i] < upper) {
            out.add(data[i]);
        } 
        return out;
    }

    /**
     * Generates a sublist of the array that has only the items within 3 SD of the mean
     * @return a new ArrayList containing the sublist without noise
     */
    public ArrayList removeNoise() {
        double stdev = 3 * Math.sqrt(this.variance());
        double mean = this.mean();
        ArrayList out = new ArrayList();
        for (int i = 0; i < size; i++) if (Math.abs(data[i] - mean) < stdev) out.add(data[i]);
        return out;
    }
}
