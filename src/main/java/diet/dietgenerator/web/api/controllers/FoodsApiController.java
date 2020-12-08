package diet.dietgenerator.web.api.controllers;

import diet.dietgenerator.service.models.food.CustomFoodServiceModel;
import diet.dietgenerator.service.models.food.FoodNutrientsServiceModel;
import diet.dietgenerator.service.models.food.base.BaseFoodServiceModel;
import diet.dietgenerator.service.services.FoodService;
import diet.dietgenerator.utils.linearsolver.FoodsLowestCostSolver;
import diet.dietgenerator.web.api.models.*;
import org.modelmapper.ModelMapper;
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

    private final FoodService foodService;
    private final ModelMapper modelMapper;

    public FoodsApiController(FoodService foodService, ModelMapper modelMapper) {
        this.foodService = foodService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/load")
    public ResponseEntity<List<FoodTableViewModel>> getFoodsTableView(@RequestParam(value = "foodCategory", required = false) String foodCategory,
                                                                      @RequestParam(value = "foodGroup", required = false) String foodGroup,
                                                                      @RequestParam(value = "additionalNutrient", defaultValue = "fiber") String additionalNutrient,
                                                                      @PageableDefault(page = 0, size = 20) Pageable pageable) {
        List<FoodTableViewModel> foods;
        List<? extends BaseFoodServiceModel> serviceModels;

        //TODO CHANGE foodCategory to foodType

        if (foodCategory.equals("basic")) {
            serviceModels = foodService.getAllBasicFoodsByFoodGroup(foodGroup, pageable);
        } else if (foodCategory.equals("custom")) {
            serviceModels = foodService.getAllCustomFoodsByFoodGroup(foodGroup, pageable);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        foods = serviceModels.stream()
                .map(f -> {
                    FoodTableViewModel model = modelMapper.map(f, FoodTableViewModel.class);
                    model.setAdditionalNutrient((Float) ReflectionTestUtils.getField(f, additionalNutrient));
                    return model;
                }).collect(Collectors.toList());

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<FoodSearchModel>> getFoodsBySearchWord(@RequestParam(value = "foodType") String foodType,
                                                                      @RequestParam(value = "searchWord") String searchWord) {

        List<FoodSearchModel> foods;
        List<? extends BaseFoodServiceModel> serviceModels;

        if (foodType.equals("basic")) {
            serviceModels = foodService.getAllBasicFoodsByMatchingName(searchWord);
        } else if (foodType.equals("custom")) {
            serviceModels = foodService.getAllCustomFoodsByMatchingName(searchWord);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        foods = serviceModels.stream()
                .map(f -> modelMapper.map(f, FoodSearchModel.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/nutrition")
    public ResponseEntity<FoodNutrientsModel> getFoodNutrients(@RequestParam(value = "foodId") Long id) {
        FoodNutrientsModel food = modelMapper.map(foodService.getBasicFoodById(id), FoodNutrientsModel.class);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @GetMapping("/custom")
    ResponseEntity<CustomFoodDynamicWeightModel> getCustomFood(@RequestParam(value = "foodId") Long id,
                                                               @RequestParam(value = "servingSize", required = false) Integer servingSize) {
        CustomFoodDynamicWeightModel food = modelMapper.map(foodService.getCustomFoodById(id), CustomFoodDynamicWeightModel.class);
        if (servingSize != null) {
            food.setDynamicProductWeight(servingSize);
        }
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @GetMapping("/general-data")
    public ResponseEntity<CustomFoodGeneralModel> getCustomFoodGeneralData(@RequestParam(value = "foodId") Long id) {
        CustomFoodServiceModel model = foodService.getCustomFoodById(id);
        CustomFoodGeneralModel food = modelMapper.map(model, CustomFoodGeneralModel.class);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @GetMapping("/custom/all")
    public ResponseEntity<List<FoodSearchModel>> getAllCustomFoods() {
        List<FoodSearchModel> foods = foodService.getAllCustomFoods(Pageable.unpaged())
                .stream()
                .map(f -> modelMapper.map(f, FoodSearchModel.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @PostMapping("/generate")
    public ResponseEntity<List<CustomFoodDynamicWeightModel>> generateDiet(@RequestBody List<FoodNutrientsModel> requiredNutrients) {
        List<CustomFoodServiceModel> serviceModels = foodService.getAllCustomFoods(Pageable.unpaged())
                .stream()
                .map(f -> modelMapper.map(f, CustomFoodServiceModel.class))
                .collect(Collectors.toList());

        FoodNutrientsServiceModel goalNutrients = modelMapper.map(requiredNutrients.get(0), FoodNutrientsServiceModel.class);
        FoodNutrientsServiceModel maxNutrients = modelMapper.map(requiredNutrients.get(1), FoodNutrientsServiceModel.class);
        FoodsLowestCostSolver solver = new FoodsLowestCostSolver(goalNutrients, maxNutrients, serviceModels);

        //TODO catch no solution exception
        List<CustomFoodDynamicWeightModel> foods = solver.getLowestCostFoods()
                .stream()
                .map(f -> modelMapper.map(f, CustomFoodDynamicWeightModel.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
