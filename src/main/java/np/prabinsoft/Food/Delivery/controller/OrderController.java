package np.prabinsoft.Food.Delivery.controller;

import lombok.AllArgsConstructor;
import np.prabinsoft.Food.Delivery.io.OrderRequest;
import np.prabinsoft.Food.Delivery.io.OrderResponse;
import np.prabinsoft.Food.Delivery.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public OrderResponse createOrderWithPayment(@RequestBody OrderRequest request) {
        return orderService.createOrderWithPayment(request);
    }
}
