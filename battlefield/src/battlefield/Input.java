package battlefield;

import battlefield.cell.Coordinates;
import battlefield.cell.Letter;

import java.util.Scanner;

public class Input {

    private static final Scanner sc = new Scanner(System.in);

    public static Coordinates inputCoordinates() {
        char fig2;
        while (true) {
            System.out.println("Введите координаты,например, А3");

            String line = sc.nextLine().trim().toUpperCase();

            String reg = "^[a-jA-J0-9]+$";
            if (!line.matches(reg)) {
                continue;
            }

            if (line.length() > 3 || line.length() < 2) {
                continue;
            }
            char let = line.charAt(0);
            char fig1 = line.charAt(1);

            if (!Character.isLetter(let)) {
                continue;
            }

            if (!Character.isDigit(fig1)) {
                continue;
            }

            Letter letter = Letter.getLetter(let);
            if (line.length() == 2) {
                int figure = Character.getNumericValue(fig1);
                return new Coordinates(letter, figure - 1);
            }
            fig2 = line.charAt(2);
            if (!Character.isDigit(fig2)) {
                continue;
            }
            String str = String.valueOf(fig1) + fig2;
            int figure = Integer.parseInt(str);
            if (figure > 10) {
                continue;
            }
            return new Coordinates(letter, figure - 1);
        }
    }

    public static String input() {
        return sc.nextLine().trim();
    }
}

