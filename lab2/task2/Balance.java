package lab2.task2;

public class Balance {
    public int leftScale = 0;
    public int rightScale = 0;

    public void addLeft(int weight) {
        leftScale += weight;
    }

    public void addRight(int weight) {
        rightScale += weight;
    }

    public void showScales() {
        System.out.print("Left scale: " + leftScale + ", ");
        System.out.println("Right scale: " + rightScale);
    }

    public void result() {
        if (rightScale == leftScale) System.out.println("=");
        else if (rightScale > leftScale) System.out.println("R");
        else System.out.println("L");
    }
}
