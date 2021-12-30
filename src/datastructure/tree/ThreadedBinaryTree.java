package datastructure.tree;

public class ThreadedBinaryTree {
    private ThreadedBinaryNode root;
    private ThreadedBinaryNode pre;

    public ThreadedBinaryTree(ThreadedBinaryNode root) {
        this.root = root;
    }

    public void inThreadedNodes() {
        inThreadedNodes(root);
    }

    public void inThreadedNodes(ThreadedBinaryNode node) {
        if (node == null) return;

        // 中序线索二叉树
        inThreadedNodes(node.left);
        // 前驱结点
        if (node.left == null) {
            node.left = pre;
            node.leftTag = true;
        }
        // 后继结点
        if (pre != null && pre.right == null) {
            pre.right = node;
            pre.rightTag = true;
        }
        pre = node;
        inThreadedNodes(node.right);
    }

    public void inorderThreaded() {
        ThreadedBinaryNode node = root;

        // 中序遍历
        while (node != null) {
            // 左结点
            while (!node.leftTag) node = node.left;
            System.out.println(node);
            // 根结点，根据线索获取根结点
            while (node.rightTag) {
                node = node.right;
                System.out.println(node);
            }
            // 右结点
            node = node.right;
        }
    }

    public void preThreadedNodes(ThreadedBinaryNode node) {
        if (node == null) return;

        if (node.left == null) {
            node.left = pre;
            node.leftTag = true;
        }
        if (pre != null && pre.right == null) {
            pre.right = node;
            pre.rightTag = true;
        }
        pre = node;
        // 已经线索化就不需要再线索化了，防止出现递归
        if (!node.leftTag) preThreadedNodes(node.left);
        if (!node.rightTag) preThreadedNodes(node.right);
    }

    public void preorderThreaded() {
        ThreadedBinaryNode node = root;

        while (node != null) {
            // 根结点，一直到遍历到叶子结点
            while (!node.leftTag) {
                System.out.println(node);
                node = node.left;
            }
            // 左结点
            System.out.println(node);
            // 右结点
            if (node.rightTag) {
                node = node.right;
                System.out.println(node);
            }
            node = node.right;
        }
    }
}

class ThreadedBinaryNode {
    public int value;
    public ThreadedBinaryNode left;
    public ThreadedBinaryNode right;
    public boolean leftTag;
    public boolean rightTag;

    public ThreadedBinaryNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ThreadedBinaryNode{" +
                "value=" + value +
                ", leftTag=" + leftTag +
                ", rightTag=" + rightTag +
                '}';
    }
}

class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        ThreadedBinaryNode root = new ThreadedBinaryNode(1);
        ThreadedBinaryNode node2 = new ThreadedBinaryNode(2);
        ThreadedBinaryNode node3 = new ThreadedBinaryNode(3);
        ThreadedBinaryNode node4 = new ThreadedBinaryNode(4);
        ThreadedBinaryNode node5 = new ThreadedBinaryNode(5);
        ThreadedBinaryNode node6 = new ThreadedBinaryNode(6);

        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;

        ThreadedBinaryTree tree = new ThreadedBinaryTree(root);
        tree.preThreadedNodes(root);
        tree.preorderThreaded();
        show(node4);
        show(node5);
    }

    public static void show(ThreadedBinaryNode node) {
        if (node.leftTag)
            System.out.println(node.value + " pre is " + node.left.value);
        if (node.rightTag)
            System.out.println(node.value + " next is " + node.right.value);
    }
}
