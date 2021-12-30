package datastructure.hashtable;

public class HashTable {
    private LinkedList[] linkedLists;
    private int capacity;
    private int size;

    public HashTable() {
        this(8);
    }

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.linkedLists = new LinkedList[capacity];
        this.size = 0;
        for (int i = 0; i < capacity; i++)
            this.linkedLists[i] = new LinkedList();
    }

    public int size() {
        return size;
    }

    public int hash(int num) {
        if (num < 0) num = -num;
        return num % capacity;
    }

    public void put(int key, String value) {
        size++;
        linkedLists[hash(key)].add(new Node(key, value));
    }

    public String remove(int key) {
        Node node = linkedLists[hash(key)].remove(key);
        if (node == null) return null;
        size--;
        return node.value;
    }

    public String get(int key) {
        Node node = linkedLists[hash(key)].find(key);
        return node == null ? null : node.value;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('{');
        for (int i = 0; i < capacity; i++) {
            builder.append(linkedLists[i]);
            if (i != capacity - 1) builder.append(", ");
        }
        return builder.append('}').toString();
    }
}

class Node {
    public int key;
    public String value;
    public Node next;

    public Node(int key, String value) {
        this(key, value, null);
    }

    public Node(int key, String value, Node next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    @Override
    public String toString() {
        return key + ":\"" + value + '"';
    }
}

class LinkedList {
    private Node head;
    private Node last;

    public LinkedList() {
        head = new Node(-1, null);
        last = null;
    }

    public void add(Node node) {
        if (isEmpty()) head.next = node;
        else last.next = node;
        last = node;
    }

    public Node find(int key) {
        Node temp = head.next;
        while (temp != null) {
            if (key == temp.key) return temp;
            temp = temp.next;
        }
        return null;
    }

    public Node remove(int key) {
        Node temp = head;
        while (temp.next != null) {
            if (key == temp.next.key) {
                Node remove = temp.next;
                temp.next = temp.next.next;
                return remove;
            }
            temp = temp.next;
        }
        return null;
    }

    public boolean isEmpty() {
        return last == null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        Node temp = head.next;
        while (temp != null) {
            builder.append(temp);
            temp = temp.next;
            if (temp != null) builder.append(", ");
        }
        return builder.append(']').toString();
    }
}

class HashTableDemo {
    public static void main(String[] args) {
        HashTable table = new HashTable(3);
        table.put(1, "张三");
        table.put(2, "李四");
        table.put(3, "王五");
        table.put(4, "赵六");
        System.out.println(table);
        System.out.println(table.size());
        table.remove(1);
        System.out.println(table.size());
        System.out.println(table);

        System.out.println(table.get(-100));
        System.out.println(table.get(2));
        System.out.println(table.get(1));
        System.out.println(table.get(10));
    }
}