package diet.dietgenerator.web.api.controllers;

import diet.dietgenerator.service.services.FoodService;
import diet.dietgenerator.web.api.models.FoodResponseModel;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FoodsApiController {

    private final FoodService foodService;
    private final ModelMapper modelMapper;
    private static int count = 0;

    public FoodsApiController(FoodService foodService, ModelMapper modelMapper) {
        this.foodService = foodService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api/foods")
    public ResponseEntity<List<FoodResponseModel>> getFoodsPage() {
        Pageable firstPageWithTwentyElements = PageRequest.of(count++, 20);
        List<FoodResponseModel> foods = foodService.getAllByFoodGroup("Fruits", firstPageWithTwentyElements)
                .stream()
                .map(f -> modelMapper.map(f, FoodResponseModel.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
