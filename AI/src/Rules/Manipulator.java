package Rules;

import java.util.HashMap;

public class Manipulator {
   
    // 0 - nordeste, 1 - norte, 2 - noroeste, 3 - leste, 4 - oeste, 5 - sudeste, 6 -
    // sul, 7- sudoeste
    public static long rayAttacks[][] = new long [64][8];
    public static long knightAttacks[] = new long[64];
    public static long kingAttacks[] = new long[64];

    public static final long AFile = Long.parseLong("0000000100000001000000010000000100000001000000010000000100000001", 2);
    public static final long BAFile = Long.parseLong("0000001100000011000000110000001100000011000000110000001100000011", 2);
    public static final long HFile = -9187201950435737472L;
    public static final long HGFile = -4557430888798830400L;
    public static final long[] ranks = {-72057594037927936L, Long.parseLong("0000000111111110000000000000000000000000000000000000000000000000", 2), 
                                        Long.parseLong("0000000000000001111111100000000000000000000000000000000000000000", 2), 
                                        Long.parseLong("0000000000000000000000011111111000000000000000000000000000000000", 2), 
                                        Long.parseLong("0000000000000000000000000000000111111110000000000000000000000000", 2), 
                                        Long.parseLong("0000000000000000000000000000000000000001111111100000000000000000", 2), 
                                        Long.parseLong("0000000000000000000000000000000000000000000000001111111100000000", 2), 
                                        Long.parseLong("0000000000000000000000000000000000000000000000000000000011111111", 2)};
    //pretas pares, brancas ímpares
    // peão 0 e 1; cavalo: 2 e 3; bispo: 4 e 5; torre: 6 e 7; rainha: 8 e 9; rei: 10 e 11
    public static HashMap<Character, Integer> mapa = new HashMap<Character, Integer>();
    //INVOCAR ANTES DO JOGO:
    public static void init() {
   
        noEaRay();
        northRay();
        noWeRay();
        eastRay();
        westRay();
        soEaRay();
        southRay();
        soWeRay();

        for (int i = 0; i<64; i++) {
            long b = 1L<<i;
            long a = 0L;
            a |= noNoEa(b);
            a |= noEaEa(b);
            a |= soEaEa(b);
            a |= soSoEa(b);
            a |= noNoWe(b);
            a |= noWeWe(b);
            a |= soWeWe(b);
            a |= soSoWe(b);
            knightAttacks[i] = a;
            
            long c = 0L;
            c |= noEa(b);
            c |= nort(b);
            c |= noWe(b);
            c |= soWe(b);
            c |= south(b);
            c |= soEa(b);
            kingAttacks[i] = c;
        }
        mapa.put('p', 0);
        mapa.put('P', 1);
        mapa.put('c', 2);
        mapa.put('C', 3);
        mapa.put('b', 4);
        mapa.put('B', 5);
        mapa.put('t', 6);
        mapa.put('T', 7);
        mapa.put('q', 8);
        mapa.put('Q', 9);
        mapa.put('k', 10);
        mapa.put('K', 11);
    }

    public static long noNoEa(long b) {return (b & ~AFile) << 15;}
    public static long noEaEa(long b) {return (b & ~BAFile) << 6;} 
    public static long soEaEa(long b) {return (b & ~BAFile) >>> 10;}
    public static long soSoEa(long b) {return (b & ~AFile ) >>> 17;} 
    public static long noNoWe(long b) {return (b & ~HFile) << 17;}
    public static long noWeWe(long b) {return (b & ~HGFile) <<  10;}
    public static long soWeWe(long b) {return (b & ~HGFile) >>> 6;}
    public static long soSoWe(long b) {return (b & ~HFile ) >>> 15;}

    public static long noEa(long b) {return (b & ~AFile) << 7;}
    public static long nort(long b) {return (b << 8);}
    public static long noWe(long b) {return (b & ~HFile) << 9;}
    public static long soWe(long b) {return (b & ~HFile) >>> 7;}
    public static long south(long b) {return (b >>> 8);}
    public static long soEa(long b) {return (b & ~AFile) >>> 9;}

    public static void makeBoards(Bits bit) {
        for (int sq = 0; sq <64; sq++) {
            long mask = (1L << sq);
            char c = bit.chessBoard[sq/8][sq%8];
            if (isPiece(sq, bit)) {
                bit.board |= mask;
                if(Character.isUpperCase(c)) {
                    bit.white = bit.white | mask;
                }
                else {
                    bit.black = bit.black | mask;
                }
                int i = Manipulator.mapa.get(c);
                bit.pieceBoard[i] = bit.pieceBoard[i] | mask;
            }
            
            
        }
    }
    //posição do bit 1 em um long com um único bit 1:
    public static int positionOfBit(long x) {
        int count = 0;
        while (x != 0L) {
            x>>>=1;
            count++;
        }
        return count-1;
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
        x |= x >>> 32;
        x |= x >>> 16;
        x |= x >>>  8;
        x |= x >>>  4;
        x |= x >>>  2;
        x |= x >>>  1;
        return ((x >>> 1) + 1);
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
        for (int j=7; j >= 0; j--, a >>>= 8, a &= (a-1)) {
            long ne = a;
            for (int i = 0; i < 64; i += 8, ne <<= 8)
               rayAttacks[i+j][0] = ne;
        }
    }

