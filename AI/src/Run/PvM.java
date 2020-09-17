package Run;

import Rules.Board;
import java.util.*;
import Algorithm.*;
import Notation.*;

import Rules.BoardOutOfBoundsException;
import Rules.Game;
import Rules.IllegalMoveException;
import Rules.UnexpectedPieceException;

//rodar em um while
public class PvM {
    public static void main(String[] args) throws Exception {

        Board board = new Board();
        Scanner s = new Scanner(System.in);
        

        int depth = 4;

        while (!board.endOfGame) {
            Game game = new Game(board);
            AlphaBeta foraNucleo = new AlphaBeta(board);
            game.getBoard().printImage();
            System.out.println();
            if(!board.getTurn()){
                try {
                    long time = System.currentTimeMillis();
                    
                    board = foraNucleo.bestPlaying(0, depth, false);
                    //System.out.println(foraNucleo.getGraph().HowManyNodes());
                    long tn = System.currentTimeMillis();
                    //System.out.println(tn - time);
                    Game test = new Game(board);
                    
                    test.allLegal();
                    test.isCheckMateBlack();
                    test.isCheckMateWhite();
                    test.isBlackPromotion();
                    test.isWhitePromotion();
                    Translator.NotationComputerToChess(board.getLastMove()[0].getPos_i(), board.getLastMove()[0].getPos_j());
                    System.out.println("De: " + Translator.NotationComputerToChess(board.getLastMove()[0].getPos_i(), board.getLastMove()[0].getPos_j()) 
                        + " Para: " + Translator.NotationComputerToChess(board.getLastMove()[1].getPos_i(), board.getLastMove()[1].getPos_j()));
                    
                }
                catch (BoardOutOfBoundsException b) {
                    System.err.println("\n*Fora do tabuleiro*\n");
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
                catch(ArrayIndexOutOfBoundsException a){
                    System.err.println("\n*Indice inválido*\n");
                }
                //game.setTurn(!game.getTurn());
            }else{
                System.out.print("Peça da posição.: ");
                String first = s.nextLine();
                System.out.print("Para.: ");
                String second = s.nextLine();
                try { 
                    int[] ii = Translator.NotationChessToComputer(first.charAt(0), Character.getNumericValue(first.charAt(1)));
                    int[] jj = Translator.NotationChessToComputer(second.charAt(0), Character.getNumericValue(second.charAt(1)));
                    
                    game.allLegal();
                    
                    game.getHasWhiteKingMoved();
                    game.getHasWhiteLeftRookMoved();
                    game.getHasWhiteRightRookMoved();
                                        
                    game.move(ii[0], ii[1], jj[0], jj[1]);
                    game.allLegal();
                    
                    game.isCheckMateBlack();
                    game.isCheckMateWhite();
                    
                    game.isWhitePromotion();
                    game.isBlackPromotion();
                    //System.out.println(game.hasWhiteRightRookMoved);
                }
                catch (BoardOutOfBoundsException b) {
                    System.err.println("\n*Fora do tabuleiro*\n");
                }
                catch(StringIndexOutOfBoundsException b){
                    System.err.println("\n*Entrada Inválida*\n");
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
                catch(ArrayIndexOutOfBoundsException a){
                    System.err.println("\n*Indice inválido*\n");
                }
            }

        }
        board.printImage();
        System.out.println(" ");
        System.out.println("Jogo Acabou");
        s.close();
    }
}
/* e2

*/ 