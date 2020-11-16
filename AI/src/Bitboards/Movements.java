package Bitboards;

public class Movements {
    public Bits bit = new Bits();

    //de 0 (Torre preta) a 63 (torre branca).
    //da esquerda para direita na visão das brancas.
    //a = qual a direção (norte, nordeste, noroeste e leste).
    //elas não diferenciam a cor das peças, logo ataques e defesas são vistos
    //por igual.
    //ATENÇÃO: A IMPLEMENTAÇÃO DAS PEÇAS DESLIZANTES ESTÁ ERRADA 
    //CONSERTAR DEPOIS!!!!!!!!!
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
        for (int i = 1; i<=3; i += 2) {
            a |= positiveMove(sq, i);
        }
        for (int i = 4; i<=6; i += 2) {
            a |= negativeMove(sq, i);
        }
        return a;
    } 
    //falta implementar captura
    public long pawnWhite(int sq) {
        long a = (1L << sq);
        a >>= 8;
        return a;
    }
    //falta implementar captura
    public long pawnBlack(int sq) {
        long a = (1L << sq);
        a <<= 8;
        return a;
    }

    public long kingMoves(int sq) {
        return 0L;
    }

    public long queenMoves(int sq) {
        return (rookGen(sq) | bishopGen(sq));
    }

    public char getPiece(int sq) {
        return (bit.chessBoard[sq/8][sq%8]); 
    }
    //IMPLEMENTAR DEPOIS
    public long knightMoves(int sq) {
        return 0L;
    }

    public long getPieceSet(int sq) {
        long set = getPiece(sq);
        if (set == 'p') {
            return bit.pb;
        }
        if (set == 'P') {
            return bit.pw;
        }
        if (set == 'C') {
            return bit.cw;
        }
        if (set == 'c') {
            return bit.cb;
        }
        if (set == 'b') {
            return bit.bb;
        }
        if (set == 'B') {
            return bit.bw;
        }

        if (set == 't') {
            return bit.tb;
        }
        if (set == 'T') {
            return bit.tw;
        }
        if (set == 'q') {
            return bit.qb;
        }
        if (set == 'Q') {
            return bit.qw;
        }
        if (set == 'k') {
            return bit.kb;
        }
        if (set == 'K') {
            return bit.kw;
        }
        return 0L;
    }
    //tentar substituir por um jeito mais eficiente!
    public long getPieceMove(int sq) {
        long set = getPiece(sq);
        if (set == 'p') {
            return pawnBlack(sq);
        }
        if (set == 'P') {
            return pawnWhite(sq);
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
            moves[i] = getPieceMove(bit.chessBoard[i/8][i%8]);
        }
        return moves;
    }

}
