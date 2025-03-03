public class ArrayDeque<T> {

    private int head, tail;
    private int mySize, capacity;
    private T[] elems;

    private int Head() {
        return Math.floorMod(head, capacity);
    }

    private int Tail() {
        return Math.floorMod(tail, capacity);
    }

    public ArrayDeque() {
        head = 0;
        tail = 0;

        capacity = 8;
        mySize = 0;

        elems = (T[]) new Object[capacity];
    }

    private void checkResize() {
        if (capacity < 16 && mySize < capacity) {
            return ;
        } else if (4 * mySize >= capacity && mySize < capacity) {
            return ;
        }
        // need more capacity:
        if (mySize >= capacity) {
            T[] newElems = (T[]) new Object[capacity * 2];
            // copy:
            for (int i = 0; i <= Tail(); i++) {
                newElems[i] = elems[i];
            }
            for (int i = Head(); i < capacity; i++) {
                newElems[i + capacity] = elems[i];
            }
            // modify:
            elems = newElems;
            capacity *= 2;
        }
        // need less capacity:
        if (4 * mySize < capacity) {
            T[] newElems = (T[]) new Object[mySize * 4];
            // copy:
            for (int i = 0; i <= Tail(); i++) {
                newElems[i] = elems[i];
            }
            for (int i = capacity - 1, j = mySize * 4 - 1; i >= Head(); i--, j--) {
                newElems[j] = elems[i];
            }
            // modify:
            capacity = mySize * 4;
            elems = newElems;
        }
    }

    public void addFirst(T value) {
        checkResize();
        if (mySize == 0) {
            elems[head] = value;
            mySize++;
        }
        head--;
        elems[Head()] = value;
        mySize++;
    }

    public void addLast(T value) {
        checkResize();
        if (mySize == 0) {
            elems[tail] = value;
            mySize++;
        }
        tail++;
        elems[Tail()] = value;
        mySize++;
    }

    public boolean isEmpty() {
        return (mySize == 0);
    }
    public int size() {
        return mySize;
    }

    public void printDeque() {
        for (int i = head; i < 0; i++) {
            System.out.print(elems[Math.floorMod(i, capacity)] + " ");
        }
        for (int i = 0; i <= tail; i++) {
            System.out.print(elems[Math.floorMod(i, capacity)] + " ");
        }
    }

    public T removeFirst() {
        if (mySize == 0) {
            return null;
        }
        T ret = elems[Head()];
        elems[Head()] = null;
        head++;

        mySize--;
        checkResize();
        return ret;
    }

    public T removeLast() {
        if (mySize == 0) {
            return null;
        }
        T ret = elems[Tail()];
        elems[Tail()] = null;
        tail--;

        mySize--;
        checkResize();
        return ret;
    }

    public T get(int index) {
        int i = 0, j = 0;
        for (i = head, j = 0; i <= tail && j < index; i++, j++);
        if (i > tail) {
            return null;
        } else {
            return elems[Math.floorMod(i, capacity)];
        }
    }
}
