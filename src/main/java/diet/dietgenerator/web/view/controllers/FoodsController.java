package diet.dietgenerator.web.view.controllers;

import diet.dietgenerator.service.services.FoodService;
import diet.dietgenerator.web.view.models.FoodViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FoodsController {

    private final FoodService foodService;
    private final ModelMapper modelMapper;

    public FoodsController(FoodService foodService, ModelMapper modelMapper) {
        this.foodService = foodService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/foods")
    public String getFoodsPage(Model model) {
        List<FoodViewModel> foods = foodService.getAll()
                .stream()
                .map(f -> modelMapper.map(f, FoodViewModel.class))
                .collect(Collectors.toList());
        model.addAttribute("foods", foods);
        return "foods/foods.html";
    }
}
