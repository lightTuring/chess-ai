package Rules;

// Para ser usada em Board.getPiece(), no lugar de IllegaldArgumentException
@SuppressWarnings("serial")
public class BoardOutOfBoundsException extends Exception {
    public BoardOutOfBoundsException(String message) {
    	super(message);
    }
}
