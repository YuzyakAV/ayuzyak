package ru.job4j.tracker.start;

import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.models.Task;

import java.util.ArrayList;

/**
 * Class StartUI.
 * @author ayuzyak.
 * @version 1.
 * @since 07.02.2017.
 */

public class StartUI {

	/**
	 * tracker of tasks.
	 */
	private Tracker tracker;

	/**
	 * Default Constructor Item.
	 */
	public StartUI() {
		tracker = new Tracker();
	}

	/**
	 * Constructor with tracker.
	 * @param tracker - tracker for edit.
	 */
	public StartUI(Tracker tracker) {
		this.tracker = tracker;
	}

	}