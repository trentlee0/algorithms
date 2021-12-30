package algorithm.greedy;

import java.util.*;

public class Kruskal {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.setEdgeWeight(1, 2, 6);
        graph.setEdgeWeight(1, 3, 1);
        graph.setEdgeWeight(1, 4, 5);
        graph.setEdgeWeight(2, 3, 5);
        graph.setEdgeWeight(2, 5, 3);
        graph.setEdgeWeight(3, 4, 5);
        graph.setEdgeWeight(3, 5, 6);
        graph.setEdgeWeight(3, 6, 4);
        graph.setEdgeWeight(4, 6, 2);
        graph.setEdgeWeight(5, 6, 6);

        kruskal(graph);
    }

    /**
     * Kruskal 算法
     */
    public static void kruskal(Graph graph) {
        List<Graph.Edge> list = new LinkedList<>();

        PriorityQueue<Graph.Edge> queue = new PriorityQueue<>();
        queue.addAll(graph.edges);
        UnionFind unionFind = new UnionFind(graph.vertexCount());

        // 记录选的边个数
        int i = 1;
        while (i < graph.vertexCount()) {
            // 每次选最小权值的边
            Graph.Edge edge = queue.poll();
            // 该边与已选的边不是同一个集合，说明不构成回路
            if (!unionFind.isConnected(edge.from - 1, edge.to - 1)) {
                list.add(edge);
                // 选中这条边之后，把这条边连接的两个节点合为一个整体
                unionFind.union(edge.from - 1, edge.to - 1);
                i++;
            }
        }

        // 打印结果
        list.forEach(System.out::println);
    }

    public static class Graph {
        private final List<Edge> edges;
        private final Set<Integer> vertexes;

        public Graph() {
            edges = new ArrayList<>();
            vertexes = new HashSet<>();
        }

        public double getEdgeWeight(int i) {
            return edges.get(i).weight;
        }

        public void setEdgeWeight(int from, int to, double weight) {
            vertexes.add(from);
            vertexes.add(to);
            edges.add(new Edge(from, to, weight));
        }

        public int edgeCount() {
            return edges.size();
        }

        public int vertexCount() {
            return vertexes.size();
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            edges.forEach(builder::append);
            return builder.toString();
        }

        public static class Edge implements Comparable<Edge> {
            public int from;
            public int to;
            public double weight;

            public Edge(int from, int to, double weight) {
                this.weight = weight;
                this.from = from;
                this.to = to;
            }

            @Override
            public int compareTo(Edge o) {
                return Double.compare(weight, o.weight);
            }

            @Override
            public String toString() {
                return String.format("<%d,%d> = %.1f", from, to, weight);
            }
        }
    }
}
