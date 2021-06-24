package DS;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E>  {
    // extends AbstractList<E>
    private Node<E> head; // head
    private Node<E> tail; // tail
    int size;


    public LinkedList() {
    }

    // public LinkedList(E[] objects) {
    //    super(objects);
    // }

    /** Return size of the linked list */
    public int size() {
        return size;
    }

    /** Return true if list is empty, otherwise false */
    public boolean isEmpty(){
        return size==0;
    }

    /**
     * Return true if list contains the element e
     *
     * @param e
     * @return
     */
    public boolean contains(E e) { // not Object o
        if(isEmpty()) {
            return false;
        }
        if(head.item.equals(e) || tail.item.equals(e)) {
            return true;
        }
        Node<E> current = head;
        for(int i = 1; i < size - 1; i++) {
            current = current.next;
            if(current.item.equals(e)) { // important
                return true;
            }
        }
        return false;
    }

    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Clear the list
     */
    public void clear() {
        for(Node<E> x = head; x != null;) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x = next;
        }
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Return element at the specified index
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if(index == 0) {
            return getFirst();
        }
        else if(index == size - 1) {
            return getLast();
        }
        else if(index > 0 && index < size) {
            Node<E> current = head;
            for(int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.item;
        }
        throw new IndexOutOfBoundsException();
    }

    /**
     * Return the value of the first item
     *
     * @return
     */
    public E getFirst() {
//        return (first != null) ? first.item : null;
        if(head != null) { // if it is not empty;
            return head.item;
        }
        else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Return the value of the last item
     *
     * @return
     */
    public E getLast() {
//        return (last != null) ? last.item : null;
        if(tail != null) { // if it is not empty;
            return tail.item;
        }
        else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Replace the element at the specified position in this list with the
     * specified element
     *
     * @param index
     * @param element
     * @return
     */
    public E set(int index, E element) {
        E returnEle = null;

        if(index >= 0 && index < size) {
            Node<E> current = head;
            for(int i = 0; i < index; i++) {
                current = current.next;
            }
            returnEle = current.item;
            current.item = element;
        }

        return returnEle;
    }

    /**
     * Replace the element by old element
     *
     * @param e
     * @param newE
     */
    public void replace(E e, E newE) {
        for(Node<E> x = head; x != null; x = x.next) {
            if(x.item.equals(e)) {
                x.item = newE;
                return;
            }
        }
    }

    /**
     * Return nothing, but adds an element to the list
     *
     * @param e
     */
    public void add(E e) {
        addLast(e);
    }

    /**
     * Add an element to the beginning of the list
     */
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e, head); // Create a new node
        head = newNode; // head points to the new node

        if(tail == null) // the new node is the only node in list
        {
            tail = newNode;
        }
        size++; // Increase list size
    }

    /**
     * Add an element to the end of the list
     */
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e, null); // Create a new for element e

        if(tail == null) {
            head = tail = newNode; // The new node is the only node in list
        }
        else {
            tail.next = newNode; // Link the new with the last node
            tail = newNode; // tail now points to the last node
        }

        size++; // Increase size
    }

    /** Add an element at a specified index in the linked list */
    public void add(int index, E element) {
        if(index == 0) {
            addFirst(element);
        }
        else if(index == size) {
            addLast(element);
        }
        else if(index > 0 && index < size) {
            Node<E> current = head;
            for(int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<>(element, temp);
            size++;
        }
    }

    /**
     * Remove the head node and return the object that is contained in the
     * removed node.
     */
    public E removeFirst() {
        if(size == 0) { // isEmpty()
            return null;
        }
        else {
            Node<E> temp = head;
            head = head.next;
            if(head == null) { // if only one element
                tail = null;
            }
            size--;
            return temp.item;
        }
    }

    /**
     * Remove the last node and return the object that is contained in the
     * removed node.
     */
    public E removeLast() {
        if(size == 0) { // isEmpty()
            return null;
        }
        else if(size == 1) { // if only one element
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.item;
        }
        else {
            Node<E> current = head;

            for(int i = 0; i < size - 2; i++) {
                current = current.next;
            }

            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.item;
        }
    }

    public E remove(int index) {
        if(index < 0 || index >= size) { // check out of bound
            return null;
        }
        else if(index == 0) {
            return removeFirst();
        }
        else if(index == size - 1) {
            return removeLast();
        }
        else {
            Node<E> previous = head;

            for(int i = 1; i < index; i++) {
                previous = previous.next;
            }

            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.item;
        }
    }

    /**
     * Return the index of the head matching element in this list. Return -1 of
     * no match
     *
     * @param o
     * @return
     */
    public int indexOf(E o) {
        int index = 0;
//        for(int i = 0; i < size; i++) {
//            current = current.next;
//        }
        for(Node<E> x = head; x != null; x = x.next) {
            if(x.item.equals(o)) {
                return index;
            }
            index++;
        }

        return -1;
    }

    /**
     * Return the index of the last matching element in this list. Return -1 of
     * no match
     *
     * @param o
     * @return
     */
    public int lastIndexOf(Object o) {
        int index = 0;
        int target = -1;

        for(Node<E> x = head; x != null; x = x.next) {
            if(x.item.equals(o)) {
                target = index;
            }
            index++;
        }
        return target;
    }

    /**
     * Print all the elements in the list
     */
    public void print() {
        System.out.println(toString());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for(Node<E> x = head; x != null; x = x.next) {
            if(x.item != null) {
                sb.append(x.item.toString());
            }
            else {
                sb.append("null");
            }
            if(x.next != null) {
                sb.append(", ");
            }
        }
        sb.append("]").toString();
        return sb.toString();
    }

    /**
     * Print all elements in reversePrint order
     */
    public void reversePrint() {
        StringBuilder sb = new StringBuilder("[");

        Object[] result = new Object[size];
//        T[] result = (T[]) new Object[size];
        int i = 0;
        for(Node<E> x = head; x != null; x = x.next) {
            result[i] = x.item;
            i++;
        }

//        Node<E> x = first;
//        while(x != null) {
//
//            x = x.next;
//        }
        // reversePrint looping
        for(int j = result.length - 1; j > -1; j--) {
            if(result[j] != null) {
                sb.append(result[j].toString());
            }
            else {
                sb.append("null");
            }
            if(j != 0) {
                sb.append(", ");
            }
        }
        System.out.println(sb.append("]").toString());

//        for(int j = size - 1; j > -1; j--) {
//            System.out.println(get(j));
//        }
    }



    @SuppressWarnings("unchecked")
    public void reverse() {
//        Object[] result = new Object[size];

//        E[] result = (E[]) new Object[size];
//        int i = 0;
//        for(Node<E> x = first; x != null; x = x.next) {
//            result[i] = x.item;
//            i++;
//        }
//
//        clear();
//        for(int j = result.length - 1; j > -1; j--) {
//            addLast(result[j]);
//        }
        if(isEmpty() || size == 1) {
            return;
        }
        Node<E> prevNode = null;
        Node<E> current = head;
        for(Node<E> x = head.next; x != null; x = x.next) {
            current.next = prevNode;
            prevNode = current;
            current = x;
        }
        current.next = prevNode;
        Node<E> temp = head;
        head = tail;
        tail = temp;
    }

    /**
     * returns the value of the middle element of a linked list.
     *
     * @return
     */
    public E getMiddleValue() {
        // [1, 2, 3, 4] : 2 (int midIndex = (size - 1) / 2)
        // [1, 2, 3, 4, 5] : 3 (int midIndex = (size - 1) / 2)
        if(isEmpty()) {
            return null;
        }

        int mid = (size - 1) >> 1; // shift all the bit to the right by 1 distance
        // int midIndex = (size - 1) / 2

        Node<E> current = head;
        for(int i = 0; i < mid; i++) {
            current = current.next;
        }
        return current.item;
    }

    private static class Node<E> {

        E item; // element
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }

        public E getItem() {
            return item;
        }

        public void setItem(E item) {
            this.item = item;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
}