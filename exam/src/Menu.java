import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);
    Inventory<BoardGames> inventory;

    public Menu() {
        this.inventory = new Inventory<>();
    }

    public void showMenu() {
        int option = -1;
        int option2 = 0;
        while (option != 0) {
            System.out.println("1.- Add new game");
            System.out.println("2.- Update the quantity of the game");
            System.out.println("3.- Show all the games");
            System.out.println("4.- Show the game");
            System.out.println("5.- Remove the game");
            System.out.println("5.- Show the game by category or difficulty");
            System.out.println("0.- Exit.");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    addNewGame();
                    break;
                case 2:
                    updateQuantityGame();
                    break;
                case 3:
                    showGames();
                    break;
                case 4:
                    //showGame();
                    break;
                case 5:
                    removeGame();
                    break;
                case 6:
                    System.out.println("By category(1) or difficulty(2)");
                    option2 = scanner.nextInt();
                    if (option2 == 1) {
                        //showGameCategory();
                    } else {
                        //showGameDifficulty();
                    }
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public void addNewGame() {
        String gameName = InputUtils.readInput("Enter game's name: ");
        String category = InputUtils.readInput("Enter the category of the game: ").toLowerCase();
        int id = InputUtils.readIntInput("Enter the id");
        BoardGames.Difficulty difficulty = BoardGames.Difficulty.valueOf(InputUtils.readInput("Enter the difficulty of the game: ").toUpperCase());
        int quantity = InputUtils.readIntInput("How many game of this type do you have?");
        double price = InputUtils.readDoubleInput("Enter the price of the game: ");

        BoardGames game = findGame(gameName);
        if (game != null) {
            if (category.equals("strategy")) {
                 game = new Strategy(id, gameName, difficulty, quantity, price);
                inventory.addNewGame(game);
            }
            if (category.equals("role-playing")) {
                 game = new RolePlaying(id, gameName, difficulty, quantity, price);
                inventory.addNewGame(game);
            }
            if (category.equals("game of chance")) {
                 game = new GameOfChance(id, gameName, difficulty, quantity, price);
                inventory.addNewGame(game);
            }
        } else {
            if(game instanceof RolePlaying) {
                ((RolePlaying) game).setQuantity(quantity); // ((RolePlaying) game).setQuantity(game.setQuantity(quantity))
            }
            if(game instanceof GameOfChance) {
                ((GameOfChance) game).setQuantity(quantity);
            }
            if(game instanceof Strategy) {
                ((GameOfChance) game).setQuantity(quantity);
            }

        }
    }

    public BoardGames findGame(String gameName){
        BoardGames game;
        try {
            game = inventory.getGame(gameName);
        } catch (GameNotFoundException e) {
            return null;
        }
        return game;
    }

    public void updateQuantityGame(){
        String gameName = InputUtils.readInput("Enter game's name: ");
        BoardGames game = findGame(gameName);
        if (game != null) {
            int quantity = InputUtils.readIntInput("How many game of this type do you have?");
            if (game instanceof RolePlaying) {
                ((RolePlaying) game).setQuantity(quantity);
            }
            if (game instanceof GameOfChance) {
                ((GameOfChance) game).setQuantity(quantity);
            }
            if (game instanceof Strategy) {
                ((Strategy) game).setQuantity(quantity);
            }
        }
    }
    public void removeGame(){
        String gameName = InputUtils.readInput("Enter game's name: ");
        BoardGames game = findGame(gameName);
        if (game != null) {
            inventory.games.remove(game);
        }
    }

    public void showGames(){
        inventory.games.forEach(System.out::println);
    }
}
