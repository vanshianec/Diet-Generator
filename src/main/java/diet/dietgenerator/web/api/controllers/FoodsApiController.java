package diet.dietgenerator.web.api.controllers;

import diet.dietgenerator.service.models.food.CustomFoodServiceModel;
import diet.dietgenerator.service.models.food.FoodRequiredNutrientsServiceModel;
import diet.dietgenerator.service.models.food.base.BaseFoodServiceModel;
import diet.dietgenerator.service.services.FoodService;
import diet.dietgenerator.utils.linearsolver.FoodsLowestCostSolver;
import diet.dietgenerator.web.api.models.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/foods")
public class FoodsApiController {

    private final FoodService basicFoodService;
    private final FoodService customFoodService;
    private final ModelMapper modelMapper;

    public FoodsApiController(@Qualifier("BasicFood") FoodService basicFoodService,
                              @Qualifier("CustomFood") FoodService customFoodService,
                              ModelMapper modelMapper) {

        this.basicFoodService = basicFoodService;
        this.customFoodService = customFoodService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/load")
    public ResponseEntity<List<FoodTableViewResponseModel>> getFoodsTableView(@RequestParam(value = "foodCategory", required = false) String foodCategory,
                                                                              @RequestParam(value = "foodGroup", required = false) String foodGroup,
                                                                              @RequestParam(value = "additionalNutrient", defaultValue = "fiber") String additionalNutrient,
                                                                              @PageableDefault(page = 0, size = 20) Pageable pageable) {
        List<FoodTableViewResponseModel> foods;
        List<BaseFoodServiceModel> serviceModels;

        //TODO CHANGE foodCategory to foodType

        if (foodCategory.equals("basic")) {
            serviceModels = basicFoodService.getAllFoodsByFoodGroup(foodGroup, pageable);
        } else if (foodCategory.equals("custom")) {
            serviceModels = customFoodService.getAllFoodsByFoodGroup(foodGroup, pageable);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        foods = serviceModels.stream()
                .map(f -> {
                    FoodTableViewResponseModel model = modelMapper.map(f, FoodTableViewResponseModel.class);
                    model.setAdditionalNutrient((Float) ReflectionTestUtils.getField(f, additionalNutrient));
                    return model;
                }).collect(Collectors.toList());

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<FoodSearchViewResponseModel>> getFoodsBySearchWord(@RequestParam(value = "foodType") String foodType,
                                                                                  @RequestParam(value = "searchWord") String searchWord) {

        List<FoodSearchViewResponseModel> foods;
        List<BaseFoodServiceModel> serviceModels;

        if (foodType.equals("basic")) {
            serviceModels = basicFoodService.getAllFoodsByMatchingName(searchWord);
        } else if (foodType.equals("custom")) {
            serviceModels = customFoodService.getAllFoodsByMatchingName(searchWord);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        foods = serviceModels.stream()
                .map(f -> modelMapper.map(f, FoodSearchViewResponseModel.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/nutrition")
    public ResponseEntity<FoodNutrientsViewResponseModel> getFoodNutrients(@RequestParam(value = "foodId") Long id) {
        FoodNutrientsViewResponseModel food = modelMapper.map(basicFoodService.getFoodById(id), FoodNutrientsViewResponseModel.class);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @GetMapping("/custom")
    ResponseEntity<CustomFoodDynamicWeightViewResponseModel> getCustomFood(@RequestParam(value = "foodId") Long id,
                                                                           @RequestParam(value = "servingSize", required = false) Integer servingSize) {
        CustomFoodDynamicWeightViewResponseModel food = modelMapper.map(customFoodService.getFoodById(id), CustomFoodDynamicWeightViewResponseModel.class);
        if (servingSize != null) {
            food.setDynamicProductWeight(servingSize);
        }
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @GetMapping("/general-data")
    public ResponseEntity<CustomFoodGeneralViewResponseModel> getCustomFoodGeneralData(@RequestParam(value = "foodId") Long id) {
        BaseFoodServiceModel model = customFoodService.getFoodById(id);
        CustomFoodGeneralViewResponseModel food = modelMapper.map(model, CustomFoodGeneralViewResponseModel.class);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @GetMapping("/custom/all")
    public ResponseEntity<List<FoodSearchViewResponseModel>> getAllCustomFoods() {
        List<FoodSearchViewResponseModel> foods = customFoodService.getAllFoods(Pageable.unpaged())
                .stream()
                .map(f -> modelMapper.map(f, FoodSearchViewResponseModel.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @PostMapping("/generate")
    public ResponseEntity<List<CustomFoodDynamicWeightViewResponseModel>> generateDiet(@RequestBody FoodRequiredNutrientsRequestModel requiredNutrients) {
        List<CustomFoodServiceModel> serviceModels = customFoodService.getAllFoods(Pageable.unpaged())
                .stream()
                .map(f -> modelMapper.map(f, CustomFoodServiceModel.class))
                .collect(Collectors.toList());

        FoodsLowestCostSolver solver = new FoodsLowestCostSolver(modelMapper.map(requiredNutrients, FoodRequiredNutrientsServiceModel.class), serviceModels);

        //TODO catch no solution exception
        List<CustomFoodDynamicWeightViewResponseModel> foods = solver.getLowestCostFoods()
                .stream()
                .map(f -> modelMapper.map(f, CustomFoodDynamicWeightViewResponseModel.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
