package algorithm.greedy;

import java.util.*;

public class Prim {
    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.setVertexNames("1", "2", "3", "4", "5", "6");
        graph.setEdgeWeight(1, 0, 6);
        graph.setEdgeWeight(2, 0, 1);
        graph.setEdgeWeight(2, 1, 5);
        graph.setEdgeWeight(3, 0, 5);
        graph.setEdgeWeight(3, 2, 5);
        graph.setEdgeWeight(4, 1, 3);
        graph.setEdgeWeight(4, 2, 6);
        graph.setEdgeWeight(5, 2, 4);
        graph.setEdgeWeight(5, 3, 2);
        graph.setEdgeWeight(5, 4, 6);

        prim(graph);
    }

    /**
     * Prim 算法
     */
    public static void prim(Graph graph) {
        prim(graph, 0);
    }

    public static void prim(Graph graph, int start) {
        int n = graph.vertexCount();
        // 记录选的边
        int[] prev = new int[n];
        List<Integer> list = new LinkedList<>();

        // 已选顶点的集合
        boolean[] selected = new boolean[n];
        // 已选顶点集合到未选顶点集合的最短边权
        double[] lowCost = new double[n];

        for (int i = 0; i < n; i++) {
            prev[i] = start;
            lowCost[i] = graph.getEdgeWeight(start, i);
        }
        selected[start] = true;

        for (int i = 0; i < n; i++) {
            double min = Graph.INF;
            int t = -1;
            // 选择最短边权
            for (int j = 0; j < n; j++) {
                if (!selected[j] && lowCost[j] < min) {
                    min = lowCost[j];
                    t = j;
                }
            }

            // 没找到
            if (t == -1) continue;

            selected[t] = true;

            // 更新 lowCost 和 prev
            for (int j = 0; j < n; j++) {
                if (!selected[j] && graph.getEdgeWeight(t, j) < lowCost[j]) {
                    lowCost[j] = graph.getEdgeWeight(t, j);
                    prev[j] = t;
                }
            }
            list.add(t);
        }

        // 打印结果
        list.forEach(i -> System.out.printf(
                "<%s,%s> = %.1f\n",
                graph.getVertexName(prev[i]),
                graph.getVertexName(i),
                graph.getEdgeWeight(prev[i], i)
        ));
    }
}
