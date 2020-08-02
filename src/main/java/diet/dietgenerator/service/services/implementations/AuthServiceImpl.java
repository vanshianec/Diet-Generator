package diet.dietgenerator.service.services.implementations;

import diet.dietgenerator.data.repositories.UserRepository;
import diet.dietgenerator.service.models.auth.LoginUserServiceModel;
import diet.dietgenerator.service.models.auth.RegisterUserServiceModel;
import diet.dietgenerator.service.services.AuthService;
import diet.dietgenerator.service.services.HashingService;
import diet.dietgenerator.service.services.validation.AuthValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthValidationService authValidationService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final HashingService hashingService;

    public AuthServiceImpl(AuthValidationService authValidationService, UserRepository userRepository, ModelMapper modelMapper, HashingService hashingService) {
        this.authValidationService = authValidationService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.hashingService = hashingService;
    }

    @Override
    public void register(RegisterUserServiceModel model) {
        //TODO
    }

    @Override
    public LoginUserServiceModel login(RegisterUserServiceModel serviceModel) {
        //TODO
        return null;
    }
}
