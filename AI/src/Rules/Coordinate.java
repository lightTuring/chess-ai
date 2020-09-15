package Rules;

public class Coordinate implements Cloneable {

    private int pos_i;
    private int pos_j;

    public Coordinate(int pos_i, int pos_j){
        this.pos_i = pos_i;
        this.pos_j = pos_j;
    }
    public Coordinate clone() {
        Coordinate c = new Coordinate(this.pos_i, this.pos_j);
        return c;
    }
    public int getPos_i() {
        return pos_i;
    }
    public int getPos_j() {
        return pos_j;
    }
    public void printCoordinate() {
        System.out.println(this.pos_i + ", " + this.pos_j);
    } 
    public String toString() {
        return(this.pos_i + " " + this.pos_j);
    }

    public boolean equals(Coordinate c) {
        if (c.getPos_i() == pos_i && c.getPos_j() == pos_j) {
            return true;
        }else {
            return false;
        }
    }
}
