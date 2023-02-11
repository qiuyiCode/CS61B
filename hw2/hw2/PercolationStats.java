package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.Random;

public class PercolationStats {
    private Percolation percolation;
    private int T;
    private int N;
    private double[] Xt;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T must more than 0.");
        }
        this.T = T;
        this.N = N;
        Xt = new double[T];

        for (int i = 0; i < T; i++) {
            percolation = pf.make(N);
            experiment_once(i);
        }
    }

    private void experiment_once(int i) {
        int seed = StdRandom.uniform(1000000000);
        StdRandom.setSeed(seed);
        while (percolation.percolates()) {
            int row = StdRandom.uniform(0, N);
            int col = StdRandom.uniform(0, N);
            if (!percolation.isOpen(row, col)) {
                percolation.open(row, col);
            }
        }
        double xt = percolation.numberOfOpenSites() / (N * N);
        Xt[i] = xt;
    }

    public double mean() {
        return StdStats.mean(Xt);
    }

    public double stddev() {
        return StdStats.stddev(Xt);
    }

    private double confidenceHelper(double theta) {
        double num = T;
        double process = Math.sqrt(num);
        return 1.96 * theta / process;
    }

    public double confidenceLow() {
        return mean() - confidenceHelper(stddev());
    }

    public double confidenceHigh() {
        return mean() + confidenceHelper(stddev());
    }
}
