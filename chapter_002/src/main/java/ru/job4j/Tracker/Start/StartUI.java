package ru.job4j.tracker.start;

/**
 * Class StartUI.
 * @author yuzyakav
 * @since 26.02.2017
 * @version 1.0
 */

public class StartUI {
	/**
	 * range of actions.
	 */
	private int[] range;

	/**
	 * variable of input.
	 */
	private Input input;

	/**
	 * task tracker.
	 */
	private Tracker tracker = new Tracker();
	/**
	 * constructor for MenuTracker.
	 * @param input for enter information.
	 */
	public StartUI(Input input) {
		this.input = input;
	}
	/**
	 * initialize input.
	 */
	public void init() {
		MenuTracker menu = new MenuTracker(this.input, tracker);
		menu.fillActions();
		UserAction exitAction = new BaseAction("Exit.") {
			/**
			 * key for choose.
			 * @return int key.
			 */
			public int key() {
				return 8;
			}

			/**
			 * method for execute showing comments.
			 * @param input for enter information.
			 * @param tracker for tasks.
			 */
			public void execute(Input input, Tracker tracker) {
				System.out.println("Good bye");
			}
		};
		menu.addAction(exitAction);
		range = menu.getRangeActions();
		int choice;
		System.out.println("Welcome to tracker!");
		do {
			menu.show();
			choice = input.ask("Enter number of action: \n", range);
			menu.select(choice);
		} while (choice != 8);
	}

	/**
	 * PSVM.
	 * @param args - args.
	 */
	public static void main(String[] args) {
		Input input = new ValidateInput();
		new StartUI(input).init();
	}

	/**
	 * method for getting tracker.
	 * @return tracker.
	 */
	public Tracker getTracker() {
		return tracker;
	}

	/**
	 * method for setting tracker.
	 * @param  tracker for setting.
	 */
	public void setTracker(Tracker tracker) {
		this.tracker = tracker;
	}
}