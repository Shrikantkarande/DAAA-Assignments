public class assignment4 {
    static int partition(int[] arr, int st, int end){
        int pivot = arr[st];
        int cnt = 0;

        for (int i = st + 1; i <= end; i++) {
            if(arr[i] <= pivot) cnt++;
        }

        int pivotIdx = st + cnt;

        swap(arr, st, pivotIdx);

        int i = st, j = end;
        
        while (i < pivotIdx && j > pivotIdx) {
            while (arr[i] <= arr[pivotIdx]) i++;
            while (arr[j] >= arr[pivotIdx]) j--;

            if(i < pivotIdx && j > pivotIdx){
                swap(arr, i, j);

                i++;
                j--;
            }
        }

        return pivotIdx;
    }

    static void swap(int arr[], int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void quickSort(int arr[], int st, int end){
        if(st >= end) return;

        int pi = partition(arr, st, end);
        quickSort(arr, st, pi - 1);
        quickSort(arr, pi + 1, end);
    }

    static void displayArr(int[] arr){
        for(int val : arr){
            System.out.print(val + " ");
        }
    }

    public static void main(String[] args) {
        int[] arr = {6, 6, 3, 1, 5, 5, 4};
        System.out.println("Array before sorting");
        displayArr(arr);
        System.out.println();
        quickSort(arr, 0, arr.length-1);
        System.out.println("Array after sorting"); // 1 3 4 5 6 6
        displayArr(arr);
    }
}