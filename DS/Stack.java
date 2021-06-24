package DS;

import java.util.EmptyStackException;

public class Stack<E> {

    ArrayList<E> list = new ArrayList<>();          // implemented using arraylist for stack
    int capacity;                                   // stack's capacity

    /** Default constructor of stack */
    public Stack () {
    }

    /** Initialize the stack with capacity */
    public Stack (int capacity) {
        this.capacity = capacity;
    }

    /** Return the size of the stack */
    public int getSize() {
        return list.size();
    }

    /** Push an element into the stack */
    public void push(E o) {
        if(!isFull())
            list.add(o);
    }

    /** Retrieve and remove the top element from the stack */
    public E pop() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        E o = list.get(list.size-1);
        list.remove(list.size-1);
        return o;
    }

    /** Retrieve only the top element from the stack */
    public E peek() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        return list.get(list.size-1);
    }

    /** Return true if the stack is empty */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /** Return true if the stack is full */
    public boolean isFull() {
        return list.size()==capacity;
    }

    @Override
    /** Display all elements stored in the stack */
    public String toString() {
        return "Stack : " + list.toString();
    }

    /** Return true if found, false if not found */
    public boolean contains(E o) {
        return list.contains(o);
    }

    /** Return -1 if not found, otherwise returns location number for the found element */
    public int search(E o) {
        int index = list.indexOf(o);
        return index;
    }
}