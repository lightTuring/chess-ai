package Bitboards;

public class Manipulator {
    // 0 - nordeste, 1 - norte, 2 - noroeste, 3 - leste, 4 - oeste, 5 - sudeste, 6 - sul, 7- sudoeste 
    public static long rayAttacks[][] = new long [64][8];


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
    //INVOCAR ANTES DO JOGO:
    public static void initRayEmpty(Bits bit) {
   
        noEaRay();
        northRay();
        noWeRay();
        eastRay();
        westRay();
        soEaRay();
        southRay();
        soWeRay();
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

    public static long reset(long x) {
        return (x & (x-1)); 
    }

    public static long above_msb_mask_include(long x) {
        return (-(msb(x)));
    }

    //x tem que ter apenas um bit '1';
    //north é na direção de cima das PRETAS;
    //retorna um long sem conter a peça em si;
    public static void northRay() {
        long nort = 72340172838076672L;
        for (int sq=0; sq < 64; sq++, nort <<= 1)
            rayAttacks[sq][1] = nort;
    }
    public static void noEaRay() {
        long a = 72624976668147712L;
        for (int j=7; j >= 0; j--, a >>= 8, a &= (a-1)) {
            long ne = a;
            for (int i = 0; i < 64; i += 8, ne <<= 8)
               rayAttacks[i+j][0] = ne;
        }
    }

    public static void noWeRay() {
        long a = -(9205322385119247872L);
        for (int j=0; j < 8; j++, a <<= 1) {
            long nw = a;
            for (int i = 0; i < 64; i += 8, nw <<= 8)
               rayAttacks[i+j][2] = nw;
        }
    }

    public static void eastRay() {
        long a = 127L;
        for (int j=7; j >= 0; j--, a >>= 1) {
            long e = a;
            for (int i = 0; i < 64; i += 8, e <<= 8)
               rayAttacks[i+j][3] = e;
        }
    }

    public static void westRay() {
        long a = -(144115188075855872L);
        for (int j=0; j < 8; j++, a <<= 1) {
            long W = a;
            for (int i = 56; i >= 0; i -= 8, W >>= 8)
               rayAttacks[i+j][4] = W;
        }
    }
    
    public static void southRay() {
        long a = (282578800148737L);
        for (int j=0; j < 8; j++, a <<= 1) {
            long W = a;
            for (int i = 56; i >= 0; i -= 8, W >>= 8)
               rayAttacks[i+j][6] = W;
        }
    }

    public static void soWeRay() {
        long a = (567382630219904L);
        for (int j=0; j < 8; j++, a <<= 1, a &= (a-1)) {
            long W = a;
            for (int i = 56; i >= 0; i -= 8, W >>= 8)
               rayAttacks[i+j][7] = W;
        }
    }

    public static void soEaRay() {
        long a = (18049651735527937L);
        for (int j=7; j >= 0; j--, a >>= 1) {
            long se = a;
            for (int i = 56; i >= 0; i -= 8, se >>= 8)
               rayAttacks[i+j][5] = se;
        }
    }

    public static char getPiece(int sq, Bits bit) {
        int i = sq/8;
        int j = sq%8;
        return (bit.chessBoard[i][j]); 
    }

    public static long getPieceSet(int sq, Bits bit) {
        char set = getPiece(sq, bit);
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
    //considerar otimizar
    public static long getPieceSet(char set, Bits bit) {
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

    public static boolean isWhite(int sq, Bits bit) {
        return (Character.isUpperCase(getPiece(sq, bit)));
    }
    public static boolean isBlack(int sq, Bits bit) {
        return (Character.isLowerCase(getPiece(sq, bit)) && (getPiece(sq, bit) != 'o'));
    }
    public static boolean isPiece(int sq, Bits bit) {
        return (getPiece(sq, bit) != 'o');
    }
    public static long indexOfPiece(char b, Bits bit) {
        long positions = getPieceSet(b, bit);
        return positions;
    }
    public static void setPiece(char b, int sq, Bits bit) {
        bit.chessBoard[sq/8][sq%8] = b;
    }
    public static void changePos(int sqi, int sqf, Bits bit) {
        bit.chessBoard[sqf/8][sqf%8] = bit.chessBoard[sqi/8][sqi%8];
        bit.chessBoard[sqi/8][sqi%8] = 'o';
    }
}
