public class Consumer extends Thread {
    private final ShoeWarehouse warehouse;
    private final int ordersToProcess;

    public Consumer(ShoeWarehouse warehouse, int ordersToProcess) {
        this.warehouse = warehouse;
        this.ordersToProcess = ordersToProcess;
    }

    @Override
    public void run() {
        for (int i = 0; i < ordersToProcess; i++) {
            try {
                warehouse.fulfillOrder();
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
