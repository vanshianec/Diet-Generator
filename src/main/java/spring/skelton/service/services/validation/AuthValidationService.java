package spring.skelton.service.services.validation;

import spring.skelton.service.models.auth.RegisterUserServiceModel;

public interface AuthValidationService {
    boolean isValid(RegisterUserServiceModel model);
}
