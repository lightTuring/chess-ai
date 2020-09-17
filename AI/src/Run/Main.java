package Run;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
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

    // BEGIN - RECEBE A MSG DO ARDUINO

    final int PORT = 5000;
    ServerSocket server;
    PrintWriter escritor;
    
    public Main() {
        
        try {
            server = new ServerSocket(PORT);
            while (true) {
                Socket socket = server.accept();
                new Thread(new RecebeACall(socket)).start();
                escritor = new PrintWriter(socket.getOutputStream());
            }
        } catch (IOException e1) {
            System.err.println("Não conseguiu inicializar o servidor");
        }

    }

    private void SendMovement(String txt){
        try{
            escritor.println(txt);
            escritor.flush();
        }catch (Exception e) {
            System.err.println("Não conseguiu enviar o movimento");
        }
    }

    private class RecebeACall implements Runnable {
        Scanner leitor;

        public RecebeACall(Socket socket) {

            try {
                leitor = new Scanner(socket.getInputStream());
            } catch (Exception e) {
                System.err.println("Não conseguiu inicializar a escuta");
            }
        }

        public void run() {
            String msgBot;
            try {
                while ((msgBot = leitor.nextLine()) != null) {
                    System.out.println(msgBot);
                }
            } catch (Exception e) {
                System.err.println("Não conseguiu rodar a escuta");
            }
        }

    }
    //END - RECEBE A MSG DO ARDUINO

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