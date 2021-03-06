package ru.job4j.tracker.start;

import ru.job4j.tracker.models.Item;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class Tracker.
 * @author Ayuzyak
 * @since 26.02.2016
 * @version 1.0
 */

public class Tracker {

	/**
	 * item.
	 */
	private ArrayList<Item> items = new ArrayList<>();

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
		this.items.add(item);
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
	public ArrayList<Item> getAll() {

		return this.items;
	}

	/**
	 * update item.
	 * @param item - item for updating.
	 */
	public void update(Item item) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) != null && items.get(i).getId().equals(item.getId())) {
				items.set(i, item);
				break;
			}
		}
	}

	/**
	 * delete item.
	 * @param item - item for deleting.
	 */
	public void delete(Item item) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) != null && items.get(i).getId().equals(item.getId())) {
				items.set(i, null);
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
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) != null && items.get(i).getId().equals(item.getId())) {
				comments = items.get(i).getComments();
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
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) != null && items.get(i).getId().equals(item.getId())) {
				comments = items.get(i).getComments();
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
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) != null && items.get(i).getId().equals(item.getId())) {
				trackerHasId = true;
				break;
			}
		}
		return trackerHasId;
	}
}
