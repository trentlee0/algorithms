package datastructure.linkedlist;

public class DoubleLinkedList {

    private DoubleHeroNode head = new DoubleHeroNode(0, "", "");

    public DoubleHeroNode getHead() {
        return head;
    }

    public void add(DoubleHeroNode heroNode) {
        DoubleHeroNode temp = head;
        while (temp.next != null)
            temp = temp.next;
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    public void updateByNo(DoubleHeroNode newNode) {
        DoubleHeroNode temp = head.next;
        while (temp != null) {
            if (temp.no == newNode.no) {
                temp.name = newNode.name;
                temp.nickname = newNode.nickname;
                break;
            }
            temp = temp.next;
        }
        if (temp == null) System.out.printf("没有找到编号为%d的节点\n", newNode.no);
    }

    public void deleteByNo(int no) {
        DoubleHeroNode temp = head.next;
        while (temp != null) {
            if (temp.no == no) {
                temp.pre.next = temp.next;
                if (temp.next != null) temp.next.pre = temp.pre;
                return;
            }
            temp = temp.next;
        }

        System.out.printf("没有找到编号为%d的节点\n", no);
    }

    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        DoubleHeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class DoubleHeroNode {
    public int no;
    public String name;
    public String nickname;
    public DoubleHeroNode pre;
    public DoubleHeroNode next;

    public DoubleHeroNode(int hNo, String name, String nickname) {
        this.no = hNo;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "DoubleHeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleHeroNode hero1 = new DoubleHeroNode(1, "宋江", "及时雨");
        DoubleHeroNode hero2 = new DoubleHeroNode(2, "卢俊义", "玉麒麟");
        DoubleHeroNode hero3 = new DoubleHeroNode(3, "吴用", "智多星");
        DoubleHeroNode hero4 = new DoubleHeroNode(4, "林冲", "豹子头");

        DoubleLinkedList list = new DoubleLinkedList();
        list.add(hero4);
        list.add(hero1);
        list.add(hero2);
        list.add(hero3);
        list.updateByNo(new DoubleHeroNode(1, "宋江", "及时雨~~"));
        list.deleteByNo(2);
        list.list();
    }
}
