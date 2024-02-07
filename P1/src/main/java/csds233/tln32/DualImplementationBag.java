package csds233.tln32;

import java.util.Arrays;
import java.util.Scanner;

public class DualImplementationBag<T> {
    private boolean useArrayList;

    public DualImplementationBag(boolean useArrayList) {
        this.useArrayList = useArrayList;
    }

    public void setUseArrayList(boolean useArrayList) {
        this.useArrayList = useArrayList;
        //TODO implement
    }

    public boolean add(T element) {
        //TODO implement
        return false;
    }

    public boolean remove(T element) {
        //TODO implement
        return false;
    }

    public boolean contains(T element) {
        //TODO implement
        return false;
    }

    public boolean isEmpty() {
        //TODO implement
        return false;
    }

    public int size() {
        //TODO implement
        return 0;
    }

    public int getFrequencyOf(T element) {
        //TODO implement
        return 0;
    }

    public T get(int index) {
        //TODO implement
        return null;
    }
}