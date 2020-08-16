package diet.dietgenerator.service.services.validation.implementations;

import diet.dietgenerator.data.repositories.UserRepository;
import diet.dietgenerator.service.models.auth.RegisterUserServiceModel;
import diet.dietgenerator.service.services.validation.AuthValidationService;
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
