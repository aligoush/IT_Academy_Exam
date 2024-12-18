package exceptions;

public class GameAlreadyExistsException extends Exception {
    public GameAlreadyExistsException(String message){
        super(message);
    }
}
