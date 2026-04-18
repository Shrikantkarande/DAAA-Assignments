import java.util.*;

public class assignment6 {
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
        int parent;

        Pair(int v, int cost, int parent) {
            this.v = v;
            this.cost = cost;
            this.parent = parent;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.cost - p2.cost;
        }
    }

    static void addEdge(ArrayList<ArrayList<Edge>> graph, int src, int dest, int wt) {

        graph.get(src).add(new Edge(dest, wt));
        graph.get(dest).add(new Edge(src, wt));

    }

    static void deleteEdge(ArrayList<ArrayList<Edge>> graph, int src, int dest) {

        graph.get(src).removeIf(e -> e.dest == dest);
        graph.get(dest).removeIf(e -> e.dest == src);

    }

    static void viewGraph(ArrayList<ArrayList<Edge>> graph) {
        for (int i = 0; i < graph.size(); i++) {

            System.out.print(i + " -> ");

            for (Edge e : graph.get(i)) {
                System.out.print("(" + e.dest + "," + e.wt + ") ");
            }

            System.out.println();
        }
    }

    static void prims(ArrayList<ArrayList<Edge>> graph) {
        boolean[] vis = new boolean[graph.size()];
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        pq.add(new Pair(0, 0, -1));
        int finalCost = 0;

        while (!pq.isEmpty()) {
            Pair curr = pq.remove();

            if (!vis[curr.v]) {
                vis[curr.v] = true;
                finalCost += curr.cost;

                if (curr.parent != -1) {
                    System.out.println(curr.parent + " -> " + curr.v + " @ " + curr.cost);
                }

                for (int i = 0; i < graph.get(curr.v).size(); i++) {
                    Edge e = graph.get(curr.v).get(i);
                    pq.add(new Pair(e.dest, e.wt, curr.v));
                }
            }

        }

        System.out.println("final(min) cost of MST = " + finalCost);
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
            System.out.println("4. Find MST(Prims)");
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
                    prims(graph);
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
