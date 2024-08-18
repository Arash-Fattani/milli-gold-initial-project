package com.example.millieprojects.initialproject.financialtransactionsystem.service;

import com.example.millieprojects.initialproject.financialtransactionsystem.entity.Account;
import com.example.millieprojects.initialproject.financialtransactionsystem.entity.User;
import com.example.millieprojects.initialproject.financialtransactionsystem.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements AccountServiceInterface {
    @Autowired
    public AccountService(AccountRepository accountRepository, UserService userService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
    }

    private AccountRepository accountRepository;
    private UserService userService;

    @Override
    @Transactional
    public Account saveAccount(Account account, int id) {
        User owner = userService.findUserById(id);
        Account newAccount = account.toBuilder().owner(owner).build();
        owner.addAccount(newAccount);
        return accountRepository.save(newAccount);
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
                    balance(account.getBalance()).
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
}
