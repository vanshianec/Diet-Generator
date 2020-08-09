package diet.dietgenerator.web.view.controllers;

import diet.dietgenerator.service.models.auth.RegisterUserServiceModel;
import diet.dietgenerator.service.services.AuthService;
import diet.dietgenerator.service.services.SecurityService;
import diet.dietgenerator.service.services.validation.AuthValidationService;
import diet.dietgenerator.web.view.controllers.base.BaseController;
import diet.dietgenerator.web.view.models.RegisterUserViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController extends BaseController {
    private final AuthService authService;
    private final SecurityService securityService;
    private final AuthValidationService authValidationService;
    private final ModelMapper mapper;

    public AuthController(AuthService authService, SecurityService securityService, AuthValidationService authValidationService, ModelMapper mapper) {
        this.authService = authService;
        this.securityService = securityService;
        this.authValidationService = authValidationService;
        this.mapper = mapper;
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {

        //TODO make logInMessage and logOutMessage
        if (error != null && isUserAnonymous())
            model.addAttribute("logErrorMessage", "Invalid username or password");

        if (logout != null && isUserAnonymous())
            model.addAttribute("logErrorMessage", "You have been logged out successfully");

        return "auth/login.html";
    }

    @GetMapping("/register")
    public ModelAndView getRegisterForm(ModelAndView modelAndView) {
        modelAndView.addObject("model", new RegisterUserViewModel());
        modelAndView.setViewName("auth/register.html");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute RegisterUserViewModel model, ModelAndView modelAndView) {
        RegisterUserServiceModel serviceModel = mapper.map(model, RegisterUserServiceModel.class);

        if (!authValidationService.isValid(serviceModel)) {
            modelAndView.addObject("errorMessage", "Email is taken");
            modelAndView.addObject("model", new RegisterUserViewModel());
            modelAndView.setViewName("auth/register.html");
        } else {
            authService.register(serviceModel);
            securityService.autoLogin(model.getEmail(), model.getPassword());
            modelAndView.setViewName("redirect:/home");
        }

        return modelAndView;
    }
}
