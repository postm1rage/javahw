public class Order {
    private final int id;
    private final String shoeType;
    private final int quantity;

    public Order(int id, String shoeType, int quantity) {
        this.id = id;
        this.shoeType = shoeType;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getShoeType() {
        return shoeType;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Order{id=" + id + ", shoeType='" + shoeType + "', quantity=" + quantity + "}";
    }
}
