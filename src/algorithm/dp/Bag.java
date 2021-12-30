package algorithm.dp;

public class Bag {
    public static void main(String[] args) {
        int c = 4;
        int[] weights = {1, 4, 3};
        int[] prices = {1500, 3000, 2000};
        System.out.printf("best price: %d\n", getBestPrice(c, weights, prices));
    }

    public static int getBestPrice(int c, int[] weights, int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][c + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= c; j++) {
                dp[i][j] = weights[i - 1] > j ? dp[i - 1][j] : Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + prices[i - 1]);
            }
        }

        return dp[n][c];
    }
}
