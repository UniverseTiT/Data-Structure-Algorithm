/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author Tan Chee Fung
 */
public class SortedArrayList<C extends Comparable<C>> implements ListInterface<C>, Serializable {

    private C[] array;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 1000;

    public SortedArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public SortedArrayList(int initialCapacity) {
        numberOfEntries = 0;
        array = (C[]) new Comparable[initialCapacity];
    }

    @Override
    public boolean addEntry(C newEntry) {
        int i = 0;
        while (i < numberOfEntries && newEntry.compareTo(array[i]) > 0) {
            i++;
        }
        makeNode(i + 1);
        array[i] = newEntry;
        numberOfEntries++;
        return true;
    }

    @Override
    public boolean removeEntry(C anEntry) {
        boolean stop = false;
        int index = 0;
        while (index < numberOfEntries && !stop) {
            if (array[index].compareTo(anEntry) >= 0) {
                stop = true;
            } else {
                index++;
            }
        }
        if (index < numberOfEntries && array[index].compareTo(anEntry) == 0) {
            removeNode(index + 1);
            numberOfEntries--;
            return true;
        }
        return false;
    }

    @Override
    public boolean findEntry(C anEntry) {
        boolean found = false;
        for (int index = 0; !found && (index < numberOfEntries); index++) {
            if (anEntry.equals(array[index])) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public boolean amendEntry(C oldEntry, C newEntry) {
        boolean found = false;
        for (int index = 0; !found && (index < numberOfEntries); index++) {
            if (oldEntry.equals(array[index])) {
                array[index] = newEntry;
                found = true;
            }        
        }
        return found;
    }

    private void makeNode(int newPosition) {
        int newIndex = newPosition - 1;
        int lastIndex = numberOfEntries - 1;

        for (int index = lastIndex; index >= newIndex; index--) {
            array[index + 1] = array[index];
        }
    }

    private void removeNode(int givenPosition) {
        int removedIndex = givenPosition - 1;
        int lastIndex = numberOfEntries - 1;

        for (int index = removedIndex; index < lastIndex; index++) {
            array[index] = array[index + 1];
        }
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public C getEntry(int givenPosition) {
        C result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            result = array[givenPosition - 1];
        }

        return result;
    }

    private void doubleArray() {
        C[] oldArray = array;
        array = (C[]) new Object[oldArray.length * 2];
        for (int i = 0; i < oldArray.length; i++) {
            array[i] = oldArray[i];
        }
    }

    private boolean isArrayFull() {
        return numberOfEntries == array.length;
    }

    public String toString() {
        String outputStr = "";
        for (int index = 0; index < numberOfEntries; ++index) {
            outputStr += array[index] + "\n";
        }
        return outputStr;
    }

    @Override
    public Iterator<C> getIterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<C> {

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < numberOfEntries;
        }

        @Override
        public C next() {
            C element = (C) array[currentIndex];
            if (hasNext()) {
                currentIndex++;
                return element;
            }
            return null;
        }

    }
}
