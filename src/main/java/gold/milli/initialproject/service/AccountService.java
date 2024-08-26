package gold.milli.initialproject.service;

import gold.milli.initialproject.entity.Account;
import gold.milli.initialproject.entity.CreateAccountRequestDto;
import org.springframework.data.domain.Page;

public interface AccountService {
    Account createAccount(CreateAccountRequestDto account, Long userId) throws Exception;

    Page<Account> getUserAccounts(Long userId, Integer page, Integer size, String sortBy, String direction);

    Account updateAccount(Long userId, Long accountId, Account account) throws Exception;

    void deleteAccount(Long userId, Long accountId) throws Exception;
}
