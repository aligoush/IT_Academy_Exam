package ui;

import enums.Category;
import enums.Difficulty;
import exceptions.EmptyListException;
import exceptions.GameAlreadyExistsException;
import exceptions.GameNotFoundException;
import manager.InventoryManager;
import model.BoardGames;
import utils.InputUtils;

import java.util.Scanner;

public class Menu {
    InventoryManager inventoryManager;

    public Menu() {
        this.inventoryManager = new InventoryManager();
    }

    public void showMenu() {
        boolean exit = false;
        do{
            switch (chooseOption()) {
                case 1:
                    addNewGame();
                    break;
                case 2:
                    updateQuantityGame();
                    break;
                case 3:
                    showInventory();
                    break;
                case 4:
                    showGameByName();
                    break;
                case 5:
                    removeGame();
                    break;
                case 6:
                    showGameByCategoryOrDifficulty();
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    exit = true;
                    break;
            }
        } while(!exit);
    }

    public static byte chooseOption() {
        Scanner scanner = new Scanner(System.in);
        byte option;
        final byte MIN = 0;
        final byte MAX = 6;

        do {
            System.out.println("\nmain.java.ui.Menu:");
            System.out.println("1.- Add new game");
            System.out.println("2.- Update the quantity of the game");
            System.out.println("3.- Show all the games");
            System.out.println("4.- Show the game");
            System.out.println("5.- Remove the game");
            System.out.println("6.- Show the game by category or difficulty");
            System.out.println("0.- Exit.");

            option = scanner.nextByte();
            if (option < MIN || option > MAX) {
                System.out.println("Choose valid option");
            }
        } while (option < MIN || option > MAX);
        return option;
    }

    public void addNewGame(){
        String gameName = InputUtils.readInput("Enter game's name: ");

        try {
            inventoryManager.checkIfGameExists(gameName);
        } catch (GameAlreadyExistsException e){
            System.out.println(e.getMessage());
            return;
        }

        Category category = InputUtils.readEnum("Enter the category of the game: ", Category.class);
        Difficulty difficulty = InputUtils.readEnum("Enter the difficulty of the game: ", Difficulty.class);
        int quantity = InputUtils.readIntInput("How many game of this type do you have?");
        double price = InputUtils.readDoubleInput("Enter the price of the game: ");

        inventoryManager.addGameToInventory(gameName,category,difficulty,quantity,price);
    }

    public void updateQuantityGame(){
        inventoryManager.showAllGames();

        int id = InputUtils.readIntInput("Enter game's id to update: ");

        try {
            inventoryManager.checkIfGameExists(id);
        } catch (GameNotFoundException e) {
            System.out.println("The game with ID '" + id + "' not found.");
            return;
        }
        int quantity = InputUtils.readIntInput("How many games of this type do you have?");
        inventoryManager.updateInventory(id,quantity);
    }


    public void showInventory(){
        try {
            inventoryManager.showInventory();
        } catch (EmptyListException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeGame(){
        inventoryManager.showAllGames();

        int id = InputUtils.readIntInput("Enter game's id to update: ");
        try {
            inventoryManager.checkIfGameExists(id);
        } catch (GameNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        inventoryManager.removeGame(id);
    }

    public void showGameByName(){
        String gameName = InputUtils.readInput("Enter game's name: ");

        try {
            BoardGames game = inventoryManager.getGameByName(gameName);
            if(game == null){
                throw new GameNotFoundException("The game " + gameName + " doesn't exist !");
            }
            System.out.println(game);
        } catch (GameNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void showGameByCategoryOrDifficulty(){
        System.out.println("Choose between 1-2 if you want the game by category(1) or by difficulty(2)");
        int option = InputUtils.readIntInput("Enter numbers between 1-2: ");

        if (option == 1) {
            Category category = InputUtils.readEnum("Enter the category of the game: ", Category.class);
            try {
                inventoryManager.showGamesByCategory(category);
            } catch (GameNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } else if(option == 2){
            Difficulty difficulty = InputUtils.readEnum("Enter the difficulty of the game: ", Difficulty.class);
            try {
                inventoryManager.showGamesByDifficulty(difficulty);
            } catch (GameNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } else{
            System.out.println("There is no such option");
        }
    }
}
