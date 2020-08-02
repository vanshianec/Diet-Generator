package diet.dietgenerator.data.models;

import diet.dietgenerator.data.models.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role extends BaseEntity implements GrantedAuthority {

    private String authority;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;
}
