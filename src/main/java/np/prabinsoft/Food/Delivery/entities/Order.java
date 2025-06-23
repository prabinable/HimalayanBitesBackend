package np.prabinsoft.Food.Delivery.entities;

import lombok.Builder;
import lombok.Data;
import np.prabinsoft.Food.Delivery.io.OrderItem;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String userId;
    private String userAddress;
    private String phoneNumber;
    private String email;

    private List<OrderItem> orderItemList;
    private double amount;

    private String paymentStatus;
    private String razorpayOrderId;
    private String razorpaySignature;

    private String orderStatus;
}
