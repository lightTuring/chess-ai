package Bitboards;

public class Manipulator {
    public static final long rayAttacks[][] = new long [64][8];

    public static void initBits(Bits bit) {
        for (int i = 0; i < bit.chessBoard.length; i++) {
            for (int j = 0; j < bit.chessBoard[i].length; j++) {
                bit.chessBoard[i][j] = 'o';
            }
        }
        for (int i = 0; i < bit.chessBoard.length; i++)
            bit.chessBoard[1][i] = 'p';
        for (int i = 0; i < bit.chessBoard.length; i++)
            bit.chessBoard[6][i] = 'P';
        for (int i = 0; i < bit.chessBoard.length; i++)
            bit.chessBoard[0][i] = bit.initPosBlack[i];
        for (int i = 0; i < bit.chessBoard.length; i++)
            bit.chessBoard[7][i] = bit.initPosWhite[i];

        initRayEmpty(bit);
    } 
    private static void initRayEmpty(Bits bit) {
        for (int i = 0; i<64; i++) {
            rayAttacks[i][0] = noEaRay(i);
            rayAttacks[i][1] = northRay(i);
            rayAttacks[i][2] = noWeRay(i);
            rayAttacks[i][3] = eastRay(i);
            rayAttacks[i][4] = westRay(i);
            rayAttacks[i][5] = soEaRay(i);
            rayAttacks[i][6] = southRay(i);
            rayAttacks[i][7] = soWeRay(i);
        }
        
    }
    public static void makeBoards(Bits bit) {
        for (int i = 0; i <64; i++) {
            long mask = (1L << i);
            if (bit.chessBoard[i/8][i%8] != 'o') {
                bit.board = (bit.board | mask);
                if (Character.isUpperCase(bit.chessBoard[i/8][i%8])) {
                    bit.white = (bit.white | mask);
                }
                else {
                    bit.black = (bit.black | mask);
                }
                switch (bit.chessBoard[i/8][i%8]) {
                    case 'p': bit.pb = (bit.pb | mask);
                        break;
                    case 'P': bit.pw = (bit.pw | mask);
                        break;
                    case 'b': bit.bb = (bit.bb | mask);
                        break;
                    case 'B': bit.bw = (bit.bw | mask);
                        break;
                    case 'c': bit.cb = (bit.cb | mask);
                        break;
                    case 'C': bit.cw = (bit.cw | mask);
                        break;
                    case 't': bit.tb = (bit.tb | mask);
                        break;
                    case 'T': bit.tw = (bit.tw | mask);
                        break;
                    case 'k': bit.kb = (bit.kb | mask);
                        break;
                    case 'K': bit.kw = (bit.kw | mask);
                        break;
                    case 'q': bit.qb = (bit.qb | mask);
                        break;
                    case 'Q': bit.qw = (bit.qw | mask);
                        break;                  
            
                }
            }
        }
    }
    //bit 1 menos significativo:
    public static long lsb(long x) {
        return (x & -x); 
    }

    public static long above_LS1B_mask_exclude(long x) {
        return (x ^  -x);
    } 
    
    public static long below_LS1B_mask_include(long x) {
        return (x ^  (x-1));
    } 

    public static long below_LS1B_mask_exclude(long x) {
        return (~x & (x-1));
    } 
    //bit 1 mais significativo:
    public static long msb(long x) {
        x |= x >> 32;
        x |= x >> 16;
        x |= x >>  8;
        x |= x >>  4;
        x |= x >>  2;
        x |= x >>  1;
        return ((x >> 1) + 1);
    }

    public static long above_msb_mask_include(long x) {
        return (-(msb(x)));
    }

    //x tem que ter apenas um bit '1';
    //north é na direção de cima das PRETAS;
    //retorna um long sem conter a peça em si;
    public static long northRay(long p) {
        long gen = p;
        for (int x = 0; x < 7; x++) {
            p |= (p << 8);
        }
        return (p^gen);
    }
    public static long noEaRay(long p) {
        long gen = p;
        for (int x = 0; x < 7; x++) {
            p |= (p << 9);
        }
        return (p^gen);
    }

    public static long noWeRay(long p) {
        long gen = p;
        for (int x = 0; x < 7; x++) {
            p |= (p << 7);
        }
        return (p^gen);
    }

    public static long eastRay(long p) {
        long gen = p;
        for (int x = 0; x < 7; x++) {
            p |= (p << 1);
        }
        return (p^gen);
    }

    public static long westRay(long p) {
        long gen = p;
        for (int x = 0; x < 7; x++) {
            p |= (p >> 1);
        }
        return (p^gen);
    }
    
    public static long southRay(long p) {
        long gen = p;
        for (int x = 0; x < 7; x++) {
            p |= (p >> 8);
        }
        return (p^gen);
    }

    public static long soWeRay(long p) {
        long gen = p;
        for (int x = 0; x < 7; x++) {
            p |= (p >> 9);
        }
        return (p^gen);
    }

    public static long soEaRay(long p) {
        long gen = p;
        for (int x = 0; x < 7; x++) {
            p |= (p >> 7);
        }
        return (p^gen);
    }

    public static char getPiece(int sq, Bits bit) {
        int i = sq/8;
        int j = sq%8;
        return (bit.chessBoard[i][j]); 
    }

    public static long getPieceSet(int sq, Bits bit) {
        long set = getPiece(sq, bit);
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
