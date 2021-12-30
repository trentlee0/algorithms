package algorithm.greedy;

public class Dijkstra {
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

        dijkstra(graph);
    }

    /**
     * Dijkstra 算法
     */
    public static void dijkstra(Graph graph) {
        dijkstra(graph, 0);
    }

    public static void dijkstra(Graph graph, int start) {
        int n = graph.vertexCount();
        // 记录选的边
        int[] prev = new int[n];

        // 已选的顶点
        boolean[] selected = new boolean[n];
        // 从 start 到已选顶点最短边权
        double[] dist = new double[n];

        for (int i = 0; i < n; i++) {
            prev[i] = start;
            dist[i] = graph.getEdgeWeight(start, i);
        }
        selected[start] = true;

        for (int i = 0; i < n; i++) {
            double min = Graph.INF;
            int t = -1;
            // 选择最短边权
            for (int j = 0; j < n; j++) {
                if (!selected[j] && dist[j] < min) {
                    min = dist[j];
                    t = j;
                }
            }

            // 没找到
            if (t == -1) continue;

            selected[t] = true;

            // 更新 dist 和 prev
            for (int j = 0; j < n; j++) {
                if (!selected[j] && dist[t] + graph.getEdgeWeight(t, j) < dist[j]) {
                    dist[j] = dist[t] + graph.getEdgeWeight(t, j);
                    prev[j] = t;
                }
            }
        }

        // 打印结果
        System.out.printf("源 %s 到各个顶点的最短路径：\n", graph.getVertexName(start));
        showPath(graph, prev, start);
    }

    private static void showPath(Graph graph, int[] prev, int start) {
        for (int i = 0; i < prev.length; i++) {
            System.out.print(graph.getVertexName(start));
            showPath(graph, prev, start, i);
            System.out.println();
        }
    }

    private static void showPath(Graph graph, int[] prev, int start, int i) {
        if (i == start) return;
        if (i == -1) System.out.println("到不了");

        showPath(graph, prev, start, prev[i]);
        System.out.print(graph.getVertexName(i));
    }
}
