package algorithm.greedy;

public class UnionFind {
    private int[] parent;
    private int[] weight;
    private int count;

    public UnionFind() {
    }

    public UnionFind(int n) {
        make(n);
    }

    public void make(int n) {
        parent = new int[n];
        weight = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            weight[i] = 1;
        }
    }

    public int getCount() {
        return count;
    }

    public int find(int i) {
        if (i != parent[i]) parent[i] = find(parent[i]);
        return i;
    }

    public boolean isConnected(int i, int j) {
        return find(i) == find(j);
    }

    public void union(int from, int to) {
        int fromRoot = find(from);
        int toRoot = find(to);

        if (fromRoot == toRoot) return;

        if (weight[fromRoot] <= weight[toRoot]) {
            parent[fromRoot] = toRoot;
            weight[toRoot] += weight[fromRoot];
        } else {
            parent[toRoot] = fromRoot;
            weight[fromRoot] += weight[toRoot];
        }
        count--;
    }
}
