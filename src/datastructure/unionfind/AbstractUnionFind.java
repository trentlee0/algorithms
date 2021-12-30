package datastructure.unionfind;

/**
 * 并查集基本方法
 */
public abstract class AbstractUnionFind {
    /**
     * p 对应的集合
     */
    public abstract int find(int i);

    /**
     * 是否连通
     */
    public boolean isConnected(int i, int j) {
        return find(i) == find(j);
    }

    /**
     * 合并 from 对应的集合为 to 对应的集合
     */
    public abstract void union(int from, int to);

    /**
     * 集合的个数
     */
    public abstract int getCount();
}

/**
 * 简单并查集
 */
class SimpleUnionFind extends AbstractUnionFind {
    private int[] id;
    private int count;

    public SimpleUnionFind(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) id[i] = i;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public int find(int i) {
        return id[i];
    }

    @Override
    public void union(int from, int to) {
        int fromRoot = find(from);
        int toRoot = find(to);

        if (fromRoot == toRoot) return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == fromRoot) id[i] = toRoot;
        }
        count--;
    }
}

/**
 * 优化的并查集
 */
class QuickUnionFind extends AbstractUnionFind {
    private int[] id;
    private int count;

    public QuickUnionFind(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) id[i] = i;
    }


    @Override
    public int getCount() {
        return count;
    }

    @Override
    public int find(int i) {
        while (i != id[i]) i = id[i];
        return i;
    }

    @Override
    public void union(int from, int to) {
        int fromRoot = find(from);
        int toRoot = find(to);

        if (fromRoot == toRoot) return;

        id[fromRoot] = toRoot;
        count--;
    }
}

/**
 * 加权值优化的并查集
 */
class WeightedQuickUnionFind extends AbstractUnionFind {
    private int[] id;
    private int count;
    protected int[] weight;

    public WeightedQuickUnionFind(int n) {
        id = new int[n];
        weight = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            id[i] = i;
            weight[i] = 1;
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public int find(int i) {
        while (i != id[i]) i = id[i];
        return i;
    }

    @Override
    public void union(int from, int to) {
        int fromRoot = find(from);
        int toRoot = find(to);

        if (fromRoot == toRoot) return;

        if (weight[fromRoot] <= weight[toRoot]) {
            id[fromRoot] = toRoot;
            weight[toRoot] += weight[fromRoot];
        } else {
            id[toRoot] = fromRoot;
            weight[fromRoot] += weight[toRoot];
        }
        count--;
    }
}

/**
 * 最终版并查集
 */
class UnionFind extends AbstractUnionFind {
    /**
     * 存储 i 对应的集合
     */
    private int[] parent;

    /**
     * 每个集合对应的权重，权重小的依附于权重大的
     */
    protected int[] weight;

    /**
     * 集合总数
     */
    private int count;

    public UnionFind(int n) {
        parent = new int[n];
        weight = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            weight[i] = 1;
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public int find(int i) {
        if (i != parent[i]) parent[i] = find(parent[i]);
        return parent[i];
    }

    @Override
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