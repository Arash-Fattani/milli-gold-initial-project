package gold.milli.initialproject.service;

import gold.milli.initialproject.entity.Account;
import gold.milli.initialproject.entity.User;
import gold.milli.initialproject.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserServiceImpl userServiceImpl;

    @Override
    @Transactional
    public Account createAccount(Account account, int userId) {
        User owner = userServiceImpl.findUserById(userId);
        account.setOwner(owner);
        owner.addAccount(account);
        return accountRepository.save(account);
    }

    @Override
    public List<Account> fetchAllAccounts(int userId) {
        return accountRepository.findAccountsByOwnerUserId(userId);
    }

    @Override
    @Transactional
    public Account updateAccount(int userId, int accountId, Account account)throws Exception {
        User owner = userServiceImpl.findUserById(userId);
        Optional<Account> accountHolder = accountRepository.findById(accountId);
        if (accountHolder.isPresent()){
            Account prevAccount = accountHolder.get();
            if (prevAccount.getOwner() != owner){
                throw new Exception("this is not your account");
            }
            prevAccount.setAccountType(
                    account.getAccountType()!= null ? account.getAccountType() : prevAccount.getAccountType());
            prevAccount.setBalance(account.getBalance());
            return accountRepository.save(prevAccount);
        }
        throw new Exception("This account is not registered");
    }

    @Override
    @Transactional
    public void deleteAccount(int userId, int accountId) throws Exception{
        User owner = userServiceImpl.findUserById(userId);
        Optional<Account> accountHolder = accountRepository.findById(accountId);
        if (accountHolder.isPresent()){
            Account prevAccount = accountHolder.get();
            if (prevAccount.getOwner() != owner){
                throw new Exception("this is not your account");
            }else {
                accountRepository.deleteById(accountId);
            }
        }else
            throw new Exception("This account is not registered");
    }
}