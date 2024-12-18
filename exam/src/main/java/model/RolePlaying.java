package model;


import enums.Category;
import enums.Difficulty;

public class RolePlaying extends BoardGames {
    public RolePlaying(int id, String name, Category category, Difficulty difficulty, double price){
        super(id, name, category, difficulty, price);
    }
}
