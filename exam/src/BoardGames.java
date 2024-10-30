public class BoardGames {
    private String name;
    private final int id;
    enum Difficulty {
        EASY,
        MEDIUM,
        HARD
    }
    Difficulty difficulty;
    private double price;
    public BoardGames(int id, String name, Difficulty difficulty, double price){
        this.id = id;
        this.name = name;
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

}
