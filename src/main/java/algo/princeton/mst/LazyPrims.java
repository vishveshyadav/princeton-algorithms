package algo.princeton.mst;

import algo.princeton.linkedlists.LinkedList;
import algo.princeton.sorting.PriorityQueue;

public class LazyPrims {

    private final LinkedList<Edge> mst;
    private final boolean[] marked;
    private final PriorityQueue<Edge> minQ;

    public LazyPrims(EdgeWeightedGraph graph) {
        this.mst = new LinkedList<>();
        this.marked = new boolean[graph.V()];
        this.minQ = new PriorityQueue<>(graph.E() * graph.E());
        visit(graph, 0);
        while (!minQ.isEmpty()) {

            Edge e = minQ.remove();
            int v = e.either();
            int w = e.other(v);
            if (marked[v] && marked[w]) {
                continue;
            }
            mst.addItem(e);
            if (!marked[v]) {
                visit(graph, v);
            }
            if (!marked[w]) {
                visit(graph, w);
            }
        }

    }

    private void visit(EdgeWeightedGraph graph, int vertex) {
        marked[vertex] = true;
        for (Edge e : graph.adj(vertex)) {
            if (!marked[e.other(vertex)]) {
                minQ.add(e);
            }
        }
    }

    public LinkedList<Edge> mst() {
        return mst;
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
        LazyPrims prims = new LazyPrims(graph);
        for (Edge e : prims.mst()) {
            System.out.println(e);
        }
    }

}
