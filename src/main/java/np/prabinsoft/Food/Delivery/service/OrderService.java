package np.prabinsoft.Food.Delivery.service;

import np.prabinsoft.Food.Delivery.io.OrderRequest;
import np.prabinsoft.Food.Delivery.io.OrderResponse;

public interface OrderService {
   OrderResponse createOrderWithPayment(OrderRequest request);
}
