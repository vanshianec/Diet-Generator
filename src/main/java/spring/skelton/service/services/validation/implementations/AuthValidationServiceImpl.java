package spring.skelton.service.services.validation.implementations;

import spring.skelton.data.repositories.UserRepository;
import spring.skelton.service.models.auth.RegisterUserServiceModel;
import spring.skelton.service.services.validation.AuthValidationService;
import org.springframework.stereotype.Service;

@Service
public class AuthValidationServiceImpl implements AuthValidationService {

    private final UserRepository userRepository;

    public AuthValidationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(RegisterUserServiceModel model) {
        return isUsernameFree(model.getUsername());
    }

    private boolean isUsernameFree(String username) {
        return !userRepository.existsByUsername(username);
    }
}
