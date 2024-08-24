package gold.milli.initialproject.service;

import gold.milli.initialproject.entity.Account;
import gold.milli.initialproject.entity.User;
import gold.milli.initialproject.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;

    @Override
    @Transactional
    public Account createAccount(Account account, int userId) throws Exception{
        User owner = userService.findUserById(userId);
        account.setOwner(owner);
        owner.addAccount(account);
        return accountRepository.save(account);
    }

    @Override
    public Page<Account> getUserAccounts(int userId, int page, int size, String sortBy, String direction) {
        Sort.Direction sortingDirection = Sort.Direction.fromString(direction.toUpperCase());
        Pageable pageable = PageRequest.of(page, size,Sort.by(sortingDirection, sortBy));
        return accountRepository.findAccountsByOwnerId(userId, pageable);
    }

    @Override
    @Transactional
    public Account updateAccount(int userId, int accountId, Account account) throws Exception {
        User owner = userService.findUserById(userId);
        Account prevAccount = getAccountAndCheckOwnership(owner, accountId);
        prevAccount.setAccountType(
                account.getAccountType() != null ? account.getAccountType() : prevAccount.getAccountType());
        prevAccount.setBalance(account.getBalance());
        return accountRepository.save(prevAccount);
    }

    public Account getAccountAndCheckOwnership(User owner, Integer accountId) throws Exception {
        Optional<Account> accountHolder = accountRepository.findById(accountId);
        if (accountHolder.isPresent()) {
            Account account = accountHolder.get();
            if (account.getOwner() != owner) {
                throw new Exception("this is not your account");
            }
            return account;
        } else
            throw new Exception("This account is not registered");
    }

    @Override
    @Transactional
    public void deleteAccount(int userId, int accountId) throws Exception {
        User owner = userService.findUserById(userId);
        Account prevAccount = getAccountAndCheckOwnership(owner, accountId);
        if (prevAccount.getOwner() != owner) {
            throw new Exception("this is not your account");
        } else {
            accountRepository.deleteById(accountId);
        }
    }
}
