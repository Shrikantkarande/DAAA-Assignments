import java.util.Scanner;

public class assignment10 {
    static int count = 0;
    static boolean checkRow(char[][] board, int row, int col) {

        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        return true;
    }

    static boolean checkRightDiag(char[][] board, int row, int col) {

        int i = row - 1, j = col + 1, n = board.length;

        while (i >= 0 && j < n) {
            if (board[i][j] == 'Q')
                return false;
            i--;
            j++;
        }

        return true;
    }

    static boolean checkLeftDiag(char[][] board, int row, int col) {

        int i = row - 1, j = col - 1;

        while (i >= 0 && j >= 0) {
            if (board[i][j] == 'Q')
                return false;
            i--;
            j--;
        }

        return true;
    }

    static boolean isSafe(char[][] board, int row, int col) {
        if (checkRow(board, row, col) && checkLeftDiag(board, row, col) && checkRightDiag(board, row, col)) {
            return true;
        }

        return false;
    }

    static void placeQueen(char[][] board, int row) {
        int n = board.length;

        if (row == n) {
            count++;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

        for (int i = 0; i < n; i++) {
            if (isSafe(board, row, i)) {
                board[row][i] = 'Q';
                placeQueen(board, row + 1);
                board[row][i] = '.';
            }
        }
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
            System.out.println("\n1. Find number of ways such we can place N Queens");
            System.out.println("2. exit");
            System.out.print("Enter your choice: ");

            int choice = takeValidInput();

            switch (choice) {
                case 1:
                    count = 0;
                    System.out.print("Enter size of board: ");
                    int n = takeValidInput();

                    char[][] board = new char[n][n];
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            board[i][j] = '.';
                        }
                    }

                    placeQueen(board, 0);
                    System.out.println(count);

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
