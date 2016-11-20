import java.util.Iterator;

/**
 * An interface similar to java.util.List, but doesn't include all the methods
 * of that interface and allows additions/deletions for the purposes of
 * reasoning about lists as part of class. All methods mirror their List
 * counterparts in functionality.
 * 
 * @author ola
 */

public interface ISimpleList<Type> extends Iterable<Type> {
    /**
     * Add a value to the end of this list.
     * 
     * @param t is the value added to this list
     */
    public void add(Type t);

    /**
     * Add a value at a specified index in the list, shifting other values to
     * make room.
     * 
     * @param index is the index at which new value added
     * @param t the new value added
     * @throws IndexOutOfBoundsException
     *         if index is greater than size of list or less than zero
     */
    public void add(int index, Type t);

    /**
     * Returns an iterator over this list's values, the iterator supports
     * remove.
     * 
     * @return iterator (supporting remove))
     */
    public Iterator<Type> iterator();

    /**
     * Remove and return the value at the specified index, shifting other values
     * 
     * @param index of value to remove
     * @return the removed value
     * @throws IndexOutOfBoundsException
     *         if index < 0 or >= size()
     */
    public Type remove(int index);

    /**
     * Remove first occurrence of a value, and return true iff removal succeeds.
     * 
     * @param t is value removed
     * @return true if a value is removed, false otherwise
     */
    public boolean remove(Type t);

    /**
     * Return the value at the specified index.
     * 
     * @param index of value to return
     * @return value at specified index
     * @throws IndexOutOfBoundsException
     *         if index < 0 or >= size()
     */
    public Type get(int index);

    /**
     * Return index of first occurrence of a value, or -1 if not found.
     * 
     * @param t is valuel searched for
     * @return index of first occurrence of t, or -1 if t not found
     */
    public int indexOf(Type t);

    /**
     * Returns number of elements in this list.
     * 
     * @return number of elements in list
     */
    public int size();
}
