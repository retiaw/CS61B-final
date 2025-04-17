package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    private enum status {
        VACANT, BLOCK;
    }

    private int N;
    private status[][] grid;
    private WeightedQuickUnionUF relation; // label: row * N + col; top-site && bottom-site
    private int mySize;

    private void inBound(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new java.lang.IndexOutOfBoundsException("index out of bounds");
        }
    } // O(1);

    public Percolation(int N) { // create N-by-N grid, with all sites initially blocked
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("expect a positive number");
        }

        relation = new WeightedQuickUnionUF(N * N + 2);
        mySize = 0;

        this.N = N;
        grid = new status[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = status.BLOCK;
            }
        }
    } // O(N^2);

    public void open(int row, int col) { // open the site (row, col) if it is not open already
        inBound(row, col);
        if (grid[row][col] == status.VACANT) {
            return;
        }

        grid[row][col] = status.VACANT;
        mySize++;

        int label = row * N + col;
        // union with top-site || bottom-site:
        if (row == 0) {
            relation.union(label, N * N);
        }
        if (row == N - 1) {
            relation.union(label, N * N + 1);
        }
        // left:
        if (col - 1 >= 0 && grid[row][col - 1] == status.VACANT) {
            relation.union(label, label - 1);
        }
        // right:
        if (col + 1 < N && grid[row][col + 1] == status.VACANT) {
            relation.union(label, label + 1);
        }
        // upper:
        if (label - N >= 0 && grid[row - 1][col] == status.VACANT) {
            relation.union(label, label - N);
        }
        // lower:
        if (label + N < N * N && grid[row + 1][col] == status.VACANT) {
            relation.union(label, label + N);
        }
    } // O(a);

    public boolean isOpen(int row, int col) { // is the site (row, col) open?
        inBound(row, col);
        return grid[row][col] == status.VACANT;
    } // O(1);

    public boolean isFull(int row, int col) { // is the site (row, col) full?
        inBound(row, col);
        return relation.connected(row * N + col, N * N);
    } // O(a);

    public int numberOfOpenSites() { // number of open sites
        return mySize;
    } // O(1);

    public boolean percolates() { // does the system percolate?
        return relation.connected(N * N, N * N + 1);
    }

    public static void main(String[] args) { // use for unit testing (not required)
        System.out.println("hello world");
    }
}
