package np.prabinsoft.Food.Delivery.io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "food")
public class FoodRequest {
    private String name;
    private String description;
    private double price;
    private String category;

}
