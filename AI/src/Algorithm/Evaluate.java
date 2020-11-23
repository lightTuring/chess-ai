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

    private double piece() {
        double white = 0;
        double black = 0;
        for (int i = 0; i < 64; i++) {
                if (Manipulator.isWhite(i, bit)) {
                    if (Manipulator.getPiece(i, bit) == 'P')
                        white = white + 1;
                    if (Manipulator.getPiece(i, bit) == 'Q')
                        white = white + 9;
                    if (Manipulator.getPiece(i, bit) == 'T')
                        white = white + 5;
                    if (Manipulator.getPiece(i, bit) == 'B')
                        white = white + 3.5;
                    if (Manipulator.getPiece(i, bit) == 'C')
                        white = white + 3;
                } else {
                    if (Manipulator.getPiece(i, bit) == 'p')
                        black = black + 1;
                    if (Manipulator.getPiece(i, bit) == 'q')
                        black = black + 9;
                    if (Manipulator.getPiece(i, bit) == 't')
                        black = black + 5;
                    if (Manipulator.getPiece(i, bit) == 'b')
                        black = black + 3.5;
                    if (Manipulator.getPiece(i, bit) == 'c')
                        black = black + 3;
                }
            }
        
        return (white - black);
    }

    private double kingSafety()  {
        double white = 0;
        double black = 0;
        int w = Manipulator.squareOfPiece('K', bit);
        int b = Manipulator.squareOfPiece('k', bit);

        long x = move.queenMoves(w);
        white = -(Math.sqrt((double)Long.bitCount(x))/1.5);

            
        long y = move.queenMoves(b);
        black = -(Math.sqrt((double)Long.bitCount(y))/1.5);
            
        
        return (white - black);
    }

    private double pieceSafety()  {
        double white = 0;
        double black = 0;
        for (int i = 0; i < 64; i++) {
                if (Manipulator.isBlack(i, bit) && move.isDefendedBlack(i)) {
                    if ((Manipulator.getPiece(i, bit) == 'b') || (Manipulator.getPiece(i, bit) == 't') || (Manipulator.getPiece(i, bit) == 'c')) {
                        black++;
                    }
                    if ((Manipulator.getPiece(i, bit) == 'p')) {
                        black += 0.1;
                    }
                }
                else if (Manipulator.isWhite(i, bit) && move.isDefendedWhite(i)) {
                    if (Manipulator.getPiece(i, bit) == 'B' || (Manipulator.getPiece(i, bit) == 'T') || (Manipulator.getPiece(i, bit) == 'C')) {
                        white++;
                    }
                    if ((Manipulator.getPiece(i, bit) == 'P')) {
                        white += 0.1;
                    }
                }
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
