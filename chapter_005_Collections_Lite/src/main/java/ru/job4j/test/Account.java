package ru.job4j.test;

import java.util.Objects;

/**
 * Class Account.
 *
 * @author Ayuzyak
 * @since 02.05.2017
 * @version 1.0
 */
public class Account {
    /**
     * Account balance.
     */
    private double value;

    /**
     * Account requisites.
     */
    private int requisites;

    /**
     * Account constructor.
     * @param requisites of account.
     */
    public Account(int requisites) {
        this.requisites = requisites;
    }

    /**
     * Account value setter.
     * @param value of money.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Account value getter.
     * @return value of money.
     */
    public double getValue() {
        return value;
    }

    /**
     * Account requisites getter.
     * @return account requisites.
     */
    public int getRequisites() {
        return requisites;
    }

    /**
     * Comparing accounts.
     * @param o other account.
     * @return true if requisites of accounts are same.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return requisites == account.requisites;
    }

    /**
     * Generates a hash code.
     * @return hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(requisites);
    }
}