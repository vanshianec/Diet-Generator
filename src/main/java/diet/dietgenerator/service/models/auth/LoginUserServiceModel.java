package diet.dietgenerator.service.models.auth;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginUserServiceModel {
    private String username;
    private String password;
}
