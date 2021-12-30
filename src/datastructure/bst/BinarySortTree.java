package datastructure.bst;

public class BinarySortTree {
    protected Node root;

    /**
     * 添加结点
     */
    public void add(Node node) {
        if (root == null) root = node;
        else add(root, node);
    }

    protected void add(Node cur, Node node) {
        if (node == null) return;

        // 目标结点小于当前结点
        if (cur.compareTo(node) > 0) {
            if (cur.left == null) cur.left = node;
            else add(cur.left, node);
        } else {
            if (cur.right == null) cur.right = node;
            else add(cur.right, node);
        }
    }

    /**
     * 查找目标结点
     */
    public Node search(int value) {
        if (root == null) return null;
        return search(root, value);
    }

    private Node search(Node node, int value) {
        if (node == null) return null;

        // 目标结点小于当前结点
        if (node.value > value) {
            if (node.left == null) return null;
            else return search(node.left, value);
        } else if (node.value < value) {
            if (node.right == null) return null;
            else return search(node.right, value);
        }

        return node;
    }

    /**
     * 查找目标结点的父结点
     */
    public Node searchParent(int value) {
        if (root == null) return null;
        return searchParent(root, value);
    }

    private Node searchParent(Node node, int value) {
        if (node == null) return null;

        // 当前为父结点
        if (node.left != null && node.left.value == value ||
                node.right != null && node.right.value == value) {
            return node;
        } else {
            // 目标结点小于当前结点
            if (node.value > value && node.left != null) {
                return searchParent(node.left, value);
            } else if (node.value < value && node.right != null) {
                return searchParent(node.right, value);
            } else {
                return null;
            }
        }
    }

    /**
     * 删除值为 value 的结点
     */
    public Node remove(int value) {
        if (root == null) return null;

        Node target = search(value);
        if (target == null) return null;

        if (root.isLeaf()) {
            if (root.value == value) {
                root = null;
                return target;
            }
            return null;
        }

        Node parent = searchParent(value);

        if (target.isLeaf()) {
            // 目标为左孩子
            if (parent.left != null && parent.left.value == value) {
                parent.left = null;
            } else if (parent.right != null && parent.right.value == value) {
                parent.right = null;
            }
        } else if (target.hasChildren()) {
            // 删除当前结点并用右子树的最小结点代替
            Node node = removeMin(target.right);
            target.value = node.value;
        } else {
            // 只有左孩子
            if (target.left != null) {
                if (parent != null) {
                    // 目标是左孩子
                    if (parent.left.value == value) {
                        parent.left = target.left;
                    } else {
                        parent.right = target.left;
                    }
                } else {
                    root = target.left;
                }
            } else {
                if (parent != null) {
                    // 目标是左孩子
                    if (parent.left.value == value) {
                        parent.left = target.right;
                    } else {
                        parent.right = target.right;
                    }
                } else {
                    root = target.right;
                }
            }
        }

        return target;
    }

    /**
     * 以 node 为根结点的子树的最小值，并删除它
     */
    private Node removeMin(Node node) {
        Node target = node;
        while (target.left != null) {
            target = target.left;
        }
        return remove(target.value);
    }

    /**
     * 中序遍历
     */
    public void inorder() {
        inorder(root);
    }

    private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node);
            inorder(node.right);
        }
    }
}

class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree tree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            tree.add(new Node(arr[i]));
        }
        tree.inorder();
        System.out.println("-------");
        tree.remove(2);
        tree.remove(5);
        tree.remove(9);
        tree.remove(12);
        tree.remove(7);
        tree.remove(3);
        tree.remove(1);
        // tree.remove(10);
        tree.inorder();
    }
}
