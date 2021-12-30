package algorithm.divide;

public class HanoiTower {
    public static void main(String[] args) {
        hanoi(3, 'A', 'B', 'C');
    }

    public static void hanoi(int num, char from, char with, char to) {
        if (num == 1) {
            System.out.printf("第%d个盘子 %c-->%c\n", num, from, to);
            return;
        }
        // A -> B
        hanoi(num - 1, from, to, with);
        // A -> C
        System.out.printf("第%d个盘子 %c-->%c\n", num, from, to);
        // B -> C
        hanoi(num - 1, with, from, to);
    }
}
