package datastructure.queue;

public class CircleArrayQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public CircleArrayQueue(int size) {
        maxSize = size + 1;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }


    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    public int getQueue() {
        if (isEmpty())
            throw new RuntimeException("队列为空");
        int temp = arr[front];
        front = (front + 1) % maxSize;
        return temp;
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    public int size() {
        return (maxSize - front + rear) % maxSize;
    }

    public int peak() {
        if (isEmpty())
            throw new RuntimeException("队列为空");
        return arr[front];
    }
}

class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(3);
        queue.addQueue(2);
        queue.addQueue(5);
        queue.addQueue(7);
        queue.showQueue();
        System.out.println("出队：" + queue.getQueue());
        System.out.println("出队：" + queue.getQueue());
        queue.addQueue(9);
        queue.addQueue(11);
        queue.showQueue();
        System.out.println("队头：" + queue.peak());
    }
}