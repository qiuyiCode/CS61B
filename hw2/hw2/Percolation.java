package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int sites[][]; // N * N grid of sites.
    private int N;
    private WeightedQuickUnionUF disjointSet;
    private int openSites;
    private int blocked = -1;
    private int virtualTop = N;
    private int virtualBottom = N + 1;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N can't smaller than or equals to 0.");
        }

        sites = new int[N][N];
        this.N = N;
        this.openSites = 0;
        disjointSet = new WeightedQuickUnionUF(N * N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.sites[i][j] = blocked;
            }
        }
    }

    private int xyToIndex(int row, int col) {
        if (row < 0 || col < 0 || row >= N || col >= N) {
            throw new IndexOutOfBoundsException("row or col must within [0,N-1].");
        }
        return row * N + col;
    }

    private void unionNeighbors(int row, int col, int index) {
        if (row != 0 && this.sites[row - 1][col] != blocked) {
            disjointSet.union(xyToIndex(row - 1, col), index);
        }

        if (col != 0 && this.sites[row][col - 1] != blocked) {
            disjointSet.union(xyToIndex(row, col - 1), index);
        }

        if (row != N - 1 && this.sites[row + 1][col] != blocked) {
            disjointSet.union(index, xyToIndex(row + 1, col));
        }

        if (col != N - 1 && this.sites[row][col + 1] != blocked) {
            disjointSet.union(index, xyToIndex(row, col + 1));
        }
    }

    public void open(int row, int col) {
        if (row < 0 || col < 0 || row >= N || col >= N) {
            throw new IndexOutOfBoundsException("row or col must within [0,N-1].");
        }
        if (this.sites[row][col] == blocked) {
            int index = xyToIndex(row, col);
            this.sites[row][col] = index;
            if (row == 0) {
                disjointSet.union(virtualTop, xyToIndex(row, col));
            }
            if (row == N - 1) {
                disjointSet.union(virtualBottom, xyToIndex(row, col));
            }
            this.openSites += 1;
            unionNeighbors(row, col, index);
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || row >= N || col >= N) {
            throw new IndexOutOfBoundsException("row or col must within [0,N-1].");
        }
        return this.sites[row][col] != blocked;
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || col < 0 || row >= N || col >= N) {
            throw new IndexOutOfBoundsException("row or col must within [0,N-1].");
        }

        if (this.sites[row][col] == blocked) {
            return false;
        }

        if (disjointSet.connected(virtualTop, xyToIndex(row, col))) {
            return true;
        }
        return false;
    }

    public int numberOfOpenSites() {
        return this.openSites;
    }

    public boolean percolates() {
        return this.disjointSet.connected(virtualTop, virtualBottom);
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        percolation.open(3, 4);
        percolation.open(2, 4);
        percolation.open(2, 2);
        percolation.open(2, 3);
        percolation.open(0, 2);
        percolation.open(1, 2);
        percolation.open(4, 4);
        System.out.println("isFull:" + percolation.isFull(2, 2));
        System.out.println("percolates:" + percolation.percolates());
    }
}
