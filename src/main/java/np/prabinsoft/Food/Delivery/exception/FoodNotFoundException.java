package np.prabinsoft.Food.Delivery.exception;

public class FoodNotFoundException extends RuntimeException {
    public FoodNotFoundException(String msg) {
        super(msg);
    }
}
