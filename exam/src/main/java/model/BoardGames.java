package model;


import enums.Category;
import enums.Difficulty;

public abstract class BoardGames {
    private String name;
    private final int id;
    private Difficulty difficulty;
    private double price;
    private Category category;
    private final double IVA = 0.1;

    public BoardGames(int id, String name,Category category, Difficulty difficulty, double price){
        this.id = id;
        this.name = name;
        this.category = category;
        this.difficulty = difficulty;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double calculatePriceWithIVA() {
        return Math.round((this.price + this.price * IVA) * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return "{" +
                "name = " + name  +
                ", id = " + id  +
                ", category = " + category +
                ", difficulty = " + difficulty +
                ", price = " + calculatePriceWithIVA() +
                '}';
    }
}
