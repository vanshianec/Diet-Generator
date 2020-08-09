package diet.dietgenerator.web.filters;

import diet.dietgenerator.service.services.AuthenticatedUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final AuthenticatedUserService authenticatedUserService;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    public UserAuthenticationSuccessHandler(AuthenticatedUserService authenticatedUserService) {
        super();
        this.authenticatedUserService = authenticatedUserService;
    }

    /* Used each time a user logs in */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String username = authenticatedUserService.getUsername();
        //TODO all kind of information in the user profile will be loaded from here
        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/home");
    }
}
