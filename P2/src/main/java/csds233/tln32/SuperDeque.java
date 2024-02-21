package csds233.tln32;

public class SuperDeque<E> {
    /**
     * The index of the array that represents the front/top of the data structure
     */
    private int front;

    /**
     * The index of the array that represents the back/bottom of the data structure
     */
    private int back;

    /**
     * The array storing the data of the SuperDeque
     */
    private E[] dq;

    /**
     * Number of size increases
     */
    private int sizeInc = 0;

    /**
     * Indicates if the data structure is full
     */
    private boolean isFull = false;

    /**
     * The initial size of the SuperDeque to be set in the constructor
     */
    private static final int DEFAULT_CAP = 1;

    /**
     * Constructs a SuperDeque, which has the properties
     * of both a stack (first in, last out) and
     * a queue (first in, first out)
     * depending on which method is being called
     * @return a new SuperDeque object
     */
    @SuppressWarnings("unchecked")
    public SuperDeque() {
        this.dq = (E[]) new Object[DEFAULT_CAP];
        this.front = 0;
        this.back = 0;
    }

    /**
     * Resolves a circular index reference to an absolute index for the dq array
     * @param index is a circular index reference
     * @return The absolute index
     */
    private int resolveIndex(int index) {
        int size = this.getMaxSize();
        return ((index % size) + size) % size;
    }

    /**
     * Gets the element at circular index reference
     * @param index is a circular index reference
     * @return The element at the index
     */
    private E get(int index) {
        return this.dq[this.resolveIndex(index)];
    }

    /**
     * Sets an element at circular index reference
     * @param index is a circular index reference
     * @param element is the element to be set
     * @return the old element at the index
     */
    private E set(int index, E element) {
        int i = this.resolveIndex(index);
        E ret = this.dq[i];
        this.dq[i] = element;
        return ret;
    }

    /**
     * Deletes an element at index reference from the back of the array
     * and shifts all elements after it back one index
     * @param index is an index reference
     * @return the element that was deleted
     */
    public E del(int index) {
        if (index >= this.length() || index < 0) {
            return null;
        }
        int i = this.resolveIndex(this.back + index);
        E ret = this.dq[i];
        this.dq[i] = null;
        while (i != this.front) {
            this.set(i, this.get(i+1));
            i = this.resolveIndex(i+1);
        }
        this.front = this.resolveIndex(this.front - 1);
        this.isFull = false;
        return ret;
    }

    /**
     * SuperDeque acts as a stack and adds an element to the top of the SuperDeque
     * @param element
     */
    public void push(E element) {
        if (this.isFull) {
            this.doubleSize();
        }
        this.set(this.front, element);
        this.front = this.resolveIndex(this.front + 1);
        this.isFull = this.isEmpty();
    }

    /**
     * SuperDeque acts as a stack and removes an element from the top of the SuperDeque
     * @return The element at the front of the SuperDeque
     */
    public E pop() {
        if (this.isEmpty()) {
            return null;
        }
        this.isFull = false;
        int i = this.resolveIndex(this.front - 1);
        E ret = this.set(i, null);
        this.front = i;
        return ret;
    }

    /**
     * SuperDeque acts as a stack and returns the element at the top of the SuperDeque
     * @return The element at the front of the SuperDeque
     */
    public E peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.get(this.front - 1);
    }

    /**
     * SuperDeque acts as a queue and adds an element to the back of the SuperDeque
     * @param element The element to be added to the back of the SuperDeque
     */
    public void enqueue(E element) {
        if (this.isFull) {
            this.doubleSize();
        }
        int index = this.resolveIndex(this.back - 1);
        this.set(index, element);
        this.back = index;
        this.isFull = this.isEmpty();
    }

    /**
     * SuperDeque acts as a queue and removes an element from the front of the SuperDeque
     * @return The element at the front of the SuperDeque
     */
    public E dequeue() {
        return this.pop();
    }

    /**
     * SuperDeque acts as a queue and removes the element at the back of the SuperDeque
     * @return The element at the back of the SuperDeque
     */
    public E unqueue() {
        if (this.isEmpty()) {
            return null;
        }
        this.isFull = false;
        E ret = this.set(this.back, null);
        this.back = this.resolveIndex(this.back + 1);
        return ret;
    }

    /**
     * Gets the current storage capacity of the data structure
     * @return The current storage capacity of the data structure
     */
    private int getMaxSize() {
        return this.dq.length;
    }

    /**
     * Gets the current length of the data structure
     * @return The number of elements in the data structure
     */
    private int length() {
        if (this.isFull) {
            return this.getMaxSize();
        }
        if (this.isEmpty()) {
            return 0;
        }
        return this.resolveIndex(this.front - this.back);
    }

    /**
     * Checks if the data structure is empty
     * @return True if the data structure is empty
     */
    public boolean isEmpty() {
        return (this.front == this.back) && !this.isFull;
    }
    
    /**
     * Doubles the size of the internal data structure
     */
    @SuppressWarnings("unchecked")
    private void doubleSize() {
        this.sizeInc++;
        E[] temp = (E[]) new Object[DEFAULT_CAP * (int) Math.round(Math.pow(2, this.sizeInc))];
        // Takes all elements in the old array and add them to the new array
        int i = 0;
        for (; !this.isEmpty(); i++) {
            temp[i] = this.unqueue();
        }
        this.dq = temp;
        this.back = 0;
        this.front = this.resolveIndex(i);
        this.isFull = false;
    }

    /**
     * Returns a string representation of the data structure
     * @return A string respresentation of the data structure
     */
    public String toString() {
        if (this.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int end = this.resolveIndex(this.front - 1);
        // Loops through all elements in the data structure and adds the string representation of each element
        for (int i = this.back; i != end; i++) {
            sb.append(this.get(i)).append(", ");
        }
        sb.append(this.get(end));
        return sb.toString();
    }
}