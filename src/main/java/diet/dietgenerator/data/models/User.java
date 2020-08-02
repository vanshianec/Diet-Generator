package diet.dietgenerator.data.models;

import diet.dietgenerator.data.models.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany
    private Set<Role> roles;

    /* should be loaded with EAGER to keep up with the session */
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> authorities;

    public User() {
        authorities = new HashSet<>();
    }
}
