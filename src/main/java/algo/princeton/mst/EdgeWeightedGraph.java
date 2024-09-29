package algo.princeton.mst;

import algo.princeton.linkedlists.LinkedList;

public class EdgeWeightedGraph {

    private final int V;
    private final LinkedList<Edge>[] adj;
    private int E;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        adj = (LinkedList<Edge>[]) new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int v, int w, double weight) {
        Edge e = createEdge(v, w, weight);
        adj[v].addItem(e);
        adj[w].addItem(e);
        E += 1;
    }

    private Edge createEdge(int v, int w, double weight) {
        return new Edge(v, w, weight);
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<Edge> edges() {
        LinkedList<Edge> list = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            for (Edge e : adj[i]) {
                list.addItem(e);
            }
        }
        return list;
    }

}
