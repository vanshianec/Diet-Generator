package diet.dietgenerator.web.view.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RegisterUserViewModel {
    private String email;
    private String password;
    private String confirmPassword;
}
