package algo.princeton.shortestpath;

import algo.princeton.linkedlists.LinkedList;

public class EdgeWeightedDigraph {

    private int V;
    private int E;
    private LinkedList<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = (LinkedList<DirectedEdge>[]) new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }
}
