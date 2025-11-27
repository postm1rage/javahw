public class Main {
    public static void main(String[] args) {
        int totalOrders = 20;

        ShoeWarehouse warehouse = new ShoeWarehouse();

        Producer producer = new Producer(warehouse, totalOrders);
        producer.start();

        int consumerCount = totalOrders / 5;
        Consumer[] consumers = new Consumer[consumerCount];
        for (int i = 0; i < consumerCount; i++) {
            consumers[i] = new Consumer(warehouse, 5);
            consumers[i].start();
        }

        try {
            producer.join();
            for (Consumer c : consumers) {
                c.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All orders processed.");
    }
}
