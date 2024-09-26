package algo.princeton.graphs;

public class ConnectedComponents {

    private int count;
    private final int[] id;
    private final boolean[] marked;

    public ConnectedComponents(UndirectedGraph graph) {
        this.id = new int[graph.size()];
        this.marked = new boolean[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            if (!marked[i]) {
                dfs(graph, i);
                count++;
            }
        }
    }

    private void dfs(UndirectedGraph graph, int vertex) {
        marked[vertex] = true;
        id[vertex] = count;
        for (int neighbor : graph.adj(vertex)) {
            if (!marked[neighbor]) {
                dfs(graph, neighbor);
            }
        }
    }

    public int count() {
        return count;
    }

    public int id(int vertex) {
        return id[vertex];
    }

}
