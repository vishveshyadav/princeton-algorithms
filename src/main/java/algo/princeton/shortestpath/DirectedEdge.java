package algo.princeton.shortestpath;

public class DirectedEdge implements Comparable<DirectedEdge> {

    private int v;
    private int w;
    private double weight;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public String toString(){
        return "[ v = " + v + ", w = " + w + ", weight = " + weight + "]";
    }

    public int from(){
        return this.v;
    }

    public int to(){
        return this.w;
    }

    public double weight(){
        return this.weight;
    }

    @Override
    public int compareTo(DirectedEdge that) {
        return Double.compare(this.weight, that.weight);
    }
}
