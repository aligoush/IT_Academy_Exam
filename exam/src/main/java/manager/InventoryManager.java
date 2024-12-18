package manager;

import enums.Category;
import enums.Difficulty;
import exceptions.EmptyListException;
import exceptions.GameAlreadyExistsException;
import exceptions.GameNotFoundException;
import model.*;
import utils.IDGenerator;

import java.util.List;

public class InventoryManager {
    public Inventory<BoardGames> inventory;
    public InventoryManager() {
        this.inventory = new Inventory<>();
    }

    public void checkIfGameExists(String gameName) throws GameAlreadyExistsException {
        if (inventory.getGame(gameName) != null) {
            throw new GameAlreadyExistsException("The game '" + gameName + "' already exists!");
        }
    }

    public void checkIfGameExists(int id) throws GameNotFoundException {
        if (inventory.getGame(id) == null) {
            throw new GameNotFoundException("The game with ID '" + id + "' not found.");
        }
    }

    public BoardGames getGameByID(int id){
        return inventory.getGame(id);
    }

    public BoardGames getGameByName(String gameName){
        return inventory.getGame(gameName);
    }

    public void addGameToInventory(String name, Category category, Difficulty difficulty, int quantity, double price){
        BoardGames game;
        int newId = IDGenerator.generateId();

        if(category == Category.GameOfChance){
            game = new GameOfChance(newId,name,category,difficulty,price);
        } else if(category == Category.RolePlaying){
            game = new RolePlaying(newId,name,category,difficulty,price);
        } else if(category == Category.Strategy){
            game = new Strategy(newId,name,category,difficulty,price);
        } else {
            throw new IllegalArgumentException("There is no such category: " + category); // excepcion personalizada??
        }
        this.inventory.addNewGame(game, quantity);
    }

    public void showInventory() throws EmptyListException {
        if(inventory.games.isEmpty()){
            throw new EmptyListException("The are no games in the inventory.");
        }
        inventory.games.forEach((game,quantity)->System.out.println("Game: " + game.getName() +
                ", Quantity: " + quantity +
                ", Total price: " + game.calculatePriceWithIVA()*quantity + " euros"));
    }

    public void showAllGames(){
        inventory.games.keySet().forEach(System.out::println);
    }

    public void updateInventory(int id, int quantity){
        BoardGames game = getGameByID(id);
        this.inventory.updateQuantityOfGame(game,quantity);
    }

    public void removeGame(int id){
        BoardGames game = getGameByID(id);
        this.inventory.removeGame(game);
    }

    public void showGamesByCategory(Category category) throws GameNotFoundException {
        List<BoardGames> gamesByCategory = inventory.getGameByCategory(category);
        if(gamesByCategory.isEmpty()){
            throw new GameNotFoundException("No games found with category: " + category);
        }
        gamesByCategory.forEach(System.out::println);
    }

    public void showGamesByDifficulty(Difficulty difficulty) throws GameNotFoundException {
        List<BoardGames> gamesByDifficulty = inventory.getGameByDifficulty(difficulty);
        if(gamesByDifficulty.isEmpty()){
            throw new GameNotFoundException("No games found with difficulty: " + difficulty);
        }
        gamesByDifficulty.forEach(System.out::println);
    }
}