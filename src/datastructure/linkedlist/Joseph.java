package datastructure.linkedlist;

public class Joseph {
    private Node first;

    public void add(Node node) {
        if (first == null) {
            first = node;
            return;
        }

        Node temp = first;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    /**
     * @param startNo  开始数数的节点
     * @param countNum 数几下
     * @param nums     最初总数量
     */
    public void dealJoseph(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) return;
        Node rear = first;
        Node current = first;
        while (rear.next != null) rear = rear.next;
        rear.next = first;

        for (int i = 0; i < startNo - 1; i++) {
            current = current.next;
            rear = rear.next;
        }

        while (rear != current) {
            for (int i = 0; i < countNum - 1; i++) {
                current = current.next;
                rear = rear.next;
            }
            System.out.printf("#%d出圈\n", current.no);
            current = current.next;
            rear.next = current;
        }
        System.out.println("最后的一个为：" + current.no);
    }

    public void list() {
        Node temp = first;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class Node {
    int no;
    Node next;

    public Node() {
    }

    public Node(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                '}';
    }
}

class JosephDemo {
    public static void main(String[] args) {
        Joseph joseph = new Joseph();
        joseph.add(new Node(1));
        joseph.add(new Node(2));
        joseph.add(new Node(3));
        joseph.add(new Node(4));
        joseph.add(new Node(5));

        joseph.dealJoseph(1, 2, 5);
    }
}