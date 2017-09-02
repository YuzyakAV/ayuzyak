package ru.job4j.jmm;

/**
 * Class BankAccount.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 02.09.2017
 */
public class BankAccount {
    /**
     * Account of client.
     */
    private int account = 0;

    /**
     * Deposit money to account.
     * @param money for deposit.
     */
    private void deposit(int money) {
        this.account += money;
    }

    /**
     * Deposit to account n-times.
     */
    public void multiDeposit() {
        for (int i = 0; i < 100_000; i++) {
            this.deposit(1);
        }
    }

    /**
     * Withdraw money from account.
     * @param money for withdraw.
     */
    public void withdraw(int money) {
        this.account -= money;
    }

    /**
     * Withdraw from account n-times.
     */
    public void multiWithdraw() {
        for (int i = 0; i < 100_000; i++) {
            withdraw(1);
        }
    }

    /**
     * Account getter.
     * @return account.
     */
    public int getAccount() {
        return account;
    }

    /**
     * Main method.
     * This method shows multithreading problems.
     * @param args from cmd-line.
     */
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();

        Thread depositThread = new Thread(new Runnable() {
            @Override
            public void run() {
                bankAccount.multiDeposit();
            }
        });

        Thread withdrawThread = new Thread(new Runnable() {
            @Override
            public void run() {
                bankAccount.multiWithdraw();
            }
        });
        Thread depositThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                bankAccount.multiDeposit();
            }
        });

        Thread withdrawThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                bankAccount.multiWithdraw();
            }
        });
        depositThread.start();
        withdrawThread.start();
        depositThread2.start();
        withdrawThread2.start();
        try {
            depositThread.join();
            withdrawThread.join();
            depositThread2.join();
            withdrawThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(bankAccount.getAccount());
    }
}