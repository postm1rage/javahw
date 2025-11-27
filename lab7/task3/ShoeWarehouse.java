import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShoeWarehouse {
    private final ExecutorService warehouseExecutor;

    public ShoeWarehouse(int nThreads) {
        warehouseExecutor = Executors.newFixedThreadPool(nThreads);
    }

    public void processOrder(ShoeOrder order) {
        warehouseExecutor.submit(order);
    }

    public void shutdown() {
        warehouseExecutor.shutdown();
    }
}
