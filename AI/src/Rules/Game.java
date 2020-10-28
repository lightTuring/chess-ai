package Rules;

import java.util.Iterator;
import java.util.LinkedList; 

@SuppressWarnings("unchecked")
public class Game {
    private Board board;
    // true = brancas; false = pretas.
    private LinkedList<Coordinate>[][] stateBoard =  new LinkedList[8][8];
    private LinkedList<Coordinate>[][] passBoard = new LinkedList[8][8];

    public Game(Board board) {
        this.board = board;
        for (int i = 0; i<8; i++) {
            for (int j = 0; j<8; j++) {
                stateBoard[i][j] = new LinkedList<Coordinate>();
                passBoard[i][j] = new LinkedList<Coordinate>();
            }
        }
    }
    public Game clone() throws CloneNotSupportedException {
        Board b = this.board.clone();
        Game game = new Game(b);
        for (int i= 0; i<8; i++) {
            for (int j= 0; j<8; j++) {
                game.stateBoard[i][j] = (LinkedList<Coordinate>)this.stateBoard[i][j].clone();
                game.passBoard[i][j] = (LinkedList<Coordinate>)this.passBoard[i][j].clone();
            }
        }
        return game;
    }

    public boolean equals (Game g) {
        if (this.board.equals(g.board)) {
            return true;
        }
        return false;
    }

    public Board getBoard() {
        return board;
    }

    public LinkedList<Coordinate>[][] getPassBoard() {
        return (passBoard);
    }


    public boolean hasEnded() {
        return board.endOfGame;
    }

    public void move(int i, int j, int final_i, int final_j)
            throws IllegalMoveException, BoardOutOfBoundsException, UnexpectedPieceException {
        Coordinate c = new Coordinate(final_i, final_j);
               
        if (isLegal(i, j, c) && (!board.isBlack(i,j) == board.turn || board.isWhite(i,j) == board.turn)) {
            if(i == 7 && j == 4 && final_i == 7 && final_j == 2){
                board.changePos(i, j, c);
                board.changePos(7, 0, new Coordinate(7, 3));
                board.turn = !board.turn;
                
                board.hasWhiteCastled = true;
            }else if(i == 7 && j == 4 && final_i == 7 && final_j == 6){
                board.changePos(i, j, c);
                board.changePos(7, 7, new Coordinate(7, 5));
                board.hasWhiteCastled = true;
                board.turn = !board.turn;
                
            }else if(i == 0 && j == 4 && final_i == 0 && final_j == 2){
                board.changePos(i, j, c);
                board.changePos(0, 0, new Coordinate(0, 3));
                board.hasBlackCastled = true;
                board.turn = !board.turn;
                
            }else if(i == 0 && j == 4 && final_i == 0 && final_j == 6){
                board.changePos(i, j, c);
                board.changePos(0, 7, new Coordinate(0, 5));
                board.hasBlackCastled = true;
                board.turn = !board.turn;
                
            }else{
                board.changePos(i, j, c);
                board.turn = !board.turn;
                
            }
            board.setLastMove(i, j, final_i, final_j);
        }
        else if (isEnPassantLegal(i, j, c) && board.isWhite(i,j) == board.turn) {
            board.changePos(i, j, c);
            board.eliminate(i, c.getPos_j());
            board.turn = !board.turn;
            
        }
        else {
            throw new IllegalMoveException("Movimento ilegal");
        }
        
    }

    public void allLegal() throws BoardOutOfBoundsException, UnexpectedPieceException, IllegalMoveException, CloneNotSupportedException {
		LinkedList<Coordinate>[][] list = Controller.uncheckedMoves(board);
		for (int i = 0; i<8; i++) {
			for (int j = 0; j<8; j++){
                if (board.isAPiece(i, j) && (board.isWhite(i, j) == board.turn)) {
                    LinkedList<Coordinate> teste = list[i][j];
                    LinkedList<Coordinate> letra = (LinkedList<Coordinate>)teste.clone();
                    for (Coordinate c : teste) {
                        Board copy = board.clone();
                        copy.changePos(i, j, c);
                        if (board.turn == true) {
                            if (board.hasSameColor(i, j, c.getPos_i(), c.getPos_j())) {
                                letra.remove(c);
                            }
                            else if (copy.isWhiteKingInCheck()) {
                                letra.remove(c);
                            } 
                            
                        }
                        if (board.turn == false) {
                            if (board.hasSameColor(i, j, c.getPos_i(), c.getPos_j())) {
                                letra.remove(c);
                            }
                            if (copy.isBlackKingInCheck()) {
                                letra.remove(c);
                            } 
                            
                        }
                    }
                    list[i][j] = letra;
                }
                else {list[i][j] = new LinkedList<Coordinate>();}
            }
		}
        stateBoard = list;
        if (board.turn) {
            WhitesCastling();
        }
        else {
            BlacksCastling();
        }
        legalEnPassant();
        getHasBlackKingMoved();
        getHasBlackLeftRookMoved();
        getHasBlackRightRookMoved();
        getHasWhiteKingMoved();
        getHasWhiteLeftRookMoved();
        getHasWhiteRightRookMoved();
        
    }
    
