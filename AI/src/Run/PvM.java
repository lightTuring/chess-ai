package Run;

import Rules.Bits;
import java.util.*;
import Algorithm.*;
import Notation.*;

import Rules.Game;
import Rules.IllegalMoveException;
import Rules.Manipulator;
import Rules.UnexpectedPieceException;

//rodar em um while
public class PvM {
    public static void main(String[] args) throws Exception {
        Manipulator.init();
        Bits bit = new Bits();
        Scanner s = new Scanner(System.in);        
        int depth = 5;

        while (!bit.endOfGame) {
            Manipulator.printImage(bit);
            AlphaBeta foraNucleo = new AlphaBeta(bit);
            System.out.println();
            if(!bit.turn){
                try {
                    long time = System.currentTimeMillis();                    
                    bit = foraNucleo.bestPlaying(0, depth, false);
                    //System.out.println(foraNucleo.getGraph().HowManyNodes());
                    Game g = new Game(bit);
                    long tn = System.currentTimeMillis();
                    System.out.println(tn - time);                    
                }
                catch (UnexpectedPieceException b) {
                    System.err.println("\n*Não é peça*\n");
                }
                catch (CloneNotSupportedException clone) {
                    System.err.println("\n*Clone Errado*\n");
                }
                catch (IllegalMoveException i) {
                    System.err.println("\n*Movimento ilegal*\n");
                }
                //game.setTurn(!game.getTurn());
            }else {
                System.out.print("Peça da posição.: ");
                String first = s.nextLine();
                System.out.print("Para.: ");
                String second = s.nextLine();
                try {
                    Game g = new Game(bit);
                    int sqi = Translator.NotationChessToComputer(first.charAt(0),first.charAt(1) - '0' );
                    int sqf = Translator.NotationChessToComputer(second.charAt(0), second.charAt(1)- '0');
                    g.move(sqi, sqf);
                    g.allLegal();
                    g.isCheckMate();
                }
                catch(StringIndexOutOfBoundsException b){
                    System.err.println("\n*Entrada Inválida*\n");
                }
                catch (IllegalMoveException i) {
                    System.err.println("\n*Movimento ilegal*\n");
                }
            }
        }
        Manipulator.printImage(bit);
        System.out.println(" ");
        System.out.println("Jogo Acabou");
        s.close();
    }
}
/* e2

*/ 