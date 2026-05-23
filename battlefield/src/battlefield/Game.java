package battlefield;

import battlefield.cell.Cell;
import battlefield.cell.Coordinates;

import java.util.Random;

public class Game {
    Renderer renderer;
    Player player1 = new Player();
    Player player2 = new Player();

    public Game(Renderer renderer) {
        this.renderer = renderer;
    }

    public void game() {
        inputNames();
        createFleetsPlayers();
        createFleet(player1);
        createFleet(player2);
        chooseFirstMove();
        while (true) {
            if (player1.isHasTurn()) {
                gameProcess(player1, player2);
            } else {
                gameProcess(player2, player1);
            }
            if (player1.isLost() || player2.isLost()) {
                break;
            }
        }
    }

    private void inputNames() {
        System.out.println("Введите имя первого игрока");
        String name1 = Input.input();
        player1.setName(name1);
        System.out.println("Введите имя второго игрока");
        String name2 = Input.input();
        player2.setName(name2);
    }

    private void chooseFirstMove() {
        Random r = new Random();
        while (true) {
            int p1 = r.nextInt(0, 10);
            int p2 = r.nextInt(0, 10);

            if (p1 == p2) continue;

            if (p1 > p2) {
                player1.setHasTurn(true);
                break;
            } else {
                player2.setHasTurn(true);
                break;
            }
        }
    }

    private void gameProcess(Player currentPlayer, Player opponentPlayer) {
        MoveProcessor moveProcessor = new MoveProcessor(currentPlayer, opponentPlayer);
        while (true) {
            //System.out.println(Arrays.toString(opponentPlayer.getGameBoard().getAllShips()));
            System.out.println("Игрок " + currentPlayer.getName() + " сделайте выстрел");
            //System.out.println("Поле противника у меня");
            renderer.renderBoard(currentPlayer.getOpponentField());
          /*  System.out.println("Игровое поле соперника");
            renderer.renderMyBoard(opponentPlayer.getGameField());*/
            System.out.println(opponentPlayer.getStorageShips().getSize());
            boolean isHit = moveProcessor.doMove();

            if (opponentPlayer.isLost()) {
                System.out.println("Игрок:" + currentPlayer.getName() + " победил!!!");
                renderer.renderBoard(currentPlayer.getOpponentField());
                break;
            }
            if (!isHit) {
                opponentPlayer.setHasTurn(true);
                currentPlayer.setHasTurn(false);
                break;
            }
        }
    }

    public void createFleetsPlayers() {
        System.out.println("Игрок " + player1.getName() + " создайте флот");
        createFleet(player1);
        System.out.println("Игрок " + player2.getName() + " создайте флот");
        createFleet(player2);

    }

    public void createFleet(Player player) {
        for (TypeShip type : TypeShip.values()) {
            putShips(player, type.getMaxCount(), type.getSize());
        }
    }

    private void putShips(Player player, int total, int size) {
        int countSize = 0;
        int countTotal = total;
        //int countTotal = 0;
        Cell[] cells = new Cell[size];
        //while (countTotal < total) {
        while (countTotal != 0) {
            renderer.printMsg(total, countTotal, size);
            while (countSize < size) {
                Coordinates coordinates = Input.inputCoordinates();

                cells[countSize] = new Cell(coordinates);
                countSize++;
            }
            if (!player.addShip(cells)) {
                countSize = 0;
                continue;
            }
            //countTotal++;
            countTotal--;
            countSize = 0;
            renderer.renderBoard(player.getGameField());
        }
    }

    // методы создают и ставят корабли на поля для проверки
    /*private void createFleet(Player player) {
        Ship[] shipsAll = createDebugFleet();
        for (TypeShip type : TypeShip.values()) {
            for (Ship ship : shipsAll)
                if (ship.getTypeShip() == type) {
                    putShips(player, ship.getCells());//type.getMaxCount(), type.getSize());
                }
        }
    }

    private void putShips(Player player, Cell[] shipCells) { //int total,int size) {
        //System.out.println("Добавьте " + total + " " + size + "х-клеточных корабля");
        int countSize = 0;
        int countTotal = 0;
        Cell[] cells = new Cell[shipCells.length];//new Cell[size];
        //while (countTotal < total) {
        //System.out.println("Добавьте " + countTotal + " " + size + "х-клеточных корабля");
        while (countSize < cells.length) { //size) {
            //Coordinates coordinates = InputCoordinates.input();

            cells[countSize] = shipCells[countSize]; //new Cell(coordinates);
            countSize++;
        }
        if (!player.addShip(cells)) {
            countSize = 0;
            //continue;
        }
        //countTotal++;
        //countSize = 0;
        //player.addShip(cells);
        renderer.renderMyBoard(player.getGameField());
        //}
    }

    private Ship[] createDebugFleet() {
        return new Ship[]{
                new Ship(new Cell[]{
                        new Cell(new Coordinates(Letter.A, 0)),
                        new Cell(new Coordinates(Letter.A, 1)),
                        new Cell(new Coordinates(Letter.A, 2)),
                        new Cell(new Coordinates(Letter.A, 3))
                }, TypeShip.AIRCRAFT_CARRIER),

                new Ship(new Cell[]{
                        new Cell(new Coordinates(Letter.C, 0)),
                        new Cell(new Coordinates(Letter.C, 1)),
                        new Cell(new Coordinates(Letter.C, 2))
                }, TypeShip.DESTROYER),

                new Ship(new Cell[]{
                        new Cell(new Coordinates(Letter.C, 5)),
                        new Cell(new Coordinates(Letter.C, 6)),
                        new Cell(new Coordinates(Letter.C, 7))
                }, TypeShip.DESTROYER),

                new Ship(new Cell[]{
                        new Cell(new Coordinates(Letter.E, 0)),
                        new Cell(new Coordinates(Letter.E, 1))
                }, TypeShip.SUBMARINE),

                new Ship(new Cell[]{
                        new Cell(new Coordinates(Letter.E, 4)),
                        new Cell(new Coordinates(Letter.E, 5))
                }, TypeShip.SUBMARINE),

                new Ship(new Cell[]{
                        new Cell(new Coordinates(Letter.E, 8)),
                        new Cell(new Coordinates(Letter.E, 9))
                }, TypeShip.SUBMARINE),

                new Ship(new Cell[]{
                        new Cell(new Coordinates(Letter.G, 0))
                }, TypeShip.FRIGATE),

                new Ship(new Cell[]{
                        new Cell(new Coordinates(Letter.G, 3))
                }, TypeShip.FRIGATE),

                new Ship(new Cell[]{
                        new Cell(new Coordinates(Letter.G, 6))
                }, TypeShip.FRIGATE),

                new Ship(new Cell[]{
                        new Cell(new Coordinates(Letter.I, 9))
                }, TypeShip.FRIGATE)
        };
    }*/
}