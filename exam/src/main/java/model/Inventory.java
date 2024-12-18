package model;


import enums.Category;
import enums.Difficulty;

import java.util.HashMap;
import java.util.List;

public class Inventory <G extends BoardGames> {
    public HashMap<G,Integer> games;

    public Inventory(){
        this.games = new HashMap<>();
    }

    public void addNewGame(G game, int quantity){
        games.put(game,quantity);
    }

    public G getGame(String name) {
        return games.keySet().stream()
                .filter(g -> g.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public G getGame(int id) {
        return games.keySet().stream()
                .filter(g -> g.getId()==id)
                .findFirst()
                .orElse(null);
    }

    public List<G> getGameByCategory(Category category) {
         return games.keySet().stream()
                .filter(g -> g.getCategory() == category)
                .toList();
    }

    public List<G> getGameByDifficulty(Difficulty difficulty){
        return games.keySet().stream()
                .filter(g -> g.getDifficulty() == difficulty)
                .toList();
    }

    public void updateQuantityOfGame(G game, int quantity){
        games.replace(game,quantity);
        System.out.println(game);
    }

    public void removeGame(G game){
        games.remove(game);
        System.out.println("Game was successfully removed.");
    }
}
