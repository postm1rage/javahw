import java.util.Random;

public class Producer extends Thread {
    private final ShoeWarehouse warehouse;
    private final int n;

    public Producer(ShoeWarehouse warehouse, int n) {
        this.warehouse = warehouse;
        this.n = n;
    }

    @Override
    public void run() {
        Random rnd = new Random();
        for (int i = 1; i <= n; i++) {
            String shoe = ShoeWarehouse.SHOE_TYPES.get(rnd.nextInt(ShoeWarehouse.SHOE_TYPES.size()));
            int quantity = rnd.nextInt(5) + 1;
            Order order = new Order(i, shoe, quantity);
            try {
                warehouse.receiveOrder(order);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
