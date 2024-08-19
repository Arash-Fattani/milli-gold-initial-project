package gold.milli.initialproject.repository;

import gold.milli.initialproject.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findAccountsByOwnerUserId(Integer id);
    boolean existsAccountByAccountNumber(String account);
}
