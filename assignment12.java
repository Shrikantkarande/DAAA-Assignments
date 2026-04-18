import java.util.Scanner;

public class assignment12 {
    static int recFibo(int n) {
        if (n <= 1) {
            return n;
        }

        return recFibo(n - 1) + recFibo(n - 2);
    }

    static int memoFibo(int[] dp, int n) {
        if (n <= 1) {
            return n;
        }

        if (dp[n] != -1) {
            return dp[n];
        }

        return dp[n] = memoFibo(dp, n - 1) + memoFibo(dp, n - 2);
    }

    static int TabuFibo(int n) {
        if (n <= 1) {
            return n;
        }

        int first = 0;
        int second = 1;

        for (int i = 2; i <= n; i++) {
            int temp = second;
            second = first + second;
            first = temp;
        }

        return second;
    }

    static int takeValidInput() {
        Scanner sc = new Scanner(System.in);
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
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Find nth fibonacci");
            System.out.println("2. exit");
            System.out.print("Enter your choice: ");

            int choice = takeValidInput();

            switch (choice) {
                case 1:
                    System.out.println("\n1. Find using recursion");
                    System.out.println("2. Find using memoization");
                    System.out.println("3. Find using tabulation");
                    System.out.print("Enter your choice: ");
                    
                    int choice2 = takeValidInput();

                    System.out.print("Enter n: ");
                    int n = takeValidInput();

                    switch (choice2) {
                        case 1:
                            System.out.println(recFibo(n));
                            break;

                        case 2:
                            int[] dp = new int[n + 1];
                            for (int i = 0; i <= n; i++) {
                                dp[i] = -1;
                            }
                            System.out.println(memoFibo(dp, n));
                            break;

                        case 3:
                            System.out.println(TabuFibo(n));
                            break;
                    
                        default:
                            break;
                    }

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
