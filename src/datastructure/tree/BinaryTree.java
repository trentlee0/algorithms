package datastructure.tree;

public class BinaryTree {
    private BinaryNode root;

    public void preorder(BinaryNode node) {
        if (node != null) {
            System.out.println(node);
            preorder(node.left);
            preorder(node.right);
        }
    }

    public void inorder(BinaryNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node);
            inorder(node.right);
        }
    }

    public void postorder(BinaryNode node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.println(node);
        }
    }

    public BinaryNode preorderSearch(BinaryNode node, int target) {
        if (node != null) {
            // 根节点
            if (node.value == target) return node;

            // 左节点
            BinaryNode res = preorderSearch(node.left, target);
            // 找到就返回
            if (res != null) return res;

            // 右节点
            return preorderSearch(node.right, target);
        }

        return null;
    }

    public BinaryNode inorderSearch(BinaryNode node, int target) {
        if (node != null) {
            BinaryNode res = inorderSearch(node.left, target);
            if (res != null) return res;

            if (node.value == target) return node;

            return inorderSearch(node.right, target);
        }

        return null;
    }

    public BinaryNode postorderSearch(BinaryNode node, int target) {
        if (node != null) {
            BinaryNode res = postorderSearch(node.left, target);
            if (res != null) return res;

            res = postorderSearch(node.right, target);
            if (res != null) return res;

            if (node.value == target) return node;
        }

        return null;
    }

    public BinaryNode deleteNode(BinaryNode node, int target) {
        if (node != null) {
            // 根节点
            if (node.value == target) {
                return node;
            }

            // 左节点
            if (node.left != null) {
                if (node.left.value == target) {
                    BinaryNode temp = node.left;
                    node.left = null;
                    return temp;
                }
                BinaryNode res = deleteNode(node.left, target);
                // 找到就返回
                if (res != null) return res;
            }

            // 右节点
            if (node.right != null) {
                if (node.right.value == target) {
                    BinaryNode temp = node.right;
                    node.right = null;
                    return temp;
                }
                return deleteNode(node.right, target);
            }
        }

        return null;
    }
}

class BinaryNode {
    public int value;
    public BinaryNode left;
    public BinaryNode right;

    public BinaryNode(int value) {
        this.value = value;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public String toString() {
        return "BinaryNode{" +
                "value=" + value +
                '}';
    }
}

class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryNode root = new BinaryNode(1);
        BinaryNode node1 = new BinaryNode(2);
        BinaryNode node2 = new BinaryNode(3);
        BinaryNode node3 = new BinaryNode(4);
        BinaryNode node4 = new BinaryNode(5);
        BinaryNode node5 = new BinaryNode(6);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;

        BinaryTree tree = new BinaryTree();

        // System.out.println("前序遍历：");
        // binaryTree.preorder(root);
        // System.out.println("中序遍历：");
        // binaryTree.inorder(root);
        // System.out.println("后序遍历：");
        // binaryTree.postorder(root);

        // System.out.println("前序查找：\n" + tree.preorderSearch(root, 3));
        // System.out.println("中序查找：\n" + tree.inorderSearch(root, 3));
        // System.out.println("后序查找：\n" + tree.postorderSearch(root, 3));

        System.out.println("删除前：");
        tree.preorder(root);
        System.out.println("删除的节点：\n" + tree.deleteNode(root, 2));
        System.out.println("删除后：");
        tree.preorder(root);
    }
}
