package ru.job4j.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test for Bank.
 *
 * @author Ayuzyak
 * @since 01.05.2016
 * @version 1.0
 */
public class BankTest {
    /**
     * test adding client to bank.
     */
    @Test
    public void whenAddClientToBank() {
        User firstUser = new User("John", 100555);
        User secondUser = new User("Ivan", 200321);
        User thirdUser = new User("Mary", 150200);
        Bank bank = new Bank();
        bank.addUser(firstUser);
        bank.addUser(secondUser);
        bank.addUser(thirdUser);
        Map<User, List<Account>> resultMap = bank.getMap();
        Map<User, List<Account>> checkMap = new HashMap<>();
        checkMap.put(firstUser, new ArrayList<>());
        checkMap.put(secondUser, new ArrayList<>());
        checkMap.put(thirdUser, new ArrayList<>());
        assertThat(resultMap, is(checkMap));
    }

    /**
     * test deleting client from bank.
     */
    @Test
    public void whenDeleteClientFromBank() {
        User firstUser = new User("John", 100555);
        User secondUser = new User("Ivan", 200321);
        User thirdUser = new User("Mary", 150200);
        Bank bank = new Bank();
        bank.addUser(firstUser);
        bank.addUser(secondUser);
        bank.addUser(thirdUser);
        try {
            bank.deleteUser(secondUser);
        } catch (NoSuchUserException e) {
            e.printStackTrace();
        }
        Map<User, List<Account>> resultMap = bank.getMap();
        Map<User, List<Account>> checkMap = new HashMap<>();
        checkMap.put(firstUser, new ArrayList<>());
        checkMap.put(thirdUser, new ArrayList<>());
        assertThat(resultMap, is(checkMap));
    }

    /**
     * test adding account to bank client.
     */
    @Test
    public void whenAddAccountToClient() {
        User firstUser = new User("John", 100555);
        User secondUser = new User("Ivan", 200321);
        Bank bank = new Bank();
        bank.addUser(firstUser);
        bank.addUser(secondUser);
        Account account = new Account(12345);
        try {
            bank.addAccountToUser(secondUser, account);
        } catch (NoSuchUserException e) {
            e.printStackTrace();
        }
        Map<User, List<Account>> resultMap = bank.getMap();
        Map<User, List<Account>> checkMap = new HashMap<>();
        checkMap.put(firstUser, new ArrayList<>());
        List<Account> list = new ArrayList<>();
        list.add(account);
        checkMap.put(secondUser, list);
        assertThat(resultMap, is(checkMap));
    }

    /**
     * test deleting account from client's account list.
     */
    @Test
    public void whenDeleteAccountFromClient() {
        User firstUser = new User("John", 100555);
        User secondUser = new User("Ivan", 200321);
        Bank bank = new Bank();
        bank.addUser(firstUser);
        bank.addUser(secondUser);
        Account firstAccount = new Account(12345);
        Account secondAccount = new Account(234);
        try {
            bank.addAccountToUser(secondUser, firstAccount);
            bank.addAccountToUser(secondUser, secondAccount);
            bank.deleteAccountFromUser(secondUser, firstAccount);
        } catch (NoSuchUserException e) {
            e.printStackTrace();
        }
        Map<User, List<Account>> resultMap = bank.getMap();
        Map<User, List<Account>> checkMap = new HashMap<>();
        checkMap.put(firstUser, new ArrayList<>());
        List<Account> list = new ArrayList<>();
        list.add(secondAccount);
        checkMap.put(secondUser, list);
        assertThat(resultMap, is(checkMap));
    }

    /**
     * test getting client's account list.
     */
    @Test
    public void whenGetListOfAccountsFromClient() {
        User firstUser = new User("John", 100555);
        User secondUser = new User("Ivan", 200321);
        Bank bank = new Bank();
        bank.addUser(firstUser);
        bank.addUser(secondUser);
        Account firstAccount = new Account(12345);
        Account secondAccount = new Account(234);
        Account thirdAccount = new Account(555);
        List<Account> resultList = null;
        try {
            bank.addAccountToUser(secondUser, firstAccount);
            bank.addAccountToUser(secondUser, secondAccount);
            bank.addAccountToUser(secondUser, thirdAccount);
            resultList = bank.getUserAccounts(secondUser);
        } catch (NoSuchUserException e) {
            e.printStackTrace();
        }
        List<Account> checkList = new ArrayList<>();
        checkList.add(firstAccount);
        checkList.add(secondAccount);
        checkList.add(thirdAccount);
        assertThat(resultList, is(checkList));
    }

