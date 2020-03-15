package Rules;

public class Coordinate {

  protected int final_i;
  protected int final_j;

  public Coordinate( int final_i, int final_j){
    this.final_i = final_i;
    this.final_j = final_j;
  }
  public void printCoordinate() {
    System.out.println(this.final_i + ", " + this.final_j);
  } 
}
