package diet.dietgenerator.service.services;

import diet.dietgenerator.service.models.auth.LoginUserServiceModel;
import diet.dietgenerator.service.models.auth.RegisterUserServiceModel;

public interface AuthService {
    void register(RegisterUserServiceModel model);
    LoginUserServiceModel login(RegisterUserServiceModel model) throws Exception;
}
