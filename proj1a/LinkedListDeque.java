public class LinkedListDeque<T> {

    private class Node {
        T data;
        Node prev;
        Node next;
    }
    private int mySize;
    private Node sentinel;

    public LinkedListDeque() {
        mySize = 0;

        sentinel = new Node();
        sentinel.data = null;
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addFirst(T value) {
        Node newNode = new Node();
        // middle:
        newNode.data = value;
        newNode.next = sentinel.next;
        newNode.prev = sentinel;
        // right:
        sentinel.next.prev = newNode;
        // sentinel:
        sentinel.next = newNode;

        mySize++;
    }

    public void addLast(T value) {
        Node newNode = new Node();
        // middle:
        newNode.data = value;
        newNode.prev = sentinel.prev;
        newNode.next = sentinel;
        // left:
        sentinel.prev.next = newNode;
        // sentinel:
        sentinel.prev = newNode;

        mySize++;
    }

    public boolean isEmpty() {
        return (mySize == 0);
    }

    public int size() {
        return mySize;
    }

    public void printDeque() {
        for (Node i = sentinel.next; i != sentinel; i = i.next) {
            System.out.print(i.data + " ");
        }
    }

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
