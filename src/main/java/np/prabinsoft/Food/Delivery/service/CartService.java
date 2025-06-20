package np.prabinsoft.Food.Delivery.service;

import np.prabinsoft.Food.Delivery.io.CartRequest;
import np.prabinsoft.Food.Delivery.io.CartResponse;

public interface CartService {

    CartResponse addToCart(CartRequest request);

    CartResponse getCart();

    void clearCart();

   CartResponse removeFromCart(CartRequest request);
}
