public class RunnableThread implements Runnable {
    public void run() {
        for (int i = 1; i <= 9; i += 2) {
            System.out.println(i);
        }
    }
}