package Run;

import java.util.*;

import Notation.Annotation;
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

        Scanner s = new Scanner(System.in);
        Game game = new Game(new Board());

        while (!game.hasEnded()) {
            game.getBoard().printImage();
            System.out.println();
            int ii = s.nextInt();
            int jj = s.nextInt();
            int iff = s.nextInt();
            int jf = s.nextInt();
            
            try {
                game.setMoves();
                game.move(ii, jj, iff, jf);
                game.isCheckMateBlack();
                game.isCheckMateWhite();

            }
            catch (BoardOutOfBoundsException board) {
                System.err.println("\n*Fora do tabuleiro*\n");
            }
            
            catch (UnexpectedPieceException board) {
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