package battlefield.cell;

public enum State {
   /* EMPTY("~ "),
    SHIP("[]"),
    MISS("o "),
    HIT("#"),
    NOT_AVAILABLE("x");*/

    EMPTY("~ "),
    SHIP("[]"),
    MISS("o "),
    HIT("x "),
    NOT_AVAILABLE(". ");


    private final String sign;

    State(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return sign;
    }
}


