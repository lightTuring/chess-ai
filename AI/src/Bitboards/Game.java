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
    public boolean isEmpty(long[] a) {
        for (int i = 0; i<a.length; i++) {
            if (a[i] != 0L) {
                return false;
            }
        }
        return true;
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
    public boolean isCheck() {
        if (bit.turn == true) {
            return isCheckWhite();
        }
        return isCheckBlack();
    }
    //implementar verificação de en passant depois;
    //deve inicializar o jogo;
    public void allLegal() {
        Movements move = new Movements(bit);
        long[] b = move.uncheckedMoves();
        for (int sq = 0; sq<64; sq++) {
            long a = 0L;
            if (Manipulator.isWhite(sq, bit) == bit.turn) {
                long x = b[sq];  
                while (x != 0) {
                    long lsb = Manipulator.lsb(x);
                    int pos = Manipulator.positionOfBit(lsb);
                    Manipulator.changePos(sq, pos, bit);
                    if (!isCheck()) {
                        a |= lsb;
                    }
                    Manipulator.changePos(pos, sq, bit);
                    Manipulator.reset(x);
                }
            }
            stateBoard[sq] = a;
        }
    }
    public boolean isCheckMateWhite() {
        if (isCheckWhite() && bit.turn && isEmpty(stateBoard) && isEmpty(enPassantBoard)){
            return true;
        }
        return false;
    }
    public boolean isCheckMateBlack() {
        if (isCheckBlack() && !bit.turn && isEmpty(stateBoard) && isEmpty(enPassantBoard)){
            return true;
        }
        return false;
    }
}