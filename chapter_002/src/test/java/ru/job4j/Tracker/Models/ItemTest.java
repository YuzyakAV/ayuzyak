package ru.job4j.tracker.models;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * Test class Item.
 *
 * @author ayuzyak.
 * @version 1.
 * @since 07.02.2017.
 */
 public class ItemTest {

	 /**
	 * Test method for checking getName().
	 */
	@Test
	public void whenItemNameIsItem() {
		Item item = new Item("Item", "Description");
		String resultName = item.getName();
		String checkName = "Item";
		assertThat(resultName, is(checkName));
	}

	/**
	 * Test method for checking getDescription().
	 */
	@Test
	public void whenItemDescriptionIsDescription() {
		Item item = new Item("Item", "Description");
		String resultDescription = item.getDescription();
		String checkDescription = "Description";
		assertThat(resultDescription, is(checkDescription));
	}

	/**
	 * Test method for checking getCreate().
	 */
	@Test
	public void whenItemCraeteIsCurrentTime() {
		Item item = new Item("Item", "Description");
		long resultTime = item.getCreate();
		long checkTime = System.currentTimeMillis();
		final long error = 100L;
		assertThat((double) resultTime, is(closeTo(checkTime, error)));
	}

	/**
	 * Test method for checking setId() and getID().
	 */
	@Test
	public void whenItemIdIsN123() {
		Item item = new Item("Item", "Description");
		item.setId("N123");
		String resultId = item.getId();
		String checkId = "N123";
		assertThat(resultId, is(checkId));
	}

	/**
	 * Test method for checking getComments().
	 */
	@Test
	public void whenGetComments() {
		Item item = new Item("Item", "Description");
		ArrayList<String> refComments = item.getComments();
		refComments.add("Test");
		refComments.add("Comment");
		ArrayList<String> resultComments = item.getComments();
		ArrayList<String> checkComments = new ArrayList<>();
		checkComments.add("Test");
		checkComments.add("Comment");
		assertThat(resultComments, is(checkComments));
	}
 }