package Rules;

public class Position {

    private char position[][];

    public Position (Board b) {
        position = b.getBoard();
    }

    public char[][] getPosition() {
        return position;
    }

}

