package algo.princeton.graphs;

import java.util.Stack;

public class TopologicalSort {

    private final boolean[] marked;
    private final Stack<Integer> stack;
    private final boolean[] recursionStack;
    private final int[] edgeTo;

    public TopologicalSort(DirectedGraph graph) {
        this.marked = new boolean[graph.size()];
        this.stack = new Stack<>();
        this.recursionStack = new boolean[graph.size()];
        this.edgeTo = new int[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            if (!marked[i]) {
                dfs(graph, i);
            }
        }
    }

    private void dfs(DirectedGraph graph, int vertex) {
        marked[vertex] = true;
        recursionStack[vertex] = true;
        for (int neighbor : graph.adj(vertex)) {
            if (!marked[neighbor]) {
                edgeTo[neighbor] = vertex;
                dfs(graph, neighbor);
            } else if (recursionStack[neighbor]) {
                System.out.println("Cycle detected");
                for (int i = vertex; i != neighbor; i = edgeTo[i]) {
                    System.out.print(i + "\t");
                }
                System.out.print(neighbor + "\t");
                System.out.print(vertex);
                System.exit(-1);
            }

        }
        stack.push(vertex);
        recursionStack[vertex] = false;
    }

    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph(7);
        graph.addEdge(0, 1);
        graph.addEdge(0, 5);
        graph.addEdge(0, 2);
        graph.addEdge(6, 0);
        graph.addEdge(5, 2);
        graph.addEdge(3, 2);
        graph.addEdge(3, 5);
        graph.addEdge(3, 6);
        graph.addEdge(3, 4);
        graph.addEdge(6, 4);
        graph.addEdge(1, 4);
        graph.addEdge(4, 0);
        TopologicalSort sort = new TopologicalSort(graph);
        for (int vertex : sort.stack) {
            System.out.print(vertex + "\t");
        }
    }
}
