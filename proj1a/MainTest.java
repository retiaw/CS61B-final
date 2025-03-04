public class MainTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> ArrayDeque = new ArrayDeque<>();
        ArrayDeque.addLast(2);
        ArrayDeque.printDeque();
        ArrayDeque.removeFirst();
        ArrayDeque.printDeque();
        ArrayDeque.addLast(5);
        ArrayDeque.printDeque();
        ArrayDeque.removeFirst();
        ArrayDeque.printDeque();
    }
}
