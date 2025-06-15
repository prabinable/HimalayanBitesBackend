package np.prabinsoft.Food.Delivery.service;

import np.prabinsoft.Food.Delivery.io.FoodRequest;
import np.prabinsoft.Food.Delivery.io.FoodResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FoodService {

    String uploadFile(MultipartFile file);

    FoodResponse addFood(FoodRequest foodRequest, MultipartFile file);

}
