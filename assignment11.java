import java.util.*;

public class assignment11 {

    static Scanner sc = new Scanner(System.in);

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

    static int findMinColors(ArrayList<Edge>[] graph, int V) {
    for (int m = 1; m <= V; m++) {
        int[] color = new int[V];

        if (solveColoring(graph, m, color, 0, V)) {
            System.out.println("\nMinimum colors required: " + m);

            System.out.println("Color assignment:");
            for (int i = 0; i < V; i++) {
                System.out.println("Vertex " + i + " -> Color " + color[i]);
            }

            return m;
        }
    }
    return V;
}

    static boolean isSafeColor(int v, ArrayList<Edge>[] graph, int[] color, int c) {
        for (Edge e : graph[v]) {
            if (color[e.dest] == c) {
                return false;
            }
        }
        return true;
    }

    static boolean solveColoring(ArrayList<Edge>[] graph, int m, int[] color, int v, int V) {
        if (v == V) return true;

        for (int c = 1; c <= m; c++) {
            if (isSafeColor(v, graph, color, c)) {
                color[v] = c;

                if (solveColoring(graph, m, color, v + 1, V))
                    return true;

                color[v] = 0;
            }
        }
        return false;
    }

    static void graphColoring() {
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        ArrayList<Edge>[] graph = createGraph(V);

        System.out.print("Enter number of colors: ");
        int m = sc.nextInt();

        int[] color = new int[V];

        if (!solveColoring(graph, m, color, 0, V)) {
            System.out.println("No solution exists");
            return;
        }

        System.out.println("Color assignment:");
        for (int i = 0; i < V; i++) {
            System.out.println("Vertex " + i + " -> Color " + color[i]);
        }

        findMinColors(graph, V);

    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n1. Graph Coloring");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    graphColoring();
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