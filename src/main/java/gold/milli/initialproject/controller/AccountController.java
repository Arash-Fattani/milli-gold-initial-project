package gold.milli.initialproject.controller;

import gold.milli.initialproject.entity.Account;
import gold.milli.initialproject.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @Operation(
            summary = "create a new Account",
            description = "create a new account for a logged in account"
    )
    @PostMapping("/user/{id}/create-account")
    public Account createAccount(@RequestBody Account account, @PathVariable int id){
      return accountService.createAccount(account, id);
    }
    @Operation(
            summary = "get owned account",
            description = "get all of the accounts belonging to a specific user"
    )
    @GetMapping("/user/{id}/accounts")
    public List<Account> fetchAllAccounts(@PathVariable int id){
        return accountService.fetchAllAccounts(id);
    }
    @Operation(
            summary = "update account",
            description = "Update Account type and balance"
    )
    @PutMapping("/user/{uId}/account/{id}/update-account")
    public Account updateAccount(@PathVariable int uId, @PathVariable int id, @RequestBody Account account){
        return accountService.updateAccount(uId, id, account);
    }
    @Operation(
            summary = "delete an account",
            description = "delete a desired account by account id"
    )
    @DeleteMapping("/user/{uId}/account/{id}/delete")
    public void deleteAccount(@PathVariable int uId, @PathVariable int id){
        accountService.deleteAccount(uId, id);
    }
}
