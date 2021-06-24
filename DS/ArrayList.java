package DS;

public class ArrayList<E> {
    public static final int INITIAL_CAPACITY = 16;
    private E[] data = (E[])new Object[INITIAL_CAPACITY];
    int size=0;

    /** Creates a default array list */
    public ArrayList() {
    }

    /** Returns the size of the list */
    public int size (){
        return size;
    }

    /** Add an element to the list*/
    public void add(E e){
        add(size,e);
    }

    /** Add an element at the specified index */
    public void add(int index, E e) {
        if (size >= data.length) {
            E[] newData = (E[])(new Object[size * 2 + 1]);
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
        // Move the elements to the right after the specified index
        for (int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];
        data[index] = e;
        size++;
    }

    /** Clear the array list */
    public void clear() {
        data = (E[])new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /** Checks whether the element already exists in the list */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++)
            if (e.equals(data[i])) return true;

        return false;
    }

    /** Get the element at the specified index in the list */
    public E get(int index) {
        if(index<0 || index>=size)
            return null;
        return data[index];
    }

    /** Get the first index of an element in the list */
    public int indexOf(E e) {
        for (int i = 0; i < size; i++)
            if (e.equals(data[i])) return i;

        return -1;
    }

    /** Get the last index of an element in the list */
    public int lastIndexOf(E e) {
        for (int i = size - 1; i >= 0; i--)
            if (e.equals(data[i])) return i;

        return -1;
    }

    /** Remove and return the element at the specified index in the list */
    public E remove(int index) {
        if(index<0 || index>=size)
            return null;

        E e = data[index];

        // Shift data to the left
        for (int j = index; j < size - 1; j++)
            data[j] = data[j + 1];

        data[size - 1] = null; // This element is now null
        size--;
        return e;
    }

    /** Set the element with a new element at the specified index in the list */
    public E set(int index, E e) {
        if(index<0 || index>=size)
            return null;
        E old = data[index];
        data[index] = e;
        return old;
    }

    /** Checks whether the list is empty */
    public boolean isEmpty(){
        return size==0;
    }

    /** Displays the elements in the list */
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            result.append(data[i]);
            if (i < size - 1) result.append(", ");
        }

        return result.toString() + "]";
    }

    // Iterator
    public java.util.Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator
            implements java.util.Iterator<E> {
        private int current = 0; // Current index

        @Override
        public boolean hasNext() {
            return (current < size);
        }

        @Override
        public E next() {
            return data[current++];
        }

        @Override
        public void remove() {
            ArrayList.this.remove(current);
        }
    }
}

