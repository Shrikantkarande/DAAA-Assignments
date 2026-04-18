import java.util.Random;
import java.util.Scanner;

public class assignment_4 {

    static int[] arr;
    static final int MAX_DEPTH = 3;

    static class QuickSortThread extends Thread {
        int left, right, depth;

        QuickSortThread(int left, int right, int depth) {
            this.left = left;
            this.right = right;
            this.depth = depth;
        }

        @Override
        public void run() {
            try {
                quickSort(left, right, depth);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static void quickSort(int left, int right, int depth)
            throws InterruptedException {

        if (left >= right)
            return;

        int pivotIndex = partition(left, right);

        if (depth < MAX_DEPTH) {

            QuickSortThread t1 = new QuickSortThread(left, pivotIndex - 1, depth + 1);

            QuickSortThread t2 = new QuickSortThread(pivotIndex + 1, right, depth + 1);

            t1.start();
            t2.start();

            t1.join();
            t2.join();

        } else {

            quickSort(left, pivotIndex - 1, depth + 1);
            quickSort(pivotIndex + 1, right, depth + 1);
        }
    }

    static int partition(int left, int right) {
        int pivot = arr[left];
        int cnt = 0;

        for (int i = left + 1; i <= right; i++) {
            if (arr[i] <= pivot)
                cnt++;
        }

        int pivotIdx = left + cnt;
        swap(left, pivotIdx);
        int i = left, j = right;

        while (i < pivotIdx && j > pivotIdx) {
            while (arr[i] <= pivot)
                i++;
            while (arr[j] > pivot)
                j--;
            if (i < pivotIdx && j > pivotIdx) {
                swap(i, j);
                i++;
                j--;
            }
        }
        return pivotIdx;
    }

    static void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static int binarySearch(int arr[], int target) {

        int left = 0, right = arr.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] == target)
                return mid;

            else if (arr[mid] > target)
                right = mid - 1;

            else
                left = mid + 1;
        }

        return -1;
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

    public static void main(String[] args) throws InterruptedException {

        int n = 1000000;
        arr = new int[n];

        Random rand = new Random();

        for (int i = 0; i < n; i++)
            arr[i] = rand.nextInt(100000);

        QuickSortThread t = new QuickSortThread(0, n - 1, 0);
        t.start();
        t.join();

        for (int i = 0; i < 100; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();

        while (true) {
            System.out.println("\n1. search element");
            System.out.println("2. exit");
            System.out.print("Enter your choice: ");

            int choice = takeValidInput();

            switch (choice) {
                case 1:
                    System.out.print("Enter target to search: ");
                    int target = takeValidInput();

                    int index = binarySearch(arr, target);

                    if (index >= 0)
                        System.out.println(target + " found at index " + index);
                    else
                        System.out.println(target + " not found");

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
