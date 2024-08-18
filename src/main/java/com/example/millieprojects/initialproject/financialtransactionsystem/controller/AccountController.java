package com.example.millieprojects.initialproject.financialtransactionsystem.controller;

import com.example.millieprojects.initialproject.financialtransactionsystem.entity.Account;
import com.example.millieprojects.initialproject.financialtransactionsystem.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    private AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Transactional
    @Operation(
            summary = "create a new Account",
            description = "create a new account for a logged in account"
    )
    @PostMapping("/user/{id}/create-account")
    public Account saveAccount(@RequestBody Account account, @PathVariable int id){
      return accountService.saveAccount(account, id);
    }
    @Transactional
    @Operation(
            summary = "get owned account",
            description = "get all of the accounts belonging to a specefic user"
    )
    @GetMapping("/user/{id}/accounts")
    public List<Account> fetchAllAccounts(@PathVariable int id){
        return accountService.getAllAccounts(id);
    }
    @Transactional
    @Operation(
            summary = "update account",
            description = "Update Account type and balance"
    )
    @PutMapping("/user/{Uid}/account/{id}")
    public Account updateAccount(@PathVariable int Uid, @PathVariable int id, @RequestBody Account account){
        return accountService.updateAccount(Uid, id, account);
    }
    @Transactional
    @Operation(
            summary = "delete an account",
            description = "delete a desired account by account id"
    )
    @DeleteMapping("/user/{Uid}/account/{id}/delete")
    public void deleteAccount(@PathVariable int Uid, @PathVariable int id){
        accountService.deleteAccount(Uid, id);
    }
}
