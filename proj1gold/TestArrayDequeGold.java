import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void RandomTest1() {
        StudentArrayDeque<Integer> dst = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> str = new ArrayDequeSolution<>();

        // random tests:
        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                dst.addLast(i);
                str.addLast(i);
            } else {
                dst.addFirst(i);
                str.addFirst(i);
            }
        }

        while (!str.isEmpty() && !dst.isEmpty()) {
            Integer a = str.removeLast();
            Integer b = dst.removeLast();
            assertEquals(a, b);
            if (!str.isEmpty() && !dst.isEmpty()) {
                a = str.removeFirst();
                b = dst.removeFirst();
                assertEquals(a, b);
            }
        }
    }
}
