package com.booleanuk.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class AccountTest {
    @Test
    void createShouldSucceed() {
        Assertions.assertNotNull(Account.create("Test Branch"));
    }

    @Test
    void transactionShouldSucceed() {
        Account account = Account.create("Test Branch");
        int transactionsBefore = account.transactions.size();
        account.transaction(new Deposit(LocalDate.now(), 100.0, account.balance()));
        int transactionsAfter = account.transactions.size();

        Assertions.assertEquals(transactionsAfter, transactionsBefore + 1);
    }
    @Test
    void transactionShouldFail() {
        Account account = Account.create("Test Branch");

        Assertions.assertEquals(0.0, account.balance());

        int transactionsBefore = account.transactions.size();

        Assertions.assertFalse(account.transaction(new Withdraw(LocalDate.now(), 100.0, account.balance())));

        int transactionsAfter = account.transactions.size();

        Assertions.assertEquals(transactionsAfter, transactionsBefore);
    }

    @Test
    void balanceShouldSucceed() {
        Account account = Account.create("Test Branch");
        Assertions.assertEquals(0.0, account.balance());

        account.transaction(new Deposit(LocalDate.now(), 100.0, account.balance()));

        Assertions.assertEquals(100.0, account.balance());
    }
}