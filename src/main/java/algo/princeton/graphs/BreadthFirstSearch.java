package algo.princeton.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

public class BreadthFirstSearch {

    private final boolean[] marked;
    private final UndirectedGraph graph;
    private final Queue<Integer> queue;


    public BreadthFirstSearch(UndirectedGraph graph) {
        this.marked = new boolean[graph.size()];
        this.graph = graph;
        this.queue = new ArrayDeque<>();
    }

    public void bfs(int vertex) {
        queue.offer(vertex);
        while (!queue.isEmpty()) {
            int currVertx = queue.poll();
            System.out.print(currVertx + "\t");
            marked[currVertx] = true;
            for (int neighbor : graph.adj(currVertx)) {
                if (!marked[neighbor]) {
                    queue.offer(neighbor);
                }
            }
        }
    }
}