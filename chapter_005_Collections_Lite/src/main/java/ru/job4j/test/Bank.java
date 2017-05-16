package ru.job4j.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class Bank.
 *
 * @author Ayuzyak
 * @since 02.05.2017
 * @version 1.0
 */
public class Bank {
    /**
     * map with key - user and value - list of user accounts.
     */
    private Map<User, List<Account>> map;

    /**
     * Bank constructor.
     */
    public Bank() {
        map = new HashMap<>();
    }

    /**
     * Add client to bank.
     * @param user - bank client.
     */
    public void addUser(User user) {
        map.put(user, new ArrayList<Account>());
    }

    /**
     * Delete client from bank.
     * @param user for deleting.
     * @throws NoSuchUserException if the bank has no client.
     */
    public void deleteUser(User user) throws NoSuchUserException {
        if (map.containsKey(user)) {
            map.remove(user);
        } else {
            throw new NoSuchUserException();
        }
    }

    /**
     * Add account to client from bank.
     * @param user for adding account.
     * @param account for adding to user's account list.
     * @throws NoSuchUserException if the bank has no client.
     */
    public void addAccountToUser(User user, Account account) throws NoSuchUserException {
        if (map.containsKey(user)) {
            map.get(user).add(account);
        } else {
            throw new NoSuchUserException();
        }
    }

    /**
     * Delete account from client's account list.
     * @param user for deleting account.
     * @param account for deleting from user's account list.
     * @throws NoSuchUserException if the bank has no client.
     */
    public void deleteAccountFromUser(User user, Account account) throws NoSuchUserException {
        if (map.containsKey(user)) {
            List<Account> list = map.get(user);
            if (list.contains(account)) {
                list.remove(account);
            } else {
                System.out.println("Client hasn't this account");
            }
        } else {
            throw new NoSuchUserException();
        }
    }

    /**
     * Get client's account list.
     * @param user for getting account list.
     * @return user's account list.
     * @throws NoSuchUserException if the bank has no client.
     */
    public List<Account> getUserAccounts(User user) throws NoSuchUserException {
        List<Account> list = null;
        if (map.containsKey(user)) {
            list = map.get(user);
        } else {
            throw new NoSuchUserException();
        }
        return list;
    }

    /**
     * Transfer money from one account to other. Return false if bank hasn't source or destination accounts
     * and if source account's balance is less than transfer value.
     *
     * @param srcUser - source client.
     * @param srcAccount - source client's account.
     * @param dstUser - destination client.
     * @param dstAccount - destination client's account.
     * @param amount - value of money for transfer.
     * @return true if transfer is made.
     * @throws NoSuchUserException if the bank has no client.
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount)
            throws NoSuchUserException {
        boolean transfer = false;
        if (map.containsKey(srcUser) && map.containsKey(dstUser)) {
            List<Account> srcAccountList = map.get(srcUser);
            List<Account> dstAccountList = map.get(dstUser);
            if (srcAccountList.contains(srcAccount) && dstAccountList.contains(dstAccount)) {
                Account srcAccFromList = this.getAccountFromList(srcUser, srcAccount);
                Account dstAccFromList = this.getAccountFromList(dstUser, dstAccount);
                double srcBalance = srcAccFromList.getValue();
                double dstBalance = dstAccFromList.getValue();
                if (srcBalance >= amount) {
                    srcAccFromList.setValue(srcBalance - amount);
                    dstAccFromList.setValue(dstBalance + amount);
                    transfer = true;
                }
            }
        } else {
            throw new NoSuchUserException();
        }
        return transfer;
    }

    /**
     * Get reference to client's account.
     * @param user for getting reference to account.
     * @param account for getting reference.
     * @return reference to user's account.
     */
    private Account getAccountFromList(User user, Account account) {
        Account accountFromList = null;
        for (Account accounts : map.get(user)) {
            if (accounts.equals(account)) {
                accountFromList = accounts;
            }
        }
        return accountFromList;
    }

    /**
     * Getter copy of map.
     * @return copy of map.
     */
    Map<User, List<Account>> getMap() {
        return new HashMap<User, List<Account>>(this.map);
    }

    /**
     * Getting reference to user.
     * @param name of client.
     * @param passport of client.
     * @return reference to client.
     * @throws NoSuchUserException if bank hasn't that client.
     */
    User getUser(String name, int  passport) throws NoSuchUserException {
        User user = new User(name, passport);
        if (map.containsKey(user)) {
            for (User client : map.keySet()) {
                if (client.equals(user)) {
                    user = client;
                    break;
                }
            }
        } else {
            throw new NoSuchUserException();
        }
        return user;
    }
}
/**
 * Outer Exception-class. Throws if bank has no given client.
 */
class NoSuchUserException extends Exception {
    /**
     * Overridin method toString.
     * @return text - "No such client".
     */
    @Override
    public String toString() {
        return "No such client";
    }
}