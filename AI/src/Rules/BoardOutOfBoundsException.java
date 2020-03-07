package Rules;

// Para ser usada em Board.getPiece(), no lugar de IllegaldArgumentException
public class BoardOutOfBoundsException extends Exception {
    public BoardOutOfBoundsException(String message) {
	if (message.isEmpty()) {
	    super("Tentou-se acessar uma casa além dos limites do tabuleiro");
	} else {
	    super(message);
	}
    }
}
