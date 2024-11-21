// Floyd Warshall

import java.util.Scanner;

class Graph {
    public int v;
    public int[][] adjMat;

    public Graph(int a) {
        v = a;
        adjMat = new int[v][v];

        // Initialize the adjacency matrix with 999 representing no connection
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                adjMat[i][j] = 999;
            }
        }
    }

    public void makeGraph() {
        Scanner sc = new Scanner(System.in);
        int e1, e2, n;
        System.out.println("Enter number of edges: ");
        n = sc.nextInt();

        System.out.println("Enter the edges and their weights:");
        for (int i = 0; i < n; i++) {
            System.out.println("Edge: ");
            e1 = sc.nextInt();
            e2 = sc.nextInt();

            if (e1 >= v || e2 >= v) {
                System.out.println("Invalid edge.");
                i--;
                continue;
            }

            System.out.println("Weight: ");
            adjMat[e1][e2] = sc.nextInt();
        }
    }

    public void fw() {
        int[][] mat = new int[v][v];

        // Copy values of adjMat into the new array
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                mat[i][j] = adjMat[i][j];
            }
        }

        for (int k = 0; k < v; k++) {
            for (int i = 0; i < v; i++) {
                for (int j = 0; j < v; j++) {
                    if (i != j) {
                        mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                    }
                    if (i == j) {
                        mat[i][j] = 0;
                    }
                }
            }
        }

        // Print the final adjacency matrix
        System.out.println("Final adjacency matrix: ");
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                System.out.print(mat[i][j] + "\t");
            }
            System.out.println();
        }
    }
}

public class NewA3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of vertices: ");
        int n = sc.nextInt();

        Graph g = new Graph(n);
        g.makeGraph();
        g.fw();
    }
}
