import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class assignment7 {
    static class Edge implements Comparable<Edge> {
        int src;
        int dest;
        int wt;

        Edge(int s, int d, int w) {
            src = s;
            dest = d;
            wt = w;
        }

        @Override
        public int compareTo(Edge e2){
            return this.wt - e2.wt;
        }
    }

    static int n;
    static int par[];
    static int rank[];

    public static void init(ArrayList<Edge> edges){
        n = edges.size();
        par = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            par[i] = i;
        }
    }

    static void addEdge(ArrayList<Edge> graph, int src, int dest, int wt) {

        graph.add(new Edge(src, dest, wt));
        // graph.add(new Edge(dest, src, wt));

    }

    static void deleteEdge(ArrayList<Edge> graph, int src, int dest) {

        graph.removeIf(e -> e.src == src && e.dest == dest);

    }

    static void viewGraph(ArrayList<Edge> graph) {
        for (int i = 0; i < graph.size(); i++) {

            System.out.print(i + " -> ");

            Edge e = graph.get(i);

            System.out.print("(" + e.src + "," + e.dest + "," + e.wt + ")");

            System.out.println();
        }
    }

    public static int find(int x){
        if (x == par[x]) {
            return x;
        }
        return par[x] = find(par[x]);
    }

    public static void union(int a, int b){
        int parA = find(a);
        int parB = find(b);

        if (rank[parA] == rank[parB]) {
            par[parB] = parA;
            rank[parA]++;
        }else if (rank[parA] < rank[parB]) {
            par[parA] = parB;
        }else{
            par[parB] = parA;
        }
    }

    static void kruskalsMST(ArrayList<Edge> edges, int v) {

        init(edges);

        Collections.sort(edges);
        int mstCost = 0;
        int count = 0;

        for (int i = 0; count < v-1; i++) {
            Edge e = edges.get(i);

            int parA = find(e.src);//src = a
            int parB = find(e.dest);//dest = b

            if (parA != parB) {
                union(e.src, e.dest);
                mstCost += e.wt;
                count++;
                System.out.println(e.src + " -> " + e.dest + " @ " + e.wt);
            }
        }
        
        System.out.println(mstCost);
    }

    public static void main(String[] args) {

        ArrayList<Edge> graph = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();

        while (true) {

            System.out.println("\n1. Add Edge");
            System.out.println("2. Delete Edge");
            System.out.println("3. View Graph");
            System.out.println("4. Find MST(kruskals)");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            System.out.println();

            switch (choice) {

                case 1:
                    System.out.print("Enter source: ");
                    int s = sc.nextInt();

                    System.out.print("\nEnter destination: ");
                    int d = sc.nextInt();

                    System.out.print("\nEnter weight: ");
                    int w = sc.nextInt();

                    addEdge(graph, s, d, w);
                    break;

                case 2:
                    System.out.print("Enter source to delete edge: ");
                    int ds = sc.nextInt();

                    System.out.print("Enter destination to delete edge: ");
                    int dd = sc.nextInt();

                    deleteEdge(graph, ds, dd);
                    break;

                case 3:
                    viewGraph(graph);
                    break;

                case 4:
                    kruskalsMST(graph, v);
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
