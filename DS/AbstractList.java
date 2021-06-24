package DS;

import java.util.Collection;

public abstract class AbstractList<E> implements List<E> {

    protected int size = 0; // The size of the list

    /**
     * Sole constructor. (For invocation by subclass constructors, typically
     * implicit.)
     */
    protected AbstractList() {
    }

    /**
     * Create a list from an array of objects
     */
    protected AbstractList(E[] objects) {
        for(int i = 0; i < objects.length; i++) {
            add(objects[i]);
        }
    }

    /**
     * Appends the specified element to the end of this list (optional
     * operation).
     *
     * <p>
     * Lists that support this operation may place limitations on what elements
     * may be added to this list. In particular, some lists will refuse to add
     * null elements, and others will impose restrictions on the type of
     * elements that may be added. List classes should clearly specify in their
     * documentation any restrictions on what elements may be added.
     *
     * @implSpec This implementation calls {@code add(size(), e)}.
     *
     * <p>
     * Note that this implementation throws an
     * {@code UnsupportedOperationException} unless
     * {@link #add(int, Object) add(int, E)} is overridden.
     *
     * @param e element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     * @throws UnsupportedOperationException if the {@code add} operation is not
     * supported by this list
     * @throws ClassCastException if the class of the specified element prevents
     * it from being added to this list
     * @throws NullPointerException if the specified element is null and this
     * list does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     * prevents it from being added to this list
     */
    @Override
    public void add(E e) {
        add(size(), e);
    }

    /**
     * Return true if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Remove the first occurrence of the element e from this list. Shift any
     * subsequent elements to the left. Return true if the element is removed.
     */
    @Override
    public boolean remove(E e) {
        if(indexOf(e) >= 0) {
            remove(indexOf(e));
            return true;
        }
        else {
            return false;
        }
    }

}