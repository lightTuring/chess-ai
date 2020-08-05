package Run;

import java.util.Scanner;

import Notation.Annotation;
import Rules.Board;
import Rules.BoardOutOfBoundsException;
import Rules.Coordinate;
import Rules.Game;
import Rules.IllegalMoveException;
import Rules.UnexpectedPieceException;

//rodar em um while
public class Main {
    public static void main(String[] args) throws UnexpectedPieceException {
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
            String initial = s.nextLine();
            String destiny = s.nextLine();
            Annotation.putMovement(initial);
            Coordinate i = Annotation.getLastMovementMatrixCoordinate();
            Annotation.putMovement(destiny);
            Coordinate f = Annotation.getLastMovementMatrixCoordinate();
            try {
                game.move(i.getPos_i(), i.getPos_j(), f.getPos_i(), f.getPos_j());
            }
            catch (BoardOutOfBoundsException board) {
                System.out.println("Fora do tabuleiro");
            }
            catch (IllegalMoveException illegal) {
                System.out.println("Movimento ilegal");
            }

        }
        s.close();
    }
}