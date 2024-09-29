package algo.princeton.graphs;

import algo.princeton.linkedlists.LinkedList;

public class DirectedGraph {

    private final int V;
    private LinkedList<Integer>[] adj;

    public DirectedGraph(int v) {
        this.V = v;
        adj = (LinkedList<Integer>[]) new LinkedList[v];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].addItem(w);
    }

    public Iterable<Integer> adj(Integer v) {
        return adj[v];
    }

    public int size() {
        return V;
    }

    public static void main(String[] args) {

        DirectedGraph graph = new DirectedGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(2, 3);
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
