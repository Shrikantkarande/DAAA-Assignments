import java.util.ArrayList;
import java.util.Scanner;

public class assignment_9 {

    static ArrayList<ArrayList<Integer>> result = new ArrayList<>();

    static void subSets(int[] arr, int targetSum) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            ArrayList<Integer> current = new ArrayList<>();
            for (int j = i; j < n; j++) {
                sum += arr[j];

                if (sum < targetSum) {
                    current.add(arr[j]);
                } else if (sum == targetSum) {
                    current.add(arr[j]);
                    result.add(current);
                } else {
                    break;
                }

            }
        }

    }

// [2, 4, 6, 8, 10]
// [2, 4, 6, 18]
// [2, 4, 8, 16]
// [2, 4, 10, 14]
// [2, 6, 8, 14]
// [2, 6, 10, 12]
// [2, 8, 20]
// [2, 10, 18]
// [2, 12, 16]
// [4, 6, 8, 12]
// [4, 6, 20]
// [4, 8, 18]
// [4, 10, 16]
// [4, 12, 14]
// [6, 8, 16]
// [6, 10, 14]
// [8, 10, 12]
// [10, 20]
// [12, 18]
// [14, 16]

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
            System.out.println("\n1. Find Subsets which give sum equal to target after adding all elements in it");
            System.out.println("2. exit");
            System.out.print("Enter your choice: ");

            int choice = takeValidInput();

            switch (choice) {
                case 1:
                    System.out.print("Enter length of array: ");
                    int length = takeValidInput();

                    System.out.println("Enter elements of array: ");

                    int arr[] = new int[length];
                    for (int i = 0; i < arr.length; i++) {
                        System.out.println("Enter " + (i + 1) + " element of array: ");
                        arr[i] = takeValidInput();
                    }

                    System.out.print("Enter targetSum: ");

                    int targetSum = takeValidInput();

                    subSets(arr, targetSum);

                    System.out.println(result.size());

                    for (int i = 0; i < result.size(); i++) {
                        System.out.println(result.get(i));
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