public class GameNotFoundException extends Exception {
    public GameNotFoundException (){
        super("There is no game found");
    }
}
