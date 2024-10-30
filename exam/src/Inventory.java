import java.util.ArrayList;
import java.util.List;

public class Inventory <G extends BoardGames> {
    List<G> games;
    public Inventory(){
        this.games = new ArrayList<G>();
    }
    public void addNewGame(G game){
        games.add(game);
    }
    public G getGame(String name) throws GameNotFoundException {
        return games.stream()
                .filter(g -> g.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new GameNotFoundException());

    }
}
