public class Strategy extends BoardGames{
    private final String category = "strategy";
    private int quantity;
    public Strategy(int id, String name, Difficulty difficulty, int quantity, double price){
        super(id,name,difficulty,price);
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
