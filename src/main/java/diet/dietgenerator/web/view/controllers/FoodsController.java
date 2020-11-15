package diet.dietgenerator.web.view.controllers;

import diet.dietgenerator.service.models.food.CustomFoodServiceModel;
import diet.dietgenerator.service.services.FoodService;
import diet.dietgenerator.web.view.models.CustomFoodViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/foods")
public class FoodsController {

    private final ModelMapper modelMapper;
    private final FoodService customFoodService;

    public FoodsController(ModelMapper modelMapper, @Qualifier("CustomFood") FoodService customFoodService) {
        this.modelMapper = modelMapper;
        this.customFoodService = customFoodService;
    }

    @GetMapping("/all")
    public String getAllFoodsTable() {
        return "foods/foods.html";
    }

    @PostMapping("/create")
    public ModelAndView createFood(@ModelAttribute CustomFoodViewModel model, ModelAndView modelAndView) {
        CustomFoodServiceModel serviceModel = modelMapper.map(model, CustomFoodServiceModel.class);
        customFoodService.createFood(serviceModel);
        modelAndView.setViewName("redirect:/foods/all");
        return modelAndView;
    }
}
