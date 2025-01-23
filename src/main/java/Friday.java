import java.util.Scanner;

public class Friday {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int numTasks = 0;

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Friday");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String userInput = scanner.nextLine();
            String[] inputParts = userInput.split(" ", 2);

            if (inputParts[0].equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;

            } else if (inputParts[0].equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < numTasks; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");

            } else if (inputParts[0].equals("mark")) {
                int taskIndex = Integer.parseInt(inputParts[1]) - 1; // Get the task index
                if (taskIndex >= 0 && taskIndex < numTasks) {
                    tasks[taskIndex].markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + tasks[taskIndex]);
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println(" Invalid task number.");
                }

            } else if (inputParts[0].equals("unmark")) {
                int taskIndex = Integer.parseInt(inputParts[1]) - 1; // Get the task index
                if (taskIndex >= 0 && taskIndex < numTasks) {
                    tasks[taskIndex].markAsNotDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks[taskIndex]);
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println(" Invalid task number.");
                }

            } else {
                tasks[numTasks] = new Task(userInput); // Add a new task
                numTasks++;
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + userInput);
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}




