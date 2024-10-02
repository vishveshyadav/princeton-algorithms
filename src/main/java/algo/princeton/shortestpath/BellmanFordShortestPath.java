package algo.princeton.shortestpath;

import java.util.Stack;

public class BellmanFordShortestPath {
    private final double[] distTo;
    private final DirectedEdge edgeTo[];

    public BellmanFordShortestPath(EdgeWeightedDigraph graph, int source) {
        this.distTo = new double[graph.V()];
        this.edgeTo = new DirectedEdge[graph.V()];
        for(int v = 0; v < graph.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[source] = 0.0;
        for(int i =0; i<graph.V(); i++) {
            for (int v = 0; v < graph.V(); v++) {
                for(DirectedEdge edge: graph.adj(v)) {
                    relax(edge);
                }
            }
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public Iterable<DirectedEdge> path(int e) {
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge v = edgeTo[e]; v != null; v = edgeTo[v.from()]) {
            path.push(v);
        }
        return path;
    }

    public void relax(DirectedEdge edge) {
        int v = edge.from();
        int w = edge.to();
        if (distTo[w] > distTo[v] + edge.weight()) {
            distTo[w] = distTo[v] + edge.weight();
            edgeTo[w] = edge;
        }
    }
}
