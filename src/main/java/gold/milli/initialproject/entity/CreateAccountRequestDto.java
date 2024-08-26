package gold.milli.initialproject.entity;

import gold.milli.initialproject.entity.enums.AccountType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CreateAccountRequestDto {
    @NotNull(message = "balance cannot be null")
    @DecimalMin(value = "50000.00", message = "you should have 50 thousand Tomans to open an account")
    @Digits(integer = 13, fraction = 4, message = "balance can be up to one billion Tomans with up to 4 decimal points")
    private BigDecimal balance;
    @NotNull(message = "you must specify an account type")
    private AccountType accountType;
}
