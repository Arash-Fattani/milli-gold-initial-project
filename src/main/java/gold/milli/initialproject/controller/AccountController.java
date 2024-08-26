package gold.milli.initialproject.controller;

import gold.milli.initialproject.entity.Account;
import gold.milli.initialproject.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/{userId}/")
public class AccountController {
    private final AccountService accountService;

    @Operation(
            summary = "create a new Account",
            description = "create a new account for a logged in account"
    )
    @PostMapping("account")
    public Account createAccount(@RequestBody Account account, @PathVariable Integer userId) throws Exception{
      return accountService.createAccount(account, userId);
    }
    @Operation(
            summary = "get owned account",
            description = "get all of the accounts belonging to a specific user"
    )
    @GetMapping("accounts")
    public Page<Account> fetchAllAccounts(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "accountNumber") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @PathVariable Integer userId){
        return accountService.getUserAccounts(userId, page, size, sortBy, direction);
    }
    @Operation(
            summary = "update account",
            description = "Update Account type and balance"
    )
    @PutMapping("account/{accountId}")
    public Account updateAccount(@PathVariable Integer userId, @PathVariable Integer accountId,
                                 @RequestBody Account account) throws Exception{
        return accountService.updateAccount(userId, accountId, account);
    }
    @Operation(
            summary = "delete an account",
            description = "delete a desired account by account id"
    )
    @DeleteMapping("account/{accountId}")
    public void deleteAccount(@PathVariable Integer userId, @PathVariable Integer accountId) throws Exception{
        accountService.deleteAccount(userId, accountId);
    }
}
