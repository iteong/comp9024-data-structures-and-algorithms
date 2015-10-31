package net.datastructures;
import java.util.Comparator;
import java.io.Serializable;
//begin#fragment DefaultComparator
/** Comparator based on the natural ordering
//end#fragment DefaultComparator
  *
  *  @author Michael Goodrich
//begin#fragment DefaultComparator
  */
public class DefaultComparator<E> implements Comparator<E> {
  /** Compares two given elements
 //end#fragment DefaultComparator
    *
    * @return a negative integer if <tt>a</tt> is less than <tt>b</tt>,
    * zero if <tt>a</tt> equals <tt>b</tt>, or a positive integer if
    * <tt>a</tt> is greater than <tt>b</tt>
//begin#fragment DefaultComparator
    */
  public int compare(E a, E b) throws ClassCastException { 
    return ((Comparable<E>) a).compareTo(b);
  }
}
//begin#fragment DefaultComparator
