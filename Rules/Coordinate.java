public class Coordinate {
  private byte initial_i;
  private byte initial_j;
  private byte final_i;
  private byte final_j;

  public Coordinate(byte initial_i, byte initial_j, byte final_i, byte final_j){
    this.initial_i = initial_i;
    this.initial_j = initial_j;
    this.final_i = final_i;
    this.final_j = final_j;
  }
}