package app.exceptions;

public class TransactionExecutionException extends  RuntimeException{
    public TransactionExecutionException(Throwable cause) {
        super(cause);
    }
}
