package diet.dietgenerator.service.services.implementations;

import diet.dietgenerator.data.models.User;
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
        if(authValidationService.isValid(model)){
            //TODO show that username is taken or invalid;
            return;
        }

        User user = modelMapper.map(model, User.class);
        user.setPassword(hashingService.hash(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public LoginUserServiceModel login(RegisterUserServiceModel model) throws Exception {

        String username = model.getUsername();
        String hashedPassword = hashingService.hash(model.getPassword());
        User user = userRepository.findByUsernameAndPassword(username, hashedPassword);
        if(user == null){
            //TODO throw custom made exception;
            throw new Exception("Invalid user!");
        }

        return modelMapper.map(user, LoginUserServiceModel.class);
    }
}
