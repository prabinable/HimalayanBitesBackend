package np.prabinsoft.Food.Delivery.service;

import lombok.AllArgsConstructor;
import np.prabinsoft.Food.Delivery.entities.User;
import np.prabinsoft.Food.Delivery.io.UserRequest;
import np.prabinsoft.Food.Delivery.io.UserResponse;
import np.prabinsoft.Food.Delivery.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationFacade authenticationFacade;

    @Override
    public UserResponse registerUser(UserRequest request) {
        User user = convertToEntity(request);
        user = userRepository.save(user);
        return convertToReponse(user);
    }

    @Override
    public String findByUserId() {
        String loggedInUserEmail = authenticationFacade.getAuthentication().getName();
        User loggedInUser = userRepository.findByEmail(loggedInUserEmail).orElseThrow(() -> new UsernameNotFoundException("User not found "));
        return loggedInUser.getId();
    }


    private User convertToEntity(UserRequest request) {
        return User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
    }

    private UserResponse convertToReponse(User request) {
        return UserResponse.builder()
                .id(request.getId())
                .email(request.getEmail())
                .name(request.getName())
                .build();
    }
}


