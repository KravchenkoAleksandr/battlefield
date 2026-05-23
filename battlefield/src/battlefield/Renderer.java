package battlefield;

import battlefield.cell.Letter;

public class Renderer {

    public void renderBoard(Board gameBoard) {
        for (int i = 1; i <= 10; i++) {
            System.out.print("  " + i);
        }
        System.out.println();
        for (Letter letter : Letter.values()) {
            String line = getLine(gameBoard, letter.ordinal());
            System.out.println(letter + " " + line);
        }
        System.out.println();
    }

    private String getLine(Board gameBoard, int row) {
        String line = "";
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 10; j++) {
                line += " " + gameBoard.getField()[row][j].toString();
            }
        }
        return line;
    }

    public void printMsg(int total, int countTotal, int size) {
        if (countTotal == total) {
            System.out.println("Добавьте " + total + doRightMsg(size));
        } else {
            System.out.println("Добавьте " + countTotal + doRightMsg(size));
        }
    }

    private String doRightMsg(int size) {
        switch (size) {
            case 1 -> {
                return " []";
            }
            case 2 -> {
                return " [][]";
            }
            case 3 -> {
                return " [][][]";
            }
            case 4 -> {
                return " [][][][]";
            }
        }
        return "";
    }


}
