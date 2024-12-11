/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;
import java.util.Iterator;
/**
 *
 * @author Ng Jun Yu
 */
public interface SortedArrayInterface<C extends Comparable<C>> {

    public boolean addEntry(C newEntry);
    public boolean removeEntry(C anEntry);
    public boolean findEntry(C anEntry);
    public boolean amendEntry(C oldEntry, C newEntry);
    public int getNumberOfEntries();
    public C getEntry(int givenPosition);
    public Iterator<C> getIterator();

}
