package gold.milli.initialproject.controller;

import gold.milli.initialproject.entity.Account;
import gold.milli.initialproject.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AccountController {
    private final AccountService accountService;

    @Operation(
            summary = "create a new Account",
            description = "create a new account for a logged in account"
    )
    @PostMapping("/user/{userId}")
    public Account createAccount(@RequestBody Account account, @PathVariable int userId){
      return accountService.createAccount(account, userId);
    }
    @Operation(
            summary = "get owned account",
            description = "get all of the accounts belonging to a specific user"
    )
    @GetMapping("/user/{userId}")
    public Page<Account> fetchAllAccounts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "accountNumber") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @PathVariable int userId){
        return accountService.fetchAllAccounts(userId, page, size, sortBy, direction);
    }
    @Operation(
            summary = "update account",
            description = "Update Account type and balance"
    )
    @PutMapping("/user/{userId}/{accountId}")
    public Account updateAccount(@PathVariable int userId, @PathVariable int accountId,
                                 @RequestBody Account account) throws Exception{
        return accountService.updateAccount(userId, accountId, account);
    }
    @Operation(
            summary = "delete an account",
            description = "delete a desired account by account id"
    )
    @DeleteMapping("/user/{userId}/{accountId}")
    public void deleteAccount(@PathVariable int userId, @PathVariable int accountId) throws Exception{
        accountService.deleteAccount(userId, accountId);
    }
}
