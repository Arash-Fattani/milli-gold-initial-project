package gold.milli.initialproject.mapper;

import gold.milli.initialproject.entity.Account;
import gold.milli.initialproject.entity.AccountDTOCreate;
import gold.milli.initialproject.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface AccountCreateMapper {
    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(source = "request.balance", target = "money")
    @Mapping(source = "request.accountType", target = "accountType")
    @Mapping(source = "owner", target = "owner")
    Account AccountCreateFromRequest (AccountDTOCreate request, User owner, String accountNumber);
    @Mapping(source = "money", target = "balance")
    @Mapping(source = "accountType", target = "accountType")
    AccountDTOCreate AccountDTOCreateFromAccount(Account bean);
}
