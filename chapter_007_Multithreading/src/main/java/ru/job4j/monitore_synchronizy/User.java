package ru.job4j.monitore_synchronizy;

/**
 * User.
 *
 * @author Ayuzyak
 * @since 07.09.2017
 * @version 1.0
 */
public class User {

    /**
     * Static ID.
     */
    private static int currentId;

    /**
     * ID for user.
     */
    private int id;

    /**
     * Amount of user's money.
     */
    private int amount;

    /**
     * Constructor of user.
     */
    public User() {
        this.id = ++currentId;
    }

    /**
     * Getter for amount.
     * @return amount.
     */
    public int getAmount() {
        return amount;
    }


    /**
     * Setter for amount.
     * @param amount of money.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Getter for ID.
     * @return user's ID.
     */
    public int getId() {
        return id;
    }
}