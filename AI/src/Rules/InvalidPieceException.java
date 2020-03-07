package Rules;

/* Exceção para ser lançada onde antes era IllegalArgumentException,
pois essa última é mais adequada quando há incompatibilidade de tipos
entre os argumentos e os parâmetros de um método.
*/
public class InvalidPieceException extends Exception {
    public InvalidPieceException(String message) {
	if (message.isEmpty()) {
	    super("A peça encontrada não corresponde à esperada pelo contexto");
	} else {
	    super(message);
	}
    }
}
