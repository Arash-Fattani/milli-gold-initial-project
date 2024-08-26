package gold.milli.initialproject.service;

import gold.milli.initialproject.entity.Account;
import gold.milli.initialproject.entity.User;
import gold.milli.initialproject.entity.CreateAccountRequestDto;
import gold.milli.initialproject.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;

    @Override
    @Transactional
    public Account createAccount(CreateAccountRequestDto accountRequest, Long userId) throws Exception{
        User owner = userService.findUserById(userId);
        Account account = new Account(generateAccountNumber(), accountRequest.getBalance(),
                accountRequest.getAccountType(), owner);
        owner.addAccount(account);
        return accountRepository.save(account);
    }

    @Override
    public Page<Account> getUserAccounts(Long userId, Integer page, Integer size, String sortBy, String direction) {
        Sort.Direction sortingDirection = Sort.Direction.fromString(direction.toUpperCase());
        Pageable pageable = PageRequest.of(page, size,Sort.by(sortingDirection, sortBy));
        return accountRepository.findAccountsByOwnerId(userId, pageable);
    }

    @Override
    @Transactional
    public Account updateAccount(Long userId, Long accountId, Account account) throws Exception {
        User owner = userService.findUserById(userId);
        Account prevAccount = getAccountAndCheckOwnership(owner, accountId);
        prevAccount.setAccountType(
                account.getAccountType() != null ? account.getAccountType() : prevAccount.getAccountType());
        prevAccount.setBalance(account.getBalance());
        return accountRepository.save(prevAccount);
    }

    public Account getAccountAndCheckOwnership(User owner, Long accountId) throws Exception {
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
    public void deleteAccount(Long userId, Long accountId) throws Exception {
        User owner = userService.findUserById(userId);
        Account prevAccount = getAccountAndCheckOwnership(owner, accountId);
        accountRepository.deleteById(prevAccount.getId());
    }
    private String generateAccountNumber(){
        Random random  = new SecureRandom();
        StringBuilder accountNumber = new StringBuilder();
        int accountNumberLength = 10;
        while (true) {
            for (int i = 0; i < accountNumberLength; i++) {
                accountNumber.append(random.nextInt(10));
            }
            if (!accountRepository.existsAccountByAccountNumber(accountNumber.toString()))
                break;
            else
                accountNumber.delete(0, 10);
        }
        return accountNumber.toString();
    }
}
