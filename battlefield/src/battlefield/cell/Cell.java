package battlefield.cell;

import java.util.Objects;

public class Cell {
    private State state;
    private final Coordinates coordinates;

    public Cell(Coordinates coordinates) {
        this.state = State.EMPTY;
        this.coordinates = coordinates;
    }

    public State getState() {
        return state;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getNumLetter() {
        return this.coordinates.getLetter().ordinal();
    }

    public int getFigure() {
        return this.coordinates.getFigure();
    }

    public void updateState(State state) {
        this.state = state;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;
        return state == cell.state && coordinates.equals(cell.coordinates);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(state);
        result = 31 * result + coordinates.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return state.toString();
    }
}
