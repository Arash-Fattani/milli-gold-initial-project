package gold.milli.initialproject.controller;

import gold.milli.initialproject.entity.Account;
import gold.milli.initialproject.service.AccountServiceImpl;
import gold.milli.initialproject.entity.AccountDTOCreate;
import gold.milli.initialproject.entity.User;
import gold.milli.initialproject.mapper.AccountCreateMapper;
import gold.milli.initialproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountServiceImpl accountServiceImpl;

    private final AccountService accountService;
    private final UserService userService;
    private final AccountCreateMapper accountCreateMapper;


    @Operation(
            summary = "create a new Account",
            description = "create a new account for a logged in account"
    )
    @PostMapping("/user/{userId}")
    public Account createAccount(@RequestBody Account account, @PathVariable int userId) {
        return accountServiceImpl.createAccount(account, userId);
    }

    @PostMapping("/user/{id}/create-account")
    public AccountDTOCreate saveAccount(@RequestBody AccountDTOCreate account, @PathVariable int id) {
        System.out.println(account.getBalance());
        User owner = this.userService.findUserById(id);
        Account newAccount = (this.accountCreateMapper.AccountCreateFromRequest
                (account, owner, accountService.generateAccountNumber()));
        return this.accountCreateMapper.AccountDTOCreateFromAccount(accountService.saveAccount(newAccount, id, owner));
    }

    @Operation(
            summary = "get owned account",
            description = "get all of the accounts belonging to a specific user"
    )
    @GetMapping("/user/{userId}")
    public List<Account> fetchAllAccounts(@PathVariable int userId) {
        return accountServiceImpl.fetchAllAccounts(userId);
    }

    @Operation(
            summary = "update account",
            description = "Update Account type and balance"
    )
    @PutMapping("/user/{userId}/{accountId}")
    public Account updateAccount(@PathVariable int userId, @PathVariable int accountId,
                                 @RequestBody Account account) throws Exception {
        return accountServiceImpl.updateAccount(userId, accountId, account);
    }

    @Operation(
            summary = "delete an account",
            description = "delete a desired account by account id"
    )
    @DeleteMapping("/user/{userId}/{accountId}")
    public void deleteAccount(@PathVariable int userId, @PathVariable int accountId) throws Exception {
        accountServiceImpl.deleteAccount(userId, accountId);
    }
}
