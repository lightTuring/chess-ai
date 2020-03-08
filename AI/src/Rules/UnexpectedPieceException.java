package Rules;

/* Exceção para ser lançada onde antes era IllegalArgumentException,
pois essa última é mais adequada quando há incompatibilidade de tipos
entre os argumentos e os parâmetros de um método.
*/
@SuppressWarnings("serial")
public class UnexpectedPieceException extends Exception {
    public UnexpectedPieceException(String message) {
    	super(message);
    }
}
