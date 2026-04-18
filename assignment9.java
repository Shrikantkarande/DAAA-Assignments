import java.util.ArrayList;
import java.util.Scanner;

public class assignment9 {

    static ArrayList<ArrayList<Integer>> result = new ArrayList<>();

    static void subSets(int[] arr, int idx, int targetSum, ArrayList<Integer> current) {
        if (targetSum == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (targetSum < 0 || idx >= arr.length) {
            return;
        }

        // include
        current.add(arr[idx]);
        subSets(arr, idx + 1, targetSum - arr[idx], current);

        // exclude
        current.remove(Integer.valueOf(arr[idx]));
        subSets(arr, idx + 1, targetSum, current);

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
            System.out.println("\n1. Find Subsets which give sum equal to target after adding all elements in it");
            System.out.println("2. exit");
            System.out.print("Enter your choice: ");

            int choice = takeValidInput();


            switch (choice) {
                case 1:
                    System.out.print("Enter length of array: ");
                    int length = takeValidInput();

                    System.out.println("Enter elements of array: ");

                    while (!result.isEmpty()) {
                        result.removeFirst();
                    }

                    int arr[] = new int[length];
                    for (int i = 0; i < arr.length; i++) {
                        System.out.println("Enter " + (i + 1) + " element of array: ");
                        arr[i] = takeValidInput();
                    }

                    System.out.print("Enter targetSum: ");

                    int targetSum = takeValidInput();

                    subSets(arr, 0, targetSum, new ArrayList<>());

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