    public static void noWeRay() {
        long a = -(9205322385119247872L);
        for (int j=0; j < 8; j++, a <<= 1, a &= ~AFile) {
            long nw = a;
            for (int i = 0; i < 64; i += 8, nw <<= 8)
               rayAttacks[i+j][2] = nw;
        }
    }

    public static void eastRay() {
        long a = 127L;
        for (int j=7; j >= 0; j--, a >>>= 1) {
            long e = a;
            for (int i = 0; i < 64; i += 8, e <<= 8)
               rayAttacks[i+j][3] = e;
        }
    }

    public static void westRay() {
        long a = -(144115188075855872L);
        for (int j=0; j < 8; j++, a <<= 1) {
            long W = a;
            for (int i = 56; i >= 0; i -= 8, W >>>= 8)
               rayAttacks[i+j][4] = W;
        }
    }
    
    public static void southRay() {
        long a = (282578800148737L);
        for (int j=0; j < 8; j++, a <<= 1) {
            long W = a;
            for (int i = 56; i >= 0; i -= 8, W >>>= 8)
               rayAttacks[i+j][6] = W;
        }
    }

    public static void soWeRay() {
        long a = (567382630219904L);
        for (int j=0; j < 8; j++, a <<= 1, a &= (a-1)) {
            long W = a;
            for (int i = 56; i >= 0; i -= 8, W >>>= 8)
               rayAttacks[i+j][7] = W;
        }
    }

    public static void soEaRay() {
        long a = (18049651735527937L);
        for (int j=7; j >= 0; j--, a >>>= 1, a &= ~HFile) {
            long se = a;
            for (int i = 56; i >= 0; i -= 8, se >>>= 8)
               rayAttacks[i+j][5] = se;
        }
    }

    public static char getPiece(int sq, Bits bit) {
        int i = sq/8;
        int j = sq%8;
        return (bit.chessBoard[i][j]); 
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
        long positions = bit.pieceBoard[Manipulator.mapa.get(b)];
        return positions;
    }
    public static int squareOfPiece(char b, Bits bit) {
        return (positionOfBit(indexOfPiece(b, bit)));
    }
    public static void setPiece(char b, int sq, Bits bit) {
        bit.chessBoard[sq/8][sq%8] = b;
    }
    //automaticamente faz os bitboards ao mover;
    public static void changePos(int sqi, int sqf, Bits bit) {
        char c = bit.chessBoard[sqi/8][sqi%8];
        char d = bit.chessBoard[sqf/8][sqf%8];
        if (c != 'o') {     
            long x = 1L<<sqi;
            long y = 1L<<sqf;

            bit.board = (bit.board & ~x) | y;
            if (Character.isUpperCase(c)) {
                bit.white = (bit.white & ~x) | y;
            }
            else {
                bit.black = (bit.black & ~x) | y;
            }

            int i = Manipulator.mapa.get(c);
            bit.pieceBoard[i] = (bit.pieceBoard[i]& ~x) | y;

            if (d != 'o') {
                if (Character.isUpperCase(d)) {
                    bit.white = (bit.white & ~y);
                }
                else {
                    bit.black = (bit.black & ~y);
                }
                int j = Manipulator.mapa.get(d);
                bit.pieceBoard[j] = (bit.pieceBoard[j]& ~y);
            }

            bit.chessBoard[sqf/8][sqf%8] = bit.chessBoard[sqi/8][sqi%8];
            bit.chessBoard[sqi/8][sqi%8] = 'o';
        }
    }
    
    public static long getColorSet(int sq, Bits bit) {
        long a = 1L << sq;
        if ((bit.white & a) != 0L) {
            return bit.white;
        }
        return bit.black;
    }
    public static void printImage(Bits bit) {
        char[] letters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };
        int[] numbers = { 8, 7, 6, 5, 4, 3, 2, 1 };
        for (int i = 0; i < bit.chessBoard.length; i++) {
            for (int j = 0; j < bit.chessBoard[i].length; j++) {
                System.out.print(bit.chessBoard[i][j]);
            }
            System.out.print(" ");
            System.out.println(numbers[i]);
        }
        System.out.println();
        for (int i = 0; i < letters.length; i++)
            System.out.print(letters[i]);
    }
    
    public static void changePiece(int sq, char c, Bits bit) {
        bit.chessBoard[sq/8][sq%8] = c; 
    }

    public static boolean isCheckWhite(Bits bit) {
        Movements move = new Movements(bit);
        long k = indexOfPiece('K', bit);
        return ((move.blackAttackMap()&k) != 0L);
    }
    public static boolean isCheckBlack(Bits bit) {
        Movements move = new Movements(bit);
        long k = indexOfPiece('k', bit);
        return ((move.whiteAttackMap()&k) != 0L);
    }
    public static boolean isCheck(Bits bit) {
        if (bit.turn == true) {
            return isCheckWhite(bit);
        }
        return isCheckBlack(bit);
    }
}
