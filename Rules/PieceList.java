package rules;

public enum PieceList {

	W_BISHOP('B', true),
	W_KNIGHT('C', true),
	W_ROOK('T', true),
	W_QUEEN('D', true),
	W_KING('R', true),
	W_PAWN('P', true),
	B_BISHOP('B', false),
	B_KNIGHT('C', false),
	B_ROOK('T', false),
	B_QUEEN('D', false),
	B_KING('R', false),
	B_PAWN('P', false);


	//True = Branco; False = preto
	private final char letter;
	private final boolean color;

	//Construtor
	Pieces(char l, boolean s)
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
