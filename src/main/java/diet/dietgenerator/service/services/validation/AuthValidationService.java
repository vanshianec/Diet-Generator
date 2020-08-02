package diet.dietgenerator.service.services.validation;

import diet.dietgenerator.service.models.auth.RegisterUserServiceModel;

public interface AuthValidationService {
    boolean isValid(RegisterUserServiceModel user);
}
