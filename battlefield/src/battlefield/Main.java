package battlefield;

public class Main {

    public static void main(String[] args) {


        Renderer renderer = new Renderer();
        Game game = new Game(renderer);

        game.game();


    }

}
