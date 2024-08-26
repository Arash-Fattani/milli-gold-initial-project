package gold.milli.initialproject.repository;

import gold.milli.initialproject.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Page<Account> findAccountsByOwnerId(Long id, Pageable pageable);
    boolean existsAccountByAccountNumber(String accountNumber);
}
