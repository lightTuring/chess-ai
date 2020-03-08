package Rules;

// Para ser usada em Board.doBlacksCastling e Board.doWhitesCastling
@SuppressWarnings("serial")
public class IllegalCastlingException extends Exception {
    public IllegalCastlingException(String message) {
        super(message);
    }
}
