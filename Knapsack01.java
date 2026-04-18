import java.util.*;

public class Knapsack01 {

    public static void knapsack(int[] wt, int[] val, int W, int n) {
        int[][] dp = new int[n + 1][W + 1];

        // Build DP table
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    dp[i][w] = 0;
                else if (wt[i - 1] <= w)
                    dp[i][w] = Math.max(
                        val[i - 1] + dp[i - 1][w - wt[i - 1]],
                        dp[i - 1][w]
                    );
                else
                    dp[i][w] = dp[i - 1][w];
            }
        }

        // Maximum value
        System.out.println("Maximum Value = " + dp[n][W]);

        // 🔍 Find selected items
        int w = W;
        System.out.println("Selected items (index starts from 0):");

        for (int i = n; i > 0 && w > 0; i--) {
            // If item is included
            if (dp[i][w] != dp[i - 1][w]) {
                System.out.println("Item " + (i - 1) + 
                    " (Weight=" + wt[i - 1] + ", Value=" + val[i - 1] + ")");
                
                w = w - wt[i - 1]; // reduce remaining capacity
            }
        }
    }

    public static void main(String[] args) {
        int[] val = {15, 25, 30, 30};
        int[] wt = {5, 6, 3, 15};
        int W = 18;

        int n = val.length;

        knapsack(wt, val, W, n);
    }
}
