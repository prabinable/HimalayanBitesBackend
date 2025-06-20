package np.prabinsoft.Food.Delivery.service;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
    // cart me rakhe k lagi authenticated user hoi parlai
    Authentication getAuthentication();
}
