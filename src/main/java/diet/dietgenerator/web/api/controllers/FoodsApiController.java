package diet.dietgenerator.web.api.controllers;

import diet.dietgenerator.service.models.food.BasicFoodServiceModel;
import diet.dietgenerator.service.models.food.CustomFoodServiceModel;
import diet.dietgenerator.service.models.food.base.BaseFoodServiceModel;
import diet.dietgenerator.service.services.FoodService;
import diet.dietgenerator.web.api.models.BasicFoodResponseModel;
import diet.dietgenerator.web.api.models.CustomFoodResponseModel;
import diet.dietgenerator.web.api.models.base.BaseFoodResponseModel;
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
    public <T extends BaseFoodResponseModel> ResponseEntity<List<T>> getFoodsPage(@RequestParam(value = "foodCategory", required = false) String foodCategory,
                                                                                  @RequestParam(value = "foodGroup", required = false) String foodGroup,
                                                                                  @RequestParam(value = "additionalNutrient", defaultValue = "fiber") String additionalNutrient,
                                                                                  @PageableDefault(page = 0, size = 20) Pageable pageable) {

        List<T> foods;

        if (foodCategory.equals("basic")) {
            foods = getBasicFoods(foodGroup, pageable, additionalNutrient);
        } else if (foodCategory.equals("custom")) {

        }

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    private <T extends BaseFoodResponseModel> List<T> getBasicFoods(String foodGroup, Pageable pageable, String additionalNutrient) {

        List<BasicFoodServiceModel> serviceModels;

        if (foodGroup == null) {
            serviceModels = foodService.getAllBasicFoods(pageable);
        } else {
            serviceModels = foodService.getAllBasicFoodsByFoodGroup(foodGroup, pageable);
        }

        return serviceModels.stream()
                .map(f -> {
                    BasicFoodResponseModel model = modelMapper.map(f, BasicFoodResponseModel.class);
                    model.setAdditionalNutrient((Float) ReflectionTestUtils.getField(f, additionalNutrient));
                    return model;
                })
                .collect(Collectors.toList());
    }

    private List<CustomFoodResponseModel> getCustomFoods(String foodGroup, Pageable pageable, String additionalNutrient) {

        List<CustomFoodServiceModel> serviceModels;

        if (foodGroup == null) {
            serviceModels =  foodService.getAllCustomFoods(pageable);
        }
        else{
            serviceModels= foodService.getAllCustomFoodByFoodGroup(foodGroup, pageable);
        }

        return serviceModels.stream()
                .map(f -> {
                    CustomFoodResponseModel model = modelMapper.map(f, CustomFoodResponseModel.class);
                    model.setAdditionalNutrient((Float) ReflectionTestUtils.getField(f, additionalNutrient));
                    return model;
                })
                .collect(Collectors.toList());
    }
}
