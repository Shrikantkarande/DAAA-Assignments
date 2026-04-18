import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class assignment8 {
    static class Edge {
        int dest;
        int wt;

        Edge(int d, int w) {
            dest = d;
            wt = w;
        }
    }

    static class Pair implements Comparable<Pair> {
        int v;
        int cost;

        Pair(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.cost - p2.cost;
        }
    }

    static void addEdge(ArrayList<ArrayList<Edge>> graph, int src, int dest, int wt) {

        graph.get(src).add(new Edge(dest, wt));

    }

    static void deleteEdge(ArrayList<ArrayList<Edge>> graph, int src, int dest) {

        graph.get(src).removeIf(e -> e.dest == dest);

    }

    static void viewGraph(ArrayList<ArrayList<Edge>> graph) {
        for (int i = 0; i < graph.size(); i++) {

            System.out.print(i + " -> ");

            for (Edge e : graph.get(i)) {
                System.out.print("(" + e.dest + "," + e.wt + ")");
            }

            System.out.println();
        }
    }

    static void printPath(int parent[], int j) {
        if (j == -1)
            return;

        printPath(parent, parent[j]);
        System.out.print(j + " ");
    }

    static void dijkstra(ArrayList<ArrayList<Edge>> graph, int src, int dest) {

        int n = graph.size();

        int dist[] = new int[n];
        boolean vis[] = new boolean[n];
        int parent[] = new int[n];

        for (int i = 0; i < graph.size(); i++) {
                dist[i] = Integer.MAX_VALUE;
                parent[i] = -1;
        }

        dist[src] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>();

        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.remove();

            if (curr.v == dest)
                break;

            if (!vis[curr.v]) {
                vis[curr.v] = true;

                for (int i = 0; i < graph.get(curr.v).size(); i++) {
                    Edge e = graph.get(curr.v).get(i);
                    int u = curr.v;
                    int v = e.dest;
                    int wt = e.wt;

                    if (dist[u] + wt < dist[v]) {
                        dist[v] = dist[u] + wt;
                        parent[v] = u;
                        pq.add(new Pair(v, dist[v]));
                    }
                }

            }

        }

        if (dist[dest] == Integer.MAX_VALUE) {
            System.out.println("No path exists");
            return;
        }

        System.out.println("Shortest Distance: " + dist[dest]);

        System.out.print("Path: ");
        printPath(parent, dest);
        System.out.println();

    }

    public static void main(String[] args) {

        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();

        for (int i = 0; i < v; i++) {
            graph.add(new ArrayList<>());
        }

        while (true) {

            System.out.println("\n1. Add Edge");
            System.out.println("2. Delete Edge");
            System.out.println("3. View Graph");
            System.out.println("4. Find shortest path from source to destination");
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
                    System.out.print("Enter source: ");
                    int src = sc.nextInt();

                    System.out.print("Enter destination: ");
                    int dest = sc.nextInt();

                    dijkstra(graph, src, dest);
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
