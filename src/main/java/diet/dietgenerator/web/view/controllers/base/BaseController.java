package diet.dietgenerator.web.view.controllers.base;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseController {

    protected boolean isUserAnonymous() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (auth instanceof AnonymousAuthenticationToken);
    }
}
