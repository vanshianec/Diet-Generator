package spring.skelton.web.base;

import spring.skelton.service.models.auth.LoginUserServiceModel;

import javax.servlet.http.HttpSession;

public class BaseController {

    protected String getUsername(HttpSession session) {
        return ((LoginUserServiceModel) session.getAttribute("user")).getUsername();
    }
}
