package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int N;
    private int T;
    private double[] fraction;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        fraction = new double[T];

        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException("N,T must > 0");

        for (int i = 0; i < T; i++) {
            Percolation perc = pf.make(N);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(0, N - 1);
                int col = StdRandom.uniform(0, N - 1);
                if (!perc.isOpen(row, col)) {
                    perc.open(row, col);
                }
            }
            fraction[i] = (double) perc.numberOfOpenSites() / N / N;
        }


    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fraction);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fraction);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - (1.96 * stddev() / Math.sqrt(T));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + (1.96 * stddev() / Math.sqrt(T));
    }
}
