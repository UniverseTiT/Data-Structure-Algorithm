/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

/**
 *
 * @author Ng Jun Yu
 */
public interface SortedListInterface<C extends Comparable<C>> {
    public boolean addEntry(C newEntry);
    public boolean removeEntry(C anEntry);
    public boolean findEntry(C anEntry);
    public boolean amendEntry(C anEntry);
    
}
