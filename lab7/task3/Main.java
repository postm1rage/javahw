import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService orderExecutor = Executors.newCachedThreadPool();

        ShoeWarehouse warehouse = new ShoeWarehouse(3);

        int n = 10;
        for (int i = 1; i <= n; i++) {
            String orderName = "Order #" + i;
            ShoeOrder order = new ShoeOrder(orderName);

            orderExecutor.submit(() -> warehouse.processOrder(order));
        }

        orderExecutor.shutdown();
        warehouse.shutdown();
    }
}
