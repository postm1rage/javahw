package lab2.task4;

public class Task4 {
    public static void main(String[] args) {
        OddEvenSeparator separator = new OddEvenSeparator();
        
        separator.addNumber(1);
        separator.addNumber(2);
        separator.addNumber(3);
        separator.addNumber(4);
        separator.addNumber(5);
        
        System.out.print("Even: ");
        separator.even();
        
        System.out.print("Odd: ");
        separator.odd();
    }
}