    /**
     * test transfer money.
     * Transfer from account to other, when balance of source account more than transfer value.
     */
    @Test
    public void transferWhenOneAccountsMoreThanTransferValue() {
        User firstUser = new User("John", 100555);
        User secondUser = new User("Ivan", 200321);
        Bank bank = new Bank();
        bank.addUser(firstUser);
        bank.addUser(secondUser);
        Account firstAccount = new Account(12345);
        firstAccount.setValue(500);
        Account secondAccount = new Account(234);
        secondAccount.setValue(100);
        boolean resultTransfer = false;
        try {
            bank.addAccountToUser(firstUser, firstAccount);
            bank.addAccountToUser(secondUser, secondAccount);
            resultTransfer = bank.transferMoney(firstUser, firstAccount, secondUser, secondAccount, 100);
        } catch (NoSuchUserException e) {
            e.printStackTrace();
        }
        double resultValueFirstAccount = firstAccount.getValue();
        double resultValueSecondAccount = secondAccount.getValue();
        double checkValueFirstAccount = 400;
        double checkValueSecondAccount = 200;
        assertThat(resultTransfer, is(true));
        assertThat(resultValueFirstAccount, is(checkValueFirstAccount));
        assertThat(resultValueSecondAccount, is(checkValueSecondAccount));
    }

    /**
     * test transfer money.
     * Transfer from account to other, when balance of source account less than destination.
     */
    @Test
    public void transferWhenOneAccountsLessThanTransferValue() {
        User firstUser = new User("John", 100555);
        User secondUser = new User("Ivan", 200321);
        Bank bank = new Bank();
        bank.addUser(firstUser);
        bank.addUser(secondUser);
        Account firstAccount = new Account(12345);
        firstAccount.setValue(100);
        Account secondAccount = new Account(234);
        secondAccount.setValue(100);
        boolean resultTransfer = false;
        try {
            bank.addAccountToUser(firstUser, firstAccount);
            bank.addAccountToUser(secondUser, secondAccount);
            resultTransfer = bank.transferMoney(firstUser, firstAccount, secondUser, secondAccount, 101);
        } catch (NoSuchUserException e) {
            e.printStackTrace();
        }
        assertThat(resultTransfer, is(false));
    }

    /**
     * test transfer money.
     * Transfer from account to other, when no source account in client's account list.
     */
    @Test
    public void transferWhenNoSourceAccount() {
        User firstUser = new User("John", 100555);
        User secondUser = new User("Ivan", 200321);
        Bank bank = new Bank();
        bank.addUser(firstUser);
        bank.addUser(secondUser);
        Account firstAccount = new Account(12345);
        firstAccount.setValue(500);
        Account secondAccount = new Account(234);
        secondAccount.setValue(200);
        Account thirdAccount = new Account(555);
        thirdAccount.setValue(300);
        boolean resultTransfer = false;
        try {
            bank.addAccountToUser(firstUser, firstAccount);
            bank.addAccountToUser(secondUser, secondAccount);
            resultTransfer = bank.transferMoney(firstUser, thirdAccount, secondUser, secondAccount, 50);
        } catch (NoSuchUserException e) {
            e.printStackTrace();
        }
        assertThat(resultTransfer, is(false));
    }

    /**
     * test throwing NoSuchUserException.
     */
    @Test
    public void whenBankHasNoClient() {
        User firstUser = new User("John", 100555);
        User secondUser = new User("Ivan", 200321);
        Bank bank = new Bank();
        bank.addUser(firstUser);
        String resultException = "NoException";
        try {
            bank.deleteUser(secondUser);
        } catch (NoSuchUserException e) {
            resultException = "NoSuchUserException was caught";
        }
        String checkException = "NoSuchUserException was caught";
        assertThat(resultException, is(checkException));
    }

    /**
     * test getting client from bank.
     */
    @Test
    public void whenBankHasClient() {
        User firstUser = new User("John", 100555);
        Bank bank = new Bank();
        bank.addUser(firstUser);
        Account account = new Account(1001);
        account.setValue(500);
        User client = null;
        List<Account> accountListFirstUser = null;
        List<Account> accountListClient = null;
        try {
            client = bank.getUser("John", 100555);
            bank.addAccountToUser(firstUser, account);
            accountListFirstUser = bank.getUserAccounts(firstUser);
            accountListClient = bank.getUserAccounts(client);
        } catch (NoSuchUserException e) {
            e.printStackTrace();
        }
        assertThat(accountListFirstUser, is(accountListClient));
    }
}