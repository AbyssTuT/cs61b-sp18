package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int length; //N-N grid
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufWithoutBackWash;
    private int numberOfOpenSites;
    private boolean[][] grid;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N<=0)
            throw new IllegalArgumentException("N must > 0");
        length = N;
        grid = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufWithoutBackWash = new WeightedQuickUnionUF(N * N + 2);
    }

    private int xyTo1d(int row, int col) {
        return row * length + col + 1;
    }


    private void unionNext(int row, int col) {
        int p = xyTo1d(row, col);
        int[][] next = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};//left,right,up,down
        for (int[] ints : next) {
            int myRow = ints[0] + row;
            int myCol = ints[1] + col;
            int myP = xyTo1d(myRow, myCol);
            //if the next unit out of bounds, then continue
            if (myCol < 0 || myCol >= length) {
                continue;
            }
            if (myRow == -1) {
                uf.union(0, p);
                ufWithoutBackWash.union(0, p);
                continue;
            }
            if (myRow == length) {
                uf.union(length * length + 1, p);
                continue;
            }
            //if the next unit is opened or fulled, then union them
            if (isOpen(myRow, myCol) && !ufWithoutBackWash.connected(p, myP)) {
                uf.union(myP, p);
                ufWithoutBackWash.union(myP, p);
            }
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if(row <0 || row >= length || col < 0 || col >= length){
            throw new IndexOutOfBoundsException("row and col must between 0 and N-1");
        }
        numberOfOpenSites++;
        grid[row][col] = true;
        int p = xyTo1d(row, col);
        unionNext(row, col);

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if(row <0 || row >= length || col < 0 || col >= length){
            throw new IndexOutOfBoundsException("row and col must between 0 and N-1");
        }
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if(row <0 || row >= length || col < 0 || col >= length){
            throw new IndexOutOfBoundsException("row and col must between 0 and N-1");
        }
        return ufWithoutBackWash.connected(xyTo1d(row, col), 0);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(0, length * length + 1);
    }

    // use for unit testing (not required)
    public static void main(String[] args) {
        Percolation perc = new Percolation(6);
        perc.open(3, 3);
        perc.open(3, 2);
        perc.open(4, 4);
        perc.open(1, 1);
        System.out.println(perc.isOpen(0, 0));
        System.out.println(perc.isOpen(4, 4));
        System.out.println(perc.isFull(0, 0));
        System.out.println(perc.isFull(1, 1));
        System.out.println(perc.numberOfOpenSites());
    }
}
