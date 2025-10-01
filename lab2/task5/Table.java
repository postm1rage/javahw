package lab2.task5;
import java.util.List;
import java.util.ArrayList;

public class Table {
    private List<List<Integer>> data;
    private int rows;
    private int cols;
    
    public Table(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new ArrayList<>();
        
        for (int i = 0; i < rows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(0);
            }
            data.add(row);
        }
    }
    
    public int getValue(int row, int col) {
        return data.get(row).get(col);
    }

    public void setValue(int row, int col, int value) {
        data.get(row).set(col, value);
    }

    public int rows() {
        return rows;
    }

    public int cols() {
        return cols;
    }

    @Override
    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();
        for (List<Integer> row : data) {
            stringbuilder.append(row).append("\n");
        }
        return stringbuilder.toString();
    }

    public double average() {
        int sum = 0;
        int positions = rows * cols;
        for (List<Integer> row : data) {
            for (int value : row) {
                sum += value;
            }
        }
        return sum/positions;
    }
}
