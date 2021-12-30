package datastructure.huffman;

public class HuffmanTree {
    private Node root;

    public void build(int[] arr) {
        PriorityQueue queue = new PriorityQueue();
        for (int i = 0; i < arr.length; i++) {
            queue.enqueue(new Node(arr[i]));
        }

        while (queue.size() > 1) {
            Node leftNode = queue.dequeue();
            Node rightNode = queue.dequeue();
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            queue.enqueue(parent);
        }

        root = queue.dequeue();
    }

    public void preorder() {
        preorder(root);
    }

    public void preorder(Node node) {
        if (node != null) {
            System.out.println(node);
            preorder(node.left);
            preorder(node.right);
        }
    }
}

class PriorityQueue {
    private Node[] nodes;
    private int size;

    public PriorityQueue() {
        this(10);
    }

    public PriorityQueue(int capacity) {
        this.nodes = new Node[Math.max(capacity, 10)];
    }

    public void enqueue(Node node) {
        if (node == null) return;
        if (isFull()) increaseCapacity();

        nodes[size++] = node;
        buildHeap();
    }

    private void buildHeap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            adjustHeap(nodes, i, size);
        }
    }

    public void increaseCapacity() {
        Node[] newNodes = new Node[nodes.length + (nodes.length >> 1)];
        int i = 0;
        while (i < nodes.length) {
            newNodes[i] = nodes[i];
            i++;
        }
        nodes = newNodes;
    }

    public Node dequeue() {
        if (isEmpty()) return null;

        Node temp = nodes[0];
        size--;
        nodes[0] = nodes[size];
        nodes[size] = temp;
        adjustHeap(nodes, 0, size);
        return temp;
    }

    private void adjustHeap(Node[] arr, int i, int len) {
        Node temp = arr[i];
        // 小顶堆
        for (int k = i * 2 + 1; k < len; k = k * 2 + 1) {
            if (k + 1 < len && arr[k].compareTo(arr[k + 1]) > 0) {
                k++;
            }

            if (temp.compareTo(arr[k]) > 0) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }

    private boolean isFull() {
        return size == nodes.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PriorityQueue{");
        for (int i = 0; i < size; i++) {
            builder.append(nodes[i].value);
            if (i != size - 1) builder.append(", ");
        }
        return builder.append('}').toString();
    }
}

class Node implements Comparable<Node> {
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
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

class HuffmanTreeDemo {
    public static void main(String[] args) {
        HuffmanTree tree = new HuffmanTree();
        tree.build(new int[]{13, 7, 8, 3, 29, 6, 1});
        tree.preorder();
        // testPriority();
    }

    public static void testPriority() {
        PriorityQueue priorityQueue = new PriorityQueue();
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        for (int i = 0; i < arr.length; i++) {
            priorityQueue.enqueue(new Node(arr[i]));
        }
        System.out.println(priorityQueue);
        System.out.println(priorityQueue.dequeue());
        System.out.println(priorityQueue);
        System.out.println(priorityQueue.dequeue());
        System.out.println(priorityQueue);
        System.out.println(priorityQueue.dequeue());
        System.out.println(priorityQueue);
        System.out.println(priorityQueue.dequeue());
        System.out.println(priorityQueue);
        System.out.println(priorityQueue.dequeue());
        System.out.println(priorityQueue);
        System.out.println(priorityQueue.dequeue());
        System.out.println(priorityQueue);
    }
}
