import java.util.*;

public class assignment16 {

    static Scanner sc = new Scanner(System.in);
    static boolean isCycle = false;

    static class Edge {
        int src, dest;

        Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    static ArrayList<Edge>[] createGraph(int V) {
        ArrayList<Edge>[] graph = new ArrayList[V];

        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        System.out.println("Enter edges (src dest):");
        for (int i = 0; i < E; i++) {
            int s = sc.nextInt();
            int d = sc.nextInt();

            graph[s].add(new Edge(s, d));
            graph[d].add(new Edge(d, s));
        }

        return graph;
    }

    static boolean isAdjacent(ArrayList<Edge>[] graph, int u, int v) {
        for (Edge e : graph[u]) {
            if (e.dest == v)
                return true;
        }
        return false;
    }

    static boolean isSafeHam(int v, ArrayList<Edge>[] graph, int[] path, int pos) {

        if (!isAdjacent(graph, path[pos - 1], v))
            return false;

        for (int i = 0; i < pos; i++) {
            if (path[i] == v)
                return false;
        }

        return true;
    }

    static boolean solveHam(ArrayList<Edge>[] graph, int[] path, int pos, int V) {

        if (pos == V) {
            if (isAdjacent(graph, path[pos - 1], path[0])) {
                for (int i = 0; i < V; i++) {
                    System.out.print(path[i] + " ");
                }
                System.out.println(path[0]);
                isCycle = true;
            }

            return isCycle;
        }

        for (int v = 1; v < V; v++) {
            if (isSafeHam(v, graph, path, pos)) {
                path[pos] = v;

                solveHam(graph, path, pos + 1, V);

                path[pos] = -1;
            }
        }

        return isCycle;
    }

    static void hamiltonianCycle() {
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        ArrayList<Edge>[] graph = createGraph(V);

        int[] path = new int[V];
        Arrays.fill(path, -1);

        path[0] = 0;

        if (!solveHam(graph, path, 1, V)) {
            System.out.println("No Hamiltonian Cycle exists");
            return;
        }

    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n1. Hamiltonian Cycle");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    hamiltonianCycle();
                    break;

                case 2:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
