package gold.milli.initialproject.service;

import gold.milli.initialproject.entity.Account;
import gold.milli.initialproject.entity.User;
import gold.milli.initialproject.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountServiceInterface {

    private final AccountRepository accountRepository;
    private final UserService userService;

    @Override
    @Transactional
    public Account saveAccount(Account account, int id, User owner) {
        owner.addAccount(account);
        System.out.println("##########################" + account.getMoney());
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccounts(int id) {
        return accountRepository.findAccountsByOwnerUserId(id);
    }

    @Override
    @SneakyThrows
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
                    money(account.getMoney()).
                    build();
            return accountRepository.save(newAccount);
        }
        throw new Exception("This account is not registered");
    }

    @Override
    @SneakyThrows
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
    public String generateAccountNumber(){
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
