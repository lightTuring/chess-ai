package Rules;

import java.util.ArrayList;

public class GenericMove {
    

    protected static ArrayList<Coordinate> bishopGen (byte pos_i, byte pos_j, Board b) throws BoardOutOfBoundsException{
        Coordinate x;
        ArrayList<Coordinate> list = new ArrayList<Coordinate>();
        for (byte i = 1; (pos_i + i) < 8; i++) {
            if (pos_j + i < 8 && b.getPiece(pos_i + i, pos_j + i) == 'o') {
              x = new Coordinate(pos_i, pos_j, pos_i + i, pos_j + i);
              list.add(x);
            }
            else {
              break;
            }
          }
        for (byte i = 1; (pos_i + i) < 8; i++) {
            if (pos_j - i >= 0 && b.getPiece(pos_i + i, pos_j - i) == 'o') {
              x = new Coordinate(pos_i, pos_j, pos_i + i, pos_j - i);
              list.add(x);
              }
            else {
              break;
              }
          }
        for (byte i = 1; (pos_i - i) >= 0; i++) {
            if (pos_j + i < 8 && b.getPiece(pos_i - i, pos_j + i) == 'o') {
              x = new Coordinate(pos_i, pos_j, pos_i - i, pos_j + i);
              list.add(x);
            }
            else {
              break;
            }
          }
        for (byte i = 1; (pos_i - i) >= 0; i++) {
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

    protected static ArrayList<Coordinate> rookGen ()
}