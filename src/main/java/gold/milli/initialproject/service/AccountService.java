package gold.milli.initialproject.service;

import gold.milli.initialproject.entity.Account;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account, int userId);

    Page<Account> fetchAllAccounts(int userId, int page, int size, String sortBy, String direction);

    Account updateAccount(int userId, int accountId, Account account) throws Exception;

    void deleteAccount(int userId, int accountId) throws Exception;
}
