package np.prabinsoft.Food.Delivery.controller;

import lombok.AllArgsConstructor;
import np.prabinsoft.Food.Delivery.io.CartRequest;
import np.prabinsoft.Food.Delivery.io.CartResponse;
import np.prabinsoft.Food.Delivery.io.FoodResponse;
import np.prabinsoft.Food.Delivery.service.CartService;
import np.prabinsoft.Food.Delivery.service.FoodService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;
    private final FoodService foodService;

    @PostMapping
    public CartResponse addToCart(@RequestBody CartRequest request) {
        String foodId = request.getFoodId();
        if (foodId == null || foodId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "foodId is required and cannot be empty");
        }

        FoodResponse foodExists = foodService.getFood(foodId);
        if (foodExists == null || foodExists.getId() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food item with the given ID not found");
        }
        return cartService.addToCart(request);
    }

    @GetMapping
    public CartResponse getCart() {
        return cartService.getCart();
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearCart() {
        cartService.clearCart();
    }

    @PostMapping("/remove-cart")
    public CartResponse removeFromCart(@RequestBody CartRequest request) {
        String foodId = request.getFoodId();
        if (foodId == null || foodId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "foodId is required and cannot be empty");
        }
        FoodResponse foodExists = foodService.getFood(foodId);
        if (foodExists == null || foodExists.getId() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food item with the given ID not found");
        }
        return cartService.removeFromCart(request);
    }
}
