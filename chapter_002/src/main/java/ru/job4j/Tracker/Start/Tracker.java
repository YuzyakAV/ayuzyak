package ru.job4j.start;

import ru.job4j.tracker.models.Item;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class Tracker.
 * @author ayuzyak.
 * @version 1.
 * @since 07.02.2017.
 */

public class Tracker {
	/**
	 * tracker's length.
	 */
	private final int arrLength = 10;
	/**
	 * item.
	 */
	private Item[] items = new Item[arrLength];

	/**
	 * position.
	 */
	private int position = 0;

	/**
	 * random number for initialize Id.
	 */
	private static final Random RN = new Random();

	/**
	 * add item to items[].
	 * @param item - item for add.
	 * @return item.
	 */
	public Item add(Item item) {
		item.setId(generateId());
		if (position < items.length) {
			this.items[position++] = item;
		}
		return item;
	}

	/**
	 * search item by id.
	 * @param id - id number for search.
	 * @return result - item with required id.
	 */
	protected Item findById(String id) {
		Item result = null;
		for (Item i : this.items) {
			if (i != null && i.getId().equals(id)) {
				result = i;
				break;
			}
		}
		return result;
	}

	/**
	 * generate id for item.
	 * @return generated id.
	 */
	String generateId() {
		return String.valueOf(System.currentTimeMillis() + RN.nextInt());
	}

	/**
	 * getting all items.
	 * @return result - items array.
	 */
	public Item[] getAll() {
		Item[] result = new Item[this.position];
		for (int i = 0; i != this.position; i++) {
			result[i] = this.items[i];
		}
		return result;
	}

	/**
	 * update item.
	 * @param item - item for updating.
	 */
	public void update(Item item) {
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null && items[i].getId().equals(item.getId())) {
				items[i] = item;
				break;
			}
		}
	}

	/**
	 * delete item.
	 * @param item - item for deleting.
	 */
	public void delete(Item item) {
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null && items[i].getId().equals(item.getId())) {
				items[i] = null;
				break;
			}
		}
	}

	/**
	 * search item by name.
	 * @param key - item's name for search item.
	 * @return result - item with required name.
	 */
	Item findByName(String key) {
		Item result = null;
		for (Item i : this.items) {
			if (i != null && i.getName().equals(key)) {
				result = i;
				break;
			}
		}
		return result;
	}

	/**
	 * add comment to item.
	 * @param item - item for commenting.
	 * @param comment - adding comment.
	 */
	public void addComment(Item item, String comment) {
		ArrayList<String> comments;
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null && items[i].getId().equals(item.getId())) {
				comments = items[i].getComments();
				comments.add(comment);
				break;
			}
		}
	}

	/**
	 * show comments of item.
	 * @param item - item for showing comments.
	 * @return list of comments.
	 */
	public ArrayList<String> showComments(Item item) {
		ArrayList<String> comments = new ArrayList<>();
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null && items[i].getId().equals(item.getId())) {
				comments = items[i].getComments();
				break;
			}
		}
		return comments;
	}

	/**
	 * checking tracker for containing item Id.
	 * @param item - item for check.
	 * @return boolean - true if tracker contain item.
	 */
	public boolean hasId(Item item) {
		boolean trackerHasId = false;
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null && items[i].getId().equals(item.getId())) {
				trackerHasId = true;
				break;
			}
		}
		return trackerHasId;
	}
}