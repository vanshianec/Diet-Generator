package diet.dietgenerator.web.api.controllers;

import diet.dietgenerator.service.services.FoodService;
import diet.dietgenerator.web.api.models.FoodTableViewResponseModel;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/foods")
public class FoodsApiController {

    private final FoodService foodService;
    private final ModelMapper modelMapper;

    public FoodsApiController(FoodService foodService, ModelMapper modelMapper) {
        this.foodService = foodService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public  ResponseEntity<List<FoodTableViewResponseModel>> getFoodsTableView(@RequestParam(value = "foodCategory", required = false) String foodCategory,
                                                                                                              @RequestParam(value = "foodGroup", required = false) String foodGroup,
                                                                                                              @RequestParam(value = "additionalNutrient", defaultValue = "fiber") String additionalNutrient,

                                                                                                              @PageableDefault(page = 0, size = 20) Pageable pageable) {
        List<FoodTableViewResponseModel> foods = null;

        if (foodCategory.equals("basic")) {
            foods = foodService.getAllBasicFoodsByFoodGroup(foodGroup, pageable)
                .stream()
                .map(f -> {
                    FoodTableViewResponseModel model = modelMapper.map(f, FoodTableViewResponseModel.class);
                    model.setAdditionalNutrient((Float) ReflectionTestUtils.getField(f, additionalNutrient));
                    return model;
                }).collect(Collectors.toList());
        } else if (foodCategory.equals("custom")) {
            foods = foodService.getAllCustomFoodsByFoodGroup(foodGroup, pageable)
                    .stream()
                    .map(f -> {
                        FoodTableViewResponseModel model = modelMapper.map(f, FoodTableViewResponseModel.class);
                        model.setAdditionalNutrient((Float) ReflectionTestUtils.getField(f, additionalNutrient));
                        return model;
                    }).collect(Collectors.toList());
        }

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
