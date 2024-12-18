import enums.Category;
import enums.Difficulty;
import exceptions.GameAlreadyExistsException;
import model.BoardGames;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import ui.Menu;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    private Menu menu;

    @BeforeEach
    void setUp(){
        menu = new Menu();
       // menu.inventoryManager = inventoryManager;
    }

    @Test
    void addNewGameSuccessfully(){
        String name = "Folks";
        Category category = Category.Strategy;
        Difficulty difficulty = Difficulty.MEDIUM;
        int quantity = 5;
        double price = 15.0;

        menu.inventoryManager.addGameToInventory(name, category, difficulty, quantity, price);

        List<BoardGames> gamesByCategory = menu.inventoryManager.inventory.getGameByCategory(category);
        assertFalse(gamesByCategory.isEmpty());

        BoardGames addedGame = gamesByCategory.get(0);
        assertEquals(name, addedGame.getName());
        assertEquals(category, addedGame.getCategory());
        assertEquals(difficulty, addedGame.getDifficulty());
        assertEquals(price, addedGame.getPrice());
    }

    @Test
    void addDuplicateGameThrowsException(){
        String name = "Folks";
        Category category = Category.Strategy;
        Difficulty difficulty = Difficulty.MEDIUM;
        int quantity = 5;
        double price = 15.0;

        menu.inventoryManager.addGameToInventory(name, category, difficulty, quantity, price);

        GameAlreadyExistsException exception = assertThrows(GameAlreadyExistsException.class, () -> {
            menu.inventoryManager.checkIfGameExists(name);
            menu.inventoryManager.addGameToInventory(name, category, difficulty, quantity, price);
        });

        assertEquals("The game '" + name + "' already exists!", exception.getMessage());
    }
}
