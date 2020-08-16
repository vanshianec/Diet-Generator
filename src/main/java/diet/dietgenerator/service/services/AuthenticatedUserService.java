package diet.dietgenerator.service.services;

import java.util.List;

public interface AuthenticatedUserService {
    String getUsername();
    List<String> getRoles();
}
