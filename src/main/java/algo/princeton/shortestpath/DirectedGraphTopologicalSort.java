package algo.princeton.shortestpath;

import java.util.Stack;

public class DirectedGraphTopologicalSort {

    private final boolean[] marked;
    private final boolean[] recursionStack;
    private final Stack<Integer> stack;

    public DirectedGraphTopologicalSort(EdgeWeightedDigraph digraph) {
        this.marked = new boolean[digraph.V()];
        this.recursionStack = new boolean[digraph.V()];
        this.stack = new Stack<>();
        for (int v = 0; v < digraph.V(); v++) {
            if (!marked[v]) {
                dfs(digraph, v);
            }
        }
    }

    private void dfs(EdgeWeightedDigraph digraph, int vertex) {
        marked[vertex] = true;
        recursionStack[vertex] = true;
        for (DirectedEdge neighbor : digraph.adj(vertex)) {
            int w = neighbor.to();
            if (!marked[w]) {
                dfs(digraph, w);
            } else if (recursionStack[w]) {
                System.exit(-1);
            }
        }
        stack.push(vertex);
        recursionStack[vertex] = false;
    }

    public Iterable<Integer> order() {
        return stack;
    }

}
