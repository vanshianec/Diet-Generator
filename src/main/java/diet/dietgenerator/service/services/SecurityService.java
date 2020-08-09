package diet.dietgenerator.service.services;

public interface SecurityService {
    String findLoggedInUsername();
    void autoLogin(String email, String password);
}
