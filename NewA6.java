import java.util.Scanner;

class NewA6{
    static int mincost = Integer.MAX_VALUE;
    static int[] best;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of students or clubs (N)");
        int n = sc.nextInt();
        int cost[][]= new int[n][n];
        System.out.println("ENter teh cost matrix");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                cost[i][j]=sc.nextInt();
            }
        }
        int[] assigned = new int[n];
        best = new int[n];

        for(int i=0;i<n;i++){
            assigned[i]=-1;
            best[i]=-1;
        }
        bb(cost, n,assigned,0,0);
        System.out.println("Optimal assignment of students to clubs & their individual costs");
        int totalcost = 0;
        for(int i=0;i<n;i++){
            int student =i+1;
            int club = best[i]+1;
            int studentcost = cost[i][best[i]];
            totalcost += studentcost;
            System.out.println("Student"+student+"->Club"+club+" Cost:"+studentcost);
        }
        System.out.println("Total cost is"+ totalcost);
        sc.close();
    }

    public static void bb(int[][] cost,int n,int[] assigned,int student , int currentcost){
        if(student ==n){
            if(currentcost< mincost){
                mincost = currentcost;
                System.arraycopy(assigned,0,best,0,n);
            }
            return;
        }
        int lowerbound = currentcost +  Calcualtelowerbound(cost,n,assigned,student);
        if(lowerbound >=mincost) return;
        for(int i=0;i<n;i++){
            if(assigned[i]==-1){
                assigned[i]=student;
                currentcost += cost[student][i];
                bb(cost,n,assigned,student+1,currentcost);
                currentcost -= cost[student][i];
                assigned[i]=-1;
            }
        }
    }
    public static int Calcualtelowerbound(int[][] cost,int n,int[] assigned,int student){
        int bound =0;
        for(int i=student;i<n;i++){
            int mincost=Integer.MAX_VALUE;
            for(int j=0;j<n;j++){
                if(assigned[j]==-1){
                    mincost = Math.min(mincost,cost[i][j]);
                }
            }
            bound +=mincost;
        }
        return bound;
    }
}