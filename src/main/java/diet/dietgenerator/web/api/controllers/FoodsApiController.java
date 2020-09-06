package diet.dietgenerator.web.api.controllers;

import diet.dietgenerator.service.services.FoodService;
import diet.dietgenerator.web.api.models.FoodResponseModel;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<List<FoodResponseModel>> getFoodsPage(@RequestParam(value = "foodGroup", required = false) String foodGroup, @PageableDefault(page = 0, size = 20) Pageable pageable) {

        List<FoodResponseModel> foods = foodService.getAllByFoodGroup("Fruits", pageable)
                .stream()
                .map(f -> modelMapper.map(f, FoodResponseModel.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
