package diet.dietgenerator.service.models.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RegisterUserServiceModel {
    private String email;
    private String password;
    private String confirmPassword;

}
