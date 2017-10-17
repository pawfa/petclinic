package hello.security.validation;

public class UserExistsException extends Exception {

    public UserExistsException(String message) {
        super(message);
    }
}
