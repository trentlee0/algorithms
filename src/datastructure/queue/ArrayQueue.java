package datastructure.queue;

public class ArrayQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public ArrayQueue(int size) {
        maxSize = size;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        arr[++rear] = n;
    }

    public int getQueue() {
        if (isEmpty())
            throw new RuntimeException("队列为空");
        return arr[++front];
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = front + 1; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    public int peak() {
        if (isEmpty())
            throw new RuntimeException("队列为空");
        return arr[front + 1];
    }
}

class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        queue.addQueue(2);
        queue.addQueue(5);
        queue.addQueue(7);
        queue.showQueue();
        System.out.println("出队：" + queue.getQueue());
        System.out.println("出队：" + queue.getQueue());
        queue.showQueue();
    }
}