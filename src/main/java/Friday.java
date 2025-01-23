import java.util.ArrayList;
import java.util.Scanner;

public class Friday {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>(); // Use ArrayList for storing tasks (the collection requirement)

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Friday");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String userInput = scanner.nextLine();
            String[] inputParts = userInput.split(" ", 2);

            try {
                if (inputParts[0].equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;

                } else if (inputParts[0].equals("list")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks.get(i));
                    }
                    System.out.println("____________________________________________________________");

                } else if (inputParts[0].equals("mark")) {
                    int taskIndex = Integer.parseInt(inputParts[1]) - 1; // Get the task index
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        tasks.get(taskIndex).markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   " + tasks.get(taskIndex));
                        System.out.println("____________________________________________________________");
                    } else {
                        throw new IndexOutOfBoundsException("Invalid task number.");
                    }

                } else if (inputParts[0].equals("unmark")) {
                    int taskIndex = Integer.parseInt(inputParts[1]) - 1; // Get the task index
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        tasks.get(taskIndex).markAsNotDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println("   " + tasks.get(taskIndex));
                        System.out.println("____________________________________________________________");
                    } else {
                        throw new IndexOutOfBoundsException("Invalid task number.");
                    }

                } else if (inputParts[0].equals("todo")) {
                    if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                        throw new TodoException("The description of a todo cannot be empty.");
                    }
                    tasks.add(new Todo(inputParts[1]));
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");

                } else if (inputParts[0].equals("deadline")) {
                    if (inputParts.length < 2 || !inputParts[1].contains(" /by ")) {
                        throw new DeadlineException("The description of a deadline must include a /by clause.");
                    }
                    String[] details = inputParts[1].split(" /by ", 2);
                    tasks.add(new Deadline(details[0], details[1]));
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");

                } else if (inputParts[0].equals("event")) {
                    if (inputParts.length < 2 || !inputParts[1].contains(" /from ") || !inputParts[1].contains(" /to ")) {
                        throw new EventException("The description of an event must include /from and /to clauses.");
                    }
                    String[] details = inputParts[1].split(" /from | /to ", 3);
                    tasks.add(new Event(details[0], details[1], details[2]));
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");

                } else if (inputParts[0].equals("delete")) {
                    int taskIndex = Integer.parseInt(inputParts[1]) - 1; // Get the task index
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        Task removedTask = tasks.remove(taskIndex);
                        System.out.println("____________________________________________________________");
                        System.out.println(" Noted. I've removed this task:");
                        System.out.println("   " + removedTask);
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    } else {
                        throw new IndexOutOfBoundsException("Invalid task number.");
                    }

                } else {
                    throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(");
                }

            } catch (Exception e) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }
}





