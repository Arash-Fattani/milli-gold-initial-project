package gold.milli.initialproject.service;

import gold.milli.initialproject.entity.Account;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account, Integer userId) throws Exception;

    Page<Account> getUserAccounts(Integer userId, Integer page, Integer size, String sortBy, String direction);

    Account updateAccount(Integer userId, Integer accountId, Account account) throws Exception;

    void deleteAccount(Integer userId, Integer accountId) throws Exception;
}
