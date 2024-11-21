import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class a2
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of tasks: ");
        int n = sc.nextInt();

        //declare an array of task objects
        ArrayList<Task> tasks = new ArrayList<>();

        //take user input for each task
        for (int i = 0; i < n; i++) 
        {
            System.out.print("Enter profit for task " + (i + 1) + ": ");
            int profit = sc.nextInt();
            System.out.print("Enter deadline for task " + (i + 1) + ": ");
            int deadline = sc.nextInt();
            tasks.add(new Task(profit, deadline));
        }

        // Find the maximum deadline
        int maxDeadline = 0;
        for (Task task : tasks) 
        {
            maxDeadline = Math.max(maxDeadline, task.deadline);
        }

        // Sort tasks based on profit in descending order
        Collections.sort(tasks, new Comparator<Task>() 
        {
            @Override
            public int compare(Task t1, Task t2)    
            {
                return t2.profit - t1.profit;
            }
        }
        );

        // Create a schedule array to keep track of which slots are filled
        int[] schedule = new int[maxDeadline];
        for (int i = 0; i < maxDeadline; i++) 
        {
            schedule[i] = -1; // Initialize all slots to -1 (empty)
        }

        // Schedule tasks to maximize profit
        for (Task task : tasks) 
        {
            for (int j = Math.min(maxDeadline - 1, task.deadline - 1); j >= 0; j--) 
            {
                if (schedule[j] == -1) 
                {
                    schedule[j] = task.profit;
                    break;
                }
            }
        }

        // Calculate and print the total profit
        int totalProfit = 0;
        System.out.println("Scheduled tasks:");
        for (int i = 0; i < schedule.length; i++) 
        {
            if (schedule[i] != -1) 
            {
                System.out.println("Slot " + (i + 1) + ": Profit " + schedule[i]);
                totalProfit += schedule[i];
            }
        }
        System.out.println("Total Profit: " + totalProfit);
    }
}
    //create a class Task with properties profit and deadline
    class Task 
    {
        int profit;
        int deadline;

        public Task(int profit, int deadline) 
        {
            this.profit = profit;
            this.deadline = deadline;
        }
}