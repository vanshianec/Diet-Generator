package spring.skelton.web.view.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RegisterUserViewModel {
    private String username;
    private String password;
    private String confirmPassword;
}
