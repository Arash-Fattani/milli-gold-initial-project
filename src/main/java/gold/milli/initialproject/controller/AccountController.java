package gold.milli.initialproject.controller;

import gold.milli.initialproject.entity.Account;
import gold.milli.initialproject.entity.AccountDto;
import gold.milli.initialproject.entity.CreateAccountRequestDto;
import gold.milli.initialproject.mapper.AccountMapper;
import gold.milli.initialproject.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/{userId}/")
public class AccountController {
    private final AccountService accountService;
    private final AccountMapper accountMapper;
    @Operation(
            summary = "create a new Account",
            description = "create a new account for a logged in account"
    )
    @PostMapping("account")
    public AccountDto createAccount(@RequestBody CreateAccountRequestDto account, @PathVariable Integer userId) throws Exception{
      return accountMapper.toAccountDto( accountService.createAccount(account, userId));
    }
    @Operation(
            summary = "get owned account",
            description = "get all of the accounts belonging to a specific user"
    )
    @GetMapping("accounts")
    public Page<AccountDto> fetchAllAccounts(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "balance") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @PathVariable Integer userId){
        Page<Account> accountPage =  accountService.getUserAccounts(userId, page, size, sortBy, direction);
        List<AccountDto> accountDtoList = accountPage.getContent().stream()
                .map(accountMapper::toAccountDto).collect(Collectors.toList());
        return new PageImpl<>(accountDtoList, accountPage.getPageable(), accountPage.getTotalElements());
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
