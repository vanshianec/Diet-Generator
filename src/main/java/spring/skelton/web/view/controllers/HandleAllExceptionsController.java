package spring.skelton.web.view.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class HandleAllExceptionsController {

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleException(Throwable exception) {
        ModelAndView modelAndView = new ModelAndView("error/error.html");
        modelAndView.addObject("exceptionMessage", exception.getMessage());
        return modelAndView;
    }
}
