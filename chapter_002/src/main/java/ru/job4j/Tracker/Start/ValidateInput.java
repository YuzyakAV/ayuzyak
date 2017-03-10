package ru.job4j.tracker.start;

/**
 * Class ValidateInput.
 * @author yuzyakav
 * @since 10.03.2017
 * @version 1.0
 */

public class ValidateInput extends ConsoleInput {

    @Override
    public int ask(String question, int[] range) {
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