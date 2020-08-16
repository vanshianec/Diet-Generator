package spring.skelton.web.view.controllers;

import spring.skelton.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController extends AbstractErrorController {
    private static final String ERROR_PATH = "/error";

    @Autowired
    public CustomErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @ExceptionHandler(NotFoundException.class)
    public String notFound() {
        return "error/not-found.html";
    }

    /**
     * Responsible for handling all errors and throw especial exceptions
     * for some HTTP status codes. Otherwise, it will return a map that
     * ultimately will be converted to a json error.
     */

    @RequestMapping(ERROR_PATH)
    public ResponseEntity<?> handleErrors(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        if (status.equals(HttpStatus.NOT_FOUND)) {
            throw new NotFoundException();
        }

        return ResponseEntity.status(status).body(getErrorAttributes(request, false));
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}