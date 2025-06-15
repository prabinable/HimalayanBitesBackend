package np.prabinsoft.Food.Delivery.service;

import np.prabinsoft.Food.Delivery.entities.Food;
import np.prabinsoft.Food.Delivery.io.FoodRequest;
import np.prabinsoft.Food.Delivery.io.FoodResponse;
import np.prabinsoft.Food.Delivery.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.util.UUID;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private S3Client s3Client;

    @Value("${aws.s3.bucketname}")
    private String bucketName;

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public String uploadFile(MultipartFile file) {
        String fileNameExtension = file.getOriginalFilename().substring(file.getOriginalFilename()
                .lastIndexOf(".") + 1);
        String key = UUID.randomUUID().toString() + "." + fileNameExtension;

        try {
            PutObjectRequest por = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .acl("public-read")
                    .contentType(file.getContentType())
                    .build();

            PutObjectResponse response = s3Client.putObject(por, RequestBody.fromBytes(file.getBytes()));

            if (response.sdkHttpResponse().isSuccessful()) {
                return "https://" + bucketName + ".s3.amazonaws.com/" + key;
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File upload failed");
            }
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while uploading the file");
        }
    }

    @Override
    public FoodResponse addFood(FoodRequest foodRequest, MultipartFile file) {
        Food food = convertToEntity(foodRequest);
        String imageUrl = uploadFile(file);
        food.setImageUrl(imageUrl);
        food = foodRepository.save(food);
        return convertToReponse(food);
    }

    private Food convertToEntity(FoodRequest request) {
        return Food.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .price(request.getPrice())
                .build();
    }

    private FoodResponse convertToReponse(Food request) {
        return FoodResponse.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .price(request.getPrice())
                .imageUrl(request.getImageUrl())
                .build();
    }
}
