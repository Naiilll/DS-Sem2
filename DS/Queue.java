package DS;

import java.util.EmptyStackException;

public class Queue <E> {

    private LinkedList<E> list = new LinkedList<>();    // implemented using linked list for queue
    private int capacity;   // if necessary

    /** Default constructor of queue */
    public Queue() {
    }

    /** Add an array of elements into the queue */
    public Queue(E[] e) {
        for(E e1 : e) {
            enqueue(e1);
        }
    }

    /** Add an element at the end of queue */
    public void enqueue(E e) {
        list.addLast(e);
    }

    /** Remove the first element from the queue */
    public E dequeue() {
        return list.removeFirst();
    }

    /** Get the element at specified index in the queue */
    public E getElement(int i) {
        return list.get(i);
    }

    /** Retrieve only the first element in the queue */
    public E peek() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        return list.getFirst();
    }

    /** Return the size of the queue */
    public int getSize() {
        return list.size();
    }

    /** Return true if the element exists in the queue */
    public boolean contains(E e) {
        return list.contains(e);
    }

    /** Return true if the queue is empty, otherwise false */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    /** Display the elements in the queue */
    public String toString() {
        return "Queue: " + list.toString();
    }
}


