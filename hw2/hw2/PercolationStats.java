package hw2;
import edu.princeton.cs.algs4.RandomSeq;
import edu.princeton.cs.introcs.StdRandom;

import java.util.ArrayList;

public class PercolationStats {

    private ArrayList<Double> canPercolate = new ArrayList<>();

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException("expect a positive number");
        }

        int min = 0;
        int max = N;
        for (int i = 0; i < T; i++) {
            Percolation test = pf.make(N);
            while (!test.percolates()) {
            int rand1 = StdRandom.uniform(min, max);
            int rand2 = StdRandom.uniform(min, max);
            test.open(rand1, rand2);
            }
            double fraction = 1.0 * test.numberOfOpenSites() / (N * N);
            canPercolate.add(fraction);
        }
    } // perform T independent experiments on an N-by-N grid

    public double mean() {
        double sum = 0;
        for (double fraction : canPercolate) {
            sum += fraction;
        }
        double ret = sum / canPercolate.size();
        return ret;
    } // sample mean of percolation threshold

    public double stddev() {
        double mean = mean();
        double sum = 0;
        for (double fraction : canPercolate) {
            sum += Math.pow(fraction - mean, 2);
        }
        double powOfRet = sum / (canPercolate.size() - 1);
        return Math.pow(powOfRet, 0.5);
    }  // sample standard deviation of percolation threshold

    public double confidenceLow()  {
        return (mean() - 1.96 * stddev() / (Math.pow(canPercolate.size(), 0.5)));
    } // low endpoint of 95% confidence interval
    public double confidenceHigh() {
        return (mean() + 1.96 * stddev() / (Math.pow(canPercolate.size(), 0.5)));
    } // high endpoint of 95% confidence interval
}
