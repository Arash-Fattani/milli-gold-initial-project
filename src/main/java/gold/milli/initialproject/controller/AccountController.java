package gold.milli.initialproject.controller;

import gold.milli.initialproject.entity.Account;
import gold.milli.initialproject.service.AccountServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountServiceImpl accountServiceImpl;

    @Operation(
            summary = "create a new Account",
            description = "create a new account for a logged in account"
    )
    @PostMapping("/user/{accountId}/create-account")
    public Account createAccount(@RequestBody Account account, @PathVariable int accountId){
      return accountServiceImpl.createAccount(account, accountId);
    }
    @Operation(
            summary = "get owned account",
            description = "get all of the accounts belonging to a specific user"
    )
    @GetMapping("/user/{accountId}/accounts")
    public List<Account> fetchAllAccounts(@PathVariable int accountId){
        return accountServiceImpl.fetchAllAccounts(accountId);
    }
    @Operation(
            summary = "update account",
            description = "Update Account type and balance"
    )
    @PutMapping("/user/{userId}/account/{accountId}/update-account")
    public Account updateAccount(@PathVariable int userId, @PathVariable int accountId,
                                 @RequestBody Account account) throws Exception{
        return accountServiceImpl.updateAccount(userId, accountId, account);
    }
    @Operation(
            summary = "delete an account",
            description = "delete a desired account by account id"
    )
    @DeleteMapping("/user/{userId}/account/{id}/delete")
    public void deleteAccount(@PathVariable int userId, @PathVariable int id) throws Exception{
        accountServiceImpl.deleteAccount(userId, id);
    }
}
