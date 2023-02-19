package lab11.graphs;

import java.util.Iterator;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze maze;

    public MazeCycles(Maze m) {
        super(m);
        this.maze = m;
    }

    public boolean isCyclicUtil(int v,int parent){
        marked[v] = true;
        announce();

        for(int w : maze.adj(v)){
            if(!marked[w]){
                edgeTo[w] = v;
                announce();
                distTo[w] = distTo[v] + 1;
                if(isCyclicUtil(w,v)){
                    break;
                }
            }else{
                if(w != parent){
                    break;
                }
            }
        }
        return false;
    }

    @Override
    public void solve() {
        isCyclicUtil(0,-1);
    }

    // Helper methods go here
}

