package datastructure.bst.avl;

import datastructure.bst.BinarySortTree;
import datastructure.bst.Node;

public class AVLTree extends BinarySortTree {
    /**
     * 树的高度
     */
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node != null) {
            int l = height(node.left);
            int r = height(node.right);
            return Math.max(l, r) + 1;
        }
        return 0;
    }

    /**
     * 根的左子树高度
     */
    public int leftHeight() {
        return leftHeight(root);
    }

    private int leftHeight(Node node) {
        if (node == null) return 0;
        return height(node.left);
    }

    /**
     * 根的右子树高度
     */
    public int rightHeight() {
        return rightHeight(root);
    }

    private int rightHeight(Node node) {
        if (node == null) return 0;
        return height(node.right);
    }

    /**
     * 左旋转
     */
    public void leftRotate() {
        leftRotate(root);
    }

    private void leftRotate(Node node) {
        // 将右子树最小的变为根的右结点，而原根的右结点变为根结点，原根的左结点变为新根的左结点
        Node newNode = new Node(node.value);
        newNode.left = node.left;
        newNode.right = node.right.left;
        node.value = node.right.value;
        node.right = node.right.right;
        node.left = newNode;
    }

    /**
     * 右旋转
     */
    public void rightRotate() {
        rightRotate(root);
    }

    private void rightRotate(Node node) {
        Node newNode = new Node(node.value);
        newNode.right = node.right;
        newNode.left = node.left.right;
        node.value = node.left.value;
        node.left = node.left.left;
        node.right = newNode;
    }

    @Override
    public void add(Node node) {
        if (root == null) root = node;
        else add(root, node);
    }

    @Override
    protected void add(Node cur, Node node) {
        super.add(cur, node);
        // 右子树高度比左子树大于1
        if (rightHeight(cur) - leftHeight(cur) > 1) {
            // 右结点的左子树大于右结点的右子树
            if (cur.right != null && leftHeight(cur.right) > rightHeight(cur.right)) {
                rightRotate(cur.right);
            }
            leftRotate(cur);
        } else if (leftHeight(cur) - rightHeight(cur) > 1) {
            if (cur.left != null && rightHeight(cur.left) > leftHeight(cur.left)) {
                leftRotate(cur.left);
            }
            rightRotate(cur);
        }
    }
}

class AVLTreeDemo {
    public static void main(String[] args) {
        // int[] arr = {4, 3, 6, 5, 7, 8};
        // int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree tree = new AVLTree();
        for (int val : arr) {
            tree.add(new Node(val));
        }
        tree.inorder();
        System.out.println(tree.height());
        System.out.println(tree.leftHeight());
        System.out.println(tree.rightHeight());
    }
}