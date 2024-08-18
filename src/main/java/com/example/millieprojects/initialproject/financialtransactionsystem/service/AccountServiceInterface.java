package com.example.millieprojects.initialproject.financialtransactionsystem.service;

import com.example.millieprojects.initialproject.financialtransactionsystem.entity.Account;

import java.util.List;

public interface AccountServiceInterface {
    Account saveAccount(Account account, int id);

    List<Account> getAllAccounts(int id);

    Account updateAccount(int uid, int id, Account account);

    void deleteAccount(int uid, int id);
}
