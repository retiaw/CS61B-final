import edu.princeton.cs.algs4.Queue;

public class TestMergeSort {

    public static void main(String[] args) {
        Queue<Integer> que = new Queue<>();

        final int N = 100;
        for (int i = 0; i < N; i++) {
            que.enqueue(N - i);
        }

        print(que);
        Queue<Integer> result = MergeSort.mergeSort(que);
        print(que);
        print(result);
    }

    public static void print(Queue<Integer> que) {
        for (Integer o : que) {
            System.out.print(o + " ");
        }
        System.out.println();
    }
}
