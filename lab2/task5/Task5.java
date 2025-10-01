package lab2.task5;

public class Task5 {
    public static void main(String[] args) {
        Table table = new Table(3,3);

        System.out.println("Table's rows: " + table.rows());
        System.out.println("Table's columns: " + table.cols());

        System.out.println("Table at the beginning: ");
        System.out.print(table.toString());

        table.setValue(0, 0, 52);
        table.setValue(0, 1, 1488);
        table.setValue(0, 2, 1337);
        table.setValue(1, 0, 42);
        table.setValue(1, 1, 228);
        table.setValue(1, 2, 69);
        table.setValue(2, 0, 69);
        table.setValue(2, 1, 69);
        table.setValue(2, 2, 69);

        System.out.println("Table after setting values: ");
        System.out.print(table.toString());


        System.out.println("Table's separate values: ");
        System.out.println(table.getValue(0, 0));
        System.out.println(table.getValue(0, 1));
        System.out.println(table.getValue(0, 2));
        System.out.println(table.getValue(1, 0));
        System.out.println(table.getValue(1, 1));
        System.out.println(table.getValue(1, 2));
        System.out.println(table.getValue(2, 0));
        System.out.println(table.getValue(2, 1));
        System.out.println(table.getValue(2, 2));

        System.out.println("Table's average value: " + table.average());
    }
}
