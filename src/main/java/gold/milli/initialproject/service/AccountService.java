package gold.milli.initialproject.service;

import gold.milli.initialproject.entity.Account;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account, int userId);

    List<Account> fetchAllAccounts(int userId);

    Account updateAccount(int userId, int accountId, Account account) throws Exception;

    void deleteAccount(int userId, int accountId) throws Exception;
}
