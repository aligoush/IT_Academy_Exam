package model;


import enums.Category;
import enums.Difficulty;

public class Strategy extends BoardGames {
    public Strategy(int id, String name, Category category, Difficulty difficulty, double price){
        super(id, name, category, difficulty, price);
    }
}
