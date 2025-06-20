package np.prabinsoft.Food.Delivery.controller;

import lombok.AllArgsConstructor;
import np.prabinsoft.Food.Delivery.io.UserRequest;
import np.prabinsoft.Food.Delivery.io.UserResponse;
import np.prabinsoft.Food.Delivery.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerUser(@RequestBody UserRequest request) {
        return userService.registerUser(request);
    }
}
