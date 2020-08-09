package diet.dietgenerator.service.services.implementations;

import diet.dietgenerator.data.models.Role;
import diet.dietgenerator.data.models.User;
import diet.dietgenerator.data.repositories.RoleRepository;
import diet.dietgenerator.data.repositories.UserRepository;
import diet.dietgenerator.service.models.auth.RegisterUserServiceModel;
import diet.dietgenerator.service.services.AuthService;
import diet.dietgenerator.service.services.HashingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final HashingService hashingService;

    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, HashingService hashingService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.hashingService = hashingService;
    }

    @Override
    @Transactional
    public void register(RegisterUserServiceModel model) {
        User user = modelMapper.map(model, User.class);
        user.setPassword(hashingService.hash(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(createRoleIfNotFound("ROLE_USER"));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Transactional
    Role createRoleIfNotFound(String roleName) {
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            role = roleRepository.save(new Role(roleName));
        }

        return role;
    }

}
