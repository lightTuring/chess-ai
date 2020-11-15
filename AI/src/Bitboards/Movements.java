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

    

}
