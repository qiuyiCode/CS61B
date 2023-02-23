package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {
    private int minCounts;
    private Queue<SearchNode> all_nodes;
    private SearchNode min;
    private MinPQ<SearchNode> pq = new MinPQ<>(new CommonComParator());

    private class SearchNode {
        WorldState node;
        // the number of moves made to reach this world state from the initial state.
        int numberOfMoves;
        // a reference to the previous search node.
        SearchNode preNode;
        int cacheOfEstimate;

        public SearchNode(WorldState init) {
            this.node = init;
            this.numberOfMoves = 0;
            this.preNode = null;
            this.cacheOfEstimate = 0;
        }

        public SearchNode(WorldState node, int num, SearchNode pre) {
            this.node = node;
            this.numberOfMoves = num;
            this.preNode = pre;
        }
    }

    private class CommonComParator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            int l = o1.cacheOfEstimate + o1.numberOfMoves;
            int r = o2.cacheOfEstimate + o2.numberOfMoves;
            if(l > r){
                return 1;
            }else if(l < r){
                return -1;
            }else{
                return 0;
            }
        }
    }

    public Solver(WorldState initial) {
        SearchNode initialSearchNode = new SearchNode(initial);
        this.all_nodes = new LinkedList<>();
        pq.insert(initialSearchNode);
        this.minCounts = 0;
        SearchNode X;
        while (true) {
            X = pq.delMin();
            all_nodes.add(X);
            if (X.node.isGoal()) {
                min = X;
                break;
            }
            if (X.node.neighbors() != null) {
                for (WorldState w : X.node.neighbors()) {
                    if (X.preNode != null && w.equals(X.preNode.node)) {
                        continue;
                    }
                    SearchNode newSearchNode = new SearchNode(w, X.numberOfMoves + 1, X);
                    newSearchNode.cacheOfEstimate = newSearchNode.node.estimatedDistanceToGoal();
                    pq.insert(newSearchNode);
                }
            }
        }
        this.minCounts = X.numberOfMoves;
    }

    public int moves() {
        return this.minCounts;
    }

    public Iterable<WorldState> solution() {
        Stack<SearchNode> s = new Stack<>();
        LinkedList<WorldState> solution = new LinkedList<>();

        SearchNode pos = this.min;
        while(pos != null){
            s.push(pos);
            pos = pos.preNode;
        }

        while(!s.isEmpty()){
            solution.add(s.pop().node);
        }
        return solution;
    }

}
