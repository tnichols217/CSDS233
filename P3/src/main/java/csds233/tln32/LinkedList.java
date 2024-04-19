package csds233.tln32;

public class LinkedList {
    /**
     * Private class Node for linked list objects
     */
    private class Node {
        /**
         * Value of the node
         */
        public int value;
        /**
         * The next node in the list
         */
        public Node next;
        /**
         * Constructs a new node with the given value
         * @param value the value of the node
         */
        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * The head of the linked list
     */
    private Node head;

    /**
     * Constructs a new empty LinkedList
     */
    public LinkedList() {
        head = null;
    }

    /**
     * Adds the given value to the end of the linked list
     * @param n is the value to be added
     */
    public void add(int n) {
        Node node = this.head;
        Node newNode = new Node(n);
        if (node == null) {
            this.head = newNode;
            return;
        }
        while (node.next != null) node = node.next;
        node.next = newNode;
    }

    /**
     * Adds the given value to the given index in the linked list
     * @param n is the value to be added
     * @param index is the index at which to add the value
     */
    public void add(int n, int index) {
        if (index < 0) throw new IndexOutOfBoundsException();
        Node node = this.head;
        Node newNode = new Node(n);
        
        if (node == null | index == 0) {
            newNode.next = this.head;
            this.head = newNode;
            return;
        }
        for (int i = 0; i < index - 1 && node.next != null; node = node.next, i++);
        newNode.next = node.next;
        node.next = newNode;
    }

    /**
     * Gets the index at the given value in the linked list
     * @param n is the value to be found
     * @return the index at which the value is found
     */
    public int indexOf(int n) {
        Node node = this.head;
        for (int i = 0; node != null; node = node.next, i++) if (node.value == n) return i;
        return -1;
    }

    /**
     * Removes the value at the given index in the linked list
     * @param index is the index of the value to be removed
     * @return the value that was removed
     */
    public int remove(int index) {
        if (index < 0) throw new IllegalArgumentException();
        Node node = this.head;
        if (node == null) throw new IndexOutOfBoundsException();
        int value;
        if (index == 0 ) {
            value = head.value;
            this.head = this.head.next;
        } else {
            for (int i = 1; i < index && node.next != null; node = node.next, i++);
            value = node.next.value;
            node.next = node.next.next;
        }
        return value;
    }

    /**
     * Removes the first occurence given value from the linked list
     * @param n is the value to be removed
     */
    public void removeValue(int n) {
        Node node = this.head;
        if (node == null) return;
        for (; node.next != null; node = node.next) if (node.next.value == n) {
            node.next = node.next.next;
            return;
        }
        if (this.head.value == n) this.head = this.head.next;
    }

    /**
     * Removes all occurences of the given value from the linked list
     * @param n is the value to be removed
     */
    public void removeAll(int n) {
        Node node = this.head;
        while (node != null && node.next != null) {
            if (node.next.value == n) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        if (this.head != null && this.head.value == n) this.head = this.head.next;
    }

    /**
     * Gets the mean value of the linked list
     * @return the mean value of the linked list
     */
    public double mean() {
        int sum = 0;
        int i = 0;
        for (Node node = this.head; node != null; i++, sum += node.value, node = node.next);
        return ((double) sum) / i;
    }

    /**
     * Gets the variance of the linked list
     * @return the variance of the linked list
     */
    public double variance() {
        double mean = this.mean();
        int sum = 0;
        int i = 0;
        for (Node node = this.head; node != null; i++, sum += (node.value - mean) * (node.value - mean), node = node.next);
        return ((double) sum) / (i - 1);
    }

    /**
     * Creates a sublist of the linked list
     * @param lower is the lower bound of items in the sublist
     * @param upper is the upper bound of items in the sublist
     * @return A new LinkedList containing the sublist
     */
    public LinkedList sublist(int lower, int upper) {
        LinkedList list = new LinkedList();
        for (Node node = this.head; node != null; node = node.next) if (node.value > lower && node.value < upper) list.add(node.value);
        return list;
    }

    /**
     * Generates a sublist of the linked list that has only the items within 3 SD of the mean
     * @return a new LinkedList without the noise
     */
    public LinkedList removeNoise() {
        double mean = this.mean();
        double stdev = 3 * Math.sqrt(this.variance());
        LinkedList list = new LinkedList();
        for (Node node = this.head; node != null; node = node.next) if (Math.abs(node.value - mean) < stdev) list.add(node.value);
        return list;
    }
}
