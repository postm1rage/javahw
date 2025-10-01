package lab2.task2;

public class Task2 {
    public static void main(String[] args) {
        Balance balance1 = new Balance();
        Balance balance2 = new Balance();
        Balance balance3 = new Balance();

        balance1.addLeft(52);
        balance1.addRight(52);
        balance1.showScales();
        balance1.result();

        balance2.addLeft(14);
        balance2.addRight(88);
        balance2.showScales();
        balance2.result();

        balance3.addLeft(322);
        balance3.addRight(228);
        balance3.showScales();
        balance3.result();
    }
}
