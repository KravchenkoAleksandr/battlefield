package battlefield;

import battlefield.cell.Cell;
import battlefield.cell.State;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BoardUtils {

    public static Cell[] getInaccessibleCells(Player player) {
        Ship ship = player.getStorageShips().getDeadShip();
        Set<Cell> storage = new HashSet<>();
        Cell[] neighbors;
        if (ship != null) {
            for (Cell cell : ship.getCells()) {
                neighbors = ShipValidation.getNeighbors(player.getGameField().getField(), cell);
                storage.addAll(Arrays.asList(neighbors));
            }
        }
        return storage.toArray(new Cell[0]);
    }

    public static void paintInaccessibleCells(Player player, Board board) {
        Cell[] inaccessibleCells = BoardUtils.getInaccessibleCells(player);
        for (Cell cell : inaccessibleCells) {
            int l = cell.getCoordinates().getLetter().ordinal();
            int f = cell.getCoordinates().getFigure();

            if (board.getField()[l][f].getState() == State.MISS) continue;

            if (cell.equals(board.getField()[l][f])) {
                board.getField()[l][f].updateState(State.NOT_AVAILABLE);
            }
        }
    }
}
