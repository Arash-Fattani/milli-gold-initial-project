package gold.milli.initialproject.entity;

import gold.milli.initialproject.entity.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @SequenceGenerator(name = "account_seq", allocationSize = 1)
    private int id;

    @NonNull
    @Column(unique = true)
    private String accountNumber;
    @NonNull
    private double balance;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @Enumerated(EnumType.STRING)
    AccountType accountType;

    public Account(@NonNull String accountNumber, AccountType accountType, User owner) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.owner = owner;
    }
}
