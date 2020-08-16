package spring.skelton.service.services.implementations;

import spring.skelton.data.models.Role;
import spring.skelton.data.models.User;
import spring.skelton.data.repositories.RoleRepository;
import spring.skelton.data.repositories.UserRepository;
import spring.skelton.service.models.auth.RegisterUserServiceModel;
import spring.skelton.service.services.AuthService;
import spring.skelton.service.services.HashingService;
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
