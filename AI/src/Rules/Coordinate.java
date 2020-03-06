package Rules;

public class Coordinate {
  protected int initial_i;
  protected int initial_j;
  protected int final_i;
  protected int final_j;

  public Coordinate(int initial_i, int initial_j, int final_i, int final_j){
    this.initial_i = initial_i;
    this.initial_j = initial_j;
    this.final_i = final_i;
    this.final_j = final_j;
  }
}
