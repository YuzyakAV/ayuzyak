package ru.job4j.tracker.models;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class Task.
 *
 * @author ayuzyak.
 * @version 1.
 * @since 07.02.2017.
 */
 public class TaskTest {

	 /**
	 * Test method for checking calculatePrice().
	 */
	@Test
	public void whenUseMethodThenReturn100() {
		Task task = new Task("Item", "Description");
		String resultPrice = task.calculatePrice();
		String checkPrice = "100%";
		assertThat(resultPrice, is(checkPrice));
	}
 }