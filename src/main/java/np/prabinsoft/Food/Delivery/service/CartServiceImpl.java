package np.prabinsoft.Food.Delivery.service;

import lombok.AllArgsConstructor;
import np.prabinsoft.Food.Delivery.entities.Cart;
import np.prabinsoft.Food.Delivery.io.CartRequest;
import np.prabinsoft.Food.Delivery.io.CartResponse;
import np.prabinsoft.Food.Delivery.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserService userService;

    @Override
    public CartResponse addToCart(CartRequest request) {
        String loggedInUserId = userService.findByUserId();
        Optional<Cart> cartOptional = cartRepository.findByUserId(loggedInUserId);
        Cart cart = cartOptional.orElseGet(() -> new Cart(loggedInUserId, new HashMap<>()));
        Map<String, Integer> items = cart.getItems();
        items.put(request.getFoodId(), cart.getItems().getOrDefault(request.getFoodId(), 0) + 1);
        cart.setItems(items);
        Cart save = cartRepository.save(cart);
        return convertToReponse(save);
    }

    @Override
    public CartResponse getCart() {
        String loggedInUserId = userService.findByUserId();
        Cart cart = cartRepository.findByUserId(loggedInUserId)
                .orElse(new Cart(null, loggedInUserId, new HashMap<>()));
        return convertToReponse(cart);
    }

    @Override
    public void clearCart() {
        String loggedInUserId = userService.findByUserId();
        cartRepository.deleteByUserId(loggedInUserId);
    }

    @Override
    public CartResponse removeFromCart(CartRequest request) {
        String loggedInUserId = userService.findByUserId();

        Cart cart = cartRepository.findByUserId(loggedInUserId).orElseThrow(()
                -> new RuntimeException("Cart is not present"));

        Map<String, Integer> cartItems = cart.getItems();

        if (cartItems.containsKey(request.getFoodId())) {
            Integer currentQty = cartItems.get(request.getFoodId());
            if (currentQty > 1) {
                cartItems.put(request.getFoodId(), currentQty - 1);
            } else {
                cartItems.remove(request.getFoodId());
            }
            cart = cartRepository.save(cart);
            return convertToReponse(cart);
        }


        return null;
    }

    private CartResponse convertToReponse(Cart cart) {
        return CartResponse.builder()
                .id(cart.getId())
                .userId(cart.getUserId())
                .items(cart.getItems())
                .build();
    }
}