    private void legalEnPassant() throws BoardOutOfBoundsException, CloneNotSupportedException, UnexpectedPieceException,
            IllegalMoveException {
        if (board.turn) {
            for (int j = 0; j<8; j++) {
                passBoard[3][j] = Controller.pass(board, 3, j)[0];
            }
            for (int j = 0; j<8; j++) {
                for (Coordinate c : passBoard[3][j]) {
                    Board copy = board.clone();
                    copy.changePos(3, j, c);
                    copy.eliminate(3, c.getPos_j());
                    if (copy.isWhiteKingInCheck()) {
                        passBoard[3][j].remove(c);
                    }
                }
            }
        }
        else {
            for (int j = 0; j<8; j++) {
                passBoard[4][j] = Controller.pass(board, 4, j)[0];
            }
            for (int j = 0; j<8; j++) {
                for (Coordinate c : passBoard[4][j]) {
                    Board copy = board.clone();
                    copy.changePos(4, j, c);
                    copy.eliminate(4, c.getPos_j());
                    if (copy.isBlackKingInCheck()) {
                        passBoard[4][j].remove(c);
                    }
                }
            }
        
        }

    }

    public boolean isLegal(int i, int j, Coordinate c) throws IllegalMoveException, BoardOutOfBoundsException, UnexpectedPieceException {
        LinkedList<Coordinate> list = stateBoard[i][j];
        for (Coordinate x : list) {
            if (x.equals(c)) {
                return true;
            }
        }
        
        return false;
    }

    public boolean isEnPassantLegal(int i, int j, Coordinate c) {
        if (i == 3 && board.turn) {
            LinkedList<Coordinate> b = passBoard[i][j];
            for (Coordinate x : b) {
                if (x.equals(c)) {
                    return true;
                }
            }
        }
        else if (i == 4 && !board.turn) {
            LinkedList<Coordinate> b = passBoard[i][j];
            for (Coordinate x : b) {
                if (x.equals(c)) {
                    return true;
                }
            }
        }
        return false;
    }

    public LinkedList<Coordinate>[][] getStateBoard() {
        return stateBoard;
    }

    public void isCheckMateWhite() throws IllegalMoveException, BoardOutOfBoundsException, UnexpectedPieceException,
            CloneNotSupportedException {
        int legal = 0;        
        if (board.isWhiteKingInCheck()) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Iterator<Coordinate> x = stateBoard[i][j].iterator();
                    while (x.hasNext() && legal == 0) {
                        Board copy = board.clone();
                        Coordinate c = x.next();
                        copy.changePos(i, j, c);
                        if (!copy.isWhiteKingInCheck()) {
                            legal++;
                        }
                        
                    }

                    if (legal != 0) {
                        break;
                    }
                }

