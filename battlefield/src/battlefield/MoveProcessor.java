package battlefield;

import battlefield.cell.Coordinates;

import java.util.Arrays;

public class MoveProcessor {
    Player currentPlayer;
    Player opponentPlayer;

    public MoveProcessor(Player currentPlayer, Player opponentPlayer) {
        this.currentPlayer = currentPlayer;
        this.opponentPlayer = opponentPlayer;
    }

    public boolean doMove() {
        Coordinates cr;
        System.out.println("Соперник = " + Arrays.toString(opponentPlayer.getStorageShips().getAllShips()));
        while (true) {
            cr = Input.inputCoordinates();

            if (opponentPlayer.getGameField().isRepeat(cr)) {
                System.out.println("Повторный выстрел в клетку");
            } else {
                break;
            }
        }
        boolean isHit = currentPlayer.doShot(opponentPlayer.getStorageShips(), cr);

        changeViewBoard(isHit, cr, currentPlayer, opponentPlayer);

        if (isHit) {
            opponentPlayer.getStorageShips().updateStateCellShip(cr);
        }
        if (opponentPlayer.getStorageShips().hasDeadShip()) {
            changeStateAroundShip(currentPlayer, opponentPlayer);
            opponentPlayer.getStorageShips().removeDeadShip();
        }
        return isHit;
    }

    private void changeViewBoard(boolean isHit, Coordinates cr, Player currentPlayer, Player opponentPlayer) {
        opponentPlayer.getGameField().paintShotResult(isHit, cr);
        currentPlayer.getOpponentField().paintShotResult(isHit, cr);
    }

    private void changeStateAroundShip(Player currentPlayer, Player opponentPlayer) {
        BoardUtils.paintInaccessibleCells(opponentPlayer, opponentPlayer.getGameField());
        BoardUtils.paintInaccessibleCells(opponentPlayer, currentPlayer.getOpponentField());
    }
}
