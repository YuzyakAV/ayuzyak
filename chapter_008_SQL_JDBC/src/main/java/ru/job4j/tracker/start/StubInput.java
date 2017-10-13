package ru.job4j.tracker.start;
 
 import java.util.ArrayList;
 
 /**
  * Class StubInput.
  * @author Ayuzyak
  * @since 10.10.2017
  * @version 1.0
  */
 
 public class StubInput implements Input {
 
 	/**
 	 * answers.
 	 */
 	private String[] answers;
 
 	/**
 	 * position of answer.
 	 */
 	private int position = 0;
 
 	/**
 	 * constructor for StubInput.
 	 * @param answers - array of String's
 	 */
 	public StubInput(String[] answers) {
 		this.answers = answers;
 	}
 
 	/**
 	 * Method for asking question.
 	 * @param question asking user for input.
 	 * @return answers.
 	 */
 	public String ask(String question) {
 		return answers[position++];
 	}
 
 	@Override
 	public int ask(String question, ArrayList<Integer> range) {
 		int key = Integer.parseInt(this.ask(question));
 		boolean exist = false;
 		for (int value : range) {
 			if (value == key) {
 				exist = true;
 				break;
 			}
 		}
 		if (exist) {
 			return key;
 		} else {
 			throw new MenuOutException("Out of menu range");
 		}
 	}
 } 