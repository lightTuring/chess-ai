package Algorithm;

import Rules.Bits;
import Rules.Manipulator;
import Rules.Movements;

public class Evaluate {
    Bits bit;
    Movements move;
    long attackWhite;
    long attackBlack;

    public Evaluate(Bits bit) {
        this.bit = bit;
        this.move = new Movements(bit);
        this.attackWhite = move.whiteAttackMap();
        this.attackBlack= move.blackAttackMap();
    }
    //pretas pares, brancas ímpares
    // peão 0 e 1; cavalo: 2 e 3; bispo: 4 e 5; torre: 6 e 7; rainha: 8 e 9; rei: 10 e 11
    private double piece() {
        double white = 0;
        double black = 0;
      
        white += Long.bitCount(bit.pieceBoard[1]); 
        white += Long.bitCount(bit.pieceBoard[3])*3;
        white += Long.bitCount(bit.pieceBoard[5])*3.5;
        white += Long.bitCount(bit.pieceBoard[7])*5;
        white += Long.bitCount(bit.pieceBoard[9])*9;

        black += Long.bitCount(bit.pieceBoard[0]); 
        black += Long.bitCount(bit.pieceBoard[2])*3;
        black += Long.bitCount(bit.pieceBoard[4])*3.5;
        black += Long.bitCount(bit.pieceBoard[6])*5;
        black += Long.bitCount(bit.pieceBoard[8])*9;

        return (white - black);
    }

    private double kingSafety()  {
        double white = 0;
        double black = 0;
        int w = Manipulator.squareOfPiece('K', bit);
        int b = Manipulator.squareOfPiece('k', bit);

        long x = move.queenMoves(w)& ~bit.white;
        white = -(Math.sqrt((double)Long.bitCount(x))/1.5);

            
        long y = move.queenMoves(b)& ~bit.black;
        black = -(Math.sqrt((double)Long.bitCount(y))/1.5);
            
        
        return (white - black);
    }

    private double pieceSafety(){
        int l = 0;
        int r = 63;
        double white= 0;
        double black = 0;
        while(l<=r){
            int mid = (l+r)/2;
            if (Manipulator.isBlack(mid, bit) && move.isDefendedBlack(mid)) {
                if ((Manipulator.getPiece(mid, bit) == 'b') || (Manipulator.getPiece(mid, bit) == 't') || (Manipulator.getPiece(mid, bit) == 'c')) {
                    black+=0.2;
                }
                if ((Manipulator.getPiece(mid, bit) == 'p')) {
                    black += 0.05;
                }
            }
            else if (Manipulator.isWhite(mid, bit) && move.isDefendedWhite(mid)) {
                if (Manipulator.getPiece(mid, bit) == 'B' || (Manipulator.getPiece(mid, bit) == 'T') || (Manipulator.getPiece(mid, bit) == 'C')) {
                    white+=0.2;
                }
                if ((Manipulator.getPiece(mid, bit) == 'P')) {
                    white += 0.05;
                }
            }
            l = mid + 1;
        }

        l = 0;
        r = 63;

        while(l<=r){
            int mid = (l+r)/2;
            if (Manipulator.isBlack(mid, bit) && move.isDefendedBlack(mid)) {
                if ((Manipulator.getPiece(mid, bit) == 'b') || (Manipulator.getPiece(mid, bit) == 't') || (Manipulator.getPiece(mid, bit) == 'c')) {
                    black+=0.2;
                }
                if ((Manipulator.getPiece(mid, bit) == 'p')) {
                    black += 0.05;
                }
            }
            else if (Manipulator.isWhite(mid, bit) && move.isDefendedWhite(mid)) {
                if (Manipulator.getPiece(mid, bit) == 'B' || (Manipulator.getPiece(mid, bit) == 'T') || (Manipulator.getPiece(mid, bit) == 'C')) {
                    white+=0.2;
                }
                if ((Manipulator.getPiece(mid, bit) == 'P')) {
                    white += 0.05;
                }
            }
            r = mid - 1;
        }

        return (white - black);

    }
    private double pawnAdvancement() {
        double white = 0;
        double black = 0;
        long whitePawn = Manipulator.indexOfPiece('P', bit);
        long blackPawn = Manipulator.indexOfPiece('p', bit);
        while (whitePawn != 0L) {
            long x = Manipulator.lsb(whitePawn);
            int pos = Manipulator.positionOfBit(x);
            white += (6 - (pos/8))*0.1;
            whitePawn = Manipulator.reset(whitePawn);
        }
        while (blackPawn != 0L) {
            long x = Manipulator.lsb(blackPawn);
            int pos = Manipulator.positionOfBit(x);
            black += ((pos/8) - 1)*0.1;
            blackPawn = Manipulator.reset(blackPawn);
        }

        
        return (white - black);

    }
    
    private double pieceMobility() {
        double white = 0;
        double black = 0;
        for (int i = 0; i<64; i++) {
            if (Manipulator.getPiece(i, bit) != 'q' || Manipulator.getPiece(i, bit) != 'Q') {
                long x = move.getPieceMove(i) & ~bit.board;
                if (Manipulator.isWhite(i, bit)) {
                    white += Math.sqrt((double)Long.bitCount(x));
                }
                else if(Manipulator.isBlack(i, bit)) {
                    black += Math.sqrt((double)Long.bitCount(x));
                }                                
            }                         
        }

        return (white-black);
    }

    public double total () {
        return (pieceSafety() + piece() + kingSafety() + pawnAdvancement() + pieceMobility());
    }
    
}
