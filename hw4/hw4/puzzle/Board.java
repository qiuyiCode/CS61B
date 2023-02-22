package hw4.puzzle;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;

public class Board implements WorldState{
    int tiles[][];
    int N;
    public Board(int[][] tiles){
        this.N = tiles.length;
        this.tiles = new int[N][N];
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.N; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }
    public int tileAt(int i,int j){
        if((i < 0 || i > N-1) || (j < 0 || i > N-1)){
            throw new IndexOutOfBoundsException("The index of i/j must within [0,N)");
        }
        return this.N != 0 ? tiles[i][j] : 0;
    }
    public int size(){
        return this.N;
    }

    private boolean isCorrectPos(int i,int j){
        int correct = i * this.N + j + 1;
        return this.tiles[i][j] == correct;
    }
    public int hamming(){
        int rlt = 0;
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.N; j++) {
                if(this.tiles[i][j] == 0){
                    continue;
                }
                if(!isCorrectPos(i,j)){
                    rlt++;
                }
            }
        }
        return rlt;
    }
    private int singleManhattanDis(int i,int j){
        int rec = this.tiles[i][j] - 1;
        int correctJ = rec % this.N;
        int correctI = (rec - correctJ) / this.N;
        int rlt = Math.abs(correctI - i) + Math.abs(correctJ-j);
        return rlt;
    }
    public int manhattan(){
        int rlt = 0;
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.N; j++) {
                if(this.tiles[i][j] == 0){
                    continue;
                }
                rlt += singleManhattanDis(i,j);
            }
        }
        return rlt;
    }

    public boolean equals(Object y){
        Board yy = (Board)y;
        if(this.N != yy.N){
            return false;
        }
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.N; j++) {
                if(this.tileAt(i,j) != yy.tileAt(i,j)){
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public int estimatedDistanceToGoal() {
//        return this.hamming();
        return this.manhattan();
    }
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new LinkedList<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == 0) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = 0;
                    Board neighbor = new Board(ili1li1);
                    neighbors.add(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = 0;
                }
            }
        }
        return neighbors;
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
}
