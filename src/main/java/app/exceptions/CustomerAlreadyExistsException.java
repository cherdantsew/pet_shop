package app.exceptions;

public class CustomerAlreadyExistsException extends RuntimeException{
    public CustomerAlreadyExistsException(String s, String message){
        super(message);
    }
}
