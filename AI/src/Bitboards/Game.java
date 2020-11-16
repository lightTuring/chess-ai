package Bitboards;

@SuppressWarnings("unchecked")
public class Game {
    public long[] stateBoard = new long[64];
    public long[] castleBoard = new long[64];
    public long[] enPassantBoard = new long[64];

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
        long k = bit.kw;
        return ((move.blackAttackMap()&k) != 0);
    }
    public boolean isCheckBlack() {
        Movements move = new Movements(bit);
        long k = bit.kb;
        return ((move.whiteAttackMap()&k) != 0);
    }
    public boolean isCheckMateWhite() {
        Movements move = new Movements(bit);
        
    }
}