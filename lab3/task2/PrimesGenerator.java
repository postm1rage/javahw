package lab3.task2;

import java.util.*;

public class PrimesGenerator implements Iterator<Integer> {
    private int count;
    private int current = 2;
    private int generated = 0;
    
    public PrimesGenerator(int count) {
        this.count = count;
    }
    
    @Override
    public boolean hasNext() {
        return generated < count;
    }
    
    @Override
    public Integer next() {
        while (!isPrime(current)) {
            current++;
        }
        int prime = current;
        current++;
        generated++;
        return prime;
    }
    
    private boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
