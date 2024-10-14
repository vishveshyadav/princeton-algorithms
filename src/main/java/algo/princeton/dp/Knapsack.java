package algo.princeton.dp;

public class Knapsack {

    public int knapsack(int[] value, int[] weight, int w) {

        int v = value.length;
        int[][] dp = new int[v + 1][w + 1];
        for (int i = 0; i <= v; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j <= w; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= v; i++) {
            for (int j = 0; j <= w; j++) {

                if(weight[i -1] <= j){
                    dp[i][j] = Math.max(dp[i-1][j],  dp[i-1][j-weight[i-1]] + value[i-1]);
                }else{
                    dp[i][j] = dp[i-1][j];
                }

            }
        }
        return dp[v][w];
    }

    public static void main(String[] args) {
        Knapsack knapsack = new Knapsack();
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int w = 50;
        System.out.println(knapsack.knapsack(values, weights, w));
    }
}
