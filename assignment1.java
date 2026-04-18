import java.util.*;

public class assignment1 {
    static class MyThread extends Thread {
        int arr[];
        int localMax = Integer.MIN_VALUE, localMin = Integer.MAX_VALUE, from, to;

        public MyThread(int arr[], int from, int to) {
            this.arr = arr;
            this.from = from;
            this.to = to;
        }

        public void run() {
            System.out.println("Thread running: " + Thread.currentThread().getName());
            for (int i = from; i <= to; i++) {
                localMax = Integer.max(localMax, arr[i]);
                localMin = Integer.min(localMin, arr[i]);
            }
            // System.out.println(localMax);
            // System.out.println(localMin);

        }

        public int getMax() {
            return localMax;
        }

        public int getMin() {
            return localMin;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        int arr[] = new int[1000000000];

        Random rand = new Random();

        for (int i = 0; i < arr.length; i++) {
            int randomNum = rand.nextInt(2000000000) + 1;
            arr[i] = randomNum;
        }

        int divide = arr.length / 5;

        int maxArr[] = new int[5];
        int minArr[] = new int[5];

        

        MyThread t1 = new MyThread(arr, 0, divide - 1);
    

        MyThread t2 = new MyThread(arr, divide, divide * 2 - 1);
        

        MyThread t3 = new MyThread(arr, divide * 2, divide * 3 - 1);
        

        MyThread t4 = new MyThread(arr, divide * 3, divide * 4 - 1);
        

        MyThread t5 = new MyThread(arr, divide * 4, (divide * 5) - 1);
        
        long threadStartTime = System.nanoTime();

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        

        long threadEndTime = System.nanoTime();
        System.out.println();
        long threadTime = threadEndTime - threadStartTime;
        System.out.println("Time with threads (ns): " + threadTime);
        System.out.println("Time with threads (ms): " + threadTime / 1_000_000);

        int max1 = t1.getMax();
        int min1 = t1.getMin();
        maxArr[0] = max1;
        minArr[0] = min1;

        int max2 = t2.getMax();
        int min2 = t2.getMin();
        maxArr[1] = max2;
        minArr[1] = min2;

        int max3 = t3.getMax();
        int min3 = t3.getMin();
        maxArr[2] = max3;
        minArr[2] = min3;

        int max4 = t4.getMax();
        int min4 = t4.getMin();
        maxArr[3] = max4;
        minArr[3] = min4;

        int max5 = t5.getMax();
        int min5 = t5.getMin();
        maxArr[4] = max5;
        minArr[4] = min5;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < 5; i++) {
            min = Integer.min(min, minArr[i]);
            max = Integer.max(max, maxArr[i]);
        }

        // for (int i = 0; i < 5; i++) {
        // System.out.println("min" + " "+ minArr[i]);
        // System.out.println("max" + " "+maxArr[i]);
        // }

        System.out.println();

        System.out.println("max" + " " + max);
        System.out.println("min" + " " + min);

        System.out.println();

        int testmin = Integer.MAX_VALUE;
        int testmax = Integer.MIN_VALUE;

        long singleStartTime = System.nanoTime();

        for (int i = 0; i < arr.length; i++) {
            testmax = Integer.max(testmax, arr[i]);
            testmin = Integer.min(testmin, arr[i]);
        }

        long singleEndTime = System.nanoTime();

        long singleTime = singleEndTime - singleStartTime;
        System.out.println("Time without threads (ns): " + singleTime);
        System.out.println("Time without threads (ms): " + singleTime / 1_000_000);
        System.out.println();

        System.out.println("max" + " " + testmax);
        System.out.println("min" + " " + testmin);

    }
}
