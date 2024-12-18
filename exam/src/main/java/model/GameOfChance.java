package model;


import enums.Category;
import enums.Difficulty;

public class GameOfChance extends BoardGames{
    public GameOfChance(int id, String name, Category category, Difficulty difficulty, double price){
        super(id, name, category, difficulty, price);
    }
}
