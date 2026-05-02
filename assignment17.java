import java.util.Arrays;
import java.util.Scanner;

public class assignment17 {
    static Scanner sc = new Scanner(System.in);

    public static class pair implements Comparable<pair> {
    
        int val;
        int idx;

        pair(int val, int idx){
            this.val = val;
            this.idx = idx;
        }

        @Override
        public int compareTo(pair p2){
            return this.val - p2.val;
        }
    }

    public static int findContentChildren(int[] g, int[] s) {
        int i = 0, j = 0, cnt = 0;

        int m = g.length;
        pair[] newg = new pair[m];
        for (int k = 0; k < m; k++) {
            newg[k] = new pair(g[k], k);
        }

        int n = s.length;
        pair[] news = new pair[n];
        for (int k = 0; k < n; k++) {
            news[k] = new pair(s[k], k);
        }

        Arrays.sort(newg);
        Arrays.sort(news);

        int[] result = new int[m];

        while(i < m && j < n){
            if(news[j].val >= newg[i].val){
                cnt++;
                result[newg[i].idx] = news[j].val;
                i++;
                j++;
            }else{
                j++;
            }
        }

        for (int k = 0; k < m; k++) {
            System.out.print(result[k] + " ");
        }

        System.out.println();

        return cnt;
    }

    static int takeValidInput() {

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

        while (true) {
            System.out.println("\n1. Assign Cookies");
            System.out.println("2. exit");
            System.out.print("Enter your choice: ");

            int choice = takeValidInput();

            switch (choice) {
                case 1:
                    System.out.print("Enter length of the greed array: ");
                    int n = takeValidInput();

                    int[] g = new int[n];
                    System.out.print("Enter elements of greed array: ");
                    for (int i = 0; i < n; i++) {
                        g[i] = takeValidInput();
                    }

                    System.out.print("Enter length of the cookies array: ");
                    int m = takeValidInput();

                    int[] s = new int[m];
                    System.out.print("Enter elements of cookies array: ");
                    for (int i = 0; i < m; i++) {
                        s[i] = takeValidInput();
                    }

                    int cnt = findContentChildren(g, s);
                    System.out.println("To " + cnt + " number of childrens cookies are assigned");

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
