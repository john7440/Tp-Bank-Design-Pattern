package exception;

public class ClientNotFoundException extends BusinessException {
    public ClientNotFoundException(String message) {
        super(message);
    }
}
