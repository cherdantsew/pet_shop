package app.exceptions;

public class CustomerNotFountException extends RuntimeException {
    public CustomerNotFountException(String login, String password) {
        super(String.format("There is no customer with login: %s, password: %s.", login, password));
    }
}
