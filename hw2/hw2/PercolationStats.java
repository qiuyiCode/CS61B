package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private Percolation percolation;
    private int T;
    private int N;
    private double[] xInstance;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T must more than 0.");
        }
        this.T = T;
        this.N = N;
        xInstance = new double[T];

        for (int i = 0; i < T; i++) {
            percolation = pf.make(N);
            calculateOnce(i);
        }
    }

    private void calculateOnce(int i) {
        while (percolation.percolates() == false) {
            int seed = StdRandom.uniform(1000000);
            StdRandom.setSeed(seed);
            int row = StdRandom.uniform(0, N);
            int col = StdRandom.uniform(0, N);
            if (!percolation.isOpen(row, col)) {
                percolation.open(row, col);
            }
        }
        double xt = Double.valueOf(percolation.numberOfOpenSites()) / Double.valueOf(N * N);
        xInstance[i] = xt;
    }

    public double mean() {
        return StdStats.mean(xInstance);
    }

    public double stddev() {
        return StdStats.stddev(xInstance);
    }

    private double confidenceHelper(double theta) {
        double num = Double.valueOf(T);
        double process = Math.sqrt(num);
        return 1.96 * theta / process;
    }

    public double confidenceLow() {
        return mean() - confidenceHelper(stddev());
    }

    public double confidenceHigh() {
        return mean() + confidenceHelper(stddev());
    }

    public static void main(String[] args) {
        PercolationFactory factory = new PercolationFactory();
        PercolationStats p = new PercolationStats(20,10,factory);
        System.out.println(p.mean());
    }
}
