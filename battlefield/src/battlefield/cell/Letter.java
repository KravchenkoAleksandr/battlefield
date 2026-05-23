package battlefield.cell;

public enum Letter {
    A, B, C, D, E, F, G, H, I, J;

    public static Letter getLetter(char c) {
        try {
            return Letter.valueOf(String.valueOf(c).toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
