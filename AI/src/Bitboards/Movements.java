package Bitboards;

public class Movements {
    public Bits bit = new Bits();

    //de 0 (Torre preta) a 63 (torre branca).
    //da esquerda para direita na visão das brancas.
    //a = qual a direção (norte, nordeste, noroeste e leste).
    //elas não diferenciam a cor das peças, logo ataques e defesas são vistos
    //por igual.
    public long positiveMove(int sq, int a) {
        long board = bit.board;
        long mask = bit.rayAttacks[sq][a] & board;
        long attack = bit.rayAttacks[sq][a] & bit.below_LS1B_mask_include(mask);
        return attack;
    }

    public long negativeMove(int sq, int a) {
        long board = bit.board;
        long mask = bit.rayAttacks[sq][a] & board;
        long attack = bit.rayAttacks[sq][a] & bit.above_msb_mask_include(mask);
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

    public long pawnWhite(int sq) {
        long a = (1L << sq);
        a >>= 8;
        return a;
    }

    public long pawnBlack(int sq) {
        long a = (1L << sq);
        a <<= 8;
        return a;
    }

    public long kingMoves(int sq) {
        long a = (1<<sq);
        long b = a;
        for (int i = 9; i>=7; i++) {
            b |= a<<i;
        }
        b |= b<<1;
        b |= b>>1;
        for (int i = 9; i>=7; i++) {
            b |= a>>i;
        }

        return (b^a);
    }

    public long queenMoves(int sq) {
        return (rookGen(sq) | bishopGen(sq));
    }

    public char getPiece(int sq) {
        int i = sq/8;
        int j = sq%8;
        return (bit.chessBoard[i][j]); 
    }

    public long getPieceSet(int sq) {
        long set = getPieceSet(sq);
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
}
