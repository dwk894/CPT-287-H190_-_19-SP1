/** Created by Dayu Wang (dwang@stchas.edu) on 01-25-19. */

/** Last updated by Dayu Wang (dwang@stchas.edu) on 01-25-19. */

package data_structures;

public class Linkedlist<T> implements IterableList<T> {
    private class ListNode {
        public T data;
        public ListNode prev, next;
        public ListNode(T theData) { data = theData; }
    }
    private ListNode head, tail;
    private int numOfNodes;
    public boolean isEmpty() { return numOfNodes == 0; }
    public int size() { return numOfNodes; }
    public void addLast(T item) {
        if (isEmpty()) { head = tail = new ListNode(item); }
        else {
            tail.next = new ListNode(item);
            tail.next.prev = tail;
            tail = tail.next;
        }
        numOfNodes++;
    }
    public void addFirst(T item) {
        if (isEmpty()) { head = tail = new ListNode(item); }
        else {
            head.prev = new ListNode(item);
            head.prev.next = head;
            head = head.prev;
        }
        numOfNodes++;
    }
    public T getFirst() throws Exception {
        if (isEmpty()) { throw new Exception("Attempt to access element in an empty list."); }
        return head.data;
    }
    public T getLast() throws Exception {
        if (isEmpty()) { throw new Exception("Attempt to access element in an empty list."); }
        return tail.data;
    }
    public T removeFirst() throws Exception {
        if (isEmpty()) { throw new Exception("Attempt to remove element from empty list."); }
        T returnValue = head.data;
        if (size() == 1) { head = tail = null; }
        else {
            head = head.next;
            head.prev = null;
        }
        numOfNodes--;
        return returnValue;
    }
    public T removeLast() throws Exception {
        if (isEmpty()) { throw new Exception("Attempt to remove element from empty list."); }
        T returnValue = tail.data;
        if (size() == 1) { head = tail = null; }
        else {
            tail = tail.prev;
            tail.next = null;
        }
        numOfNodes--;
        return returnValue;
    }
    public String toString() {
        String output = "[";
        ListNode p = head;
        while (p != null) {
            output += p.data.toString();
            if (p.next != null) { output += ", "; }
            p = p.next;
        }
        return output + ']';
    }
    @Override
    public List_Iterator<T> iterator() {
        List_Iterator<T> it = new List_Iterator<T>() {
            private ListNode current = head;
            @Override
            public boolean hasNext() { return current != null; }
            @Override
            public boolean hasPrevious() { return current != head; }
            @Override
            public T next() throws Exception {
                if (!hasNext()) { throw new Exception("Cannot increment iterator."); }
                T returnValue = current.data;
                current = current.next;
                return returnValue;
            }
            @Override
            public T previous() throws Exception {
                if (!hasPrevious()) { throw new Exception("Cannot decrement iterator."); }
                current = current == null ? tail : current.prev;
                return current.data;
            }
            @Override
            public void add(T item) {
                if (current == head) { addFirst(item); }
                else if (current == null) { addLast(item); }
                else {
                    ListNode p = new ListNode(item);
                    p.prev = current.prev;
                    p.next = current;
                    p.prev.next = p;
                    p.next.prev = p;
                    numOfNodes++;
                }
            }
            @Override
            public void remove() throws Exception {
                if (current == head) { removeFirst(); }
                current = current == null ? tail : current.prev;
                if (current == tail) { removeLast(); }
                else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    numOfNodes--;
                }
            }
            @Override
            public void set(T item) throws Exception {
                if (current == null) { throw new Exception("Iterator not bound to a valid node."); }
                current.data = item;
            }
        };
        return it;
    }
    public List_Iterator<T> find(T target) throws Exception {
        List_Iterator<T> it = iterator();
        while (it.hasNext()) {
            if (it.next().equals(target)) {
                it.previous();
                break;
            }
        }
        return it;
    }
}