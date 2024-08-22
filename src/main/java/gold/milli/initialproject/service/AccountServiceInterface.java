package gold.milli.initialproject.service;

import gold.milli.initialproject.entity.Account;

import java.util.List;

public interface AccountServiceInterface {
    Account createAccount(Account account, int id);

    List<Account> fetchAllAccounts(int id);

    Account updateAccount(int uid, int id, Account account);

    void deleteAccount(int uid, int id);
}
