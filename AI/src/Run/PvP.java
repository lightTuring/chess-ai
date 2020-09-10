package Run;

import java.util.*;

import Notation.*;
import Rules.Board;
import Rules.BoardOutOfBoundsException;
import Rules.Game;
import Rules.IllegalMoveException;
import Rules.UnexpectedPieceException;

public class PvP {

    public static void main(String[] args) {
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
                game.allLegal();
                game.move(ii[0], ii[1], jj[0], jj[1]);
                game.allLegal();
                game.isCheckMateBlack();
                game.isCheckMateWhite();
                game.isBlackPromotion();
                game.isWhitePromotion();
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
        game.getBoard().printImage();
        System.out.println(" ");
        System.out.println("Jogo Acabou");
        s.close();
    }
}