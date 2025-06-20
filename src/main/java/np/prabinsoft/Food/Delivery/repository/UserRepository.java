package np.prabinsoft.Food.Delivery.repository;

import np.prabinsoft.Food.Delivery.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
   Optional<User> findByEmail(String email);

}
