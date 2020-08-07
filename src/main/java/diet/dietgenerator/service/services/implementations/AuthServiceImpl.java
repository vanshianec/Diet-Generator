package diet.dietgenerator.service.services.implementations;

import diet.dietgenerator.data.models.Role;
import diet.dietgenerator.data.models.User;
import diet.dietgenerator.data.repositories.UserRepository;
import diet.dietgenerator.exceptions.UsernameTakenException;
import diet.dietgenerator.service.models.auth.LoginUserServiceModel;
import diet.dietgenerator.service.models.auth.RegisterUserServiceModel;
import diet.dietgenerator.service.services.AuthService;
import diet.dietgenerator.service.services.HashingService;
import diet.dietgenerator.service.services.validation.AuthValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final HashingService hashingService;

    public AuthServiceImpl(UserRepository userRepository, ModelMapper modelMapper, HashingService hashingService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.hashingService = hashingService;
    }

    @Override
    public void register(RegisterUserServiceModel model) {
        User user = modelMapper.map(model, User.class);
        user.setPassword(hashingService.hash(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("USER"));
        user.setRoles(roles);
        userRepository.save(user);
    }

}
