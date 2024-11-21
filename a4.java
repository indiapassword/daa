import java.util.*;
public class a4 {
        static void dijkstra(int[][] network, int src, int n) 
        {
            int[] vis = new int[n];
            int[] dist = new int[n];
            src--;
            for (int i = 0; i < n; i++) 
            {
                vis[i] = 0;
                dist[i] = Integer.MAX_VALUE;
            }
            dist[src] = 0;
            for (int c = 0; c < n; c++) 
            {
                int u = -1;
                int min = Integer.MAX_VALUE;
                for (int i = 0; i < n; i++) 
                {
                    if (vis[i] == 0 && dist[i] < min) 
                    {
                        u = i;
                        min = dist[i];
                    }
                }
                vis[u] = 1;
                for (int v = 0; v < n; v++) 
                {
                    if (dist[u] + network[u][v] < dist[v] && vis[v] == 0 && network[u][v] != 0) 
                    {
                        dist[v] = dist[u] + network[u][v];
                    }
                }
            }
            System.out.println("Time taken from node " + (src + 1) + " to all nodes : ");
            for (int i = 0; i < n; i++) 
            {
                if (dist[i] == Integer.MAX_VALUE) 
                {
                    System.out.println(i + 1 + " : -1");
                } 
                else 
                {
                    System.out.println(i + 1 + " : " + dist[i]);
                }
            }
        }
        public static void main(String[] args) 
        {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the number of nodes : ");
            int n = sc.nextInt();
            System.out.print("Enter the number of edges : ");
            int m = sc.nextInt();
            int[][] network = new int[n][n];
            for (int i = 0; i < n; i++) 
            {
                for (int j = 0; j < n; j++) 
                {
                    network[i][j] = 0;
                }
            }
            for (int i = 0; i < m; i++) 
            {
                int u, v, w;
                System.out.print("Enter source : ");
                u = sc.nextInt();
                System.out.print("Enter destination : ");
                v = sc.nextInt();
                System.out.print("Enter time taken : ");
                w = sc.nextInt();
                System.out.println();
                if (u > n && v > n && w < 0 && u < 1 && v < 1) 
                {
                    i--;
                    System.out.println("Enter correct source, destination and time taken");
                } 
                else if (network[u - 1][v - 1] != 0) 
                {
                    System.out.println("Edge already present");
                } 
                else 
                {
                    network[u - 1][v - 1] = w;
                    network[v - 1][u - 1] = w;
                }
            }
            System.out.println("Enter starting source : ");
            int src = sc.nextInt();
            System.out.println();
            System.out.println("Distance matrix : ");
            for (int i = 0; i < n; i++) 
            {
                for (int j = 0; j < n; j++) 
                {
                    System.out.print(network[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
            dijkstra(network, src, n);
        }
}