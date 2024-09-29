package algo.princeton.mst;

import algo.princeton.linkedlists.LinkedList;
import algo.princeton.sorting.PriorityQueue;
import algo.princeton.unionfind.QuickUnion;

public class Kruskals {

    private LinkedList<Edge> edges;

    public Kruskals(EdgeWeightedGraph graph) {
        this.edges = new LinkedList<>();
        QuickUnion union = new QuickUnion(graph.V());
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(2 * graph.E());
        for (Edge e : graph.edges()) {
            minHeap.add(e);
        }
        while (!minHeap.isEmpty()) {
            Edge e = minHeap.remove();
            int v = e.either();
            int w = e.other(v);
            if (!union.isConnected(v, w)) {
                union.union(v, w);
                edges.addItem(e);

            }
        }
    }

    public LinkedList<Edge> edges() {
        return edges;
    }

    public static void main(String[] args) {

        EdgeWeightedGraph graph = new EdgeWeightedGraph(8);
        graph.addEdge(5, 1, 0.32);
        graph.addEdge(1, 3, 0.29);
        graph.addEdge(3, 6, 0.52);
        graph.addEdge(6, 4, 20.0);
        graph.addEdge(4, 5, 0.35);
        graph.addEdge(7, 1, 0.19);
        graph.addEdge(7, 0, 0.16);
        graph.addEdge(7, 5, 0.28);
        graph.addEdge(7, 4, 0.37);
        graph.addEdge(7, 2, 0.34);
        graph.addEdge(0, 2, 0.26);
        graph.addEdge(0, 4, 0.38);
        graph.addEdge(0, 6, 0.58);
        graph.addEdge(2, 3, 0.17);
        graph.addEdge(2, 6, 0.40);
        graph.addEdge(2, 1, 0.36);
        Kruskals kruskal = new Kruskals(graph);
        for (Edge e : kruskal.edges()) {
            System.out.println(e);
        }
    }
}
