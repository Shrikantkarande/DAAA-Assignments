import java.util.ArrayList;
import java.util.Scanner;

public class assignment5 {
    static ArrayList<String> result = new ArrayList<>();

    static void permute(String str, String t) {
        if (str.equals("")) {
            result.add(t);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            String left = str.substring(0, i);
            String right = str.substring(i + 1);
            String rem = left + right;
            permute(rem, t + ch);
        }
    }

    static void permute2(char[] arr, int idx) {
        if (idx == arr.length) {
            result.add(String.valueOf(arr));
            return;
        }

        for (int i = idx; i < arr.length; i++) {

            swap(arr, idx, i);

            permute2(arr, idx + 1);

            swap(arr, idx, i);
        }
    }

    static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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
            System.out.println("\n1. Find Permutations");
            System.out.println("2. exit");
            System.out.print("Enter your choice: ");

            int choice = takeValidInput();

            switch (choice) {
                case 1:
                    System.out.print("Enter string for permutation: ");

                    String str = sc.nextLine();

                    // permute(str, "");

                    permute2(str.toCharArray(), 0);

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
