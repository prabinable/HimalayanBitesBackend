package np.prabinsoft.Food.Delivery.service;

import lombok.AllArgsConstructor;
import np.prabinsoft.Food.Delivery.entities.Order;
import np.prabinsoft.Food.Delivery.io.OrderRequest;
import np.prabinsoft.Food.Delivery.io.OrderResponse;
import np.prabinsoft.Food.Delivery.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;

    @Override
    public OrderResponse createOrderWithPayment(OrderRequest request) {
        Order order = convertToEntity(request);
        order = orderRepository.save(order);
        return convertToResponse(order);
    }

    private Order convertToEntity(OrderRequest request) {
        return Order.builder()
                .userAddress(request.getUserAddress())
                .amount(request.getAmount())
                .orderItemList(request.getItems())
                .orderStatus(request.getOrderStatus())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }

    private OrderResponse convertToResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .amount(order.getAmount())
                .userAddress(order.getUserAddress())
                .userId(order.getUserId())
                .paymentStatus(order.getPaymentStatus())
                .orderStatus(order.getOrderStatus())
                .email(order.getEmail())
                .phoneNumber(order.getPhoneNumber())
                .build();
    }
}

