package diet.dietgenerator.service.services.implementations;

import diet.dietgenerator.data.models.Role;
import diet.dietgenerator.data.models.User;
import diet.dietgenerator.data.repositories.UserRepository;
import diet.dietgenerator.service.services.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/* Used to retrieve user's authentication and authorization information */

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            /* Note: This exception will not be displayed on the client side */
            throw new UsernameNotFoundException("User not found.");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                getAuthorities(user.getRoles())
        );
    }

    private List<SimpleGrantedAuthority> getAuthorities(Set<Role> roles){
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }
}
