public class LinkedListDeque<T> implements Deque<T> {

    private class Node {
        T data;
        Node prev;
        Node next;
    }
    private int mySize;
    private Node sentinel;

    public int getMySize() {
        return mySize;
    }

    public LinkedListDeque() {
        mySize = 0;

        sentinel = new Node();
        sentinel.data = null;
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    @Override
    public void addFirst(T item) {
        Node newNode = new Node();
        // middle:
        newNode.data = item;
        newNode.next = sentinel.next;
        newNode.prev = sentinel;
        // right:
        sentinel.next.prev = newNode;
        // sentinel:
        sentinel.next = newNode;

        mySize++;
    }

    @Override
    public void addLast(T item) {
        Node newNode = new Node();
        // middle:
        newNode.data = item;
        newNode.prev = sentinel.prev;
        newNode.next = sentinel;
        // left:
        sentinel.prev.next = newNode;
        // sentinel:
        sentinel.prev = newNode;

        mySize++;
    }

    @Override
    public boolean isEmpty() {
        return (mySize == 0);
    }

    @Override
    public int size() {
        return mySize;
    }

    @Override
    public void printDeque() {
        for (Node i = sentinel.next; i != sentinel; i = i.next) {
            System.out.print(i.data + " ");
        }
    }

    @Override
    public T removeFirst() {
        if (mySize == 0) {
            return null;
        }
        Node toBeDeleted = sentinel.next;
        T ret = toBeDeleted.data;
        sentinel.next = toBeDeleted.next;
        toBeDeleted.next.prev = sentinel;
        mySize--;

        toBeDeleted = null;
        return ret;
    }

    @Override
    public T removeLast() {
        if (mySize == 0) {
            return null;
        }
        Node toBeDeleted = sentinel.prev;
        T ret = toBeDeleted.data;
        sentinel.prev = toBeDeleted.prev;
        toBeDeleted.prev.next = sentinel;
        mySize--;

        toBeDeleted = null;
        return ret;
    }

    // Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    // If no such item exists, returns null. Must not alter the deque!
    @Override
    public T get(int index) {
        int j = 0;
        Node i = null;
        for (j = 0, i = sentinel.next; j < index && i != sentinel; j++, i = i.next);

        return i.data;
    }

    public T getRecursive(int index) {
        return getRecursive_helper(index, sentinel.next, 0);
    }

    private T getRecursive_helper(int index, Node currentNode, int currentIndex) {
        if (currentNode == sentinel) {
            return null;
        } else if (currentIndex == index) {
            return currentNode.data;
        } else {
            return getRecursive_helper(index, currentNode.next, currentIndex + 1);
        }
    }
}
