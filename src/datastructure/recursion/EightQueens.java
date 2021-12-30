package datastructure.recursion;

/**
 * 八皇后问题
 */
public class EightQueens {
    public static void main(String[] args) {
        int i = check();
        System.out.printf("摆法有 %d 种", i);
    }

    private static int eight = 8;
    // 保存皇后放置的位置，下标代表行，值代表列
    private static int[] queens = new int[eight];
    // 摆法总数
    private static int count = 0;

    public static int check() {
        // 从第一列开始
        check(0);
        return count;
    }

    /**
     * 放置皇后
     */
    private static void check(int n) {
        if (n >= eight) {
            count++;
            show();
            return;
        }

        // 依次放入皇后
        for (int j = 0; j < eight; j++) {
            queens[n] = j;
            // 不冲突
            if (judge(n)) {
                check(n + 1);
            }
            // 冲突，回溯 j++
        }
    }

    /**
     * 判断是否冲突
     */
    private static boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // 在同一列，或同一斜线（斜率为1）
            if (queens[i] == queens[n] ||
                    Math.abs(n - i) == Math.abs(queens[n] - queens[i])) {
                return false;
            }
        }

        return true;
    }

    private static void show() {
        for (int j : queens) {
            System.out.printf("%-2d", j);
        }
        System.out.println();
    }
}
