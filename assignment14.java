import java.util.Scanner;

public class assignment14 {

    static class Mythread extends Thread {
        long x, y;
        long result;

        Mythread(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void run() {
            try {
                result = multiply(x, y);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static long multiply(long x, long y)
            throws InterruptedException {

        if (x < 10 || y < 10) {
            return x * y;
        }

        int n = Math.max(Long.toString(x).length(), Long.toString(y).length());
        int m = n / 2;

        long power = (long) Math.pow(10, m);

        long xh = x / power;
        long xl = x % power;
        long yh = y / power;
        long yl = y % power;

        Mythread t1 = new Mythread(xh, yh);
        Mythread t2 = new Mythread(xl, yl);
        Mythread t3 = new Mythread(xh + xl, yh + yl);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        long z2 = t1.result;
        long z0 = t2.result;
        long z1 = t3.result;

        return z2 * (long) Math.pow(10, 2 * m)
                + (z1 - z2 - z0) * power
                + z0;
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

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Find multipliaction");
            System.out.println("2. exit");
            System.out.print("Enter your choice: ");

            int choice = takeValidInput();

            switch (choice) {
                case 1:
                    System.out.print("Enter num1: ");
                    long num1 = sc.nextLong();

                    System.out.print("Enter num2: ");
                    long num2 = sc.nextLong();

                    long result = multiply(num1, num2);
                    System.out.println(result);

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
