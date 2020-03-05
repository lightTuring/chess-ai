import java.util.ArrayList;

public class Controller {

  //A checagem de tipo de peça será realizada aqui
  public ArrayList getAllMoves(byte i, byte j, char[][] b) {

    }


  public ArrayList movePawn(byte i, byte j, char[][] b) {
    String s = "";
    ArrayList<String> moves = new ArrayList<String> ();

    if ("Implementar checagem de cor") {
      if (char[i-1][j+1] != 'O') {
        //Aqui implementar adição das coordenadas no Arraylist
      }
    }
  }



  //Controles de movimento (falta implementar a checagem de validade)

  public void move(int io, int jo, int ia, int ja, char[][] b) {
    if (b[io][jo] != 'R' && io < 8 && jo < 8){
      b[ia][ja] = b[io][jo];
      b[io][jo] = 'O';
    }
  }

  //Pegar o nome da peça
  public char getPiece(int io, int jo, char[][] b){
    String s = "";
    for (PieceList p : PieceList.values()) {
      if (b[io][jo] == p.getLetter())
      {
        s = p.getLetter();
      }
    }
    return s;
  }
}
