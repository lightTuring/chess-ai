package Bitboards;

@SuppressWarnings("unchecked")
public class Game {
    public long[] stateBoard = new long[64];
    public Bits bit = new Bits();
    
    public Game(Bits bit) {
        this.bit = bit;
    }
    public Game clone() throws CloneNotSupportedException {
        Bits b = this.bit.clone();
        Game game = new Game(b);
        for (int i= 0; i<64; i++) {
            game.stateBoard[i] = this.stateBoard[i];
        }
        return game;
    }
    public boolean isCheckWhite() {
        Movements move = new Movements(bit);
        long k = Manipulator.indexOfPiece('K', bit)[0];
        return ((move.blackAttackMap()&k) != 0);
    }
    public boolean isCheckBlack() {
        Movements move = new Movements(bit);
        long k = Manipulator.indexOfPiece('k', bit)[0];
        return ((move.whiteAttackMap()&k) != 0);
    }
    public boolean isCheckMateWhite() {
        
    }
}