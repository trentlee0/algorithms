package datastructure.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
    private List<String> vertexes;
    private int[][] edges;
    private int edgeCount;

    public Graph(int n) {
        edges = new int[n][n];
        vertexes = new ArrayList<>(n);
        edgeCount = 0;
    }

    public void insertVertex(String vertex) {
        vertexes.add(vertex);
    }

    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        edgeCount++;
    }

    public String getVertexString(int v) {
        return vertexes.get(v);
    }

    public int getVertexWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    public int vertexSize() {
        return vertexes.size();
    }

    public int edgeSize() {
        return edgeCount;
    }

    /**
     * 深度优先遍历
     */
    public void dfs() {
        int n = vertexSize();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) dfs(visited, i);
        }
        System.out.println();
    }

    private void dfs(boolean[] visited, int vertex) {
        if (vertex < 0) return;
        if (visited[vertex]) return;

        // 访问该顶点
        System.out.printf("%-4s", vertexes.get(vertex));
        visited[vertex] = true;

        // 获取该顶点第一个邻接顶点
        int v = getFirstNeighbor(vertex);
        while (v != -1) {
            dfs(visited, v);
            // 获取下一个邻接顶点
            v = getNextNeighbor(vertex, v + 1);
        }
    }

    private int getFirstNeighbor(int vertex) {
        return getNextNeighbor(vertex, 0);
    }

    private int getNextNeighbor(int vertex, int startInVertex) {
        if (startInVertex < 0) return -1;
        for (int j = startInVertex; j < vertexSize(); j++) {
            if (edges[vertex][j] != 0) return j;
        }
        return -1;
    }

    /**
     * 广度优先遍历
     */
    public void bfs() {
        int n = vertexSize();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) bfs(visited, i);
        }
        System.out.println();
    }

    private void bfs(boolean[] visited, int vertex) {
        if (vertex < 0) return;
        if (visited[vertex]) return;

        System.out.printf("%-4s", vertexes.get(vertex));
        visited[vertex] = true;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);
        int u;
        int v;

        while (!queue.isEmpty()) {
            // 从队列中取出再进行广度优先遍历
            u = queue.poll();
            // 获取该顶点第一个邻接顶点
            v = getFirstNeighbor(u);
            // 广度优先
            while (v != -1) {
                if (!visited[v]) {
                    System.out.printf("%-4s", vertexes.get(v));
                    visited[v] = true;
                    queue.add(v);
                }
                // 获取下一个邻接顶点
                v = getNextNeighbor(u, v + 1);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int[] edge : edges) {
            for (int v : edge)
                builder.append(String.format("%-4d", v));
            builder.append('\n');
        }
        return builder.toString();
    }
}

class GraphDemo {
    public static void main(String[] args) {
        // String[] arr = {"A", "B", "C", "D", "E"};
        String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8"};
        Graph graph = new Graph(arr.length);
        for (int i = 0; i < arr.length; i++) {
            graph.insertVertex(arr[i]);
        }
        // graph.insertEdge(0, 1, 1);
        // graph.insertEdge(0, 2, 1);
        // graph.insertEdge(1, 2, 1);
        // graph.insertEdge(1, 3, 1);
        // graph.insertEdge(1, 4, 1);

        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        // System.out.println(graph);
        System.out.print("DFS：");
        graph.dfs();
        System.out.print("BFS：");
        graph.bfs();
    }
}