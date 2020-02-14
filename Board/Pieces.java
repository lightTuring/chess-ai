package rules;

public enum Pieces {
	
	W_BISHOP(3, "W_BISHOP"),
	W_KNIGHT(2, "W_KNIGHT"),
	W_ROOK(1, "W_ROOK"), 
	W_QUEEN(4, "W_QUEEN"), 
	W_KING(5, "W_KING"), 
	W_PAWN(6, "W_PAWN"),
	B_BISHOP(10, "B_BISHOP"),
	B_KNIGHT(9, "B_KNIGHT"),
	B_ROOK(8, "B_ROOK"), 
	B_QUEEN(4, "B_QUEEN"), 
	B_KING(12, "B_KING"), 
	B_PAWN(7, "B_PAWN");
	
	
	//Instance
	private final int number;
	private final String name;
	
	//Constructor
	Pieces(int n, String s) 
	{
		number = n;
		name = s; 
		
		
	}
	
	public int getNumber() {
		return number;
	}
	
	public String getName() {
		return name;
	}
}
