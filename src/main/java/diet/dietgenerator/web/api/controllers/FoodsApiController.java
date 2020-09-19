package diet.dietgenerator.web.api.controllers;

import diet.dietgenerator.service.services.FoodService;
import diet.dietgenerator.web.api.models.BasicFoodNutrientsViewResponseModel;
import diet.dietgenerator.web.api.models.CustomFoodGeneralViewResponseModel;
import diet.dietgenerator.web.api.models.FoodSearchViewResponseModel;
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


    @GetMapping("/load")
    public ResponseEntity<List<FoodTableViewResponseModel>> getFoodsTableView(@RequestParam(value = "foodCategory", required = false) String foodCategory,
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

    @GetMapping("/search")
    public ResponseEntity<List<FoodSearchViewResponseModel>> getFoodsBySearchWord(@RequestParam(value = "foodType") String foodType,
                                                                                  @RequestParam(value = "searchWord") String searchWord) {

        List<FoodSearchViewResponseModel> foods = null;

        if (foodType.equals("basic")) {
            foods = foodService.getAllBasicFoodsByMatchingName(searchWord)
                    .stream()
                    .map(f -> modelMapper.map(f, FoodSearchViewResponseModel.class))
                    .collect(Collectors.toList());
        } else if (foodType.equals("custom")) {
            foods = foodService.getAllCustomFoodsByMatchingName(searchWord)
                    .stream()
                    .map(f -> modelMapper.map(f, FoodSearchViewResponseModel.class))
                    .collect(Collectors.toList());
        }

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/nutrition")
    public ResponseEntity<BasicFoodNutrientsViewResponseModel> getFoodNutrients(@RequestParam(value = "foodName") String foodName) {
        BasicFoodNutrientsViewResponseModel food = modelMapper.map(foodService.getBasicFoodByName(foodName), BasicFoodNutrientsViewResponseModel.class);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @GetMapping("/general-data")
    public ResponseEntity<CustomFoodGeneralViewResponseModel> getCustomFoodGeneralData(@RequestParam(value = "foodName") String foodName) {
        CustomFoodGeneralViewResponseModel food = modelMapper.map(foodService.getCustomFoodByName(foodName), CustomFoodGeneralViewResponseModel.class);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @GetMapping("/custom/all")
    public ResponseEntity<List<FoodSearchViewResponseModel>> getAllCustomFoods() {
        List<FoodSearchViewResponseModel> foods = foodService.getAllCustomFoods(Pageable.unpaged())
                .stream()
                .map(f -> modelMapper.map(f, FoodSearchViewResponseModel.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
