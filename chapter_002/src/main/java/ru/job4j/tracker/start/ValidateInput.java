package ru.job4j.tracker.start;

import java.util.ArrayList;

/**
 * Class ValidateInput.
 * @author Ayuzyak
 * @since 19.03.2017
 * @version 1.0
 */

public class ValidateInput extends ConsoleInput {

    @Override
    public int ask(String question, ArrayList<Integer> range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please select key from menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter valid data.");
            }
        } while (invalid);
        return value;
    }
}
