package np.prabinsoft.Food.Delivery.repository;

import np.prabinsoft.Food.Delivery.entities.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends MongoRepository<Food, String> {
}
