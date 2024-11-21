import java.util.*;

class Node implements Comparable<Node> 
{
    int level;
    int cost;
    Map<Integer, Integer> assigned; // stores student -> club assignments
    Set<Integer> clubsAssigned;     // tracks assigned clubs
    
    public Node(int level, int cost, Map<Integer, Integer> assigned, Set<Integer> clubsAssigned) 
    {
        this.level = level;
        this.cost = cost;
        this.assigned = new HashMap<>(assigned);
        this.clubsAssigned = new HashSet<>(clubsAssigned);
    }

    @Override
    public int compareTo(Node other) 
    {
        return Integer.compare(this.cost, other.cost); // min heap based on cost
    }
}

public class a6 
{

    public static int calculateLowerBound(int[][] costMatrix, Map<Integer, Integer> assigned, Set<Integer> clubsAssigned) {
        int n = costMatrix.length;
        int lb = 0;

        // Only consider rows and columns that haven't been assigned yet
        for (int i = 0; i < n; i++) 
        {
            if (!assigned.containsKey(i)) {  // student i not assigned yet
                int minRow = Integer.MAX_VALUE;
                for (int j = 0; j < n; j++) 
                {
                    if (!clubsAssigned.contains(j)) 
                    {  // club j not assigned yet
                        minRow = Math.min(minRow, costMatrix[i][j]);
                    }
                }
                lb += minRow;
            }
        }

        return lb;
    }

    public static int[] branchAndBound(int[][] costMatrix) 
    {
        int n = costMatrix.length;
        int minCost = Integer.MAX_VALUE;
        Map<Integer, Integer> bestAssignment = new HashMap<>();

        PriorityQueue<Node> pq = new PriorityQueue<>();
        Node initialNode = new Node(0, 0, new HashMap<>(), new HashSet<>());
        pq.add(initialNode);

        while (!pq.isEmpty()) 
        {
            Node currentNode = pq.poll();

            // If we reached a leaf node (all students are assigned)
            if (currentNode.level == n) 
            {
                if (currentNode.cost < minCost) 
                {
                    minCost = currentNode.cost;
                    bestAssignment = new HashMap<>(currentNode.assigned);
                }
                continue;
            }

            // Explore the children nodes (assigning next student to all available clubs)
            for (int club = 0; club < n; club++) 
            {
                if (!currentNode.clubsAssigned.contains(club)) 
                {
                    int newCost = currentNode.cost + costMatrix[currentNode.level][club];
                    if (newCost < minCost) 
                    {
                        Map<Integer, Integer> newAssigned = new HashMap<>(currentNode.assigned);
                        newAssigned.put(currentNode.level, club);
                        Set<Integer> newClubsAssigned = new HashSet<>(currentNode.clubsAssigned);
                        newClubsAssigned.add(club);
                        
                        int lowerBound = newCost + calculateLowerBound(costMatrix, newAssigned, newClubsAssigned);
                        if (lowerBound < minCost) 
                        {
                            Node newNode = new Node(currentNode.level + 1, newCost, newAssigned, newClubsAssigned);
                            pq.add(newNode);
                        }
                    }
                }
            }
        }

        int[] result = new int[n + 1]; // result[0] = minCost, rest for assignments
        result[0] = minCost;
        for (Map.Entry<Integer, Integer> entry : bestAssignment.entrySet()) 
        {
            result[entry.getKey() + 1] = entry.getValue() + 1;  // Adjust for 1-based indexing
        }

        return result;
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of students/clubs: ");
        int n = sc.nextInt();

        System.out.println("Enter the cost matrix (one row at a time):");
        int[][] costMatrix = new int[n][n];

        for (int i = 0; i < n; i++) 
        {
            for (int j = 0; j < n; j++) 
            {
                costMatrix[i][j] = sc.nextInt();
            }
        }

        int[] result = branchAndBound(costMatrix);

        System.out.println("Minimum Cost: " + result[0]);
        System.out.println("Best Assignment:");
        for (int i = 1; i <= n; i++) 
        {
            System.out.println("Student " + i + " is assigned to Club " + result[i]);
        }

        sc.close();
    }
}
