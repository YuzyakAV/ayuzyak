package ru.job4j.tracker.start;

import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.models.Task;

import java.util.ArrayList;

/**
 * Class StartUIwithSwitch.
 * @author Ayuzyak
 * @since 22.03.2017
 * @version 1.0
 */

public class StartUIwithSwitch {

	/**
	 * tracker of tasks.
	 */
	private Tracker tracker;

	/**
	 * Default Constructor Item.
	 */
	public StartUIwithSwitch() {
		tracker = new Tracker();
	}

	/**
	 * Constructor with tracker.
	 * @param tracker - tracker for edit.
	 */
	public StartUIwithSwitch(Tracker tracker) {
		this.tracker = tracker;
	}

	/**
	 * initialize input.
	 * @param enter for init.
	 */
	public void init(Enter enter) {
		int choice;

		Item item;
		String name;
		String desc;
		String id;
		String comment;
		ArrayList<String> comments;
		int countAddedTask = 0;

		do {
			System.out.println("Choose action from 1 to 8");
			System.out.println("1. Add new task.");
			System.out.println("2. Edit task.");
			System.out.println("3. Delete task.");
			System.out.println("4. Show all task.");
			System.out.println("5. Find task by filter.");
			System.out.println("6. Add comment to task.");
			System.out.println("7. Show all comments of task.");
			System.out.println("8. Exit.");
			name = enter.execute("Enter number of action: \n");

			choice = Integer.parseInt(name);
			switch (choice) {
				case 1:
					name = enter.execute("Enter name of task - ");
					desc = enter.execute("Enter description of task - ");
					item = new Task(name, desc);
					tracker.add(item);
					countAddedTask++;
					if (countAddedTask <= tracker.getAll().size()) {
						System.out.println("You have successfully added the task. Your task id is: " + item.getId());
					} else {
						System.out.println("Sorry you can't add task. List of task is full.");
					}
					break;
				case 2:
					id = enter.execute("Enter id of edit task - ");
					item = new Task();
					item.setId(id);
					if (tracker.hasId(item)) {
						name = enter.execute("Change name of task - ");
						desc = enter.execute("Change description of task to edit - ");
						item = new Task(name, desc);
						item.setId(id);
						tracker.update(item);
					} else {
						System.out.println("!!!There is not task with this ID!!!");
					}
					break;
				case 3:
					id = enter.execute("Enter id of edit task - ");
					item = new Task();
					item.setId(id);
					if (tracker.hasId(item)) {
						tracker.delete(item);
						System.out.println("Item deleted.");
					} else {
						System.out.println("!!!There is not task with this ID!!!");
					}
					break;
				case 4:
					System.out.println("The List of tasks: ");
					ArrayList<Item> items = tracker.getAll();
					for (int i = 0; i < items.size(); i++) {
						if (items.get(i) != null) {
							System.out.println("Task - " + items.get(i).getName() + " with id - " + items.get(i).getId());
						}
					}
					break;
				case 5:
					boolean rightChoice = false;
					int choiceFilter;
					while (!rightChoice) {
						System.out.println("Choose filter");
						System.out.println("1. Find by name.");
						System.out.println("2. Find by ID.");
						System.out.println("3. Return to main menu.");
						choiceFilter = Integer.parseInt(enter.execute("Enter number of action: \n"));
						if (choiceFilter == 1) {
							name = enter.execute("Enter the name of task - ");
							item = tracker.findByName(name);
							if (item != null) {
								System.out.println("Your task was found. Task id is: " + item.getId());
							} else {
								System.out.println("!!!There is not task with this name!!!");
							}
							rightChoice = true;
						} else if (choiceFilter == 2) {
							id = enter.execute("Enter the ID of task - ");
							item = tracker.findById(id);
							if (item != null) {
								System.out.println("Your task was found.");
							} else {
								System.out.println("!!!There is not task with this name!!!");
							}
							rightChoice = true;
						} else if (choiceFilter == 3) {
							rightChoice = true;
						} else {
							System.out.println("Enter right number of action");
						}
					}
					break;
				case 6:
					id = enter.execute("Enter id of task to comment - ");
					item = new Task();
					item.setId(id);
					if (tracker.hasId(item)) {
						comment = enter.execute("Enter your comment - ");
						tracker.addComment(item, comment);
					} else {
						System.out.println("!!!There is not task with this ID!!!");
					}
					break;
				case 7:
					id = enter.execute("Enter id of task to show all comments - ");
					item = new Task();
					item.setId(id);
					if (tracker.hasId(item)) {
						comments = tracker.showComments(item);
						if (comments.size() > 0) {
							for (int i = 1; i <= comments.size(); i++) {
								System.out.println("Comment #" + i + " - " + comments.get(i - 1));
							}
						} else {
							System.out.println(("This task is not got comments yet"));
						}
					} else {
						System.out.println("!!!There is not task with this ID!!!");
					}
					break;
				case 8:
					System.out.println("Good bye");
					break;
				default:
					System.out.println("You entered wrong number");
			}
		} while (choice != 8);
	}

	/**
	 * getter for tracker.
	 * @return tracker.
	 */
	public Tracker getTracker() {
		return tracker;
	}

	/**
	 * PSVM.
	 * @param args - args.
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to tracker!");
		Enter enter = new Enter(new ConsoleInput());
		new StartUIwithSwitch().init(enter);
	}
}
