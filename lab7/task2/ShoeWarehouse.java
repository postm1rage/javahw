import java.util.ArrayList;
import java.util.List;

public class ShoeWarehouse {
    public static final List<String> SHOE_TYPES = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();
    private final int MAX_CAPACITY = 10;

    static {
        SHOE_TYPES.add("Sneakers");
        SHOE_TYPES.add("Boots");
        SHOE_TYPES.add("Sandals");
        SHOE_TYPES.add("Loafers");
    }

    public synchronized void receiveOrder(Order order) throws InterruptedException {
        while (orders.size() >= MAX_CAPACITY) {
            wait();
        }
        orders.add(order);
        System.out.println("Received: " + order);
        notifyAll();
    }

    public synchronized Order fulfillOrder() throws InterruptedException {
        while (orders.isEmpty()) {
            wait();
        }
        Order order = orders.remove(0);
        System.out.println("Fulfilled: " + order);
        notifyAll();
        return order;
    }
}
