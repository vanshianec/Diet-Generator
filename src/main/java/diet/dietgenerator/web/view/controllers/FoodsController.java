package diet.dietgenerator.web.view.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FoodsController {

    @GetMapping("/foods")
    public String getFoodsTable() {
        return "foods/foods.html";
    }
}
