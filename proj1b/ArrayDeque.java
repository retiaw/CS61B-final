public class ArrayDeque<T> implements Deque<T> {
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

    @Override
    public void addFirst(T item) {
        checkResize();
        if (mySize == 0) {
            elems[getHead()] = item; // 不要用head， 因为如果调用remove， 不知道head最后会指向哪里， 可能会是一个负数；
            mySize++;
        } else {
            head--;
            elems[getHead()] = item;
            mySize++;
        }
        checkResize();
    }

    @Override
    public void addLast(T item) {
        checkResize();
        if (mySize == 0) {
            elems[getTail()] = item;
            mySize++;
        } else {
            tail++;
            elems[getTail()] = item;
            mySize++;
        }
        checkResize();
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
        for (int i = head; i <= tail; i++) {
            // T demo = elems[Math.floorMod(i, capacity)];
            System.out.print(elems[Math.floorMod(i, capacity)] + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (mySize == 0) {
            return null;
        }
        checkResize();
        T ret = elems[getHead()];
        elems[getHead()] = null;
        if (head != tail) { // 如果head == tail 证明只剩下一个元素了， 我们需要回到初始状态， 即head == tail， head++ 会破坏数组结构；
            head++;
        }
        mySize--;
        checkResize();

        return ret;
    }

    @Override
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

    @Override
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
