package algo.princeton.graphs;

public class DepthFirstSearch {

    private final boolean[] marked;

    public DepthFirstSearch(UndirectedGraph graph, int vertex) {
        this.marked = new boolean[graph.size()];
        dfs(graph, vertex);
    }

    private void dfs(UndirectedGraph graph, int vertex) {
        marked[vertex] = true;
        System.out.print(vertex + "\t");
        for (int neighbor : graph.adj(vertex)) {
            if (!marked[neighbor]) {
                dfs(graph, neighbor);
            }
        }
    }
}