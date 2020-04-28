package Rules;

@SuppressWarnings("serial")
public class IllegalMoveException extends Exception{
    public IllegalMoveException(String message) {
    	super(message);
    }
}