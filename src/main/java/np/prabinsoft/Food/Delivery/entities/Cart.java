package np.prabinsoft.Food.Delivery.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "carts")
public class Cart {
    @Id
    private String id;
    private String userId;
    private Map<String, Integer> items = new HashMap<>();

    public Cart(String userId, Map<String, Integer> items) {
        this.userId = userId;
        this.items = items;
    }
}
