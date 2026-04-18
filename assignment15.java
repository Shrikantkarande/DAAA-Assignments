import java.util.Scanner;

public class assignment15 {

    static Scanner sc = new Scanner(System.in);

    public static void knapsack(int[] wt, int[] val, int W, int n) {
        int[][] dp = new int[n + 1][W + 1];

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

        System.out.println("Maximum Value = " + dp[n][W]);

        int w = W;
        System.out.println("Selected items:");

        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                System.out.println("Item " + (i - 1) + 
                    " (Weight=" + wt[i - 1] + ", Value=" + val[i - 1] + ")");
                
                w = w - wt[i - 1];
            }
        }
    }

    static int takeValidInput() {

        int target;

        while (true) {

            if (sc.hasNextInt()) {
                target = sc.nextInt();
                break;
            } else {
                System.out.println("Invalid input! Please enter a valid integer.");
                sc.next();
            }
        }

        return target;
    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n1. Find max profit");
            System.out.println("2. exit");
            System.out.print("Enter your choice: ");

            int choice = takeValidInput();

            switch (choice) {
                case 1:
                    System.out.print("Enter length of the array: ");
                    int n = takeValidInput();

                    int[] val = new int[n];
                    System.out.print("Enter elements of val array: ");
                    for (int i = 0; i < n; i++) {
                        val[i] = takeValidInput();
                    }

                    int[] wt = new int[n];
                    System.out.print("Enter elements of wt array: ");
                    for (int i = 0; i < n; i++) {
                        wt[i] = takeValidInput();
                    }

                    System.out.print("Enter capacity of knapsack: ");
                    int w = takeValidInput();

                    knapsack(wt, val, w, n);

                    break;

                case 2:
                    System.out.println("Exiting program...");
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        }
    }
}
