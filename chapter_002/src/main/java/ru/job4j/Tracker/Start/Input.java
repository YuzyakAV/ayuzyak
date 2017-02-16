package ru.job4j.tracker.start;

/**
 * Interface Input.
 * @author ayuzyak.
 * @version 1.
 * @since 07.02.2017.
 */

public interface Input {

	/**
	 * Abstract method for asking question.
	 * @param question asking user for input.
	 * @return String.
	 */
	String ask(String question);

	/**
	 * Abstract method for asking number of action's.
	 * @param question asking user for input.
	 * @param range of numbers.
	 * @return String.
	 */
	int ask(String question, int[] range);
}