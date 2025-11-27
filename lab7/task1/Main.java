public class Main {
    public static void main(String[] args) {
        Thread even = new JavaLangThread();
        Thread odd = new Thread(new RunnableThread());

        even.start();
        odd.start();
    }
}