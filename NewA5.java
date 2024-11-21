import java.util.Scanner;

public class NewA5
{
    static int[] xMove = {2,1,-1,-2,-2,-1,1,2};
    static int[] yMove = {1,2,2,1,-1,-2,-2,-1};

    public static boolean explore(int x, int y, int count, int[][] board, int n)
    {
        if(count == n*n)
        {
            return true;
        }
        int nx,ny;
        for(int i = 0;i<8;i++)
        {
            nx = x + xMove[i];
            ny = y + yMove[i];

            if(isSafe(nx, ny, board, n))
            {
                board[nx][ny] = count;
            
            if(explore(nx,ny,count+1,board,n))
            {
               
                return true;
            }
            else
            {
                board[nx][ny] = -1;
            }
        }
        }
        return false;
    }

    public static boolean isSafe(int x, int y, int[][] board, int n)
    {
        return (x >=0 && x<n && y>=0 && y<n && board[x][y] == -1);
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of chess board : ");
        int n = sc.nextInt();
        System.out.print("Enter starting co-ordinates : ");
        int x = sc.nextInt();
        int y = sc.nextInt();

        int[][] board = new int[n][n];
        for(int i = 0;i<n;i++)
        {
            for(int j = 0 ;j<n;j++)
            {
                board[i][j] = -1;
            }
        }

        

        board[x][y] = 0;
        if(!explore(x,y,1,board,n))
        {
            System.out.println("No solution exist.");
        }
        else
        {
            for(int i = 0;i<n;i++)
            {
                for(int j = 0 ;j<n;j++)
                {
                    System.out.print(board[i][j] + "    ");
                }
                System.out.println();
            }
            System.out.print("Total Moves : " + (n*n-1));
        }
        sc.close();
    }
}