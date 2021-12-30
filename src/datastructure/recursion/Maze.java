package datastructure.recursion;

/**
 * 迷宫问题
 */
public class Maze {
    public static void main(String[] args) {
        // 0 表示该点每走过，1 表示墙，2 表示可以走的通路，3 表示该点已经走过，但是走不通

        int[][] map = new int[8][7];
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        map[3][1] = 1;
        map[3][2] = 1;

        // 起点
        findWay(map, 1, 1);

        show(map);
    }

    public static boolean findWay(int[][] map, int i, int j) {
        // 终点
        if (map[6][5] == 2) return true;

        if (map[i][j] == 0) {
            // 假定走通
            map[i][j] = 2;
            // 约定走迷宫策略，下->右->上->左
            if (findWay(map, i + 1, j)) {
                return true;
            } else if (findWay(map, i, j + 1)) {
                return true;
            } else if (findWay(map, i - 1, j)) {
                return true;
            } else if (findWay(map, i, j - 1)) {
                return true;
            }
            // 走不通
            map[i][j] = 3;
            return false;
        }
        // 不用走了
        return false;
    }

    public static void show(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++)
                System.out.print(arr[i][j] + "  ");
            System.out.println();
        }
    }
}
