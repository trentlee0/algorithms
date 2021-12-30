package algorithm.greedy;

public class Graph {
    public static final double INF = Double.POSITIVE_INFINITY;

    private final double[][] edges;
    private final String[] vertexes;

    public Graph(int vertexSize) {
        edges = new double[vertexSize][vertexSize];
        vertexes = new String[vertexSize];
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges.length; j++) {
                edges[i][j] = INF;
            }
        }
    }

    public void setEdgeWeight(int v1, int v2, double cost) {
        edges[v1][v2] = cost;
        edges[v2][v1] = cost;
    }

    public double getEdgeWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    public void setVertexName(int v, String name) {
        vertexes[v] = name;
    }

    public String getVertexName(int v) {
        return vertexes[v];
    }

    public int edgeCount() {
        return edges.length;
    }

    public int vertexCount() {
        return vertexes.length;
    }

    public void setVertexNames(String... names) {
        int i = 0;
        while (i < names.length && i < vertexCount()) {
            setVertexName(i, names[i]);
            i++;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (double[] edge : edges) {
            builder.append('[');
            for (int j = 0; j < edgeCount(); j++)
                builder.append(edge[j] == INF ? "INF" : edge[j])
                        .append(j != edgeCount() - 1 ? ", " : "");
            builder.append("]\n");
        }
        return builder.toString();
    }
}
