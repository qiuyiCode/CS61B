package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solver {
    private int minCounts;
    private Queue<WorldState> solution;

    private class SearchNode {
        WorldState node;
        // the number of moves made to reach this world state from the initial state.
        int numberOfMoves;
        // a reference to the previous search node.
        WorldState preNode;
        int cacheOfEstimate;

        public SearchNode(WorldState init) {
            this.node = init;
            this.numberOfMoves = 0;
            this.preNode = null;
            this.cacheOfEstimate = -1;
        }

        public SearchNode(WorldState node, int num, WorldState pre) {
            this.node = node;
            this.numberOfMoves = num;
            this.preNode = pre;
            this.cacheOfEstimate = -1;
        }
    }

    private class CommonComParator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            int l = o1.cacheOfEstimate + o1.numberOfMoves;
            int r = o2.cacheOfEstimate + o2.numberOfMoves;
            if (l > r) {
                return 1;
            } else if (l < r) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public Solver(WorldState initial) {
        SearchNode initialSearchNode = new SearchNode(initial);
        MinPQ<SearchNode> pq = new MinPQ<>(new CommonComParator());
        pq.insert(initialSearchNode);
        this.minCounts = 0;
        this.solution = new LinkedList<>();
        SearchNode X;
        while (true) {
            X = pq.delMin();
            this.solution.add(X.node);
            if (X.node.isGoal()) {
                break;
            }
            if (X.node.neighbors() != null) {
                for (WorldState w : X.node.neighbors()) {
                    if (X.preNode != null && w.equals(X.preNode)) {
                        continue;
                    }
                    SearchNode newSearchNode = new SearchNode(w, X.numberOfMoves + 1, X.node);
                    if (newSearchNode.cacheOfEstimate == -1) {
                        newSearchNode.cacheOfEstimate = newSearchNode.node.estimatedDistanceToGoal();
                    }
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
        return this.solution;
    }

    public int hashCode() {
        return this.hashCode();
    }
}
