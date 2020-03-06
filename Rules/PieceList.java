package rules;

public enum PieceList {

	W_BISHOP('B', true),
	W_KNIGHT('C', true),
	W_ROOK('T', true),
	W_QUEEN('D', true),
	W_KING('R', true),
	W_PAWN('P', true),
	B_BISHOP('b', false),
	B_KNIGHT('c', false),
	B_ROOK('t', false),
	B_QUEEN('d', false),
	B_KING('r', false),
	B_PAWN('p', false);


	//True = Branco; False = preto
	private final char letter;
	private final boolean color;

	//Construtor
	PieceList(char l, boolean s)
	{
		letter = l;
		color = s;

	}

	public int getLetter() {
		return letter;
	}

	public String getColor() {
		return color;
	}
}
