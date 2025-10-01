package lab2.task3;

public class Bell {
    public boolean flag = true;
    public void sound() {
        if (flag == true) System.out.println("ding");
        else System.out.println("dong");
        flag = !flag;
    }
}
