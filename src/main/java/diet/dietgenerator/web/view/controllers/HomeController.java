package diet.dietgenerator.web.view.controllers;

import diet.dietgenerator.service.services.AuthenticatedUserService;
import diet.dietgenerator.web.base.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    private final ModelMapper modelMapper;
    private final AuthenticatedUserService authenticatedUserService;

    public HomeController(ModelMapper modelMapper, AuthenticatedUserService authenticatedUserService) {
        this.modelMapper = modelMapper;
        this.authenticatedUserService = authenticatedUserService;
    }

    @GetMapping("/")
    public String getIndex() {
        return "home/index.html";
    }

    @GetMapping("/home")
    public ModelAndView getHome(ModelAndView modelAndView) {
        //TODO
        return modelAndView;
    }
}
