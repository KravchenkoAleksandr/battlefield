package battlefield;

import battlefield.cell.Cell;
import battlefield.cell.Coordinates;
import battlefield.cell.State;

public class StorageShips {
    private static final int MAX_SHIPS = 10;

    private int size;

    private final Ship[] allShips = new Ship[MAX_SHIPS];


    public StorageShips() {
    }

    public int getSize() {
        return size;
    }

    public Ship[] getAllShips() {
        return allShips;
    }


    public void addShipStorage(Ship ship) {
        for (Cell cell : ship.getCells()) {
            cell.updateState(State.SHIP);
        }
        allShips[size++] = ship;
    }


    public void updateStateCellShip(Coordinates cr) {
        for (int i = 0; i < size; i++) {
            boolean hasUpdate = allShips[i].hasUpdate(cr);
            if (hasUpdate) return;
        }
    }

    public boolean hasDeadShip() {
        for (int i = 0; i < size; i++) {
            if (allShips[i].isDead()) {
                return true;
            }
        }
        return false;
    }

    public Ship getDeadShip() {
        for (int i = 0; i < size; i++) {
            if (allShips[i].isDead()) {
                return allShips[i];
            }
        }
        return null;
    }

    public void removeDeadShip() {
        for (int i = 0; i < size; i++) {
            if (allShips[i].isDead()) {
                size--;
                int len = size - i;
                System.arraycopy(allShips, i + 1, allShips, i, len);
                int currentLast = allShips.length - size;
                allShips[allShips.length - currentLast] = null;
                return;
                /*System.out.println(Arrays.toString(allShips));
                System.out.println(allShips.length);
                System.out.println(size);*/
            }
        }
    }
}
