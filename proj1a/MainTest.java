public class MainTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> ArrayDeque = new ArrayDeque<>();
        ArrayDeque.addLast(0);
        ArrayDeque.isEmpty();
        ArrayDeque.addLast(2);
        int demo = ArrayDeque.removeFirst();
        System.out.print(demo);
    }
}
