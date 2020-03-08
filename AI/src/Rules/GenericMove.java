package Rules;

import java.util.ArrayList;

public class GenericMove {
    

    protected static ArrayList<Coordinate> bishopGen (int pos_i, int pos_j, Board b) throws BoardOutOfBoundsException{
        Coordinate x;
        ArrayList<Coordinate> list = new ArrayList<Coordinate>();
        for (int i = 1; (pos_i + i) < 8; i++) {
            if (pos_j + i < 8 && b.getPiece(pos_i + i, pos_j + i) == 'o') {
              x = new Coordinate(pos_i, pos_j, pos_i + i, pos_j + i);
              list.add(x);
            }
            else {
              break;
            }
          }
        for (int i = 1; (pos_i + i) < 8; i++) {
            if (pos_j - i >= 0 && b.getPiece(pos_i + i, pos_j - i) == 'o') {
              x = new Coordinate(pos_i, pos_j, pos_i + i, pos_j - i);
              list.add(x);
              }
            else {
              break;
              }
          }
        for (int i = 1; (pos_i - i) >= 0; i++) {
            if (pos_j + i < 8 && b.getPiece(pos_i - i, pos_j + i) == 'o') {
              x = new Coordinate(pos_i, pos_j, pos_i - i, pos_j + i);
              list.add(x);
            }
            else {
              break;
            }
          }
        for (int i = 1; (pos_i - i) >= 0; i++) {
            if ((pos_j - i) >= 0 && b.getPiece(pos_i - i, pos_j - i) == 'o') {
              x = new Coordinate(pos_i, pos_j, pos_i - i, pos_j - i);
              list.add(x);
            }
            else {
              break;
            }
          }
        return list;
    }

    protected static ArrayList<Coordinate> rookGen (int pos_i, int pos_j, Board b) throws BoardOutOfBoundsException{
        ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
        Coordinate x; 
        boolean pathBlocked = false;
        boolean pathBlocked2 = false;
        int t = 0;


        //Para uma fileira
        for (int j = 0; j < pos_j; j++) {
          if(b.getPiece(pos_i, j) != 'o') {
              pathBlocked = true;
              t = j;
              }
          }
        if (pathBlocked == true) {
          if (!(b.hasSameColor(pos_i, pos_j, pos_i, t))) {
            x = new Coordinate(pos_i, pos_j, pos_i, t);
            moves.add(x);
          }
          for (int j = (t+1); j < pos_j; j++) {
            x = new Coordinate(pos_i, pos_j, pos_i, j);
            moves.add(x);
           }
          }
        else {
          for (int j = 0; j < pos_j; j++) {
            x = new Coordinate(pos_i, pos_j, pos_i, j);
            moves.add(x);
            }
          }
        
        for (int j = (pos_j + 1); j < 8; j++) {
            if (b.getPiece(pos_i, j) == 'o') {
              x = new Coordinate(pos_i, pos_j, pos_i, j);
              moves.add(x);
            }
            else {
              if (b.hasSameColor(pos_i, pos_j, pos_i, j) == false) {
                x = new Coordinate(pos_i, pos_j, pos_i, j);
                moves.add(x);
              }
              break;
            }
          }
          //Para uma coluna 
          for (int i = 0; i<pos_i; i++) {
            if(b.getPiece(i, pos_j) != 'o') {
              pathBlocked2 = true;
              t = i;
              }
          }
          if (pathBlocked2 == true) {
            if (!(b.hasSameColor(pos_i, pos_j, t, pos_j))) {
              x = new Coordinate(pos_i, pos_j, t, pos_j);
              moves.add(x);
            }
            for (int i = (t+1); i < pos_i; i++) {
              x = new Coordinate(pos_i, pos_j, i, pos_j);
              moves.add(x);
            }
          }
          else {
            for (int i = 0; i < pos_i; i++) {
              x = new Coordinate(pos_i, pos_j, i, pos_j);
              moves.add(x);
              }
            }
          for (int i = (pos_i + 1); i<8; i++) {
            if (b.getPiece(i, pos_j) == 'o') {
              x = new Coordinate(pos_i, pos_j, i, pos_j);
              moves.add(x);
            }
            else {
              if(!(b.hasSameColor(pos_i, pos_j, i, pos_j))) {
                x = new Coordinate(pos_i, pos_j, i, pos_j);
                moves.add(x);
              }
              break;
            }
          }
          return moves;
    }
}