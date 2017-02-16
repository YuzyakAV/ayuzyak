package ru.job4j.tracker.start;

import java.util.Scanner;

/**
 * @author ayuzyak.
 * @version 1.
 * @since 07.02.2017.
 */

public class ConsoleInput implements Input {

    /**
     * scanner for input.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Method for asking question.
     * @param question asking user for input.
     * @return scanner.
     */
    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    @Override
    public int ask(String question, int[] range) {
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