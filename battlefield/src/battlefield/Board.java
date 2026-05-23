package battlefield;

import battlefield.cell.Cell;
import battlefield.cell.Coordinates;
import battlefield.cell.Letter;
import battlefield.cell.State;

public class Board {

    private static final int MAX_ROW = 10;
    private static final int MAX_COLUMNS = 10;

    private final Cell[][] field = new Cell[MAX_ROW][MAX_COLUMNS];

    public Board() {
    }

    public static int getMaxColumns() {
        return MAX_COLUMNS;
    }

    public Cell[][] getField() {
        /*Cell[][] copy = new Cell[board.length][];
        for (int i = 0; i < board.length; i++) {
            copy[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return copy;*/
        return field;
    }

    public void createBoard() {
        for (Letter letter : Letter.values()) {
            for (int i = 0; i < MAX_COLUMNS; i++) {
                field[letter.ordinal()][i] = new Cell(new Coordinates(letter, i));
            }
        }
    }

    public void add(Cell[] cells) {
        for (Cell cell : cells) {
            if (cell.getCoordinates() != null) {
                int letter = cell.getCoordinates().getLetter().ordinal();
                int figure = cell.getCoordinates().getFigure();

                Cell cellBoard = field[letter][figure];

                if (cellBoard.getState() == State.EMPTY) {
                    cellBoard.updateState(State.SHIP);
                }
            } else {
                System.out.println("Cell null");
                return;
            }
        }
    }

    public void paintShotResult(boolean result, Coordinates coordinates) {
        int letter = coordinates.getLetter().ordinal();
        int figure = coordinates.getFigure();
        if (result) {
            field[letter][figure].updateState(State.HIT);
        } else {
            field[letter][figure].updateState(State.MISS);
        }
    }

    public boolean isRepeat(Coordinates coordinates) {
        int letter = coordinates.getLetter().ordinal();
        int figure = coordinates.getFigure();
        return field[letter][figure].getState() == State.MISS || field[letter][figure].getState() == State.HIT;
    }
}

