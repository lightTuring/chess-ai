package Rules;

public class Movements {
    public Bits bit = new Bits();

    //de 0 (Torre preta) a 63 (torre branca).
    //da esquerda para direita na visão das brancas.
    //a = qual a direção (norte, nordeste, noroeste etc.).
    //elas não diferenciam a cor das peças, logo ataques e defesas são vistos
    //por igual.

    public Movements (Bits bit) {
        this.bit = bit;
    }    

    public long positiveMove(int sq, int a) {
        long board = bit.board;
        long mask = Manipulator.rayAttacks[sq][a] & board;
        long attack = Manipulator.rayAttacks[sq][a] & Manipulator.below_LS1B_mask_include(mask);
        return attack;
    }

    public long negativeMove(int sq, int a) {
        long board = bit.board;
        long mask = Manipulator.rayAttacks[sq][a] & board;
        long attack = Manipulator.rayAttacks[sq][a] & Manipulator.above_msb_mask_include(mask);
        return attack;
    }

    public long bishopGen(int sq) {
        long a = 0L;
        for (int i = 0; i<=2; i += 2) {
            a |= positiveMove(sq, i);
        }
        for (int i = 5; i<=7; i += 2) {
            a |= negativeMove(sq, i);
        }
        return a;
    }
    
    public long rookGen(int sq) {
        long a = 0L;
        for (int i = 1; i<=4; i += 3) {
            a |= positiveMove(sq, i);
        }
        for (int i = 3; i<=6; i += 3) {
            a |= negativeMove(sq, i);
        }
        return a;
    } 
    private long pawnWhite(int sq) {
        long a = (1L << sq);
        a >>>= 8;
        return a& ~bit.board;
    }
    private long pawnBlack(int sq) {
        long a = (1L << sq);
        a <<= 8;
        return a& ~bit.board;
    }

    private long pawnWhiteAttack(int sq) {
        long b = (1L << sq);
        long a = 0L;
        a |= Manipulator.soEa(b);
        a |= Manipulator.soWe(b);
        return a&bit.board;
    }
    private long pawnWhiteAttackUnchecked(int sq) {
        long b = (1L << sq);
        long a = 0L;
        a |= Manipulator.soEa(b);
        a |= Manipulator.soWe(b);
        return a;
    }

    private long pawnBlackAttack(int sq) {
        long b = (1L << sq);
        long a = 0L;
        a |= Manipulator.noEa(b);
        a |= Manipulator.noWe(b);
        return a&bit.board;
    }

    private long pawnBlackAttackUnchecked(int sq) {
        long b = (1L << sq);
        long a = 0L;
        a |= Manipulator.noEa(b);
        a |= Manipulator.noWe(b);
        return a;
    }

    public long doublePawnWhite(int sq) {
        long b = (1L << sq);
        long mask = 71776119061217280L;
        b &= mask;
        return (((b >>> 8) & ~bit.board) >>> 8)& ~bit.board;
    }

    private long doublePawnBlack(int sq) {
        long b = (1L << sq);
        long mask = 65280L;
        b &= mask;
        return (((b << 8) & ~bit.board) << 8)& ~bit.board;
    }

    public long pawnWhiteTotal(int sq) {
        return (pawnWhite(sq) | pawnWhiteAttack(sq) | doublePawnWhite(sq));
    }

    public long pawnBlackTotal(int sq) {
        return (pawnBlack(sq) | pawnBlackAttack(sq) | doublePawnBlack(sq));
    }

    public long kingMoves(int sq) {
        return Manipulator.kingAttacks[sq];
    }

    public long queenMoves(int sq) {
        return (rookGen(sq) | bishopGen(sq));
    }

    public char getPiece(int sq) {
        return (bit.chessBoard[sq/8][sq%8]); 
    }
    public long knightMoves(int sq) {
        return Manipulator.knightAttacks[sq];
    }
    //tentar substituir por um jeito mais eficiente!

    public long getPieceMove(int sq) {
        char set = getPiece(sq);
        
        if (set == 'p') {
            return pawnBlackTotal(sq);
        }
        if (set == 'P') {
            return pawnWhiteTotal(sq);
        }
        if (set == 'C' || set == 'c') {
            return knightMoves(sq);
        }
        if (set == 'b' || set == 'B') {
            return bishopGen(sq);
        }

        if (set == 't'||set == 'T') {
            return rookGen(sq);
        }
        if (set == 'q' || set == 'Q') {
            return queenMoves(sq);
        }
        if (set == 'k' || set == 'K') {
            return kingMoves(sq);
        }
        return 0L;
    }

    public long getPieceAttacks(int sq) {
        long set = getPiece(sq);

        if (set == 'p') {
            return pawnBlackAttackUnchecked(sq);
        }
        if (set == 'P') {
            return pawnWhiteAttackUnchecked(sq);
        }
        if (set == 'C' || set == 'c') {
            return knightMoves(sq);
        }
        if (set == 'b' || set == 'B') {
            return bishopGen(sq);
        }

        if (set == 't'||set == 'T') {
            return rookGen(sq);
        }
        if (set == 'q' || set == 'Q') {
            return queenMoves(sq);
        }
        if (set == 'k' || set == 'K') {
            return kingMoves(sq);
        }
        return 0L;
    }

    public long[] uncheckedMoves() {
        long[] moves = new long[64];
        for (int i = 0; i<64; i++) {
            moves[i] = getPieceMove(i) & ~Manipulator.getColorSet(i, bit);
        }
        return moves;
    }
    public long[] uncheckedMoves(boolean turn) {
        long[] moves = new long[64];
        if (turn) {
            long a = bit.white;
            while (a != 0L) {
                long lsb = Manipulator.lsb(a);
                int sq = Manipulator.positionOfBit(lsb);
                moves[sq] = getPieceMove(sq) & ~bit.white &~bit.pieceBoard[10];
                a = Manipulator.reset(a);
            }
        }  
        else {
            long a = bit.black;
            while (a != 0L) {
                long lsb = Manipulator.lsb(a);
                int sq = Manipulator.positionOfBit(lsb);
                moves[sq] = getPieceMove(sq)& ~bit.black &~bit.pieceBoard[11];
                a = Manipulator.reset(a);
            }
        }      
        return moves;
    }
    //tentar otimizar
    public long whiteAttackMap () {
        long board = bit.white;
        long a = 0L;
        for (int i = 0; i<64; i++) {
            if (Manipulator.isWhite(i, bit)) {
                a |= getPieceAttacks(i);
            }
        }
        return (a& ~board);
    }

    public long blackAttackMap () {
        long board = bit.black;
        long a = 0L;
        for (int i = 0; i<64; i++) {
            if (Manipulator.isBlack(i, bit)) {
                a |= getPieceAttacks(i);
            }
        }
        return (a& ~board);
    }

    public long blackDefendMap () {
        long board = bit.white;
        long a = 0L;
        for (int i = 0; i<64; i++) {
            if (Manipulator.isBlack(i, bit)) {
                a |= getPieceAttacks(i);
            }
        }
        return (a& ~board);
    }

    public long whiteDefendMap () {
        long board = bit.black;
        long a = 0L;
        for (int i = 0; i<64; i++) {
            if (Manipulator.isWhite(i, bit)) {
                a |= getPieceAttacks(i);
            }
        }
        return (a& ~board);
    }

    public boolean isDefendedWhite(int sq) {
        return (whiteDefendMap()&(1L<<sq)) != 0L;
    }

    public boolean isDefendedBlack(int sq) {
        return (blackDefendMap()&(1L<<sq)) != 0L;
    }

}
