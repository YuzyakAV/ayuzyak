package ru.job4j.tracker.models;

/**
 * Class Task.
 * @author ayuzyak.
 * @version 1.
 * @since 07.02.2017.
 */

public class Task extends Item {
	/**
	 * Constructor Task with name and description.
	 * @param name - name.
	 * @param desc - description.
	 */
	public Task(String name, String desc) {
		super(name, desc);
	}

	/**
	 * Default Constructor Task.
	 */
	public Task() {
		super();
	}

	/**
	 * calculate price.
	 * @return 100%.
	 */
	public String calculatePrice() {
		return "100%";
	}
}