package battlefield;

import battlefield.cell.Cell;
import battlefield.cell.Coordinates;
import battlefield.cell.State;

import java.util.Arrays;

public class Ship {

    private final Cell[] cells;
    private final TypeShip typeShip;

    public Ship(Cell[] ship, TypeShip typeShip) {
        this.cells = ship;
        this.typeShip = typeShip;
    }

    public Cell[] getCells() {
        return Arrays.copyOf(cells, cells.length);
    }

    public TypeShip getTypeShip() {
        return typeShip;
    }

    public boolean hasUpdate(Coordinates cr) {
        for (Cell cell : cells) {
            if (cell.getCoordinates().equals(cr)) {
                cell.updateState(State.HIT);
                return true;
            }
        }
        return false;
    }

    public boolean isDead() {
        for (Cell field : cells) {
            if (field.getState() == State.SHIP) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return Arrays.toString(cells);
    }
}
