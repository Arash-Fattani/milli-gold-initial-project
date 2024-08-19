package gold.milli.initialproject.service;

import gold.milli.initialproject.entity.Account;
import gold.milli.initialproject.entity.User;

import java.util.List;

public interface AccountServiceInterface {
    Account saveAccount(Account account, int id, User owner);

    List<Account> getAllAccounts(int id);

    Account updateAccount(int uid, int id, Account account);

    void deleteAccount(int uid, int id);
}
