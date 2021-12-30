package datastructure.tree;

public class ArrayBinaryTree {
    private int[] arr;
    private int size;

    public ArrayBinaryTree(int... arr) {
        this.arr = arr;
        size = arr.length;
    }

    public void preorder() {
        preorder(0);
    }

    public void inorder() {
        inorder(0);
    }

    public void postorder() {
        postorder(0);
    }

    public void preorder(int i) {
        if (arr != null && size > 0 && i < size) {
            System.out.println(arr[i]);
            preorder(2 * i + 1);
            preorder(2 * i + 2);
        }
    }

    public void inorder(int i) {
        if (arr != null && size > 0 && i < size) {
            inorder(2 * i + 1);
            System.out.println(arr[i]);
            inorder(2 * i + 2);
        }
    }

    public void postorder(int i) {
        if (arr != null && size > 0 && i < size) {
            postorder(2 * i + 1);
            postorder(2 * i + 2);
            System.out.println(arr[i]);
        }
    }
}

class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree tree = new ArrayBinaryTree(arr);
        System.out.println("前序遍历：");
        tree.preorder();
        System.out.println("中序遍历：");
        tree.inorder();
        System.out.println("后序遍历：");
        tree.postorder();
    }
}
