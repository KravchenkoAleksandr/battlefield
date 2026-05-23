package battlefield;

public enum TypeShip {
    AIRCRAFT_CARRIER(4, "Авивносец", 1),
    DESTROYER(3, "Эсминец", 2),
    SUBMARINE(2, "Подводная лодка", 3),
    FRIGATE(1, "Фрегат", 4);

    private int size;
    private String name;
    private int maxCount;

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public int getMaxCount() {
        return maxCount;
    }

    TypeShip(int len, String name, int maxCount) {
        this.size = len;
        this.name = name;
        this.maxCount = maxCount;
    }

    public static TypeShip getTypeShip(int len) {
        for (TypeShip type : TypeShip.values()) {
            if (type.size == len) {
                return type;
            }
        }
        return null;
    }


}
