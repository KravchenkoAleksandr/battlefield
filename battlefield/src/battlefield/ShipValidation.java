package battlefield;

import battlefield.cell.Cell;
import battlefield.cell.State;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class ShipValidation {

    public boolean isValidShipShape(Cell[] shipField) {
        Arrays.sort(shipField, Comparator.comparing(Cell::getCoordinates));

        for (int currCell = 1; currCell < shipField.length; currCell++) {

            if (shipField[currCell].getCoordinates().getLetter() != (shipField[currCell - 1].getCoordinates().getLetter()) &&
                    !shipField[currCell].getCoordinates().getFigure().equals(shipField[currCell - 1].getCoordinates().getFigure())) {
                return false;//разные буквы и цифры
            }

            if (shipField[currCell].getCoordinates().getLetter() == (shipField[currCell - 1].getCoordinates().getLetter())) {
                // если по горизонтали разные буквы= неверно
                int currFigure = shipField[currCell].getCoordinates().getFigure();

                if (currFigure - shipField[currCell - 1].getCoordinates().getFigure() != 1) {
                    return false;// буквы не подряд
                }
            } else if (shipField[currCell].getCoordinates().getFigure().equals(shipField[currCell - 1].getCoordinates().getFigure())) {
                // если по вертикали разные цифры = неверно
                int currLetter = shipField[currCell].getCoordinates().getLetter().ordinal();

                if (currLetter - shipField[currCell - 1].getCoordinates().getLetter().ordinal() != 1) {
                    return false;// цифры не подряд
                }
            }
        }
        return true;
    }

    public boolean canPlaceShip(Cell[][] board, Cell[] shipCells) {
        for (int i = 0; i < shipCells.length; i++) {

            if (i < shipCells.length - 1) {
                Cell currentCellShip = shipCells[i];
                Cell nextCellShip = shipCells[i + 1];

                Cell[] neighbors = getNeighbors(board, currentCellShip);

                for (Cell currentNeighbor : neighbors) {

                    if (currentNeighbor.equals(nextCellShip)) continue;

                    if (isNotAvailableNeighbors(board, currentNeighbor)) return false;
                }
            } else {
                Cell[] neighbors = getNeighbors(board, shipCells[i]);
                for (Cell currentNeighbor : neighbors) {
                    if (isNotAvailableNeighbors(board, currentNeighbor)) return false;
                }
            }
        }
        return true;
    }

    private static boolean isNotAvailableNeighbors(Cell[][] board, Cell currentNeighbor) {
        int letter = currentNeighbor.getCoordinates().getLetter().ordinal();
        int figure = currentNeighbor.getCoordinates().getFigure();

        return board[letter][figure].getState() == State.SHIP;
    }


    public static Cell[] getNeighbors(Cell[][] board, Cell cell) {
        int rows = board.length;
        int cols = Board.getMaxColumns();

        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        int maxElements = directions.length;
        Cell[] neighbors = new Cell[maxElements];

        for (int i = 0; i < directions.length; i++) {

            int numberLetter = cell.getNumLetter() + directions[i][0];
            int newFigure = cell.getFigure() + directions[i][1];

            if ((numberLetter >= 0 && numberLetter < rows) && (newFigure >= 0 && newFigure < cols)) {
                neighbors[i] = new Cell(board[numberLetter][newFigure].getCoordinates());
            }
        }

        return doWithoutNull(neighbors);
    }

    private static Cell[] doWithoutNull(Cell[] neighbors) {
        return Arrays.stream(neighbors).filter(Objects::nonNull).toArray(Cell[]::new);
    }

    public boolean isWithinBoard(Cell[] cells) {
        for (Cell cell : cells) {
            if (cell.getCoordinates().isWithinBounds()) {
                return true;
            }
        }
        return false;
    }
}
