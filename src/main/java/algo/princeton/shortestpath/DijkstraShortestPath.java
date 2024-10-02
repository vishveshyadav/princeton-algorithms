package algo.princeton.shortestpath;


import algo.princeton.mst.IndexMinPQ;

import java.util.Stack;

public class DijkstraShortestPath {

    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private IndexMinPQ<Double> pq;

    public DijkstraShortestPath(EdgeWeightedDigraph digraph, int source) {
        this.distTo = new double[digraph.V()];
        this.edgeTo = new DirectedEdge[digraph.V()];
        this.pq = new IndexMinPQ<>(digraph.V());
        for (int v = 0; v < digraph.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[source] = 0.0;
        pq.insert(source, 0.0D);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (DirectedEdge edge : digraph.adj(v)) {
                relax(edge);
            }
        }

    }

    public double distTo(int v) {
        return distTo[v];
    }

    public Iterable<DirectedEdge> path(int v) {
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge edge = edgeTo[v]; edgeTo != null; edge = edgeTo[edge.from()]) {
            path.push(edge);
        }
        return path;
    }

    private void relax(DirectedEdge edge) {
        int v = edge.from();
        int w = edge.to();
        if (distTo[w] > edge.weight() + distTo[v]) {
            distTo[w] = edge.weight() + distTo[v];
            edgeTo[w] = edge;
            if (pq.contains(w)) {
                pq.decreaseKey(w, distTo[w]);
            } else {
                pq.insert(w, distTo[w]);
            }
        }
    }
}
