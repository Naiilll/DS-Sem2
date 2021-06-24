package DS;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <ol>
 * <li>What if the value is null?<br>
 * <li>Is index out of bound?<br>
 * <li>What if the list is empty?<br>
 * <li>What if the list has only one element?<br>
 * <li>Is there any change in size for the list?<br>
 * <li>What if operation occurs in first node?<br>
 * <li>What if operation occurs in last node?<br>
 * </ol>
 */
public class DoublyLinkedList<E> extends AbstractList<E> {

    private Node<E> first; // head
    private Node<E> last; // tail
    private int size;

    private E unlink(Node<E> x) {
        // x cannot be null
        final E returnEle = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        // if its next node becomes first node after remove
        if(prev == null) {
            first = next;
        }
        else {
            prev.next = next;
            x.prev = null;
        }

        // if its prev node becomes last node after remove
        if(next == null) {
            last = prev;
        }
        else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return returnEle;
    }

    public E getFirst() {
        if(!isEmpty()) {
            final E returnEle = first.item;
            return returnEle;
        }
        throw new NoSuchElementException();
    }

    public E getLast() {
        if(!isEmpty()) {
            final E returnEle = last.item;
            return returnEle;
        }
        throw new NoSuchElementException();
    }

    public E removeFirst() {
        if(!isEmpty()) {
            final E returnEle = first.item;
            final Node<E> newFirst = first.next;
            first = newFirst;
            // if empty after remove
            if(first == null) {
                last = null;
            }
            else {
                newFirst.prev = null;
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
            final Node<E> newLast = last.prev;
            last = newLast;
            // if empty after remove
            if(last == null) {
                first = null;
            }
            else {
                newLast.next = null;
            }
            size--;
            return returnEle;
        }
        throw new NoSuchElementException();
    }

    public void addFirst(E e) {
        final Node<E> oldFirst = first;
        final Node<E> newFirst = new Node<>(null, e, oldFirst);
        first = newFirst;
        // if there is only one element after adding
        if(oldFirst == null) {
            last = newFirst;
        }
        else {
            oldFirst.prev = newFirst;
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
        }
        else {
            oldLast.next = newLast;
        }
        size++;
    }

    @Override
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

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(E e) {
        addLast(e);
    }

    @Override
    public boolean remove(E e) {
        // because null does not have equals()
        if(e == null) {
            for(Node<E> x = first; x != null; x = x.next) {
                if(x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        }
        else {
            for(Node<E> x = first; x != null; x = x.next) {
                if(e.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void clear() {
        // help GC
        for(Node<E> x = first; x != null;) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        if(index >= 0 && index < size) {
            E returnEle = null;
            returnEle = node(index).item;
            return returnEle;
        }
        throw new IndexOutOfBoundsException(index);
    }

    @Override
    public E set(int index, E element) {
        if(index >= 0 && index < size) {
            Node<E> returnNode = null;
            E returnEle = null;
            returnNode = node(index);
            returnEle = returnNode.item;
            returnNode.item = element;
            return returnEle;
        }
        throw new IndexOutOfBoundsException(index);
    }

    @Override
    public void add(int index, E element) {
        if(index == size) {
            addLast(element);
        }
        else if(index >= 0 && index < size) {
            Node<E> nextNode = node(index);
            Node<E> prevNode = nextNode.prev;
            Node<E> newNode = new Node<>(prevNode, element, nextNode);

            nextNode.prev = newNode;
            // if index is not 0
            if(prevNode != null) {
                prevNode.next = newNode;
            }
            else {
                first = newNode;
            }

            size++;
        }
        else {
            throw new IndexOutOfBoundsException(index);
        }
    }

    @Override
    public E remove(int index) {
        if(index >= 0 && index < size) {
            return unlink(node(index));
        }
        throw new IndexOutOfBoundsException(index);
    }

    /**
     * Searching
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

    @Override
    public int indexOf(E e) {
        int index = 0;
        if(e == null) {
            for(Node<E> x = first; x != null; x = x.next) {
                if(x.item == null) {
                    return index;
                }
                index++;
            }
        }
        else {
            for(Node<E> x = first; x != null; x = x.next) {
                if(e.equals(x.item)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        int index = size;
        if(e == null) {
            for(Node<E> x = last; x != null; x = x.prev) {
                index--;
                if(x.item == null) {
                    return index;
                }
            }
        }
        else {
            for(Node<E> x = last; x != null; x = x.prev) {
                index--;
                if(e.equals(x.item)) {
                    return index;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void iterateForward() {
        StringBuilder sb = new StringBuilder("Iterate forward:\n");
        for(Node<E> x = first; x != null; x = x.next) {
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
        System.out.println(sb.toString());
    }

    public void iterateBackward() {
        StringBuilder sb = new StringBuilder("Iterate backward:\n");
        for(Node<E> x = last; x != null; x = x.prev) {
            if(x.item != null) {
                sb.append(x.item.toString());
            }
            else {
                sb.append("null");
            }
            if(x.prev != null) {
                sb.append(", ");
            }
        }
        System.out.println(sb.toString());
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {

        private Node<E> lastReturned;
        private Node<E> next;
        private int nextIndex;

        public Itr() {
            next = first;
            nextIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        @Override
        public void remove() {
            if(lastReturned == null) {
                throw new IllegalStateException();
            }

            Node<E> lastNext = lastReturned.next;
            unlink(lastReturned);

            nextIndex--;

            lastReturned = null;
        }

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