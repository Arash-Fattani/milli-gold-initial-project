package gold.milli.initialproject.entity;

import gold.milli.initialproject.entity.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Entity(name = "users")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
//TODO: when the application's data increases use a higher value for allocation size to increase performance
public class User {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", allocationSize = 1)
    @Id
    @Column(name = "user_id")
    private Integer id;
    @Column(unique = true, name = "username")
    private String username;
    @Column(name = "hashed_password")
    private String password;

    @Column(unique = true, name = "email")
    private String email;
    @Singular
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    private List<Role> roles;

    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private List<Account> accounts;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                ", accounts=" + accounts +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public String getPassword() {
        return password;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }
}
