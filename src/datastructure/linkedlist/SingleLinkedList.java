package datastructure.linkedlist;

import java.util.Stack;

public class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");

    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        while (temp.next != null)
            temp = temp.next;
        temp.next = heroNode;
    }

    public void updateByNo(HeroNode newNode) {
        HeroNode temp = head.next;
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

    public HeroNode findLastIndexOf(int i) {
        if (i <= 0) return null;

        int len = length();
        if (i > len) return null;
        HeroNode temp = head.next;
        for (int j = 0; j < len - i; j++) {
            temp = temp.next;
        }
        return temp;
    }

    public int length() {
        HeroNode temp = head.next;
        int len = 0;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        return len;
    }

    public void deleteByNo(int no) {
        HeroNode temp = head;
        while (temp.next != null) {
            if (temp.next.no == no) {
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }

        System.out.printf("没有找到编号为%d的节点\n", no);
    }

    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        while (temp.next != null) {
            if (temp.next.no > heroNode.no) {
                heroNode.next = temp.next;
                temp.next = heroNode;
                break;
            } else if (temp.next.no == heroNode.no) {
                break;
            }
            temp = temp.next;
        }

        if (temp.next == null) temp.next = heroNode;
    }

    public void reverse() {
        HeroNode reverseHead = new HeroNode(0, "", "");
        HeroNode temp = head.next;
        while (temp != null) {
            HeroNode next = temp.next;
            temp.next = reverseHead.next;
            reverseHead.next = temp;
            temp = next;
        }
        head = reverseHead;
    }

    public void reversePrint() {
        Stack<HeroNode> stack = new Stack<>();

        HeroNode temp = head.next;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }

        while (!stack.empty()) System.out.println(stack.pop());
    }

    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        HeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public static SingleLinkedList mergeByOrder(SingleLinkedList l1, SingleLinkedList l2) {
        if (l1 == null) return l2;
        else if (l2 == null) return l1;
        else if (l1 == l2) return l1;

        HeroNode temp1 = l1.head.next;
        HeroNode temp2 = l2.head.next;

        SingleLinkedList resList = new SingleLinkedList();
        HeroNode current = resList.head;
        while (temp1 != null && temp2 != null) {
            if (temp1.no <= temp2.no) {
                current.next = temp1;
                temp1 = temp1.next;
            } else {
                current.next = temp2;
                temp2 = temp2.next;
            }
            current = current.next;
        }

        current.next = temp1 != null ? temp1 : temp2;

        return resList;
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int hNo, String name, String nickname) {
        this.no = hNo;
        this.name = name;
        this.nickname = nickname;
    }

    public HeroNode(int hNo) {
        this(hNo, "", "");
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList list = new SingleLinkedList();
        list.addByOrder(hero4);
        list.addByOrder(hero1);
        list.addByOrder(hero2);
        list.addByOrder(hero3);
        list.addByOrder(hero2);
        // list.updateByNo(new HeroNode(2, "小卢", "玉麒麟~~"));
        // list.list();
        // System.out.println("======");
        // list.deleteByNo(1);
        // list.deleteByNo(2);
        // list.list();
        // System.out.println("------");
        // System.out.println(list.findLastIndexOf(3));
        // System.out.println(list.length());
        //
        // System.out.println("==========链表反转==========");
        // System.out.println("----前----");
        // list.list();
        // list.reverse();
        // System.out.println("----后----");
        // list.list();
        //
        // System.out.println("==========逆序打印==========");
        // list.reversePrint();

        SingleLinkedList list3 = SingleLinkedList.mergeByOrder(list, list);
        list3.list();
    }
}
