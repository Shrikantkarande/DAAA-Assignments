public class test {

    static int[] arr = {5, 8, 2, 9, 1, 6, 3, 7, 4, 10};
    static int target = 7;

    static int foundIndex = -1; // shared result
    static boolean found = false;

    static final Object lock = new Object();

    static class SearchTask extends Thread {
        int start, end;

        SearchTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++) {

                synchronized (lock) {
                    if (found) return; // stop if already found
                }

                if (arr[i] == target) {
                    synchronized (lock) {
                        if (!found) {
                            found = true;
                            foundIndex = i;
                            System.out.println(
                                Thread.currentThread().getName() +
                                " found target at index " + i
                            );
                            lock.notifyAll(); // wake up all threads
                        }
                    }
                    return;
                }
            }

            // If not found, wait
            synchronized (lock) {
                try {
                    if (!found) {
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        int n = arr.length;
        int chunk = n / 5;

        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            int start = i * chunk;
            int end = (i == 4) ? n : start + chunk;
            threads[i] = new SearchTask(start, end);
            threads[i].setName("Thread-" + (i + 1));
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Final result index: " + foundIndex);
    }
}

