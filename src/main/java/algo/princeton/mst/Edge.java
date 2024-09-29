package algo.princeton.mst;

public class Edge implements Comparable<Edge> {

    private int v;
    private int w;
    private double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int either() {
        return v;
    }

    public int other(int v) {
        return v == this.v ? w : v;
    }

    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }

    public double weight() {
        return this.weight;
    }

    @Override
    public String toString() {
        return "Edge [v=" + v + ", w=" + w + ", weight=" + weight + "]";
    }
}
