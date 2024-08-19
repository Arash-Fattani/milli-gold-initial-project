package gold.milli.initialproject.entity;

import gold.milli.initialproject.entity.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class AccountDTOCreate {
    private Double balance;
    private AccountType accountType;

}
