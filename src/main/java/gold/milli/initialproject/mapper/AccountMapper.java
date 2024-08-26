package gold.milli.initialproject.mapper;

import gold.milli.initialproject.entity.Account;
import gold.milli.initialproject.entity.AccountDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDto toAccountDto(Account account);
}
