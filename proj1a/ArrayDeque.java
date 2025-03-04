public class ArrayDeque<T> {
    private int head, tail;
    private int mySize, capacity;
    private T[] elems;
    private static final int BLOCK = 8;

    private int getHead() {
        return Math.floorMod(head, capacity);
    }
    private int getTail() {
        return Math.floorMod(tail, capacity);
    }

    public ArrayDeque() {
        head = 0;
        tail = 0;

        mySize = 0;
        capacity = BLOCK;

        elems = (T[]) new Object[capacity];
    }

    private void checkResize() {
        if (mySize < capacity && capacity < 16) {
            return ;
        } else if (mySize < capacity && mySize * 4 >= capacity) {
            return ;
        }
        // need more capacity:
        if (mySize == capacity) {
            T[] newElems = (T[]) new Object[capacity * 2];
            // copy:
            for (int i = head; i <= tail; i++) {
                newElems[Math.floorMod(i, capacity * 2)] = elems[Math.floorMod(i, capacity)];
                elems[Math.floorMod(i, capacity)] = null;
            }
            // modify:
            capacity *= 2;
            elems = newElems;
        }
        // need less capacity:
        if (4 * mySize < capacity) {
            T[] newElems = (T[]) new Object[4 * mySize];
            // copy:
            for (int i = head; i <= tail; i++) {
                newElems[Math.floorMod(i, 4 * mySize)] = elems[Math.floorMod(i, capacity)];
                elems[Math.floorMod(i, capacity)] = null;
            }
            // modify:
            capacity = mySize * 4;
            elems = newElems;
        }
    }

    public void addFirst(T item) {
        checkResize();
        if (mySize == 0) {
            elems[head] = item;
            mySize++;
        } else {
            head--;
            elems[getHead()] = item;
            mySize++;
        }
        checkResize();
    }

    public void addLast(T item) {
        checkResize();
        if (mySize == 0) {
            elems[tail] = item;
            mySize++;
        } else {
            tail++;
            elems[getTail()] = item;
            mySize++;
        }
        checkResize();
    }

    public boolean isEmpty() {
        return (mySize == 0);
    }

    public int size() {
        return mySize;
    }

    public void printDeque() {
        for (int i = head; i <= tail; i++) {
            // T demo = elems[Math.floorMod(i, capacity)];
            System.out.print(elems[Math.floorMod(i, capacity)] + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (mySize == 0) {
            return null;
        }
        checkResize();
        T ret = elems[getHead()];
        elems[getHead()] = null;
        if (head != tail) {
            head++;
        }
        mySize--;
        checkResize();

        return ret;
    }

    public T removeLast() {
        if (mySize == 0) {
            return null;
        }
        checkResize();
        T ret = elems[getTail()];
        elems[getTail()] = null;
        if (head != tail) {
            tail--;
        }
        mySize--;
        checkResize();

        return ret;
    }

    public T get(int index) {
        int i, j;
        for (i = head, j = 0; i <= tail && j < index; i++, j++);
        if (i > tail) {
            return null;
        } else {
            return elems[Math.floorMod(i, capacity)];
        }
    }
}