public class ShoeOrder implements Runnable {
    private final String orderName;

    public ShoeOrder(String orderName) {
        this.orderName = orderName;
    }

    @Override
    public void run() {
        System.out.println("Processing order: " + orderName + " - " + Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Order completed: " + orderName);
    }
}
