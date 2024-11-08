/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package todo.list.manager;


import java.util.Stack;
import javax.swing.JOptionPane;
import java.util.LinkedList;
/**
 *
 * @author maxin
 */
public class ToDoListManager {

    LinkedList<String> taskList = new LinkedList<>();
    LinkedList<String> completedTasks = new LinkedList<>();
    Stack<String> undo = new Stack<>();

    public static void main(String[] args) {

        ToDoListManager taskManager = new ToDoListManager();
        String option;

        do {
            option = JOptionPane.showInputDialog(
                "Choose an Option:\n" +
                "1. Add Task\n" +
                "2. Mark Task Done\n" +
                "3. Undo\n" +
                "4. View To-Do List\n" +
                "5. View Completed Tasks\n" +
                "6. Exit"
            );

            if (option != null) {
                switch (option) {
                    case "1":
                        String task = JOptionPane.showInputDialog("Enter task to add:");
                        if (task != null && !task.trim().isEmpty()) {
                            taskManager.addTask(task.trim());
                        }
                        break;
                    case "2":
                        taskManager.taskDone();
                        break;
                    case "3":
                        taskManager.undo();
                        break;
                    case "4":
                        taskManager.viewToDoList();
                        break;
                    case "5":
                        taskManager.viewCompletedTasks();
                        break;
                    case "6":
                        JOptionPane.showMessageDialog(null, "Exiting ToDo List Manager.");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid option.");
                }
            }

        } while (option != null && !option.equals("6"));
    }

    public void addTask(String task) {
        taskList.add(task);
        JOptionPane.showMessageDialog(null, "Added task: " + task);
    }

    private void taskDone() {
        if (taskList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks available to mark as done.");
            return;
        }

        StringBuilder tasks = new StringBuilder("To-Do List:\n");
        for (int i = 0; i < taskList.size(); i++) {
            tasks.append((i + 1)).append(": ").append(taskList.get(i)).append("\n");
        }

        String taskNumberString = JOptionPane.showInputDialog(tasks.toString() + "Enter the number of the task to mark as done:");
        try {
            int taskNumber = Integer.parseInt(taskNumberString) - 1;
            if (taskNumber < 0 || taskNumber >= taskList.size()) {
                JOptionPane.showMessageDialog(null, "Invalid task number.");
                return;
            }

            String task = taskList.remove(taskNumber);
            completedTasks.add(task);
            undo.push("done:" + task);
            JOptionPane.showMessageDialog(null, "Task marked as done.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
        }
    }

    public void undo() {
        if (!undo.isEmpty()) {
            String action = undo.pop();
            String[] parts = action.split(":", 2);
            if (parts[0].equals("done")) {
                String task = parts[1];
                taskList.add(task);
                completedTasks.remove(task);
                JOptionPane.showMessageDialog(null, "Undid task: " + task);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No action to undo.");
        }
    }

    public void viewCompletedTasks() {
        StringBuilder completedTasksList = new StringBuilder();
        for (String task : completedTasks) {
            completedTasksList.append(task).append("\n");
        }
        JOptionPane.showMessageDialog(null, completedTasksList.length() > 0 ? completedTasksList.toString() : "No completed tasks.");
    }

    public void viewToDoList() {
        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            tasks.append((i + 1)).append(": ").append(taskList.get(i)).append("\n");
        }
        JOptionPane.showMessageDialog(null, tasks.length() > 0 ? tasks.toString() : "No tasks available.");
    }
}
