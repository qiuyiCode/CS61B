package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX,sourceY);
        t = maze.xyTo1D(targetX,targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(int v) {
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        marked[v] = true;
        announce();

        while(!q.isEmpty()){
            v = q.poll();
            if(v == t){
                targetFound = true;
            }
            if(targetFound){
                return;
            }
            for (int w : maze.adj(v)) {
                if(!marked[w]){
                    edgeTo[w] = v;
                    marked[w] = true;
                    announce();
                    q.add(w);
                    distTo[w] = distTo[v] + 1;
                }
            }
        }
    }


    @Override
    public void solve() {
         bfs(s);
    }
}

