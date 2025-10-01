package lab2.task4;

import java.util.List;
import java.util.ArrayList;

public class OddEvenSeparator {
    private List<Integer> evenNumbers;
    private List<Integer> oddNumbers;
    private List<Integer> numbers;
    
    public OddEvenSeparator() {
        numbers = new ArrayList<>(); // смотреть комментарий ниже
        evenNumbers = new ArrayList<>();
        oddNumbers = new ArrayList<>();
    }
    
    public void addNumber(int number) {
        numbers.add(number);  // так надо по заданию ("Числа добавляются в объект с помощью метода addNumber"), а так бесполезная строка
        if (number % 2 == 0) {
            evenNumbers.add(number);
        } else {
            oddNumbers.add(number);
        }
    }
    
    public void even() {
        System.out.println(evenNumbers);
    }
    
    public void odd() {
        System.out.println(oddNumbers);
    }
}