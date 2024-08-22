package gold.milli.initialproject.entity;

import gold.milli.initialproject.entity.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Users")
@EqualsAndHashCode(of = "userId")
//TODO: when the application's data increases use a higher value for allocation size to increase performance
public class User {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name= "user_seq", sequenceName = "user_sequence", allocationSize = 1)
    @Id
    private int userId;

    @NonNull
    @Column(unique = true)
    private String username;

    @NonNull
    private String password;

    @NonNull
    @Column(unique = true)
    private String email;
    @NonNull
    @Singular
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    private List<Role> roles;

    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private List<Account>accounts;


    public void addAccount(Account account){
        this.accounts.add(account);
    }
}
