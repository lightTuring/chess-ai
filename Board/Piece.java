package rules;

public class Piece {

    byte[][] b;

    Piece (byte [][] board){
      b = board;
    }

    //Mover a peça alterando o valor do array
    public void move(int io, int jo, int ia, int ja) {
      if (b[io][jo] != 0 && io < 8 && jo < 8){
        b[ia][ja] = b[io][jo];
        b[io][jo] = 0;
      }
    }

    //Pegar o nome da peça
    public String getPiece(){
      
    }



    }
}
