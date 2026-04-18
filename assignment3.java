import java.util.Random;
import java.util.Scanner;

public class assignment3 {

    static int[] arr;
    static final int MAX_DEPTH = 3;

    static class MergeSortThread extends Thread {
        int left, right, depth;

        MergeSortThread(int left, int right, int depth) {
            this.left = left;
            this.right = right;
            this.depth = depth;
        }

        @Override
        public void run() {
            try {
                mergeSort(left, right, depth);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static void mergeSort(int left, int right, int depth)
            throws InterruptedException {

        if (left >= right) return;

        int mid = left + (right - left) / 2;

        if (depth < MAX_DEPTH) {
            MergeSortThread t1 = new MergeSortThread(left, mid, depth + 1);
            MergeSortThread t2 = new MergeSortThread(mid + 1, right, depth + 1);

            t1.start();
            t2.start();

            t1.join();
            t2.join();
        } else {
            mergeSort(left, mid, depth + 1);
            mergeSort(mid + 1, right, depth + 1);
        }

        merge(left, mid, right);
    }

    static void merge(int left, int mid, int right) {
        int[] temp = new int[right - left + 1];

        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j])
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (int p = 0; p < temp.length; p++)
            arr[left + p] = temp[p];
    }

    static int binarySearch(int arr[], int target){
        int n = arr.length;

        int left = 0, right = n - 1;

        while (left < right) {
            int mid = left + (right - left)/2;

            if(arr[mid] == target) return mid;

            else if(arr[mid] > target) right = mid - 1;

            else left = mid + 1;
        }

        return -1;
    }

    public static void main(String[] args) throws InterruptedException {

        int n = 1000000;
        arr = new int[n];

        Random rand = new Random();
        for (int i = 0; i < n; i++)
            arr[i] = rand.nextInt(100000);

        MergeSortThread t = new MergeSortThread(0, n - 1, 0);
        t.start();
        t.join();

        // for (int i = 0; i < n; i++) {
        //     System.out.print(arr[i] + " ");
        // }

        int target;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter target to find: ");
        target = sc.nextInt();
        int index = binarySearch(arr, target);

        if(index != -1) System.out.println(target + " is found at index " + index);
        else System.out.println(target + " not found");
    }
}
