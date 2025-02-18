# **Friday Chatbot User Guide**

## **Introduction**
Friday is a personal task management chatbot that helps users organize their tasks efficiently. It supports **todos, deadlines, events**, and other commands such as **finding tasks, marking them as done, and deleting them**.

---

## Features

### Adding Todos

You can add a todo task by providing a description.

**Command Format:**
todo DESCRIPTION

**Example:**
```
todo Read a book
```

**Expected Output:**
```
Got it. I've added this task: 
[T][ ] Read a book Now you have 1 task in the list.
```

---

### Adding Deadlines

You can add a deadline task by specifying a description and a due date.

**Command Format:**
deadline DESCRIPTION /by DATE

**Example:**
```
deadline Submit report /by 2025-03-01
```

**Expected Output:**
```
Got it. I've added this task: 
[D][ ] Submit report (by: 2025-03-01) Now you have 2 tasks in the list.
```
---

### Adding Events

You can add an event task by specifying a description, start time, and end time.

**Command Format:**
event DESCRIPTION /from START_TIME /to END_TIME

**Example:**
```
event Team meeting /from 2pm /to 4pm
```

**Expected Output:**
```
Got it. I've added this task: 
[E][ ] Team meeting (from: 2pm to: 4pm) 
Now you have 3 tasks in the list.
```
---

### Listing All Tasks

You can list all your tasks.

**Command Format:**
list

**Example:**
```
list
```

**Expected Output:**
```
Here are the tasks in your list:

1. [T][ ] Read a book
2. [D][ ] Submit report (by: 2025-03-01)
3. [E][ ] Team meeting (from: 2pm to: 4pm)
```

---

### Marking a Task as Done

You can mark a task as done using its task number.

**Command Format:**
mark TASK_NUMBER

**Example:**
```
mark 2
```

**Expected Output:**
```
Nice! I've marked this task as done: 
[D][X] Submit report (by: 2025-03-01)
```
---

### Unmarking a Task as Not Done

You can unmark a task as not done using its task number.

**Command Format:**
unmark TASK_NUMBER

**Example:**
```
unmark 2
```

**Expected Output:**
```
OK, I've marked this task as not done yet: 
[D][ ] Submit report (by: 2025-03-01)
```
---

### Deleting a Task

You can delete a task using its task number.

**Command Format:**
delete TASK_NUMBER

**Example:**
```
delete 1
```

**Expected Output:**
```
Noted. I've removed this task: 
[T][ ] Read a book Now you have 2 tasks in the list.
```
---

### Handling Duplicate Tasks

The chatbot prevents duplicate tasks from being added.

**Example:**
todo Read a book; Read a book

**Expected Output:**
```
Got it. I've added this task: 
[T][ ] Read a book 
Now you have 1 task in the list.

These todos were not added because they are duplicates:
[T][] Read a book
```

---

### Handling Invalid Inputs

If you try to delete, mark, or unmark a task using an invalid number, the chatbot will handle it gracefully.

**Example:**
delete 10

**Expected Output:**
Error: Task number 10 is out of range.

---

## Exit the Program

You can exit the chatbot.

**Command Format:**
bye

**Example:**
```
bye
```

**Expected Output:**
```
Bye. Hope to see you again soon!
```
