package rules;

public class Piece {


    //Mover a peça alterando o valor do array
    public void move(int io, int jo, int ia, int ja, byte[][] b) {
      if (b[io][jo] != 0 && io < 8 && jo < 8){
        b[ia][ja] = b[io][jo];
        b[io][jo] = 0;
      }
    }

    //Pegar o nome da peça
    public String getPiece(int io, int jo, byte[][] b){
      String s = "";
      for (PieceList p : PieceList.values()) {
        if (b[io][jo] == p.getNumber())
        {
          s = p.getName();

        }
      }
      return s;
    }
}
