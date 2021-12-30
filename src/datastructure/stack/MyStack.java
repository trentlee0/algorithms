package datastructure.stack;

public class MyStack {
    private int maxSize;
    private int[] stack;
    private int top;

    public MyStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
        top = -1;
    }

    public MyStack() {
        this(16);
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        return stack[top--];
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        return stack[top];
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        stack[++top] = value;
    }

    public void list() {
        if (isEmpty()) System.out.println("栈空");
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}

class ArrayStackDemo {
    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.list();
    }
}

