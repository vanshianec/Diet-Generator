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
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role extends BaseEntity{

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(String name){
        this.name = name;
        users = new HashSet<>();
    }

}
