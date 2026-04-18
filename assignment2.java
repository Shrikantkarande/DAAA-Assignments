import java.util.*;

public class assignment2 {
    static boolean found = false;
    static final Object lock = new Object();
    static int foundIndex = -1;

    static class MyThread extends Thread {
        String arr[];
        int from, to;
        String target;

        public MyThread(String arr[], int from, int to, String target) {
            this.arr = arr;
            this.from = from;
            this.to = to;
            this.target = target;
        }

        public void run() {

            for (int i = from; i <= to; i++) {
                synchronized(lock){
                    if (found)
                        return;

                    if (arr[i].equals(target)) {
                        if (!found) {
                            found = true;
                            foundIndex = i;
                            System.out.println(Thread.currentThread().getName() + " found target at foundIndex " + i);
                        }
                        return;
                }
                }
                // System.out.print(i + " ");
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        String arr[] = new String[10000];

        // String[] arr = {
        //         "5", "8", "2", "9", "1",
        //         "6", "3", "7", "4", "10"
        // };

        int n = arr.length; 

        Set<Integer> set = new HashSet<>();
        Random rand = new Random();

        int i = 0;
        while (set.size() < n) {
            int randomNum = rand.nextInt(200000) + 1;
            if(!set.contains(randomNum)){
                arr[i] = Integer.toString(randomNum);
                i++;
                set.add(randomNum);
            }
        }

        for(int j = 0; j < 10; j++){
            System.out.print(arr[j] + " ");
        }

        System.out.println();

        // System.out.println(set);

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter target: ");

        String target = sc.nextLine();

        int divide = arr.length / 5;

        MyThread t1 = new MyThread(arr, 0, divide - 1, target);

        MyThread t2 = new MyThread(arr, divide, divide * 2 - 1, target);

        MyThread t3 = new MyThread(arr, divide * 2, divide * 3 - 1, target);

        MyThread t4 = new MyThread(arr, divide * 3, divide * 4 - 1, target);

        MyThread t5 = new MyThread(arr, divide * 4, (divide * 5) - 1, target);
        
        // System.out.println();

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

        System.out.println();

        long threadEndTime = System.nanoTime();
        
        long threadTime = threadEndTime - threadStartTime;
        System.out.println("Time with threads (ns): " + threadTime);
        System.out.println("Time with threads (ms): " + threadTime / 1_000_000);

        System.out.println("Final result index with threads: " + foundIndex);

        System.out.println();

        int testIndex = -1;

        long singleStartTime = System.nanoTime();

        for(int j = 0; j < n; j++){
            if(arr[j].equals(target)) testIndex = j; 
        }

        long singleEndTime = System.nanoTime();

        long singleTime = singleEndTime - singleStartTime;
        System.out.println("Time without threads (ns): " + singleTime);
        System.out.println("Time without threads (ms): " + singleTime / 1_000_000);

        System.out.println("Final result index without threads: " + testIndex);
    }
}
