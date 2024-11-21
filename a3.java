import java.util.Scanner;

public class a3 {
    static final int INF = 99999; // Represents infinity

    // Function to implement the Floyd-Warshall algorithm
    static void floydWarshall(int graph[][], int V) {
        int dist[][] = new int[V][V];

        // Initialize the solution matrix same as the input graph matrix
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        // Add all vertices one by one to the set of intermediate vertices
        for (int k = 0; k < V; k++) {
            // Pick all vertices as source
            for (int i = 0; i < V; i++) {
                // Pick all vertices as destination
                for (int j = 0; j < V; j++) {
                    // If vertex k is on the shortest path from i to j, update dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Print the shortest distances matrix
        printSolution(dist, V);
    }

    // Function to print the solution
    static void printSolution(int dist[][], int V) {
        System.out.println("The shortest distances between every pair of vertices:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Number of offices (vertices)
        System.out.print("Enter the number of offices (vertices): ");
        int V = sc.nextInt();

        // Input the adjacency matrix
        int[][] graph = new int[V][V];
        System.out.println("Enter the cost matrix (enter " + INF + " for no direct connection):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        // Execute the Floyd-Warshall algorithm
        floydWarshall(graph, V);

        sc.close();
    }
}
