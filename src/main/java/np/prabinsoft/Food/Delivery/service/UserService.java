package np.prabinsoft.Food.Delivery.service;

import np.prabinsoft.Food.Delivery.io.UserRequest;
import np.prabinsoft.Food.Delivery.io.UserResponse;

public interface UserService {

    UserResponse registerUser(UserRequest request);

    String findByUserId();
}
