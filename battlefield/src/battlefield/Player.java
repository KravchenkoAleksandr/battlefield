package battlefield;

import battlefield.cell.Cell;
import battlefield.cell.Coordinates;

public class Player {

    private final Board gameField = new Board();
    private final Board opponentField = new Board();
    private final StorageShips storageShips = new StorageShips();
    private final Fleet fleet = new Fleet();
    private String name;
    private boolean hasTurn;


    public Player() {
        gameField.createBoard();
        opponentField.createBoard();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Board getGameField() {
        return gameField; // нужно вернуть дубликат?
    }

    public Board getOpponentField() {
        return opponentField;// нужно вернуть дубликат?
    }

    public StorageShips getStorageShips() {
        return storageShips;
    }

    /*public Ship[] getAllShips() {

    }*/

    public boolean isHasTurn() {
        return hasTurn;
    }

    public void setHasTurn(boolean hasTurn) {
        this.hasTurn = hasTurn;
    }

    public boolean addShip(Cell[] cells) {
        Ship ship = fleet.createTypeShip(gameField.getField(), cells);
        if (ship != null) {
            storageShips.addShipStorage(ship);
            gameField.add(cells);
            return true;
        }
        return false;
    }

    public boolean doShot(StorageShips storageShips, Coordinates coordinates) {
        for (int i = 0; i < storageShips.getSize(); i++) {
            for (int j = 0; j < storageShips.getAllShips()[i].getCells().length; j++) {
                if (storageShips.getAllShips()[i].getCells()[j].getCoordinates().equals(coordinates)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isLost() {
        return storageShips.getSize() <= 0;
    }
}
