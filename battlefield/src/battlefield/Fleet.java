package battlefield;

import battlefield.cell.Cell;

import java.util.HashMap;
import java.util.Map;

public class Fleet {

    private final ShipValidation shipValidation = new ShipValidation();
    private final Map<TypeShip, Integer> typeCount = new HashMap<>();

    public Fleet() {
        typeCount.put(TypeShip.AIRCRAFT_CARRIER, 0);
        typeCount.put(TypeShip.DESTROYER, 0);
        typeCount.put(TypeShip.SUBMARINE, 0);
        typeCount.put(TypeShip.FRIGATE, 0);
    }

    public Ship createTypeShip(Cell[][] board, Cell[] cells) {
        if (!shipValidation.isValidShipShape(cells)) {
            System.out.println("Ошибка при введении координат");
            return null;
        }
        if (!shipValidation.canPlaceShip(board, cells)) {
            System.out.println("Нельзя ставить корабли рядом друг с другом");
            return null;
        }

        if (!shipValidation.isWithinBoard(cells)) {
            System.out.println("Нельзя поставить корабль за пределами площадки");
            return null;
        }

        TypeShip type = TypeShip.getTypeShip(cells.length);
        typeCount.put(type, typeCount.get(type) + 1);

        // если null
        if (typeCount.get(type) > type.getMaxCount()) {
            throw new IllegalStateException("Нельзя создать больше кораблей этого типа");
        }
        return new Ship(cells, type);
    }

}
