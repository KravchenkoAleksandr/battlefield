package battlefield.cell;

public class Coordinates implements Comparable<Coordinates> {
    private final Letter letter;
    private final Integer figure;

    public Coordinates(Letter letter, Integer figure) {
        this.letter = letter;
        this.figure = figure;
    }

    public Integer getFigure() {
        return figure;
    }

    public Letter getLetter() {
        return letter;
    }

    public boolean isWithinBounds() {
        int l = this.letter.ordinal();
        int f = this.figure;

        if ((l < 0) || (l > 9)) return false;
        if ((f < 0) || (f > 9)) return false;

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;
        return letter == that.letter && figure.equals(that.figure);
    }

    @Override
    public int hashCode() {
        int result = letter.hashCode();
        result = 31 * result + figure.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return letter + "" + figure;
    }


    @Override
    public int compareTo(Coordinates o) {
        int result = this.letter.compareTo(o.letter);

        if (result != 0) {
            return result;
        }

        return Integer.compare(this.figure, o.figure);
    }
}
