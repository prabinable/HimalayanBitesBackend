package np.prabinsoft.Food.Delivery.io;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderRequest {
    private List<OrderItem> items;
    private String userAddress;
    private double amount;
    private String email;
    private String phoneNumber;
    private String orderStatus;

}
