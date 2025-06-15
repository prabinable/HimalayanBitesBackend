package np.prabinsoft.Food.Delivery.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import np.prabinsoft.Food.Delivery.io.FoodRequest;
import np.prabinsoft.Food.Delivery.io.FoodResponse;
import np.prabinsoft.Food.Delivery.service.FoodService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@AllArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @PostMapping
    public FoodResponse addFood(@RequestPart("food") String foodString, @RequestPart("file") MultipartFile file) {
        ObjectMapper objectMapper = new ObjectMapper();
        FoodRequest request = null;
        try {
            request = objectMapper.readValue(foodString, FoodRequest.class);
        } catch (JsonProcessingException e) {
//            return ResponseEntity.badRequest().build();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Json format");
        }
        FoodResponse foodResponse = foodService.addFood(request, file);
        return foodResponse;
    }

    @GetMapping
    public List<FoodResponse> getAllFood() {
        return foodService.getAllFood();
    }

    @GetMapping("/{id}")
    public FoodResponse getFoodById(@PathVariable String id) {
        return foodService.getFood(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFoodById(@PathVariable String id) {
        foodService.deleteFood(id);
    }
}
