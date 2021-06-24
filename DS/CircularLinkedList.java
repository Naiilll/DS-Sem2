package DS;

import java.util.NoSuchElementException;

public class CircularLinkedList<E> {

    private Node<E> first; // head
    private Node<E> last; // tail
    private int size;

    public CircularLinkedList() {
    }

    public void addFirst(E e) {
        final Node<E> oldFirst = first;
        final Node<E> newFirst = new Node<>(null, e, oldFirst);
        first = newFirst;
        // if there is only one element after adding
        if(oldFirst == null) {
            last = newFirst;
            newFirst.next = newFirst;
            newFirst.prev = newFirst;
        }
        else {
            oldFirst.prev = newFirst;
            last.next = newFirst;
            newFirst.prev = last;
        }
        size++;
    }

    public void addLast(E e) {
        final Node<E> oldLast = last;
        final Node<E> newLast = new Node<>(oldLast, e, null);
        last = newLast;
        // if there is only one element after adding
        if(oldLast == null) {
            first = newLast;
            newLast.next = newLast;
            newLast.prev = newLast;
        }
        else {
            oldLast.next = newLast;
            first.prev = newLast;
            newLast.next = first;
        }
        size++;
    }

    public void add(int index, E element) {
        index = (index >= 0) ? index % size : (index % size) + size;
        if(index == 0) {
            addFirst(element);
        }
        else if(index == size) {
            addLast(element);
        }
        else {
            Node<E> nextNode = node(index);
            Node<E> prevNode = nextNode.prev;
            Node<E> newNode = new Node<>(prevNode, element, nextNode);

            nextNode.prev = newNode;
            prevNode.next = newNode;

            size++;
        }
    }

    public E removeFirst() {
        if(!isEmpty()) {
            final E returnEle = first.item;
            final Node<E> oldFirst = first;
            final Node<E> newFirst = first.next;
            first = newFirst;
            oldFirst.prev = null;
            oldFirst.next = null;
            // if empty after remove
            if(size == 1) {
                first = null;
                last = null;
            }
            else {
                newFirst.prev = last;
                last.prev = newFirst;
            }
            size--;
            return returnEle;
        }
        else {
            throw new NoSuchElementException();
        }
    }

    public E removeLast() {
        if(!isEmpty()) {
            final E returnEle = first.item;
            final Node<E> oldLast = last;
            final Node<E> newLast = last.prev;
            last = newLast;
            oldLast.prev = null;
            oldLast.next = null;
            // if empty after remove
            if(size == 1) {
                first = null;
                last = null;
            }
            else {
                newLast.next = null;
                first.prev = newLast;
            }
            size--;
            return returnEle;
        }
        throw new NoSuchElementException();
    }

    public E remove(int index) {
        index = (index >= 0) ? index % size : (index % size) + size;
        return unlink(node(index));
    }

    public boolean contains(E e) {
        // because null does not have equals()
        if(e == null) {
            for(Node<E> x = first; x != null; x = x.next) {
                if(x.item == null) {
                    return true;
                }
            }
        }
        else {
            for(Node<E> x = first; x != null; x = x.next) {
                // null value is allowed in this data structure
                if(e.equals(x.item)) {
                    return true;
                }
            }
        }
        return false;
    }

    public E get(int index) {
        index = (index >= 0) ? index % size : (index % size) + size;
        E returnEle = null;
        returnEle = node(index).item;
        return returnEle;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private E unlink(Node<E> x) {
        final E returnEle = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        prev.next = next;
        x.prev = null;

        next.prev = prev;
        x.next = null;

        x.item = null;
        size--;
        return returnEle;
    }

    /**
     * Searching
     *
     * @param index
     * @return
     */
    Node<E> node(int index) {
        // index must be in range
        if(index < (size >> 1)) { // divide by 2
            // upper part looping
            Node<E> x = first;
            for(int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        }
        else {
            // lower part looping
            Node<E> x = last;
            for(int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    public void print() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> x = first;
        for(int i = 0; i < size; i++) {
            if(x.item != null) {
                sb.append(x.item.toString());
            }
            else {
                sb.append("null");
            }
            if(i != size - 1) {
                sb.append(", ");
            }
            x = x.next;
        }
        System.out.println(sb.append("]").toString());
    }

    private static class Node<E> {

        E item; // element
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.prev = prev;
            this.next = next;
        }

    }

}