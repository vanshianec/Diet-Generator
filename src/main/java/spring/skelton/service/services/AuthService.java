package spring.skelton.service.services;

import spring.skelton.service.models.auth.RegisterUserServiceModel;

public interface AuthService {
    void register(RegisterUserServiceModel model);
}
