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

    static class Vertex {
        int id;
        int color;

        Vertex(int id) {
            this.id = id;
            this.color = 0;
        }
    }

    static ArrayList<Integer>[] graph;
    static Vertex[] vertices;

    static void createGraph(int V) {
        graph = new ArrayList[V];

        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        System.out.println("Enter edges (src dest):");
        for (int i = 0; i < E; i++) {
            int s = sc.nextInt();
            int d = sc.nextInt();

            graph[s].add(d);
            graph[d].add(s);
        }
    }

    static int findMinColors(int V) {
        for (int i = 0; i < V; i++) {
            vertices[i].color = 0;
        }

        for (int m = 1; m <= V; m++) {

            if (solveColoring(m, 0, V)) {
                System.out.println("\nMinimum colors required: " + m);

                System.out.println("Color assignment:");
                for (int i = 0; i < V; i++) {
                    System.out.println("Vertex " + i + " -> Color " + vertices[i].color);
                }

                return m;
            }
        }
        return V;
    }

    static boolean isSafeColor(int v, ArrayList<Integer>[] graph, Vertex[] vertices, int c) {
        for (int neighbor : graph[v]) {
            if (vertices[neighbor].color == c) {
                return false;
            }
        }
        return true;
    }

    static boolean solveColoring(int m, int v, int V) {
        if (v == V)
            return true;

        for (int c = 1; c <= m; c++) {
            if (isSafeColor(v, graph, vertices, c)) {
                vertices[v].color = c;

                if (solveColoring(m, v + 1, V))
                    return true;

                vertices[v].color = 0;
            }
        }
        return false;
    }

    static void graphColoring() {
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter number of colors: ");
        int m = sc.nextInt();

        createGraph(V);

        vertices = new Vertex[V];
        for (int i = 0; i < V; i++) {
            vertices[i] = new Vertex(i);
        }

        boolean result = solveColoring(m, 0, V);

        if (!result) {
            System.out.println("No solution exists");
        }

        if (result) {
            System.out.println("Color assignment:");
            for (int i = 0; i < V; i++) {
                System.out.println("Vertex " + i + " -> Color " + vertices[i].color);
            }
        }

        findMinColors(V);

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