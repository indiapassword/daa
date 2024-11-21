import java.util.Scanner;

class a5 {
    // A recursive utility function to solve Knight's Tour problem
    static boolean explore(int x, int y, int count, int board[][], int xMove[], int yMove[], int N) {
        int k, nx, ny;
        if (count == N * N) // All squares are visited
            return true;

        // Try all next moves from the current coordinate (x, y)
        for (k = 0; k < 8; k++) {
            nx = x + xMove[k];
            ny = y + yMove[k];
            if (isSafe(nx, ny, board, N)) {
                board[nx][ny] = count; // Mark the move
                if (explore(nx, ny, count + 1, board, xMove, yMove, N)) // Recursive call
                    return true;
                else
                    board[nx][ny] = -1; // Backtrack
            }
        }
        return false;
    }

    // A utility function to check if x, y are valid indexes for N*N chessboard
    static boolean isSafe(int x, int y, int board[][], int N) {
        return (x >= 0 && x < N && y >= 0 && y < N && board[x][y] == -1);
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // Enter size of the board
        System.out.print("Enter value of N (board size): ");
        int N = sc.nextInt();

        // Validate board size
        if (N <= 0) {
            System.out.println("Invalid board size! N must be greater than 0.");
            sc.close();
            return;
        }

        // Enter starting coordinates of the knight
        System.out.print("Enter starting coordinates (x y): ");
        int sx = sc.nextInt();
        int sy = sc.nextInt();

        // Validate starting coordinates
        if (sx < 0 || sx >= N || sy < 0 || sy >= N) {
            System.out.println("Invalid starting coordinates! They must be within the board's range (0 to " + (N - 1) + ").");
            sc.close();
            return;
        }

        // NxN chessboard declared
        int board[][] = new int[N][N];

        // Initialization of board
        for (int x = 0; x < N; x++)
            for (int y = 0; y < N; y++)
                board[x][y] = -1;

        // xMove[] and yMove[] define the next moves of the knight
        int xMove[] = {2, 1, -1, -2, -2, -1, 1, 2};
        int yMove[] = {1, 2, 2, 1, -1, -2, -2, -1};

        // Knight is initially at the starting position
        board[sx][sy] = 0;

        // Start exploring
        if (!explore(sx, sy, 1, board, xMove, yMove, N)) {
            System.out.println("Solution does not exist.");
        } else {
            System.out.println("Knight's Tour solution:");
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    System.out.printf("%2d ", board[x][y]);
                }
                System.out.println();
            }

            // Print the total number of jumps
            System.out.println("Total number of jumps: " + (N * N - 1));
        }

        sc.close();
    }
}
