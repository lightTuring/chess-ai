package Rules;

public class Position {

    private char position[][];

    public Position (Board b) {
        position = b.getBoard();
    }

    public char[][] getPosition() {
        return position;
    }

    public void changePosition (Board b) {
        b.setBoard(position);
    }

}

