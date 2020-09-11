package Run;

import java.util.*;

import Algorithm.*;
import Notation.*;
import Rules.Board;
import Rules.BoardOutOfBoundsException;
import Rules.Game;
import Rules.IllegalMoveException;
import Rules.UnexpectedPieceException;

//rodar em um while
public class Main {
    public static void main(String[] args) throws Exception {
        /*
        CLASSE PRINCIPAL DE EXECUÇÃO        

        *PROJETO DE TESTE*
    
        -> *TURNO DO JOGADOR*
        
        FAZ A JOGADA PELO TERMINAL
        
        -> *TURNO DA MÁQUINA*
        
        VERIFICA SE OS MOVIMENTOS GERADOS SÃO LEGAIS
        ATUALIZA O ARRAY
        ATRIBUI OS PESOS AOS NÓS FINAIS
        CONSTROI O GRAFO
        APLICA MINMAX
        FAZ A JOGADA
    
        -> *TURNO DO JOGADOR*


        *PROJETO FINAL*

        -> *TURNO DO JOGADOR*
        
        PYTHON MANDA PRO JAVA A JOGADA
        
        -> *TURNO DA MÁQUINA*
        
        VERIFICA SE OS MOVIMENTOS GERADOS SÃO LEGAIS
        ATUALIZA O ARRAY
        ATRIBUI OS PESOS AOS NÓS FINAIS
        CONSTROI O GRAFO
        APLICA MINMAX
        FAZ A JOGADA
        MANDA PARA O PYTHON/ARDUINO
        
        -> *TURNO DO JOGADOR*
        
                ...
        
        */
        Board board = new Board();
        Scanner s = new Scanner(System.in);
        

        int depth = 3;

        while (!board.endOfGame) {
            Game game = new Game(board);
            AlphaBeta foraNucleo = new AlphaBeta(board);
            game.getBoard().printImage();
            System.out.println();
            if(!board.getTurn()){
                try {
                    long time = System.currentTimeMillis();
                    
                    game.getHasBlackKingMoved();
                    game.getHasBlackLeftRookMoved();
                    game.getHasBlackRightRookMoved();
                    game.BlacksCastling();

                    board = foraNucleo.bestPlaying(0, depth, false);
                    long tn = System.currentTimeMillis();
                    System.out.println(tn - time);
                    Game test = new Game(board);
                    
                    test.allLegal();
                    test.isCheckMateBlack();
                    test.isCheckMateWhite();
                    test.isBlackPromotion();
                    test.isWhitePromotion();
                    System.out.println(" ");
                    
                }
                catch (BoardOutOfBoundsException b) {
                    System.err.println("\n*Fora do tabuleiro*\n");
                }

                catch (StringIndexOutOfBoundsException | UnexpectedPieceException b) {
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
                
                int[] ii = Translator.NotationChessToComputer(first.charAt(0), Character.getNumericValue(first.charAt(1)));
                int[] jj = Translator.NotationChessToComputer(second.charAt(0), Character.getNumericValue(second.charAt(1)));
                
                try {
                    game.getHasWhiteKingMoved();
                    game.getHasWhiteLeftRookMoved();
                    game.getHasWhiteRightRookMoved();
                    game.WhitesCastling();

                    game.allLegal();
                    
                    game.move(ii[0], ii[1], jj[0], jj[1]);
                    game.allLegal();
                    
                    game.isCheckMateBlack();
                    game.isCheckMateWhite();
                    
                    game.isWhitePromotion();
                    game.isBlackPromotion();

                }
                catch (BoardOutOfBoundsException b) {
                    System.err.println("\n*Fora do tabuleiro*\n");
                }
                
                catch (StringIndexOutOfBoundsException | UnexpectedPieceException b) {
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