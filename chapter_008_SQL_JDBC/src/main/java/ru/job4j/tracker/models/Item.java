package ru.job4j.tracker.models;

import java.util.ArrayList;

/**
 * Class Item.
 * @author Ayuzyak
 * @since 20.10.2017
 * @version 1.0
 */

public class Item {
	/**
	 * ID.
	 */
	private String id;

	/**
	 * name.
	 */
	private String name;

	/**
	 * description.
	 */
	private String description;

	/**
	 * date of creation.
	 */
	private long create;

	/**
	 * comments.
	 */
	private ArrayList<String> comments = new ArrayList<String>();

	/**
	 * Default Constructor Item.
	 */
	public Item() {

	}

	/**
	 * Constructor Item with name and description.
	 * @param name - name.
	 * @param description - description.
	 */
	public Item(String name, String description) {
		this.name = name;
		this.description = description;
		this.create = System.currentTimeMillis();
	}

	/**
	 * get name.
	 * @return name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * get description.
	 * @return description.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * get create.
	 * @return create.
	 */
	public long getCreate() {
		return this.create;
	}

	/**
	 * get id.
	 * @return id.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set item's id.
	 * @param id - id number.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * get comments.
	 * @return comments.
	 */
	public ArrayList<String> getComments() {
		return this.comments;
	}
}