package gold.milli.initialproject.service;

import gold.milli.initialproject.entity.Account;
import gold.milli.initialproject.entity.User;
import gold.milli.initialproject.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements AccountServiceInterface {

    private final AccountRepository accountRepository;
    private final UserService userService;

    @Override
    @Transactional
    public Account createAccount(Account account, int id) {
        User owner = userService.findUserById(id);
        Account newAccount = account.toBuilder().owner(owner).build();
        owner.addAccount(newAccount);
        return accountRepository.save(newAccount);
    }

    @Override
    public List<Account> fetchAllAccounts(int id) {
        return accountRepository.findAccountsByOwnerUserId(id);
    }

    @Override
    @SneakyThrows
    @Transactional
    public Account updateAccount(int uid, int id, Account account) {
        User owner = userService.findUserById(uid);
        Optional<Account> accountHolder = accountRepository.findById(id);
        if (accountHolder.isPresent()){
            Account prevAccount = accountHolder.get();
            if (prevAccount.getOwner() != owner){
                throw new Exception("this is not your account");
            }
            Account newAccount = prevAccount.toBuilder().
                    accountType(account.getAccountType()).
                    balance(account.getBalance()).
                    build();
            return accountRepository.save(newAccount);
        }
        throw new Exception("This account is not registered");
    }

    @Override
    @SneakyThrows
    @Transactional
    public void deleteAccount(int uid, int id) {
        User owner = userService.findUserById(uid);
        Optional<Account> accountHolder = accountRepository.findById(id);
        if (accountHolder.isPresent()){
            Account prevAccount = accountHolder.get();
            if (prevAccount.getOwner() != owner){
                throw new Exception("this is not your account");
            }else {
                accountRepository.deleteById(id);
            }
        }else
            throw new Exception("This account is not registered");
    }
}
