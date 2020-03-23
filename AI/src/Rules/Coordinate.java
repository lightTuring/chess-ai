package Rules;

public class Coordinate {

  private int final_i;
  private int final_j;

  public Coordinate(int final_i, int final_j){
    this.final_i = final_i;
    this.final_j = final_j;
  }
  public int getPos_i() {
	  return final_i;
  }
  public int getPos_j() {
	  return final_j;
  }
  public void printCoordinate() {
    System.out.println(this.final_i + ", " + this.final_j);
  } 
}
