package gold.milli.initialproject.entity;

import gold.milli.initialproject.entity.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private String accountNumber;
    private BigDecimal balance;
    private AccountType accountType;
}
