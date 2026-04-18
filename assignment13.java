import java.util.Scanner;

public class assignment13 {

    static Scanner sc = new Scanner(System.in);

    public static String lcs(String str1, String str2, int n, int m, String dp[][]) {
        if (n == 0 || m == 0) {
            return "";
        }
        if (dp[n][m] != null) {
            return dp[n][m];
        }
        if (str1.charAt(n - 1) == str2.charAt(m - 1)) {// same
            return dp[n][m] = lcs(str1, str2, n - 1, m - 1, dp) + str1.charAt(n - 1);
        } else {// diff
            String left = lcs(str1, str2, n - 1, m, dp);
            String right = lcs(str1, str2, n, m - 1, dp);

            if (left.length() > right.length()) {
                return dp[n][m] = left;
            } else {
                return dp[n][m] = right;
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
            System.out.println("\n1. Find lcs");
            System.out.println("2. exit");
            System.out.print("Enter your choice: ");

            int choice = takeValidInput();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter str1: ");
                    String str1 = sc.nextLine();

                    System.out.print("Enter str2: ");
                    String str2 = sc.nextLine();

                    int n = str1.length();
                    int m = str2.length();
                    String dp[][] = new String[n + 1][m + 1];

                    String result = lcs(str1, str2, n, m, dp);
                    System.out.println(result);

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
