package lab2.task1;

public class Button {
    public int clicks = 0;
    public String name;
    public Button(String name) {
        this.name = name;
    }
    public void click() {
        clicks += 1;
        System.out.println(name + " total clicks: " + clicks);
    }
}
