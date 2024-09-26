package algo.princeton.graphs;

import algo.princeton.linkedlists.LinkedList;

public class UndirectedGraph {

    private final int V;
    private LinkedList<Integer>[] adj;

    public UndirectedGraph(int v) {
        this.V = v;
        adj = (LinkedList<Integer>[]) new LinkedList[v];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].addItem(w);
        adj[w].addItem(v);
    }

    public Iterable<Integer> adj(Integer v) {
        return adj[v];
    }

    public int size(){
        return V;
    }

    public static void main(String[] args) {

        UndirectedGraph graph = new UndirectedGraph(4);
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(2, 3);
        System.out.println();
        new DepthFirstSearch(graph, 0);
        System.out.println();
        bfs.bfs(0);
        System.out.println();
        ConnectedComponents cc = new ConnectedComponents(graph);
        System.out.println(cc.count());
    }
}