                if (legal != 0) {
                    break;
                }
            }

            if (legal == 0) {
                board.endOfGame = true;
                board.isCheckmateWhite = true;
            }
        }
        
    }

    public void isCheckMateBlack() throws IllegalMoveException, BoardOutOfBoundsException, UnexpectedPieceException, CloneNotSupportedException {
        int legal = 0;
        
        
        if (board.isBlackKingInCheck()) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Iterator<Coordinate> x = stateBoard[i][j].iterator();
                    while (x.hasNext() && legal == 0 && board.isBlack(i,j)) {
                        Board copy = board.clone();
                        Coordinate c = x.next();
                        copy.changePos(i, j, c);
                        if (!copy.isBlackKingInCheck()) {
                            legal++;
                        }
                        
                    }

                    if (legal != 0) {
                        break;
                    }
                }

                if (legal != 0) {
                    break;
                }
            }

            if (legal == 0) {
                board.endOfGame = true;
                board.isCheckmateBlack = true;
            }
        }
    }
    
    public void isBlackPromotion(){
        char[][] ourBoard = board.getBoard();
        for(int i=0;i<ourBoard[7].length;i++){
            if(ourBoard[7][i] == 'p'){
                ourBoard[7][i] = 'q';
            }
        }
    }
    public void isWhitePromotion(){
        char[][] ourBoard = board.getBoard();
        for(int i=0;i<ourBoard[0].length;i++){
            if(ourBoard[0][i] == 'P'){
                ourBoard[0][i] = 'Q';
            }
        }
        
    }
    public void getHasBlackKingMoved(){
        char[][] ourBoard = board.getBoard();
        if(ourBoard[0][4] == 'o'){
            board.hasBlackKingMoved = true;
        }
    }
    public void getHasWhiteKingMoved(){
        char[][] ourBoard = board.getBoard();
        if(ourBoard[7][4] == 'o'){
            board.hasWhiteKingMoved = true;
        }
    }
    public void getHasWhiteLeftRookMoved(){
        char[][] ourBoard = board.getBoard();
        if(ourBoard[7][0] == 'o'){
            board.hasLeftWhiteRookMoved = true;
        }
    }
    public void getHasWhiteRightRookMoved(){
        char[][] ourBoard = board.getBoard();
        if(ourBoard[7][7] != 'T'){
            board.hasRightWhiteRookMoved = true;
        }
    }
    public void getHasBlackLeftRookMoved(){
        char[][] ourBoard = board.getBoard();
        if(ourBoard[0][0] != 't'){
            board.hasLeftBlackRookMoved = true;
        }
    }
    public void getHasBlackRightRookMoved(){
        char[][] ourBoard = board.getBoard();
        if(ourBoard[0][7] == 'o'){
            board.hasRightBlackRookMoved = true;
        }
    }
    public void BlacksCastling() throws BoardOutOfBoundsException, UnexpectedPieceException, IllegalMoveException {
        if(!board.hasBlackKingMoved && !board.hasLeftBlackRookMoved && (board.getPiece(0,3) == 'o') && (board.getPiece(0,2) == 'o') && (board.getPiece(0,1) == 'o')){
            Coordinate[] c = new Coordinate[2];
            c[0] = new Coordinate(0, 3);
            c[1] = new Coordinate(0, 2);
            if (Controller.isSquareAttacked(c, board, board.turn)) {
                stateBoard[0][4].add(new Coordinate(0, 2));
                //stateBoard[0][0].add(new Coordinate(0, 3));
            }
        }
        if(!board.hasBlackKingMoved && !board.hasRightBlackRookMoved && (board.getPiece(0,5) == 'o') && (board.getPiece(0,6) == 'o')){
            Coordinate[] c = new Coordinate[2];
            c[0] = new Coordinate(0, 5);
            c[1] = new Coordinate(0, 6);
            if (Controller.isSquareAttacked(c, board, board.turn)) {
                stateBoard[0][4].add(new Coordinate(0, 6));
                //stateBoard[0][7].add(new Coordinate(0, 5)); 
            }
        }
    }
    public void WhitesCastling() throws BoardOutOfBoundsException, UnexpectedPieceException, IllegalMoveException {
        if(!board.hasWhiteKingMoved && !board.hasLeftWhiteRookMoved && (board.getPiece(7,3) == 'o') && (board.getPiece(7,2) == 'o') && (board.getPiece(7,1) == 'o')){
            Coordinate[] c = new Coordinate[2];
            c[0] = new Coordinate(7, 3);
            c[1] = new Coordinate(7, 2);
            if (Controller.isSquareAttacked(c, board, board.turn)) {
                stateBoard[7][4].add(new Coordinate(7, 2));
                //stateBoard[7][0].add(new Coordinate(7, 3));
            }
        }
        if(!board.hasWhiteKingMoved && !board.hasRightWhiteRookMoved && (board.getPiece(7,5) == 'o') && (board.getPiece(7,6) == 'o')) {
            Coordinate[] c = new Coordinate[2];
            c[0] = new Coordinate(7, 5);
            c[1] = new Coordinate(7, 6);
            if (Controller.isSquareAttacked(c, board, board.turn)) {
                stateBoard[7][4].add(new Coordinate(7, 6));
                //stateBoard[7][7].add(new Coordinate(7, 5));
            }
        }

    }
}