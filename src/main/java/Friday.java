import java.util.ArrayList;
import java.util.Scanner;

public class Friday {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Friday");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String userInput = scanner.nextLine();
            String[] inputParts = userInput.split(" ", 2);
            Command command = Command.toCommand(inputParts[0]);

            try {
                switch (command) {
                    case BYE:
                        System.out.println("____________________________________________________________");
                        System.out.println(" Bye. Hope to see you again soon!");
                        System.out.println("____________________________________________________________");
                        break;

                    case LIST:
                        System.out.println("____________________________________________________________");
                        System.out.println(" Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(" " + (i + 1) + ". " + tasks.get(i));
                        }
                        System.out.println("____________________________________________________________");
                        break;

                    case MARK:
                        int markIndex = Integer.parseInt(inputParts[1]) - 1;
                        if (markIndex >= 0 && markIndex < tasks.size()) {
                            tasks.get(markIndex).markAsDone();
                            System.out.println("____________________________________________________________");
                            System.out.println(" Nice! I've marked this task as done:");
                            System.out.println("   " + tasks.get(markIndex));
                            System.out.println("____________________________________________________________");
                        } else {
                            throw new IndexOutOfBoundsException("Invalid task number.");
                        }
                        break;

                    case UNMARK:
                        int unmarkIndex = Integer.parseInt(inputParts[1]) - 1;
                        if (unmarkIndex >= 0 && unmarkIndex < tasks.size()) {
                            tasks.get(unmarkIndex).markAsNotDone();
                            System.out.println("____________________________________________________________");
                            System.out.println(" OK, I've marked this task as not done yet:");
                            System.out.println("   " + tasks.get(unmarkIndex));
                            System.out.println("____________________________________________________________");
                        } else {
                            throw new IndexOutOfBoundsException("Invalid task number.");
                        }
                        break;

                    case TODO:
                        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                            throw new TodoException("The description of a todo cannot be empty.");
                        }
                        tasks.add(new Todo(inputParts[1]));
                        System.out.println("____________________________________________________________");
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + tasks.get(tasks.size() - 1));
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                        break;

                    case DEADLINE:
                        if (inputParts.length < 2 || !inputParts[1].contains(" /by ")) {
                            throw new DeadlineException("The description of a deadline must include a /by clause.");
                        }
                        String[] deadlineDetails = inputParts[1].split(" /by ", 2);
                        tasks.add(new Deadline(deadlineDetails[0], deadlineDetails[1]));
                        System.out.println("____________________________________________________________");
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + tasks.get(tasks.size() - 1));
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                        break;

                    case EVENT:
                        if (inputParts.length < 2 || !inputParts[1].contains(" /from ") || !inputParts[1].contains(" /to ")) {
                            throw new EventException("The description of an event must include /from and /to clauses.");
                        }
                        String[] eventDetails = inputParts[1].split(" /from | /to ", 3);
                        tasks.add(new Event(eventDetails[0], eventDetails[1], eventDetails[2]));
                        System.out.println("____________________________________________________________");
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + tasks.get(tasks.size() - 1));
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                        break;

                    case DELETE:
                        int deleteIndex = Integer.parseInt(inputParts[1]) - 1;
                        if (deleteIndex >= 0 && deleteIndex < tasks.size()) {
                            Task removedTask = tasks.remove(deleteIndex);
                            System.out.println("____________________________________________________________");
                            System.out.println(" Noted. I've removed this task:");
                            System.out.println("   " + removedTask);
                            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                            System.out.println("____________________________________________________________");
                        } else {
                            throw new IndexOutOfBoundsException("Invalid task number.");
                        }
                        break;

                    default:
                        throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(");
                }

            } catch (Exception e) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }
    }
}





