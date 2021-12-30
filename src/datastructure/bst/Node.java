package datastructure.bst;

public class Node implements Comparable<Node> {
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public boolean hasChildren() {
        return left != null && right != null;
    }

    @Override
    public int compareTo(Node o) {
        if (value > o.value) return 1;
        else if (value < o.value) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
