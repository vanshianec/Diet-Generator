package diet.dietgenerator.web.view.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/foods")
public class FoodsController {

    @GetMapping("/all")
    public String getAllFoodsTable() {
        return "foods/foods.html";
    }
}
