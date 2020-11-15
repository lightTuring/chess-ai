package Bitboards;

public class Bits {
    public char[][] chessBoard = new char[8][8];
    // 0 - nordeste, 1 - norte, 2 - noroeste, 3 - leste, 4 - oeste, 5 - sudeste, 6 - sul, 7- sudoeste 
    public long rayAttacks[][] = new long [64][8];
    private final char[] initPosBlack = { 't', 'c', 'b', 'q', 'k', 'b', 'c', 't' };
    private final char[] initPosWhite = { 'T', 'C', 'B', 'Q', 'K', 'B', 'C', 'T' };
    public long board = 0L;
    public long white = 0L;
    public long black = 0L;
    public long cb = 0L;
    public long cw = 0L;
    public long pb = 0L;
    public long pw = 0L;
    public long bb = 0L;
    public long bw = 0L;
    public long tb = 0L;
    public long tw = 0L;
    public long kb = 0L;
    public long kw = 0L;
    public long qb = 0L;
    public long qw = 0L;
    
    public Bits() {
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                chessBoard[i][j] = 'o';
            }
        }
        for (int i = 0; i < chessBoard.length; i++)
            chessBoard[1][i] = 'p';
        for (int i = 0; i < chessBoard.length; i++)
            chessBoard[6][i] = 'P';
        for (int i = 0; i < chessBoard.length; i++)
            chessBoard[0][i] = initPosBlack[i];
        for (int i = 0; i < chessBoard.length; i++)
            chessBoard[7][i] = initPosWhite[i];

        initRayEmpty();
    } 
    private void initRayEmpty() {
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
    public void makeBoards() {
        for (int i = 0; i <64; i++) {
            long mask = (1L << i);
            if (chessBoard[i/8][i%8] != 'o') {
                board = (board | mask);
                if (Character.isUpperCase(chessBoard[i/8][i%8])) {
                    white = (white | mask);
                }
                else {
                    black = (black | mask);
                }
                switch (chessBoard[i/8][i%8]) {
                    case 'p': pb = (pb | mask);
                        break;
                    case 'P': pw = (pw | mask);
                        break;
                    case 'b': bb = (bb | mask);
                        break;
                    case 'B': bw = (bw | mask);
                        break;
                    case 'c': cb = (cb | mask);
                        break;
                    case 'C': cw = (cw | mask);
                        break;
                    case 't': tb = (tb | mask);
                        break;
                    case 'T': tw = (tw | mask);
                        break;
                    case 'k': kb = (kb | mask);
                        break;
                    case 'K': kw = (kw | mask);
                        break;
                    case 'q': qb = (qb | mask);
                        break;
                    case 'Q': qw = (qw | mask);
                        break;                  
            
                }
            }
        }
    }
    //bit 1 menos significativo:
    public long lsb(long x) {
        return (x & -x); 
    }

    public long above_LS1B_mask_exclude(long x) {
        return (x ^  -x);
    } 
    
    public long below_LS1B_mask_include(long x) {
        return (x ^  (x-1));
    } 

    public long below_LS1B_mask_exclude(long x) {
        return (~x & (x-1));
    } 
    //bit 1 mais significativo:
    public long msb(long x) {
        x |= x >> 32;
        x |= x >> 16;
        x |= x >>  8;
        x |= x >>  4;
        x |= x >>  2;
        x |= x >>  1;
        return ((x >> 1) + 1);
    }

    public long above_msb_mask_include(long x) {
        return (-(msb(x)));
    }

    //x tem que ter apenas um bit '1';
    //north é na direção de cima das PRETAS;
    //retorna um long sem conter a peça em si;
    public long northRay(long p) {
        long gen = p;
        for (int x = 0; x < 7; x++) {
            p |= (p << 8);
        }
        return (p^gen);
    }
    public long noEaRay(long p) {
        long gen = p;
        for (int x = 0; x < 7; x++) {
            p |= (p << 9);
        }
        return (p^gen);
    }

    public long noWeRay(long p) {
        long gen = p;
        for (int x = 0; x < 7; x++) {
            p |= (p << 7);
        }
        return (p^gen);
    }

    public long eastRay(long p) {
        long gen = p;
        for (int x = 0; x < 7; x++) {
            p |= (p << 1);
        }
        return (p^gen);
    }

    public long westRay(long p) {
        long gen = p;
        for (int x = 0; x < 7; x++) {
            p |= (p >> 1);
        }
        return (p^gen);
    }
    
    public long southRay(long p) {
        long gen = p;
        for (int x = 0; x < 7; x++) {
            p |= (p >> 8);
        }
        return (p^gen);
    }

    public long soWeRay(long p) {
        long gen = p;
        for (int x = 0; x < 7; x++) {
            p |= (p >> 9);
        }
        return (p^gen);
    }

    public long soEaRay(long p) {
        long gen = p;
        for (int x = 0; x < 7; x++) {
            p |= (p >> 7);
        }
        return (p^gen);
    }

   

    public long bPawnDouble() {
        long rank = 130816L; 
        long d = ((pb & rank)<<16);
        long dblTargets = d & (~board);
        return dblTargets;
    }

}