package Run;

import java.util.*;

import Notation.*;
import Rules.Board;
import Rules.BoardOutOfBoundsException;
import Rules.Coordinate;
import Rules.Game;
import Rules.IllegalMoveException;
import Rules.UnexpectedPieceException;

//rodar em um while
public class Main {
    public static void main(String[] args) throws UnexpectedPieceException, IllegalMoveException {
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
        Game game = new Game(board);

        while (!game.hasEnded()) {
            game.getBoard().printImage();
            System.out.println();
            System.out.print("Peça da posição.: ");
            String first = s.nextLine();
            System.out.print("Para.: ");
            String second = s.nextLine();
            
            int[] ii = Translator.NotationChessToComputer(first.charAt(0), Character.getNumericValue(first.charAt(1)));
            int[] jj = Translator.NotationChessToComputer(second.charAt(0), Character.getNumericValue(second.charAt(1)));
            
            try {
                game.setMoves();
<<<<<<< HEAD
                game.move(ii[0], ii[1], jj[0], jj[1]);
                board.buildMark();
                board.isCheckBlackDFS(new Coordinate(0, 4), new Coordinate(0, 4));//POS DO REI
                if(board.getIfIsCheckInBlack()) System.out.println("DEU XEQUE KCT!");
=======
                game.move(ii[0], ii[1], jj[0], jj[1]);/*
                board1.buildMark();
                if(board1.isCheckBlackDFS(new Coordinate(0, 4), new Coordinate(0, 4))) System.out.println("DEU CHEQUE KCT!");*/
>>>>>>> 93a8ba9d0485d25084758c0114cce6fa08eb9117
                game.isCheckMateBlack();
                game.isCheckMateWhite();
                
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

        }
        System.out.println("Jogo Acabou");
        s.close();
    }
}